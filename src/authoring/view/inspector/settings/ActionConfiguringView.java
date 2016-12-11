package authoring.view.inspector.settings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.ResourceBundle;

import authoring.AuthoringController;
import game_object.acting.ActionName;
import game_object.acting.ActionTrigger;
import game_object.acting.KeyEvent;
import game_object.core.ISprite;
import game_object.level.Level;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import resources.ResourceBundles;

public class ActionConfiguringView extends AbstractSettingsView {
    private ISprite mySprite;
    private Map<String, String> myEntryMap;
    private TableView<Map.Entry<String, String>> myTableView;
    private ObservableList<Map.Entry<String, String>> myItems;
    private ResourceBundle componentProperties;
    private ResourceBundle myLanguageResourceBundle;

    public ActionConfiguringView(AuthoringController controller, ISprite sprite) {
        super(controller);
        mySprite = sprite;
        componentProperties = ResourceBundles.componentProperties;
        myEntryMap = new HashMap<>();
        myLanguageResourceBundle = controller.getEnvironment().getLanguageResourceBundle();
    }

    @Override
    public void initializeSettings() {
        for (ActionName name : ActionName.values()) {
            Level currentLevel = this.getController().getEnvironment().getCurrentLevel();
            ActionTrigger trigger = currentLevel.getTriggerWithSpriteAndAction(mySprite, name);
            if (trigger == null) {
                myEntryMap.put(name.toString(), myLanguageResourceBundle.getString("none"));
            } else {
                myEntryMap.put(name.toString(), ((KeyEvent) trigger.getEvent()).getKeyCode().toString());
            }
        }
        myItems = FXCollections.observableArrayList(myEntryMap.entrySet());
        myTableView.setItems(myItems);
        setTableActions();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void initUI() {
        super.initUI();
        myTableView = new TableView<>();
        
        TableColumn<Map.Entry<String, String>, String> column1 = new TableColumn<>(myLanguageResourceBundle.getString("actionType"));
        column1.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getKey()));
        TableColumn<Map.Entry<String, String>, String> column2 = new TableColumn<>(myLanguageResourceBundle.getString("keyInput"));
        column2.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue()));

        myTableView.getColumns().addAll(column1, column2);
        myContent.getChildren().add(myTableView);
    }

    @Override
    protected void updateLayoutSelf() {
        super.updateLayoutSelf();
        myTableView.setPrefWidth(getWidth());
    }

    private void setTableActions() {
        myTableView.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                Entry<String, String> entry = myTableView.getSelectionModel().getSelectedItem();
                ActionName action = ActionName.getActionWithName(entry.getKey());
                Level currentLevel = this.getController().getEnvironment().getCurrentLevel();
                ActionTrigger currentTrigger =
                        currentLevel.getTriggerWithSpriteAndAction(mySprite, action);
                if (currentTrigger == null) {
                    currentTrigger = new ActionTrigger(new KeyEvent(null), mySprite, action);
                    currentLevel.getAllTriggers().add(currentTrigger);
                }
                KeyCode code = getKeyDialog(currentTrigger);
                if (code == null) {
                    currentLevel.getAllTriggers().remove(currentTrigger);
                    myEntryMap.put(action.toString(), myLanguageResourceBundle.getString("none"));
                } else {
                    currentTrigger.setEvent(new KeyEvent(code));
                    myEntryMap.put(action.toString(), code.toString());
                }
                myItems.clear();
                myItems.addAll(myEntryMap.entrySet());
            }
        });
    }

    private KeyCode getKeyDialog(ActionTrigger trigger) {
        List<String> choices = new ArrayList<>();
        choices.addAll(new ArrayList<>(Arrays.asList(
        		myLanguageResourceBundle.getString("noneCap"), 
        		myLanguageResourceBundle.getString("upCap"), 
        		myLanguageResourceBundle.getString("downCap"), 
        		myLanguageResourceBundle.getString("leftCap"), 
        		myLanguageResourceBundle.getString("rightCap"))));
        for (int i = 65; i <= 90; i++) {
            choices.add("" + (char) i);
        }
        ChoiceDialog<String> dialog = new ChoiceDialog<>(KeyCode.A.toString(), choices);
        dialog.setTitle(myLanguageResourceBundle.getString("choiceKey"));
        dialog.setHeaderText(myLanguageResourceBundle.getString("whenUserPressesKey") + trigger.getActionName());
        dialog.setContentText(myLanguageResourceBundle.getString("chooseKey"));

        Optional<String> result = dialog.showAndWait();

        if (!result.isPresent()) {
            return null;
        }
        switch (result.get()) {
            case "up":
                return KeyCode.UP;
            case "down":
                return KeyCode.DOWN;
            case "left":
                return KeyCode.LEFT;
            case "right":
                return KeyCode.RIGHT;
            case "NONE":
                return null;
            default:
                break;
        }
        return KeyCode.valueOf(result.get());
    }

}
