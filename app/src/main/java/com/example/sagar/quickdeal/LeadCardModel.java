package com.example.sagar.quickdeal;

/**
 * Created by sagar on 20/4/17.
 */

public class LeadCardModel {

    String name;
    String requirements;
    String days_update;

    public LeadCardModel(String name, String requirements, String days_update) {
        this.name = name;
        this.requirements = requirements;
        this.days_update = days_update;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getDays_update() {
        return days_update;
    }

    public void setDays_update(String days_update) {
        this.days_update = days_update;
    }
}
