package parentPackage.army;

import parentPackage.soldier.Archer;
import parentPackage.soldier.Cavalry;
import parentPackage.soldier.Spearman;
import parentPackage.soldier.Swordsman;

import java.util.ArrayList;
import java.util.List;

public class ArmyHolder {
    private final List<Army> armies;

    public ArmyHolder() {
        this.armies = new ArrayList<>();
        this.createArmies();
    }

    private void createArmies() {
        Army attacker = new Army("Attacker");
        attacker.addSoldiers(new Cavalry("Cavalry 01"));
        attacker.addSoldiers(new Cavalry("Cavalry 02"));
        attacker.addSoldiers(new Swordsman("Swordsman 01"));
        attacker.addSoldiers(new Swordsman("Swordsman 02"));
        attacker.addSoldiers(new Archer("Archer 01"));
        attacker.addSoldiers(new Archer("Archer 02"));
        armies.add(attacker);

        Army defender = new Army("Defender");
        defender.addSoldiers(new Spearman("Spearman 01"));
        defender.addSoldiers(new Spearman("Spearman 02"));
        defender.addSoldiers(new Swordsman("Swordsman 01"));
        defender.addSoldiers(new Swordsman("Swordsman 02"));
        defender.addSoldiers(new Archer("Archer 01"));
        defender.addSoldiers(new Archer("Archer 02"));
        armies.add(defender);
    }

    public void evaluateRound() {
        for (int i = 0; i < this.armies.size(); i++) {
            this.armies.get(i).evaluateLosses(this.armies.get((i + 1) % 2).getDealtInstantKillsPerRound(),
                    this.armies.get((i + 1) % 2).getDealtDamagePerRound());
        }
    }

    public void checkArmiesAlive() {
        this.armies.removeIf(army -> army.getSoldiers().isEmpty());
    }

    public void resetRoundProperties() {
        for (Army army : this.armies) {
            army.resetRoundProperties();
        }
    }

    public List<Army> getArmies() {
        return armies;
    }
}
