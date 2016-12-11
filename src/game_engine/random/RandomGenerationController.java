package game_engine.random;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Random;
import game_object.core.Dimension;
import game_object.core.ISprite;
import game_object.core.Position;
import game_object.level.Level;


public class RandomGenerationController {

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
        System.out.println("Current time: " + myCurrentTime);
        System.out.println("Repeat time: " + myRepeatTime);
        myCurrentTime += elapsedTime;
        if (myCurrentTime >= myRepeatTime) {
            myCurrentTime = 0;
            generateSprites();
        }
    }

    private void generateSprites () {
        System.out.println("Generate sprite");
        System.out.println(myRepeated.get(0));
        for (SpriteInfo si : myRepeated) {
            Class<? extends ISprite> c = si.getSpriteClass();
            List<String> imagePaths = si.getImagePaths();
            Dimension dim = si.getDimension();
            try {
                Constructor<? extends ISprite> ctor =
                        c.getConstructor(Position.class, Dimension.class, List.class);
                double xPos = new Random().nextDouble()*myXRange;
                double yPos = new Random().nextDouble()*myYRange;
                Position position = new Position(xPos, yPos);
                ISprite sprite = ctor.newInstance(position, new Dimension(dim.getHeight(),dim.getWidth()), imagePaths);
                System.out.println("added new sprite");
                myLevel.addSprite(sprite);
                
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
