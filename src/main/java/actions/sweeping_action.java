package actions;

import com.brashmonkey.spriter.Player;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import powers.ghost_vessel_power;

public class sweeping_action extends AbstractGameAction {
    public DamageInfo info;
    public AbstractPlayer p;
    public sweeping_action(AbstractPlayer p, DamageInfo info) {
        this.p=p;
        this.info = info;
    }

    @Override
    public void update() {
        //this.target.damage(this.info);
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.p, this.p, new ghost_vessel_power(this.p, 1), 1));
        }
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p,info.base,info.type,AttackEffect.NONE));
        this.isDone = true;
    }
}
