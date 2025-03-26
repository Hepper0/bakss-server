package com.bakss.server.domain;

import java.util.Date;
import com.bakss.common.core.domain.BaseEntity;
import com.bakss.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 【请填写功能名称】对象 bakss_apply
 *
 * @author author
 * @date 2025-03-26
 */
public class BakssApply extends BaseEntity
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
    private Long reviewStatus;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long deleted;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setApplyType(Long applyType)
    {
        this.applyType = applyType;
    }

    public Long getApplyType()
    {
        return applyType;
    }
    public void setApplyUser(String applyUser)
    {
        this.applyUser = applyUser;
    }

    public String getApplyUser()
    {
        return applyUser;
    }
    public void setApplyTime(Date applyTime)
    {
        this.applyTime = applyTime;
    }

    public Date getApplyTime()
    {
        return applyTime;
    }
    public void setBackupId(Long backupId)
    {
        this.backupId = backupId;
    }

    public Long getBackupId()
    {
        return backupId;
    }
    public void setBackupTime(Date backupTime)
    {
        this.backupTime = backupTime;
    }

    public Date getBackupTime()
    {
        return backupTime;
    }
    public void setBackupStatus(Long backupStatus)
    {
        this.backupStatus = backupStatus;
    }

    public Long getBackupStatus()
    {
        return backupStatus;
    }
    public void setReviewStatus(Long reviewStatus)
    {
        this.reviewStatus = reviewStatus;
    }

    public Long getReviewStatus()
    {
        return reviewStatus;
    }
    public void setDeleted(Long deleted)
    {
        this.deleted = deleted;
    }

    public Long getDeleted()
    {
        return deleted;
    }

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
            .append("reviewStatus", getReviewStatus())
            .append("remark", getRemark())
            .append("deleted", getDeleted())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
