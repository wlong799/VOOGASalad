package game_engine.collision;

import java.util.List;
import game_object.block.StaticBlock;
import game_object.character.Enemy;
import game_object.character.Hero;

public interface ICollisionEngine {
    
    /**
     * Checks for collisions between heroes, enemies, and blocks
     * @param heroes
     * @param enemies
     * @param blocks
     */
    void checkCollisions(List<Hero> heroes, List<Enemy> enemies, List<StaticBlock> blocks);
    
    
}
