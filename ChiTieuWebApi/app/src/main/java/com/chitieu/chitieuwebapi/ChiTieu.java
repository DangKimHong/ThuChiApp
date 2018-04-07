package com.chitieu.chitieuwebapi;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by MyPC on 06/04/2018.
 */

public class ChiTieu implements Serializable{
    private long ID;
    private String Ten;
    private long SoTien;
    private String Ngay;

    public ChiTieu(long ID, String ten, long soTien, String ngay) {
        this.ID = ID;
        Ten = ten;
        SoTien = soTien;
        Ngay = ngay;
    }

    public ChiTieu(String ten, long soTien, String ngay) {
        Ten = ten;
        SoTien = soTien;
        Ngay = ngay;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public long getSoTien() {
        return SoTien;
    }

    public void setSoTien(long soTien) {
        SoTien = soTien;
    }

    public String getNgay() {
        return Ngay;
    }

    public void setNgay(String ngay) {
        Ngay = ngay;
    }
}
