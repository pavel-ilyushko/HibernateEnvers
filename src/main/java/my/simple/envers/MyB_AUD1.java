package my.simple.envers;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name = "MyB_AUD")
public class MyB_AUD1 implements Serializable{

//    @Id
//    @GeneratedValue
    private Long id;

    //@Id
    private Integer REV;

    private String name;

//    @ManyToOne
//    private MyA_AUD1 myA_aud1;
//
//    public MyA_AUD1 getMyA_aud1() {
//        return myA_aud1;
//    }
//
//    public void setMyA_aud1(final MyA_AUD1 myA_aud1) {
//        this.myA_aud1 = myA_aud1;
//    }

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
        return "$MyB_AUD1{" +
                "id=" + id +
                ", REV=" + REV +
                ", name='" + name + '\'' +
                '}';
    }
}
