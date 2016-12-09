package authoring.view.inspector;

import authoring.AuthoringController;
import authoring.view.canvas.SpriteView;
import authoring.view.inspector.settings.CheckBoxView;
import authoring.view.inspector.settings.JumpConfiguringView;
import authoring.view.inspector.settings.TextInputBoxView;
import game_object.block.IBlock;
import game_object.character.Hero;
import game_object.constants.DefaultConstants;
import game_object.core.ISprite;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class InspectorSpriteView extends AbstractInspectorTabView {
    private SpriteView spriteView;
    private ISprite sprite;
    private TextInputBoxView xBox, yBox, zBox, widthBox, heightBox;
    private CheckBoxView herosCollisionCB, enemiesCollisionCB, blockCollisionCB, applyPhysics;

    private ComponentPhysicsSettings componentPhysicsSettings;
    private ActionConfiguringView myActionView;
    private JumpConfiguringView myJumpView;
    private ReachPointGoalConfiguringView reachPointGoal;

    public interface ITextChangeHandler {
        void handle(String newVal);
    }

    public InspectorSpriteView(AuthoringController controller) {
        super(controller);
    }

    private void updateUI() {
        if (spriteView.getSprite() == null) {
            return;
        }
        clearSettingsView();

        xBox = new TextInputBoxView(getController(), "Position X", "",
                (newVal) -> spriteView.setAbsolutePositionX(Double.parseDouble(newVal)));
        yBox = new TextInputBoxView(getController(), "Position Y", "",
                (newVal) -> spriteView.setAbsolutePositionY(Double.parseDouble(newVal)));
        zBox = new TextInputBoxView(getController(), "Position Z", "",
                (newVal) -> spriteView.setAbsolutePositionZ(Double.parseDouble(newVal)));
        widthBox = new TextInputBoxView(getController(), "Width", "",
                (newVal) -> spriteView.setDimensionWidth(Double.parseDouble(newVal)));
        heightBox = new TextInputBoxView(getController(), "Height", "",
                (newVal) -> spriteView.setDimensionHeight(Double.parseDouble(newVal)));


        componentPhysicsSettings = new ComponentPhysicsSettings(sprite);
        herosCollisionCB = new CheckBoxView(getController(), "Collide with Heros",
                (sprite.getCollisionBitMask() & DefaultConstants.HERO_CATEGORY_BIT_MASK) != 0,
                (obv, old_val, new_val) -> componentPhysicsSettings.setCollisionSettingWithHeros(new_val));
        enemiesCollisionCB = new CheckBoxView(getController(), "Collide with Enemies",
                (sprite.getCollisionBitMask() & DefaultConstants.ENEMY_CATEGORY_BIT_MASK) != 0,
                (obv, old_val, new_val) -> componentPhysicsSettings.setCollisionSettingWithEnemies(new_val));
        blockCollisionCB = new CheckBoxView(getController(), "Collide with Blocks",
                (sprite.getCollisionBitMask() & DefaultConstants.BLOCK_CATEGORY_BIT_MASK) != 0,
                (obv, old_val, new_val) -> componentPhysicsSettings.setCollisionSettingWithBlock(new_val));
        applyPhysics = new CheckBoxView(getController(), "Apply Physics",
                sprite.getAffectedByPhysics(),
                (obv, old_val, new_val) -> componentPhysicsSettings.makePhysicsApplicable(new_val));
        addSettingsViews(xBox, yBox, zBox, widthBox, heightBox, herosCollisionCB, enemiesCollisionCB, blockCollisionCB, applyPhysics);


        if (sprite instanceof IBlock) {
            reachPointGoal = new ReachPointGoalConfiguringView(this.getController());
            reachPointGoal.setUpReachPointGoalCheckBox(sprite);
            this.addSubView(reachPointGoal);
            configs.getChildren().add(reachPointGoal.getUI());
        }
        if (sprite instanceof Hero) {
            myActionView = new ActionConfiguringView(this.getController());
            myActionView.setSprite(sprite);
            this.addSubView(myActionView);
            myJumpView = new JumpConfiguringView(this.getController());
            myJumpView.setSprite(sprite);
            this.addSubView(myJumpView);
            configs.getChildren().addAll(myActionView.getUI(), myJumpView.getUI());
        }
    }

    public void setSpriteView(SpriteView spView) {
        spriteView = spView;
        updateUI();
    }
}