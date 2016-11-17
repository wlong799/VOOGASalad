package game_engine.collision;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import game_object.block.StaticBlock;
import game_object.character.Enemy;
import game_object.character.Hero;
import game_object.character.ICharacter;
import game_object.core.Dimension;
import game_object.core.Position;
import game_object.core.Velocity;


/**
 * 
 * @author Michael
 * @author Charlie
 *
 */

public class CollisionEngine extends AbstractCollisionEngine{

    @Override
    public void checkCollisions (List<Hero> heroes,
                                 List<Enemy> enemies,
                                 List<StaticBlock> blocks) {
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

    private void checkCharacterBlockCollisions (List<? extends ICharacter> characters,
                                                List<StaticBlock> blocks) {
        for (ICharacter c : characters) {
            for (StaticBlock block : blocks) {
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

    private CollisionDirection getBlockAndCharacterCollision (ICharacter character,
                                                              StaticBlock block) {
        // TODO: figure out if/where the block/character collided
        if (overlap(character.getDimension(), character.getPosition(), block.getDimension(),
                    block.getPosition())) {
            Position prevPosition = character.getPreviousPosition();
            Dimension charDimension = character.getDimension();
            Position blockPosition = block.getPosition();
            Dimension blockDimension = block.getDimension();

            // collided from the left (i.e. your right edge was to the left, and you were above the
            // bottom edge of the block
            double charLeft = prevPosition.getX();
            double charRight = charLeft + charDimension.getWidth();
            double charTop = prevPosition.getY();
            double charBottom = charTop + charDimension.getHeight();
            double blockTop = blockPosition.getY();
            double blockBottom = blockTop + blockDimension.getHeight();
            double blockLeft = blockPosition.getX();
            double blockRight = blockLeft + blockDimension.getWidth();
            boolean couldLandOnBlock = (charLeft > blockLeft && charLeft < blockRight) ||
                                       (charRight > blockLeft && charRight < blockRight);
            if (charRight < blockLeft && charTop < blockBottom) {
                return CollisionDirection.LEFT;
            }
            else if (charLeft > blockRight && charTop < blockBottom) {
                return CollisionDirection.RIGHT;
            }
            else if (charBottom < blockTop &&
                     couldLandOnBlock) {
                return CollisionDirection.TOP;
            }
            else if (charTop > blockBottom && couldLandOnBlock) {
                return CollisionDirection.BOTTOM;
            }
            else {
                return CollisionDirection.CORNER;
            }
        }
        return CollisionDirection.NONE;

    }

    private boolean overlap (Dimension dimA, Position posA, Dimension dimB, Position posB) {

        double xLeftA = posA.getX();
        double xRightA = xLeftA + dimA.getWidth();
        double xLeftB = posB.getX();
        double xRightB = xLeftB + dimB.getWidth();

        if ((xLeftA >= xLeftB && xLeftA <= xRightB) || (xRightA >= xLeftB && xRightA <= xRightB) ||
            (xLeftB >= xLeftA && xRightB <= xRightA) || (xLeftA >= xLeftB && xRightA <= xRightB)) {
            return true;
        }

        double yTopA = posA.getY();
        double yBottomA = yTopA - dimA.getHeight();
        double yTopB = posB.getY();
        double yBottomB = yTopB - dimB.getHeight();

        if ((yTopA >= yTopB && yTopA <= yBottomB) || (yBottomA >= yTopB && yBottomA <= yBottomB) ||
            (yTopB >= yTopA && yBottomB <= yBottomA) ||
            (yBottomA >= yTopB && yBottomA <= yBottomB)) {
            return true;
        }

        return false;
    }

    private enum CollisionDirection {
                                     TOP,
                                     BOTTOM,
                                     LEFT,
                                     RIGHT,
                                     CORNER,
                                     NONE;
    }
}
