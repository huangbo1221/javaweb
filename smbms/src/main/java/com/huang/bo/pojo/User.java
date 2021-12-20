package com.huang.bo.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName User
 * @Description TODO
 * @Author huangbo1221
 * @Date 2021/12/20 21:54
 * @Version 1.0
 */
@Data
public class User {

    private Integer id;

    private String userCode;

    private String userName;

    private String userPassword;

    private Integer gender;

    private Date birthday;

    private String phone;

    private String address;

    private Integer userRole;

    private Integer createdBy;

    private Date creationDate;

    private Integer modifyBy;

    private Date modifyDate;

}
