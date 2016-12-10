package authoring;

import java.io.File;

import authoring.controller.CanvasViewController;
import authoring.controller.ComponentController;
import authoring.controller.run.TestGameController;
import authoring.share.NetworkController;
import authoring.share.exception.ShareEditException;
import authoring.updating.AbstractPublisher;
import authoring.view.canvas.SpriteView;
import game_engine.physics.PhysicsParameterSetOptions;
import game_object.level.Level;
import game_player.image.ImageRenderer;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class AuthoringController extends AbstractPublisher {
	
	private AuthorEnvironment myEnvironment;
	private SpriteView selectedSpriteView;
	private Scene myScene;
	
	private CanvasViewController canvasViewController;
	private ComponentController componentController;
	private TestGameController testGameController;
	private ImageRenderer renderer;
	private NetworkController myNetworkController;
	
	public AuthoringController(AuthorEnvironment environment) {
		myEnvironment = environment;
		canvasViewController = new CanvasViewController();
		componentController = new ComponentController();
		testGameController = new TestGameController(this);
		renderer = new ImageRenderer();
		myNetworkController = new NetworkController(this);
	}
	
	public CanvasViewController getCanvasViewController() {
		return canvasViewController;
	}
	
	public ComponentController getComponentController() {
		return componentController;
	}
	
	public TestGameController getTestGameController() {
		return testGameController;
	}
	
	public ImageRenderer getRenderer() {
		return renderer;
	}
	
	public NetworkController getNetworkController() {
		return myNetworkController;
	}
	
	public AuthorEnvironment getEnvironment() {
		return myEnvironment;
	}
	
	/**
	 * @param spriteView SpriteView to be selected
	 * first tries to lock the SpriteView from server
	 * if not granted, does nothing and notifies the user
	 */
	public void selectSpriteView(SpriteView spView) {
		if (spView == null || spView == selectedSpriteView) return;
		try {
			myNetworkController.getShareEditor().select(spView);
			this.deselectSpriteView(false);
			spView.indicateSelection();
			selectedSpriteView = spView;
			this.notifySubscribers();
		} catch (ShareEditException e) {
			System.out.println("already selected by " + e.getMessage());
		}
	}
	
	/**
	 * @param notify specifies if the subscribers (frontend UI) should update
	 * deselects the sprite view visually and unlocks it at the server
	 */
	public void deselectSpriteView(boolean notify) {
		if (selectedSpriteView != null) {
			myNetworkController.getShareEditor().unlock(selectedSpriteView);
			selectedSpriteView.indicateDeselection();
			selectedSpriteView = null;
			if (notify) {
				this.notifySubscribers();
			}
		}
	}
	
	public SpriteView getSelectedSpriteView() {
		return selectedSpriteView;
	}
	
	public void setScene(Scene scene) {
		myScene = scene;
		myScene.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.BACK_SPACE) {
				canvasViewController.delete(selectedSpriteView);
			}
			else if (event.getCode() == KeyCode.ESCAPE) {
				this.deselectSpriteView(true);
			}
		});
		File f = new File("css/style.css");
		scene.getStylesheets().clear();
		scene.getStylesheets().add(f.getPath());
	}
	
	public void setMouseCursor(Cursor type) {
		myScene.setCursor(type);
	}
	
	public void setParameter(Level level, PhysicsParameterSetOptions option, double value) {
		level.getPhysicsParameters().set(option, value);
		this.notifySubscribers();
	}
	
	public SpriteView getSpriteViewWithID(long id) {
		return this.getCanvasViewController().getSpriteViewWithID(id);
	}

}
