package actions;

import cards.reaper_mi;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.combat.SearingBlowEffect;
import powers.ghost_vessel_power;

public class ghost_vessel_reaper_upgrade_action extends AbstractGameAction {
    public int charges;
    public AbstractPlayer p;
    public ghost_vessel_reaper_upgrade_action(AbstractPlayer player, int relic_charges) {
        this.p=player;
        this.charges=relic_charges;
    }

    @Override
    public void update() {
        if (this.charges>=15){
            for (AbstractCard c : this.p.hand.group) {
                if(c instanceof reaper_mi &&c.canUpgrade()){
                    c.upgrade();
                    c.superFlash();
                }
            }
        }
        this.isDone = true;
    }
}
