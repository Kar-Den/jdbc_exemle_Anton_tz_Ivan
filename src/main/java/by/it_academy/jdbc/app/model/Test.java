package by.it_academy.jdbc.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Test {

    private int id;
    private Date data;
    private int user_id;
    private int mark;
    private Time duration;
    private String comment;
    private User user;

    public String strTestWithUser() {
        return new StringBuilder()
                .append(getUser().getName())
                .append(" ")
                .append(getUser().getSurname())
                .append(" ")
                .append(getMark())
                .append(" ")
                .append(getData())
                .append(" ")
                .append(getDuration())
                .append(" ")
                .append(getComment()).toString();
    }
}
