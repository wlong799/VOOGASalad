package authoring.share.action;

import java.io.Serializable;

import authoring.AuthoringController;
import authoring.share.exception.ShareEditException;

public interface IAction extends Serializable {
	
	void apply(AuthoringController controller) throws ShareEditException;

}
