package game_object.powerup;

import java.util.List;

import game_engine.collision.CollisionEngine.CollisionDirection;
import game_object.character.IUpgrader;
import game_object.core.Dimension;
import game_object.core.Position;

public class ChangeSizePowerUp extends AbstractPowerUp{
    
    private int myMultiplier;
    
    public ChangeSizePowerUp(Position position, Dimension dimension, List<String> imagePaths, int multiplier) {
        super(position, dimension, imagePaths);
        myMultiplier = multiplier;
    }

    @Override
    public void affect(IUpgrader u) {
    	u.changeSize(myMultiplier);
    }

	@Override
	public void onCollideWith(IPowerUp p, CollisionDirection collisionDirection) {
		// TODO Auto-generated method stub
		
	}

}
