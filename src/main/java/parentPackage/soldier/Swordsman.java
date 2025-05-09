package parentPackage.soldier;

import parentPackage.Ability;
import parentPackage.soldier.inteaction.Defensive;
import parentPackage.soldier.inteaction.Offensive;


public class Swordsman extends Soldier implements Offensive, Defensive {
    public Swordsman(String name) {
        super(name, 2, 9);
        super.abilities.add(Ability.DOUBLE_DAMAGE);
        super.abilities.add(Ability.INCREASE_DAMAGE);
        super.abilities.add(Ability.BLOCK_DAMAGE);
        super.abilities.add(Ability.BLOCK_INSTANT_KILL);
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
