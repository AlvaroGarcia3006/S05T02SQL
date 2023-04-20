package s05.t02.sql.model.dto;

import java.time.LocalDateTime;

public class PlayerDTO {
    private int id;
    private String name;
    private LocalDateTime registrationDate;
    private Float winRate;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
    public Float getWinRate() {
        return winRate;
    }
    public void setWinRate(Float winRate) {
        this.winRate = winRate;
    }
}
