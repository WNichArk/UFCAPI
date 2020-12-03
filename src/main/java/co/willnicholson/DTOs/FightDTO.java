package co.willnicholson.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FightDTO {
    public int id;

    private String date;

    private String fighter1;

    private String fighter2;

    private int winRound;

    private String winMethod;

}