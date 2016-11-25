package game_player_menu;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GamePlayMenu {
	private List<ItemDescription> myMenuItems;
	private MenuSceneGenerator myMenuSceneGenerator;
	
	public GamePlayMenu(Stage s){
		getItemDescriptions();
		myMenuSceneGenerator = new HBoxMenu();
		showMenu(s, myMenuSceneGenerator.getMenuScene(myMenuItems));
	}

	private void showMenu(Stage s, Scene menuScene) {
		s.setScene(menuScene);
		s.show();	
	}

	private void getItemDescriptions() {
		myMenuItems = new ArrayList<ItemDescription>();
		myMenuItems.add(new ItemDescription("TestName", "TestDescription", "images/blue_snail.png"));
	}
	
}
