package authoring.controller;

import authoring.view.canvas.CanvasView;
import authoring.view.canvas.SpriteView;
import game_object.core.ISprite;

public class ComponentController {

    private ISprite myCurrentlyCopiedSprite;

    public void setCurrentlyCopiedSprite(ISprite sprite) {
        myCurrentlyCopiedSprite = sprite;
    }

    public SpriteView makeSpriteViewFromCopiedSprite(CanvasView canvas) {
        SpriteView spriteView = new SpriteView(
        		canvas.getController(), 
        		canvas.getController().getNetworkController().getIDManager().getNextID());
        spriteView.setSprite(myCurrentlyCopiedSprite);
        return spriteView;
    }
}
