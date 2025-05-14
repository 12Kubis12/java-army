package parentPackage.ability;

import parentPackage.AbilityEffect;

public class DoubleDamage extends AbstractAbility {
    public DoubleDamage() {
        super(AbilityEffect.MULTIPLY_DAMAGE, 2);
    }

    @Override
    public void trigger(int damage) {
        System.out.println(super.toString() + " and deals double damage equals to "
                + (damage * this.abilityEffectValue) + ".");
    }
}
