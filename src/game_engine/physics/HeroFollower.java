package game_engine.physics;

import java.util.List;
import java.util.Random;

import game_object.core.ISprite;
import game_object.core.Position;
import game_object.core.Velocity;
import game_object.level.Level;
import game_object.simulation.IPhysicsBody;

public class HeroFollower extends AbstractPhysicsEngine {

	private List<ISprite> myHeroes;
	private ISprite myHero;

	public HeroFollower(Level level) {
		super(level);
	}

	public void setHeroes(List<ISprite> heroes) {
		myHeroes = heroes;
		selectHero();
	}

	private void selectHero() {
		Random r = new Random();
		int i = r.nextInt(myHeroes.size());
		myHero = myHeroes.get(i);
	}

	@Override
	public double calculateNewVerticalPosition(IPhysicsBody body, double elapsedTime) {
		return 0;
	}

	@Override
	public double calculateNewVerticalVelocity(IPhysicsBody body, double elapsedTime) {
		double rocketVelocity = Math
				.sqrt(Math.pow(body.getVelocity().getXVelocity(), 2) + Math.pow(body.getVelocity().getYVelocity(), 2));
		double yDis = myHero.getPosition().getY() - body.getPosition().getY();
		double xDis = myHero.getPosition().getX() - body.getPosition().getX();
		double dis = Math.sqrt(Math.pow(yDis, 2) + Math.pow(xDis, 2));
		double yVelocity = yDis/dis*rocketVelocity;
		return yVelocity;
	}

	@Override
	public double calculateNewHorizontalPosition(IPhysicsBody sprite, double elapsedTime) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double calculateNewHorizontalVelocity(IPhysicsBody sprite, double elapsedTime) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Position calculateNewPosition(IPhysicsBody body, double elapsedTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Velocity calculateNewVelocity(IPhysicsBody body, double elapsedTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateHorizontalPositionAndVelocity(double newx, double newvx, IPhysicsBody body) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateVerticalPositionAndVelocity(double newy, double newvy, IPhysicsBody body) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updatePosition(Position position, IPhysicsBody body) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateVelocity(Velocity velocity, IPhysicsBody body) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updatePositionAndVelocity(double newx, double newvx, double newy, double newvy, IPhysicsBody body) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updatePositionAndVelocity(Position position, Velocity velocity, IPhysicsBody body) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setParameters(PhysicsParameterSetOptions option, double value) {

	}

	@Override
	public void setExisted(boolean exist) {
		return;
	}

}
