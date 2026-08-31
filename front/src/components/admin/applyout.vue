<template>
  <div class="header">
    <el-form :inline="true" :model="form" class="demo-form-inline">
      <span style="margin-right: 150px">退租申请</span>
      <el-form-item label="地址:">
        <el-input v-model="form.search" placeholder="搜索" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
      </el-form-item>
    </el-form>
    <br />
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
      <el-table-column
        align="center"
        prop="house_id"
        label="房屋ID"
        width="100"
      />
      <el-table-column align="center" prop="address" label="地址" />
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
        label="申请人联系电话"
      />
      <el-table-column align="center" prop="status" label="状态" />
      <el-table-column align="center" label="操作">
        <template #default="scope">
          <el-button
            size="small"
            @click="handleEdit(scope.$index, scope.row)"
            v-if="scope.row.status != '已同意' && scope.row.status != '已拒绝'"
            >同意</el-button
          >
          <el-button
            size="small"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
            v-if="scope.row.status != '已同意' && scope.row.status != '已拒绝'"
            >拒绝</el-button
          >
          <el-button
            size="small"
            type="danger"
            @click="del(scope.$index, scope.row)"
            v-if="scope.row.status == '已同意' || scope.row.status == '已拒绝'"
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
  get("/api/applyout", {
    search: form.search,
    cur: cur.value,
    size: size.value,
  }).then((resp) => {
    console.log(resp);
    tableData.value = resp.list;
    total.value = resp.total; //总页数
  });
};

// 点击同意
const handleEdit = (index, row) => {
  console.log(row.house_id)
  get("/api/agreeapplyout", {
    house_id: row.house_id,
  })
    .then((resp) => {
      if (resp.code === 200) {
        getData();
        ElMessage.success(resp.message);
      }
      console.log(resp);
    })
    .catch((msg) => {
      console.log("失败回调==》" + msg);
    });
  console.log(index, row.id);
};
// 拒绝按钮
const handleDelete = (index, row) => {
  get("/api/jujueApplyout", {
    house_id: row.house_id,
  })
    .then((resp) => {
      if (resp.code === 200) {
        getData();
        ElMessage.success(resp.message);
      }
      console.log(resp);
    })
    .catch((msg) => {
      console.log("失败回调==》" + msg);
    });
  console.log(index, row);
};
// 删除按钮
const del = (index, row) => {
  get("/api/delapplyout", { house_id: row.house_id })
    .then((resp) => {
      if (resp.code === 200) {
        getData();
        ElMessage.success("删除成功^v^");
      }
      console.log("成功");
      console.log(resp);
    })
    .catch((msg) => {
      console.log("失败回调==》" + msg);
    });
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
  