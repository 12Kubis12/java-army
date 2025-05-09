package parentPackage.army;

import parentPackage.Ability;
import parentPackage.AbilityEffect;
import parentPackage.Command;
import parentPackage.soldier.inteaction.Defensive;
import parentPackage.soldier.inteaction.Offensive;
import parentPackage.soldier.Soldier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Army {
    private final String name;
    private final List<Soldier> soldiers;
    private final List<Ability> availableAbilities;
    private int dealtDamagePerRound;
    private int dealtInstantKillsPerRound;
    private int blockedDamagePerRound;
    private int blockedInstantKillsPerRound;

    public Army(String name) {
        this.name = name;
        this.soldiers = new ArrayList<>();
        this.availableAbilities = new ArrayList<>();
        this.dealtDamagePerRound = 0;
        this.dealtInstantKillsPerRound = 0;
        this.blockedDamagePerRound = 0;
        this.blockedInstantKillsPerRound = 0;
    }

    public void giveCommand(Command command) {
        switch (command) {
            case ATTACK -> this.attack();
            case DEFEND -> this.defend();
            case REPORT -> this.report();
            case TRIGGER_SPECIAL_ABILITY -> this.reportAbilities();
        }
    }

    public void report() {
        printHeader(Command.REPORT.toString());
        System.out.println(this.name + ":");
        for (Soldier soldier : this.soldiers) {
            soldier.report();
        }
        this.printFooter();
    }

    public void attack() {
        printHeader(Command.ATTACK.toString());
        for (Soldier soldier : this.soldiers) {
            if (soldier instanceof Offensive) {
                ((Offensive) soldier).attack();
                this.dealtDamagePerRound += soldier.getDamage();
            }
        }
        this.printRoundProperties();
        this.printFooter();
    }

    public void defend() {
        printHeader(Command.DEFEND.toString());
        for (Soldier soldier : this.soldiers) {
            if (soldier instanceof Defensive) {
                ((Defensive) soldier).defend();
                this.dealtDamagePerRound += soldier.getDamage();
            }
        }
        this.printRoundProperties();
        this.printFooter();
    }

    public void reportAbilities() {
        printHeader(Command.TRIGGER_SPECIAL_ABILITY.toString());
        System.out.println("Choose from available special abilities:");
        for (int i = 0; i < this.availableAbilities.size(); i++) {
            System.out.println((i + 1) + " -> " + availableAbilities.get(i).toString());
        }
        this.printFooter();
    }

    public void triggerAbility(int line) {
        Ability ability = this.availableAbilities.get(line - 1);
        this.printHeader(ability.toString());
        for (Soldier soldier : this.soldiers) {
            if (soldier.getAbilities().contains(ability)) {
                Map<AbilityEffect, Integer> abilitiesEffects = soldier.triggerAbility(ability);
                this.dealtDamagePerRound += abilitiesEffects.get(AbilityEffect.DAMAGE);
                this.dealtInstantKillsPerRound += abilitiesEffects.get(AbilityEffect.KILL);
                this.blockedDamagePerRound += abilitiesEffects.get(AbilityEffect.BLOCKED_DAMAGE);
                this.blockedInstantKillsPerRound += abilitiesEffects.get(AbilityEffect.BLOCKED_KILL);
                soldier.removeAbility(ability);
            }
        }
        this.printRoundProperties();
        this.printFooter();
    }

    private void printHeader(String word) {
        int repeatValue = (174 - word.length() - 2) / 2;
        System.out.println("-".repeat(repeatValue) + " " + word + " " + "-".repeat(repeatValue));
        System.out.println();
    }

    private void printFooter() {
        System.out.println("-".repeat(174));
        System.out.println();
    }

    private void printRoundProperties() {
        System.out.println();
        StringBuilder notification = new StringBuilder(this.name);
        if (this.dealtDamagePerRound > 0) {
            notification.append(" dealt ").append(this.dealtDamagePerRound).append(" damage.");
        } else if (this.dealtInstantKillsPerRound > 0) {
            notification.append(" instantly killed ").append(this.dealtInstantKillsPerRound).append(" enemies.");
        } else if (this.blockedDamagePerRound > 0) {
            notification.append(" blocked ").append(this.blockedDamagePerRound).append(" damage.");
        } else if (this.blockedInstantKillsPerRound > 0) {
            notification.append(" blocked ").append(this.blockedInstantKillsPerRound).append(" instant kills.");
        }
        System.out.println(notification);
    }

    public void addSoldiers(Soldier soldier) {
        this.soldiers.add(soldier);
        this.updateAbilities();
    }

    public void updateAbilities() {
        for (Soldier soldier : this.soldiers) {
            for (Ability specialAbility : soldier.getAbilities()) {
                if (!this.availableAbilities.contains(specialAbility)) {
                    this.availableAbilities.add(specialAbility);
                }
            }
        }
    }

    public void evaluateLosses(int kills, int damage) {
        while (!this.soldiers.isEmpty() && (damage > 0 || kills > 0)) {
            if (kills != 0) {
                if (this.blockedInstantKillsPerRound == 0) {
                    this.killSoldier();
                } else {
                    this.blockedInstantKillsPerRound--;
                }
                kills--;
            } else {
                if (this.blockedDamagePerRound <= 0) {
                    Soldier tempSoldier = this.soldiers.get(0);
                    int tempHealth = tempSoldier.getHealth();
                    tempSoldier.setHealth(tempHealth - damage);
                    if (tempSoldier.getHealth() <= 0) {
                        this.killSoldier();
                    }
                    damage -= tempHealth;
                } else {
                    int tempDamage = damage;
                    damage -= this.blockedDamagePerRound;
                    this.blockedDamagePerRound -= tempDamage;
                }
            }
        }
    }

    private void killSoldier() {
        this.soldiers.remove(0);
        this.availableAbilities.clear();
        this.updateAbilities();
    }

    public void resetRoundProperties() {
        this.dealtDamagePerRound = 0;
        this.dealtInstantKillsPerRound = 0;
        this.blockedDamagePerRound = 0;
        this.blockedInstantKillsPerRound = 0;
        for (Soldier soldier : this.soldiers) {
            soldier.resetAbilitiesEffects();
        }
    }

    public List<Soldier> getSoldiers() {
        return this.soldiers;
    }

    public String getName() {
        return name;
    }

    public int getDealtDamagePerRound() {
        return dealtDamagePerRound;
    }

    public int getDealtInstantKillsPerRound() {
        return dealtInstantKillsPerRound;
    }
}
