package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import powers.ghost_vessel_power;
import relics.mi_ghost_vessel;

public class ghost_vessel_action extends AbstractGameAction {
    public DamageInfo info;
    public ghost_vessel_action(AbstractMonster target, DamageInfo info) {
        this.target=target;
        this.info = info;
    }

    @Override
    public void update() {
        this.target.damage(this.info);
        if ((this.target.isDying || this.target.currentHealth <= 0) && !this.target.halfDead
                && !this.target.hasPower("Minion")) {
            //mi_ghost_vessel.counter++;
            addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ghost_vessel_power(AbstractDungeon.player, 1), 1));
        }
        this.isDone = true;
    }
}
