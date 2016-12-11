package authoring.controller;

import authoring.view.canvas.CanvasView;
import authoring.view.canvas.SpriteView;
import game_object.core.ISprite;

public class ComponentController {

    private ISprite myCurrentlyCopiedSprite;

    public void setCurrentlyCopiedSprite(ISprite sprite) {
        myCurrentlyCopiedSprite = sprite;
    }
    
    public ISprite getCurrentlyCopiedSprite() {
    	return myCurrentlyCopiedSprite;
    }

    public SpriteView makeSpriteViewFromSprite(
    		CanvasView canvas, ISprite sprite, long id) {
        SpriteView spriteView = new SpriteView(canvas.getController(), id);
        spriteView.setSprite(sprite);
        return spriteView;
    }
}
