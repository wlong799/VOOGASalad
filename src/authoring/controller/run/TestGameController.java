package authoring.controller.run;

import authoring.AuthoringController;
import authoring.view.run.TestGameView;
import game_engine.physics.PhysicsParameterSetOptions;
import game_object.core.Game;
import game_object.level.Level;
import game_player.GameRunner;
import game_player.IEndListener;

/**
 * @author billyu
 */
public class TestGameController implements IEndListener {
	
	private TestGameView myTestView;
	private AuthoringController myTopController;
	private GameRunner myRunner;

	public TestGameController (AuthoringController topController) {
		myTopController = topController;
		myTestView = new TestGameView(topController);
	}

	public void showTestGame() {
		Game currentGame = myTopController.getEnvironment().getCurrentGame();
		myRunner = new GameRunner(myTestView.getScene(), currentGame, level -> myTestView.updateUI(level), this);
		myTestView.setRunningRoot(myRunner.getRunningView().getViews());
		myTestView.updateLayout();
		myTestView.show();
	}

	public void setParameter(PhysicsParameterSetOptions option, double value) {
		if (myRunner == null) return;
		Level original = myRunner.getOriginalLevel();
		Level running = myRunner.getRunningLevel();
		if (original == null || running == null) return;
		myTopController.setParameter(original, option, value);
		myTopController.setParameter(running, option, value);
	}

	@Override
	public void onEnd() {
	}

}