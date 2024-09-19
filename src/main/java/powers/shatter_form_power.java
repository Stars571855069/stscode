package powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.RegenPower;

public class shatter_form_power extends AbstractPower {

    public static final String POWER_ID = "mi_shatter_form";
    private static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public shatter_form_power(AbstractCreature owner, int Amount){

        this.ID= POWER_ID;
        this.name=POWER_STRINGS.NAME;
        this.owner = owner;
        this.amount = Amount;
        String path128 = "mikanresources/images/power_img/mi_shatter_form_128.png";
        String path48 = "mikanresources/images/power_img/mi_shatter_form_48.png";
        //this.region48 = atlas.findRegion("48/" + fileName);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = POWER_STRINGS.DESCRIPTIONS[0];
        }
        else {
            this.description = POWER_STRINGS.DESCRIPTIONS[1] + this.amount + POWER_STRINGS.DESCRIPTIONS[2];
        }
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        // 非荆棘伤害，非生命流失伤害，伤害来源不为空，伤害来源不是能力持有者本身，伤害大于0
        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner && damageAmount > 0) {
            this.flash();
            this.amount--;
            this.updateDescription();
            AbstractDungeon.actionManager.addToBottom(new DamageAction(info.owner, new DamageInfo(this.owner, damageAmount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            if (this.amount <= 0) {
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "mi_shatter_form"));
            }
        }
        return damageAmount;
    }

}
