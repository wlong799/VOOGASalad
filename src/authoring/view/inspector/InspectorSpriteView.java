package authoring.view.inspector;

import static resources.ResourceBundles.componentProperties;

import authoring.AuthoringController;
import authoring.view.canvas.SpriteView;
import authoring.view.inspector.settings.ActionConfiguringView;
import authoring.view.inspector.settings.CheckBoxView;
import authoring.view.inspector.settings.ComponentPhysicsSettings;
import authoring.view.inspector.settings.HealthPointConfiguringView;
import authoring.view.inspector.settings.LabelView;
import authoring.view.inspector.settings.NullSettingsView;
import authoring.view.inspector.settings.SliderBoxView;
import authoring.view.inspector.settings.TextInputBoxView;
import game_object.block.IBlock;
import game_object.character.Enemy;
import game_object.character.Hero;
import game_object.character.ICharacter;
import game_object.collision.AttackCollisionStrategy;
import game_object.collision.MotionCollisionStrategy;
import game_object.constants.DefaultConstants;
import game_object.core.ISprite;
import game_object.level.Level;
import game_object.powerup.NewWeaponPowerUp;
import game_object.powerup.SpeedUpPowerUp;
import goal.position.ReachPointGoal;
import javafx.scene.control.Label;


public class InspectorSpriteView extends AbstractInspectorTabView {

    private SpriteView mySpriteView;
    private ISprite mySprite;

    private ComponentPhysicsSettings componentPhysicsSettings;
    private TextInputBoxView myXBox, myYBox, myZBox, myWidthBox, myHeightBox, myVelocityXBox,
            myVelocityYBox;
    private CheckBoxView myHerosCollisionCheckBox, myEnemiesCollisionCheckBox,
            myBlockCollisionCheckBox,
            myApplyPhysicsCheckBox, myReachPointCheckBox, enemyHasAIBox, myBounceHBox, myBounceVBox,
            myPushedHBox, myPushedVBox, myKillHeroBox;
    private SliderBoxView myMaxJumpSlider, myJumpUnitSlider, myDamageSlider;
    private CheckBoxView myInfiniteJumps;
    private ActionConfiguringView myActionView;
    private HealthPointConfiguringView myHealthPointConfiguringView;

    public InspectorSpriteView (AuthoringController controller) {
        super(controller);
    }

    @Override
    protected void initUI () {
        super.initUI();
        addSettingsView(new NullSettingsView(getController()));
    }

    private void updateUI () {
        clearSettingsView();
        if (mySpriteView == null || (mySprite = mySpriteView.getSprite()) == null) {
            addSettingsView(new NullSettingsView(getController()));
            return;
        }
        addCommonSettings();

        if (mySprite instanceof IBlock) {
            addBlockSettings();
        }

        if (mySprite instanceof Hero) {
            addHeroSettings();
        }

        if (mySprite instanceof Enemy) {
            addEnemySettings();
        }
        if (mySprite instanceof ICharacter) {
            addCharacterSettings();
        }
        if (mySprite instanceof NewWeaponPowerUp) {
        	addWeaponSettings();
        }
        if (mySprite instanceof SpeedUpPowerUp) {
        	addSpeedSettings();
        }
        updateLayout();
    }

    public void setSpriteView (SpriteView spView) {
        mySpriteView = spView;
        updateUI();
    }
    
