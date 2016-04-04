package com.keriackus.auction.data.entities;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by keriackus on 4/4/2016.
 */
public class TestItem {

    public TestItem() {
    }

    @Test
    public void If_ItemWon_IsWon_ReturnTrue() {
        Item item = new Item();
        item.biddingAmount = 10;
        item.otherMaximumBiddingAmount = 9;
        item.startTime = Calendar.getInstance().getTimeInMillis() - 1;

        assertThat(item.isWon(), is(true));
    }

    @Test
    public void If_ItemNotWon_IsWon_ReturnFalse() {
        Item item = new Item();
        item.biddingAmount = 9;
        item.otherMaximumBiddingAmount = 10;
        item.startTime = Calendar.getInstance().getTimeInMillis() - 1;

        assertThat(item.isWon(), is(false));

    }

    @Test
    public void If_ItemIsOutOfDate_OutOfDate_ReturnTrue() {
        Item item = new Item();

        item.startTime = Calendar.getInstance().getTimeInMillis() - 1;

        assertThat(item.isOutOfDate(), is(true));

    }

    @Test
    public void If_ItemIsNot_OutOfDate_OutOfDate_ReturnFalse() {
        Item item = new Item();
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR, 1);
        item.startTime = c.getTimeInMillis();

        assertThat(item.isOutOfDate(), is(false));


    }
}
