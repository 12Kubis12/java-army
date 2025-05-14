package parentPackage.ability;

import parentPackage.AbilityEffect;

public class InstantKill extends AbstractAbility {
    public InstantKill() {
        super(AbilityEffect.KILL, 1);
    }

    @Override
    public void trigger(int damage) {
        System.out.println(super.toString() + " and instantly kills " + this.abilityEffectValue +
                " enemy (soldier does not deal his damage equals to " + damage + ").");
    }
}
