package com.example.w13_28917;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MarkerAndShape {
    String jenis = "Marker";
    String keterangan = "";
    double radius = 0.0;
    List<LatLng> titiks = new ArrayList<LatLng>();
    Object objekPeta = null;

    public MarkerAndShape(String jenis, String keterangan, double radius, List<LatLng> titiks) {
        this.jenis = jenis;
        this.keterangan = keterangan;
        this.radius = radius;
        this.titiks = new ArrayList<LatLng>();
        this.titiks.addAll(titiks);
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public List<LatLng> getTitiks() {
        return titiks;
    }

    public int getJumlahTitiks() {
        return this.titiks.size();
    }

    public void setTitiks(List<LatLng> titiks) {
        this.titiks = titiks;
    }

    public Object getObjekPeta() {
        return objekPeta;
    }

    public void setObjekPeta(Object objekPeta) {
        this.objekPeta = objekPeta;
    }
}
