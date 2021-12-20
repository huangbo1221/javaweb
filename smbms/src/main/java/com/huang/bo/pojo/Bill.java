package com.huang.bo.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName Bill
 * @Description TODO
 * @Author huangbo1221
 * @Date 2021/12/20 7:52
 * @Version 1.0
 */
@Data
public class Bill {

    private Integer id;

    private String billCode;

    private String productName;

    private String productDesc;

    private String productUnit;

    private BigDecimal productCount;

    private BigDecimal totalPrice;

    private Integer isPayment;

    private Integer createdBy;

    private Date createdDate;

    private Integer modifyBy;

    private Date modifyDate;

    private Integer providerId;
}
