package game_player;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import game_object.core.Game;
import game_object.statistics.GameStatistics;
import game_object.statistics.StatisticsField;
import javafx.scene.Node;
import resources.ResourceBundles;

public class HUDController {
	private ResourceBundle myResources = ResourceBundles.languageProperties;
	private GameStatistics myGameStatistics;
	private Map<String, String> myStatisticsMap;
	private HUDView myView;
	
	public HUDController(Game game) {
		myGameStatistics = game.getGameStats();
		myStatisticsMap = new TreeMap<String,String>();
		createStatisticsMap();
		myView = new HUDView(myStatisticsMap);
	}

	public void updateStatisticsMap() {
		createStatisticsMap();
		myView.updateView();
	}
	
	private void createStatisticsMap(){
		myStatisticsMap.put(myResources.getString("Health"), myGameStatistics.getHealthOfHero(0));
		myStatisticsMap.put(myResources.getString("FPS"), myGameStatistics.getFPS());
		myStatisticsMap.put(myResources.getString("XPosition"), myGameStatistics.getXPosOfHero(0));
		myStatisticsMap.put(myResources.getString("YPosition"), myGameStatistics.getYPosOfHero(0));
		myStatisticsMap.put(myResources.getString("Score"), myGameStatistics.getScoreOfHero(0));
	}
	
	public Node getView(){
		return myView.getView();
	}
	
	
	

}
