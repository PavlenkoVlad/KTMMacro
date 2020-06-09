package com.confluence.ktmMacro.rest.label;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "label")
@XmlAccessorType(XmlAccessType.FIELD)
public class LabelRestModel {

    @XmlElement(name = "id")
    private int id;

    @XmlElement(name = "name")
    private String name;

    public LabelRestModel() {}

    public LabelRestModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

}