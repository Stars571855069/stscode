package powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.RegenPower;

public class essence_retrive_power extends AbstractPower {

    public static final String POWER_ID = "mi_essence_retrive";
    private static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public essence_retrive_power(AbstractCreature owner, int Amount){

        this.ID= POWER_ID;
        this.name=POWER_STRINGS.NAME;
        this.owner = owner;
        this.amount = Amount;
        this.type = PowerType.DEBUFF;
        String path128 = "mikanresources/images/power_img/mi_essence_retrive_128.png";
        String path48 = "mikanresources/images/power_img/mi_essence_retrive_48.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = POWER_STRINGS.DESCRIPTIONS[0] + this.amount + POWER_STRINGS.DESCRIPTIONS[1];
    }

    public void onUseCard(AbstractCard c, UseCardAction action){
        if (c.type == AbstractCard.CardType.ATTACK){
            AbstractDungeon.actionManager.addToBottom(new HealAction(AbstractDungeon.player, AbstractDungeon.player, this.amount));
        }
    }
}
