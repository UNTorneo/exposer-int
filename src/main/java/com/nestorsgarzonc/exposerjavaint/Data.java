package com.nestorsgarzonc.exposerjavaint;

import java.io.Serializable;
import java.util.ArrayList;

public class Data implements Serializable {

    private GetVenuesParent data;

    public GetVenuesParent getData() {
        return data;
    }

    public void setVenues(GetVenuesParent data) {
        this.data = data;
    }
}

