package domain.logic;

import domain.logic.Menu.Menu;

import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();
        menu.menuPrincipale(scanner);
        scanner.close();
    }
}