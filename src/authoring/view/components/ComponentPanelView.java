package authoring.view.components;

import authoring.AuthoringController;
import authoring.constants.UIConstants;
import authoring.view.AbstractView;
import game_object.constants.GameObjectConstants;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Optional;

public class ComponentPanelView extends AbstractView {
    private static final double LIST_WIDTH_RATIO = 0.9;
    private static final double BUTTON_HEIGHT_RATIO = 0.5;
    private static final double BUTTON_WIDTH_RATIO = 0.5;

    private HBox myContent;
    private TabPane myTabPane;
    private Button myComponentCreationButton;
    private ImageView myButtonImageView;

    private ComponentPanelView(AuthoringController controller) {
        super(controller);
    }

    @Override
    protected void initUI() {
        myContent = new HBox();
        myContent.setAlignment(Pos.CENTER);
        myTabPane = new TabPane();
        //myTabPane.getStyleClass().add("data/css/style2.css");

        myButtonImageView = new ImageView(GameObjectConstants.UPLOAD);
        myButtonImageView.setPreserveRatio(true);
        myComponentCreationButton = new Button();
        myComponentCreationButton.setPrefHeight(UIConstants.BOTTOM_HEIGHT + 50);
        myComponentCreationButton.setPrefWidth(200);
        myComponentCreationButton.setGraphic(myButtonImageView);
        setComponentCreationButtonAction();

        myContent.getChildren().addAll(myTabPane, myComponentCreationButton);
        addUI(myContent);
    }

    @Override
    protected void updateLayoutSelf() {
        double listWidth = getWidth() * LIST_WIDTH_RATIO;
        double buttonWidth = getWidth() - listWidth;
        myContent.setPrefWidth(getWidth());
        myContent.setPrefHeight(getHeight());
        myContent.setSpacing(buttonWidth * (BUTTON_WIDTH_RATIO / 2));

        myTabPane.setPrefWidth(listWidth);
        myTabPane.setPrefHeight(getHeight());
        getSubViews().forEach(subView -> {
            subView.setWidth(listWidth);
            subView.setHeight(getHeight());
        });

        double newImageWidth = buttonWidth * BUTTON_WIDTH_RATIO;
        double newImageHeight = getHeight() * BUTTON_HEIGHT_RATIO;
        if (myButtonImageView.getImage().getWidth() / newImageWidth >
                myButtonImageView.getImage().getHeight() / newImageHeight) {
            myButtonImageView.setFitHeight(0);
            myButtonImageView.setFitWidth(newImageWidth);
        } else {
            myButtonImageView.setFitWidth(0);
            myButtonImageView.setFitHeight(newImageHeight);
        }
    }

    public void addTab(String tabName, ComponentListView componentListView) {
        Tab newTab = new Tab(tabName);
        setTabStyle(newTab);
        newTab.setClosable(false);
        final ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(componentListView.getUI());
        newTab.setContent(scrollPane);
        myTabPane.getTabs().add(newTab);
        addSubView(componentListView);
    }
    
    private void setTabStyle(Tab tab) {
    	tab.setStyle(
        		"-fx-background-insets: 0 1 0 1,0,0;"
        		+"-fx-alignment: CENTER;"
        		+"-fx-text-fill: #828282;"
        		+ "-fx-font-size: 12px;"
        		+ "-fx-font-weight: bold;"
        		);
    }

    private void setComponentCreationButtonAction() {
        myComponentCreationButton.setOnAction(event -> {
            File imageFile = showFileSelectionDialog();
            if (imageFile == null) return;
            String componentTitle = showStringInputDialog("Component Name");
            String componentDescription = showStringInputDialog("Component Description");
            if (!(imageFile == null || componentTitle == null || componentDescription == null)) {
                int selectedTabIndex = myTabPane.getSelectionModel().getSelectedIndex();
                ComponentListView componentListView = (ComponentListView) getSubViews().get(selectedTabIndex);
                componentListView.addComponent(imageFile.toURI().toString(), componentTitle, componentDescription);
                updateLayout();
            }
        });
    }

    private File showFileSelectionDialog() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Sprite Image");
        File file = fileChooser.showOpenDialog(new Stage());
        return file;
    }

    private String showStringInputDialog(String dialogMessage) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText(null);
        dialog.setContentText(dialogMessage);
        Optional<String> result = dialog.showAndWait();
        return result.isPresent() ? result.get() : null;
    }
    
}
