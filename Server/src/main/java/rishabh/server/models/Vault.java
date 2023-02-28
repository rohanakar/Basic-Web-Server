package rishabh.server.models;

import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Vault implements Persistable<String> {
    
    @Id
    private String key;
    
    private String value;
    
    private String code;
    
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getId() {
        return this.key;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
