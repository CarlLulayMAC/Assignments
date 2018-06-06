package com.example.admin.myapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {

    String name;
    String city;
    String email;
    int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(String name, String city, String email, int age) {
        this.name = name;
        this.city = city;
        this.email = email;
        this.age = age;
    }


    protected Person(Parcel in) {
        name = in.readString();
        city = in.readString();
        email = in.readString();
        age = in.readInt();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(city);
        dest.writeString(email);
        dest.writeInt(age);
    }
}
