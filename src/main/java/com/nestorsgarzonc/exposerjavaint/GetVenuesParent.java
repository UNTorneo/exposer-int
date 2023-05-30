package com.nestorsgarzonc.exposerjavaint;

import java.io.Serializable;
import java.util.ArrayList;

public class GetVenuesParent implements Serializable {
    private ArrayList<GetVenues> getVenues;


    public ArrayList<GetVenues> getGetVenues() {
        return getVenues;
    }

    public void setGetVenues(ArrayList<GetVenues> getVenues) {
        this.getVenues = getVenues;
    }
}
