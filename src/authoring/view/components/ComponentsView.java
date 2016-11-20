package authoring.view.components;

import java.util.ArrayList;
import java.util.List;

import authoring.AuthoringController;
import authoring.View;
import game_object.constants.GameObjectConstants;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

public class ComponentsView extends View {
	
	private ScrollPane scrollPane;
	private HBox content;

	public ComponentsView(AuthoringController controller) {
		super(controller);
	}

	@Override
	protected void layoutSelf() {
		scrollPane.setPrefWidth(this.getWidth());
		scrollPane.setPrefHeight(this.getHeight());
	}

	@Override
	protected void initUI() {
		scrollPane = new ScrollPane();
		this.addUI(scrollPane);
		content = new HBox();
		content.setSpacing(20);
		content.setAlignment(Pos.CENTER);
		scrollPane.setContent(content);
		scrollPane.setFitToHeight(true);
		
		//debug
		List<Component> list = new ArrayList<>();
		list.add(GameObjectConstants.BLUE_SNAIL);
		list.add(GameObjectConstants.ELIZA);
		list.add(GameObjectConstants.ORANGE_MUSHROOM);
		list.add(GameObjectConstants.RIBBON_PIG);
		list.add(GameObjectConstants.SLIME);
		list.add(GameObjectConstants.STONE_BLOCK);
		for (int i = 0; i < 20; i++) {
			ComponentView c = new ComponentView(this.getController());
			c.setWidth(80);
			c.setComponent(list.get(i % list.size()));
			content.getChildren().add(c.getUI());
		}
	}

}
