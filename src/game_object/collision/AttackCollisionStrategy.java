package game_object.collision;

import game_engine.collision.CollisionEngine.CollisionDirection;
import game_object.character.ICharacter;

public class AttackCollisionStrategy<A extends ICharacter, B extends ICharacter>
	extends AbstractCollisionStrategy<A, B> {

	private double myTopDamage;
	private double myBottomDamage;
	private double myLeftDamage;
	private double myRightDamage;
	
	public void setTopDamage(double topDamage) {
		myTopDamage = topDamage;
	}
	
	public void setBottomDamage(double bottomDamage) {
		myBottomDamage = bottomDamage;
	}
	
	public void setLeftDamage(double leftDamage) {
		myLeftDamage = leftDamage;
	}
	
	public void setRightDamage(double rightDamage) {
		myRightDamage = rightDamage;
	}
	
	@Override
	public void applyCollision(A a, B b, CollisionDirection collisionDirection) {
		double damage = 0;
		switch (collisionDirection) {
		case TOP:
			damage = myTopDamage;
			break;
		case BOTTOM:
			damage = myBottomDamage;
		case LEFT:
			damage = myLeftDamage;
		case RIGHT:
			damage = myRightDamage;
		default:
			break;
		}
		a.setCurrentHP(a.getCurrentHP() - damage);
	}


}
