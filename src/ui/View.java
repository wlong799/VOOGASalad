package ui;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;

public abstract class View {
	
	// position x and y are relative to parentView
	// i.e if x = 0, y = 0, view is at top left corner of parentView
	private double width;
	private double height;
	private List<View> subViews;
	private View parentView;
	private Group root;
	
	public View() {
		subViews = new ArrayList<>();
		root = new Group();
	}
	
	public void setPositionAndSize(double x, double y, double width, double height) {
		setPositionX(x);
		setPositionY(y);
		this.width = width;
		this.height = height;
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
		return width;
	}
	
	public void setWidth(double width) {
		this.width = width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public void setHeight(double height) {
		this.height = height;
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
	
	protected abstract void layoutSelf();
	
	private void layoutSubViews() {
		for (View child : subViews) {
			child.layout();
		}
	}

}
