package ui;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.Node;

public abstract class View {
	
	private double x;
	private double y;
	// position x and y are relative to parentView
	// i.e if x = 0, y = 0, view is at top left corner of parentView
	private double width;
	private double height;
	private List<View> subViews;
	private View parentView;
	private Group root;
	
	public View(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		subViews = new ArrayList<>();
	}
	
	public Node getUI() {
		return root;
	}
	
	public void layout() {
		layoutSelf();
		layoutSubViews();
	}
	
	public void addSubView(View child) {
		if (child.getParentView() != null) {
			throw new IllegalArgumentException("Child already has a parent view");
		}
		subViews.add(child);
		child.setParentView(this);
	}
	
	public View getParentView() {
		return parentView;
	}
	
	public void setParentView(View parent) {
		this.parentView = parent;
	}
	
	public double getPositionX() {
		return x;
	}
	
	public void setPositionX(double x) {
		this.x = x;
	}
	
	public double getPositionY() {
		return y;
	}
	
	public void setPositionY(double y) {
		this.y = y;
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
