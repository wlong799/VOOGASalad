package authoring.view.inspector.settings;

import authoring.AuthoringController;

/**
 * Simple message to display when settings not available for editing.
 *
 * @author Will Long
 * @version 12/8/16
 */
public class NullSettingsView extends AbstractSettingsView {
    private static final String LABEL_MESSAGE = "Settings not currently available";

    public NullSettingsView(AuthoringController controller) {
        super(controller);
    }

    @Override
    public void initializeSettings() {
        myLabel.setText(LABEL_MESSAGE);
    }
}
