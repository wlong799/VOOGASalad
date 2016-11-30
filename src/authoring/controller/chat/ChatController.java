package authoring.controller.chat;

import java.util.Queue;

import authoring.view.chat.ChatView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import network.client.INetworkClient;
import network.client.NetworkClient;
import network.exceptions.JeopardyException;
import network.exceptions.MessageCreationFailureException;
import network.exceptions.ServerDownException;
import network.messages.Message;
import network.messages.MessageType;

public class ChatController {
	
	private ChatView myView;
	private INetworkClient myClient;

	public void init(ChatView view) {
		myView = view;
		KeyFrame frame = new KeyFrame(Duration.millis(100.0),
				new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {
				read();
			}
		});
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
	
	public void initClientWithName(String name) throws ServerDownException {
		myClient = new NetworkClient(name);
	}
	
	public void send(String chatMessage) {
		try {
			myClient.broadcast(chatMessage, MessageType.CHAT);
		} catch (MessageCreationFailureException | JeopardyException e) {
			e.printStackTrace();
		}
	}
	
	private void read() {
		if (myClient == null) return;
		try {
			Queue<Message> q = myClient.read(MessageType.CHAT);
			while (!q.isEmpty()) {
				Message msg = q.poll();
				String chat = (String) msg.getPayload();
				myView.appendText(msg.getSender() + ": " + chat);
			}
		} catch (JeopardyException e) {
			e.printStackTrace();
		}
	}
	
}
