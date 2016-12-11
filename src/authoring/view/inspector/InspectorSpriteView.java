package authoring.view.inspector;

import static resources.ResourceBundles.componentProperties;

import authoring.AuthoringController;
import authoring.view.canvas.SpriteView;
import authoring.view.inspector.settings.ActionConfiguringView;
import authoring.view.inspector.settings.CheckBoxView;
import authoring.view.inspector.settings.ComponentPhysicsSettings;
import authoring.view.inspector.settings.LivesConfiguringView;
import authoring.view.inspector.settings.NullSettingsView;
import authoring.view.inspector.settings.SliderBoxView;
import authoring.view.inspector.settings.TextInputBoxView;
import game_object.block.IBlock;
import game_object.character.Enemy;
import game_object.character.Hero;
import game_object.character.ICharacter;
import game_object.constants.DefaultConstants;
import game_object.core.ISprite;
import game_object.level.Level;
import goal.position.ReachPointGoal;

public class InspectorSpriteView extends AbstractInspectorTabView {
    private SpriteView spriteView;
    private ISprite sprite;

    private ComponentPhysicsSettings componentPhysicsSettings;
    private TextInputBoxView myXBox, myYBox, myZBox, myWidthBox, myHeightBox, myVelocityXBox, myVelocityYBox;
    private CheckBoxView myHerosCollisionCheckBox, myEnemiesCollisionCheckBox, myBlockCollisionCheckBox,
            myApplyPhysicsCheckBox, myReachPointCheckBox, enemyHasAIBox, myBounceHBox, myBounceVBox, myPushedHBox, myPushedVBox;
    private SliderBoxView myMaxJumpSlider, myJumpUnitSlider, myDamageSlider;
    private ActionConfiguringView myActionView;
    private LivesConfiguringView myLivesConfiguringView;

    public InspectorSpriteView(AuthoringController controller) {
        super(controller);
    }

    @Override
    protected void initUI() {
        super.initUI();
        addSettingsView(new NullSettingsView(getController()));
    }

