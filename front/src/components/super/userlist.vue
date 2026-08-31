<template>
  <div class="header">
    <el-form :inline="true" :model="form" class="demo-form-inline">
      <span style="margin-right: 150px">账号信息</span>
    </el-form>
    <br />
    <el-button @click="addUser = true">添加账号</el-button>
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
      <el-table-column align="center" prop="name" label="用户名" />
      <el-table-column
        align="center"
        prop="password"
        label="密码"
        width="180"
      />
      <el-table-column align="center" prop="phone" label="手机号" />
      <el-table-column
        align="center"
        prop="idcard"
        label="证件号"
      /><el-table-column align="center" prop="nickname" label="昵称" />
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
  <el-dialog v-model="addUser" title="添加账户">
    <el-form :model="form">
      <el-form-item label="账号" :label-width="formLabelWidth">
        <el-input v-model="form.name" autocomplete="off" />
      </el-form-item>
      <el-form-item label="密码" :label-width="formLabelWidth">
        <el-input v-model="form.pass" autocomplete="off" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="addUser = false">取消</el-button>
        <el-button type="primary" @click="add"> 确定 </el-button>
      </span>
    </template>
  </el-dialog>
  <el-dialog v-model="editUser" title="修改信息">
    <el-form :model="form">
      <el-form-item label="账号" :label-width="formLabelWidth">
        <el-input v-model="form.name" autocomplete="off" />
      </el-form-item>
      <el-form-item label="密码" :label-width="formLabelWidth">
        <el-input v-model="form.pass" autocomplete="off" />
      </el-form-item>
      <el-form-item label="手机号" :label-width="formLabelWidth">
        <el-input v-model="form.phone" autocomplete="off" />
      </el-form-item>
      <el-form-item label="证件号" :label-width="formLabelWidth">
        <el-input v-model="form.idcard" autocomplete="off" />
      </el-form-item>
      <el-form-item label="昵称" :label-width="formLabelWidth">
        <el-input v-model="form.nickname" autocomplete="off" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="editUser = false">取消</el-button>
        <el-button type="primary" @click="queren()"> 确定 </el-button>
      </span>
    </template>
  </el-dialog>
</template>
      
  <script setup>
import router from "@/router";
import { ref, onMounted, reactive } from "vue";
import { get, post } from "@/api/request";
import { ElMessage } from "element-plus";

// 弹出框
const addUser = ref(false);
const editUser = ref(false);
const formLabelWidth = "140px";
const form = reactive({
  id: "",
  name: "",
  pass: "",
  phone: "",
  idcard: "",
  nickname: "",
});
// 表格数据
const tableData = ref([]);
const shuaxin = () => {
  getData();
};
const getData = () => {
// 查询所有用户以及房东
get("/api/findallUser").then((resp) => {
    tableData.value = resp.data;
    console.log(resp);
});
};
// 添加
const add = () => {
  if (form.name == "" || form.pass == "") ElMessage.error("信息不为空");
  else
    get("/api/addUser", { name: form.name, pass: form.pass }).then((resp) => {
      if (resp.code == 200) {
        addUser = false;
        ElMessage.success(resp.message);
      }
    });
};
// 修改信息
const handleEdit = (index, row) => {
  alert("整改中");
};
// const queren = () => {
//   alert(JSON.stringify(form));
//   post("/api/editUserInfo", form).then((resp) => {
//     alert(resp.message);
//   });
// };
// 删除
const handleDelete = (index, row) => {
  get("/api/delUserInfo", { id: row.user_id }).then((resp) => {
    ElMessage.success(resp.message);
    getData();
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
.el-button--text {
  margin-right: 15px;
}
.el-select {
  width: 300px;
}
.el-input {
  width: 300px;
}
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>
      