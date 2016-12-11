package authoring.share;

import authoring.AuthoringController;
import authoring.controller.chat.ChatController;
import network.client.NetworkClient;
import network.exceptions.ServerDownException;

public class NetworkController {
	
	private AuthoringController myAuthoringController;
	private String myName;
	private NetworkClient myClient;
	private IDManager myIDManager;
	private ShareEditController shareEditor;
	private ChatController chatController;
	
	public NetworkController(AuthoringController authoringController) {
		myAuthoringController = authoringController;
		myIDManager = new IDManager(0);
		chatController = new ChatController(this);
		shareEditor = new ShareEditController(this, myAuthoringController);
	}
	
	public void initClientWithName(String name) throws ServerDownException {
		myName = name;
		myClient = new NetworkClient(name);
		myIDManager = new IDManager(myClient.getNextSequenceNumber());
	}
	
	public String getMyName() {
		return myName;
	}
	
	public NetworkClient getClient() {
		return myClient;
	}
	
	public IDManager getIDManager() {
		return myIDManager;
	}
	
	public ShareEditController getShareEditor() {
		return shareEditor;
	}
	
	public ChatController getChatController() {
		return chatController;
	}

}
