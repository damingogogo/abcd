package com.ruoyi.course.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.course.mapper.CourseDbMapper;
import com.ruoyi.course.domain.CourseDb;
import com.ruoyi.course.service.ICourseDbService;

/**
 * courseService业务层处理
 * 
 * @author 邓周明
 * @date 2022-12-05
 */
@Service
public class CourseDbServiceImpl implements ICourseDbService 
{
    @Autowired
    private CourseDbMapper courseDbMapper;

    /**
     * 查询course
     * 
     * @param id course主键
     * @return course
     */
    @Override
    public CourseDb selectCourseDbById(Long id)
    {
        return courseDbMapper.selectCourseDbById(id);
    }

    /**
     * 查询course列表
     * 
     * @param courseDb course
     * @return course
     */
    @Override
    public List<CourseDb> selectCourseDbList(CourseDb courseDb)
    {
        return courseDbMapper.selectCourseDbList(courseDb);
    }

    /**
     * 新增course
     * 
     * @param courseDb course
     * @return 结果
     */
    @Override
    public int insertCourseDb(CourseDb courseDb)
    {
        return courseDbMapper.insertCourseDb(courseDb);
    }

    /**
     * 修改course
     * 
     * @param courseDb course
     * @return 结果
     */
    @Override
    public int updateCourseDb(CourseDb courseDb)
    {
        return courseDbMapper.updateCourseDb(courseDb);
    }

    /**
     * 批量删除course
     * 
     * @param ids 需要删除的course主键
     * @return 结果
     */
    @Override
    public int deleteCourseDbByIds(Long[] ids)
    {
        return courseDbMapper.deleteCourseDbByIds(ids);
    }

    /**
     * 删除course信息
     * 
     * @param id course主键
     * @return 结果
     */
    @Override
    public int deleteCourseDbById(Long id)
    {
        return courseDbMapper.deleteCourseDbById(id);
    }
}
