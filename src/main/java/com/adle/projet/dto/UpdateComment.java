package com.adle.projet.dto;

public class UpdateComment {

    private Integer id;

    private String  updateContents;

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getUpdateContents() {
        return updateContents;
    }

    public void setUpdateContents( String updateContents ) {
        this.updateContents = updateContents;
    }
}
