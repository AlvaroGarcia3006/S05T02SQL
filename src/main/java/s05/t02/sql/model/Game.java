package s05.t02.sql.model;

import javax.persistence.*;

@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_player")
    private Player player;
    @Column(name = "dice_1")
    private int dice1;
    @Column(name = "dice_2")
    private int dice2;
    @Column(name = "score")
    private int score;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
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
