package relics;

import basemod.abstracts.CustomRelic;
import cards.reaper_mi;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.cards.tempCards.*;
import powers.ghost_vessel_power;


public class mi_ghost_vessel extends CustomRelic{

    public static final String ID = "mi_ghost_vessel";
    private static final String IMG = "mikanresources/images/relics_img/mi_ghost_vessel.png";
    private static final String IMG_OTL = "mikanresources/images/relics_img/outline/mi_ghost_vessel.png";
    private static final LandingSound LANDING_SOUND = LandingSound.MAGICAL;
    private static final RelicTier RELIC_TIER = RelicTier.STARTER;
    //调用父类的构造方法，传参为super(遗物ID,遗物全图，遗物白底图，遗物稀有度，获得遗物时的音效)
    public mi_ghost_vessel() {
        //super(ID, ImageMaster.loadImage(IMG), RELIC_TIER, LANDING_SOUND);
        super(ID,ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL),RELIC_TIER,LANDING_SOUND);
        this.counter=0;
}

    @Override
    public void atBattleStart() {
        int GHOST_ENERGY_CHARGE = this.counter;
            flash();
            addToBot(new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
            addToTop(new MakeTempCardInHandAction(new reaper_mi(), 1, false));
            addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ghost_vessel_power(AbstractDungeon.player, GHOST_ENERGY_CHARGE), GHOST_ENERGY_CHARGE));
    }
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new mi_ghost_vessel();
    }
}