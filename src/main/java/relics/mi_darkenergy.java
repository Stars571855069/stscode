package relics;
import Helpers.ModHelper;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;


public class mi_darkenergy extends CustomRelic{

    public static final String ID = "mi_darkenergy";
    private static final String IMG = "mikanresources/images/relics_img/mi_darkenergy.png";
    private static final String IMG_OTL = "mikanresources/images/relics_img/outline/mi_darkenergy.png";
    private static final LandingSound LANDING_SOUND = LandingSound.CLINK;
    private static final RelicTier RELIC_TIER = RelicTier.COMMON;
    private static final int darkenergy_potency = 1;

    //调用父类的构造方法，传参为super(遗物ID,遗物全图，遗物白底图，遗物稀有度，获得遗物时的音效)
    public mi_darkenergy() {
        //super(ID, ImageMaster.loadImage(IMG), RELIC_TIER, LANDING_SOUND);
        super(ID,ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL),RELIC_TIER,LANDING_SOUND);
        //super(ID, ImageMaster.loadImage(IMG_PATH), ImageMaster.loadImage(OUTLINE_PATH), RELIC_TIER, LANDING_SOUND);
}

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        //在用户使用牌时触发
        if (card.type == AbstractCard.CardType.ATTACK) {
                flash();
                addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new DexterityPower((AbstractCreature)AbstractDungeon.player,darkenergy_potency),darkenergy_potency));
                addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new LoseDexterityPower((AbstractCreature)AbstractDungeon.player,darkenergy_potency), darkenergy_potency));

        }
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new mi_darkenergy();
    }
}