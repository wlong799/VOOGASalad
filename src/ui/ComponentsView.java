package ui;

import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
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
		for (int i = 0; i < 20; i++) {
			ComponentView c = new ComponentView(this.getController());
			c.setImage(new Image("turtle.gif"));
			c.setTitleText("test");
			content.getChildren().add(c.getUI());
		}
	}

}
