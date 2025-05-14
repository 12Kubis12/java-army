package parentPackage.ability;

import parentPackage.AbilityEffect;

public class IncreaseDamage extends AbstractAbility {
    public IncreaseDamage() {
        super(AbilityEffect.INCREASE_DAMAGE, 2);
    }

    @Override
    public void trigger(int damage) {
        System.out.println(super.toString() + " which increases his damage by " + this.abilityEffectValue
                + " and deals " + (damage + this.abilityEffectValue) + " damage.");
    }
}
