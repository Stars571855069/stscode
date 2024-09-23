package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import powers.ghost_vessel_power;

public class catastrophe_action extends AbstractGameAction {

    private AbstractPlayer p;
    private int ghost_energy_divide;

    public catastrophe_action(AbstractPlayer p, int ghost_energy_divide) {
        this.ghost_energy_divide = ghost_energy_divide;
        this.p = p;
        this.actionType = ActionType.SPECIAL;
    }

    @Override
    public void update() {
        int power_count = AbstractDungeon.player.getPower("mi_ghost_vessel").amount;
        int strength_gain = power_count/2;
        if (strength_gain > 0) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.p,this.p,new StrengthPower(this.p,strength_gain)));
        }
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.p,this.p,new ghost_vessel_power(this.p,-power_count)));
        this.isDone = true;
    }
}
