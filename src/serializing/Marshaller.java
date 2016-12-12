package serializing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.swing.JOptionPane;

import org.xml.sax.SAXParseException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.XStreamException;
import com.thoughtworks.xstream.io.StreamException;
import com.thoughtworks.xstream.io.xml.DomDriver;

import game_object.core.Game;
import jdk.internal.org.xml.sax.SAXException;

public class Marshaller {
	
	private static XStream mySerializer = new XStream(new DomDriver());;
	private static final int FILE_OFFSET = 5;
	//"file:" before the real file path
	
	public static void saveGame(Game game, String destPath) throws IOException {
		try (Writer writer = new BufferedWriter(new FileWriter(destPath.substring(FILE_OFFSET)))) {
			String output = mySerializer.toXML(game);
			writer.write(output);
		}
	}
	
	public static Game loadGame(String srcPath) throws IOException {
		try(BufferedReader br = new BufferedReader(new FileReader(srcPath.substring(FILE_OFFSET)))) {
			return (Game)mySerializer.fromXML(br);
		}
	}
	
	public static Game loadGameFromFile(File f){
		return (Game)mySerializer.fromXML(f);
	}
	
}
