package com.example.admin.techkid_session4.database;

import java.io.Serializable;

public class MusicTypeModel implements Serializable {
    public String id;
    public String name;
    public int imageID;

    public MusicTypeModel(String id, String name, int imageID) {
        this.id = id;
        this.name = name;
        this.imageID = imageID;
    }
}