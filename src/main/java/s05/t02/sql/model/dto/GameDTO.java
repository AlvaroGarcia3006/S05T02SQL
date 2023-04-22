package s05.t02.sql.model.dto;

public class GameDTO {
    private Integer id;
    private Integer playerId;
    private Integer dice1;
    private Integer dice2;
    private Integer score;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getPlayerId() {
        return playerId;
    }
    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }
    public Integer getDice1() {
        return dice1;
    }
    public void setDice1(Integer dice1) {
        this.dice1 = dice1;
    }
    public Integer getDice2() {
        return dice2;
    }
    public void setDice2(Integer dice2) {
        this.dice2 = dice2;
    }
    public Integer getScore() {
        return score;
    }
    public void setScore(Integer score) {
        this.score = score;
    }
}
