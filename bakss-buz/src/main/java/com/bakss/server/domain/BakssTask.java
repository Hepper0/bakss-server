package com.bakss.server.domain;

import com.bakss.common.annotation.Excel;
import com.bakss.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 申请流程对象 bakss_apply
 *
 * @author author
 * @date 2025-04-02
 */
@Data
public class BakssTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long applyType;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String applyUser;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date applyTime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long backupId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date backupTime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long backupStatus;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long flowId;

    private Integer flowStep;

    private String reviewUser;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long deleted;

//    public void setId(String id)
//    {
//        this.id = id;
//    }
//
//    public String getId()
//    {
//        return id;
//    }
//    public void setApplyType(Long applyType)
//    {
//        this.applyType = applyType;
//    }
//
//    public Long getApplyType()
//    {
//        return applyType;
//    }
//    public void setApplyUser(String applyUser)
//    {
//        this.applyUser = applyUser;
//    }
//
//    public String getApplyUser()
//    {
//        return applyUser;
//    }
//    public void setApplyTime(Date applyTime)
//    {
//        this.applyTime = applyTime;
//    }
//
//    public Date getApplyTime()
//    {
//        return applyTime;
//    }
//    public void setBackupId(Long backupId)
//    {
//        this.backupId = backupId;
//    }
//
//    public Long getBackupId()
//    {
//        return backupId;
//    }
//    public void setBackupTime(Date backupTime)
//    {
//        this.backupTime = backupTime;
//    }
//
//    public Date getBackupTime()
//    {
//        return backupTime;
//    }
//    public void setBackupStatus(Long backupStatus)
//    {
//        this.backupStatus = backupStatus;
//    }
//
//    public Long getBackupStatus()
//    {
//        return backupStatus;
//    }
//    public void setFlowId(Long flowId)
//    {
//        this.flowId = flowId;
//    }
//
//    public Long getFlowId()
//    {
//        return flowId;
//    }
//    public void setDeleted(Long deleted)
//    {
//        this.deleted = deleted;
//    }
//
//    public Long getDeleted()
//    {
//        return deleted;
//    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("applyType", getApplyType())
            .append("applyUser", getApplyUser())
            .append("applyTime", getApplyTime())
            .append("backupId", getBackupId())
            .append("backupTime", getBackupTime())
            .append("backupStatus", getBackupStatus())
            .append("flowId", getFlowId())
            .append("remark", getRemark())
            .append("deleted", getDeleted())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
