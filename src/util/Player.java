package util;

import java.io.Serializable;
import java.util.Objects;

public class Player implements Serializable {
    private String name;
    private String country;
    private int ageInYears;
    private double heightInMeters;
    private String club;
    private String position;
    private int number;
    private double weeklySalary;
    private double price;

    //Constructors
    public Player() { }
    Player(Player player) {
        this.name = player.getName();
        this.country = player.getCountry();
        this.ageInYears = player.getAgeInYears();
        this.heightInMeters = player.getHeightInMeters();
        this.club = player.getClub();
        this.position = player.getPosition();
        this.number = player.getNumber();
        this.weeklySalary = player.getWeeklySalary();
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public void setAgeInYears(int ageInYears) {
        this.ageInYears = ageInYears;
    }
    public void setHeightInMeters(double heightInMeters) {
        this.heightInMeters = heightInMeters;
    }
    public void setClub(String club) {
        this.club = club;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public void setWeeklySalary(double weeklySalary) {
        this.weeklySalary = weeklySalary;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    //Getters
    public String getName() {
        return name;
    }
    public String getCountry() {
        return country;
    }
    public int getAgeInYears() {
        return ageInYears;
    }
    public double getHeightInMeters() {
        return heightInMeters;
    }
    public String getClub() {
        return club;
    }
    public String getPosition() {
        return position;
    }
    public int getNumber() {
        return number;
    }
    public double getWeeklySalary() {
        return weeklySalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return getAgeInYears() == player.getAgeInYears() && Double.compare(player.getHeightInMeters(), getHeightInMeters()) == 0 && getNumber() == player.getNumber() && Double.compare(player.getWeeklySalary(), getWeeklySalary()) == 0 && getName().equals(player.getName()) && getCountry().equals(player.getCountry()) && getClub().equals(player.getClub()) && getPosition().equals(player.getPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCountry(), getAgeInYears(), getHeightInMeters(), getClub(), getPosition(), getNumber(), getWeeklySalary());
    }

    public double getPrice() {
        return price;
    }

    //Display
    public void displayInfo() {
        System.out.println("Details About Player:");
        System.out.println("Name: " + name);
        System.out.println("Country: " + country);
        System.out.println("Age: " + ageInYears + " years");
        System.out.println("Height: " + heightInMeters + " meters");
        System.out.println("Club: " + club);
        System.out.println("Position: " + position);
        System.out.println("Number: " + number);
        System.out.println("Weekly Salary: " + weeklySalary+ "\n");
    }

    public String showInfo() {
        String str = "Details About Player:\n";
        str += "Name: " + name + "\n";
        str += "Country: " + country + "\n";
        str += "Age: " + ageInYears + "\n";
        str += "Height: " + heightInMeters + "\n";
        str += "CLub " + club + "\n";
        str += "Position " + position + "\n";
        str += "Number: " + number + "\n";
        str += "Weekly Salary: " + weeklySalary;
        return str;
    }

    public String writeIntoFileFormat() {
        return name+","+ country+","+ageInYears+","+heightInMeters+","+club+","+position+","+number+","+weeklySalary;
    }
}
