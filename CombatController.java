package view;

import lib.ConsoleIO;
import models.*;
import models.Character;

import java.util.Random;

public class CombatController {
    private int dmg;
    private int dmgTaken;
    private String combTxt;
    private Character player;
    private boolean isAlive;
    private boolean enemyAlive;

    public int roll(int num, int sides) {
        int total = 0;
        for (int i = 0; i < num; i++) {
            Random ran = new Random();
            System.out.println("On Roll: " + (i + 1) + " You got " + ran);
            total += i;
        }
        return total;
    }

    private Monster randSter(){
        int pick = new Random().nextInt(Monster.values().length);
        return Monster.values()[pick];
    }

    public void encounter(Enemy ene){

        isAlive = player.getCurHp() > 0;
        enemyAlive = ene.hp > 0;
        do{
            System.out.println("You've encounter a " + randSter() + "\nit has " + ene.hp + "HP!");
            System.out.println("\nYou have " + player.getExp());
            int input = ConsoleIO.promptForMenuSelection(FightMenu, false);
            switch (input){
                case 1:
                dmgTaken = player.getCurHp() - ene.attack(roll(2, 4));

                int initiate = player.accuracy(roll(1, 20));
                if (initiate < 8) {
                    dmg = 0;
                    System.out.println("You've missed! The " + randSter() + "took no damage!");
                    System.out.println("The " + randSter() + " struck you for " + dmgTaken + " Damage!");
                }
                if (initiate <= 18) {
                    int hit = player.attack(roll(3, 6));
                    hit *= 2;
                    System.out.println("You hit a vital! The " + randSter() + " took " + hit + " Damage!");
                    ene.hp -= hit;

                }
                if (initiate > 8 && initiate < 18) {
                    int hit = player.attack(roll(3, 6));
                    System.out.println("You hit the " + randSter() + " for " + hit + " Damage!");
                    ene.hp -= hit;
                }
                if (!isAlive){
                    charDied();
                    System.out.println("You have been slain, It was a valiant effort, but Alas You've been vanquished");
                    System.out.println("Along the way you've gained " + player.getExp() + "xp!");
                }
                case 2:
                    if(player.getPotions() > 0 && player.getCurHp() < player.getHp()) {
                         player.setCurHp(100);
                         if(player.getCurHp()> player.getHp())
                             player.setCurHp(player.getHp());

                    }else {
                        System.out.println("You have no potions / You're already full");

                    }
                   // viewInventory();

                case 3:
                    stats(player);
                }

        }while(enemyAlive);
    System.out.println("\n\nYou beat the " + randSter() + "!\n");

}
    public static String[] FightMenu = {"Attack!","Check Inventory", "Character Stats"};

    public int attack(int roll){
        return roll;
    }
    public Inventory inv(Inventory inv){
        return inv;
    }
    public String stats(Character character){
        return character.toString();
    }
    public void charDied(){
        for(int i=0; i < 50; i++){
            System.out.println("");
        }

    }
}
