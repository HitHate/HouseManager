<template>
  <div class="header">
    <el-form :inline="true" :model="form" class="demo-form-inline">
      <span style="margin-right: 150px">查看日程</span>
      <el-form-item label="日期:"
        ><div class="block">
          <el-date-picker
            v-model="form.date"
            type="date"
            placeholder="选择日期"
            format="YYYY/MM/DD"
            value-format="YYYY-MM-DD"
          />
        </div>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
      </el-form-item>
    </el-form>
    <br />
    <el-button type="danger" @click="deletes()">批量删除</el-button>
    <el-button @click="shuaxin()">
      <el-icon size="30"><Refresh /></el-icon>
    </el-button>
  </div>
  <div class="wrapper">
    <el-table
      :data="tableData"
      border
      style="width: 100%"
      empty-text="暂无数据"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" />
      <el-table-column align="center" prop="date" label="日期" />
      <el-table-column align="center" prop="content" label="日程" />
      <el-table-column align="center" label="操作">
        <template #default="scope">
          <el-button size="small" @click="handleEdit(scope.$index, scope.row)"
            >修改</el-button
          >
          <el-button
            size="small"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </div>
  <div class="block">
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="cur"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="size"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
    >
    </el-pagination>
  </div>
</template>
  
<script setup>
import { reactive, ref, onMounted } from "vue";
import { get, post } from "@/api/request";
import router from "@/router";
import { adminStore } from "@/store";
const adminStore1 = adminStore();
const form = reactive({
  search: "",
  date: "",
});
// 刷新
const shuaxin = () => {
  form.search = "";
  form.date = "";
  getData();
};
// 查询
const onSubmit = () => {
  console.log(form.search);
  console.log(form.date);
  console.log("点击查询");
  getData()
};

const tableData = ref([]);
// 分页数据
const total = ref(100), //数据总数
  size = ref(10), //每页的数据条数
  cur = ref(1); //当前页
// 表格数据
const getData = async () => {
  console.log(cur.value + "<=====cur");
  console.log(size.value + "<=====size");
  console.log(total.value + "<=====total");
  get("/api/schedulelist", {
    search: form.date,
    cur: cur.value,
    size: size.value,
  }).then((resp) => {
    console.log(resp);
    let data = resp.list;
    tableData.value = data;
    total.value = resp.total; //总页数
  });
};
// 表格全选
const multipleSelection = ref([]);
const handleSelectionChange = (val) => {
  multipleSelection.value = val;
  console.log(val);
};
// 批量删除
const deletes = () => {
  let list = [];
  multipleSelection.value.forEach((item) => list.push(item.date));
  console.log(list);
  // 调用ajax 批量删除
  del("/api/delSchedule", list);
};
// 点击删除按钮
const handleDelete = (index, row) => {
  let list = [];
  list.push(row.date);
  del("/api/delSchedule", list);
  console.log(index, row);
};
// 删除 功能
const del = (url, data) => {
  post(url, data)
    .then((resp) => {
      if (resp.code === 200) {
        getData();
        ElMessage.success(resp.message);
      } else {
        ElMessage.error(resp.message);
      }
      console.log("成功");
      console.log(resp);
    })
    .catch((msg) => {
      console.log("失败回调==》" + msg);
    });
};
// 修改
const handleEdit = (index, row) => {
  adminStore1.changeRowData(row);
  router.push("/editschedule");
  console.log(index, row);
};
// 分页
function handleSizeChange(val) {
  size.value = val;
  getData();
  console.log(`每页 ${val} 条`);
}
function handleCurrentChange(val) {
  cur.value = val;
  getData();
  console.log(`当前页: ${val}`);
}
// 初始化表格数据
onMounted(() => {
  getData();
});
</script>
  <style scoped>
.header {
  border: 1px solid #eee;
  padding: 10px;
  border-radius: 6px;
}
.el-form-item {
  margin-bottom: 0;
}
</style>
  
    
    