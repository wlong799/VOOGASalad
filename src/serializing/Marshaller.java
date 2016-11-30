package serializing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import game_object.LevelGenerator;
import game_object.core.Game;

public class Marshaller {
	
	private XStream mySerializer;
	private static final int FILE_OFFSET = 5;
	//"file:" before the real file path
	
	public Marshaller() {
		mySerializer = new XStream(new DomDriver());
	}
	
	public void saveGame(Game game, String destPath) throws IOException {
		try (Writer writer = new BufferedWriter(new FileWriter(destPath.substring(FILE_OFFSET)))) {
			String output = mySerializer.toXML(game);
			writer.write(output);
		}
	}
	
	public Game loadGame(String srcPath) throws IOException {
		try(BufferedReader br = new BufferedReader(new FileReader(srcPath.substring(FILE_OFFSET)))) {
			return (Game)mySerializer.fromXML(br);
		}
	}
	
	public static void main(String[] args) {
		String path = "file:data/game/test.xml";
		Game testGame = new Game();
		testGame.addLevel(LevelGenerator.getTestLevelA());
		Marshaller test = new Marshaller();
		try {
			test.saveGame(testGame, path);
			testGame = test.loadGame(path);
			System.out.println(testGame.getAllLevelsReadOnly().get(0).getAllSprites().size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
