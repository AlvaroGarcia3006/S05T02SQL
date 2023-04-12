package s05.t02.sql.model.dto;

public class GameDTO {
    private int id;
    private int playerId;
    private int dice1;
    private int dice2;
    private int score;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getPlayerId() {
        return playerId;
    }
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
    public int getDice1() {
        return dice1;
    }
    public void setDice1(int dice1) {
        this.dice1 = dice1;
    }
    public int getDice2() {
        return dice2;
    }
    public void setDice2(int dice2) {
        this.dice2 = dice2;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
}
