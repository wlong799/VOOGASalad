package authoring.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import authoring.AuthoringController;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;

/**
 * Abstract superclass for all other viewable elements in authoring environment GUI.
 */
public abstract class AbstractView {
    private static final String ILLEGAL_SUBVIEW_MESSAGE = "Child already has a parent view";

    private double myWidth, myHeight;
    private Group myUIRoot;
    private AuthoringController myController;
    private List<AbstractView> mySubViews;
    private AbstractView myParentView;

    public AbstractView(AuthoringController controller) {
        myUIRoot = new Group();
        mySubViews = new ArrayList<>();
        myController = controller;
        initUI();
    }

    public Parent getUI() {
        return myUIRoot;
    }

    public AuthoringController getController() {
        return myController;
    }

    public void updateLayout() {
        updateLayoutSelf();
        mySubViews.forEach(AbstractView::updateLayout);
    }

    protected List<AbstractView> getSubViews() {
        return mySubViews;
    }

    public void addSubView(AbstractView view) {
        if (view.getParentView() != null) {
            throw new IllegalArgumentException(ILLEGAL_SUBVIEW_MESSAGE);
        }
        mySubViews.add(view);
        view.setParentView(this);
    }

    public void addSubViews(AbstractView... views) {
        Arrays.stream(views).forEach(this::addSubView);
    }

    public void removeSubView(AbstractView view) {
        if (view == null) {
            return;
        }
        mySubViews.remove(view);
        view.setParentView(null);
    }

    public AbstractView getParentView() {
        return myParentView;
    }

    public void setParentView(AbstractView parent) {
        myParentView = parent;
    }

    protected void addUI(Node node) {
        myUIRoot.getChildren().add(node);
    }

    protected void addUIAll(Node... nodes) {
        Arrays.stream(nodes).forEach(this::addUI);
    }

    protected void removeUI(Node node) {
        myUIRoot.getChildren().remove(node);
    }

    protected void clearUI() {
        myUIRoot.getChildren().clear();
    }

    public double getPositionX() {
        return myUIRoot.getLayoutX();
    }

    public void setPositionX(double x) {
        myUIRoot.setLayoutX(x);
    }

    public double getPositionY() {
        return myUIRoot.getLayoutY();
    }

    public void setPositionY(double y) {
        myUIRoot.setLayoutY(y);
    }

    public double getWidth() {
        return myWidth;
    }

    public void setWidth(double width) {
        myWidth = width;
    }

    public double getHeight() {
        return myHeight;
    }

    public void setHeight(double height) {
        myHeight = height;
    }

    public void setSize(double width, double height) {
        myWidth = width;
        myHeight = height;
    }

    protected abstract void initUI();

    protected abstract void updateLayoutSelf();
}
