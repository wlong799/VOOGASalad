package authoring.view.chat;

import java.util.ResourceBundle;

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
import resources.ResourceBundles;

public class ChatView extends AbstractView {

	private NetworkController myNetworkController;
	private TextFlow myTextFlow;
	private ScrollPane myScrollPane;
	private TextField myTextField;
	private Button myEnterButton;
	private HBox mySendingBox;
	private VBox myChatViewCollection;
	private ChatController myController;
	private ResourceBundle myChatWindowProperties;

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
		myTextFlow.getChildren().add(newText);
		myTextFlow.requestLayout();
		myScrollPane.setVvalue(Double.parseDouble(myChatWindowProperties.getString("SCROLL_PANE_VERTICAL_VALUE")));
	}

	@Override
	protected void initUI() {
		myChatWindowProperties = ResourceBundles.chatWindowProperties;
		
		myTextFlow = new TextFlow();
		myTextFlow.setMaxHeight(this.getHeight());
		myScrollPane = new ScrollPane(myTextFlow);
		myScrollPane.setFitToWidth(true);
		myScrollPane.setPrefHeight(this.getHeight());
		myScrollPane.setStyle("-fx-background: rgb(255,255,255);");

		initSendingBox();

		myChatViewCollection = new VBox();
		myChatViewCollection.getChildren().addAll(myScrollPane, mySendingBox);

		this.addUI(myChatViewCollection);

		getName();
	}

	@Override
	protected void updateLayoutSelf() {
		myChatViewCollection.setPrefWidth(getWidth());
		myChatViewCollection.setPrefHeight(getHeight());
		myScrollPane.setPrefWidth(getWidth());
		myScrollPane.setPrefHeight(getHeight() - Double.parseDouble(myChatWindowProperties.getString("SENDING_HEIGHT")));
		myTextField.setPrefWidth(this.getWidth() - Double.parseDouble(myChatWindowProperties.getString("BUTTON_WIDTH")));
		myEnterButton.setPrefWidth(Double.parseDouble(myChatWindowProperties.getString("BUTTON_WIDTH")));
		mySendingBox.setPrefHeight(Double.parseDouble(myChatWindowProperties.getString("SENDING_HEIGHT")));
	}

	private void initSendingBox() {
		mySendingBox = new HBox();
		myTextField = new TextField();
		myEnterButton = new Button("Send");
		myEnterButton.getStyleClass().add("send-button");
		myTextField.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				send();
			}
		});
		myEnterButton.setOnAction(e -> send());
		mySendingBox.getChildren().addAll(myTextField, myEnterButton);
	}

	private void send() {
		String current = myTextField.getText();
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
		myTextField.clear();
	}

	private void getName() {
		this.appendText("Welcome to chat, please enter your name:");
	}

}
