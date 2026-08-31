<template>
  <div class="header">
    <el-form :inline="true" :model="form" class="demo-form-inline">
      <span style="margin-right: 150px">我的租赁</span>
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
      empty-text="您还没有租赁您的房屋,请前往房源列表租赁"
      @selection-change="handleSelectionChange"
    >
      <el-table-column
        align="center"
        prop="house_id"
        label="房间号"
        width="100"
      />
      <el-table-column align="center" prop="address" label="地址" width="180" />
      <el-table-column align="center" prop="price" label="价格" />
      <el-table-column align="center" prop="userlist.nickname" label="租赁人" />
      <el-table-column
        align="center"
        prop="userlist.idcard"
        label="租赁人身份证号"
      />
      <el-table-column
        align="center"
        width="150"
        prop="userlist.phone"
        label="租赁人联系电话"
      />
      <el-table-column align="center" label="操作">
        <template #default="scope">
          <nav v-if="scope.row.status == '退租中'">退租申请中</nav>
          <nav v-else>
          <el-button size="small" @click="handleEdit(scope.$index, scope.row)" 
            >查看合同</el-button>
          <el-button
            size="small"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)" 
            >申请退租</el-button
          >
           </nav>
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
import { ElMessage } from "element-plus";
import { userStore } from "@/store/index";
import router from "@/router";

const houselist = userStore();
const status=""
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
const getData = () => {
  console.log(cur.value + "<=====cur");
  console.log(size.value + "<=====size");
  console.log(total.value + "<=====total");
  let s = window.sessionStorage.getItem("token-U");
  let user = JSON.parse(s);
  get("/api/zulist", {
    search: form.search,
    cur: cur.value,
    size: size.value,
    username: user.nickname,
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
// 查看合同
const handleEdit = (index, row) => {
	console.log("house_id:"+row.house_id);
  get("/api/findHetong", {
    contract_id: row.contract_id
  }).then((resp) => {
    console.log(resp);
    if (resp.code == 200) {
      console.log(resp.data)
      ElMessage.success(resp.message)
      houselist.changeRowData(resp.data);
    }
  });
  router.push("/uhetong");
  console.log(row);
};

// 申请退租
const handleDelete = (index, row) => {
  alert("用户");
  get("/api/addApplyout", { contract_id: row.contract_id })
    .then((resp) => {
      if (resp.code == 200) {
        getData();
        ElMessage.success(resp.message);
      }
    })
    .catch((msg) => {
      console.log("错误");
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
  