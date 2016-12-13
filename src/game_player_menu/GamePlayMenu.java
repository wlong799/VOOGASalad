package game_player_menu;

import java.io.File;
import java.io.IOException;
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
import serializing.Marshaller;

/**
 * @author samuelcurtis
 *This class is responsible for displaying the initial GUI Menu and switching between scenes 
 *from this menu to various games that may be played.
 */
public class GamePlayMenu implements IMenuInputListener {

	public static final String RESOURCE_FOLDER = "game_player_resources/GamePlayMenu";
	private static final String XML_SUFFIX = ".xml";
	private ResourceBundle myResources = ResourceBundle.getBundle(RESOURCE_FOLDER);

	private List<ItemDescription> myInitialMenuItems;
	private ObservableList<ItemDescription> myMenuItems;
	private MenuSceneGenerator myMenuSceneGenerator;
	private List<Game> myGames;
	private ISceneManager myManager;
	private HashMap<ItemDescription, Game> myGameMap;
	private Stage myStage;

	public GamePlayMenu(Stage s, ISceneManager gamePlayManager){
		myManager = gamePlayManager;
		myGames = new ArrayList<Game>();
		myGameMap = new HashMap<ItemDescription,Game>();
		loadGames();
		myInitialMenuItems = new ArrayList<ItemDescription>();
		generateInitialDescriptions();
		makeItemsObservable(myInitialMenuItems);
		myMenuSceneGenerator = new HBoxMenu(this,s);
		myStage = s;
		showMenu(myStage, myMenuSceneGenerator.getMenuScene(myInitialMenuItems));
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

	private void showMenu(Stage s, Scene menuScene) {
		s.setScene(menuScene);
		s.show();	
	}

	private ItemDescription generateDescription(Game game){
		String name = game.getId();
		String description = game.getDescription();
		String imagePath = game.getImagePath();
		return new ItemDescription(name, description, imagePath);
	}


	private void loadGames() {
		File folder = new File(myResources.getString("DefaultGameDirectory"));
		String[] listOfFilePaths = folder.list((dir, name) -> {
			return name.endsWith(XML_SUFFIX);
		});
		for(String path : listOfFilePaths){
			try {
				Game game = Marshaller.loadGame(folder.toURI().toString() + path);
				myGames.add(game);
			} catch (IOException e) {
				System.out.println("fail loading game: " + path);
			}
		}
	}

	@Override
	public void itemChosen(ItemDescription item) {
		Game toPlay = myGameMap.get(item);
		myManager.playGame(toPlay);
	}

	@Override
	public void playGame(File f) {
		Game game = Marshaller.loadGameFromFile(f);
		myManager.playGame(game);
	}

	@Override
	public void loadGame(File f) {
		Game game = Marshaller.loadGameFromFile(f);
		ItemDescription gameDescription = generateDescription(game);
		myMenuItems.add(gameDescription);
		myGames.add(game);
		myGameMap.put(gameDescription, game);
	}

}
