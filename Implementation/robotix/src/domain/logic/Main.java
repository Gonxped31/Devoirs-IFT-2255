package domain.logic;
import java.io.IOException;

import java.util.Scanner;

import domain.logic.Menu.Menu;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();
        menu.menuPrincipale(scanner);
    }
}