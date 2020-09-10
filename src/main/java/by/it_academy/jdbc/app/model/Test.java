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

    public Test(Date data, int user_id, int mark, Time duration, String comment) {
        this.data = data;
        this.user_id = user_id;
        this.mark = mark;
        this.duration = duration;
        this.comment = comment;
    }
}
