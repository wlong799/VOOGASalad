package game_engine.physics;

import java.util.List;
import java.util.Random;

import game_object.core.ISprite;
import game_object.core.Position;
import game_object.core.Velocity;
import game_object.level.Level;
import game_object.simulation.IPhysicsBody;

public class PhysicsHeroFollower extends AbstractPhysicsEngine {

	private List<ISprite> myHeroes;
	private ISprite myHero;
	private double scalarVelocity, scalarDistance;

	public PhysicsHeroFollower(Level level) {
		super(level);
		//setHeroes();
	}

	private void setHeroes(List<ISprite> heroes) {
		myHeroes = heroes;
		selectHero();
	}

	private void selectHero() {
		Random r = new Random();
		int i = r.nextInt(myHeroes.size());
		myHero = myHeroes.get(i);
	}

	public double calculateScalarVelocity(IPhysicsBody body, double elapsedTime) {
		double scalarVelocity = Math
				.sqrt(Math.pow(body.getVelocity().getXVelocity(), 2) + Math.pow(body.getVelocity().getYVelocity(), 2));
		return scalarVelocity;
	}

	public double calculateDistance(IPhysicsBody body, double elapsedTime) {
		double yDis = myHero.getPosition().getY() - body.getPosition().getY();
		double xDis = myHero.getPosition().getX() - body.getPosition().getX();
		double dis = Math.sqrt(Math.pow(yDis, 2) + Math.pow(xDis, 2));
		return dis;
	}

	@Override
	public double calculateNewVerticalPosition(IPhysicsBody body, double elapsedTime) {
		double y = body.getPosition().getY();
		double vy = calculateNewVerticalVelocity(body, elapsedTime);
		double newy = y + elapsedTime * vy;
		return newy;
	}
	
	@Override
	public double calculateNewVerticalVelocity(IPhysicsBody body, double elapsedTime) {
		double yDis = myHero.getPosition().getY() - body.getPosition().getY();
		double yVelocity = yDis / scalarDistance * scalarVelocity;
		return yVelocity;
	}

	@Override
	public double calculateNewHorizontalPosition(IPhysicsBody body, double elapsedTime) {
		double x = body.getPosition().getX();
		double vx = calculateNewHorizontalVelocity(body, elapsedTime);
		double newx = x + elapsedTime * vx;
		return newx;
	}

	@Override
	public double calculateNewHorizontalVelocity(IPhysicsBody body, double elapsedTime) {
		double xDis = myHero.getPosition().getX() - body.getPosition().getX();
		double xVelocity = xDis / scalarDistance * scalarVelocity;
		return xVelocity;
	}

	@Override
	public Position calculateNewPosition(IPhysicsBody body, double elapsedTime) {
		double x = calculateNewHorizontalPosition(body, elapsedTime);
		double y = calculateNewVerticalVelocity(body, elapsedTime);
		return new Position(x, y);
	}

	@Override
	public Velocity calculateNewVelocity(IPhysicsBody body, double elapsedTime) {
		scalarVelocity = calculateScalarVelocity(body, elapsedTime);
		scalarDistance = calculateDistance(body, elapsedTime);
		double yV = calculateNewVerticalVelocity(body, elapsedTime);
		double xV = calculateNewHorizontalVelocity(body, elapsedTime);
		return new Velocity(xV, yV);
	}

	@Override
	public void setParameters(PhysicsParameterSetOptions option, double value) {
		return;
	}

}
