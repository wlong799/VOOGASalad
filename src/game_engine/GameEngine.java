package game_engine;

public class GameEngine implements IGameEngine{
	boolean runFlag;
	
	public GameEngine() {
		runFlag = true;
	}
	
	@Override
	public void loop() {
		while (runFlag) {
			endCheck();
			update();
			draw();
		}
		shutdown();
	}
	
	@Override
	public void init() {
		
	}

	@Override
	public void endCheck() {
		
	}
	
	@Override
	public void shutdown() {
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public void draw() {
		
	}

}
