package authoring.view.components;

import authoring.AuthoringController;
import authoring.View;
import authoring.constants.UIConstants;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;

public class ComponentView extends View {

	private ImageView imageView;
	private Label title;
	private VBox box;
	private Component myComponent;

	public ComponentView(AuthoringController controller) {
		super(controller);
	}

	public void setComponent(Component component) {
		myComponent = component;
		updateUI();
	}

	@Override
	protected void layoutSelf() {
	}

	@Override
	protected void initUI() {
		imageView = new ImageView();
		title = new Label("");
		box = new VBox();
		box.setAlignment(Pos.CENTER);
		box.getChildren().addAll(imageView, title);
		box.setPrefWidth(this.getWidth());
		box.setPrefHeight(this.getHeight());
		box.setAlignment(Pos.CENTER);
		box.setSpacing(10);
		box.setPadding(new Insets(5,5,5,5));
		imageView.setPreserveRatio(true);
		imageView.setFitWidth(
				UIConstants.COMPONENT_WIDTH * UIConstants.COMPONENT_WIDTH_PORTION);
		this.addUI(box);
		setOnDrag();
	}
	
	private void updateUI() {
		imageView.setImage(new Image(myComponent.getImagePath()));
		title.setText(myComponent.getTitle());
	}

	private void setOnDrag() {
		imageView.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				/* drag was detected, start a drag-and-drop gesture*/
				/* allow any transfer mode */
				Dragboard db = imageView.startDragAndDrop(TransferMode.COPY);

				/* Put a string on a dragboard */
				ClipboardContent content = new ClipboardContent();
				content.putString(myComponent.getType().toString() + "," + myComponent.getImagePath());
				db.setContent(content);

				event.consume();
			}
		});
	}

}
