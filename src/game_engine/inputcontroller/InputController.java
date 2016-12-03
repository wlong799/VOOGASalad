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
	private Level myCurrentLevel;
	private Game myGame;
	private boolean exist;

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
		exist = false;
		jumping = false;
		if (myList != null && myList.size() != 0) {
			for (KeyEvent event : myList) {
				List<ActionTrigger> trigger = myCurrentLevel.getTriggersWithEvent(event);
				for (ActionTrigger actionTrigger : trigger) {
					chooseAction(actionTrigger);
				}
			}
		}
		myJump = jumping;
	}

	private void chooseAction(ActionTrigger at) {
		ISprite sprite = at.getSprite();
		System.out.println(at.getActionName());
		if (at.getActionName() == ActionName.MOVE_LEFT) {
			IMover m = (IMover) sprite;
			m.moveLeft();
			exist = true;
		} else if (at.getActionName() == ActionName.MOVE_RIGHT) {
			IMover m = (IMover) sprite;
			m.moveRight();
			exist = true;
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
			ICharacter character = (ICharacter) sprite;
			// c.shoot();
			addProjectile(character);
		}
	}

	private void addProjectile(ICharacter character) {
		Weapon weapon = character.getCurrentWeapon();
		ProjectileModel pm = weapon.getProjectileModel();
		String imgPath = pm.getImgPath();
		Projectile p = new Projectile(new Position(character.getPosition().getX(), character.getPosition().getY()),
				new Dimension(5, 5), imgPath, pm);
		myCurrentLevel.getProjectiles().add(p);
	}

	public boolean getInputExist() {
		return exist;
	}
}
