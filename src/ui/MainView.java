package ui;

public class MainView extends View {
	
	private NavigatorView navigator;
	private InspectorView inspector;
	private ComponentsView components;

	public MainView() {
		navigator = new NavigatorView();
		inspector = new InspectorView();
		components = new ComponentsView();
		addSubViews(navigator, inspector, components);
	}

	@Override
	protected void layoutSelf() {
		navigator.setPositionAndSize(0, 0, 100, this.getHeight());
		inspector.setPositionAndSize(this.getWidth() - 100, 0, 100, 
				this.getHeight());
		components.setPositionAndSize(100, this.getHeight() - 100, 
				this.getWidth() - 200, 100);
	}
	
}
