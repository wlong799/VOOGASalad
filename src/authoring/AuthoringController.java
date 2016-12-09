package authoring;

import java.io.File;
import java.util.Observable;

import authoring.controller.CanvasController;
import authoring.controller.ComponentController;
import authoring.controller.chat.ChatController;
import authoring.controller.run.TestGameController;
import authoring.view.canvas.SpriteView;
import authoring.view.components.Component;
import game_engine.physics.PhysicsParameterSetOptions;
import game_object.level.Level;
import game_player.image.ImageRenderer;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import serializing.Marshaller;

public class AuthoringController extends Observable {

    private AuthorEnvironment myEnvironment;
    private Component selectedComponent;
    private SpriteView selectedSpriteView;
    private Scene myScene;

    private CanvasController canvasController;
    private ComponentController componentController;
    private TestGameController testGameController;
    private ChatController chatController;
    private Marshaller marshaller;
    private ImageRenderer renderer;

    public AuthoringController(AuthorEnvironment environment) {
        myEnvironment = environment;
        canvasController = new CanvasController();
        componentController = new ComponentController();
        testGameController = new TestGameController(this);
        chatController = new ChatController();
        marshaller = new Marshaller();
        renderer = new ImageRenderer();
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

    public ChatController getChatController() {
        return chatController;
    }

    public Marshaller getMarshaller() {
        return marshaller;
    }

    public ImageRenderer getRenderer() {
        return renderer;
    }

    public AuthorEnvironment getEnvironment() {
        return myEnvironment;
    }

    public void selectComponent(Component component) {
        if (component == null) {
            return;
        }
        deselect();
        selectedComponent = component;
        updateObservers();
    }

    public Component getSelectedComponent() {
        return selectedComponent;
    }

    public void selectSpriteView(SpriteView spriteView) {
        if (spriteView == null) {
            return;
        }
        deselect();
        spriteView.indicateSelection();
        selectedSpriteView = spriteView;
        updateObservers();
    }

    public SpriteView getSelectedSpriteView() {
        return selectedSpriteView;
    }

    private void deselect() {
        if (selectedSpriteView != null) {
            selectedSpriteView.indicateDeselection();
            selectedSpriteView = null;
        }
        selectedComponent = null;
        updateObservers();
    }

    public void setScene(Scene scene) {
        myScene = scene;
        myScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.BACK_SPACE) {
                canvasController.delete(selectedSpriteView);
            } else if (event.getCode() == KeyCode.ESCAPE) {
                deselect();
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
