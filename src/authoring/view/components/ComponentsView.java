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

public class ComponentsView extends AbstractView {

    private List<Component> heroList, enemyList, blockList;
    private HBox personalizedHBox;
    private Button upload;
    private TabPane tabPane;

    public ComponentsView(AuthoringController controller) {
        super(controller);
    }

    @Override
    protected void updateLayoutSelf() {
        tabPane.setPrefWidth(this.getWidth());
        tabPane.setPrefHeight(this.getHeight());
    }

    @Override
    protected void initUI() {
        tabPane = new TabPane();
        tabPane.setPrefHeight(this.getHeight());
        tabPane.setPrefWidth(this.getWidth());
        this.addUI(tabPane);

        initHeroTab();
        initEnemyTab();
        initBlockTab();
        initUploadedTab();
    }

    private void initHeroTab() {
        Tab heroTab = initTab("Hero");
        heroTab.setClosable(false);
        tabPane.getTabs().add(heroTab);
        initHeroGraphics();
        ComponentListView heroListView = new ComponentListView(getController(), GameObjectType.Hero);

        for (Component hero : heroList) {
            ComponentView c = createComponentView(heroList, hero);
            heroListView.addComponent(hero);
        }
        heroTab.setContent(heroListView.getUI());
    }

    private void initEnemyTab() {
        Tab enemyTab = initTab("Enemy");
        enemyTab.setClosable(false);
        tabPane.getTabs().add(enemyTab);

        HBox hbox = initNewHBox();

        ScrollPane scrollPane = initScrollPane();
        scrollPane.setContent(hbox);

        initEnemyGraphics();

        for (Component enemy : enemyList) {
            ComponentView c = createComponentView(enemyList, enemy);
            hbox.getChildren().add(c.getUI());
        }
        enemyTab.setContent(scrollPane);
    }

    private void initBlockTab() {
        Tab blockTab = initTab("Block");
        blockTab.setClosable(false);
        tabPane.getTabs().add(blockTab);

        HBox hbox = initNewHBox();

        ScrollPane scrollPane = initScrollPane();
        scrollPane.setContent(hbox);

        initBlockGraphics();

        for (Component block : blockList) {
            ComponentView component = createComponentView(blockList, block);
            hbox.getChildren().add(component.getUI());
        }
        blockTab.setContent(scrollPane);
    }

    private void initUploadedTab() {
        Tab uploadTab = initTab("Uploaded");
        uploadTab.setClosable(false);
        tabPane.getTabs().add(uploadTab);

        personalizedHBox = initNewHBox();

        ScrollPane scrollPane = initScrollPane();
        scrollPane.setContent(personalizedHBox);

        personalizedHBox.getChildren().add(initUploadButton());

        uploadTab.setContent(scrollPane);
    }

    private Tab initTab(String tabName) {
        Tab tab = new Tab();
        tab.setText(tabName);
        return tab;
    }

    private ScrollPane initScrollPane() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToHeight(true);
        return scrollPane;
    }

    private HBox initNewHBox() {
        HBox hbox = new HBox();
        hbox.setSpacing(20);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(0, 0, 0, 10));
        return hbox;
    }

    private void initHeroGraphics() {
        heroList = new ArrayList<>();
        heroList.add(GameObjectConstants.BLUE_SNAIL);
        heroList.add(GameObjectConstants.RIBBON_PIG);
    }

    private void initEnemyGraphics() {
        enemyList = new ArrayList<>();
        enemyList.add(GameObjectConstants.ELIZA);
        enemyList.add(GameObjectConstants.ORANGE_MUSHROOM);
        enemyList.add(GameObjectConstants.SLIME);
    }

    private void initBlockGraphics() {
        blockList = new ArrayList<>();
        blockList.add(GameObjectConstants.BRICK);
        blockList.add(GameObjectConstants.BUSH);
        blockList.add(GameObjectConstants.STONE_BLOCK);
    }

    private ComponentView createComponentView(List<Component> list, Component enemy) {
        ComponentView c = new ComponentView(this.getController());
        c.setWidth(50);
        c.setComponent(enemy);
        return c;
    }

    private Button initUploadButton() {
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
    }

    private GameObjectType spriteType(String input) {
        switch (input) {
            case "Hero":
                return GameObjectType.Hero;
            case "Enemy":
                return GameObjectType.Enemy;
            case "Block":
                return GameObjectType.StaticBlock;
        }
        return GameObjectType.Hero;
    }

    private File userChosenFile() {
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
        spriteTypes.add("Hero");
        spriteTypes.add("Enemy");
        spriteTypes.add("Block");

        ChoiceDialog<String> typeChooser = new ChoiceDialog<>("Hero", spriteTypes);
        typeChooser.setTitle("Choose your type!");
        typeChooser.setHeaderText(null);
        typeChooser.setContentText("Please choose which type of character you image will be");

        Optional<String> result = typeChooser.showAndWait();
        return result.isPresent() ? result.get() : "Hero";
    }

}
