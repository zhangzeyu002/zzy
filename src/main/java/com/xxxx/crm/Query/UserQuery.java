package com.xxxx.crm.Query;

import com.xxxx.crm.base.BaseQuery;

public class UserQuery extends BaseQuery {
    //用户名
    public  String userName;
    //邮箱
    public String email;
    //电话号码
    public String phone;

    public UserQuery(String userName, String email, String phone) {
        this.userName = userName;
        this.email = email;
        this.phone = phone;
    }

    public UserQuery() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
