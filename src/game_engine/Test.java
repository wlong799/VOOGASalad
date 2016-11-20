package game_engine;

import game_object.LevelGenerator;
import game_object.level.Level;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Level level = LevelGenerator.getTestLevelB();
		GameEngine ge = new GameEngine(level);
		ge.init();
		for (int i = 0; i < 60; i++) {
			ge.update(1.0 / 60);
			ge.printOutput();
		}
	}

}
