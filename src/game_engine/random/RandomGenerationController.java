package game_engine.random;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Random;
import game_object.core.Dimension;
import game_object.core.ISprite;
import game_object.core.Position;
import game_object.level.Level;


public class RandomGenerationController {
    private static final double OFFSET = 100;
    private Level myLevel;
    private List<SpriteInfo> myRepeated;
    private double myRepeatTime, myCurrentTime, myXRange, myYRange;
    
    public RandomGenerationController (Level level,
                                       List<SpriteInfo> toRepeat,
                                       double repeatTime) {
        myLevel = level;
        myRepeated = toRepeat;
        myRepeatTime = repeatTime;
        myCurrentTime = 0;
    }

    public void setGenerationMetrics (double timeout, double xRange, double yRange) {
        myXRange = xRange;
        myYRange = yRange;
    }

    public void generateSprites (double elapsedTime) {
        myCurrentTime += elapsedTime;
        if (myCurrentTime >= myRepeatTime) {
            myCurrentTime = 0;
            generateSprites();
        }
    }

    private void generateSprites () {
        for (SpriteInfo si : myRepeated) {
            Class<? extends ISprite> c = si.getSpriteClass();
            List<String> imagePaths = si.getImagePaths();
            Dimension dim = si.getDimension();
            try {
                Constructor<? extends ISprite> ctor =
                        c.getConstructor(Position.class, Dimension.class, List.class);
                double xPos = new Random().nextDouble()*myXRange + myLevel.getBoundary().right() + OFFSET;
                System.out.println(myLevel.getBoundary().getPosition().getX());
                System.out.println("my level boundary right " + myLevel.getBoundary().right());
                System.out.println(myLevel.getBoundary().getDimension().getWidth());
                System.out.println("X POS " + xPos);
                double yPos = new Random().nextDouble()*myYRange;
                Position position = new Position(xPos, yPos);
                ISprite sprite = ctor.newInstance(position, new Dimension(dim.getHeight(),dim.getWidth()), imagePaths);
                
                myLevel.addSprite(sprite);
                
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
