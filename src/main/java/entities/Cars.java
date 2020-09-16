package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Malthe
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Cars.deleteAllRows", query = "DELETE from Cars"),
})
public class Cars implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String manufacturer;
    private int year;
    private String model;
    private int price;
    private int quantity;

    public Cars(String manufacturer, int year, String model, int price, int quantity) {
        this.manufacturer = manufacturer;
        this.year = year;
        this.model = model;
        this.price = price;
        this.quantity = quantity;
    }
    
    public Cars() {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Cars{" + "id=" + id + ", manufacturer=" + manufacturer + 
                ", year=" + year + ", model=" + model + ", price=" + price + 
                ", quantity=" + quantity + '}';
    }

    
}
