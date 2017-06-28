package com.ucr.fofis.dataaccess.entity;

import java.io.Serializable;

/**
 * Created by rapuc on 6/22/17.
 */

public class Resource implements Serializable {

    private int id;
    private String title;

    public Resource() {
    }

    public Resource(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

