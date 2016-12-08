package authoring.view.components;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import game_object.GameObjectType;
import game_object.block.Block;
import game_object.character.Enemy;
import game_object.character.Hero;
import game_object.core.Dimension;
import game_object.core.ExceptionThrower;
import game_object.core.ISprite;
import game_object.core.Position;
import game_object.powerup.NewWeaponPowerUp;

/**
 * Component class provides a way for users to have editable game component templates to add to the environment. Each
 * component has a title and a description, and holds a single template sprite, which can be edited. Editing the template
 * edits all currently linked sprites on screen created using the template.
 */
// TODO: 11/21/16 Make a way to edit the template sprite
// TODO: 11/21/16 Make a way to link all previously created sprites and update them as well 
public class Component {
    private static final String COPY_ERROR = "Error when copying from template sprite.";

    private ISprite myTemplateSprite;
    private String myTitle;
    private String myDescription;

    public Component(GameObjectType gameObjectType, String imagePathLeft, String imagePathRight, String title, String description) {
        List<String> imagePaths = new ArrayList<String>(Arrays.asList(imagePathLeft, imagePathRight));
        myTemplateSprite = createTemplateSpriteFromType(gameObjectType, imagePaths);
        myTitle = title;
        myDescription = description;
    }

    public Component(GameObjectType gameObjectType, String imagePath, String title, String description) {
        List<String> imagePaths = new ArrayList<String>(Arrays.asList(imagePath));
        myTemplateSprite = createTemplateSpriteFromType(gameObjectType, imagePaths);
        myTitle = title;
        myDescription = description;
    }

    public ISprite copySpriteFromTemplate() {
        ISprite sprite = null;
        try {
            Constructor<? extends ISprite> spriteConstructor = myTemplateSprite.getClass().getConstructor(
                    Position.class, Dimension.class, List.class);
            Position oldPos = myTemplateSprite.getPosition();
            Position newPos = new Position(oldPos.getX(), oldPos.getY(), oldPos.getZ());
            Dimension oldDim = myTemplateSprite.getDimension();
            Dimension newDim = new Dimension(oldDim.getWidth(), oldDim.getHeight());
            List<String> newImagePaths = new ArrayList<>(myTemplateSprite.getImagePaths());
            sprite = spriteConstructor.newInstance(newPos, newDim, newImagePaths);
        } catch (Exception e) {
            System.out.println(COPY_ERROR);
            e.printStackTrace();
        }
        return sprite;
    }

    public ISprite getTemplateSprite() {
        return myTemplateSprite;
    }

    public String getTitle() {
        return myTitle;
    }

    public String getDescription() {
        return myDescription;
    }

    public String getImagePath() {
        return myTemplateSprite.getImagePath();
    }

	private ISprite createTemplateSpriteFromType(GameObjectType gameObjectType, List<String> imagePaths) {
		ISprite sprite = null;
		switch (gameObjectType) {
		case ENEMY:
			sprite = new Enemy(new Position(0, 0), new Dimension(0, 0), imagePaths);
			break;
		case HERO:
			sprite = new Hero(new Position(0, 0), new Dimension(0, 0), imagePaths);
			break;
		case STATIC_BLOCK:
			sprite = new Block(new Position(0, 0), new Dimension(0, 0), imagePaths);
			break;
		case WEAPON_POWER_UP:
			sprite = new NewWeaponPowerUp(new Position(0, 0), new Dimension(0, 0), imagePaths, null, null);
			break;
		case WEAPON_PROJECTILE:
			ExceptionThrower.illegalArgs("Projectile should not be created directly inside authoring environment");
			break;
		default:
			break;
		}
		return sprite;
	}

}
