package cards;
import actions.press_the_attack_action;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static characters.mikan.PlayerColorEnum.EXAMPLE_GREEN;

public class pocket_essence_mi extends CustomCard
{
    public static final String ID = "mi_pocket_essence";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG_PATH = "mikanresources/images/cards_img/mi_pocket_essence.png";

    //=================================================
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = EXAMPLE_GREEN;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int COST = 2;

    private static final int DRAW_AMOUNT = 2;
    private static final int DRAW_AMOUNT_UPGRADE = 1;
    private static final int HEAL_AMOUNT = 1;
    //=================================================

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public pocket_essence_mi() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        //添加基础防御标签和将格挡设为5
        this.magicNumber=this.baseMagicNumber = DRAW_AMOUNT;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //使用卡牌时触发的动作
        AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, HEAL_AMOUNT));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(this.magicNumber));
        updateCost(-1);
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return new pocket_essence_mi();
    }


    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            //更改名字和提高3点格挡
            upgradeName();
            upgradeMagicNumber(DRAW_AMOUNT_UPGRADE);
        }
    }





}
