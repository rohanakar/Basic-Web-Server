package rishabh.server.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.SequenceGenerator;
import java.io.Serializable;

public class UserId implements Serializable {
    
    private Long id;

    private String email;
    
    public UserId(Long id, String email){
        this.email = email;
        this.id = id;
    }
    
    public UserId(){
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
