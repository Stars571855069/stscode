package powers;

import cards.reaper_mi;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.RegenPower;

public class ghost_vessel_power extends AbstractPower {

    public static final String POWER_ID = "mi_ghost_vessel";
    private static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public ghost_vessel_power(AbstractCreature owner, int Amount){

        this.ID= POWER_ID;
        this.name=POWER_STRINGS.NAME;
        this.owner = owner;
        this.amount = Amount;
        //this.type = PowerType.BUFF;
        //System.out.print(this.name);

        String path128 = "mikanresources/images/power_img/mi_ghost_vessel_128.png";
        String path48 = "mikanresources/images/power_img/mi_ghost_vessel_48.png";
        //this.region48 = atlas.findRegion("48/" + fileName);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
        updateExistingReapers();
    }
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        if (this.amount <= 0) {
            this.amount = 0;
        }
        updateDescription();
        updateExistingReapers();
    }

    public void updateDescription() {
        this.description = POWER_STRINGS.DESCRIPTIONS[0] + this.amount*3 + POWER_STRINGS.DESCRIPTIONS[1];
    }
    private void updateExistingReapers() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c instanceof reaper_mi) {
                c.baseDamage = 4 + 3 * this.amount;
            }
        }
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c instanceof reaper_mi) {
                c.baseDamage = 4 + 3 * this.amount;
            }
        }
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c instanceof reaper_mi) {
                c.baseDamage = 4 + 3 * this.amount;
            }
        }
        for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
            if (c instanceof reaper_mi) {
                c.baseDamage = 4 + 3 * this.amount;
            }
        }
    }

    public void onDrawOrDiscard() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c instanceof reaper_mi) {
                c.baseDamage = 4 + 3 * this.amount;
            }
        }
    }
}
