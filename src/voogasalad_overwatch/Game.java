package voogasalad_overwatch;

import java.util.Collection;

public class Game implements IGame {

    @Override
    public void setLevel (int index, ILevel level) {
        // TODO Auto-generated method stub

    }

    @Override
    public ILevel getLevel (int index) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setStartSprites (Collection<ISprite> starts) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setEndSprites (Collection<ISprite> ends) {
        // TODO Auto-generated method stub

    }

    @Override
    public void editSprite (Sprite s) {
       Collision coll = new Collision();
       s.setCollisionProperty(coll);
    }

}
