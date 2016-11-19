package authoring.view.canvas;

import java.util.Comparator;

public class SpriteViewComparator implements Comparator<SpriteView> {

	@Override
	public int compare(SpriteView o1, SpriteView o2) {
		return o1.getSprite().getPosition().getZ() - 
				o2.getSprite().getPosition().getZ() < 0 ? -1 : 1;
	}

}
