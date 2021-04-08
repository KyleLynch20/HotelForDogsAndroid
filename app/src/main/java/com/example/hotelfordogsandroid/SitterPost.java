package com.example.hotelfordogsandroid;

public class SitterPost {
    final private String amountPerHour, bio, breedSize, city, state, date, email, fencedBackYard,
            fullName, otherAnimals, phone, title;

    public SitterPost(String amountPerHour, String bio, String breedSize, String city, String state,
                      String date, String email, String fencedBackYard, String fullName, String otherAnimals,
                      String phone, String title) {
        this.amountPerHour = amountPerHour;
        this.bio = bio;
        this.breedSize = breedSize;
        this.city = city;
        this.state = state;
        this.date = date;
        this.email = email;
        this.fencedBackYard = fencedBackYard;
        this.fullName = fullName;
        this.otherAnimals = otherAnimals;
        this.phone = phone;
        this.title = title;
    }

    public String getAmountPerHour() {
        return amountPerHour;
    }

    public String getBio() {
        return bio;
    }

    public String getBreedSize() {
        return breedSize;
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

    public String getEmail() {
        return email;
    }

    public String getFencedBackYard() {
        return fencedBackYard;
    }

    public String getFullName() {
        return fullName;
    }

    public String getOtherAnimals() {
        return otherAnimals;
    }

    public String getPhone() {
        return phone;
    }

    public String getTitle() {
        return title;
    }
}
