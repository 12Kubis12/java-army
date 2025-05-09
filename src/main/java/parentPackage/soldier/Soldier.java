package parentPackage.soldier;

import parentPackage.Ability;
import parentPackage.AbilityEffect;
import parentPackage.soldier.inteaction.Defensive;
import parentPackage.soldier.inteaction.Offensive;
import parentPackage.soldier.inteaction.Reportable;
import parentPackage.soldier.inteaction.Triggerable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Soldier implements Reportable, Triggerable {
    protected final String name;
    protected int health;
    protected int damage;
    protected List<Ability> abilities;
    protected Map<AbilityEffect, Integer> abilityEffects;

    public Soldier(String name, int damage, int health) {
        this.name = name;
        this.damage = damage;
        this.health = health;
        this.abilities = new ArrayList<>();
        this.abilityEffects = new HashMap<>();
        this.resetAbilitiesEffects();
    }

    @Override
    public void report() {
        System.out.print("Soldier " + this.name + " here. Type - ");
        if (this instanceof Offensive && !(this instanceof Defensive)) {
            System.out.print("OFFENSIVE");
        } else if (this instanceof Defensive && !(this instanceof Offensive)) {
            System.out.print("DEFENSIVE");
        } else {
            System.out.print("ADAPTABLE");
        }

        System.out.print(". Abilities -> ");
        for (int i = 0; i < this.abilities.size(); i++) {
            System.out.print(this.abilities.get(i).toString());
            if (i == this.abilities.size() - 1) {
                System.out.print(". ");
            } else {
                System.out.print(", ");
            }
        }

        System.out.println("(Remaining health: " + this.health + ")");
    }

    @Override
    public Map<AbilityEffect, Integer> triggerAbility(Ability ability) {
        System.out.print(this.getClass().getSimpleName() + " " + this.name + " uses ability " + ability);
        switch (ability) {
            case INSTANT_KILL:
                this.abilityEffects.put(AbilityEffect.KILL, this.abilityEffects.get(AbilityEffect.KILL) + 1);
                System.out.println(" and instantly kills one enemy.");
                break;
            case DOUBLE_DAMAGE:
                this.abilityEffects.put(AbilityEffect.DAMAGE, this.abilityEffects.get(AbilityEffect.DAMAGE) + (this.damage * 2));
                System.out.println(" and deals double damage equals to "
                        + this.abilityEffects.get(AbilityEffect.DAMAGE) + ".");
                break;
            case INCREASE_DAMAGE:
                this.abilityEffects.put(AbilityEffect.DAMAGE, this.abilityEffects.get(AbilityEffect.DAMAGE) + (this.damage + 2));
                System.out.println(" which increases his damage by 2 and deals "
                        + this.abilityEffects.get(AbilityEffect.DAMAGE) + " damage.");
                break;
            case BLOCK_DAMAGE:
                this.abilityEffects.put(AbilityEffect.BLOCKED_DAMAGE, this.abilityEffects.get(AbilityEffect.BLOCKED_DAMAGE) + 2);
                System.out.println(" and blocks " + this.abilityEffects.get(AbilityEffect.BLOCKED_DAMAGE) + " damage.");
                break;
            case BLOCK_INSTANT_KILL:
                this.abilityEffects.put(AbilityEffect.BLOCKED_KILL, this.abilityEffects.get(AbilityEffect.BLOCKED_KILL) + 1);
                System.out.println(" and blocks " + this.abilityEffects.get(AbilityEffect.BLOCKED_KILL) + " instant kill.");
                break;
        }
        return this.abilityEffects;
    }

    protected void dealDamage() {
        System.out.println(this.getClass().getSimpleName() + " " + this.name + " deals " + this.damage + " damage.");
    }

    public void resetAbilitiesEffects() {
        this.abilityEffects.put(AbilityEffect.DAMAGE, 0);
        this.abilityEffects.put(AbilityEffect.KILL, 0);
        this.abilityEffects.put(AbilityEffect.BLOCKED_DAMAGE, 0);
        this.abilityEffects.put(AbilityEffect.BLOCKED_KILL, 0);
    }

    public void removeAbility(Ability ability) {
        this.abilities.remove(ability);
    }

    public int getDamage() {
        return damage;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
