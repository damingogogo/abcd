package com.ruoyi.web.controller.course;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.course.domain.CourseDb;
import com.ruoyi.course.service.ICourseDbService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * courseController
 * 
 * @author 邓周明
 * @date 2022-12-05
 */
@RestController
@RequestMapping("/course/course")
public class CourseDbController extends BaseController
{
    @Autowired
    private ICourseDbService courseDbService;

    /**
     * 查询course列表
     */
    @PreAuthorize("@ss.hasPermi('course:course:list')")
    @GetMapping("/list")
    public TableDataInfo list(CourseDb courseDb)
    {
        startPage();
        List<CourseDb> list = courseDbService.selectCourseDbList(courseDb);
        return getDataTable(list);
    }

    /**
     * 导出course列表
     */
    @PreAuthorize("@ss.hasPermi('course:course:export')")
    @Log(title = "course", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CourseDb courseDb)
    {
        List<CourseDb> list = courseDbService.selectCourseDbList(courseDb);
        ExcelUtil<CourseDb> util = new ExcelUtil<CourseDb>(CourseDb.class);
        util.exportExcel(response, list, "course数据");
    }

    /**
     * 获取course详细信息
     */
    @PreAuthorize("@ss.hasPermi('course:course:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(courseDbService.selectCourseDbById(id));
    }

    /**
     * 新增course
     */
    @PreAuthorize("@ss.hasPermi('course:course:add')")
    @Log(title = "course", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CourseDb courseDb)
    {
        return toAjax(courseDbService.insertCourseDb(courseDb));
    }

    /**
     * 修改course
     */
    @PreAuthorize("@ss.hasPermi('course:course:edit')")
    @Log(title = "course", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CourseDb courseDb)
    {
        return toAjax(courseDbService.updateCourseDb(courseDb));
    }

    /**
     * 删除course
     */
    @PreAuthorize("@ss.hasPermi('course:course:remove')")
    @Log(title = "course", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(courseDbService.deleteCourseDbByIds(ids));
    }
}
