package network.messages;

public abstract class AbstractMessage implements Message{
	
	private static final long serialVersionUID = -1904304542591909587L;
	
	private String sender;
	
	public AbstractMessage() {}
	
	public AbstractMessage(String sender) {
		this.sender = sender;
	}
	
	@Override
	public String getSender() {
		return sender;
	}
	
	@Override
	public void setSender(String sender) {
		this.sender = sender;
	}

}
