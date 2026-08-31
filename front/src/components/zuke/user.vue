<template>
  <div class="header">
    <el-form :inline="true" :model="form" class="demo-form-inline">
      <span style="margin-right: 150px">个人信息</span>
    </el-form>
    <br />
    <el-button @click="shuaxin()">
      <el-icon size="30"><Refresh /></el-icon>
    </el-button>
    <el-button @click="editInfo()">修改信息</el-button>
    <el-button type="success" @click="apply()">申请成为房东</el-button>
    <el-button @click="editPass()">修改密码</el-button>
  </div>
  <div class="wrapper">
    <el-table
      :data="tableData"
      border
      style="width: 100%"
      empty-text="暂无数据"
      @selection-change="handleSelectionChange"
    >
      <el-table-column align="center" prop="name" label="用户名" />
      <!-- <el-table-column
        align="center"
        prop="password"
        label="密码"
        width="180"
      /> -->
      <el-table-column align="center" prop="phone" label="手机号" />
      <el-table-column
        align="center"
        prop="idcard"
        label="证件号"
      /><el-table-column align="center" prop="nickname" label="昵称" />
    </el-table>
  </div>
  <el-dialog
    v-model="centerDialogVisible"
    title="确认申请"
    width="30%"
    align-center
    draggable
  >
    <span>点击确定,确认申请成为房东</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="centerDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="queren()"> 确定 </el-button>
      </span>
    </template>
  </el-dialog>
</template>
    
<script setup>
import router from "@/router";
import { ref, onMounted } from "vue";
import { get, post } from "@/api/request";
// 弹出框
const centerDialogVisible = ref(false);
// 表格数据
const tableData = ref([]);

const getData = () => {
  let s = window.sessionStorage.getItem("token-U");
  let user = JSON.parse(s);
  console.log(user);
  get("/api/user/getUserList", { userid: user.id }).then((resp) => {
    console.log(resp.data);
    let list = [];
    list.push(resp.data);
    tableData.value = list;
    console.log(tableData.value)
  });
};
// 修改信息
const editInfo = () => {

    const data = JSON.parse(JSON.stringify(tableData.value))
    console.log(data[0].phone)
    router.push({
    path:"/ugrxx",
    query: {
          phone : data[0].phone,
          idcard : data[0].idcard,
          nickname : data[0].nickname
      }
  });
};
const editPass = () => {
  router.push("/upass");
};
const shuaxin = () => {
  getData();
};
// 申请成为房东
const apply = () => {
  centerDialogVisible.value = true;
};
const queren = () => {
  centerDialogVisible.value = false;
  // 申请成为房东
  alert("申请成功");
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
    