    private void addCommonSettings() {
    	LabelView myLabelView = new LabelView(getController(), mySprite);
        myXBox =
                new TextInputBoxView(getController(), myLanguageResourceBundle.getString("posX"),
                                     mySprite.getPosition().getX() + "",
                                     (newVal) -> mySpriteView
                                             .setAbsolutePositionX(Double.parseDouble(newVal)));
        myYBox =
                new TextInputBoxView(getController(), myLanguageResourceBundle.getString("posY"),
                                     mySprite.getPosition().getY() + "",
                                     (newVal) -> mySpriteView
                                             .setAbsolutePositionY(Double.parseDouble(newVal)));
        myZBox =
                new TextInputBoxView(getController(), myLanguageResourceBundle.getString("posZ"),
                                     mySprite.getPosition().getZ() + "",
                                     (newVal) -> mySpriteView
                                             .setAbsolutePositionZ(Double.parseDouble(newVal)));

        myWidthBox =
                new TextInputBoxView(getController(), myLanguageResourceBundle.getString("width"),
                                     mySprite.getDimension().getWidth() + "",
                                     (newVal) -> mySpriteView
                                             .setDimensionWidth(Double.parseDouble(newVal), true));
        myHeightBox =
                new TextInputBoxView(getController(), myLanguageResourceBundle.getString("height"),
                                     mySprite.getDimension().getHeight() + "",
                                     (newVal) -> mySpriteView
                                             .setDimensionHeight(Double.parseDouble(newVal), true));
        myVelocityXBox =
                new TextInputBoxView(getController(),
                                     myLanguageResourceBundle.getString("initialVelX"),
                                     mySprite.getVelocity().getXVelocity() + "",
                                     (newVal) -> mySpriteView.getSprite().getVelocity()
                                             .setXVelocity(Double.parseDouble(newVal)));
        myVelocityYBox =
                new TextInputBoxView(getController(),
                                     myLanguageResourceBundle.getString("initialVelY"),
                                     mySprite.getVelocity().getYVelocity() + "",
                                     (newVal) -> mySpriteView.getSprite().getVelocity()
                                             .setYVelocity(Double.parseDouble(newVal)));
        componentPhysicsSettings = new ComponentPhysicsSettings(mySprite);
        myHerosCollisionCheckBox =
                new CheckBoxView(getController(),
                                 myLanguageResourceBundle.getString("collideWithHeros"),
                                 (mySprite.getCollisionBitMask() &
                                  DefaultConstants.HERO_CATEGORY_BIT_MASK) != 0,
                                 (obv, old_val, new_val) -> {
                                     componentPhysicsSettings
                                         .setCollisionSettingWithHeros(new_val);
                                     /*if(new_val){
                                         addSettingsView(myKillHeroBox);
                                         }
                                     else{
                                         removeSettingsView(myKillHeroBox);
                                     }*/
                                     }
                                 );
        myEnemiesCollisionCheckBox =
                new CheckBoxView(getController(),
                                 myLanguageResourceBundle.getString("collideWithEnemies"),
                                 (mySprite.getCollisionBitMask() &
                                  DefaultConstants.ENEMY_CATEGORY_BIT_MASK) != 0,
                                 (obv, old_val, new_val) -> componentPhysicsSettings
                                         .setCollisionSettingWithEnemies(new_val));
        myBlockCollisionCheckBox =
                new CheckBoxView(getController(),
                                 myLanguageResourceBundle.getString("collideWithBlocks"),
                                 (mySprite.getCollisionBitMask() &
                                  DefaultConstants.BLOCK_CATEGORY_BIT_MASK) != 0,
                                 (obv, old_val, new_val) -> {
                                     componentPhysicsSettings
                                         .setCollisionSettingWithBlock(new_val);
                                     if(myKillHeroBox!=null){
                                         myKillHeroBox.getBox().setDisable(!new_val);
                                     }
                                 }
                                 );
        myApplyPhysicsCheckBox =
                new CheckBoxView(getController(),
                                 myLanguageResourceBundle.getString("applyPhysics"),
                                 mySprite.getAffectedByPhysics(),
                                 (obv, old_val, new_val) -> componentPhysicsSettings
                                         .makePhysicsApplicable(new_val));
        addSettingsViews(myLabelView, myXBox, myYBox, myZBox, myWidthBox, myHeightBox,
                         myVelocityXBox, myVelocityYBox, myHerosCollisionCheckBox,
                         myEnemiesCollisionCheckBox, myBlockCollisionCheckBox,
                         myApplyPhysicsCheckBox);
    }
    
    private void addBlockSettings() {
    	myReachPointCheckBox =
                new CheckBoxView(getController(),
                                 myLanguageResourceBundle.getString("assignGoal"), false,
                                 (obv, oldVal, newVal) -> {
                                     Level currentLevel =
                                             getController().getEnvironment().getCurrentLevel();
                                     currentLevel.getHeros().forEach(hero -> {
                                         ReachPointGoal reachGoal =
                                                 new ReachPointGoal(hero, mySprite);
                                         currentLevel.getAllGoals().add(reachGoal);
                                     });
                                 });
        addSettingsView(myReachPointCheckBox);
    }
    
