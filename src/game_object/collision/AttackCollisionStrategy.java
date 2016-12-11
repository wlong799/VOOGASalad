package game_object.collision;

import game_engine.collision.CollisionEngine.CollisionDirection;
import game_object.character.ICharacter;
import game_object.simulation.ICollisionBody;

public class AttackCollisionStrategy<A extends ICharacter, B extends ICollisionBody>
	extends AbstractCollisionStrategy<A, B> {

	private double myCornerDamage;
	private double myTopDamage;
	private double myBottomDamage;
	private double myLeftDamage;
	private double myRightDamage;
	
	public void setDamageFromAllDirection(double damage) {
		setTopDamage(damage);
		setBottomDamage(damage);
		setLeftDamage(damage);
		setRightDamage(damage);
		setCornerDamage(damage);
	}
	
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
	
	public void setCornerDamage(double cornerDamage) {
		myCornerDamage = cornerDamage;
	}
	
	public double getDamage() {
		return myLeftDamage;
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
			break;
		case LEFT:
			damage = myLeftDamage;
			break;
		case RIGHT:
			damage = myRightDamage;
			break;
		case CORNER:
			damage = myCornerDamage;
		default:
			break;
		}
		a.setCurrentHP(a.getCurrentHP() - damage);
	}

}
