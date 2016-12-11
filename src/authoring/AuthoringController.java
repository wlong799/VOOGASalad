package authoring;

import java.io.File;
import java.util.Observable;

import authoring.controller.CanvasController;
import authoring.controller.ComponentController;
import authoring.controller.run.TestGameController;
import authoring.share.NetworkController;
import authoring.share.exception.ShareEditException;
import authoring.view.canvas.SpriteView;
import authoring.view.components.Component;
import game_engine.physics.PhysicsParameterSetOptions;
import game_object.level.Level;
import game_player.image.ImageRenderer;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class AuthoringController extends Observable {
	
	private AuthorEnvironment myEnvironment;
	private Component mySelectedComponent;
	private SpriteView selectedSpriteView;
	private Scene myScene;
	
	private CanvasController canvasController;
	private ComponentController componentController;
	private TestGameController testGameController;
	private ImageRenderer renderer;
	private NetworkController myNetworkController;
	
	public AuthoringController(AuthorEnvironment environment) {
		myEnvironment = environment;
		canvasController = new CanvasController();
		componentController = new ComponentController();
		testGameController = new TestGameController(this);
		renderer = new ImageRenderer();
		myNetworkController = new NetworkController(this);
	}
	
	public CanvasController getCanvasController() {
		return canvasController;
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
	
	public void selectComponent(Component component) {
        if (component == null) {
            return;
        }
        deselectComponent();
        mySelectedComponent = component;
        updateObservers();
    }
	
    public Component getSelectedComponent() {
        return mySelectedComponent;
    }
	
	/**
	 * @param spriteView SpriteView to be selected
	 * first tries to lock the SpriteView from server
	 * if not granted, does nothing and notifies the user
	 */
	public void selectSpriteView(SpriteView spView) {
		if (spView == null || spView == selectedSpriteView) return;
		this.deselectSpriteView(false);
		try {
			myNetworkController.getShareEditor().select(spView);
			spView.indicateSelection();
			selectedSpriteView = spView;
			spView.setEditor(myNetworkController.getMyName());
			this.updateObservers();
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
			selectedSpriteView.setEditor("");
			selectedSpriteView = null;
			if (notify) {
				this.updateObservers();
			}
		}
	}
	
	/**
	 * @param spView selected by another user
	 * @param name the editor's name
	 */
	public void selectSpriteViewFromNetwork(SpriteView spView, String name) {
		spView.setEditor(name);
	}
	
	/**
	 * @param spView to be selected
	 * de-selects sprite view by removing the name tag above
	 * received only from network
	 */
	public void deselectSpriteViewFromNetwork(SpriteView spView) {
		spView.setEditor("");
	}
	
	public SpriteView getSelectedSpriteView() {
		return selectedSpriteView;
	}
	
	public void setScene(Scene scene) {
		myScene = scene;
		myScene.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.BACK_SPACE) {
				canvasController.delete(selectedSpriteView, true);
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
		this.updateObservers();
	}
	
	public SpriteView getSpriteViewWithID(long id) {
		return this.getCanvasController().getSpriteViewWithID(id);
	}
	
	private void deselectComponent() {
		mySelectedComponent = null;
        updateObservers();
	}

    private void updateObservers() {
        setChanged();
        notifyObservers();
    }

}
