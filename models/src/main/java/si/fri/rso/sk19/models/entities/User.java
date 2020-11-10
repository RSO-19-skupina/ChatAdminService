package si.fri.rso.sk19.models.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "created")
    private Instant created;

    public Integer getId(){
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
