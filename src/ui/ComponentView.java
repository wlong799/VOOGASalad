package ui;

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
	private static final double imageWidthRatio = 0.6;

	public ComponentView(AuthoringController controller) {
		super(controller);
	}

	public void setImage(Image img) {
		imageView.setImage(img);
	}

	public void setTitleText(String text) {
		title.setText(text);
	}

	@Override
	protected void layoutSelf() {
		box.setPrefWidth(this.getWidth());
		box.setPrefHeight(this.getHeight());
		box.setAlignment(Pos.CENTER);
		box.setSpacing(10);
		box.setPadding(new Insets(5,5,5,5));
		imageView.setFitWidth(this.getWidth() * imageWidthRatio);
	}

	@Override
	protected void initUI() {
		imageView = new ImageView();
		title = new Label("");
		box = new VBox();
		box.setAlignment(Pos.CENTER);
		box.getChildren().addAll(imageView, title);
		this.addUI(box);
		setOnDrag();
	}

	private void setOnDrag() {
		//TODO drag and drop to canvas
		imageView.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				/* drag was detected, start a drag-and-drop gesture*/
				/* allow any transfer mode */
				Dragboard db = imageView.startDragAndDrop(TransferMode.COPY);

				/* Put a string on a dragboard */
				ClipboardContent content = new ClipboardContent();
				content.putString("dragging");
				db.setContent(content);

				event.consume();
			}
		});
	}

}
