package authoring.view;

import authoring.AuthoringController;
import javafx.scene.Parent;

public interface IView {
	
	Parent getUI();

    AuthoringController getController();

    void updateLayout();

    void addSubView(IView view);

    void addSubViews(IView... views);

    void removeSubView(IView view);

    IView getParentView();

    void setParentView(IView parent);

    double getPositionX();

    void setPositionX(double x);

    double getPositionY();

    void setPositionY(double y);

    double getWidth();

    void setWidth(double width);

    double getHeight();

    void setHeight(double height);

    void setSize(double width, double height);

}
