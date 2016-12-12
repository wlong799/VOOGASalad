package game_engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import game_engine.collision.Boundary;
import game_engine.collision.CollisionEngine;
import game_engine.collision.ICollisionEngine;
import game_engine.enemyai.EnemyControllerFactory;
import game_engine.enemyai.IEnemyController;
import game_engine.enemyai.IEnemyControllerFactory;
import game_engine.inputcontroller.InputController;
import game_engine.physics.IPhysicsEngine;
import game_engine.physics.PhysicsEngineWithFriction;
import game_engine.physics.PhysicsHeroFollower;
import game_engine.physics.PhysicsParameterSetOptions;
import game_engine.random.RandomGenerationController;
import game_engine.random.RandomSpriteCluster;
import game_engine.random.SpriteInfo;
import game_engine.transition.ITransitionManager;
import game_engine.transition.TransitionManager;
import game_engine.transition.WinStatus;
import game_object.acting.ActionName;
import game_object.acting.Event;
import game_object.background.Background;
import game_object.block.Block;
import game_object.character.Enemy;
import game_object.character.Hero;
import game_object.character.IMover;
import game_object.constants.GameObjectConstants;
import game_object.core.AbstractSprite;
import game_object.core.Dimension;
import game_object.core.Game;
import game_object.core.ISprite;
import game_object.core.Position;
import game_object.core.Velocity;
import game_object.level.Level;
import game_object.simulation.IPhysicsBody;
import game_object.visualization.ISpriteVisualization;
import game_object.weapon.Projectile;
import goal.IGoal;
import goal.time.TimeGoal;


public class GameEngine_Game implements IGameEngine {

    private Level myCurrentLevel;
    private IPhysicsEngine myPhysicsEngine;
    private ICollisionEngine myCollisionEngine;
    private ITransitionManager myTransitionManager;
    private InputController myInputController;
    private IEnemyController myEnemyController;
    private IEnemyControllerFactory myEnemyControllerFactory;
    private RandomGenerationController myGenerator;
    private double myElapsedTime;
    private double myTotalTime;
    private int myFPS;
    private boolean logSuppressed = false;

    public GameEngine_Game (Game game) {
        myCurrentLevel = game.getAllLevelsReadOnly().get(0);
        init();
        game.setCurrentLevel(myCurrentLevel);
        myCurrentLevel.init();
        myPhysicsEngine = new PhysicsEngineWithFriction(myCurrentLevel);
        myCollisionEngine = new CollisionEngine();
        myInputController = new InputController(game);
        myEnemyControllerFactory = new EnemyControllerFactory();
        myEnemyController =
                myEnemyControllerFactory.createEnemyController(game.getEnemyDifficulty());
        myTransitionManager = new TransitionManager(game, myCurrentLevel);
        myFPS = game.getFPS();
        myTotalTime = 0;
        myGenerator = game.getRandomGenerationController();
    }

    private void initRandom () {
        List<SpriteInfo> sprites = new ArrayList<SpriteInfo>();
        List<String> imagePaths = new ArrayList<String>();
        imagePaths.add(GameObjectConstants.WARP_PIPE_DOWN);
        Dimension dim = new Dimension(100, 400);
        RandomSpriteCluster rsc = new RandomSpriteCluster(0, 300, 5);
        SpriteInfo si = new SpriteInfo(Block.class, imagePaths, dim, new Position(0, -400));
        imagePaths = new ArrayList<String>();
        imagePaths.add(GameObjectConstants.WARP_PIPE);
        SpriteInfo bottomSi = new SpriteInfo(Block.class, imagePaths, dim, new Position(0, 200));
        sprites.add(si);
        sprites.add(bottomSi);
        sprites.forEach(s -> rsc.addSprite(s));
        List<RandomSpriteCluster> clusters = new ArrayList<RandomSpriteCluster>();
        clusters.add(rsc);
        myGenerator = new RandomGenerationController(myCurrentLevel, clusters);
    }

    public void suppressLogDebug () {
        logSuppressed = true;
        myCollisionEngine.suppressLogDebug();
    }

    private void init () {
        // setElements(myCurrentLevel);
        myCurrentLevel.init();
    }

    @Override
    public void shutdown () {
        System.out.println("SHUT THE FUCK UP. I MEAN DOWN");
        System.exit(0);
        return;
    }

