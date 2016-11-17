package authoring.view;

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
			content.getChildren().add(c.getUI());
		}
	}

}
