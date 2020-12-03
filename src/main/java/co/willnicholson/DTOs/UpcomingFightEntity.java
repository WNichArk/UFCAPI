package co.willnicholson.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "upcomingfights")
public class UpcomingFightEntity {
    @Id
    private int id;
    @Column(name = "date")
    private String date;
    @Column(name = "fighter1name")
    private String fighter1name;
    @Column(name = "fighter1url")
    private String fighter1url;
    @Column(name = "fighter2name")
    private String fighter2name;
    @Column(name = "fighter2url")
    private String fighter2url;
    @Column(name= "event")
    private String event;
    @Column(name = "eventurl")
    private String eventurl;
}
