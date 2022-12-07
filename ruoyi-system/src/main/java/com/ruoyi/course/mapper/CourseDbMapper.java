package com.ruoyi.course.mapper;

import java.util.List;
import com.ruoyi.course.domain.CourseDb;

/**
 * courseMapper接口
 * 
 * @author 邓周明
 * @date 2022-12-05
 */
public interface CourseDbMapper 
{
    /**
     * 查询course
     * 
     * @param id course主键
     * @return course
     */
    public CourseDb selectCourseDbById(Long id);

    /**
     * 查询course列表
     * 
     * @param courseDb course
     * @return course集合
     */
    public List<CourseDb> selectCourseDbList(CourseDb courseDb);

    /**
     * 新增course
     * 
     * @param courseDb course
     * @return 结果
     */
    public int insertCourseDb(CourseDb courseDb);

    /**
     * 修改course
     * 
     * @param courseDb course
     * @return 结果
     */
    public int updateCourseDb(CourseDb courseDb);

    /**
     * 删除course
     * 
     * @param id course主键
     * @return 结果
     */
    public int deleteCourseDbById(Long id);

    /**
     * 批量删除course
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCourseDbByIds(Long[] ids);
}
