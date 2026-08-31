<template>
  <div class="header">
    <el-form :inline="true" class="demo-form-inline">
      <span style="margin-right: 150px">租客未缴列表</span>
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
      empty-text="您没有待缴的租金"
      @selection-change="handleSelectionChange"
    >
      <el-table-column
        align="center"
        prop="house_id"
        label="房屋ID"
        width="180"
      />
      <el-table-column align="center" prop="address" label="地址" />
      <el-table-column align="center" prop="price" label="应缴租金" />
      <el-table-column align="center" prop="date" label="租金应缴日期" />
      <el-table-column align="center" prop="name" label="租赁人姓名" />
      <el-table-column align="center" prop="status" label="状态" />
      <el-table-column align="center" label="操作">
        <template #default="scope">
          <el-button
            size="small"
            type="warning"
            @click="handleEdit(scope.$index, scope.row)"
            >支付租金</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </div>

  <el-dialog
    v-model="centerDialogVisible"
    title="支付租金"
    width="30%"
    align-center
    draggable
  >
    <span>点击确定支付租金</span>
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
import { get, post } from "@/api/request";
import { ElMessage } from "element-plus";

// 刷新
const shuaxin = () => {
  getData();
};
// 弹出框
const centerDialogVisible = ref(false);
// 查询
const onSubmit = () => {
  console.log(form.search);
  console.log("点击查询");
  getData();
};
const tableData = ref([]);

// 表格数据
const getData = () => {
  let user = window.sessionStorage.getItem("token-U");
  let nickname = JSON.parse(user).nickname;
  get("/api/topaidlistU", { nickname: nickname }).then((resp) => {
    console.log(resp);
    tableData.value = resp.data;
  });
};
const r = reactive({
  house_id: "",
});
// 支付租金
const handleEdit = (index, row) => {
  centerDialogVisible.value = true;
  r.house_id = row.house_id;
  console.log(index, row);
};
const queren = () => {
  centerDialogVisible.value = false;
  get("/api/gotopay", {
    house_id: r.house_id,
  }).then((resp) => {
    if (resp.code == 200) {
      getData();
      ElMessage.success(resp.message);
    }
    console.log(resp);
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
  