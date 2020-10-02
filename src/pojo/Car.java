package pojo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="car"
    ,catalog="hib"
)
public class Car  implements java.io.Serializable {


     private Integer idCar;
     private String mark;
     private String model;
     private int cc;
     private int hp;

    public Car() {
    }

    public Car(String mark, String model, int cc, int hp) {
       this.mark = mark;
       this.model = model;
       this.cc = cc;
       this.hp = hp;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idCar", unique=true, nullable=false)
    public Integer getIdCar() {
        return this.idCar;
    }
    
    public void setIdCar(Integer idCar) {
        this.idCar = idCar;
    }

    
    @Column(name="mark", nullable=false, length=30)
    public String getMark() {
        return this.mark;
    }
    
    public void setMark(String mark) {
        this.mark = mark;
    }

    
    @Column(name="model", nullable=false, length=30)
    public String getModel() {
        return this.model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }

    
    @Column(name="cc", nullable=false)
    public int getCc() {
        return this.cc;
    }
    
    public void setCc(int cc) {
        this.cc = cc;
    }

    
    @Column(name="hp", nullable=false)
    public int getHp() {
        return this.hp;
    }
    
    public void setHp(int hp) {
        this.hp = hp;
    }

}


