package com.cinema.model;

public final class Seat {
    public enum Status { AVAILABLE, BOOKED }

    private final char row;
    private final int number;
    private Status status;
    private String customerName; // optional

    public Seat(char row, int number) {
        this.row = row;
        this.number = number;
        this.status = Status.AVAILABLE;
    }

    public String getId() {
        return String.format("%c%d", row, number);
    }

    public boolean isAvailable() {
        return status == Status.AVAILABLE;
    }

    public void book(String customerName) {
        if (!isAvailable()) {
            throw new IllegalStateException("Seat " + getId() + " is already booked.");
        }
        this.customerName = customerName;
        this.status = Status.BOOKED;
    }

    public Status getStatus() { return status; }
    public String getCustomerName() { return customerName; }
}
