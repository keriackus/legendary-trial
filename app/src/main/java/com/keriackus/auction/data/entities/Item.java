package com.keriackus.auction.data.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by keriackus on 4/2/2016.
 */
@DatabaseTable
public class Item extends Entity {

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
    public String thumbPath;

    @DatabaseField
    public double estimatePrice;

    @DatabaseField
    public double biddingAmount;

    @DatabaseField
    public Long startTime;

    @DatabaseField(foreign = true)
    public Account auctioneer;

    @DatabaseField(columnName = WonFieldName)
    public boolean won;

    @DatabaseField(columnName = InBidFieldName)
    public boolean inBid;

    @Override
    public String toString() {
        return (name + "\n" + estimatePrice + "$");
    }

    public long getID() {
        return ID;
    }
}
