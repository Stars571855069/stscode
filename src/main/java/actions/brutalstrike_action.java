package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class brutalstrike_action extends AbstractGameAction {
    public DamageInfo info;
    public brutalstrike_action(AbstractMonster target, DamageInfo info) {
        this.target=target;
        this.info = info;
    }

    @Override
    public void update() {
        this.target.damage(this.info);
        if (this.target != null && this.target.hasPower("Weakened")) {
            this.addToTop(new GainEnergyAction(2));
        }
        addToTop((AbstractGameAction)new DamageAction(this.target, this.info, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        this.isDone = true;
    }
}
