package Utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Constants.constants;

public class utils {

    public static int getNumberScore(String text) {

        int result = 0;
        StringBuilder sb = new StringBuilder();
        char[] sequence;
        sequence = text.toCharArray();

        for (char l : sequence) {
            if (l >= '0' && l <= '9') {
                sb.append(l);
            }
        }

        result = Integer.parseInt(sb.toString());
        return result;

    }

    public static String getNameScore(String text) {

        String result = "";
        StringBuilder sb = new StringBuilder();
        char[] sequence;
        sequence = text.toCharArray();

        for (char l : sequence) {
            if (l >= 'a' && l <= 'z' || l >= 'A' && l <= 'Z' || l == ' ') {
                sb.append(l);
            }
        }

        result = sb.toString();
        return result;

    }

    public static Map<String, Integer> processNameAndScoreTeam(String text) {

        Map<String, Integer> team = new HashMap<>();
        String teamName = utils.getNameScore(text);
        int teamScore = utils.getNumberScore(text);

        team.put(teamName, teamScore);

        return team;
    }

    public static String trimStartAndEndSpace(String text) {
        String result = "";
        result = text;
        if (text.charAt(0) == ' ') {
            result = text.substring(1, text.length());
        }
        if (result.charAt(result.length() - 1) == ' ') {
            result = result.substring(0, result.length() - 1);
        }

        return result;
    }

    public static List<String> readFile(String file) {
        List<String> lines = new ArrayList<>();
        List<String> cleanList= new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
            cleanList = lines.stream().filter(l->l.length()>0).collect(Collectors.toList());
        } catch (IOException ex) {
            System.out.println(constants.fileNotExist);
        }
        return cleanList;
    }

}