    private void addHeroSettings() {
    	Hero hero = (Hero) mySprite;
        myActionView = new ActionConfiguringView(getController(), mySprite);
        myKillHeroBox = new CheckBoxView(getController(),
                                         myLanguageResourceBundle.getString("collisionBlockDeath"),
                                         false,
                                         (obv, old_val, new_val) -> {
                                             AttackCollisionStrategy strat = new AttackCollisionStrategy();
                                             MotionCollisionStrategy motion = new MotionCollisionStrategy();
                                             if (new_val) {
                                                 strat.setDamageFromAllDirection(hero.getMaxHP()+1); //die bitch
                                                 hero.setCollideWithBlockStrategy(strat);
                                             }
                                             if (old_val){
                                                 hero.setCollideWithBlockStrategy(motion);
                                             }
                                         });
        myMaxJumpSlider = new SliderBoxView(
                                            getController(),
                                            myLanguageResourceBundle.getString("noJumps"),
                                            Double.parseDouble(componentProperties
                                                    .getString("MIN_NUMBER_JUMPS")),
                                            Double.parseDouble(componentProperties
                                                    .getString("MAX_NUMBER_JUMPS")),
                                            hero.getMaxNumberOfJumps(),
                                            Double.parseDouble(componentProperties
                                                    .getString("JUMP_INCREMENT")),
                                            (obv, oldVal, newVal) -> ((Hero) mySprite)
                                                    .setMaxNumberOfJumps(newVal.intValue()));
        myInfiniteJumps = new CheckBoxView(
                                           getController(),
                                           myLanguageResourceBundle.getString("infiniteJumps"),
                                           false,
                                           (obv, oldVal, newVal) -> {
                                               if (newVal) {
                                                   hero.setMaxNumberOfJumps(Integer.MAX_VALUE);
                                                   myMaxJumpSlider.getUI().setDisable(true);
                                               }
                                               else {
                                                   myMaxJumpSlider.getUI().setDisable(false);
                                               }
                                           });
        myJumpUnitSlider = new SliderBoxView(
                                             getController(),
                                             myLanguageResourceBundle.getString("jumpUnit"),
                                             Double.parseDouble(componentProperties
                                                     .getString("MIN_JUMP_UNIT")),
                                             Double.parseDouble(componentProperties
                                                     .getString("MAX_JUMP_UNIT")),
                                             hero.getJumpingUnit(),
                                             Double.parseDouble(componentProperties
                                                     .getString("JUMP_UNIT_INCREMENT")),
                                             (obv, oldVal, newVal) -> (hero)
                                                     .setJumpingUnit(newVal.doubleValue()));
        myHealthPointConfiguringView = new HealthPointConfiguringView(getController(), hero);
        myPushedHBox = new CheckBoxView(
                                        getController(),
                                        myLanguageResourceBundle.getString("enemyHorizontally"),
                                        hero.getPushByEnemyCollsionStrategy()
                                                .getHorizontalBounce(),
                                        (obv, oldVal, newVal) -> {
                                            hero.getPushByEnemyCollsionStrategy()
                                                    .setHorizontalBounce(newVal);
                                        });
        myPushedVBox = new CheckBoxView(
                                        getController(),
                                        myLanguageResourceBundle.getString("enemyVertically"),
                                        hero.getPushByEnemyCollsionStrategy()
                                                .getVerticalBounce(),
                                        (obv, oldVal, newVal) -> {
                                            hero.getPushByEnemyCollsionStrategy()
                                                    .setVerticalBounce(newVal);
                                            ;
                                        });
        if((hero.getCollisionBitMask() & DefaultConstants.BLOCK_CATEGORY_BIT_MASK) == 0){
            myKillHeroBox.getBox().setDisable(true);
        }
        addSettingsViews(myKillHeroBox,
                         myActionView,
                         myInfiniteJumps,
                         myMaxJumpSlider,
                         myJumpUnitSlider,
                         myHealthPointConfiguringView,
                         myPushedHBox,
                         myPushedVBox);
    }
    
