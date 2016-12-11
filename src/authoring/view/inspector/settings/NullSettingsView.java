package authoring.view.inspector.settings;

import java.util.ResourceBundle;

import authoring.AuthoringController;

/**
 * Simple message to display when settings not available for editing.
 *
 * @author Will Long
 * @version 12/8/16
 */
public class NullSettingsView extends AbstractSettingsView {
    private ResourceBundle myLanguageResourceBundle;

    public NullSettingsView(AuthoringController controller) {
        super(controller);
    }

    @Override
    public void initializeSettings() {
    	myLanguageResourceBundle = super.getController().getEnvironment().getLanguageResourceBundle();
        myLabel.setText(myLanguageResourceBundle.getString("settingsNotAvail"));
    }
}
