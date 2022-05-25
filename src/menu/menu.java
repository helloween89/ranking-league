package menu;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Constants.constants;
import core.processByConsole;
import core.processByFile;
import models.teams;

public class menu {

    private Scanner scanner = new Scanner(System.in);

    public void showMenu() {

        boolean stop = false;

        TreeMap<String, Integer> scoreResults = new TreeMap<>();
        List<teams> orderFinalResults = new ArrayList<>();
        List<String> badLines = new ArrayList<>();
        List<String> teams = new ArrayList<>();

        do {
            printMenu();
            String option = scanner.nextLine();
            int op = checkMenuOption(option);
            switch (op) {
                case 1:
                    processByConsole processConsole= new processByConsole(scoreResults, orderFinalResults, badLines, teams, scanner);
                    processConsole.typeMatch();
                    System.out.println("------------ Thanks to use ranking system ------------");
                    break;
                case 2:
                    processByFile processFile = new processByFile(scoreResults, orderFinalResults, badLines, teams, scanner);
                    processFile.processRecordByFile();
                    System.out.println("------------ Thanks to use ranking system ------------");
                    break;
                case 3:
                    System.out.println("------------ Thanks to use ranking system ------------");
                    stop = true;
                    break;
            }
        } while (!stop);

        scanner.close();

    }

    private int checkMenuOption(String option) {

        System.out.print("\033[H\033[2J");  
        System.out.flush(); 

        int operation = 0;
        Pattern pattern = Pattern.compile(constants.menuOption);
        Matcher matcher = pattern.matcher(option);
        boolean resultRegex = matcher.find();

        if (!resultRegex) {
            operation = 3;
        }

        if (option.equals("1")) {
            operation = 1;
        } else if (option.equals("2")) {
            operation = 2;
        }

        return operation;

    }

    private void printMenu() {

        System.out.println("-------Welcome to the ranking system, please select one of the next options-------");
        System.out.println("------- Type 1 to record team ranking manually -------");
        System.out.println("------- Type 2 to load and calculate using an already file in resources -------");
        System.out.println("------- Type any other number or letter to quit -------");

    }

    public static void main(String[] args) {
        menu Menu = new menu();
        Menu.showMenu();  
    }

}
