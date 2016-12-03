package authoring.controller.run;

import authoring.AuthoringController;
import authoring.view.run.TestGameView;
import game_engine.physics.PhysicsParameterSetOptions;
import game_object.core.Game;
import game_object.level.Level;
import game_player.GameRunner;

/**
 * @author billyu
 */
public class TestGameController {
	
	private TestGameView myTestView;
	private AuthoringController myTopController;
	private GameRunner myRunner;

	public TestGameController (AuthoringController topController) {
		myTopController = topController;
		myTestView = new TestGameView(topController);
	}

	public void showTestGame() {
		Game currentGame = myTopController.getEnvironment().getCurrentGame();
		myRunner = new GameRunner(myTestView.getScene(), currentGame);
		myTestView.setRunningRoot(myRunner.getRunningView().getViews());
		myTestView.updateUI();
		myTestView.updateLayout();
		myTestView.show();
	}

	public void setParameter(PhysicsParameterSetOptions option, double value) {
		Level original = myRunner.getOriginalLevel();
		Level running = myRunner.getRunningLevel();
		if (original == null || running == null) return;
		original.getPhysicsParameters().set(option, value);
		running.getPhysicsParameters().set(option, value);
	}

}