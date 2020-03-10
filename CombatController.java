package controllers;

import lib.ConsoleIO;
import models.classes.Character;
import models.monsters.Monster;

import java.util.Random;

public class CombatController {
    private int dmg;
    private int dmgTaken;
    private String combTxt;
    private Character player;
    private boolean isAlive;
    private boolean enemyAlive;
    private int HPots, MPots;
    private int dung;
    public int roll(int num, int sides) {
        int total = 0;
        for (int i = 0; i < num; i++) {
            Random ran = new Random();
            System.out.println("On Roll: " + (i + 1) + " You got " + ran);
            total += i;
        }
        return total;
    }

    public void encounter(Monster m){
        int hp = m.getCurrentHP();
        isAlive = player.getCurHp() > 0;
        enemyAlive = m.getCurrentHP() > 0;
        do{
            System.out.println("You've encounter a " + m.getName() + "\nit has " + m.getCurrentHP() + "HP!");
            System.out.println("\nYou have " + player.getExp());
            int input = ConsoleIO.promptForMenuSelection(FightMenu, false);
            switch (input){
                case 1:
                    dmgTaken = player.getCurHp() - m.attack(roll(2, 4));

                    int initiate = player.roll(1, 20);
                    if (initiate < 8) {
                        dmg = 0;
                        System.out.println("You've missed! The " + m.getName() + "took no damage!");
                        System.out.println("The " + m.getName() + " struck you for " + dmgTaken + " Damage!");
                        if (dmgTaken < 1) {
                            System.out.println("The Monster missed!");
                        }
                    }
                    if (initiate <= 18) {
                        int hit = player.attack(roll(3, 6));
                        hit *= 2;
//                        System.out.println("You hit a vital! The " + randSter() + " took " + hit + " Damage!");
                        System.out.println(m.takeDamage(hp,hit));
                    }
                    if (initiate > 8 && initiate < 18) {
                        int hit = player.attack(roll(3, 6));
                        System.out.println("You hit the " + m.getName() + " for " + hit + " Damage!");
                        System.out.println(m.takeDamage(hp,hit));
                    }
                    if (!isAlive){
                        charDied();
                        System.out.println("You have been slain, It was a valiant effort, but Alas You've been vanquished");
                        System.out.println("Along the way you've gained " + player.getExp() + "xp!");
                    }
                case 2:
                    if(player.getHPots() > 0 && player.getCurHp() < player.getHp()) {
                        int  heal =  player.getCurHp() + player.getCurHp() + 10;
                        player.setCurHp(heal);
                        if(player.getCurHp()> player.getHp())
                            player.setCurHp(player.getHp());
                        HPots--;
                    }else {
                        System.out.println("You have no potions / You're already full!");

                    }

                case 3:
                    if(player.getMPots() > 0){
                        int boost = player.attack(roll(3,6)+10);
                        System.out.println(m.takeDamage(hp,boost));
                        MPots--;
//                        System.out.println("Your boosted damage hit the " + randSter() + "for" + boost + "Damage!");
                    }
                case 4:
                    stats(player);
                default:
                    System.out.println("something happened, Try again!");
            }

        }while(enemyAlive);
        System.out.println("\n\nYou beat the " + m.getName() + "!\n");

    }
    public static String[] FightMenu = {"Attack!","Drink Health Potion", "Magic Boost!", "Character Stats"};


    public int attack(int roll){
        return roll;
    }
//    public Inventory inv(Inventory inv){
//        return inv;
//    }
    public String stats(Character character){
        return character.toString();
    }
    public void charDied(){
        for(int i=0; i < 50; i++){
            System.out.println("");
        }
    }
}

