package parentPackage.army;

import parentPackage.Command;
import parentPackage.ability.AbstractAbility;
import parentPackage.soldier.inteaction.Defensive;
import parentPackage.soldier.inteaction.Offensive;
import parentPackage.soldier.Soldier;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Army {
    private final String name;
    private final List<Soldier> soldiers;
    private final List<AbstractAbility> availableAbilities;
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
            AbstractAbility abstractAbility = availableAbilities.get(i);
            System.out.print((i + 1) + " -> " + abstractAbility.toString());
            switch (abstractAbility.getAbilityEffectName()) {
                case INCREASE_DAMAGE, BLOCKED_DAMAGE ->
                        System.out.print(" (value for each soldier with this ability is "
                                + abstractAbility.getAbilityEffectValue() + ")");
            }
            System.out.println();
        }
        this.printFooter();
    }

    public void triggerAbility(int line) {
        AbstractAbility ability = this.availableAbilities.get(line - 1);
        this.printHeader(ability.getClass().getSimpleName());
        for (Soldier soldier : this.soldiers) {
            if (soldier.getAbilities().contains(ability)) {
                soldier.triggerAbility(ability);
                switch (ability.getAbilityEffectName()) {
                    case INCREASE_DAMAGE:
                        this.dealtDamagePerRound += soldier.getDamage() + ability.getAbilityEffectValue();
                        break;
                    case MULTIPLY_DAMAGE:
                        this.dealtDamagePerRound += soldier.getDamage() * ability.getAbilityEffectValue();
                        break;
                    case KILL:
                        this.dealtInstantKillsPerRound += ability.getAbilityEffectValue();
                        break;
                    case BLOCKED_DAMAGE:
                        this.blockedDamagePerRound += ability.getAbilityEffectValue();
                        break;
                    case BLOCKED_KILL:
                        this.blockedInstantKillsPerRound += ability.getAbilityEffectValue();
                        this.dealtDamagePerRound += soldier.getDamage();
                        break;
                }
            }
        }
        this.printRoundProperties();
        this.printFooter();
        this.availableAbilities.remove(ability);
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
        if (this.blockedInstantKillsPerRound > 0) {
            notification.append(" blocked ").append(this.blockedInstantKillsPerRound)
                    .append(" instant kills and dealt ").append(this.dealtDamagePerRound).append(" damage.");
        } else if (this.blockedDamagePerRound > 0) {
            notification.append(" blocked ").append(this.blockedDamagePerRound).append(" damage.");
        } else if (this.dealtInstantKillsPerRound > 0) {
            notification.append(" instantly killed ").append(this.dealtInstantKillsPerRound).append(" enemies.");
        } else if (this.dealtDamagePerRound > 0) {
            notification.append(" dealt ").append(this.dealtDamagePerRound).append(" damage.");
        }
        System.out.println(notification);
    }

    public void addSoldiers(Soldier soldier) {
        this.soldiers.add(soldier);
        this.updateAbilities();
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

    public void updateAbilities() {
        for (Soldier soldier : this.soldiers) {
            for (AbstractAbility specialAbility : soldier.getAbilities()) {
                if (!this.availableAbilities.contains(specialAbility)) {
                    this.availableAbilities.add(specialAbility);
                }
            }
        }
        availableAbilities.sort(Comparator.comparing(AbstractAbility::toString));
    }

    public void resetRoundProperties() {
        this.dealtDamagePerRound = 0;
        this.dealtInstantKillsPerRound = 0;
        this.blockedDamagePerRound = 0;
        this.blockedInstantKillsPerRound = 0;
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

    public List<AbstractAbility> getAvailableAbilities() {
        return availableAbilities;
    }
}
