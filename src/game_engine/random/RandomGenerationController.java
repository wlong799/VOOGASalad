package game_engine.random;

import java.util.List;
import game_object.core.ISprite;
import game_object.level.Level;

public class RandomGenerationController {

    private Level myLevel;
    private List<Class<? extends ISprite>> myRepeated;
    
    public RandomGenerationController(Level level, List<Class<? extends ISprite>> toRepeat){
        myLevel = level;
        myRepeated = toRepeat;
    }
    
    
}
