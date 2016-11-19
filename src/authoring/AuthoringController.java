package authoring;

import authoring.controller.CanvasViewController;
import authoring.updating.AbstractPublisher;
import authoring.view.canvas.SpriteView;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import voogasalad_overwatch.AuthorEnvironment;

public class AuthoringController extends AbstractPublisher {
	
	private AuthorEnvironment myEnvironment;
	private SpriteView selectedSpriteView;
	private Scene myScene;
	private CanvasViewController canvasViewController;
	
	public AuthoringController(AuthorEnvironment environment) {
		myEnvironment = environment;
		canvasViewController = new CanvasViewController();
	}
	
	public CanvasViewController getCanvasViewController() {
		return canvasViewController;
	}
	
	public AuthorEnvironment getEnvironment() {
		return myEnvironment;
	}
	
	public void selectSpriteView(SpriteView spriteView) {
		if (spriteView == null) return;
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
	
	public void setScene(Scene scene) {
		myScene = scene;
	}
	
	public void setMouseCursor(Cursor type) {
		myScene.setCursor(type);
	}

}
