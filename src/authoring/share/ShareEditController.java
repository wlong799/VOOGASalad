package authoring.share;

import java.util.Queue;

import authoring.AuthoringController;
import authoring.share.action.AddSpriteAction;
import authoring.share.action.DeselectSpriteAction;
import authoring.share.action.IAction;
import authoring.share.action.MoveSpriteAction;
import authoring.share.action.RemoveSpriteAction;
import authoring.share.action.ResizeSpriteAction;
import authoring.share.action.SelectSpriteAction;
import authoring.share.exception.ShareEditException;
import authoring.ui.DialogFactory;
import authoring.view.canvas.SpriteView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import network.exceptions.SessionExpiredException;
import network.messages.Message;
import network.messages.MessageType;

public class ShareEditController {
	
	private AuthoringController myAuthoringController;
	private NetworkController myNetworkController;
	
	public ShareEditController(NetworkController networkController, AuthoringController authoringController) {
		myNetworkController = networkController;
		myAuthoringController = authoringController;
		init();
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
		IAction selectAction = new SelectSpriteAction(spView.getID(), myNetworkController.getMyName());
		myNetworkController.getClient().broadcast(selectAction, MessageType.ACTION);
	}
	
	/**
	 * @param spView SpriteView currently locked by the user
	 * unlock the selected SpriteView
	 * no-op if the user does not have the lock
	 */
	public void unlock(SpriteView spView) {
		if (!hasClient()) return;
		IAction deselectAction = new DeselectSpriteAction(spView.getID());
		myNetworkController.getClient().broadcast(deselectAction, MessageType.ACTION);
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
		IAction removeAction = new RemoveSpriteAction(spView.getID());
		myNetworkController.getClient().broadcast(removeAction, MessageType.ACTION);
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
	
	/**
	 * @param spView SpriteView to be resized
	 * @param newWidth new width of the view
	 * @param newHeight new height of the view
	 */
	public void resize(SpriteView spView, double newWidth, double newHeight) {
		if (!hasClient()) return;
		IAction resizeAction = new ResizeSpriteAction(spView.getID(), newWidth, newHeight);
		myNetworkController.getClient().broadcast(resizeAction, MessageType.ACTION);
	}
	
	private void init() {
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
		if (!hasClient()) return;
		try {
			Queue<Message> q = myNetworkController.getClient().read(MessageType.ACTION);
			while (!q.isEmpty()) {
				Message msg = q.poll();
				if (msg.getSender().equals(myNetworkController.getMyName())) {
					continue;
				}
				IAction action = (IAction) msg.getPayload();
				action.apply(myAuthoringController);
			}
		} catch (SessionExpiredException e) {
			DialogFactory.showSessionExpired();
		} catch (ShareEditException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private boolean hasClient() {
		return myNetworkController.getClient() != null;
	}

}
