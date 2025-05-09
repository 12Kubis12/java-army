package parentPackage.soldier.inteaction;

import parentPackage.Ability;
import parentPackage.AbilityEffect;

import java.util.Map;

public interface Triggerable {
    Map<AbilityEffect, Integer> triggerAbility(Ability ability);
}
