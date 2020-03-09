package models;

import interfaces.ICombatable;

import java.util.Random;

public class Character implements ICombatable{
    private String name;
    private int hp;
    private int curHp;
    private int damage;
    private int exp;
    private Inventory inv;
    private int potions;

    public Character() {

    }

    public Character(String name, int hp, int curHp, int damage, int exp, Inventory inv) {
        this.name = name;
        this.hp = hp;
        this.curHp = curHp;
        this.damage = damage;
        this.exp = exp;
        this.inv = inv;
    }

    public int getPotions() {
        return potions;
    }

    public void setPotions(int potions) {
        this.potions = potions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getCurHp() {
        return curHp;
    }

    public void setCurHp(int curHp) {
        this.curHp = curHp;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public Inventory getInv() {
        return inv;
    }

    public void setInv(Inventory inv) {
        this.inv = inv;
    }
    public int roll(int num, int sides) {
//        int x = num;
//        int y = sides;
        int total = 0;
        for (int i = 0; i < num; i++) {
            Random ran = new Random();
//            int n = ran.nextInt(sides) + 1;
            System.out.println("On Roll: " + (i + 1) + " You got " + ran);
            total += i;
        }
        return total;
    }
    @Override
    public int attack(int rollN) {
        rollN = roll(5,3);
        return rollN;
    }
    @Override
    public int accuracy(int roll) {
        roll = roll(5,3);
        int statBuff = roll + getExp() / 10 * 2;
        return roll;
    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", hp=" + hp +
                ", curHp=" + curHp +
                ", damage=" + damage +
                ", exp=" + exp +
                ", inv=" + inv +
                ", potions=" + potions +
                '}';
    }
}
