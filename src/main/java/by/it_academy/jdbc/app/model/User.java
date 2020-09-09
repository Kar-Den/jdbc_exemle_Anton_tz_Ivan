package by.it_academy.jdbc.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private long id;
    private String name;
    private String surname;
    private String email;
    private String login;
    private String password;
    private List<Role> roles;

    public User(String name, String surname, String email, String login, String password, List<Role> roles) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.login = login;
        this.password = password;
        this.roles = roles;
    }
}
