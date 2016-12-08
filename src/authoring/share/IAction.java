package authoring.share;

import authoring.AuthoringController;
import authoring.share.exception.ShareEditException;

public interface IAction {
	
	void apply(AuthoringController controller) throws ShareEditException;

}
