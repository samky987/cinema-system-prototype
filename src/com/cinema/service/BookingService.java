package com.cinema.service;

import com.cinema.model.Hall;
import com.cinema.model.Movie;
import com.cinema.model.Seat;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public final class BookingService {
    private final Movie movie;
    private final Hall hall;
    private final Scanner scanner;

    public BookingService(Movie movie, Hall hall, Scanner scanner)
    {
        this.movie = movie;
        this.hall = hall;
        this.scanner = scanner;
    }

    public void runInteractive()
    {
        printHeader();
        while (true) {
            printSeatingMap();
            printMenu();
            var choice = scanner.nextLine().trim();
            switch (choice)
            {
                case "1" -> handleBookSeat();
                case "2" -> handleShowDetails();
                case "q", "Q" -> {
                    System.out.println("Exiting. Goodbye!"); return;
                }
                default -> System.out.println("Unknown option. Please try again.");
            }
        }
    }

    private void printHeader()
    {
        System.out.println("Movie: " + movie.getTitle());
        System.out.println("Hall size: " + hall.getRows() + " rows x " + hall.getCols() + " cols\n");
    }

    private void printMenu()
    {
        System.out.println();
        System.out.println("Options:");
        System.out.println("  1) Book a seat");
        System.out.println("  2) Show booking details for a seat");
        System.out.println("  Q) Quit");
        System.out.print("Choose: ");
    }

    private void handleBookSeat()
    {
        System.out.print("Enter seat id to book (e.g. A1): ");
        var seatId = scanner.nextLine().trim();
        Optional<Seat> sOpt = hall.findSeat(seatId);
        if (sOpt.isEmpty()) {
            System.out.println("Seat not found: " + seatId);
            return;
        }
        Seat seat = sOpt.get();
        if (!seat.isAvailable()) {
            System.out.println("Seat " + seat.getId() + " is already booked by " + seat.getCustomerName());
            return;
        }
        System.out.print("Enter customer name: ");
        var name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty."); return;
        }
        try {
            seat.book(name);
            System.out.println("Successfully booked " + seat.getId() + " for " + name);
        } catch (IllegalStateException e) {
            System.out.println("Could not book seat: " + e.getMessage());
        }
    }

    private void handleShowDetails()
    {
        System.out.print("Enter seat id to inspect (e.g. A1): ");
        var seatId = scanner.nextLine().trim();
        Optional<Seat> sOpt = hall.findSeat(seatId);
        if (sOpt.isEmpty()) {
            System.out.println("Seat not found: " + seatId);
            return;
        }
        Seat seat = sOpt.get();
        System.out.println("Seat " + seat.getId() + " status: " + seat.getStatus());
        if (!seat.isAvailable()) {
            System.out.println("  Booked by: " + seat.getCustomerName());
        }
    }

    private void printSeatingMap()
    {
        List<Seat> seats = hall.getAllSeats();
        int cols = hall.getCols();
        System.out.println("Seating map (X = booked):");
        for (int i = 0; i < seats.size(); i++) {
            Seat s = seats.get(i);
            String symbol = s.isAvailable() ? "" : "X";
            System.out.printf("%s(%s) ", s.getId(), symbol);
            if ((i + 1) % cols == 0) System.out.println();
        }
    }
}
