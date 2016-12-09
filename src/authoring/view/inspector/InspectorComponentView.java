package authoring.view.inspector;

import authoring.AuthoringController;
import authoring.view.components.Component;
import authoring.view.inspector.settings.ImageChangeButtonView;
import authoring.view.inspector.settings.NullSettingsView;
import authoring.view.inspector.settings.TextInputBoxView;

/**
 * Inspector view that allows for editing of game-wide settings/metadata.
 *
 * @author Will Long
 */
public class InspectorComponentView extends AbstractInspectorTabView {
    private Component myComponent;

    private TextInputBoxView myTitleInputView, myDescriptionInputView;
    private ImageChangeButtonView myImageChangeButtonView;

    public InspectorComponentView(AuthoringController controller) {
        super(controller);
    }

    @Override
    protected void initUI() {
        super.initUI();
        myTitleInputView = new TextInputBoxView(getController(), "Title", "", newValue ->
                myComponent.setTitle(newValue));
        myImageChangeButtonView = new ImageChangeButtonView(getController(), "", newValue ->
                myComponent.setImagePath(newValue));
        myDescriptionInputView = new TextInputBoxView(getController(), "Description", "", newValue ->
                myComponent.setDescription(newValue));
        addSettingsViews(new NullSettingsView(getController()));
    }

    public void setComponent(Component component) {
        myComponent = component;
        updateUI();
    }

    private void updateUI() {
        clearSettingsView();
        if (myComponent == null) {
            addSettingsView(new NullSettingsView(getController()));
            return;
        }
        myTitleInputView.setText(myComponent.getTitle());
        myImageChangeButtonView.setImageFilename(myComponent.getImagePath());
        myDescriptionInputView.setText(myComponent.getDescription());
        addSettingsViews(myTitleInputView, myImageChangeButtonView, myDescriptionInputView);
    }
}