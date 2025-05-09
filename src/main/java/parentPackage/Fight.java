package parentPackage;

import parentPackage.army.Army;
import parentPackage.army.ArmyHolder;

import java.util.Scanner;

public class Fight {
    private final Scanner scanner = new Scanner(System.in);
    private final ArmyHolder armiesHolder = new ArmyHolder();
    private int round = 1;
    private int currentPlayer = 0;

    public Fight() {
        reportAllArmies();
        this.startFight();
    }

    private void reportAllArmies() {
        for (Army army : this.armiesHolder.getArmies()) {
            army.report();
        }
    }

    private void startFight() {
        printStartInfo();
        while (true) {
            System.out.println("Round: " + this.round);
            String line = scanInput("Command", printCommandOptions());
            if (line.equals(Command.TRIGGER_SPECIAL_ABILITY.getShortcut())) {
                line = scanInput("Ability", "Write number corresponding to the ability:");
            }

            if (line.equals("Q")) {
                break;
            }

            changePlayer();

            this.armiesHolder.checkArmiesAlive();
            if (this.armiesHolder.getArmies().size() < 2) {
                this.printEndInfo();
                break;
            }
        }
    }

    private String printCommandOptions() {
        StringBuilder info = new StringBuilder(this.armiesHolder.getArmies().get(this.currentPlayer).getName()
                + " choose command (");
        Command[] commands = Command.values();
        for (int i = 0; i < commands.length; i++) {
            info.append(commands[i].toString()).append(" -> '").append(commands[i].getShortcut()).append("'");
            if (i == commands.length - 1) {
                info.append("): ");
            } else {
                info.append(", ");
            }
        }
        return info.toString();
    }

    private void printStartInfo() {
        System.out.println("The fight has started. " + this.armiesHolder.getArmies().get(this.currentPlayer).getName()
                + " goes first. You can quit the fight anytime by writing 'q'");
        System.out.println();
    }

    private void printEndInfo() {
        if (this.armiesHolder.getArmies().size() == 1) {
            System.out.println(this.armiesHolder.getArmies().get(0).getName() + " wins!!!");
        } else {
            System.out.println("Draw!!!");
        }
    }

    private String scanInput(String scanType, String prompt) {
        String line;
        while (true) {
            try {
                System.out.println(prompt);
                line = this.scanner.nextLine().replaceAll("\\s", "").toUpperCase();
                if (line.equals("Q")) {
                    break;
                } else if (scanType.equals("Command")) {
                    this.armiesHolder.getArmies().get(this.currentPlayer).giveCommand(Command.getFromShortcut(line));
                    break;
                } else if (scanType.equals("Ability")) {
                    this.armiesHolder.getArmies().get(this.currentPlayer).triggerAbility(Integer.parseInt(line));
                    break;
                }
            } catch (Exception e) {
                System.out.println("Invalid Input. Try again!!!");
            }
        }
        return line;
    }

    private void changePlayer() {
        this.currentPlayer++;
        if (this.currentPlayer == this.armiesHolder.getArmies().size()) {
            this.currentPlayer = 0;
            this.round++;
            this.evaluateRound();
        }
    }

    private void evaluateRound() {
        this.armiesHolder.evaluateRound();
        this.reportAllArmies();
        this.armiesHolder.resetRoundProperties();
    }
}

