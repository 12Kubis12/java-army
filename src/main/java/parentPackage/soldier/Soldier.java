package parentPackage.soldier;

import parentPackage.ability.AbstractAbility;
import parentPackage.soldier.inteaction.Defensive;
import parentPackage.soldier.inteaction.Offensive;
import parentPackage.soldier.inteaction.Reportable;
import parentPackage.soldier.inteaction.Triggerable;

import java.util.ArrayList;
import java.util.List;

public abstract class Soldier implements Reportable, Triggerable {
    protected final String name;
    protected int health;
    protected int damage;
    protected List<AbstractAbility> abilities;

    public Soldier(String name, int damage, int health) {
        this.name = name;
        this.damage = damage;
        this.health = health;
        this.abilities = new ArrayList<>();
    }

    @Override
    public void report() {
        System.out.print("Soldier " + this.name + " here. Type - ");
        if (this instanceof Offensive && !(this instanceof Defensive)) {
            System.out.print("OFFENSIVE.");
        } else if (this instanceof Defensive && !(this instanceof Offensive)) {
            System.out.print("DEFENSIVE.");
        } else {
            System.out.print("ADAPTABLE.");
        }

        if (!this.abilities.isEmpty()) {
            System.out.print(" Abilities -> ");
            for (int i = 0; i < this.abilities.size(); i++) {
                System.out.print(this.abilities.get(i).toString());
                if (i == this.abilities.size() - 1) {
                    System.out.print(".");
                } else {
                    System.out.print(", ");
                }
            }
        }

        System.out.println(" (Remaining health: " + this.health + ")");
    }

    @Override
    public void triggerAbility(AbstractAbility ability) {
        System.out.print(this.getClass().getSimpleName() + " " + this.name + " uses ability ");
        ability.trigger(this.damage);
        this.removeAbility(ability);
    }

    protected void dealDamage() {
        System.out.println(this.getClass().getSimpleName() + " " + this.name + " deals " + this.damage + " damage.");
    }

    private void removeAbility(AbstractAbility ability) {
        this.abilities.remove(ability);
    }

    public int getDamage() {
        return damage;
    }

    public List<AbstractAbility> getAbilities() {
        return abilities;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
