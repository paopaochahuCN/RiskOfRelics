package riskOfRelics.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import riskOfRelics.RiskOfRelics;
import riskOfRelics.bosses.BulwarksAmbry;
import riskOfRelics.util.TextureLoader;

import java.util.ArrayList;

import static riskOfRelics.RiskOfRelics.makePowerPath;

public class Untargetable extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = RiskOfRelics.makeID("UntargetablePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // There's a fallback "missing texture" image, so the game shouldn't crash if you accidentally put a non-existent file.
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("Untargetable_84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("Untargetable_32.png"));

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if (info.type == DamageInfo.DamageType.HP_LOSS) {
            return damageAmount;
        }
        ArrayList<AbstractMonster> monsters = new ArrayList<>(AbstractDungeon.getCurrRoom().monsters.monsters);
        monsters.removeIf(AbstractCreature::isDeadOrEscaped);
        if (!monsters.isEmpty() && !(monsters.get(0) instanceof BulwarksAmbry)) {
            addToBot(new DamageAction(AbstractDungeon.getRandomMonster((AbstractMonster) this.owner), new DamageInfo(null, damageAmount, DamageInfo.DamageType.THORNS)));
        }

        return 0;
    }

    public Untargetable(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;

        type = NeutralPowertypePatch.NEUTRAL;
        isTurnBased = false;

        // We load those txtures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public AbstractPower makeCopy() {
        return new Untargetable(owner, source, amount);
    }
}
