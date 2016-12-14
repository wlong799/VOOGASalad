package authoring.view.menu.menu_element;

import authoring.AuthoringController;
import authoring.view.inspector.settings.ITextChangeHandler;
import authoring.view.menu.AbstractGameMenuElement;
import game_engine.random.RandomGenerationController;
import game_engine.random.RandomSpriteCluster;
import game_engine.random.SpriteInfo;
import game_object.core.ISprite;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import resources.ResourceBundles;
import java.util.*;


/**
 * Allows users to configure generation of random sprites.
 *
 * @author Will Long
 * @version 12/11/16
 */
public class RandomClusterElement extends AbstractGameMenuElement implements Observer {
    private ResourceBundle myConstantProperties;
    private ResourceBundle myLanguageProperties;

    private boolean isAcceptingComponents;
    private double mySize;
    private List<SpriteInfo> myClusterSpriteInfoList;
    private VBox myClusterSpritesView;

    protected RandomClusterElement (AuthoringController controller) {
        super(controller.getEnvironment().getLanguageResourceBundle().getString("configureRandom"),
              controller);
        myConstantProperties = ResourceBundles.componentProperties;
        myLanguageProperties = myController.getEnvironment().getLanguageResourceBundle();

        myController.addObserver(this);
        myClusterSpriteInfoList = new ArrayList<>();
        isAcceptingComponents = false;
        myClusterSpritesView = new VBox();
        mySize = Double.parseDouble(myConstantProperties.getString("CLUSTER_WINDOW_SIZE"));
        myClusterSpritesView.setPrefWidth(mySize);
    }

    @Override
    protected void setFunctionality () {
        myMenuItem.setOnAction(event -> {
            if (!isAcceptingComponents) {
                isAcceptingComponents = true;
                launchAddComponentScreen();
            }
        });
    }

    @Override
    public void update (Observable o, Object arg) {
        if (isAcceptingComponents && myController.getSelectedComponent() != null) {
            addSpriteToCluster(myController.getSelectedComponent().copySpriteFromTemplate());
        }
    }

    private void launchAddComponentScreen () {
        myClusterSpriteInfoList.clear();
        myClusterSpritesView.getChildren().clear();

        ScrollPane scrollPane = new ScrollPane(myClusterSpritesView);
        scrollPane.setPrefWidth(mySize);
        scrollPane.setPrefHeight(mySize);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        Scene scene = new Scene(scrollPane, mySize, mySize);
        Stage stage = new Stage();
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);
        stage.show();

        stage.showingProperty().addListener(( (observable, oldValue, newValue) -> {
            isAcceptingComponents = false;
            launchEditClusterGenerationScreen();
        }));
    }

    private void launchEditClusterGenerationScreen () {

        List<String> choices = new ArrayList<>();
        choices.add("Up");
        choices.add("Right");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("Right", choices);
        dialog.setTitle("Scroll Direction");
        dialog.setHeaderText("Scroll");
        dialog.setContentText("Which way do you want the sprites to generate:");

        // Traditional way to get the response value.
        Optional<String> scrollResult = dialog.showAndWait();

        TextInputDialog xRangeInput = new TextInputDialog();
        xRangeInput.setContentText(myLanguageProperties.getString("randomXRange"));
        Optional<String> result = xRangeInput.showAndWait();
        if (!result.isPresent()) {
            return;
        }
        double xRange = Double.parseDouble(result.get());

        TextInputDialog yRangeInput = new TextInputDialog();
        yRangeInput.setContentText(myLanguageProperties.getString("randomYRange"));
        result = yRangeInput.showAndWait();
        if (!result.isPresent()) {
            return;
        }
        double yRange = Double.parseDouble(result.get());

        TextInputDialog repeatTimeInput = new TextInputDialog();
        repeatTimeInput.setContentText(myLanguageProperties.getString("randomRepeatTime"));
        result = repeatTimeInput.showAndWait();
        if (!result.isPresent()) {
            return;
        }
        double repeatTime = Double.parseDouble(result.get());
        // TODO: 12/12/16 FIX THIS ONCE BACKEND IS BETTER
        RandomSpriteCluster randomSpriteCluster =
                new RandomSpriteCluster(xRange, yRange, repeatTime);
        myClusterSpriteInfoList.forEach(randomSpriteCluster::addSprite);
        List<RandomSpriteCluster> randomSpriteClusters = new ArrayList<>();
        randomSpriteClusters.add(randomSpriteCluster);
        RandomGenerationController rgc =
                new RandomGenerationController(myController.getEnvironment().getCurrentLevel(),
                                               randomSpriteClusters);
        scrollResult.ifPresent(direction -> {
            boolean right = direction.equals("Right");
            rgc.setSidewaysScrolling(right);
        });
        myController.getEnvironment().getCurrentGame().setRandomGenerationController(rgc);
    }

    private void addSpriteToCluster (ISprite sprite) {
        if (sprite == null) {
            return;
        }
        SpriteInfo spriteInfo = new SpriteInfo(sprite.getClass(), sprite.getImagePaths(),
                                               sprite.getDimension(), sprite.getPosition());
        myClusterSpriteInfoList.add(spriteInfo);

        HBox hBox = new HBox();
        double miniSize =
                Double.parseDouble(myConstantProperties.getString("CLUSTER_SPRITE_HEIGHT"));
        hBox.setPrefWidth(mySize);
        hBox.setPrefHeight(miniSize);
        ImageView imageView = new ImageView(sprite.getImagePath());
        imageView.setFitHeight(miniSize);
        imageView.setFitWidth(miniSize);
        hBox.getChildren().add(imageView);

        Node positionXInput = makeInputBox(myLanguageProperties.getString("posX"),
                                           spriteInfo.getRelativePosition().getX(),
                                           newValue -> spriteInfo.getRelativePosition()
                                                   .setX(Double.parseDouble(newValue)));
        Node positionYInput = makeInputBox(myLanguageProperties.getString("posY"),
                                           spriteInfo.getRelativePosition().getY(),
                                           newValue -> spriteInfo.getRelativePosition()
                                                   .setY(Double.parseDouble(newValue)));
        Node widthInput = makeInputBox(myLanguageProperties.getString("width"),
                                       spriteInfo.getDimension().getWidth(),
                                       newValue -> spriteInfo.getDimension()
                                               .setWidth(Double.parseDouble(newValue)));
        Node heightInput = makeInputBox(myLanguageProperties.getString("height"),
                                        spriteInfo.getDimension().getHeight(),
                                        newValue -> spriteInfo.getDimension()
                                                .setHeight(Double.parseDouble(newValue)));

        myClusterSpritesView.getChildren().addAll(hBox, positionXInput, positionYInput, widthInput,
                                                  heightInput);
    }

    private Node makeInputBox (String title, double defaultValue, ITextChangeHandler handler) {
        TextField textField = new TextField(defaultValue + "");
        textField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handler.handle(textField.getText());
            }
        });
        textField.focusedProperty().addListener( (obs, oldVal, newVal) -> {
            if (!newVal) {
                handler.handle(textField.getText());
            }
        });
        Label label = new Label(title);
        label.setLabelFor(textField);
        return new VBox(label, textField);
    }
}
