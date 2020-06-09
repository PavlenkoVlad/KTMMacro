package com.confluence.ktmMacro.rest.difficultyLevel;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "difficultyLevel")
@XmlAccessorType(XmlAccessType.FIELD)
public class DifficultyLevelRestModel {

    @XmlElement(name = "id")
    private int id;

    @XmlElement(name = "name")
    private String name;

    public DifficultyLevelRestModel() {}

    public DifficultyLevelRestModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

}
