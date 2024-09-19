package cards;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import powers.ghost_vessel_power;

import static characters.mikan.PlayerColorEnum.EXAMPLE_GREEN;

public class delusion_mi extends CustomCard
{
    public static final String ID = "mi_delusion";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG_PATH = "mikanresources/images/cards_img/mi_delusion.png";

    //=================================================
    private static final CardType TYPE = CardType.POWER;
    private static final CardColor COLOR = EXAMPLE_GREEN;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int COST = 1;

    private static final int GHOST_ENERGY_AMOUNT = 2;
    private static final int GHOST_ENERGY_UPGRADE_AMOUNT = 1;
    //=================================================

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public delusion_mi() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        //添加基础防御标签和将格挡设为5
        this.magicNumber=this.baseMagicNumber = GHOST_ENERGY_AMOUNT;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //使用卡牌时触发的动作
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new ghost_vessel_power(p,this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return new delusion_mi();
    }


    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            //更改名字和提高3点格挡
            upgradeName();
            upgradeMagicNumber(GHOST_ENERGY_UPGRADE_AMOUNT);
        }
    }

}
