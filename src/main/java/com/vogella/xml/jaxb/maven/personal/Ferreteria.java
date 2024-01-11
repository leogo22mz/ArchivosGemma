package com.vogella.xml.jaxb.maven.personal;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement(namespace = "com.vogella.xml.jaxb.gradle.model")
public class Ferreteria {

    @XmlElementWrapper(name = "linternaList")
    @XmlElement(name = "linterna")
    private List<Linternas> linternaList;

    private String name;
    private String location;

    public void setLinternaList(List<Linternas> linternaList) {
        this.linternaList = linternaList;
    }

    public List<Linternas> getLinternasList() {
        return linternaList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
