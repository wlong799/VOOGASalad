package authoring.view.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import game_object.GameObjectType;
import game_object.block.Block;
import game_object.character.Enemy;
import game_object.character.Hero;
import game_object.core.Dimension;
import game_object.core.ISprite;
import game_object.core.Position;
import game_object.powerup.NewWeaponPowerUp;
import game_object.powerup.ReplenishHealthPowerUp;
import game_object.powerup.SpeedUpPowerUp;

/**
 * Component class provides a way for users to have editable game component templates to add to the environment. Each
 * component has a title and a description, and holds a single template sprite, which can be edited. Editing the template
 * edits all currently linked sprites on screen created using the template.
 */
public class Component extends Observable {

    private ISprite myTemplateSprite;
    private String myTitle;
    private String myDescription;

    public Component(GameObjectType gameObjectType, String imagePath, String title, String description) {
        List<String> imagePaths = new ArrayList<String>(Arrays.asList(imagePath));
        myTemplateSprite = createTemplateSpriteFromType(gameObjectType, imagePaths);
        myTitle = title;
        myDescription = description;
    }

    public ISprite copySpriteFromTemplate() {
    	XStream mySerializer = new XStream(new DomDriver());
		return (ISprite)mySerializer.fromXML(mySerializer.toXML(myTemplateSprite));
    }

    public void setTitle(String title) {
        myTitle = title;
        updateObservers();
    }

    public String getTitle() {
        return myTitle;
    }

    public void setDescription(String description) {
        myDescription = description;
        updateObservers();
    }

    public String getDescription() {
        return myDescription;
    }

    public void setImagePath(String imagePath) {
        List<String> imagePaths = new ArrayList<>();
        imagePaths.add(imagePath);
        myTemplateSprite.setImagePaths(imagePaths);
        updateObservers();
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
            case SPEED_POWER_UP:
                sprite = new SpeedUpPowerUp(new Position(0, 0), new Dimension(0, 0), imagePaths, 2);
                break;
            case HEALTH_POWER_UP:
                sprite = new ReplenishHealthPowerUp(new Position(0, 0), new Dimension(0, 0), imagePaths);
                break;
            case WEAPON_PROJECTILE:
                break;
            default:
                break;
        }
        return sprite;
    }

    private void updateObservers() {
        setChanged();
        notifyObservers();
    }
}
