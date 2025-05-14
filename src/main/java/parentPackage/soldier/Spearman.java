package parentPackage.soldier;

import parentPackage.ability.BlockDamage;
import parentPackage.ability.IncreaseDamage;
import parentPackage.soldier.inteaction.Defensive;

public class Spearman extends Soldier implements Defensive {

    public Spearman(String name) {
        super(name, 3, 8);
        super.abilities.add(new IncreaseDamage());
        super.abilities.add(new BlockDamage());
    }

    @Override
    public void defend() {
        super.dealDamage();
    }
}
