package com.confluence.ktmMacro.rest.scope;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "scope")
@XmlAccessorType(XmlAccessType.FIELD)
public class ScopeRestModel {

    @XmlElement(name = "id")
    private int id;

    @XmlElement(name = "name")
    private String name;

    public ScopeRestModel() {}

    public ScopeRestModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

}
