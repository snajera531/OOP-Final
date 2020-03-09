package view;

import lib.ConsoleIO;
import models.Enemy;
import models.Inventory;
import models.Monster;

public class CombatController {
    private int dmg;
    private String combTxt;
    private Character character;
    private boolean isAlive;
    private boolean enemyAlive;

    public String encounter(Enemy ene){
       String encore = "You've encounterd a " + Monster.SPOOKIE + " He wants to eat your head!";
       return encore;
    }
    public void choose(String[] choice){
        int userChoice;
        userChoice = ConsoleIO.promptForMenuSelection(choice,false);

    }
    public int attack(int roll){
        return roll;
    }
    public Inventory inv(Inventory inv){
        return inv;
    }
    public String stats(Character character){
        return character.toString();
    }
}


