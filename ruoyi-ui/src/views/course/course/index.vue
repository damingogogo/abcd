<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="课程名称" prop="subName">
        <el-input
          v-model="queryParams.subName"
          placeholder="请输入课程名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="之后" prop="subStart">
        <el-date-picker clearable
          v-model="queryParams.subStart"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="在此时间点之后">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="之前" prop="subEnd">
        <el-date-picker clearable
          v-model="queryParams.subEnd"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="在此时间点之前">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['course:course:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['course:course:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['course:course:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['course:course:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="courseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="课程编号" align="center" prop="id" />
      <el-table-column label="课程名称" align="center" prop="subName" />
      <el-table-column label="开课时间" align="center" prop="subStart" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.subStart, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结课时间" align="center" prop="subEnd" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.subEnd, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="任课教师" align="center" prop="subTeacher" />
      <el-table-column label="课程对应学院" align="center" prop="subCollege">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.college" :value="scope.row.subCollege ? scope.row.subCollege.split(',') : []"/>
        </template>
      </el-table-column>
      <el-table-column label="课程对应系别" align="center" prop="subDep">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.dep" :value="scope.row.subDep ? scope.row.subDep.split(',') : []"/>
        </template>
      </el-table-column>
      <el-table-column label="课程对应年级" align="center" prop="subGrade">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.stu_nianji" :value="scope.row.subGrade ? scope.row.subGrade.split(',') : []"/>
        </template>
      </el-table-column>
      <el-table-column label="课程对应班级" align="center" prop="subClass">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.stu_class" :value="scope.row.subClass ? scope.row.subClass.split(',') : []"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['course:course:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['course:course:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改course对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="课程名称" prop="subName">
          <el-input v-model="form.subName" placeholder="请输入课程名称" />
        </el-form-item>
        <el-form-item label="开课时间" prop="subStart">
          <el-date-picker clearable
            v-model="form.subStart"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择开课时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结课时间" prop="subEnd">
          <el-date-picker clearable
            v-model="form.subEnd"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择结课时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="任课教师" prop="subTeacher">
          <el-input v-model="form.subTeacher" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="课程对应院">
          <el-checkbox-group v-model="form.subCollege">
            <el-checkbox
              v-for="dict in dict.type.college"
              :key="dict.value"
              :label="dict.value">
              {{dict.label}}
            </el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="课程对应系别">
          <el-checkbox-group v-model="form.subDep">
            <el-checkbox
              v-for="dict in dict.type.dep"
              :key="dict.value"
              :label="dict.value">
              {{dict.label}}
            </el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="课程对应年级">
          <el-checkbox-group v-model="form.subGrade">
            <el-checkbox
              v-for="dict in dict.type.stu_nianji"
              :key="dict.value"
              :label="dict.value">
              {{dict.label}}
            </el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="课程对应班级">
          <el-checkbox-group v-model="form.subClass">
            <el-checkbox
              v-for="dict in dict.type.stu_class"
              :key="dict.value"
              :label="dict.value">
              {{dict.label}}
            </el-checkbox>
          </el-checkbox-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCourse, getCourse, delCourse, addCourse, updateCourse } from "@/api/course/course";

export default {
  name: "Course",
  dicts: ['stu_nianji', 'stu_class', 'college', 'dep'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // course表格数据
      courseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        subName: null,
        subStart: null,
        subEnd: null,
        subTeacher: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        subName: [
          { required: true, message: "课程名称不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询course列表 */
    getList() {
      this.loading = true;
      listCourse(this.queryParams).then(response => {
        this.courseList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        subName: null,
        subStart: null,
        subEnd: null,
        subTeacher: null,
        subCollege: [],
        subDep: [],
        subGrade: [],
        subClass: []
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加course";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCourse(id).then(response => {
        this.form = response.data;
        this.form.subCollege = this.form.subCollege.split(",");
        this.form.subDep = this.form.subDep.split(",");
        this.form.subGrade = this.form.subGrade.split(",");
        this.form.subClass = this.form.subClass.split(",");
        this.open = true;
        this.title = "修改course";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.subCollege = this.form.subCollege.join(",");
          this.form.subDep = this.form.subDep.join(",");
          this.form.subGrade = this.form.subGrade.join(",");
          this.form.subClass = this.form.subClass.join(",");
          if (this.form.id != null) {
            updateCourse(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCourse(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除course编号为"' + ids + '"的数据项？').then(function() {
        return delCourse(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('course/course/export', {
        ...this.queryParams
      }, `course_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
