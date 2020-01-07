package com.example.crudmhs.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Mahasiswa implements Serializable {
    private String nim,nama;
    private int semester;
    private float ipk;
    private String key;

    public Mahasiswa() {

    }

    public Mahasiswa(String nim, String nama, int semester, float ipk) {
        this.nim = nim;
        this.nama = nama;
        this.semester = semester;
        this.ipk = ipk;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public float getIpk() {
        return ipk;
    }

    public void setIpk(float ipk) {
        this.ipk = ipk;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @NonNull
    @Override
    public String toString() {
        return " "+nim+"\n"+
                " "+nama+"\n"+
                " "+Integer.toString(semester)+"\n"+
                " "+Float.toString(ipk);
    }
}
