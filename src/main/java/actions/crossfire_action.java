package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ClashEffect;

public class crossfire_action extends AbstractGameAction {
    public DamageInfo info;
    private AbstractMonster targetMonster;

    public crossfire_action(AbstractMonster target, DamageInfo info) {
        this.targetMonster=target;
        this.info = info;
    }

    @Override
    public void update() {
        //this.target.damage(this.info);
        if (this.targetMonster != null && this.targetMonster.getIntentBaseDmg() >= 0) {
            addToBot(new VFXAction(new ClashEffect(this.targetMonster.hb.cX, this.targetMonster.hb.cY), 0.1F));
            this.targetMonster.damage(new DamageInfo(this.info.owner, this.info.base*2, DamageInfo.DamageType.NORMAL));
        }
        else {
            //this.info.base=this.info.base/2;
            if (this.targetMonster != null) {
                //this.info.base=this.info.base/2;
                AbstractDungeon.actionManager.addToTop(new DamageAction(this.targetMonster, this.info, AttackEffect.SLASH_DIAGONAL));
                //this.targetMonster.damage(this.info);

            }
        }
        //addToTop((AbstractGameAction)new DamageAction(this.target, this.info, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        this.isDone = true;
    }
}
