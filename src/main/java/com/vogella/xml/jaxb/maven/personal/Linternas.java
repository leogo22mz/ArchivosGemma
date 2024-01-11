package com.vogella.xml.jaxb.maven.personal;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "book")

@XmlType(propOrder = { "brand", "name", "retailer"})
public class Linternas {
    private String name;
    private String brand;
    private String retailer;

    // Si te gusta el nombre de la variable, por ejemplo, "name"
    // puedes cambiar fácilmente este nombre para tu salida XML:
    @XmlElement(name = "title")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

}
