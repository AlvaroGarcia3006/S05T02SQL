package s05.t02.sql.model;

import javax.persistence.*;

@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;
    @Column(name = "dice_1")
    private Integer dice1;
    @Column(name = "dice_2")
    private Integer dice2;
    @Column(name = "score")
    private Integer score;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
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
