package game_object.level;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import game_engine.collision.Boundary;
import game_object.core.Dimension;
import game_object.core.ISprite;
import game_object.core.Position;

public class SpriteScavenger {
	
	private static final double BORDER_OFFSET = 500;
	private Boundary myBorderDimension;
	
	public void setBorderDimension(Boundary border) {
		myBorderDimension = border;
	}
	
	public void scavengeList(List<? extends ISprite> list) {
		Set<ISprite> removeSet = new HashSet<>();
		for (ISprite sprite : list) {
			if (sprite != null) {
				if (!sprite.isValid() || isOutOfBoundary(sprite)) {
					removeSet.add(sprite);
				}
			}
		}
		list.removeAll(removeSet);
	}
	
	private boolean isOutOfBoundary(ISprite sprite) {
		return !myBorderDimension.overlaps(new Boundary(sprite.getPosition(),sprite.getDimension()));
	}
	
}
