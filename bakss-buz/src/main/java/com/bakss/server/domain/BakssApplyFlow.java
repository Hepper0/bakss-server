package com.bakss.server.domain;

import com.bakss.common.annotation.Excel;

/**
 * 申请流程对象 bakss_apply_flow
 *
 * @author author
 * @date 2025-04-02
 */
public class BakssApplyFlow extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 1 Leader/指派人 2 DBA 3 备份管理员 */
    @Excel(name = "1 Leader/指派人 2 DBA 3 备份管理员")
    private Long flowStep;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String applyId;

    /** 当前环节最后审批的人 */
    @Excel(name = "当前环节最后审批的人")
    private String reviewUser;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long reviewStatus;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long deleted;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setFlowStep(Long flowStep)
    {
        this.flowStep = flowStep;
    }

    public Long getFlowStep()
    {
        return flowStep;
    }
    public void setApplyId(String applyId)
    {
        this.applyId = applyId;
    }

    public String getApplyId()
    {
        return applyId;
    }
    public void setReviewUser(String reviewUser)
    {
        this.reviewUser = reviewUser;
    }

    public String getReviewUser()
    {
        return reviewUser;
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
            .append("flowStep", getFlowStep())
            .append("applyId", getApplyId())
            .append("reviewUser", getReviewUser())
            .append("reviewStatus", getReviewStatus())
            .append("remark", getRemark())
            .append("deleted", getDeleted())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
