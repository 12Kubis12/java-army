package parentPackage.ability;

import parentPackage.AbilityEffect;

import java.util.Objects;

public abstract class AbstractAbility {

    protected final AbilityEffect abilityEffectName;
    protected final int abilityEffectValue;

    public AbstractAbility(AbilityEffect abilityEffect, int abilityEffectValue) {
        this.abilityEffectName = abilityEffect;
        this.abilityEffectValue = abilityEffectValue;
    }

    public abstract void trigger(int damage);

    public AbilityEffect getAbilityEffectName() {
        return abilityEffectName;
    }

    public int getAbilityEffectValue() {
        return abilityEffectValue;
    }

    @Override
    public String toString() {
        String[] words = this.getClass().getSimpleName().split("(?=\\p{Upper})");
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            name.append(words[i].toUpperCase());
            if (i < words.length - 1) {
                name.append("_");
            }
        }
        return name.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AbstractAbility that = (AbstractAbility) o;
        return abilityEffectValue == that.abilityEffectValue && abilityEffectName == that.abilityEffectName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(abilityEffectName, abilityEffectValue);
    }
}
