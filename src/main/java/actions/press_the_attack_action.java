package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.actions.utility.WaitAction;

public class press_the_attack_action extends AbstractGameAction {
    public int ENERGY_GAIN;
    public press_the_attack_action(AbstractPlayer target, int ENERGY_GAIN ) {
        this.target=target;
        this.ENERGY_GAIN = ENERGY_GAIN;
    }

    @Override
    public void update() {
        AbstractDungeon.actionManager.addToTop((AbstractGameAction)new WaitAction(0.4F));
        tickDuration();
        boolean isallattack = true;
        if (this.isDone)
            for (AbstractCard c : DrawCardAction.drawnCards) {
                if (c.type != AbstractCard.CardType.ATTACK) {
                    isallattack=false;
                }
            }
        if (isallattack) {
            AbstractDungeon.actionManager.addToTop(new GainEnergyAction(this.ENERGY_GAIN));
        }
    }
}
