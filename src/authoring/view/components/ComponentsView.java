package authoring.view.components;

import java.util.ArrayList;
import java.util.List;

import authoring.AuthoringController;
import authoring.View;
import game_object.constants.GameObjectConstants;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;

public class ComponentsView extends View {
	

	public ComponentsView(AuthoringController controller) {
		super(controller);
	}

	@Override
	protected void layoutSelf() {
		TabPane tabPane = new TabPane();
		tabPane.setPrefHeight(this.getHeight());
		tabPane.setPrefWidth(this.getWidth());
		this.addUI(tabPane);
		
		initEnemyTab(tabPane);
		initBlockTab(tabPane);
	}

	@Override
	protected void initUI() {
		
	}
	
	protected void initEnemyTab(TabPane tabPane) {
		Tab enemyTab = initTab("Enemy");
		tabPane.getTabs().add(enemyTab);
		HBox hbox = new HBox();
		hbox.setSpacing(20);
		hbox.setAlignment(Pos.CENTER);
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(hbox);
		scrollPane.setFitToHeight(true);
		
		List<String> list = new ArrayList<String>();
		list.add(GameObjectConstants.BLUE_SNAIL_FILE);
		list.add(GameObjectConstants.ELIZA_FILE);
		list.add(GameObjectConstants.ORANGE_MUSHROOM_FILE);
		list.add(GameObjectConstants.RIBBON_PIG_FILE);
		list.add(GameObjectConstants.SLIME_FILE);
		for (int i = 0; i < 20; i++) {
			ComponentView c = new ComponentView(this.getController());
			c.setWidth(50);
			c.setImagePath(list.get(i % list.size()));
			c.setTitleText("test");
			hbox.getChildren().add(c.getUI());
		}
		enemyTab.setContent(initScrollPane());
	}
	
	protected void initBlockTab(TabPane tabPane) {
		Tab blockTab = initTab("Block");
		tabPane.getTabs().add(blockTab);
		HBox hbox = new HBox();
		hbox.setSpacing(20);
		hbox.setAlignment(Pos.CENTER);
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(hbox);
		scrollPane.setFitToHeight(true);
		
		List<String> list = new ArrayList<String>();
		list.add(GameObjectConstants.BRICK);
		list.add(GameObjectConstants.BUSH);
		for (int i = 0; i < 20; i++) {
			ComponentView c = new ComponentView(this.getController());
			c.setWidth(50);
			c.setImagePath(list.get(i % list.size()));
			c.setTitleText("test");
			hbox.getChildren().add(c.getUI());
		}
		blockTab.setContent(initScrollPane());
		
	}
	
	protected Tab initTab(String tabName) {
		Tab tab = new Tab();
		tab.setText(tabName);
		return tab;
	}
	
	protected ScrollPane initScrollPane(){
		HBox hbox = new HBox();
		hbox.setSpacing(20);
		hbox.setAlignment(Pos.CENTER);
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(hbox);
		scrollPane.setFitToHeight(true);
		
		return scrollPane;
	}
	
	

}
