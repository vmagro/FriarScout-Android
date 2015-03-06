package io.vinnie.friarscout.model;

/**
 * Created by vmagro on 3/3/15.
 */
public class Team {

    private int number;
    private String name;
    private String image;

    public Team(int number, String name, String image) {
        this.number = number;
        this.name = name;
        this.image = image;
    }

    public Team() {

    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
