package authoring;

import authoring.updating.AbstractPublisher;
import authoring.view.canvas.SpriteView;
import voogasalad_overwatch.AuthorEnvironment;

public class AuthoringController extends AbstractPublisher {
	
	private AuthorEnvironment env;
	private SpriteView selectedSpriteView;
	
	public AuthoringController(AuthorEnvironment environment) {
		env = environment;
	}
	
	public AuthorEnvironment getEnvironment() {
		return env;
	}
	
	public void selectSpriteView(SpriteView spriteView) {
		if (spriteView == null) return;
		if (selectedSpriteView == spriteView) return;
		if (selectedSpriteView != null) {
			selectedSpriteView.indicateDeselection();
		}
		spriteView.indicateSelection();
		selectedSpriteView = spriteView;
		this.notifySubscribers();
	}
	
	public SpriteView getSelectedSpriteView() {
		return selectedSpriteView;
	}

}
