package powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
//import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.RegenPower;

public class core_recharge_power extends AbstractPower {

    public static final String POWER_ID = "mi_core_recharge";
    private static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public core_recharge_power(AbstractCreature owner, int HEAL_Amount){

        this.ID= POWER_ID;
        this.name=POWER_STRINGS.NAME;
        this.owner = owner;
        this.amount = HEAL_Amount;
        this.type = PowerType.BUFF;
        System.out.print(this.name);
        String path128 = "mikanresources/images/power_img/mi_core_recharge_128.png";
        String path48 = "mikanresources/images/power_img/mi_core_recharge_48.png";
        //this.region48 = atlas.findRegion("48/" + fileName);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = POWER_STRINGS.DESCRIPTIONS[0] + this.amount + POWER_STRINGS.DESCRIPTIONS[1];
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        // 非荆棘伤害，非生命流失伤害，伤害来源不为空，伤害来源不是能力持有者本身，伤害大于0
        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner && damageAmount > 0) {
            // 能力闪烁一下
            this.flash();
            // 添加回复action
            addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, new RegenPower(this.owner, this.amount), this.amount));
        }
        return damageAmount;
    }
}