    @Override
    public void update (double elapsedTime) {
        endCheck();
        updateTime();
        myGenerator.generateSprites(elapsedTime);
        setElapsedTime(elapsedTime);
        executeInput(); // input for heroes
        for (ISprite s : myCurrentLevel.getAllSprites()) {
            // mimic enemy behavior; treat them as players
            if (s instanceof Enemy && ((Enemy) s).hasAI()) {
                IMover enemy = (IMover) s;
                Set<ActionName> list =
                        myEnemyController.getActions(enemy, myCurrentLevel.getHeros().get(0));
                myEnemyController.executeInput(enemy, list);
                List<Projectile> plist = myEnemyController.getNewProjectiles();
                for (Projectile p : plist) {
                    myCurrentLevel.addSprite(p);
                }
            }
            updateNewParameters(s);
        }
        if (!logSuppressed) {
            System.out.println(myCurrentLevel.getHeros().get(0));
        }
        myCollisionEngine.checkCollisions(myCurrentLevel.getAllSprites()
        // myCurrentLevel.getProjectiles(),
        );
        updateLevel();
    }

    public void updateTime () {
        myTotalTime += 1.0 / myFPS;
    }

    @Override
    public void setInputList (Set<Event> list) {
        myInputController.setInputList(list);
    }

    @Override
    public void setParameter (PhysicsParameterSetOptions option, double value) {
        myPhysicsEngine.setParameters(option, value);
    }

    private void updateLevel () {
        Hero pivotHero = myCurrentLevel.getHeros().get(0);
        if (pivotHero != null) {
            AbstractSprite.getStaticPivotPosition().setX(pivotHero.getPosition().getX());
            AbstractSprite.getStaticPivotPosition().setY(pivotHero.getPosition().getY());
        }
        myCurrentLevel.update();
    }

    private void updateNewParameters (IPhysicsBody body) {
        Position newPosition = myPhysicsEngine.calculateNewPosition(body, myElapsedTime);
        Velocity newVelocity = myPhysicsEngine.calculateNewVelocity(body, myElapsedTime);
        myPhysicsEngine.updatePositionAndVelocity(newPosition, newVelocity, body);
    }

    private void endCheck () {
        WinStatus ws = checkWin();
        if (ws != WinStatus.GO_ON) {
            if (!logSuppressed) {
                System.out.println("transition");
                System.out.println(myCurrentLevel);
            }

            
            myCurrentLevel = myTransitionManager.readWinStatus(ws);
            if (myCurrentLevel == null) {
                System.out.println("MADE IT");
                shutdown();
            }
            myPhysicsEngine.setLevel(myCurrentLevel);
            init();
        }
    }

    private WinStatus checkWin () {
        List<IGoal> myGoals = myCurrentLevel.getAllGoals();
        for (IGoal g : myGoals) {
            if (g instanceof TimeGoal) {
                ((TimeGoal) g).setCurrentTime(myTotalTime);
            }

            if (g.checkGoal()) {
                return g.getResult();
            }
        }
        return heroCheck();
    }

    private WinStatus heroCheck() {
        for(Hero h : myCurrentLevel.getHeros()){
            if(h.getDead()){
                return WinStatus.LOST;
            }
        }
        return WinStatus.GO_ON;
    }
    
    private void setElapsedTime (double elapsedTime) {
        myElapsedTime = elapsedTime;
    }

    private void executeInput () {
        myInputController.executeInput();
    }

    @Override
    public Background getBackground () {
        return myCurrentLevel.getBackground();
    }

    @Override
    public List<ISpriteVisualization> getSprites () {
        System.out.println(myCurrentLevel.getBoundary().right() + "right");
        System.out.println(myCurrentLevel.getBoundary().left());
        System.out.println(myCurrentLevel.getBoundary().top());
        System.out.println(myCurrentLevel.getBoundary().bottom());
        System.out.println(myCurrentLevel.getAllSpriteVisualizations().size() + " sprites");
        List<ISpriteVisualization> l =
                myCurrentLevel.getAllSprites().stream().filter(s -> myCurrentLevel.getBoundary()
                        .overlaps(new Boundary(new Position(s.getPosition().getX(),
                                                            s.getPosition().getY()),
                                               new Dimension(s.getDimension().getWidth(),
                                                             s.getDimension().getHeight()))))
                        .map(s -> (ISpriteVisualization) s).collect(Collectors.toList());

        System.out.println(l.size());
        return l;
    }
}
