package rishabh.server;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class Student {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="STUDENT_SEQ_GEN")
    @SequenceGenerator(name="STUDENT_SEQ_GEN", sequenceName="STUDENT_SEQ")
    private Long id;
    
    public Long getId() {
        return id;
    }
    
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
