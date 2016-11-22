package authoring.view.components;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import game_object.GameObjectType;
import game_object.constants.GameObjectConstants;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ComponentPanelView extends AbstractView {

    private List<Component> heroList, enemyList, blockList;
    private HBox personalizedHBox;
    private Button upload;
    private TabPane myTabPane;

    private ComponentPanelView(AuthoringController controller) {
        super(controller);
    }

    @Override
    protected void initUI() {
        myTabPane = new TabPane();
        addUI(myTabPane);
    }

    @Override
    protected void updateLayoutSelf() {
        myTabPane.setPrefWidth(getWidth());
        myTabPane.setPrefHeight(getHeight());
        getSubViews().forEach(subView -> {
            subView.setWidth(getWidth());
            subView.setHeight(getHeight());
        });
    }

    public void addTab(String tabName, ComponentListView componentListView) {
        Tab newTab = new Tab(tabName);
        newTab.setClosable(false);
        newTab.setContent(componentListView.getUI());
        myTabPane.getTabs().add(newTab);
        addSubView(componentListView);
    }

 /*   private void initUploadedTab() {
        Tab uploadTab = initTab("Uploaded");
        uploadTab.setClosable(false);
        myTabPane.getTabs().add(uploadTab);

        personalizedHBox = initNewHBox();

        ScrollPane scrollPane = initScrollPane();
        scrollPane.setContent(personalizedHBox);

        personalizedHBox.getChildren().add(initUploadButton());

        uploadTab.setContent(scrollPane);
    }*/

 /*   private Button initUploadButton() {
        upload = new Button();
        ImageView uploadImage = new ImageView(GameObjectConstants.UPLOAD);
        uploadImage.setFitHeight(50);
        uploadImage.setFitWidth(50);
        upload.setGraphic(uploadImage);
        initUploadButtonAction();

        return upload;
    }

    private void initUploadButtonAction() {
        upload.setOnAction((event) -> {
            File userFile = userChosenFile();
            if (userFile == null) return;
            String userFileName = userChosenName();
            String userSpriteType = userChosenType();

            if ((!userFileName.equals("") && (userFile != null))) {
                updatePersonalizedList(userFile.toURI().toString(), userFileName, userSpriteType);
            }
        });
    }

    private void updatePersonalizedList(String filePath, String imageName, String spriteType) {
        ComponentView c = new ComponentView(this.getController());
        c.setWidth(50);
        Component personalizedComponent = new Component(spriteType(spriteType), filePath, imageName, imageName);
        c.setComponent(personalizedComponent);
        personalizedHBox.getChildren().add(c.getUI());
    }*/

 /*   private File userChosenFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose sprite image");
        File file = fileChooser.showOpenDialog(new Stage());
        return file;
    }

    private String userChosenName() {
        TextInputDialog dialog = new TextInputDialog("image name");
        dialog.setTitle("Image Name");
        dialog.setHeaderText(null);
        dialog.setContentText("Please enter a name to save your image by");
        Optional<String> result = dialog.showAndWait();
        return result.isPresent() ? result.get() : "";
    }

    private String userChosenType() {
        List<String> spriteTypes = new ArrayList<>();
        spriteTypes.add("HERO");
        spriteTypes.add("ENEMY");
        spriteTypes.add("Block");

        ChoiceDialog<String> typeChooser = new ChoiceDialog<>("HERO", spriteTypes);
        typeChooser.setTitle("Choose your type!");
        typeChooser.setHeaderText(null);
        typeChooser.setContentText("Please choose which type of character you image will be");

        Optional<String> result = typeChooser.showAndWait();
        return result.isPresent() ? result.get() : "HERO";
    } */

}
