package authoring.view.inspector;

import authoring.AuthoringController;
import authoring.view.canvas.SpriteView;
import authoring.view.inspector.settings.*;
import game_object.block.IBlock;
import game_object.character.Hero;
import game_object.constants.DefaultConstants;
import game_object.core.ISprite;
import game_object.level.Level;
import goal.position.ReachPointGoal;

import static resources.ResourceBundles.componentProperties;

public class InspectorSpriteView extends AbstractInspectorTabView {
    private SpriteView spriteView;
    private ISprite sprite;

    private ComponentPhysicsSettings componentPhysicsSettings;
    private TextInputBoxView xBox, yBox, zBox, widthBox, heightBox;
    private CheckBoxView herosCollisionCB, enemiesCollisionCB, blockCollisionCB, applyPhysicsCB,
            reachPointCB;
    private SliderBoxView numJumpBox, jumpUnitBox;
    private ActionConfiguringView myActionView;

    public InspectorSpriteView(AuthoringController controller) {
        super(controller);
    }

    private void updateUI() {
        if (spriteView == null || (sprite = spriteView.getSprite()) == null) {
            return;
        }
        clearSettingsView();

        xBox = new TextInputBoxView(getController(), "Position X", sprite.getPosition().getX() + "",
                (newVal) -> spriteView.setAbsolutePositionX(Double.parseDouble(newVal)));
        yBox = new TextInputBoxView(getController(), "Position Y", sprite.getPosition().getY() + "",
                (newVal) -> spriteView.setAbsolutePositionY(Double.parseDouble(newVal)));
        zBox = new TextInputBoxView(getController(), "Position Z", sprite.getPosition().getZ() + "",
                (newVal) -> spriteView.setAbsolutePositionZ(Double.parseDouble(newVal)));
        widthBox = new TextInputBoxView(getController(), "Width", sprite.getDimension().getWidth() + "",
                (newVal) -> spriteView.setDimensionWidth(Double.parseDouble(newVal)));
        heightBox = new TextInputBoxView(getController(), "Height", sprite.getDimension().getHeight() + "",
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
        applyPhysicsCB = new CheckBoxView(getController(), "Apply Physics",
                sprite.getAffectedByPhysics(),
                (obv, old_val, new_val) -> componentPhysicsSettings.makePhysicsApplicable(new_val));
        addSettingsViews(xBox, yBox, zBox, widthBox, heightBox, herosCollisionCB, enemiesCollisionCB, blockCollisionCB,
                applyPhysicsCB);

        if (sprite instanceof IBlock) {
            reachPointCB = new CheckBoxView(getController(), "Assign sprite as goal point of level", false,
                    (obv, oldVal, newVal) -> {
                        Level currentLevel = getController().getEnvironment().getCurrentLevel();
                        currentLevel.getHeros().forEach(hero -> {
                            ReachPointGoal reachGoal = new ReachPointGoal(hero, sprite);
                            currentLevel.getAllGoals().add(reachGoal);
                        });
                    });
            addSettingsView(reachPointCB);
        }

        if (sprite instanceof Hero) {
            myActionView = new ActionConfiguringView(getController(), sprite);
            numJumpBox = new SliderBoxView(
                    getController(),
                    "Number of Jumps",
                    Double.parseDouble(componentProperties.getString("MIN_NUMBER_JUMPS")),
                    Double.parseDouble(componentProperties.getString("MAX_NUMBER_JUMPS")),
                    ((Hero) sprite).getMaxNumberOfJumps(),
                    Double.parseDouble(componentProperties.getString("JUMP_INCREMENT")),
                    (obv, oldVal, newVal) -> ((Hero) sprite).setMaxNumberOfJumps(newVal.intValue()));
            jumpUnitBox = new SliderBoxView(
                    getController(),
                    "Jump Unit",
                    Double.parseDouble(componentProperties.getString("MIN_JUMP_UNIT")),
                    Double.parseDouble(componentProperties.getString("MAX_JUMP_UNIT")),
                    ((Hero) sprite).getJumpingUnit(),
                    Double.parseDouble(componentProperties.getString("JUMP_UNIT_INCREMENT")),
                    (obv, oldVal, newVal) -> ((Hero) sprite).setJumpingUnit(newVal.doubleValue()));
            addSettingsViews(myActionView, numJumpBox, jumpUnitBox);
        }
        updateLayout();
    }

    public void setSpriteView(SpriteView spView) {
        spriteView = spView;
        updateUI();
    }
}