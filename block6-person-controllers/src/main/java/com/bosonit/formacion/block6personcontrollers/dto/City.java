package com.bosonit.formacion.block6personcontrollers.dto;

public class City {
    private String name;
    private int citizensNum;

    public City(String name, int citizensNum) {
        this.name = name;
        this.citizensNum = citizensNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCitizensNum() {
        return citizensNum;
    }

    public void setCitizensNum(int citizensNum) {
        this.citizensNum = citizensNum;
    }
}
