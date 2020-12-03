package co.willnicholson.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="fights")
public class FightEntity {
    @Id
    public int id;
    @Column(name="date")
    private String date;
    @Column(name="fighter1name")
    private String fighter1;
    @Column(name="fighter2name")
    private String fighter2;
    @Column(name="winround")
    private int winRound;
    @Column(name="winmethod")
    private String winMethod;

}
