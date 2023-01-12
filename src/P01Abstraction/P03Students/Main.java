package P01Abstraction.P03Students;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentSystem studentSystem = new StudentSystem();
        String[] input = scanner.nextLine().split(" ");

        while (!input.equals("Exit")) {
            studentSystem.ParseCommand(input);
            input = scanner.nextLine().split(" ");
        }
    }
}
