package authoring;

import java.io.File;
import java.util.Observable;

import authoring.controller.CanvasController;
import authoring.controller.ComponentController;
import authoring.controller.chat.ChatController;
import authoring.controller.run.TestGameController;
import authoring.view.canvas.SpriteView;
import game_engine.physics.PhysicsParameterSetOptions;
import game_object.level.Level;
import game_player.image.ImageRenderer;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import serializing.Marshaller;

public class AuthoringController extends Observable {

    private AuthorEnvironment myEnvironment;
    private SpriteView mySelectedSpriteView;
    private Scene myScene;

    private CanvasController myCanvasController;
    private ComponentController myComponentController;
    private TestGameController myTestGameController;
    private ChatController myChatController;
    private Marshaller myMarshaller;
    private ImageRenderer myRenderer;

    public AuthoringController(AuthorEnvironment environment) {
        myEnvironment = environment;
        myCanvasController = new CanvasController();
        myComponentController = new ComponentController();
        myTestGameController = new TestGameController(this);
        myChatController = new ChatController();
        myMarshaller = new Marshaller();
        myRenderer = new ImageRenderer();
    }

    public CanvasController getCanvasController() {
        return myCanvasController;
    }

    public ComponentController getComponentController() {
        return myComponentController;
    }

    public TestGameController getTestGameController() {
        return myTestGameController;
    }

    public ChatController getChatController() {
        return myChatController;
    }

    public Marshaller getMarshaller() {
        return myMarshaller;
    }

    public ImageRenderer getRenderer() {
        return myRenderer;
    }

    public AuthorEnvironment getEnvironment() {
        return myEnvironment;
    }

    public void selectSpriteView(SpriteView spriteView) {
        if (spriteView == null) return;
        if (mySelectedSpriteView != null) {
            mySelectedSpriteView.indicateDeselection();
        }
        spriteView.indicateSelection();
        mySelectedSpriteView = spriteView;
        updateObservers();
    }

    public void deselectSpriteViews() {
        if (mySelectedSpriteView != null) {
            mySelectedSpriteView.indicateDeselection();
            mySelectedSpriteView = null;
            updateObservers();
        }
    }

    public SpriteView getSelectedSpriteView() {
        return mySelectedSpriteView;
    }

    public void setScene(Scene scene) {
        myScene = scene;
        myScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.BACK_SPACE) {
                myCanvasController.delete(mySelectedSpriteView);
            } else if (event.getCode() == KeyCode.ESCAPE) {
                deselectSpriteViews();
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
        updateObservers();
    }

    private void updateObservers() {
        setChanged();
        notifyObservers();
    }

}
