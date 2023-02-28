package rishabh.server.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table(name="USERS")
@Entity
@IdClass(UserId.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "USER_SEQ_GEN")
    @SequenceGenerator(name = "USER_SEQ_GEN", sequenceName = "STUDENT_SEQ")
    private Long id;
    
    private String firstName;
    private String lastName;
    private String country;
    private String mobileNumber;

    @Id
    private String email;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String number) {
        this.mobileNumber = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}