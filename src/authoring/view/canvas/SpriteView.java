package authoring.view.canvas;

import authoring.AuthoringController;
import authoring.view.AbstractView;
import game_object.core.ISprite;
import game_object.core.Position;

/**
 * wrapper for Sprite in AuthEnv
 */
public class SpriteView extends AbstractView {
	
	private long myID;
    private ISprite mySprite;
    private CanvasView myCanvas;
    private SpriteImageView mySpriteImageView;
    private SpriteResizeView mySpriteResizeView;
    private SpriteNameView mySpriteNameView;
    private Position myMouseOffset;
    private static final double Y_OFFSET = -20;

    public SpriteView(AuthoringController controller, long id) {
        super(controller);
        myID = id;
    }
    
    public long getID() {
    	return myID;
    }

    public void setSprite(ISprite sprite) {
        mySprite = sprite;
        initUI();
    }

    public ISprite getSprite() {
        return mySprite;
    }

    public void setCanvasView(CanvasView canvas) {
        myCanvas = canvas;
    }

    public CanvasView getCanvasView() {
        return myCanvas;
    }

    public void setAbsolutePositionX(double x) {
        setPositionX(x);
        mySprite.getPosition().setX(x);
    }

    public void setAbsolutePositionY(double y) {
        setPositionY(y);
        mySprite.getPosition().setY(y);
    }

    public void setAbsolutePositionZ(double z) {
        mySprite.getPosition().setZ(z);
        getController().getCanvasController().reorderSpriteViewsWithPositionZ();
    }

    public Position getMouseOffset() {
        return myMouseOffset;
    }

    /**
     * @param width set width both frontend and backend mySprite
     * @param share if the action is broadcasted
     */
    public void setDimensionWidth(double width, boolean share) {
        setWidth(width);
        mySprite.getDimension().setWidth(width);
        updateLayout();
        if (share) {
        	this.getController().getNetworkController().getShareEditor()
        		.resize(this, width, mySprite.getDimension().getHeight());
        }
    }

    /**
     * @param height set height both frontend and backend mySprite
     * @param share if the action is broadcasted
     */
    public void setDimensionHeight(double height, boolean share) {
        setHeight(height);
        mySprite.getDimension().setHeight(height);
        updateLayout();
        if (share) {
        	this.getController().getNetworkController().getShareEditor()
        		.resize(this, mySprite.getDimension().getWidth(), height);
        }
    }

    public void indicateSelection() {
        this.indicateDeselection();
        mySpriteResizeView = new SpriteResizeView(this.getController());
        addUI(mySpriteResizeView.getUI());
        addSubView(mySpriteResizeView);
        updateLayout();
    }

    public void indicateDeselection() {
        this.removeSubView(mySpriteResizeView);
        if (mySpriteResizeView != null) {
            removeUI(mySpriteResizeView.getUI());
        }
    }
    
    public void setEditor(String name) {
    	mySpriteNameView.setName(name);
    }

    @Override
    protected void initUI() {
        if (mySprite == null) return;
        mySpriteImageView = new SpriteImageView(this.getController());
        mySpriteNameView = new SpriteNameView(this.getController());
        addUIAll(mySpriteImageView.getUI(), mySpriteNameView.getUI());
        addSubViews(mySpriteImageView, mySpriteNameView);
        this.setHeight(mySpriteImageView.getHeight());
        this.setWidth(mySpriteImageView.getWidth());

        setMouseClicked();
        mySpriteImageView.setDragMove();
        myMouseOffset = new Position(0, 0);
    }

    @Override
    protected void updateLayoutSelf() {
        mySpriteImageView.setWidth(getWidth());
        mySpriteImageView.setHeight(getHeight());
        mySpriteNameView.setPositionX(0);
        mySpriteNameView.setPositionY(Y_OFFSET);
        if (mySpriteResizeView != null) {
            mySpriteResizeView.setWidth(getWidth());
            mySpriteResizeView.setHeight(getHeight());
        }
    }

    private void setMouseClicked() {
        getUI().setOnMouseClicked(e -> {
            getController().selectSpriteView(this);
            if (this.getController().getCanvasController().getSnapToGrid()) {
            	snapToGrid();
            }
        });
    }

    public void snapToGrid() {
        setAbsolutePositionX(getController().getCanvasController().convertToNearestBlockValue(getPositionX()));
        setAbsolutePositionY(getController().getCanvasController().convertToNearestBlockValue(getPositionY()));
        setDimensionWidth(getController().getCanvasController().convertToNearestBlockValue(getWidth()), true);
        setDimensionHeight(getController().getCanvasController().convertToNearestBlockValue(getHeight()), true);
    }
}
