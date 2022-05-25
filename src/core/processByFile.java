package core;

import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

import Constants.constants;
import Utils.utils;
import models.teams;

public class processByFile extends coreProcess {

    private List<String> badLines;
    private List<String> teams;
    private Scanner scanner;

    public processByFile(TreeMap<String, Integer> scoreResults, List<teams> orderFinalResults, List<String> badLines,
            List<String> teams, Scanner scanner) {
        super(scoreResults, orderFinalResults);
        this.badLines = badLines;
        this.teams = teams;
        this.scanner = scanner;
    }

    public void processRecordByFile() {
        System.out.println(
                "------- Please type the name of the file to process (The file should be in resources folder)-------");
        String fileName = scanner.nextLine();

        String file;
        String systemRoute = System.getProperty("user.dir");
        file = systemRoute;
        file += constants.pathResource + fileName;
        teams = utils.readFile(file);

        for (String line : teams) {
            if (checkExpression(line)) {
                calculateScore(line);
            } else {
                badLines.add(line);
            }
        }

        showFinalResults();

        showBadRecods();

        teams.clear();

    }

    private void showBadRecods() {

        if (badLines.size() > 0) {

            System.out.println("\n------- Bad records that were not process -------\n");

            for (String line : badLines) {
                System.out.println(line + "\n");
            }

            badLines.clear();

        }

    }

}
