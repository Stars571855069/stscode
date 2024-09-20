package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.ClashEffect;
import powers.ghost_vessel_power;

public class gathering_action extends AbstractGameAction {


    private boolean freeToPlayOnce = false;

    private AbstractPlayer p;

    private int energyOnUse = -1;
    private int ghost_energy = 2;
    public gathering_action(AbstractPlayer p, int amount,int ghost_energy_2, boolean freeToPlayOnce, int energyOnUse) {
        this.amount = amount;
        this.p = p;
        this.ghost_energy = ghost_energy_2;
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = AbstractGameAction.ActionType.SPECIAL;
        this.energyOnUse = energyOnUse;
    }

    @Override
    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1)
            effect = this.energyOnUse;
        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }
        if (effect > 0) {
            for (int i = 0; i < effect; i++) {
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.p, this.p, this.amount));
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.p, this.p, new ghost_vessel_power(this.p, this.ghost_energy), this.ghost_energy));
            }
            if (!this.freeToPlayOnce)
                this.p.energy.use(EnergyPanel.totalCount);
        }
        this.isDone = true;
    }
}
