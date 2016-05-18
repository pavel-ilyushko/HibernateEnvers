package my.simple.envers;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Audited
public class MyB {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    private MyA myA;

    public MyA getMyA() {
        return myA;
    }

    public void setMyA(final MyA myA) {
        this.myA = myA;
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
}
