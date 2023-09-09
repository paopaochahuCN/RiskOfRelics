package riskOfRelics.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;
import riskOfRelics.RiskOfRelics;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class BustlingFungus extends BaseRelic {

    public static boolean Canceledthisturn = false;
    public static boolean Canceledthisfight = false;
    public static final int HEAL_AMOUNT = 3;
    public static final int HEAL_AMOUNT_END = 10;
    public static final int MAX_USES = 5;

    // ID, images, text.
    public static final String ID = RiskOfRelics.makeID("BustlingFungus");
    private static final String IMAGENAME = "bustlingFungus.png";

    public BustlingFungus() {super(ID,IMAGENAME, RelicTier.COMMON, LandingSound.MAGICAL);}



    @Override
    public void atBattleStart() {
        Canceledthisturn = false;
        Canceledthisfight = false;
        counter = MAX_USES;
    }

    @Override
    public void atTurnStart() {
        beginLongPulse();
        Canceledthisturn = false;
        super.atTurnStart();
    }

    @Override
    public void onPlayerEndTurn() {

        if (!Canceledthisturn && counter > 0){
            flash();
            this.addToBot(new ApplyPowerAction(player, player, new RegenPower(player, HEAL_AMOUNT)));
            AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(player, this));
            counter--;
        }


        super.onPlayerEndTurn();
    }

    @Override
    public void onVictory() {

        if (!Canceledthisfight){
            flash();
        player.heal(player.maxHealth/HEAL_AMOUNT_END);
        }
        super.onVictory();
        counter = -1;
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
    Canceledthisturn = true;
    if  (c.type == AbstractCard.CardType.ATTACK){
        Canceledthisfight = true;
    }

    stopPulse();
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]+HEAL_AMOUNT+DESCRIPTIONS[1]+HEAL_AMOUNT_END+DESCRIPTIONS[2] + MAX_USES + DESCRIPTIONS[3];
    }

}
