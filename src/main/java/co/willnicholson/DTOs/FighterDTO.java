package co.willnicholson.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FighterDTO {
    private int id;
    private String name;
    private String height;
    private String weight;
    private String picture;
    private String url;
    private int totalFights;
    private String birthDate;
    private int draws;
    private int nc;
    private int losses;
    private int lossesSub;
    private int lossesDec;
    private int lossesKo;
    private int lossesOther;
    private int wins;
    private int winsKo;
    private int winsDec;
    private int winsSub;
    private int winsOther;


}
