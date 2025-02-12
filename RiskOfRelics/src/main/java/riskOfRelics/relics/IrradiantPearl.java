package riskOfRelics.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import riskOfRelics.RiskOfRelics;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;


public class IrradiantPearl extends BaseRelic {


    public static final int AMOUNT = 30;
    public static final int AMOUNT2 = 2;
    // ID, images, text.
    public static final String ID = RiskOfRelics.makeID("IrradiantPearl");
    private static final String IMAGENAME = "IrradiantPearl.png";

    public IrradiantPearl() {
        super(ID, IMAGENAME, RelicTier.SPECIAL, LandingSound.MAGICAL);
        description =  getUpdatedDescription();
    }

    @Override
    public void obtain() {
        player.increaseMaxHp(Math.round(player.maxHealth * (((float) AMOUNT /100))), true);

        super.obtain();

    }

    @Override
    public void atBattleStart() {
        this.addToBot(new ApplyPowerAction(player,player, new StrengthPower(player, AMOUNT2)));
        this.addToBot(new ApplyPowerAction(player,player, new DexterityPower(player, AMOUNT2)));
        if (player.maxOrbs > 0) {
            this.addToBot(new ApplyPowerAction(player,player, new FocusPower(player, AMOUNT2)));
        }
        super.atBattleStart();
    }

    @Override
    public String getUpdatedDescription() {
        String desc = DESCRIPTIONS[0] + AMOUNT + DESCRIPTIONS[1] + AMOUNT2 + DESCRIPTIONS[2];
        if (player != null && player.maxOrbs > 0) {
            desc += DESCRIPTIONS[4];
        }else {
            desc += DESCRIPTIONS[3];
        }

        return desc;
    }

}