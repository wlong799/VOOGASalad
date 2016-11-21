package authoring.controller;

import java.util.ArrayList;
import java.util.List;

import authoring.view.canvas.CanvasView;
import authoring.view.canvas.SpriteView;
import authoring.view.components.Component;
import game_object.GameObjectType;
import game_object.block.StaticBlock;
import game_object.character.Enemy;
import game_object.character.Hero;
import game_object.core.Dimension;
import game_object.core.ISprite;
import game_object.core.Position;

public class ComponentController {
	
	public String component2ID(Component component) {
		return component.getType().toString() + "," + component.getImagePath();
	}
	
	public SpriteView makeSpriteViewWithID(String id, CanvasView canvas) {
		String[] arr = id.split(",");
		GameObjectType type = GameObjectType.valueOf(arr[0]);
		List<String> path = new ArrayList<String>();
		path.add(arr[1]);
		ISprite sprite = null;
		switch(type) {
		case Enemy:
			sprite = new Enemy(new Position(0, 0), new Dimension(0, 0), path);
		case Hero:
			sprite = new Hero(new Position(0, 0), new Dimension(0, 0), path);
		case StaticBlock:
			sprite = new StaticBlock(new Position(0, 0), new Dimension(0, 0), path);
		}
		SpriteView spView = new SpriteView(canvas.getController());
		spView.setSprite(sprite);
		return spView;
	}

}
