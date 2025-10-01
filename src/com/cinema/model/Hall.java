package com.cinema.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public final class Hall {
    private final int rows;
    private final int cols;
    private final List<Seat> seats;

    public Hall(int rows, int cols)
    {
        this.rows = rows;
        this.cols = cols;
        this.seats = new ArrayList<>(rows * cols);
        initializeSeats();
    }

    private void initializeSeats() {
        for (int r = 0; r < rows; r++)
        {
            char rowChar = (char) ('A' + r);
            for (int c = 1; c <= cols; c++)
            {
                seats.add(new Seat(rowChar, c));
            }
        }
    }

    public List<Seat> getAllSeats() {
        return Collections.unmodifiableList(seats);
    }

    public Optional<Seat> findSeat(String seatId)
    {
        return seats.stream().filter(s -> s.getId().equalsIgnoreCase(seatId)).findFirst();
    }

    public int getRows() { return rows; }
    public int getCols() { return cols; }
}
