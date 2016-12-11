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
    private List<RandomSpriteCluster> myRepeated;
    private double myRepeatTime, myCurrentTime, myXRange, myYRange;
    
    public RandomGenerationController (Level level,
                                       List<RandomSpriteCluster> toRepeat
                                       ) {
        myLevel = level;
        myRepeated = toRepeat;
    }

    public void generateSprites (double elapsedTime) {
        for(RandomSpriteCluster si : myRepeated){
            if(si.shouldRender(elapsedTime)){
                List<ISprite> sprites = si.getSprites();
                for(ISprite s : sprites){
                    double xPos = myLevel.getBoundary().right() + OFFSET;
                    double yPos = 0;
                    Position position = new Position(xPos, yPos);
                    s.getPosition().addPosition(position);
                    myLevel.addSprite(s);
                }
            }
        }
    }
}
