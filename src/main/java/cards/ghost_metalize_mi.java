package cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import powers.ghost_metalize_power;
import powers.voidform_power;

import static characters.mikan.PlayerColorEnum.EXAMPLE_GREEN;

public class ghost_metalize_mi extends CustomCard {

    public static final String ID = "mi_ghost_metalize";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final CardColor COLOR = EXAMPLE_GREEN;
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG_PATH = "mikanresources/images/cards_img/mi_ghost_metalize.png";

    private static final int COST = 2;
    private static final CardType TYPE = CardType.POWER;

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int GHOST_ENERGY_GAIN = 1;
    private static final int GHOST_ENERGY_GAIN_UPGRADE = 1;
    private static final int METALIZE_GAIN = 6;

    public ghost_metalize_mi() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber=this.baseMagicNumber= GHOST_ENERGY_GAIN;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new MetallicizePower(p,METALIZE_GAIN)));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new ghost_metalize_power(p,this.magicNumber)));
    }
    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return new ghost_metalize_mi();
    }

    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            upgradeName();
            //upgradeMagicNumber();
            upgradeMagicNumber(GHOST_ENERGY_GAIN_UPGRADE);
        }
    }
}
