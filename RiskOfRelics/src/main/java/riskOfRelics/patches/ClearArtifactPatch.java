package riskOfRelics.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;

import static riskOfRelics.RiskOfRelics.*;

@SpirePatch2(clz = com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen.class,
        method = SpirePatch.CONSTRUCTOR,
        paramtypez = {boolean.class}
)
public class ClearArtifactPatch {
    @SpirePostfixPatch

    public static void Postfix(com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen __instance, boolean playBgm) {
        try {
            ActiveArtifacts.clear();
            for (String A: ModConfig.getString("EnabledArtifacts").split(",")) {
                Artifacts a = getArtifactfromName(A);
                if (UnlockedArtifacts.contains(a) && !ActiveArtifacts.contains(a)) {
                    ActiveArtifacts.add(a);
                }

            }
        } catch (Exception e) {
            ModConfig.setString("EnabledArtifacts","");
        }
    }
}
