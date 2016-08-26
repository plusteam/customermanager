package com.example.le.custommanagerdemo_theme.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dung-DamDe-DeTien on 13/08/2016.
 */
public class AccountResponse {
    private List<Account> data = new ArrayList<Account>();

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The data
     */

    public List<Account> getData() {
        return data;
    }

    /**
     *
     *
     * @param data
     *     The data
     */

    public void setData(List<Account> data) {
        this.data = data;
    }


}
