package com.example.le.custommanagerdemo_theme.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dung-DamDe-DeTien on 13/08/2016.
 */
public class Customer {
    @SerializedName("cong_ty")
    @Expose
    private String congTy;
    @SerializedName("customer_type")
    @Expose
    private String customerType;
    @SerializedName("danh_gia")
    @Expose
    private String danhGia;
    @SerializedName("dia_chi")
    @Expose
    private String diaChi;
    @SerializedName("duan_qt")
    @Expose
    private String duanQt;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("ghi_chu")
    @Expose
    private String ghiChu;
    @SerializedName("ho_va_ten")
    @Expose
    private String hoVaTen;
    @SerializedName("id_account")
    @Expose
    private Integer idAccount;
    @SerializedName("id_customer")
    @Expose
    private Integer idCustomer;
    @SerializedName("lh_dau")
    @Expose
    private String lhDau;
    @SerializedName("lh_tiep")
    @Expose
    private String lhTiep;
    @SerializedName("nguon_khach")
    @Expose
    private String nguonKhach;
    @SerializedName("nhu_cau")
    @Expose
    private String nhuCau;
    @SerializedName("other")
    @Expose
    private String other;
    @SerializedName("sdt")
    @Expose
    private String sdt;

    /**
     *
     * @return
     *     The congTy
     */
    public String getCongTy() {
        return congTy;
    }

    /**
     *
     * @param congTy
     *     The cong_ty
     */
    public void setCongTy(String congTy) {
        this.congTy = congTy;
    }

    /**
     *
     * @return
     *     The customerType
     */
    public String getCustomerType() {
        return customerType;
    }

    /**
     *
     * @param customerType
     *     The customer_type
     */
    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    /**
     *
     * @return
     *     The danhGia
     */
    public String getDanhGia() {
        return danhGia;
    }

    /**
     *
     * @param danhGia
     *     The danh_gia
     */
    public void setDanhGia(String danhGia) {
        this.danhGia = danhGia;
    }

    /**
     *
     * @return
     *     The diaChi
     */
    public String getDiaChi() {
        return diaChi;
    }

    /**
     *
     * @param diaChi
     *     The dia_chi
     */
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    /**
     *
     * @return
     *     The duanQt
     */
    public String getDuanQt() {
        return duanQt;
    }

    /**
     *
     * @param duanQt
     *     The duan_qt
     */
    public void setDuanQt(String duanQt) {
        this.duanQt = duanQt;
    }

    /**
     *
     * @return
     *     The email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     *     The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     *     The ghiChu
     */
    public String getGhiChu() {
        return ghiChu;
    }

    /**
     *
     * @param ghiChu
     *     The ghi_chu
     */
    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    /**
     *
     * @return
     *     The hoVaTen
     */
    public String getHoVaTen() {
        return hoVaTen;
    }

    /**
     *
     * @param hoVaTen
     *     The ho_va_ten
     */
    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    /**
     *
     * @return
     *     The idAccount
     */
    public Integer getIdAccount() {
        return idAccount;
    }

    /**
     *
     * @param idAccount
     *     The id_account
     */
    public void setIdAccount(Integer idAccount) {
        this.idAccount = idAccount;
    }

    /**
     *
     * @return
     *     The idCustomer
     */
    public Integer getIdCustomer() {
        return idCustomer;
    }

    /**
     *
     * @param idCustomer
     *     The id_customer
     */
    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }

    /**
     *
     * @return
     *     The lhDau
     */
    public String getLhDau() {
        return lhDau;
    }

    /**
     *
     * @param lhDau
     *     The lh_dau
     */
    public void setLhDau(String lhDau) {
        this.lhDau = lhDau;
    }

    /**
     *
     * @return
     *     The lhTiep
     */
    public String getLhTiep() {
        return lhTiep;
    }

    /**
     *
     * @param lhTiep
     *     The lh_tiep
     */
    public void setLhTiep(String lhTiep) {
        this.lhTiep = lhTiep;
    }

    /**
     *
     * @return
     *     The nguonKhach
     */
    public String getNguonKhach() {
        return nguonKhach;
    }

    /**
     *
     * @param nguonKhach
     *     The nguon_khach
     */
    public void setNguonKhach(String nguonKhach) {
        this.nguonKhach = nguonKhach;
    }

    /**
     *
     * @return
     *     The nhuCau
     */
    public String getNhuCau() {
        return nhuCau;
    }

    /**
     *
     * @param nhuCau
     *     The nhu_cau
     */
    public void setNhuCau(String nhuCau) {
        this.nhuCau = nhuCau;
    }

    /**
     *
     * @return
     *     The other
     */
    public String getOther() {
        return other;
    }

    /**
     *
     * @param other
     *     The other
     */
    public void setOther(String other) {
        this.other = other;
    }

    /**
     *
     * @return
     *     The sdt
     */
    public String getSdt() {
        return sdt;
    }

    /**
     *
     * @param sdt
     *     The sdt
     */
    public void setSdt(String sdt) {
        this.sdt = sdt;
    }



}
