package authoring.share;

import java.util.Queue;

import authoring.AuthoringController;
import authoring.share.action.AddSpriteAction;
import authoring.share.action.IAction;
import authoring.share.action.MoveSpriteAction;
import authoring.share.exception.ShareEditException;
import authoring.view.canvas.SpriteView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import network.exceptions.ServerDownException;
import network.exceptions.SessionExpiredException;
import network.messages.Message;
import network.messages.MessageType;

public class ShareEditController {
	
	private AuthoringController myAuthoringController;
	private NetworkController myNetworkController;
	
	public ShareEditController(NetworkController networkController, AuthoringController authoringController) throws ServerDownException {
		myNetworkController = networkController;
		myAuthoringController = authoringController;
	}
	
	/**
	 * @param spView view to be selected
	 * @throws ShareEditException if the current SpriteView is being editted by another user
	 */
	public void select(SpriteView spView) throws ShareEditException {
		if (!hasClient()) return;
		String holderName = myNetworkController.getClient().tryLock(spView.getID());
		if (holderName != null && !holderName.equals(myNetworkController.getMyName())) {
			throw new ShareEditException(holderName);
		}
	}
	
	/**
	 * @param spView SpriteView currently locked by the user
	 * unlock the selected SpriteView
	 * no-op if the user does not have the lock
	 */
	public void unlock(SpriteView spView) {
		if (!hasClient()) return;
		myNetworkController.getClient().unlock(spView.getID());
	}
	
	/**
	 * @param spView SpriteView to be added
	 * @param x position x
	 * @param y position y
	 * send an add action to the server
	 */
	public void add(SpriteView spView, double x, double y) {
		if (!hasClient()) return;
		IAction addAction = new AddSpriteAction(spView, x, y);
		myNetworkController.getClient().broadcast(addAction, MessageType.ACTION);
	}
	
	/**
	 * @param spView SpriteView to be removed
	 * send a remove action to the server
	 */
	public void remove(SpriteView spView) {
		if (!hasClient()) return;
	}
	
	/**
	 * @param spView SpriteView to be moved
	 * @param newX new position x for the sprite view
	 * @param newY new position y for the sprite view
	 * send a move action to the server
	 */
	public void move(SpriteView spView, double newX, double newY) {
		if (!hasClient()) return;
		IAction moveAction = new MoveSpriteAction(spView.getID(), newX, newY);
		myNetworkController.getClient().broadcast(moveAction, MessageType.ACTION);
	}
	
	public void init() {
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
	
	private void read() {
		try {
			Queue<Message> q = myNetworkController.getClient().read(MessageType.ACTION);
			while (!q.isEmpty()) {
				Message msg = q.poll();
				IAction chat = (IAction) msg.getPayload();
				chat.apply(myAuthoringController);
			}
		} catch (SessionExpiredException e) {
			System.out.println("session expired");
		} catch (ShareEditException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private boolean hasClient() {
		return myNetworkController.getClient() != null;
	}

}
