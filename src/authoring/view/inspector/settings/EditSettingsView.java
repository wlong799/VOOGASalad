package authoring.view.inspector.settings;

import authoring.AuthoringController;
import javafx.scene.control.CheckBox;

public class EditSettingsView extends AbstractSettingsView {
    private static final String LABEL_TEXT = "Snap to Grid";
    private CheckBox mySnapBox;

    public EditSettingsView(AuthoringController controller) {
        super(controller);
    }


    @Override
    protected void initUI() {
        super.initUI();
        myLabel.setText(LABEL_TEXT);
        mySnapBox = new CheckBox();
        mySnapBox.setSelected(getController().getCanvasController().getSnapToGrid());
        mySnapBox.selectedProperty().addListener((obv, old_val, new_val) ->
                getController().getCanvasController().setSnapToGrid(new_val));
        myContent.getChildren().add(mySnapBox);
    }

    @Override
    public void initializeSettings() {
        // nothing
    }
}
