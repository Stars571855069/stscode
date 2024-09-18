package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

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
            AbstractRelic relic = AbstractDungeon.player.getRelic("mi_ghost_vessel");
            if (relic != null) {
                relic.counter++;
            }
        }
        this.isDone = true;
    }
}
