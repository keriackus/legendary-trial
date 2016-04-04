package com.keriackus.auction.presentation.views.util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by keriackus on 4/4/2016.
 */



public class TestValidators {

    public TestValidators() {
    }

    @Test
    public void emailValidator_CorrectEmail_ReturnTrue() {
        assertThat(Validators.isValidEmail("abanoub.keriackus@outlook.com"), is(true));
    }

    @Test
    public void emailValidator_InCorrectEmail_ReturnsFalse() {
        assertThat(Validators.isValidEmail("abanoub keriackus@x.w"), is(false));
    }

    @Test
    public void passwordValidator_CorrectPassword_ReturnTrue() {
        assertThat(Validators.isValidPassword("QWERTY"), is(true));
    }

    @Test
    public void passwordValidator_InCorrectPassword_ReturnsFalse() {
        assertThat(Validators.isValidPassword("XYZ"), is(false));
    }
}
