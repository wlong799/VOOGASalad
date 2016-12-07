package game_engine.collision;

import game_object.core.ISprite;

public class CollisionKey {
    private Class<? extends ISprite> mySpriteA;
    private Class<? extends ISprite> mySpriteB;

    public CollisionKey (Class<? extends ISprite> a, Class<? extends ISprite> b) {
        mySpriteA = a;
        mySpriteB = b;
    }

    @Override
    public boolean equals (Object other) {
        if (other == null || !(other instanceof CollisionKey)) {
            return false;
        }
        if (other == this) {
            return true;
        }
        CollisionKey otherKey = (CollisionKey) other;
        return (otherKey.mySpriteA.equals(mySpriteA) && otherKey.mySpriteB.equals(mySpriteB) ||
                otherKey.mySpriteA.equals(mySpriteB) && otherKey.mySpriteA.equals(mySpriteB));
    }
}
