package by.it_academy.jdbc.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
    private int id;
    private String role;

    public Role(String role) {
        this.role = role;
    }
}
