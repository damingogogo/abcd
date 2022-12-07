package com.ruoyi.course.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * course对象 course_db
 * 
 * @author 邓周明
 * @date 2022-12-05
 */
public class CourseDb extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 课程编号 */
    private Long id;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String subName;

    /** 开课时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开课时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date subStart;

    /** 结课时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结课时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date subEnd;

    /** 任课教师 */
    @Excel(name = "任课教师")
    private String subTeacher;

    /** 课程对应院 */
    @Excel(name = "课程对应院")
    private String subCollege;

    /** 课程对应系别 */
    @Excel(name = "课程对应系别")
    private String subDep;

    /** 课程对应年级 */
    @Excel(name = "课程对应年级")
    private String subGrade;

    /** 课程对应班级 */
    @Excel(name = "课程对应班级")
    private String subClass;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSubName(String subName) 
    {
        this.subName = subName;
    }

    public String getSubName() 
    {
        return subName;
    }
    public void setSubStart(Date subStart) 
    {
        this.subStart = subStart;
    }

    public Date getSubStart() 
    {
        return subStart;
    }
    public void setSubEnd(Date subEnd) 
    {
        this.subEnd = subEnd;
    }

    public Date getSubEnd() 
    {
        return subEnd;
    }
    public void setSubTeacher(String subTeacher) 
    {
        this.subTeacher = subTeacher;
    }

    public String getSubTeacher() 
    {
        return subTeacher;
    }
    public void setSubCollege(String subCollege) 
    {
        this.subCollege = subCollege;
    }

    public String getSubCollege() 
    {
        return subCollege;
    }
    public void setSubDep(String subDep) 
    {
        this.subDep = subDep;
    }

    public String getSubDep() 
    {
        return subDep;
    }
    public void setSubGrade(String subGrade) 
    {
        this.subGrade = subGrade;
    }

    public String getSubGrade() 
    {
        return subGrade;
    }
    public void setSubClass(String subClass) 
    {
        this.subClass = subClass;
    }

    public String getSubClass() 
    {
        return subClass;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("subName", getSubName())
            .append("subStart", getSubStart())
            .append("subEnd", getSubEnd())
            .append("subTeacher", getSubTeacher())
            .append("subCollege", getSubCollege())
            .append("subDep", getSubDep())
            .append("subGrade", getSubGrade())
            .append("subClass", getSubClass())
            .toString();
    }
}
