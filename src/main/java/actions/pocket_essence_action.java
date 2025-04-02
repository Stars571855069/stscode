package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ClashEffect;

public class pocket_essence_action extends AbstractGameAction {
    public AbstractPlayer p;
    public int heal;

    public pocket_essence_action(AbstractPlayer p,int heal) {
        //this.targetMonster=target;
        this.heal=heal;
        this.p=p;
    }

    @Override
    public void update() {
            if (p.currentHealth<=p.maxHealth/2) {
                //this.info.base=this.info.base/2;
                AbstractDungeon.actionManager.addToBottom(new HealAction(p,p,heal));
                //this.targetMonster.damage(this.info);
            }
        //addToTop((AbstractGameAction)new DamageAction(this.target, this.info, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        this.isDone = true;
    }
}
