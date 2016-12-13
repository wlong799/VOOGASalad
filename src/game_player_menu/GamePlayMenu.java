package game_player_menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import game_object.core.Game;
import game_player.ISceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import resources.ResourceBundles;
import serializing.Marshaller;
import utils.FileLoader;
import utils.FileLoader.StartDirectory;

/**
 * @author samuelcurtis
 *This class is responsible for displaying the initial GUI Menu and switching between scenes 
 *from this menu to various games that may be played.
 */
public class GamePlayMenu implements IMenuInputListener {
	protected ResourceBundle myResources = ResourceBundles.languageProperties;
	private List<ItemDescription> myInitialMenuItems;
	private ObservableList<ItemDescription> myMenuItems;
	private MenuSceneGenerator myMenuSceneGenerator;
	private List<Game> myGames;
	private ISceneManager myManager;
	private HashMap<ItemDescription, Game> myGameMap;
	private Stage myStage;
	private Scene myMenuScene;

	public GamePlayMenu(Stage s, ISceneManager gamePlayManager){
		myManager = gamePlayManager;
		myGames = new ArrayList<Game>();
		myGameMap = new HashMap<ItemDescription,Game>();
		loadDefaultGames();
		myInitialMenuItems = new ArrayList<ItemDescription>();
		generateInitialDescriptions();
		makeItemsObservable(myInitialMenuItems);
		myMenuSceneGenerator = new HBoxMenu(this,s);
		myStage = s;
		myMenuScene = myMenuSceneGenerator.getMenuScene(myMenuItems);
	}

	private void makeItemsObservable(List<ItemDescription> items) {
		myMenuItems = FXCollections.observableList(items);
		myMenuItems.addListener(new ListChangeListener<ItemDescription>() {
			@Override
			public void onChanged(ListChangeListener.Change change) {
				myMenuSceneGenerator.addItem(change);
			}
		});

	}

	private void generateInitialDescriptions() {
		for(Game game : myGames){
			ItemDescription gameDescription = generateDescription(game);
			myInitialMenuItems.add(gameDescription);
			myGameMap.put(gameDescription, game);
		}
	}
	
	private ItemDescription generateDescription(Game game){
		String name = game.getId();
		String description = game.getDescription();
		String imagePath = game.getImagePath();
		return new ItemDescription(name, description, imagePath);
	}

	private void loadDefaultGames() {
		FileLoader loader = new FileLoader(StartDirectory.DEFAULT_DIRECTORY,utils.FileType.DATA);
		try {
			List<File> allGameFiles = loader.loadMultipleFromDefaultDirectory();
			for(File f : allGameFiles){
				Game game = serializeGame(f);
				myGames.add(game);
			}
		} catch (FileNotFoundException e) {
			//
		}
	}

	private Game serializeGame(File f){
		return Marshaller.loadGameFromFile(f);
	}

	@Override
	public void itemChosen(ItemDescription item) {
		Game toPlay = myGameMap.get(item);
		myManager.playGame(toPlay);
	}

	@Override
	public void loadGame(File f) {
		Game game = serializeGame(f);
		if(game != null){
			ItemDescription gameDescription = generateDescription(game);
			myMenuItems.add(gameDescription);
			myGames.add(game);
			myGameMap.put(gameDescription, game);
		}
	}

	@Override
	public void exit() {
		myStage.close();
	}

	public Scene getMenuScene() {
		return myMenuScene;
	}

}
