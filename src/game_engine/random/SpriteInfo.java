package game_engine.random;

import java.util.List;
import game_object.core.Dimension;
import game_object.core.ISprite;

public class SpriteInfo {

    private Class<? extends ISprite> myClass;
    private List<String> myImagePaths;
    private Dimension myDimension;
    
    public SpriteInfo(Class<? extends ISprite> clas, List<String> imagePaths, Dimension dimension){
        myClass = clas;
        myImagePaths = imagePaths;
        myDimension = dimension;
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
}
