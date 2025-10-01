package com.cinema;

import com.cinema.model.Hall;
import com.cinema.model.Movie;
import com.cinema.service.BookingService;

import java.util.Scanner;

public final class Main {
    private Main() {}

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        System.out.println("=== Cinema Booking System ===");

        System.out.print("Enter movie title: ");
        var title = scanner.nextLine().trim();
        while (title.isEmpty()) {
            System.out.print("Title can't be empty. Enter movie title: ");
            title = scanner.nextLine().trim();
        }

        System.out.print("Enter how many seating rows do you want for the cinema (e.g. 5): ");
        int rows = readPositiveInt(scanner);
        System.out.print("Enter how many seating columns do you want for the cinema (e.g. 8): ");
        int cols = readPositiveInt(scanner);

        var movie = new Movie(title);
        var hall = new Hall(rows, cols);
        var service = new BookingService(movie, hall, scanner);

        System.out.println();
        System.out.println("Setup complete. Starting interactive booking session.\n");

        service.runInteractive();
        scanner.close();
    }

    private static int readPositiveInt(Scanner scanner)
    {
        while (true)
        {
            var line = scanner.nextLine().trim();
            try
            {
                int v = Integer.parseInt(line);
                if (v <= 0)
                {
                    System.out.print("Please enter a positive integer: ");
                    continue;
                }
                return v;
            } catch (NumberFormatException e) {
                System.out.print("Invalid number. Try again: ");
            }
        }
    }
}
