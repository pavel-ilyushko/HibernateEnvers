package my.simple.envers;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "MyA_AUD")
public class MyA_AUD1 implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    //@Id
    private Integer REV;

    private String name;

    public Integer getREV() {
        return REV;
    }

    public void setREV(final Integer REV) {
        this.REV = REV;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "$MyA_AUD1{" +
                "id=" + id +
                ", REV=" + REV +
                ", name='" + name + '\'' +
                '}';
    }
}
