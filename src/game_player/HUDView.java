package game_player;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import game_object.core.Game;
import game_object.statistics.GameStatistics;
import game_object.statistics.StatisticsField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import resources.ResourceBundles;

public class HUDView {
	private ResourceBundle myResources = ResourceBundles.languageProperties;
	private VBox myStatsView;
	private Map<String, String> myStatsMap;
	
	public HUDView(Map<String, String> statsMap){
		myStatsMap = statsMap;
		myStatsView = new VBox();
		myStatsView.getStyleClass().add(myResources.getString("HUDVBoxStyle"));
		updateView();
	}
	
	public void updateView(){
		myStatsView.getChildren().clear();
		for(Map.Entry<String, String> entry : myStatsMap.entrySet()){
			HBox hBox = new HBox();
			hBox.getStyleClass().add(myResources.getString("HUDHBoxStyle"));
			String label = entry.getKey();
			String value = entry.getValue();
			Label field = new Label(label);
			field.getStyleClass().add(myResources.getString("HUDFieldLabelStyle"));
			Text fieldValue = new Text(value);
			fieldValue.getStyleClass().add(myResources.getString("HUDFieldValueStyle"));
			hBox.getChildren().addAll(field,fieldValue);
			myStatsView.getChildren().add(hBox);
		}
		myStatsView.setMinWidth(myStatsView.getWidth());
	}
	
	public Node getView(){
		return myStatsView;
	}


	
	
	
	
	
	
	
	
	
	
	
	
}
