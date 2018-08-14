package com.company.model;

import static java.lang.Thread.sleep;

public class Lift {
    private int floor;
    private int[] selectedFloors;
    private Direction direction;

    private DoorStatus doorStatus;


    public Lift(int floor) {
        this.floor = floor;
        this.doorStatus = DoorStatus.OPEN;
        this.setDirection(Direction.DING);
    }

    public void call(int actualFloor) throws InterruptedException {
        while (actualFloor != this.getFloor()){
            this.setDoorStatus(DoorStatus.CLOSE);
            if (this.getFloor() > actualFloor){
                this.floor--;
                this.setDirection(Direction.DOWN);
                System.out.println(this.toString());
            } else if (this.getFloor() < actualFloor){
                this.floor++;
                this.setDirection(Direction.UP);
                System.out.println(this.toString());
            }
            sleep(500);
        }
        this.setDoorStatus(DoorStatus.OPEN);
        this.setDirection(Direction.DING);
        System.out.println(this.toString());
    }


    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Lift{" +
                "floor=" + floor +
                ", doorStatus=" + doorStatus +
                ", direction=" + direction +
                '}';
    }

    public int[] getSelectedFloors() {
        return selectedFloors;
    }

    public void setSelectedFloors(int[] selectedFloors) {
        this.selectedFloors = selectedFloors;
    }

    public DoorStatus getDoorStatus() {
        return doorStatus;
    }

    public void setDoorStatus(DoorStatus doorStatus) {
        this.doorStatus = doorStatus;
    }
}
