package models;

public class teams implements Comparable<teams> {

    private String teamName;
    private Integer scoreTeam;

    public teams(String teamName, int scoreTeam) {
        this.teamName = teamName;
        this.scoreTeam = scoreTeam;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getScoreTeam() {
        return this.scoreTeam;
    }

    public void setScoreTeam(Integer scoreTeam) {
        this.scoreTeam = scoreTeam;
    }

    @Override
    public int compareTo(teams o) {
        return scoreTeam.compareTo(o.scoreTeam);
    }
    
}
