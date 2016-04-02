package com.keriackus.auction.data.entities;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by keriackus on 4/1/2016.
 */
@DatabaseTable
public class Account extends Entity {

    @DatabaseField(id = true)
    String email;

    @DatabaseField
    String password;


    public Account() {
        super();
    }

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
