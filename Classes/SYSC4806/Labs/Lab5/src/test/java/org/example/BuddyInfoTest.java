package org.example;

import org.example.model.BuddyInfo;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BuddyInfoTest {
    private BuddyInfo buddy;

    @Before
    public void setUp(){
        buddy = new BuddyInfo("Kareem", "111111");
    }

    @Test
    public void getName() {
        assertEquals("Kareem", buddy.getName());
    }

    @Test
    public void getPhoneNumber() {
        assertEquals("111111", buddy.getPhoneNumber());
    }

    @Test
    public void setName() {
        buddy.setName("Janet");
        assertEquals("Janet", buddy.getName());
    }

    @Test
    public void setPhoneNumber() {
        buddy.setPhoneNumber("222222");
        assertEquals("222222", buddy.getPhoneNumber());
    }
}