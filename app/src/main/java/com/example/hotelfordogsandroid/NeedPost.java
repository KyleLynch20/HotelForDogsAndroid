package com.example.hotelfordogsandroid;

public class NeedPost {
    final private String amountOfTime, amountPerHour, animalFriendly,
            city, state, date, dogBreed, dogName, dogNeeds, email,
            fullName, phone, pottyTrained, title;

    public NeedPost(String amountOfTime, String amountPerHour, String animalFriendly,
                    String city, String state, String date, String dogBreed, String dogName,
                    String dogNeeds, String email, String fullName, String phone, String pottyTrained,
                    String title) {
        this.amountOfTime = amountOfTime;
        this.amountPerHour = amountPerHour;
        this.animalFriendly = animalFriendly;
        this.city = city;
        this.state = state;
        this.date = date;
        this.dogBreed = dogBreed;
        this.dogName = dogName;
        this.dogNeeds = dogNeeds;
        this.email = email;
        this.fullName = fullName;
        this.phone = phone;
        this.pottyTrained = pottyTrained;
        this.title = title;
    }

    public String getAmountOfTime() {
        return amountOfTime;
    }

    public String getAnimalFriendly() {
        return animalFriendly;
    }

    public String getAmountPerHour() {
        return amountPerHour;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getDate() {
        return date;
    }

    public String getDogBreed() {
        return dogBreed;
    }

    public String getDogName() {
        return dogName;
    }

    public String getDogNeeds() {
        return dogNeeds;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public String getPottyTrained() {
        return pottyTrained;
    }

    public String getTitle() {
        return title;
    }
}
