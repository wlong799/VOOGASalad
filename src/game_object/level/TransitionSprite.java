package game_object.level;

/**
 * An interface sprites that will transit to another level or menu,
 * With the convention being that if getAssociatedLevel()
 * returns a non-null value, getAssociatedMenu() will be ignored.
 * @author Jay
 */
public interface TransitionSprite {

	void setAssociatedLevel(Level level);
	
	Level getAssociatedLevel();
	
	void setAssociatedMenu(TransitionMenu menu);
	
	TransitionMenu getAssociatedMenu();
	
}
