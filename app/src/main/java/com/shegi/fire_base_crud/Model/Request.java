package com.shegi.fire_base_crud.Model;

import java.io.Serializable;

/**
 * created by shegi-developer on 01/07/20
 */
public class Request implements Serializable {

    private String nama;
    private String email;
    private String pesan;

    private String key;

    public Request(){

    }

    public Request(String nama, String email, String pesan){
        this.nama = nama;
        this.email = email;
        this.pesan = pesan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString(){
        return " "+nama+"\n" +
                " "+email+"\n"+
                " "+pesan;
    }
}
