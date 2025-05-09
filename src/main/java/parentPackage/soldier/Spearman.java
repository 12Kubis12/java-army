package parentPackage.soldier;

import parentPackage.Ability;
import parentPackage.soldier.inteaction.Defensive;

public class Spearman extends Soldier implements Defensive {

    public Spearman(String name) {
        super(name, 3, 8);
        super.abilities.add(Ability.INCREASE_DAMAGE);
        super.abilities.add(Ability.BLOCK_DAMAGE);
    }

    @Override
    public void defend() {
        super.dealDamage();
    }
}
