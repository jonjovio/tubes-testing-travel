/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

public class Rute implements Kota {

    private int idRute;
    private int tujuan;
    private int keberangkatan;

    public Rute() {
    }

    public Rute(int idRute, int tujuan, int keberangkatan) {
        this.idRute = idRute;
        this.tujuan = tujuan;
        this.keberangkatan = keberangkatan;
    }

    public int getIdRute() {
        return idRute;
    }

    public void setIdRute(int idRute) {
        this.idRute = idRute;
    }

    public int getTujuan() {
        return tujuan;
    }

    public void setTujuan(int tujuan) {
        this.tujuan = tujuan;
    }

    public int getKeberangkatan() {
        return keberangkatan;
    }

    public void setKeberangkatan(int keberangkatan) {
        this.keberangkatan = keberangkatan;
    }

}
