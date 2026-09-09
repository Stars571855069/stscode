package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.green.DodgeAndRoll;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import com.megacrit.cardcrawl.vfx.combat.ClashEffect;

public class longrange_strike_action extends AbstractGameAction {
    public DamageInfo info;
    private AbstractMonster targetMonster;
    private AbstractPlayer p;

    public longrange_strike_action(AbstractPlayer p,AbstractMonster target, int block_amt) {
        this.targetMonster=target;
        this.p=p;
        //this.info = info;
        this.amount=block_amt;
        //this.info.
    }

    @Override
    public void update() {
        //System.out.print("敌人意图" + this.targetMonster.getIntentBaseDmg());
        //this.targetMonster.damage(this.info);
        if (this.targetMonster != null && this.targetMonster.getIntentBaseDmg() <= 0) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.p,this.p,new NextTurnBlockPower(this.p,this.amount),this.amount));
        }
        this.isDone = true;
    }
}
