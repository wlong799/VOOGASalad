package game_object.powerup;

import java.util.List;
import game_object.character.IUpgrader;
import game_object.core.Dimension;
import game_object.core.Position;

public class ChangeSizePowerUp extends PowerUp{
    
    int multiplier;
    
    protected ChangeSizePowerUp(Position position, Dimension dimension, List<String> imagePaths, int mult) {
        super(position, dimension, imagePaths);
        multiplier = mult;
    }

    @Override
    public void affect(IUpgrader u) {
            u.changeSize(multiplier);
    }

}
