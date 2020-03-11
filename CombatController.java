package controllers;

import lib.ConsoleIO;
import models.classes.Character;
import models.classes.Warrior;
import models.monsters.Monster;

import java.util.Random;

public class CombatController {
    private int dmg;
    private int dmgTaken;
    private String combTxt;
    private Character pl;
    private boolean isAlive = true;
    private boolean enemyAlive;
    private int HPots, MPots;
    private int dung;

//    Character ch = new Warrior("Jim",30,30,5,0,0,0,5,5,3);


    public int roll(int num, int sides) {
        int total = 0;
        for (int i = 0; i < num; i++) {
            Random ran = new Random();
            int rand = ran.nextInt(sides)+1;
            System.out.println("On Roll: " + (i+1) + " You got " + rand);
            total += rand;
        }
        return total;
    }

    public void encounter(Character pl, Monster m){
        this.pl = pl;
        int gain = pl.getExp();
        int xp = 10;
        System.out.println("You've encountered a " + m.getName() + "\nit has " + m.getCurrentHP() + "HP!");

        do{
        int hp = m.getCurrentHP();
        isAlive = pl.getCurHp() > 0;
        enemyAlive = m.getCurrentHP() > 0;

//            System.out.println("\nYou have " + pl.getExp());
            int initiate = roll(1, 20);
            int dmgTaken = m.attack(roll(1,9 ));

            int input = ConsoleIO.promptForMenuSelection(FightMenu, false);
            switch (input){
                case 1:
                    if (initiate < 8) {
                        dmg = 0;
                        System.out.println("You've missed! The " + m.getName() + " took no damage!");
                        System.out.println("The " + m.getName() + " struck you for " + dmgTaken + " Damage!");

                        if (dmgTaken < 1) {
                            System.out.println("The Monster missed!");

                            isAlive = pl.getCurHp() > 0;
                            enemyAlive = m.getCurrentHP() > 0;
                        }
                    } else {
                    if (initiate >= 18) {
                        int hit = pl.attack(roll(1, 1));
                        hit *= 2;
//                        System.out.println("You hit a vital! The " + randSter() + " took " + hit + " Damage!");
                        System.out.println(m.takeDamage(hp, hit));
                        System.out.println("The " + m.getName() + " struck you for " + dmgTaken + " Damage!");
                        hp -= dmgTaken;
                        isAlive = pl.getCurHp() > 0;
                        enemyAlive = m.getCurrentHP() > 0;
                        break;
                    } else {
                    if (initiate > 8 && initiate < 18) {
                        int hit = pl.attack(roll(3, 6));
                        System.out.println("You hit the " + m.getName() + " for " + hit + " Damage!");
                        System.out.println(m.takeDamage(hp,hit));
                        System.out.println("The " + m.getName() + " struck you for " + dmgTaken + " Damage!");
                        hp -= dmgTaken;
                        isAlive = pl.getCurHp() > 0;
                        enemyAlive = m.getCurrentHP() > 0;
                        break;
                    } else {
                        if (!isAlive) {
                            charDied();
                            System.out.println("You have been slain, It was a valiant effort, but Alas You've been vanquished");
                            System.out.println("Along the way you've gained " + pl.getExp() + "xp!");
                            break;
                        }
                    }
                    }
                    }
                    break;
                case 2:
                    if(pl.getHPots() > 0 && pl.getCurHp() < pl.getHp()) {
                        int  heal =  pl.getCurHp() + pl.getCurHp() + 10;
                        pl.setCurHp(heal);
                        if(pl.getCurHp()> pl.getHp())
                            pl.setCurHp(pl.getHp());
                        HPots--;

                    }else {
                        System.out.println("You have no potions / You're already full!\n\n");

                    }
                        break;
                case 3:
                    if(pl.getMPots() > 0){
                        int boost = pl.attack(roll(3,6)+10);
                        System.out.println(m.takeDamage(hp,boost));
                        MPots--;
                        System.out.println("The " + m.getName() + " struck you for " + dmgTaken + " Damage!");
                        hp -= dmgTaken;
                        isAlive = pl.getCurHp() > 0;
                        enemyAlive = m.getCurrentHP() > 0;

//                        System.out.println("Your boosted damage hit the " + randSter() + "for" + boost + "Damage!");
                    } else if(pl.getMPots() < 1){
                        System.out.println("You don't have any Mana potions!\n\n");
                    }
                    break;
                case 4:
                    stats();
                    break;
//                default:
//                    System.out.println("something happened, Try again!");
            }

        }while(enemyAlive);
        System.out.println("\n\nYou beat the " + m.getName() + "!\n");
         gain += xp;
        System.out.println("You've gained " + gain + " exp!");

    }
    public static String[] FightMenu = {"Attack!","Drink Health Potion", "Magic Boost!", "Character Stats"};


    public int attack(int roll){
        return roll;
    }
//    public Inventory inv(Inventory inv){
//        return inv;
//    }
    public void stats(){
        System.out.println(pl.toString());
//        System.out.println(pl.getName() + "HP: " + pl.getHp() + "\nXP: " + pl.getExp()
//        + "Health Potions: " + pl.getHPots() + "Mana Potions: " + pl.getMPots()+pl.getClass());

    }
    public void charDied(){
        for(int i=0; i < 50; i++){
            System.out.println("");
        }
    }
}
