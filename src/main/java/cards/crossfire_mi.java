package cards;

import actions.brutalstrike_action;
import actions.crossfire_action;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static characters.mikan.PlayerColorEnum.EXAMPLE_GREEN;

public class crossfire_mi extends CustomCard{

    public static final String ID = "mi_crossfire";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final CardColor COLOR = EXAMPLE_GREEN;
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG_PATH = "mikanresources/images/cards_img/mi_crossfire.png";

    private static final CardType TYPE = CardType.ATTACK;
    private static final int COST = 0;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int UPGRADE_DAMAGE = 3;
    private static final int ATTACK_DMG = 6;

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public crossfire_mi() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = ATTACK_DMG;
        this.damage = this.baseDamage;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //使用卡牌时触发的动作
        AbstractDungeon.actionManager.addToBottom(new crossfire_action(m, new DamageInfo(p,this.damage, DamageInfo.DamageType.NORMAL )));
        //AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        //AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)p, (AbstractPower)new VulnerablePower((AbstractCreature)m, 2, false), 2));
    }

    @Override

    public AbstractCard makeCopy() {
        return (AbstractCard)new crossfire_mi();
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            if (!m.isDeadOrEscaped() && m.getIntentBaseDmg() >= 0) {
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                break;
            }
        }
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_DAMAGE);
        }
    }

}