package game_engine.collision;

import java.util.List;
import java.util.stream.Collectors;
import game_object.block.AbstractBlock;
import game_object.character.ActiveCharacter;
import game_object.character.Enemy;
import game_object.character.Hero;
import game_object.core.Velocity;


/**
 * 
 * @author Michael
 * @author Charlie
 *
 */
public class CollisionEngine extends AbstractCollisionEngine {

    @Override
    public void checkCollisions (List<Hero> heroes,
                                 List<Enemy> enemies,
                                 List<AbstractBlock> blocks) {
        for (Hero h : heroes) {
            for (Enemy e : enemies) {
                if ((h.getCategoryBitMask() & e.getCategoryBitMask()) != 0) {
                    // IF COLLISON
                    if (h.getPosition() == e.getPosition()) {
                        h.setDead(true);
                    }
                }
            }
        }
        checkCharacterBlockCollisions(heroes, blocks);
        checkCharacterBlockCollisions(enemies, blocks);
    }

    private void checkCharacterBlockCollisions (List<? extends ActiveCharacter> characters,
                                                List<AbstractBlock> blocks) {
        for (ActiveCharacter c : characters) {
            for (AbstractBlock block : blocks) {
                if ((c.getCategoryBitMask() & block.getCollisionBitMask()) != 0) {
                    CollisionDirection collision = getBlockAndCharacterCollision(c, block);
                    if (collision != CollisionDirection.NONE) {
                        if (collision == CollisionDirection.TOP ||
                            collision == CollisionDirection.BOTTOM) {
                            c.setVelocity(new Velocity(c.getVelocity().getXVelocity(), 0));
                        }
                        else {
                            c.setVelocity(new Velocity(0, c.getVelocity().getYVelocity()));
                        }

                    }
                }
            }
        }
    }

    private CollisionDirection getBlockAndCharacterCollision (ActiveCharacter character,
                                                              AbstractBlock block) {
        // TODO: figure out if/where the block/character collided
        
        return CollisionDirection.NONE;
    }

    private enum CollisionDirection {
                                     TOP,
                                     BOTTOM,
                                     LEFT,
                                     RIGHT,
                                     NONE;
    }
}
