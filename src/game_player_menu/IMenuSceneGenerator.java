package game_player_menu;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;

public interface IMenuSceneGenerator {
	
	Scene getMenuScene(List<ItemDescription> menuItems);
	
}
