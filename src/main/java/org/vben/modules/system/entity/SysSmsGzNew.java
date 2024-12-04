package org.vben.modules.system.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysSmsGzNew {

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @JsonIgnore
    private String id;

    private String sendType;

    private String sendDate;

    @JsonIgnore
    private String sendState;

    private String name;

    private String phone;

    private String salary;

    private String mail;

    @JsonIgnore
    private String hide;

    private String showNull;

    private String showBroadcast;

    private String broadcastText;

    @JsonIgnore
    private String showCron;
    @JsonIgnore
    private String cronText;
    @JsonIgnore
    private String accountSuitId;



    /**
     * 删除状态（0，正常，1已删除）
     */
    @TableLogic
    @ExcelIgnore
    @JsonIgnore
    private Integer delFlag;

    /**
     * 创建人
     */
    @ExcelIgnore
    @JsonIgnore
    private String createBy;

    /**
     * 创建时间
     */
    @ExcelIgnore
    @JsonIgnore
    private Date createTime;

    /**
     * 更新人
     */
    @ExcelIgnore
    @JsonIgnore
    private String updateBy;

    /**
     * 更新时间
     */
    @ExcelIgnore
    @JsonIgnore
    private Date updateTime;

    /**
     * 备注
     */
    @ExcelIgnore
    @JsonIgnore
    private String remarks;

}
