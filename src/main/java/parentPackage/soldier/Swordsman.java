package parentPackage.soldier;

import parentPackage.ability.BlockDamage;
import parentPackage.ability.BlockInstantKill;
import parentPackage.ability.DoubleDamage;
import parentPackage.ability.IncreaseDamage;
import parentPackage.soldier.inteaction.Defensive;
import parentPackage.soldier.inteaction.Offensive;


public class Swordsman extends Soldier implements Offensive, Defensive {
    public Swordsman(String name) {
        super(name, 2, 9);
        super.abilities.add(new DoubleDamage());
        super.abilities.add(new IncreaseDamage());
        super.abilities.add(new BlockDamage());
        super.abilities.add(new BlockInstantKill());
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
