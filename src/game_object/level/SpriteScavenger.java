package game_object.level;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import game_object.core.Dimension;
import game_object.core.ISprite;
import game_object.core.Position;

public class SpriteScavenger {
	
	private static final double BORDER_OFFSET = 500;
	private Dimension myBorderDimension;
	
	public void setBorderDimension(Dimension borderDimension) {
		myBorderDimension = borderDimension;
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
		Position pos = sprite.getPosition();
		return pos.getX() < 0 - BORDER_OFFSET
			|| pos.getX() > myBorderDimension.getWidth() + BORDER_OFFSET
			|| pos.getY() < 0 - BORDER_OFFSET
			|| pos.getY() > myBorderDimension.getHeight() + BORDER_OFFSET;
	}
	
}
