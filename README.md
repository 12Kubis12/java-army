# Java Army ⚔️

It is a Java object-oriented programming (OOP) console application designed to simulate a simple battle between armies consisting of different types of soldiers with unique abilities.

---

## ✨ Features

- Various types of soldiers (defensive soldiers defend and offensive soldiers attack)
- Soldiers have special abilities (each ability can only be used once and every soldier who has that ability will use it)
- Commanding armies in rounds
- Command that makes everyone report themselves
- Two armies fighting each other, resulting in a draw or one survivor

---

## 🛠️ Technologies Used

- Java 17
- Maven

---

## 🚀 How to Run

1. Clone or download this repository:
   ```bash
   git clone https://github.com/12Kubis12/java-army.git

2. Build and run the application.

3. Follow the instructions in console.

4. In this file you can set up your armies by adding soldiers with unique names:

   [`src/main/java/parentPackage/army/ArmyHolder.java`](https://github.com/12Kubis12/java-army/blob/main/src/main/java/parentPackage/army/ArmyHolder.java)

    ```
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
    ```