    private void addEnemySettings() {
    	Enemy enemy = (Enemy) mySprite;
        enemyHasAIBox =
                new CheckBoxView(getController(), myLanguageResourceBundle.getString("hasAI"),
                                 enemy.hasAI(),
                                 (obv, old_val, new_val) -> ((Enemy) mySprite)
                                         .setHasAI(new_val));
        CheckBoxView canShootBox = new CheckBoxView(
        		this.getController(),
        		myLanguageResourceBundle.getString("canShoot"),
        		enemy.canShoot(),
        		(obv, old_val, new_val) -> enemy.setShoot(new_val));
        CheckBoxView followBox = new CheckBoxView(
        		this.getController(),
        		myLanguageResourceBundle.getString("canFollow"),
        		enemy.getCanProjectileFollowHero(),
        		(obv, old_val, new_val) -> enemy.setCanProjectileFollowHero(new_val));
        myPushedHBox = new CheckBoxView(
                                        getController(),
                                        myLanguageResourceBundle.getString("heroHorizontally"),
                                        enemy.getPushByHeroCollsionStrategy()
                                                .getHorizontalBounce(),
                                        (obv, oldVal, newVal) -> {
                                            enemy.getPushByHeroCollsionStrategy()
                                                    .setHorizontalBounce(newVal);
                                        });
        myPushedVBox = new CheckBoxView(
                                        getController(),
                                        myLanguageResourceBundle.getString("heroVertically"),
                                        enemy.getPushByHeroCollsionStrategy()
                                                .getVerticalBounce(),
                                        (obv, oldVal, newVal) -> {
                                            enemy.getPushByHeroCollsionStrategy()
                                                    .setVerticalBounce(newVal);
                                            ;
                                        });
        addSettingsViews(
                         enemyHasAIBox,
                         canShootBox,
                         followBox,
                         myPushedHBox,
                         myPushedVBox);
    }
    
    private void addCharacterSettings() {
    	ICharacter character = (ICharacter) mySprite;
        myDamageSlider = new SliderBoxView(
                                           getController(),
                                           myLanguageResourceBundle
                                                   .getString("damageByProjectile"),
                                           Double.parseDouble(componentProperties
                                                   .getString("MIN_DAMAGE")),
                                           Double.parseDouble(componentProperties
                                                   .getString("MAX_DAMAGE")),
                                           character.getAttackByProjectileStrategy()
                                                   .getDamage(),
                                           Double.parseDouble(componentProperties
                                                   .getString("DAMAGE_INCREMENT")),
                                           (obv, oldVal, newVal) -> character
                                                   .getAttackByProjectileStrategy()
                                                   .setDamageFromAllDirection(newVal
                                                           .doubleValue()));
        if(character.getCollideWithBlockStrategy() instanceof MotionCollisionStrategy){
        MotionCollisionStrategy mcs = (MotionCollisionStrategy)character.getCollideWithBlockStrategy();
        myBounceHBox = new CheckBoxView(
                                        getController(),
                                        myLanguageResourceBundle
                                                .getString("bounceHorizontally"),
                                        mcs.getHorizontalBounce(),
                                        (obv, oldVal, newVal) -> mcs.setHorizontalBounce(newVal));
        myBounceVBox = new CheckBoxView(
                                        getController(),
                                        myLanguageResourceBundle.getString("bounceVertically"),
                                        mcs.getVerticalBounce(),
                                        (obv, oldVal, newVal) -> mcs.setVerticalBounce(newVal));
        addSettingsViews(
                         myDamageSlider,
                         myBounceHBox,
                         myBounceVBox);
        } else {
            addSettingsViews(myDamageSlider);
        }
    }
    
    private void addWeaponSettings() {
    	NewWeaponPowerUp up = (NewWeaponPowerUp) mySprite;
    	SliderBoxView vSlider = new SliderBoxView(
                getController(),
                myLanguageResourceBundle.getString("projectileVelocity"),
                Double.parseDouble(componentProperties
                        .getString("projVMin")),
                Double.parseDouble(componentProperties
                        .getString("projVMax")),
                up.getProjectileVelocity().getXVelocity(),
                Double.parseDouble(componentProperties
                        .getString("projVInc")),
                (obv, oldVal, newVal) -> 
                up.getProjectileVelocity().setXVelocity(newVal.doubleValue()));
    	addSettingsViews(vSlider);
    }
    
    private void addSpeedSettings() {
    	SpeedUpPowerUp up = (SpeedUpPowerUp) mySprite;
    	SliderBoxView vSlider = new SliderBoxView(
                getController(),
                myLanguageResourceBundle.getString("speedUp"),
                Double.parseDouble(componentProperties
                        .getString("speedMin")),
                Double.parseDouble(componentProperties
                        .getString("speedMax")),
                up.getSpeedUpFactor(),
                Double.parseDouble(componentProperties
                        .getString("speedInc")),
                (obv, oldVal, newVal) -> 
                up.setSpeedUpFactor(newVal.doubleValue()));
    	addSettingsViews(vSlider);
    }

}
