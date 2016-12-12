package game_engine.random;

import java.util.List;
import game_object.core.Dimension;
import game_object.core.ISprite;
import game_object.core.Position;

public class SpriteInfo {

    private Class<? extends ISprite> myClass;
    private List<String> myImagePaths;
    private Dimension myDimension;
    private Position myRelativePosition;
    
    public SpriteInfo(Class<? extends ISprite> clas, List<String> imagePaths, Dimension dimension, Position position){
        myClass = clas;
        myImagePaths = imagePaths;
        myDimension = dimension;
        myRelativePosition = position;
    }
    
    public Class<? extends ISprite> getSpriteClass() {
        return myClass;
    }
    
    public List<String> getImagePaths() {
        return myImagePaths;
    }
    
    public Dimension getDimension() {
        return myDimension;
    }
    
    public Position getRelativePosition() {
        return myRelativePosition;
    }
}
