package authoring.view.inspector;

import java.awt.Desktop.Action;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractAction;

import authoring.AuthoringController;
import authoring.constants.UIConstants;
import authoring.view.AbstractView;
import authoring.updating.IPublisher;
import authoring.updating.ISubscriber;
import authoring.view.canvas.SpriteView;
import game_object.acting.ActionName;
import game_object.core.ISprite;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.effect.Effect;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;

public class InspectorView extends AbstractView implements ISubscriber {
	
	private SpriteView inspectedSpriteView;
	private VBox configs;
	private VBox xBox;
	private VBox yBox;
	private VBox zBox;
	private VBox widthBox;
	private VBox heightBox;
	private VBox keyInputBox;
	private TableView<ActionTypeKeyInput> table;
	
	public interface ITextChangeHandler {
		void handle(String newVal);
	}

	public InspectorView(AuthoringController controller) {
		super(controller);
	}
	
	@Override
	public void didUpdate(IPublisher target) {
		if (target instanceof AuthoringController) {
			inspectedSpriteView = ((AuthoringController) target).getSelectedSpriteView();
			updateUI();
		}
	}
	
	@Override
	protected void initUI() {
		this.getController().addSubscriber(this);
		configs = new VBox();

		this.addUI(configs);
	}

	@Override
	protected void updateLayoutSelf() {
		configs.setPrefWidth(this.getWidth());
		configs.setPrefHeight(this.getHeight());
	}
	
	private void updateUI() {
		configs.getChildren().clear();
		ISprite sprite = inspectedSpriteView.getSprite();
		xBox = makeDoubleInputBox("Position X", sprite.getPosition().getX(), 
				(newVal) -> {
					inspectedSpriteView.setAbsolutePositionX(Double.parseDouble(newVal));
				});
		yBox = makeDoubleInputBox("Position Y", sprite.getPosition().getY(), 
				(newVal) -> {
					inspectedSpriteView.setAbsolutePositionY(Double.parseDouble(newVal));
				});
		zBox = makeDoubleInputBox("Position Z", sprite.getPosition().getZ(),
				(newVal) -> {
					inspectedSpriteView.setAbsolutePositionZ(Double.parseDouble(newVal));
				});
		widthBox = makeDoubleInputBox("Width", sprite.getDimension().getWidth(),
				(newVal) -> {
					inspectedSpriteView.setDimensionWidth(Double.parseDouble(newVal));
				});
		heightBox = makeDoubleInputBox("Height", sprite.getDimension().getHeight(),
				(newVal) -> {
					inspectedSpriteView.setDimensionHeight(Double.parseDouble(newVal));
				});
		keyInputBox = unitTable();
		configs.getChildren().addAll(xBox, yBox, zBox, widthBox, heightBox, keyInputBox);
	}
	
	private VBox makeDoubleInputBox(String title, double defaultValue, 
			ITextChangeHandler handler) {
		VBox box = new VBox();
		Label label = new Label(title);
		label.setFont(Font.font("Segoe UI Semibold"));
		TextField tf = new TextField(defaultValue + "");
		box.getChildren().addAll(label, tf);
		box.setPadding(new Insets(5,5,5,5));
		tf.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				handler.handle(tf.getText());
			}
		});
		tf.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (!newVal) {
				handler.handle(tf.getText());
			}
		});
		return box;
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

		TilePane tileButtons = new TilePane(Orientation.HORIZONTAL);
		tileButtons.setPadding(new Insets(8, 10, 8, 0));
		tileButtons.setHgap(10.0);
		tileButtons.setVgap(8.0);
		tileButtons.getChildren().addAll(btnLeft, btnRight, btnJump, btnShoot);
		
//		Button setKeyInput = new Button("Set");
//		setKeyInput.setPrefWidth(UIConstants.RIGHT_WIDTH + 100);
//		setKeyInput.setOnAction(new EventHandler<ActionEvent>() {
//			@Override public void handle(ActionEvent e) {
//				//do this
//			}
//		});
		
		vbox.getChildren().addAll(label, getInitialTableData(), tileButtons);
		
		return vbox;
	}
	
	@SuppressWarnings("unchecked")
	private TableView<Map.Entry<String,String>> getInitialTableData() {
		
		Map<String, String> map = new HashMap<>();
		
		ActionTypeKeyInput actionTypeLeft = new ActionTypeKeyInput(ActionName.MOVE_LEFT);
		ActionTypeKeyInput actionTypeRight = new ActionTypeKeyInput(ActionName.MOVE_RIGHT);
		ActionTypeKeyInput actionTypeJump = new ActionTypeKeyInput(ActionName.JUMP);
		ActionTypeKeyInput actionTypeShoot = new ActionTypeKeyInput(ActionName.SHOOT);
		
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

        
//        TableColumn<TableView, StringProperty> column = new TableColumn<>("option");
//        column.setCellValueFactory(i -> {
//            final StringProperty value = i.getValue().optionProperty();
//            // binding to constant value
//            return Bindings.createObjectBinding(() -> value);
//        });
//
//        column.setCellFactory(col -> {
//            TableCell<TableView, StringProperty> c = new TableCell<>();
//            final ComboBox<String> comboBox = new ComboBox<>(options);
//            c.itemProperty().addListener((observable, oldValue, newValue) -> {
//                if (oldValue != null) {
//                    comboBox.valueProperty().unbindBidirectional(oldValue);
//                }
//                if (newValue != null) {
//                    comboBox.valueProperty().bindBidirectional(newValue);
//                }
//            });
//            c.graphicProperty().bind(Bindings.when(c.emptyProperty()).then((Node) null).otherwise(comboBox));
//            return c;
//        });
        
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