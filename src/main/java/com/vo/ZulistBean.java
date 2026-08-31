package com.vo;

import com.entity.Userlist;
import com.entity.Zulist;

public class ZulistBean extends Zulist {

    private Userlist userlist;

    public Userlist getUserlist() {
        return userlist;
    }

    public void setUserlist(Userlist userlist) {
        this.userlist = userlist;
    }
}
