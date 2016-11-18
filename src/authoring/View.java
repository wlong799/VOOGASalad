package authoring;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;

public abstract class View {
	
	// position x and y are relative to parentView
	// i.e if x = 0, y = 0, view is at top left corner of parentView
	private double myWidth;
	private double myHeight;
	private List<View> subViews;
	private View parentView;
	private Group root;
	private AuthoringController myController;
	
	public View(AuthoringController controller) {
		subViews = new ArrayList<>();
		root = new Group();
		myController = controller;
		initUI();
	}
	
	public void setPositionAndSize(double x, double y, double width, double height) {
		setPositionX(x);
		setPositionY(y);
		myWidth = width;
		myHeight = height;
	}
	
	public Parent getUI() {
		return root;
	}
	
	public void layout() {
		layoutSelf();
		layoutSubViews();
	}
	
	public void addSubViews(View...views) {
		for (View child : views) {
			addSubView(child);
		}
	}
	
	public void addSubView(View child) {
		if (child.getParentView() != null) {
			throw new IllegalArgumentException("Child already has a parent view");
		}
		subViews.add(child);
		child.setParentView(this);
		addUI(child.getUI());
	}
	
	public View getParentView() {
		return parentView;
	}
	
	public void setParentView(View parent) {
		this.parentView = parent;
	}
	
	public double getPositionX() {
		return root.getLayoutX();
	}
	
	public void setPositionX(double x) {
		root.setLayoutX(x);
	}
	
	public double getPositionY() {
		return root.getLayoutY();
	}
	
	public void setPositionY(double y) {
		root.setLayoutY(y);
	}
	
	public double getWidth() {
		return myWidth;
	}
	
	public void setWidth(double width) {
		myWidth = width;
		layout();
	}
	
	public double getHeight() {
		return myHeight;
	}
	
	public void setHeight(double height) {
		myHeight = height;
		layout();
	}
	
	protected AuthoringController getController() {
		return myController;
	}
	
	protected void addUI(Node e) {
		root.getChildren().add(e);
	}
	
	protected void addUIAll(Node...nodes) {
		for (Node e : nodes) {
			addUI(e);
		}
	}
	
	protected void clearUI() {
		root.getChildren().clear();
	}
	
	protected abstract void initUI();
	
	protected abstract void layoutSelf();
	
	private void layoutSubViews() {
		for (View child : subViews) {
			child.layout();
		}
	}

}
