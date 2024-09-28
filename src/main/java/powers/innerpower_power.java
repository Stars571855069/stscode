package powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class innerpower_power extends AbstractPower {

    public static final String POWER_ID = "mi_innerpower";
    private static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    private int cardscostreducedThisTurn = 0;

    public innerpower_power(AbstractCreature owner, int REDUCE_Amount){

        this.ID= POWER_ID;
        this.name=POWER_STRINGS.NAME;
        this.owner = owner;
        this.amount = REDUCE_Amount;
        String path128 = "mikanresources/images/power_img/mi_innerpower_128.png";
        String path48 = "mikanresources/images/power_img/mi_innerpower_48.png";
        //this.region48 = atlas.findRegion("48/" + fileName);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = POWER_STRINGS.DESCRIPTIONS[0];
        }
        else {
            this.description = POWER_STRINGS.DESCRIPTIONS[1] + this.amount + POWER_STRINGS.DESCRIPTIONS[2];
        }
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    public void atStartOfTurn() {
        this.cardscostreducedThisTurn = this.amount;
    }

    public void onCardDraw(AbstractCard card) {
        if (this.cardscostreducedThisTurn>0 && card.type!= AbstractCard.CardType.CURSE &&card.costForTurn>0 &&!card.freeToPlayOnce) {
            this.flash();
            this.cardscostreducedThisTurn--;
            card.setCostForTurn(card.costForTurn-1);
        }
    }
}
