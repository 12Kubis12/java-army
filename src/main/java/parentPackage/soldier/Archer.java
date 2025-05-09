package parentPackage.soldier;

import parentPackage.Ability;
import parentPackage.soldier.inteaction.Defensive;
import parentPackage.soldier.inteaction.Offensive;

public class Archer extends Soldier implements Offensive, Defensive {
    public Archer(String name) {
        super(name, 3, 7);
        super.abilities.add(Ability.INSTANT_KILL);
        super.abilities.add(Ability.DOUBLE_DAMAGE);
        super.abilities.add(Ability.BLOCK_DAMAGE);
    }

    @Override
    public void attack() {
        super.dealDamage();
    }

    @Override
    public void defend() {
        super.dealDamage();
    }
}
