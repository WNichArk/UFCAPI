package co.willnicholson.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="fighters")
public class FighterEntity {
    @Id
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="height")
    private String height;
    @Column(name="weight")
    private String weight;
    @Column(name="picture")
    private String picture;
    @Column(name="url")
    private String url;
    @Column(name="totalfights")
    private int totalFights;
    @Column(name="birthdate")
    private String birthDate;
    @Column(name="draws")
    private int draws;
    @Column(name="nc")
    private int nc;
    @Column(name="losses")
    private int losses;
    @Column(name="lossessub")
    private int lossesSub;
    @Column(name="lossesdec")
    private int lossesDec;
    @Column(name="lossesko")
    private int lossesKo;
    @Column(name="lossesother")
    private int lossesOther;
    @Column(name="wins")
    private int wins;
    @Column(name="winsko")
    private int winsKo;
    @Column(name="winsdec")
    private int winsDec;
    @Column(name="winssub")
    private int winsSub;
    @Column(name="winsother")
    private int winsOther;
}
