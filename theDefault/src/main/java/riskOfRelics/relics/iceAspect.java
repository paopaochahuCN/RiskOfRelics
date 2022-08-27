package riskOfRelics.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.SlowPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import riskOfRelics.DefaultMod;
import riskOfRelics.powers.Burning;

public class iceAspect extends BaseRelic{


    public static final float SCALAR = 0.2f;
    public static final int AMOUNT = 2;
    // ID, images, text.
    public static final String ID = DefaultMod.makeID("iceAspect");
    private static final String IMAGENAME = "iceAspect.png";

    public iceAspect() {super(ID,IMAGENAME, RelicTier.SPECIAL, LandingSound.MAGICAL);}


    @Override
    public void atBattleStart() {
        flash();
        //apply slow to all enemies in the room
        AbstractRoom room = AbstractDungeon.getCurrRoom();
        for (AbstractMonster m : room.monsters.monsters) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, AbstractDungeon.player, new SlowPower(m, 1), 1));
        }

    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] ;
    }

}