    private void updateUI() {
        clearSettingsView();
        if (spriteView == null || (sprite = spriteView.getSprite()) == null) {
            addSettingsView(new NullSettingsView(getController()));
            return;
        }

        myXBox = new TextInputBoxView(getController(), myLanguageResourceBundle.getString("posX"), sprite.getPosition().getX() + "",
                (newVal) -> spriteView.setAbsolutePositionX(Double.parseDouble(newVal)));
        myYBox = new TextInputBoxView(getController(), myLanguageResourceBundle.getString("posY"), sprite.getPosition().getY() + "",
                (newVal) -> spriteView.setAbsolutePositionY(Double.parseDouble(newVal)));
        myZBox = new TextInputBoxView(getController(), myLanguageResourceBundle.getString("posZ"), sprite.getPosition().getZ() + "",
                (newVal) -> spriteView.setAbsolutePositionZ(Double.parseDouble(newVal)));

        myWidthBox = new TextInputBoxView(getController(), myLanguageResourceBundle.getString("width"), sprite.getDimension().getWidth() + "",
                (newVal) -> spriteView.setDimensionWidth(Double.parseDouble(newVal), true));
        myHeightBox = new TextInputBoxView(getController(), myLanguageResourceBundle.getString("height"), sprite.getDimension().getHeight() + "",
                (newVal) -> spriteView.setDimensionHeight(Double.parseDouble(newVal), true));
        myVelocityXBox = new TextInputBoxView(getController(), myLanguageResourceBundle.getString("initialVelX"), sprite.getVelocity().getXVelocity() + "",
                (newVal) -> spriteView.getSprite().getVelocity().setXVelocity(Double.parseDouble(newVal)));
        myVelocityYBox = new TextInputBoxView(getController(), myLanguageResourceBundle.getString("initialVelY"), sprite.getVelocity().getYVelocity() + "",
                (newVal) -> spriteView.getSprite().getVelocity().setYVelocity(Double.parseDouble(newVal)));

        componentPhysicsSettings = new ComponentPhysicsSettings(sprite);
        myHerosCollisionCheckBox = new CheckBoxView(getController(), myLanguageResourceBundle.getString("collideWithHeros"),
                (sprite.getCollisionBitMask() & DefaultConstants.HERO_CATEGORY_BIT_MASK) != 0,
                (obv, old_val, new_val) -> componentPhysicsSettings.setCollisionSettingWithHeros(new_val));
        myEnemiesCollisionCheckBox = new CheckBoxView(getController(), myLanguageResourceBundle.getString("collideWithEnemies"),
                (sprite.getCollisionBitMask() & DefaultConstants.ENEMY_CATEGORY_BIT_MASK) != 0,
                (obv, old_val, new_val) -> componentPhysicsSettings.setCollisionSettingWithEnemies(new_val));
        myBlockCollisionCheckBox = new CheckBoxView(getController(), myLanguageResourceBundle.getString("collideWithBlocks"),
                (sprite.getCollisionBitMask() & DefaultConstants.BLOCK_CATEGORY_BIT_MASK) != 0,
                (obv, old_val, new_val) -> componentPhysicsSettings.setCollisionSettingWithBlock(new_val));
        myApplyPhysicsCheckBox = new CheckBoxView(getController(), myLanguageResourceBundle.getString("applyPhysics"),
                sprite.getAffectedByPhysics(),
                (obv, old_val, new_val) -> componentPhysicsSettings.makePhysicsApplicable(new_val));

        addSettingsViews(myXBox, myYBox, myZBox, myWidthBox, myHeightBox, myVelocityXBox, myVelocityYBox, myHerosCollisionCheckBox, myEnemiesCollisionCheckBox, myBlockCollisionCheckBox,
                myApplyPhysicsCheckBox);

        if (sprite instanceof IBlock) {
            myReachPointCheckBox = new CheckBoxView(getController(), myLanguageResourceBundle.getString("assignGoal"), false,
                    (obv, oldVal, newVal) -> {
                        Level currentLevel = getController().getEnvironment().getCurrentLevel();
                        currentLevel.getHeros().forEach(hero -> {
                            ReachPointGoal reachGoal = new ReachPointGoal(hero, sprite);
                            currentLevel.getAllGoals().add(reachGoal);
                        });
                    });
            addSettingsView(myReachPointCheckBox);
        }

        if (sprite instanceof Hero) {
        	Hero hero = (Hero) sprite;
            myActionView = new ActionConfiguringView(getController(), sprite);
            myMaxJumpSlider = new SliderBoxView(
                    getController(),
                    myLanguageResourceBundle.getString("noJumps"),
                    Double.parseDouble(componentProperties.getString("MIN_NUMBER_JUMPS")),
                    Double.parseDouble(componentProperties.getString("MAX_NUMBER_JUMPS")),
                    hero.getMaxNumberOfJumps(),
                    Double.parseDouble(componentProperties.getString("JUMP_INCREMENT")),
                    (obv, oldVal, newVal) -> ((Hero) sprite).setMaxNumberOfJumps(newVal.intValue()));
            myJumpUnitSlider = new SliderBoxView(
                    getController(),
                    myLanguageResourceBundle.getString("jumpUnit"),
                    Double.parseDouble(componentProperties.getString("MIN_JUMP_UNIT")),
                    Double.parseDouble(componentProperties.getString("MAX_JUMP_UNIT")),
                    hero.getJumpingUnit(),
                    Double.parseDouble(componentProperties.getString("JUMP_UNIT_INCREMENT")),
                    (obv, oldVal, newVal) -> ((Hero) sprite).setJumpingUnit(newVal.doubleValue()));
            myLivesConfiguringView = new LivesConfiguringView(getController(), hero);
            myPushedHBox = new CheckBoxView(
            		getController(), 
            		"Pushed by Enemy Horizontally", 
            		hero.getPushByEnemyCollsionStrategy().getHorizontalBounce(),
                    (obv, oldVal, newVal) -> {
                        hero.getPushByEnemyCollsionStrategy().setHorizontalBounce(newVal);
                    });
            myPushedVBox = new CheckBoxView(
            		getController(), 
            		"Pushed by Enemy Vertically", 
            		hero.getPushByEnemyCollsionStrategy().getVerticalBounce(),
                    (obv, oldVal, newVal) -> {
                        hero.getPushByEnemyCollsionStrategy().setVerticalBounce(newVal);;
                    });
            addSettingsViews(
            		myActionView, 
            		myMaxJumpSlider, 
            		myJumpUnitSlider,
            		myLivesConfiguringView,
            		myPushedHBox,
            		myPushedVBox);
        }
        
        if (sprite instanceof Enemy) {
        	Enemy enemy = (Enemy) sprite;
        	enemyHasAIBox = new CheckBoxView(getController(), "Has AI",
        			enemy.hasAI(),
                    (obv, old_val, new_val) -> ((Enemy)sprite).setHasAI(new_val));
        	myPushedHBox = new CheckBoxView(
            		getController(), 
            		"Pushed by Hero Horizontally", 
            		enemy.getPushByHeroCollsionStrategy().getHorizontalBounce(),
                    (obv, oldVal, newVal) -> {
                    	enemy.getPushByHeroCollsionStrategy().setHorizontalBounce(newVal);
                    });
            myPushedVBox = new CheckBoxView(
            		getController(), 
            		"Pushed by Hero Vertically", 
            		enemy.getPushByHeroCollsionStrategy().getVerticalBounce(),
                    (obv, oldVal, newVal) -> {
                    	enemy.getPushByHeroCollsionStrategy().setVerticalBounce(newVal);;
                    });
        	addSettingsViews(
        			enemyHasAIBox,
        			myPushedHBox,
        			myPushedVBox);
        }
        
        if (sprite instanceof ICharacter) {
        	ICharacter character = (ICharacter) sprite;
        	myDamageSlider = new SliderBoxView(
                    getController(),
                    "Damage by Projectile",
                    Double.parseDouble(componentProperties.getString("MIN_DAMAGE")),
                    Double.parseDouble(componentProperties.getString("MAX_DAMAGE")),
                    character.getAttackByProjectileStrategy().getDamage(),
                    Double.parseDouble(componentProperties.getString("DAMAGE_INCREMENT")),
                    (obv, oldVal, newVal) -> character.getAttackByProjectileStrategy().setDamageFromAllDirection(newVal.doubleValue()));
            myBounceHBox = new CheckBoxView(
            		getController(), 
            		"Bounce Horizontally", 
            		character.getCollideWithBlockStrategy().getHorizontalBounce(),
                    (obv, oldVal, newVal) -> character.getCollideWithBlockStrategy().setHorizontalBounce(newVal));
            myBounceVBox = new CheckBoxView(
            		getController(), 
            		"Bounce Vertically", 
            		character.getCollideWithBlockStrategy().getVerticalBounce(),
                    (obv, oldVal, newVal) -> character.getCollideWithBlockStrategy().setVerticalBounce(newVal));
            addSettingsViews(
            		myDamageSlider, 
            		myBounceHBox, 
            		myBounceVBox);
        }
        updateLayout();
    }

    public void setSpriteView(SpriteView spView) {
        spriteView = spView;
        updateUI();
    }
    
}