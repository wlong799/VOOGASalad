package authoring.view.chat;

import authoring.AuthoringController;
import authoring.controller.chat.ChatController;
import authoring.share.NetworkController;
import authoring.view.AbstractView;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import network.exceptions.ServerDownException;

public class ChatView extends AbstractView {

	private static final double BUTTON_WIDTH = 50;
	private static final double SENDING_HEIGHT = 50;

	private TextFlow textFlow;
	private ScrollPane scrollPane;
	private TextField textField;
	private Button enterButton;
	private HBox sendingBox;
	private VBox all;
	private NetworkController myNetworkController;
	private ChatController myController;

	private boolean hasName = false;

	public ChatView(AuthoringController controller) {
		super(controller);
		myNetworkController = controller.getNetworkController();
		myController = myNetworkController.getChatController();
		myController.init(this);
	}

	public void appendText(String text) {
		Text newText = new Text(text + "\n");
		newText.setFill(Color.BLACK);
		textFlow.getChildren().add(newText);
		textFlow.requestLayout();
		scrollPane.setVvalue(1.0);
	}

	@Override
	protected void initUI() {
		textFlow = new TextFlow();
		textFlow.setMaxHeight(this.getHeight());
		scrollPane = new ScrollPane(textFlow);
		scrollPane.setFitToWidth(true);
		scrollPane.setPrefHeight(this.getHeight());
		scrollPane.setStyle("-fx-background: rgb(255,255,255);");

		initSendingBox();

		all = new VBox();
		all.getChildren().addAll(scrollPane, sendingBox);

		this.addUI(all);

		getName();
	}

	@Override
	protected void updateLayoutSelf() {
		all.setPrefWidth(getWidth());
		all.setPrefHeight(getHeight());
		scrollPane.setPrefWidth(getWidth());
		scrollPane.setPrefHeight(getHeight() - SENDING_HEIGHT);
		textField.setPrefWidth(this.getWidth() - BUTTON_WIDTH);
		enterButton.setPrefWidth(BUTTON_WIDTH);
		sendingBox.setPrefHeight(SENDING_HEIGHT);
	}

	private void initSendingBox() {
		sendingBox = new HBox();
		textField = new TextField();
		enterButton = new Button("Send");
		textField.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				send();
			}
		});
		enterButton.setOnAction(e -> send());
		sendingBox.getChildren().addAll(textField, enterButton);
	}

	private void send() {
		String current = textField.getText();
		if (current.equals("")) {
			return;
		}
		if (hasName) {
			myController.send(current);
		}
		else {
			hasName = true;
			try {
				myNetworkController.initClientWithName(current);
				this.appendText("Oh hellooooo " + current);
				this.appendText("Start chatting now!!");
			} catch (ServerDownException e) {
				this.appendText("Server Down");
			}
		}
		textField.clear();
	}

	private void getName() {
		this.appendText("Welcome to chat, please enter your name:");
	}

}
