package authoring.view.inspector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import game_object.acting.ActionName;
import game_object.acting.ActionTrigger;
import game_object.core.ISprite;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;

public class ActionConfiguringView extends AbstractView {

	private ActionTypeKeyInput actionTypeLeft;
	private ActionTypeKeyInput actionTypeRight;
	private ActionTypeKeyInput actionTypeJump;
	private ActionTypeKeyInput actionTypeShoot;
	private Map<ISprite, ActionTrigger> spritesWithSetActionTriggers;
	private ISprite sprite;

	public ActionConfiguringView(AuthoringController controller) {
		super(controller);
	}
	
	public void setSprite(ISprite sprite) {
		//TODO: use controller to do this
		this.sprite = sprite;
	}

	@Override
	protected void initUI() {
		this.addUI(unitTable());
	}

	@Override
	protected void updateLayoutSelf() {

	}

	private VBox unitTable(){
		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding((new Insets(5,5,5,5)));

		final Label label = new Label ("Action Type Key Inputs");
		label.setFont(new Font("Arial", 20));

		Button btnLeft = new Button("Set Left");
		Button btnRight = new Button("Set Right");
		Button btnJump = new Button("Set Jump");
		Button btnShoot = new Button("Set Shoot");

		btnLeft.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		btnRight.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		btnJump.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		btnShoot.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		btnLeft.setFont(Font.font(10.3));
		btnRight.setFont(Font.font(10.3));
		btnJump.setFont(Font.font(10.3));
		btnShoot.setFont(Font.font(10.3));

		btnLeft.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				KeyCode kc = keyChoiceDialog("left");
				if (spritesWithSetActionTriggers.containsKey(sprite)) {
					//TODO: replace null with event
					spritesWithSetActionTriggers.get(sprite).setEvent(null);
				}
				//TODO: replace null with event
				ActionTrigger actionTrigger = new ActionTrigger(null, sprite, ActionName.MOVE_LEFT);
				spritesWithSetActionTriggers.put(sprite, actionTrigger);
			}
		});

		TilePane tileButtons = new TilePane(Orientation.HORIZONTAL);
		tileButtons.setPadding(new Insets(8, 10, 8, 0));
		tileButtons.setHgap(10.0);
		tileButtons.setVgap(8.0);
		tileButtons.getChildren().addAll(btnLeft, btnRight, btnJump, btnShoot);

		vbox.getChildren().addAll(label, getInitialTableData(), tileButtons);

		return vbox;
	}

	private KeyCode keyChoiceDialog(String action) {

		List<String> choices = new ArrayList<>();
		choices.add(KeyCode.A.toString());
		choices.add(KeyCode.B.toString());
		choices.add(KeyCode.C.toString());

		ChoiceDialog<String> dialog = new ChoiceDialog<>(KeyCode.A.toString(), choices);
		dialog.setTitle("Choice Key Input to Control this Action");
		dialog.setHeaderText("When you press this key during the game, the character will " + action);
		dialog.setContentText("Choose your key input:");

		Optional<String> result = dialog.showAndWait();

		if (!result.isPresent()) {
			return KeyCode.A;
		}

		switch (result.get()) {
		case "a": return KeyCode.A;
		case "b": return KeyCode.B;
		case "c": return KeyCode.C;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private TableView<Map.Entry<String,String>> getInitialTableData() {
		
		Map<String, String> map = new HashMap<>();
		
		actionTypeLeft = new ActionTypeKeyInput(ActionName.MOVE_LEFT, sprite);
		actionTypeRight = new ActionTypeKeyInput(ActionName.MOVE_RIGHT, sprite);
		actionTypeJump = new ActionTypeKeyInput(ActionName.JUMP, sprite);
		actionTypeShoot = new ActionTypeKeyInput(ActionName.SHOOT, sprite);
		
        map.put(actionTypeLeft.getActionName(), "None");
        map.put(actionTypeRight.getActionName(), "None");
        map.put(actionTypeJump.getActionName(), "None");
        map.put(actionTypeShoot.getActionName(), "None");

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

        ObservableList<Map.Entry<String, String>> items = FXCollections.observableArrayList(map.entrySet());
        final TableView<Map.Entry<String,String>> table = new TableView<>(items);
        table.getColumns().addAll(column1, column2);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

       return table;
	}

}
