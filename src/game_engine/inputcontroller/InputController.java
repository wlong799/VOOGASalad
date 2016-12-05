package game_engine.inputcontroller;

import java.util.List;
import java.util.Set;

import game_object.acting.ActionName;
import game_object.acting.ActionTrigger;
import game_object.acting.KeyEvent;
import game_object.character.ICharacter;
import game_object.character.IMover;
import game_object.core.ISprite;
import game_object.core.Position;
import game_object.core.Dimension;
import game_object.core.Game;
import game_object.level.Level;
import game_object.weapon.Projectile;
import game_object.weapon.ProjectileModel;
import game_object.weapon.Weapon;

public class InputController implements IInputController {

	private Set<KeyEvent> myList;
	private boolean myJump = false;
	private boolean jumping;
	private boolean myShoot = false;
	private boolean shooting;
	private Level myCurrentLevel;
	private Game myGame;
	private boolean myLeftRightExist;

	public InputController(Game game) {
		myGame = game;
		myCurrentLevel = game.getCurrentLevel();
	}

	@Override
	public void setInputList(Set<KeyEvent> list) {
		myList = list;
	}

	@Override
	public void executeInput() {
		myCurrentLevel = myGame.getCurrentLevel();
		myLeftRightExist = false;
		jumping = false;
		shooting = false;
		if (myList != null && myList.size() != 0) {
			for (KeyEvent event : myList) {
				List<ActionTrigger> trigger = myCurrentLevel.getTriggersWithEvent(event);
				for (ActionTrigger actionTrigger : trigger) {
					chooseAction(actionTrigger);
				}
			}
		}
		myJump = jumping;
		myShoot = shooting;
	}

	private void chooseAction(ActionTrigger at) {
		ISprite sprite = at.getSprite();
		if (at.getActionName() == ActionName.MOVE_LEFT) {
			IMover m = (IMover) sprite;
			m.moveLeft();
			myLeftRightExist = true;
		} else if (at.getActionName() == ActionName.MOVE_RIGHT) {
			IMover m = (IMover) sprite;
			m.moveRight();
			myLeftRightExist = true;
		} else if (at.getActionName() == ActionName.JUMP) {
			if (sprite.getVelocity().getYVelocity() == 0) {
				myJump = false;
			}
			jumping = true;
			IMover m = (IMover) sprite;
			if (!myJump) {
				m.jumpUp();
			}
		} else if (at.getActionName() == ActionName.SHOOT) {
			shooting = true;
			ICharacter character = (ICharacter) sprite;
			// c.shoot();
			if (!myShoot) {
				addProjectile(character);
			}
		}
	}

	private void addProjectile(ICharacter character) {
		Weapon weapon = character.getCurrentWeapon();
		ProjectileModel pm = weapon.getProjectileModel();
		Projectile p = pm.newProjectileInstance(new Position(character.getPosition().getX(), character.getPosition().getY()),
				new Dimension(20, 20));
		myCurrentLevel.getProjectiles().add(p);
	}

	public boolean getInputExist() {
		return myLeftRightExist;
	}
}
