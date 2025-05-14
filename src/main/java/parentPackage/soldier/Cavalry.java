package parentPackage.soldier;

import parentPackage.ability.IncreaseDamage;
import parentPackage.soldier.inteaction.Offensive;

public class Cavalry extends Soldier implements Offensive {
    public Cavalry(String name) {
        super(name, 4, 10);
        super.abilities.add(new IncreaseDamage());
    }

    @Override
    public void attack() {
        super.dealDamage();
    }
}
