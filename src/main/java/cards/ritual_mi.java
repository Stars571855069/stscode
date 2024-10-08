package cards;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import powers.ghost_vessel_power;

import static characters.mikan.PlayerColorEnum.EXAMPLE_GREEN;

public class ritual_mi extends CustomCard
{
    public static final String ID = "mi_ritual";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG_PATH = "mikanresources/images/cards_img/mi_ritual.png";

    //=================================================
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = EXAMPLE_GREEN;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final int COST = -2;
    private static final int GHOST_ENERGY_UPGRADE_AMOUNT = 2;
    private static final int GHOST_ENERGY_AMOUNT = 1;
    //=================================================

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public ritual_mi() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber=this.baseMagicNumber = GHOST_ENERGY_AMOUNT;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = CARD_STRINGS.EXTENDED_DESCRIPTION[0];
        return false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new ritual_mi();
    }

    public void triggerOnEndOfPlayerTurn(){
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ghost_vessel_power(AbstractDungeon.player, this.magicNumber), this.magicNumber));
    }


    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(GHOST_ENERGY_UPGRADE_AMOUNT);
        }
    }





}
