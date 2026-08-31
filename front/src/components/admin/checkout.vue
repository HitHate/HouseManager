<template>
  <div class="header">
    <el-form :inline="true" :model="form" class="demo-form-inline">
      <span style="margin-right: 150px">已退租列表</span>
      <el-form-item label="地址:">
        <el-input v-model="form.search" placeholder="搜索" />
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
      ><el-table-column type="selection" width="55" />
      <el-table-column
        align="center"
        prop="house_id"
        label="房屋ID"
        width="180"
      />
      <el-table-column align="center" prop="address" label="地址" width="180" />
      <el-table-column
        align="center"
        prop="userlist.nickname"
        label="申请人姓名"
      />
      <el-table-column
        align="center"
        prop="userlist.idcard"
        label="申请人身份证号"
      />
      <el-table-column
        align="center"
        prop="userlist.phone"
        label="申请人电话号码"
      /><el-table-column align="center" prop="status" label="状态" />
      <el-table-column align="center" label="操作">
        <template #default="scope">
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
  <el-affix position="bottom" :offset="48">
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
  </el-affix>
</template>
      
    <script setup>
import { reactive, ref, onMounted } from "vue";
import { get, post } from "@/api/request";
const form = reactive({
  search: "",
});
// 刷新
const shuaxin = () => {
  form.search = "";
  getData();
};
// 查询
const onSubmit = () => {
  console.log(form.search);
  console.log("点击查询");
  getData();
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
  get("/api/checkout", {
    search: form.search,
    cur: cur.value,
    size: size.value,
  }).then((resp) => {
    console.log(resp);
    tableData.value = resp.list;
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
  multipleSelection.value.forEach((item) => list.push(item.house_id));
  console.log(list);
  // 调用ajax 批量删除
  del("/api/deleteCheck", list);
};
const handleDelete = (index, row) => {
  let list = [];
  list.push(row.house_id);
  del("/api/deleteCheck", list);
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
// 删除
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
      