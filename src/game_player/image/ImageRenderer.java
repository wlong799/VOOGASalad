package game_player.image;

import game_object.core.ImageStyle;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class ImageRenderer {
	
	private Image myImage;
	private ImageStyle myStyle;
	private double myWidth;
	private double myHeight;
	
	public ImageView render(Image image, ImageStyle style, double width, double height) {
		myImage = image;
		myStyle = style;
		myWidth = width;
		myHeight = height;
		if (myStyle == ImageStyle.TILE) {
            return tileImages();
        } else {
            return fitImage();
        }
	}
	
	private ImageView fitImage() {
        ImageView imageView = new ImageView(myImage);
        imageView.setFitHeight(myHeight);
        imageView.setFitWidth(myWidth);
        return imageView;
    }

    private ImageView tileImages() {
    	Rectangle myContent = new Rectangle(myWidth, myHeight);
        ImagePattern pattern = new ImagePattern(myImage, 0, 0, myImage.getWidth(), myImage.getHeight(), false);
        myContent.setFill(pattern);
        WritableImage snapshot = myContent.snapshot(new SnapshotParameters(), null);
        return new ImageView(snapshot);
    }

}
