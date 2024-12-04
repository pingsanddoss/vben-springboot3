package org.vben.modules.system.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SalaryBodyDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    @JsonIgnore
    private String id;

    @JsonIgnore
    private String link;

    @JsonIgnore
    private String employId;

    private String v1;

    private String v2;

    private String v3;

    private String v4;

    private String v5;

    private String v6;

    private String v7;

    private String v8;

    private String v9;

    private String v10;
    @ExcelProperty(index = 10)
    private String v11;
    @ExcelProperty(index = 11)
    private String v12;
    @ExcelProperty(index = 12)
    private String v13;
    @ExcelProperty(index = 13)
    private String v14;
    @ExcelProperty(index = 14)
    private String v15;
    @ExcelProperty(index = 15)
    private String v16;
    @ExcelProperty(index = 16)
    private String v17;
    @ExcelProperty(index = 17)
    private String v18;
    @ExcelProperty(index = 18)
    private String v19;
    @ExcelProperty(index = 19)
    private String v20;
    @ExcelProperty(index = 20)
    private String v21;
    @ExcelProperty(index = 21)
    private String v22;
    @ExcelProperty(index = 22)
    private String v23;
    @ExcelProperty(index = 23)
    private String v24;
    @ExcelProperty(index = 24)
    private String v25;
    @ExcelProperty(index = 25)
    private String v26;
    @ExcelProperty(index = 26)
    private String v27;
    @ExcelProperty(index = 27)
    private String v28;
    @ExcelProperty(index = 28)
    private String v29;
    @ExcelProperty(index = 29)
    private String v30;
    @ExcelProperty(index = 30)
    private String v31;
    @ExcelProperty(index = 31)
    private String v32;
    @ExcelProperty(index = 32)
    private String v33;
    @ExcelProperty(index = 33)
    private String v34;
    @ExcelProperty(index = 34)
    private String v35;
    @ExcelProperty(index = 35)
    private String v36;
    @ExcelProperty(index = 36)
    private String v37;
    @ExcelProperty(index = 37)
    private String v38;
    @ExcelProperty(index = 38)
    private String v39;
    @ExcelProperty(index = 39)
    private String v40;
    @ExcelProperty(index = 40)
    private String v41;
    @ExcelProperty(index = 41)
    private String v42;
    @ExcelProperty(index = 42)
    private String v43;
    @ExcelProperty(index = 43)
    private String v44;
    @ExcelProperty(index = 44)
    private String v45;
    @ExcelProperty(index = 45)
    private String v46;
    @ExcelProperty(index = 46)
    private String v47;
    @ExcelProperty(index = 47)
    private String v48;
    @ExcelProperty(index = 48)
    private String v49;
    @ExcelProperty(index = 49)
    private String v50;
    @ExcelProperty(index = 50)
    private String v51;
    @ExcelProperty(index = 51)
    private String v52;
    @ExcelProperty(index = 52)
    private String v53;
    @ExcelProperty(index = 53)
    private String v54;
    @ExcelProperty(index = 54)
    private String v55;
    @ExcelProperty(index = 55)
    private String v56;
    @ExcelProperty(index = 56)
    private String v57;
    @ExcelProperty(index = 57)
    private String v58;
    @ExcelProperty(index = 58)
    private String v59;
    @ExcelProperty(index = 59)
    private String v60;



    private String sendStatus;


    private String sendRes;

    @JsonIgnore
    private String bizid;

    /**
     * 删除状态（0，正常，1已删除）
     */
    @TableLogic
    @JsonIgnore
    private Integer delFlag;

    /**
     * 创建人
     */
    @JsonIgnore
    private String createBy;

    /**
     * 创建时间
     */
    @JsonIgnore
    private Date createTime;

    /**
     * 更新人
     */
    @JsonIgnore
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonIgnore
    private Date updateTime;

    /**
     * 备注
     */
    @JsonIgnore
    private String remarks;
}
