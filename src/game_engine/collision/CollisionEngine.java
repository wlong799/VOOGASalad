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
public class CollisionEngine extends AbstractCollisionEngine{


    @Override
    public void checkCollisions (List<Hero> heroes,
                                 List<Enemy> enemies,
                                 List<AbstractBlock> blocks) {
        for(Hero h : heroes){
            for(Enemy e : enemies){
                if((h.getCategoryBitMask() & e.getCategoryBitMask())!= 0){
                    //IF COLLISON
                    if(h.getPosition() == e.getPosition()){
                        h.setDead(true);
                    }
                }
            }
        }
        checkCharacterBlockCollisions(heroes, blocks);
        checkCharacterBlockCollisions(enemies, blocks);
        
    }
    
    private void checkCharacterBlockCollisions(List<? extends ActiveCharacter> characters, List<AbstractBlock> blocks){
        for(ActiveCharacter c : characters){
            for(AbstractBlock block : blocks){
                // if character and block are able to collide
                
                if(didBlockAndCharacterCollide(c, block)){
                    // TODO: figure out which side of the block the character collides with
                    // if left or right, set x velocity to 0
                    // if top or bottom, set y velocity to 0
                    
                    // JUST FOR SHOWING WHAT WOULD HAPPEN
                    c.setVelocity(new Velocity(0, c.getVelocity().getYVelocity()));
                }
            }
        }
    }
    
    private boolean didBlockAndCharacterCollide(ActiveCharacter character, AbstractBlock block){
        // TODO: figure out if the block/character collided
        return false;
    }
    
    
}
