package cards;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.LoseBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.EntanglePower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;

import static characters.mikan.PlayerColorEnum.EXAMPLE_GREEN;

public class specterform_mi extends CustomCard
{
    public static final String ID = "mi_specterform";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG_PATH = "mikanresources/images/cards_img/mi_specterform.png";

    //=================================================
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = EXAMPLE_GREEN;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int COST = 3;
    private static final int UPGRADE_COST = 2;
    //private static final int BLOCK_AMT = -999999;
    private static final int INTANGIBLE_AMOUNT = 2;
    //=================================================

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public specterform_mi() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        //添加基础防御标签和将格挡设为5
        //this.tags.add(BaseModCardTags.BASIC_DEFEND);
        //this.baseBlock = BLOCK_AMT;
        this.baseMagicNumber = INTANGIBLE_AMOUNT;
        this.exhaust = true;
        //this.block=this.baseBlock;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //使用卡牌时触发的动作
        //AbstractDungeon.actionManager.addToBottom(new LoseBlockAction(p, p, 99999));
        //AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new EntanglePower(p), INTANGIBLE_AMOUNT));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p,-2), -2));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new IntangiblePlayerPower(AbstractDungeon.player, INTANGIBLE_AMOUNT), INTANGIBLE_AMOUNT));
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return new specterform_mi();
    }


    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            //更改名字和提高3点格挡
            upgradeName();
            upgradeBaseCost(UPGRADE_COST);
        }
    }





}
