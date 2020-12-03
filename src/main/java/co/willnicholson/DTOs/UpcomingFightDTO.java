package co.willnicholson.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpcomingFightDTO {
    private int id;
    private String date;
    private String fighter1name;
    private String fighter1url;
    private String fighter2name;
    private String fighter2url;
    private String event;
    private String eventurl;
}
