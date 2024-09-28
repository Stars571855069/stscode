package actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.vfx.combat.ClashEffect;

public class reaver_action extends AbstractGameAction {

    private AbstractPlayer sourceplayer;
    private AbstractMonster target;

    public reaver_action(AbstractMonster target, AbstractPlayer player) {
        this.target=target;
        this.sourceplayer = player;
    }

    @Override
    public void update() {

        if (this.target != null && this.target.hasPower("Strength")) {
            int strength_amount=this.target.getPower("Strength").amount;
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.sourceplayer,this.sourceplayer,new StrengthPower(this.sourceplayer,strength_amount)));
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.target,this.target,"Strength"));
        }

        if (this.target != null && this.target.hasPower("Dexterity")) {
            int Dexterity_amount=this.target.getPower("Dexterity").amount;
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.sourceplayer,this.sourceplayer,new DexterityPower(this.sourceplayer,Dexterity_amount)));
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.target,this.target,"Dexterity"));
        }

        if (this.target != null && this.target.hasPower("Metallicize")) {
            int Metallicize_amount=this.target.getPower("Metallicize").amount;
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.sourceplayer,this.sourceplayer,new MetallicizePower(this.sourceplayer,Metallicize_amount)));
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.target,this.target,"Metallicize"));
        }

        if (this.target != null && this.target.hasPower("Regeneration")) {
            int Regen_amount=this.target.getPower("Regeneration").amount;
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.sourceplayer,this.sourceplayer,new RegenPower(this.sourceplayer,Regen_amount)));
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.target,this.target,"Regeneration"));
        }

        if (this.target != null && this.target.hasPower("Plated Armor")) {
            int Plated_Armor_amount=this.target.getPower("Plated Armor").amount;
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.sourceplayer,this.sourceplayer,new PlatedArmorPower(this.sourceplayer,Plated_Armor_amount)));
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.target,this.target,"Plated Armor"));
        }

        if (this.target != null && this.target.hasPower("Thorns")) {
            int Thorns_amount=this.target.getPower("Thorns").amount;
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.sourceplayer,this.sourceplayer,new ThornsPower(this.sourceplayer,Thorns_amount)));
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.target,this.target,"Plated Armor"));
        }

        //if (this.target != null && this.target.hasPower("Malleable")) {
        //    int Malleable_amount=this.target.getPower("Malleable").amount;
        //    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.sourceplayer,this.sourceplayer,new MalleablePower(this.sourceplayer,Malleable_amount)));
        //    AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.target,this.target,"Malleable"));
        //}

        this.isDone = true;
    }
}
