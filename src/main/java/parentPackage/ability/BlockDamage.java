package parentPackage.ability;

import parentPackage.AbilityEffect;

public class BlockDamage extends AbstractAbility{

    public BlockDamage() {
        super(AbilityEffect.BLOCKED_DAMAGE, 2);
    }

    @Override
    public void trigger(int damage) {
        System.out.println(super.toString() + " and blocks " + this.abilityEffectValue + " damage " +
                "(soldier does not deal his damage equals to " + damage + ").");

    }
}
