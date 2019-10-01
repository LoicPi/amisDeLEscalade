package com.adle.projet.dto;

public class SearchSpot {

    private String  name;

    private String  city;

    private Integer county;

    private String  sectors;

    private Integer listing;

    private Integer level;

    public SearchSpot() {

    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity( String city ) {
        this.city = city;
    }

    public Integer getCounty() {
        return county;
    }

    public void setCounty( Integer county ) {
        this.county = county;
    }

    public String getSectors() {
        return sectors;
    }

    public void setSectors( String sectors ) {
        this.sectors = sectors;
    }

    public Integer getListing() {
        return listing;
    }

    public void setListing( Integer listing ) {
        this.listing = listing;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel( Integer level ) {
        this.level = level;
    }

}
