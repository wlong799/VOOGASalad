package game_player;


import java.util.ArrayList;
import java.util.List;

import game_object.core.Game;
import game_object.statistics.GameStatistics;
import game_object.statistics.StatisticsField;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

public class HUDView {
	
	private Group myRoot;
	private GridPane myStatisticsGrid;
	private GameStatistics myStatistics;
	private List<String> myValidFields;
	
	
	public HUDView(GameStatistics stats){
		myRoot = new Group();
		myStatistics = stats;
		myStatisticsGrid = new GridPane();
		myValidFields = new ArrayList<String>();
		init();
	}

	private void init() {
		for(StatisticsField field : StatisticsField.values()){
			if(myStatistics.isValid(field)){
				myValidFields.add(field.toString());
			}
		}
		createGridRows();
		populateRows();
	}

	private void populateRows() {
		String fps = myStatistics.getFPS();
		//String health = myStatistics.get
		
		
	}

	private void createGridRows() {
		int rowNumber = 0;
		for(String field : myValidFields){
			myStatisticsGrid.add(new Label(field), 0, rowNumber);
			rowNumber++;
		}
	}
	
	
	
	
}
