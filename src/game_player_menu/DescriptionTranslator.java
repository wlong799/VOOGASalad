package game_player_menu;




import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;

public class DescriptionTranslator extends NodeTranslator{

	@Override
	public Node createNode(String text, boolean isSelectable, ISelectable listener) {
		Text description = new Text(text);
		description.getStyleClass().add(myResources.getString("DescriptionTextStyle"));
		description.setWrappingWidth(Double.parseDouble(myResources.getString("DescriptionWrappingWidth")));
		ScrollPane sp = new ScrollPane();
		sp.getStyleClass().add(myResources.getString("ScrollPaneStyle"));
		sp.setContent(description);
		Double minHeight = Double.parseDouble(myResources.getString("ScrollPaneMinHeight"));
		if(description.getBoundsInLocal().getHeight() < minHeight){
			sp.setMinHeight(description.getBoundsInLocal().getHeight());
		} else{
			sp.setMinHeight(minHeight);
		}
		return sp;
	}
	
}
