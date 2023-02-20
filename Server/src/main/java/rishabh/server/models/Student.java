package rishabh.server.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
