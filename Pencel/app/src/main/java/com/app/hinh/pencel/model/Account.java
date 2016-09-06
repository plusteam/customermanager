package com.app.hinh.pencel.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dung-DamDe-DeTien on 13/08/2016.
 */
public class Account {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("mail")
    @Expose
    private String mail;


    /**
     *
     * @return
     *     The id
     */

    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     *     The id
     */

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     *     The mail
     */

    public String getMail() {
        return mail;
    }

    /**
     *
     * @param mail
     *     The mail
     */

    public void setMail(String mail) {
        this.mail = mail;
    }
}
