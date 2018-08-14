package com.company;

import com.company.model.Direction;
import com.company.model.DoorStatus;
import com.company.model.Lift;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LiftTest {
    public static int MAX_FLOORS = 30;
    public static Lift lift;
    private static Random random = new Random();

    @BeforeAll
    public static void initializeLift(){
        lift = new Lift(random.nextInt(MAX_FLOORS));
    }

    @Test
    public void callInitLift(){
        assertEquals(lift.getDoorStatus(), DoorStatus.OPEN);
        assertEquals(lift.getDirection(), Direction.DING);
    }

    @Test
    public void callFinishLift() throws InterruptedException {
        lift.call(random.nextInt(MAX_FLOORS));
        assertEquals(lift.getDoorStatus(), DoorStatus.OPEN);
        assertEquals(lift.getDirection(), Direction.DING);
    }

    @Test
    public void callUpLift() throws InterruptedException {
        lift.setFloor(0);
        new Thread(() -> {
            try {
                lift.call(MAX_FLOORS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        sleep(501);
        assertEquals(lift.getDoorStatus(), DoorStatus.CLOSE);
        assertEquals(lift.getDirection(), Direction.UP);
    }

    @Test
    public void callDownLift() throws InterruptedException {
        lift.setFloor(MAX_FLOORS);
        new Thread(() -> {
            try {
                lift.call(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        sleep(501);
        assertEquals(lift.getDoorStatus(), DoorStatus.CLOSE);
        assertEquals(lift.getDirection(), Direction.DOWN);
    }

    @Test
    public void callLift() throws InterruptedException {
        Random random = new Random();
        int actualFloor = random.nextInt(MAX_FLOORS);
        System.out.println(lift.toString());
        lift.call(actualFloor);
        assertEquals(actualFloor, lift.getFloor());
        assertEquals(DoorStatus.OPEN, lift.getDoorStatus());
        assertEquals(Direction.DING, lift.getDirection());
    }
}
