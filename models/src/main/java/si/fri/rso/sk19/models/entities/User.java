package si.fri.rso.sk19.models.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
@NamedQueries(value = {
        @NamedQuery(name = "User.getAll", query = "SELECT e FROM User e"),
        @NamedQuery(name = "User.getByUsername", query = "SELECT e FROM User e WHERE e.username = :username")
})

public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    @Column(name = "username", unique = true)
    private String username;

    public Integer getId() {
        return user_id;
    }

    public void setId(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
