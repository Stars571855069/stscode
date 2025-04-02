package cards;

import actions.healing_fountain_misc_increase_action;
import actions.pontiff_eye_misc_increase_action;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import powers.ghost_vessel_power;

import static characters.mikan.PlayerColorEnum.EXAMPLE_GREEN;

public class pontiff_eye_mi extends CustomCard{

    public static final String ID = "mi_pontiff_eye";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final CardColor COLOR = EXAMPLE_GREEN;
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG_PATH = "mikanresources/images/cards_img/mi_ghosty_strike.png";
    private static final int COST = 1;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final int GHOST_VESSEL_CONSUME_AMOUNT = 5;

    private static final int ATTACK_DMG = 12;
    private static final int ATTACK_UPGRADE_DMG = 4;
    private static final int ATTACK_DMG_INCREASE_AMOUNT = 3;

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public pontiff_eye_mi() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

        //this.damage=this.baseDamage= ATTACK_DMG;
        //this.magicNumber=this.baseMagicNumber=GHOST_VESSEL_CONSUME_AMOUNT;
        this.misc=ATTACK_DMG;//base healing
        this.damage=this.baseDamage=this.misc;
        this.exhaust=true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //使用卡牌时触发的动作
        //AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p,p,"mi_ghost_vessel",GHOST_VESSEL_CONSUME_AMOUNT));
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ghost_vessel_power(AbstractDungeon.player,-this.magicNumber), -this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new pontiff_eye_misc_increase_action(this.uuid, this.misc, ATTACK_DMG_INCREASE_AMOUNT));
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        AbstractPower power=AbstractDungeon.player.getPower("mi_ghost_vessel");
        if (power.amount>=GHOST_VESSEL_CONSUME_AMOUNT){
            return true;
        }
        else{
            this.cantUseMessage = CARD_STRINGS.EXTENDED_DESCRIPTION[0];
            return false;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new pontiff_eye_mi();
    }


    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            upgradeName();
            //upgradeMagicNumber(WEAK_UPGRADE_AMOUNT);
            upgradeDamage(ATTACK_UPGRADE_DMG);
        }
    }

}