package CinemaRoomManager;

import java.util.ArrayList;
import java.util.Scanner;

public class CinemaRoomManager {

    static int currentPrice = 0;
    static int potentialPrice = 0;

    public static void main(String[] args) {
        ArrayList<Integer> rowNumber = new ArrayList<>();
        ArrayList<Integer> seatNumber = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows: ");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row: ");
        int seatsPerRow = scanner.nextInt();

        if(rows * seatsPerRow < 61) {
            potentialPrice = rows * seatsPerRow * 10;
        } else {
            if(rows % 2 == 1) {
                potentialPrice = (int) ((int) (Math.floor(rows/2) * 10 * seatsPerRow) + (rows - (Math.floor(rows/2))) * 8 * seatsPerRow);
            } else {
                potentialPrice = (((rows/2) * 10) + ((rows/2) * 8)) * seatsPerRow;
            }
        }

        chooseOption(rows, seatsPerRow, rowNumber, seatNumber);
    }

    public static void chooseOption(int rows, int seatsPerRow,
                                    ArrayList<Integer> rowNumber, ArrayList<Integer> seatNumber) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int option = scanner.nextInt();

            if (option == 0) {
                return;
            } else if (option == 1) {
                displaySeats(rows, seatsPerRow, rowNumber, seatNumber);
            } else if (option == 2) {
                chooseSeat(rows, seatsPerRow, rowNumber, seatNumber);
            } else if (option == 3) {
                calculateStatistics(rows, seatsPerRow, rowNumber, seatNumber);
            } else {
                System.out.println("Wrong input!");
                chooseOption(rows, seatsPerRow, rowNumber, seatNumber);
            }
        } catch(Exception ex) {

        }
    }

    public static void chooseSeat(int rows, int seatsPerRow,
                                  ArrayList<Integer> rowNumber, ArrayList<Integer> seatNumber) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a row number: ");
        int i_rowNumber = scanner.nextInt();
        System.out.println("Enter a seat number in that row: ");
        int i_seatNumber = scanner.nextInt();

        for(int i = 0; i < rowNumber.size(); i++) {
            if(rowNumber.get(i) == i_rowNumber && seatNumber.get(i) == i_seatNumber) {
                System.out.println("That ticket has already been purchased!");
                chooseSeat(rows, seatsPerRow, rowNumber, seatNumber);
            }
        }

        if(i_rowNumber < 1 || i_rowNumber > rows || i_seatNumber < 1 || i_seatNumber > seatsPerRow) {
            System.out.println("Wrong input!");
            chooseSeat(rows, seatsPerRow, rowNumber, seatNumber);
        } else {
            seatNumber.add(i_seatNumber);
            rowNumber.add(i_rowNumber);
            calculatePrice(rows, seatsPerRow, rowNumber, seatNumber);
        }
    }

    public static void calculateStatistics(int rows, int seatsPerRow,
                                           ArrayList<Integer> rowNumber, ArrayList<Integer> seatNumber) {
        System.out.println("Number of purchased tickets: " + seatNumber.size());
        double percentage = ((double)seatNumber.size()/(rows*seatsPerRow)) * 100;
        System.out.println("Percentage: " + String.format("%.2f", percentage) + "%");
        System.out.println("Current income: $" + currentPrice);
        System.out.println("Total income: $" + potentialPrice);
        chooseOption(rows, seatsPerRow, rowNumber, seatNumber);
    }

    public static void displaySeats(int rows, int seatsPerRow,
                                    ArrayList<Integer> rowNumber, ArrayList<Integer> seatNumber) {
        System.out.println("Cinema:");
        System.out.print("  ");
        for(int i = 1; i <= seatsPerRow; i++) {
            System.out.print(i+" ");
        }
        System.out.print("\n");
        for(int j = 1; j <= rows; j++) {
            System.out.print(j);
            for(int i = 1; i <= seatsPerRow; i++) {
                if (rowNumber.contains(j) && seatNumber.contains(i))
                    System.out.print(" B");
                else
                    System.out.print(" S");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
        chooseOption(rows, seatsPerRow, rowNumber, seatNumber);
    }

    public static void calculatePrice(int rows, int seatsPerRow,
                                      ArrayList<Integer> rowNumber, ArrayList<Integer> seatNumber) {
        if(rows * seatsPerRow < 61) {
            System.out.println("Ticket price: $10\n");
            currentPrice += 10;
            potentialPrice = rows * seatsPerRow * 10;
        } else {
            if(rows % 2 == 1) {
                if(rowNumber.get(rowNumber.size()-1) <= (Math.floor(rows/2))) {
                    System.out.println("Ticket price: $10\n");
                    currentPrice += 10;
                }
                else {
                    System.out.println("Ticket price: $8\n");
                    currentPrice += 8;
                }
                potentialPrice = (int) ((int) (Math.floor(rows/2) * 10 * seatsPerRow) + (rows - (Math.floor(rows/2))) * 8 * seatsPerRow);
            } else {
                if(rowNumber.get(rowNumber.size()-1) <= (rows/2)) {
                    System.out.println("Ticket price: $10\n");
                    currentPrice += 10;
                }
                else {
                    System.out.println("Ticket price: $8\n");
                    currentPrice += 8;
                }
                potentialPrice = (((rows/2) * 10) + ((rows/2) * 8)) * seatsPerRow;
            }
        }
        chooseOption(rows, seatsPerRow, rowNumber, seatNumber);
    }

}