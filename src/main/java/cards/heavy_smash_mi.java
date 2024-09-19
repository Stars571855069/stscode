package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

import static characters.mikan.PlayerColorEnum.EXAMPLE_GREEN;

public class heavy_smash_mi extends CustomCard{

    public static final String ID = "mi_heavy_smash";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final CardColor COLOR = EXAMPLE_GREEN;
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG_PATH = "mikanresources/images/cards_img/mi_heavy_smash.png";
    private static final int COST = 6;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final int ATTACK_DMG = 54;
    private static final int ATTACK_UPGRADE_DMG = 12;

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public heavy_smash_mi() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage=this.baseDamage= ATTACK_DMG;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //使用卡牌时触发的动作
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY)));
        AbstractDungeon.actionManager.addToBottom(new WaitAction(0.8F));
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));

    }

    @Override
    public void triggerWhenDrawn() {
        updateCost(-1);
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new heavy_smash_mi();
    }


    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(ATTACK_UPGRADE_DMG);
        }
    }

}