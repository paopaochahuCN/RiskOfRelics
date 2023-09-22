package riskOfRelics.relics;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.vfx.FireBurstParticleEffect;
import riskOfRelics.RiskOfRelics;
import riskOfRelics.actions.KjarosBandAction;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;


public class KjarosBand extends BaseRelic {


    public static final int AMOUNT = 12;
    public static final int THRESHOLD = 24;
    // ID, images, text.
    public static final String ID = RiskOfRelics.makeID("KjarosBand");
    private static final String IMAGENAME = "KjarosBand.png";

    public KjarosBand() {
        super(ID, IMAGENAME, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (target != player && damageAmount > THRESHOLD) {
            flash();
            this.addToBot(new VFXAction(new FireBurstParticleEffect(target.hb.cX, target.hb.cY)));
            this.addToBot(new KjarosBandAction(AMOUNT));
        }

    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + THRESHOLD + DESCRIPTIONS[1] + AMOUNT + DESCRIPTIONS[2];
    }

}