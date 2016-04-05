package com.keriackus.auction.data.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Calendar;

/**
 * Created by keriackus on 4/2/2016.
 */
@DatabaseTable
public class Item extends Entity {

    public Item(String category, String name, String description, double estimatePrice, Long startTime) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.estimatePrice = estimatePrice;
        this.startTime = startTime;
    }

    public static final String StartTimeFieldName = "startTime";
    public static final String InBidFieldName = "inBid";
    public static final String WonFieldName = "won";
    @DatabaseField(generatedId = true)
    long ID;

    @DatabaseField
    public String category;

    @DatabaseField
    public String name;

    @DatabaseField
    public String description;


    @DatabaseField
    public double estimatePrice;

    @DatabaseField
    public double biddingAmount;

    @DatabaseField
    public double otherMaximumBiddingAmount;
    @DatabaseField(columnName = StartTimeFieldName)
    public Long startTime;


    @DatabaseField(columnName = InBidFieldName)
    public boolean inBid;

    public Item() {

    }

    @Override
    public String toString() {
        return (name + "\n" + estimatePrice + "$");
    }

    public long getID() {
        return ID;
    }

    public boolean isWon() {
        if (isOutOfDate() && biddingAmount > otherMaximumBiddingAmount) {
            return true;
        }
        return false;
    }

    public boolean isOutOfDate() {
        return (startTime < Calendar.getInstance().getTimeInMillis());
    }
}
