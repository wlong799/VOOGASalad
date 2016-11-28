package authoring.view.inspector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import game_object.acting.ActionName;
import game_object.acting.ActionTrigger;
import game_object.acting.KeyEvent;
import game_object.core.ISprite;
import game_object.level.Level;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class ActionConfiguringView extends AbstractView {
	
	private ISprite sprite;
	private VBox myTableBox;
	private Map<String, String> myEntryMap;
	private TableView<Map.Entry<String,String>> myTableView;
	private ObservableList<Map.Entry<String, String>> myItems;

	public ActionConfiguringView(AuthoringController controller) {
		super(controller);
	}

	public void setSprite(ISprite sprite) {
		//TODO: use controller to do this
		this.sprite = sprite;
		myTableBox = unitTable();
	}

	@Override
	public Parent getUI() {
		return myTableBox;
	}

	@Override
	protected void initUI() {
	}

	@Override
	protected void updateLayoutSelf() {
	}

	private VBox unitTable(){
		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding((new Insets(5,5,5,5)));
		vbox.getChildren().add(getInitialTableData());
		return vbox;
	}

	@SuppressWarnings("unchecked")
	private TableView<Map.Entry<String,String>> getInitialTableData() {

		myEntryMap = new HashMap<>();
		
		for (ActionName name : ActionName.values()) {
			Level currentLevel = this.getController().getEnvironment().getCurrentLevel();
			ActionTrigger trigger = currentLevel.getTriggerWithSpriteAndAction(sprite, name);
			if (trigger == null) {
				myEntryMap.put(name.toString(), "None");
			}
			else {
				myEntryMap.put(name.toString(), ((KeyEvent) trigger.getEvent()).getKeyCode().toString());
			}
		}

		TableColumn<Map.Entry<String, String>, String> column1 = new TableColumn<>("Action Type");
		column1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, String>, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, String>, String> p) {
				return new SimpleStringProperty(p.getValue().getKey());
			}
		});

		TableColumn<Map.Entry<String, String>, String> column2 = new TableColumn<>("Key Input");
		column2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, String>, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, String>, String> p) {
				return new SimpleStringProperty(p.getValue().getValue());
			}
		});

		myItems = FXCollections.observableArrayList(myEntryMap.entrySet());
		myTableView = new TableView<>(myItems);
		myTableView.getColumns().addAll(column1, column2);
		myTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		myTableView.setMaxHeight(150);

		setTableActions();

		return myTableView;
	}

	private void setTableActions() {
		myTableView.setOnMousePressed(event -> {
			if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
				Entry<String, String> entry = myTableView.getSelectionModel().getSelectedItem();
				ActionName action = ActionName.getActionWithName(entry.getKey());
				Level currentLevel = this.getController().getEnvironment().getCurrentLevel();
				ActionTrigger currentTrigger = 
						currentLevel.getTriggerWithSpriteAndAction(sprite, action);
				if (currentTrigger == null) {
					currentTrigger = new ActionTrigger(new KeyEvent(null), sprite, action);
					currentLevel.addTrigger(currentTrigger);
				}
				KeyCode code = getKeyDialog(currentTrigger);
				if (code == null) {
					currentLevel.removeTrigger(currentTrigger);
					myEntryMap.put(action.toString(), "None");
				}
				else {
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
		choices.addAll(new ArrayList<String>(Arrays.asList("NONE", "UP", "DOWN", "LEFT", "RIGHT")));
		for (int i = 65; i <= 90; i++) {
			choices.add("" + (char) i);
		}
		ChoiceDialog<String> dialog = new ChoiceDialog<>(KeyCode.A.toString(), choices);
		dialog.setTitle("Choice Key Input to Control this Action");
		dialog.setHeaderText("When you press this key during the game, the character will " + trigger.getActionName());
		dialog.setContentText("Choose your key input:");

		Optional<String> result = dialog.showAndWait();
		
		if (!result.isPresent()) {
			return null;
		}
		switch(result.get()) {
		case "up":
			return KeyCode.UP;
		case "down":
			return KeyCode.DOWN;
		case "left":
			return KeyCode.LEFT;
		case "right":
			return KeyCode.RIGHT;
		default:
			break;
		}
		return KeyCode.valueOf(result.get());
	}

}
