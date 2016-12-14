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
    private boolean scrollSideways;
    public RandomGenerationController (Level level,
                                       List<RandomSpriteCluster> toRepeat
                                       ) {
        myLevel = level;
        myRepeated = toRepeat;
        scrollSideways = false;
    }

    public void generateSprites (double elapsedTime) {
        for(RandomSpriteCluster si : myRepeated){
            if(si.shouldRender(elapsedTime)){
                List<ISprite> sprites = si.getSprites();
                double xPos, yPos;
                if(scrollSideways){
                    xPos = myLevel.getBoundary().right() + OFFSET;
                    yPos = 0;
                }
                else{
                    xPos = 0;
                    yPos = myLevel.getBoundary().top() - OFFSET;
                }
                for(ISprite s : sprites){
                    Position position = new Position(xPos, yPos);
                    s.getPosition().addPosition(position);
                    s.setPhysics(new ConstantStrategy());
                    s.setImageStyle(ImageStyle.FIT);
                    myLevel.addSprite(s);
                }
            }
        }
    }
    
    public void setSidewaysScrolling(boolean sideways){
        scrollSideways = sideways;
    }
}
