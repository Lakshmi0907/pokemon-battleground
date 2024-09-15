package com.impact.pokemon;

import com.opencsv.bean.CsvBindByName;

public class Pokemon {
    @CsvBindByName(column="#")
    private int id;

    @CsvBindByName(column="Name")
    private String name;

    @CsvBindByName(column="Type")
    private String type;

    @CsvBindByName(column="Total")
    private int total;

    @CsvBindByName(column="HitPoints")
    private int hitPoints;

    @CsvBindByName(column="Attack")
    private int attack;

    @CsvBindByName(column="Defense")
    private int defense;

    @CsvBindByName(column="SpecialAttack")
    private int specialAttack;

    @CsvBindByName(column="SpecialDefense")
    private int specialDefense;

    @CsvBindByName(column="Speed")
    private int speed;

    @CsvBindByName(column="Generation")
    private int generation;

    @CsvBindByName(column="Legendary")
    private boolean isLegendary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(int specialAttack) {
        this.specialAttack = specialAttack;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public void setSpecialDefense(int specialDefense) {
        this.specialDefense = specialDefense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public boolean isLegendary() {
        return isLegendary;
    }

    public void setLegendary(boolean legendary) {
        isLegendary = legendary;
    }
}
