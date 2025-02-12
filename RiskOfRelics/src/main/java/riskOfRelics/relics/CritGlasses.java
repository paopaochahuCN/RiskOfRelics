package riskOfRelics.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import riskOfRelics.RiskOfRelics;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;


public class CritGlasses extends BaseRelic {


    public static final int AMOUNT = 5;
    public static final float MULT = 50;
    // ID, images, text.
    public static final String ID = RiskOfRelics.makeID("CritGlasses");
    private static final String IMAGENAME = "CritGlasses.png";

    public CritGlasses() {
        super(ID, IMAGENAME, RelicTier.COMMON, LandingSound.MAGICAL);
        counter = 0;
    }

    @Override
    public int onAttackToChangeDamage(DamageInfo info, int damageAmount) {

        counter++;
        if (counter == AMOUNT-1){
            this.beginLongPulse();
            AbstractDungeon.player.hand.refreshHandLayout();
        }
        if (counter >= AMOUNT){
            counter = 0;
            stopPulse();
            flash();
            this.addToBot(new RelicAboveCreatureAction(player, this));
            return Math.round(damageAmount+(damageAmount*(MULT/100)));

        }
        return super.onAttackToChangeDamage(info, damageAmount);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + AMOUNT + DESCRIPTIONS[1]+ (int)MULT + DESCRIPTIONS[2];
    }

}