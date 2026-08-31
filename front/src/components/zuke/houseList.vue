<template>
  <el-affix offset="60">
    <div class="header">
      <el-form :inline="true" :model="form" class="demo-form-inline">
        <span style="margin-right: 150px">房源列表</span>
        <el-form-item label="地址搜索:">
          <el-input v-model="form.search" placeholder="搜索" />
        </el-form-item>
        <el-form-item label="状态:">
          <el-select v-model="form.select" placeholder="请选择" clearable>
            <el-option label="已租赁" value="已租赁" aria-checked="" />
            <el-option label="未租赁" value="未租赁" />
            <el-option label="申请中" value="申请中" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">查询</el-button>
        </el-form-item>
      </el-form>
      <br />
      <el-button @click="shuaxin()">
        <el-icon size="30">
          <Refresh />
        </el-icon>
      </el-button>
    </div>
  </el-affix>
  <div class="wrapper">
    <el-table :data="tableData" border style="width: 100%; text-align: center" empty-text="暂无数据"
      @selection-change="handleSelectionChange">
      <el-table-column align="center" prop="houseid" label="房间号" width="180" />
      <el-table-column align="center" prop="address" label="地址" width="220" />
      <el-table-column align="center" prop="area" label="面积(m²)" />
      <el-table-column align="center" prop="price" label="价格 (元)" />
      <el-table-column align="center" prop="status" label="状态" />
      <el-table-column align="center" label="操作">
        <template #default="scope">
          <el-button size="small" type="warning" @click="handleEdit(scope.$index, scope.row)"
            v-if="scope.row.status == '未租赁'">申请看房</el-button>
          <nav v-else-if="scope.row.status == '申请中'">该房已被申请</nav>
          <nav v-else="scope.row.status == '已租赁'">该房已被租赁</nav>
        </template>
      </el-table-column>
    </el-table>
  </div>
  <el-affix position="bottom" offset="48">
    <div class="demo-pagination-block">
      <el-pagination :current-page="cur" :page-size="size" :page-sizes="[5, 10, 20, 50, 100]"
        layout="total, sizes, prev, pager, next, jumper" :total="total" @size-change="handleSizeChange"
        @current-change="handleCurrentChange" />
    </div>
  </el-affix>
  <el-dialog v-model="centerDialogVisible" title="申请看房" width="30%" align-center draggable>
    <span>点击确定进入申请看房</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="centerDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="queren()"> 确定 </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import { reactive, ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import { userStore } from "@/store/index";

import { get, post } from "@/api/request";
const houselist = userStore();
const router = useRouter();
// 弹出框
const centerDialogVisible = ref(false);
// 搜索框
const form = reactive({
  search: "",
  select: "",
});
// 刷新
const shuaxin = () => {
  form.search = "";
  form.select = "";
  getData();
};
const tableData = ref([]);
// 搜索
const onSubmit = async () => {
  console.log(form.search);
  console.log(form.select);
  getData();
};
// 分页数据
const total = ref(100), //数据总数
  size = ref(10), //每页的数据条数
  cur = ref(1); //当前页
// 表格数据
function getData() {
  console.log(cur.value + "<=====cur");
  console.log(size.value + "<=====size");1
  console.log(total.value + "<=====total");
  post("/api/houselist", {
    search: form.search,
    select: form.select,
    cur: cur.value,
    size: size.value,
  }).then((resp) => {
    console.log(resp)
    let data = resp.list;
    tableData.value = data;
    total.value = resp.total; //总页数
  });
}
// 表格全选
let multipleSelection = ref([]);
const handleSelectionChange = (val) => {
  multipleSelection.value = val;
  console.log(multipleSelection.value);
};

// 申请看房
const handleEdit = (index, row) => {
  centerDialogVisible.value = true;
  console.log(row)
  houselist.changeRowData(row);
};

const queren = () => {
  centerDialogVisible.value = false;
  router.push("/uapplyhouse");
};
// 分页
function handleSizeChange(val) {
  size.value = val;
  getData();
  console.log(`每页 ${size} 条`);
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
