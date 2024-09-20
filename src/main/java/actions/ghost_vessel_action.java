package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ClashEffect;
import com.megacrit.cardcrawl.vfx.combat.SearingBlowEffect;

public class ghost_vessel_action extends AbstractGameAction {
    public DamageInfo info;
    public ghost_vessel_action(AbstractMonster target, DamageInfo info) {
        this.target=target;
        this.info = info;
    }

    @Override
    public void update() {
        this.target.damage(this.info);
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new SearingBlowEffect(this.target.hb.cX, this.target.hb.cY,10), 0.1F));
        if ((this.target.isDying || this.target.currentHealth <= 0) && !this.target.halfDead
                && !this.target.hasPower("Minion")) {
            AbstractRelic relic = AbstractDungeon.player.getRelic("mi_ghost_vessel");
            if (relic != null) {
                addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, relic));
                relic.counter++;
            }
        }
        this.isDone = true;
    }
}
