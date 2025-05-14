package parentPackage.soldier;

import parentPackage.ability.BlockDamage;
import parentPackage.ability.DoubleDamage;
import parentPackage.ability.InstantKill;
import parentPackage.soldier.inteaction.Defensive;
import parentPackage.soldier.inteaction.Offensive;

public class Archer extends Soldier implements Offensive, Defensive {
    public Archer(String name) {
        super(name, 3, 7);
        super.abilities.add(new InstantKill());
        super.abilities.add(new DoubleDamage());
        super.abilities.add(new BlockDamage());
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
