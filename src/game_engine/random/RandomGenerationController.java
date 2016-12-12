package game_engine.random;

import java.util.List;
import game_engine.physics.ConstantStrategy;
import game_object.core.ISprite;
import game_object.core.ImageStyle;
import game_object.core.Position;
import game_object.level.Level;


public class RandomGenerationController {
    private static final double OFFSET = 100;
    private Level myLevel;
    private List<RandomSpriteCluster> myRepeated;
    private int myCounter = 0;
    public RandomGenerationController (Level level,
                                       List<RandomSpriteCluster> toRepeat
                                       ) {
        myLevel = level;
        myRepeated = toRepeat;
    }

    public void generateSprites (double elapsedTime) {
        for(RandomSpriteCluster si : myRepeated){
            if(si.shouldRender(elapsedTime)){
                System.out.println("RENDERING");
                myCounter++;
                System.out.println(myCounter +" pipes");
                List<ISprite> sprites = si.getSprites();
                for(ISprite s : sprites){
                    
                    double xPos = myLevel.getBoundary().right() + OFFSET;
                    double yPos = 0;
                    Position position = new Position(xPos, yPos);
                    s.getPosition().addPosition(position);
                    s.setPhysics(new ConstantStrategy());
                    s.setImageStyle(ImageStyle.FIT);
                    myLevel.addSprite(s);
                }
            }
        }
    }
}
