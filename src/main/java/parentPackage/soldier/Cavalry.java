package parentPackage.soldier;

import parentPackage.Ability;
import parentPackage.soldier.inteaction.Offensive;

public class Cavalry extends Soldier implements Offensive {
    public Cavalry(String name) {
        super(name, 4, 10);
        super.abilities.add(Ability.INCREASE_DAMAGE);
    }

    @Override
    public void attack() {
        super.dealDamage();
    }
}
