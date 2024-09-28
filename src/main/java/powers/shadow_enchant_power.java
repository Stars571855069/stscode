package powers;

import cards.reaper_mi;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class shadow_enchant_power extends AbstractPower {

    public static final String POWER_ID = "mi_shadow_enchant";
    private static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public shadow_enchant_power(AbstractCreature owner, int Amount){

        this.ID= POWER_ID;
        this.name=POWER_STRINGS.NAME;
        this.owner = owner;
        this.amount = Amount;

        String path128 = "mikanresources/images/power_img/mi_shadow_enchant_128.png";
        String path48 = "mikanresources/images/power_img/mi_shadow_enchant_48.png";

        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
        updateExistingAttackCards();
    }

    public void updateDescription() {
        this.description = POWER_STRINGS.DESCRIPTIONS[0];
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    private void updateExistingAttackCards() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.type== AbstractCard.CardType.ATTACK) {
                c.setCostForTurn(-9);
            }
        }
    }

    public void onDrawOrDiscard() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.type== AbstractCard.CardType.ATTACK) {
                c.setCostForTurn(-9);
            }
        }
    }

    public void onUseCard(AbstractCard c, UseCardAction action) {
        if (c.type== AbstractCard.CardType.ATTACK) {
            flash();
            int HP_LOST_AMOUNT=c.cost;
            AbstractDungeon.actionManager.addToBottom(new LoseHPAction(this.owner, null, HP_LOST_AMOUNT));
        }
    }

    public void atEndOfRound() {
        //flash();
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "mi_shadow_enchant"));
    }
}
