package riskOfRelics.artifacts;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import riskOfRelics.RiskOfRelics;
import riskOfRelics.powers.SpitePower;

public class SpiteArt {
    public static void onMonsterDeath() {
        if (RiskOfRelics.ActiveArtifacts.contains(RiskOfRelics.Artifacts.SPITE)){
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new SpitePower(AbstractDungeon.player, 30), 3));
        }
    }

}
