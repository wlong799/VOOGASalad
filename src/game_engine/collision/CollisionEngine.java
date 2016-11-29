package game_engine.collision;

import java.util.List;
import game_object.block.StaticBlock;
import game_object.character.Enemy;
import game_object.character.Hero;
import game_object.character.ICharacter;
import game_object.core.Position;
import game_object.core.Velocity;


/**
 * 
 * @author Michael
 * @author Charlie
 * @author Grant
 */

public class CollisionEngine extends AbstractCollisionEngine{

    private static final double COLLISION_THRESHOLD = 10.0;
    
    private boolean logSuppressed = false;
    
    public void suppressLogDebug() {
    	logSuppressed = true;
    }
    
    @Override
    public void checkCollisions (List<Hero> heroes,
                                 List<Enemy> enemies,
                                 List<StaticBlock> blocks) {
        for (Hero h : heroes) {
            for (Enemy e : enemies) {
                if ((h.getCollisionBitMask() & e.getCategoryBitMask()) != 0) {
                    Boundary heroBoundary = new Boundary(h.getPosition(), h.getDimension());
                    Boundary enemyBoundary = new Boundary(e.getPosition(), e.getDimension());
                    if (heroBoundary.overlaps(enemyBoundary)) {
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
                //System.out.println("Character at " + c.getPosition());
                double r = c.getPosition().getX() + c.getDimension().getWidth();
                //System.out.println("Character right at " +r);
                //System.out.println("Block at " + block.getPosition());
                if ((c.getCategoryBitMask() & block.getCollisionBitMask()) != 0) {
                    CollisionDirection collision = getBlockAndCharacterCollision(c, block);

                    if (collision != CollisionDirection.NONE) {
                    	if (!logSuppressed) {
                    		System.out.println(collision);
                    		System.out.println("Collision between " + c.toString() + " and " + block);
                    	}
                        if (collision == CollisionDirection.TOP) {
                            c.setPosition(new Position(c.getPosition().getX(),
                                                       block.getPosition().getY()-c.getDimension().getHeight()));
                            c.setVelocity(new Velocity(c.getVelocity().getXVelocity(), 0));
                        }
                        else if (collision == CollisionDirection.BOTTOM) {
                            c.setPosition(new Position(c.getPosition().getX(), block.getPosition()
                                    .getY() + block.getDimension().getHeight()));
                            c.setVelocity(new Velocity(c.getVelocity().getXVelocity(), 0));
                            c.resetCurrentJumps();
                        }
                        else if (collision == CollisionDirection.RIGHT) {
                            c.setPosition(new Position(block.getPosition().getX() +
                                                       block.getDimension().getWidth(),
                                                       c.getPosition().getY()));
                            c.setVelocity(new Velocity(0, c.getVelocity().getYVelocity()));
                        }
                        else if (collision == CollisionDirection.LEFT) {
                            c.setPosition(new Position(block.getPosition().getX()-c.getDimension().getWidth(),
                                                       c.getPosition().getY()));
                            c.setVelocity(new Velocity(0, c.getVelocity().getYVelocity()));
                        }
                        else {
                            // TODO: Implement corner collision handling
                        }

                    }
                }
            }
        }
    }

    private CollisionDirection getBlockAndCharacterCollision (ICharacter character,
                                                              StaticBlock block) {
        Boundary characterBoundary =
                new Boundary(character.getPosition(), character.getDimension());
        Boundary blockBoundary = new Boundary(block.getPosition(), block.getDimension());
        if (characterBoundary.overlaps(blockBoundary)) {
            Boundary prevCharacterBoundary =
                    new Boundary(character.getPreviousPosition(), character.getDimension());

            double charLeft = prevCharacterBoundary.left();
            double charRight = prevCharacterBoundary.right();
            double charTop = prevCharacterBoundary.top();
            double charBottom = prevCharacterBoundary.bottom();

            double blockTop = blockBoundary.top();
            double blockBottom = blockBoundary.bottom();
            double blockLeft = blockBoundary.left();
            double blockRight = blockBoundary.right();
            
            boolean couldLandOnBlock = (charLeft > blockLeft && charLeft < blockRight) ||
                                       (charRight > blockLeft && charRight < blockRight);
            if (!logSuppressed) {
            	System.out.println(charBottom);
            	System.out.println(blockTop);
            	System.out.println(couldLandOnBlock);
            }
            if ((charTop+COLLISION_THRESHOLD) >= blockBottom && couldLandOnBlock) {
                return CollisionDirection.BOTTOM;
            }
            else if ((charBottom-COLLISION_THRESHOLD) <= blockTop &&
                     couldLandOnBlock) {
                return CollisionDirection.TOP;
            }
            else if (charLeft >= blockRight) {
                return CollisionDirection.RIGHT;
            }

            else if (charRight <= blockLeft) {
                return CollisionDirection.LEFT;
            }
            else {
                return CollisionDirection.CORNER;
            }
        }
        return CollisionDirection.NONE;

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
