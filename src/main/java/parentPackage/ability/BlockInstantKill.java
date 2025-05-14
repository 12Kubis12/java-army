package parentPackage.ability;

import parentPackage.AbilityEffect;

public class BlockInstantKill extends AbstractAbility{
    public BlockInstantKill() {
        super(AbilityEffect.BLOCKED_KILL, 1);
    }

    @Override
    public void trigger(int damage) {
        System.out.println(super.toString() + ", blocks " + this.abilityEffectValue +
                " instant kill and deals his damage equals to " + damage + ".");
    }
}
