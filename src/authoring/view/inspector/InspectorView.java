package authoring.view.inspector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import authoring.updating.IPublisher;
import authoring.updating.ISubscriber;
import authoring.view.canvas.SpriteView;
import game_object.acting.ActionName;
import game_object.core.ISprite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
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
		xBox = makeDoubleInputBox("position X", sprite.getPosition().getX(), 
				(newVal) -> {
					inspectedSpriteView.setAbsolutePositionX(Double.parseDouble(newVal));
				});
		yBox = makeDoubleInputBox("position Y", sprite.getPosition().getY(), 
				(newVal) -> {
					inspectedSpriteView.setAbsolutePositionY(Double.parseDouble(newVal));
				});
		zBox = makeDoubleInputBox("position Z", sprite.getPosition().getZ(),
				(newVal) -> {
					inspectedSpriteView.setAbsolutePositionZ(Double.parseDouble(newVal));
				});
		widthBox = makeDoubleInputBox("width", sprite.getDimension().getWidth(),
				(newVal) -> {
					inspectedSpriteView.setDimensionWidth(Double.parseDouble(newVal));
				});
		heightBox = makeDoubleInputBox("height", sprite.getDimension().getHeight(),
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
		
		TableView<ActionTypeKeyInput> table = new TableView<ActionTypeKeyInput>();
		ObservableList<ActionTypeKeyInput> data = getInitialTableData();
		table.getItems().addAll(data);
		
		TableColumn<ActionTypeKeyInput, String> actionType = new TableColumn<ActionTypeKeyInput, String>("Action Type");
		TableColumn<ActionTypeKeyInput, String> keyInput = new TableColumn<ActionTypeKeyInput, String>("Key Input");
		
		table.getColumns().addAll(actionType, keyInput);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		vbox.getChildren().addAll(label, table);
		
		return vbox;
	}
	
	private ObservableList<ActionTypeKeyInput> getInitialTableData() {
		
		ActionTypeKeyInput left = new ActionTypeKeyInput (ActionName.MOVE_LEFT);
		ActionTypeKeyInput right = new ActionTypeKeyInput (ActionName.MOVE_RIGHT);
		ActionTypeKeyInput jump = new ActionTypeKeyInput (ActionName.JUMP);
		ActionTypeKeyInput shoot = new ActionTypeKeyInput (ActionName.SHOOT);
		
		ObservableList<ActionTypeKeyInput> list = FXCollections.observableArrayList(left,right,jump,shoot);
		return list;
	}
	

}
