package game_engine.collision;

import java.util.Comparator;
import java.util.List;
import game_object.core.ISprite;


/**
 * 
 * @author Michael
 * @author Charlie
 * @author Grant
 */

public class CollisionEngine extends AbstractCollisionEngine {

    private static final double COLLISION_THRESHOLD = 10.0;
    private boolean logSuppressed = false;

    public void suppressLogDebug () {
        logSuppressed = true;
    }
    
    @Override
    public void checkCollisions (List<ISprite> sprites) {
        Comparator<ISprite> sortByBitmask = Comparator.comparing(sprite -> sprite.getClass().toString()); 
        sprites.stream().sorted(sortByBitmask);
        for(int i = 0; i < sprites.size()-1; i++){
            for(int j = i+1; j < sprites.size();j++){
                ISprite spriteA = sprites.get(i);      
                ISprite spriteB = sprites.get(j);                
                if ((spriteA.getCollisionBitMask() & spriteB.getCategoryBitMask()) != 0) {
                    Boundary heroBoundary = new Boundary(spriteA.getPosition(), spriteA.getDimension());
                    Boundary enemyBoundary = new Boundary(spriteB.getPosition(), spriteB.getDimension());
                    if (heroBoundary.overlaps(enemyBoundary)) {
                        CollisionDirection collision = getCharacterCollision(spriteA, spriteB);
                        spriteA.onCollideWith(spriteB,collision);
                        spriteB.onCollideWith(spriteA,opposite(collision));
                    }
                }
            }
        }
    }

    private CollisionDirection getCharacterCollision (ISprite character,
                                                      ISprite otherSprite) {
        Boundary characterBoundary =
                new Boundary(character.getPosition(), character.getDimension());
        Boundary blockBoundary =
                new Boundary(otherSprite.getPosition(), otherSprite.getDimension());
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
            if ((charTop + COLLISION_THRESHOLD) >= blockBottom && couldLandOnBlock) {
                return CollisionDirection.TOP;
            }
            else if ((charBottom - COLLISION_THRESHOLD) <= blockTop &&
                     couldLandOnBlock) {
                return CollisionDirection.BOTTOM;
            }
            else if (charLeft >= blockRight) {
                return CollisionDirection.LEFT;
            }

            else if (charRight <= blockLeft) {
                return CollisionDirection.RIGHT;
            }
            else {
                return CollisionDirection.CORNER;
            }
        }
        return CollisionDirection.NONE;

    }

    public CollisionDirection opposite(CollisionDirection cd){
        if(cd==CollisionDirection.TOP){
            return CollisionDirection.BOTTOM;
        }
        if(cd==CollisionDirection.BOTTOM){
            return CollisionDirection.TOP;
        }
        if(cd==CollisionDirection.LEFT){
            return CollisionDirection.RIGHT;
        }
        if(cd==CollisionDirection.RIGHT){
            return CollisionDirection.LEFT;
        }
        if(cd==CollisionDirection.CORNER){
            return CollisionDirection.CORNER;
        }
        return CollisionDirection.NONE;
    }
    
    public enum CollisionDirection {
                                     TOP,
                                     BOTTOM,
                                     LEFT,
                                     RIGHT,
                                     CORNER,
                                     NONE;
    }
}
