package cards;
import actions.healing_fountain_misc_increase_action;
import actions.reaver_action;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static characters.mikan.PlayerColorEnum.EXAMPLE_GREEN;

public class healing_fountain_mi extends CustomCard
{
    public static final String ID = "mi_healing_fountain";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG_PATH = "mikanresources/images/cards_img/mi_healing_fountain.png";

    //=================================================
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = EXAMPLE_GREEN;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int COST = 1;
    private static final int COST_UPGRADE = 0;

    private static final int BASE_HEALING_AMOUNT = 2;
    private static final int HEALING_INCREASE_AMOUNT=1;
    //=================================================

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public healing_fountain_mi() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

        this.misc=BASE_HEALING_AMOUNT;//base healing
        this.magicNumber=this.baseMagicNumber=this.misc;
        this.exhaust=true;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //使用卡牌时触发的动作
        AbstractDungeon.actionManager.addToBottom(new healing_fountain_misc_increase_action(this.uuid, this.misc, HEALING_INCREASE_AMOUNT));
        AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, this.magicNumber));
    }

    public void applyPowers() {
        this.magicNumber=this.baseMagicNumber=this.misc;
        super.applyPowers();
        initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return new healing_fountain_mi();
    }


    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(COST_UPGRADE);
        }
    }





}
