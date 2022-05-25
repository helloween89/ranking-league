package core;

import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

import Constants.constants;

public class processByConsole extends coreProcess {

    private List<String> badLines;
    private List<String> teams;
    private Scanner scanner;

    public processByConsole(TreeMap<String, Integer> scoreResults, List<models.teams> orderFinalResults,
            List<String> badLines, List<String> teams, Scanner scanner) {
        super(scoreResults, orderFinalResults);
        this.badLines = badLines;
        this.teams = teams;
        this.scanner = scanner;
    }

    public void typeMatch() {
        System.out.flush();
        System.out.println("------- Please type a match using the next format (team score, team2 score) -------");
        String text = scanner.nextLine();
        if (checkExpression(text)) {
            teams.add(text);
            typeMatch();
        } else {
            if (text.equals(constants.process)) {
                for (String tx : teams) {
                    calculateScore(tx);
                }

                showFinalResults();

                showBadRecods();

                teams.clear();

            } else {
                badLines.add(text);
                typeMatch();
            }
        }
    }

    private void showBadRecods() {

        if (badLines.size() > 0) {

            System.out.println("------- Bad records that were not process -------");

            for (String line : badLines) {
                System.out.println(line + "\n");
            }

            badLines.clear();

        }

    }
}
