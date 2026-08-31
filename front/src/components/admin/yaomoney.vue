<template>
  <div class="header">
    <span style="line-height: 30px">添加收租信息</span>
  </div>
  <el-button type="success" onclick="history.go(-1)">返回</el-button>
  <div class="wrapper">
    <el-form :model="form" label-width="180px" style="width: 400px">
      <el-form-item label="房屋ID:" required>
        <nav>{{ form.house_id }}</nav>
      </el-form-item>

      <el-form-item label="地址:" required>
        <nav>{{ form.address }}</nav>
      </el-form-item>

      <el-form-item label="租金:" required>
        <nav>{{ form.price }}元</nav>
      </el-form-item>

      <el-form-item label="租客姓名:" required>
        <nav>{{ form.name }}</nav>
      </el-form-item>

      <el-form-item label="租金交付截止日期:" required="true"
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

      <div class="btn">
        <el-button
          type="primary"
          @click="submitForm(form)"
          style="margin-right: 50px"
          >提交</el-button
        >
        <el-button type="warning" native-type="reset">重置</el-button>
      </div>
    </el-form>
  </div>
</template>
      
<script setup>
import { onMounted, reactive } from "vue";
import { ElMessage } from "element-plus";
import { get, post } from "@/api/request";
import { adminStore } from "@/store/index";
import { useRouter } from "vue-router";
const router = useRouter();
const houselist = adminStore();

const form = reactive({
  house_id: "",
  address: "",
  name: "",
  date: "",
  price: "",
  user_id: "",
});
onMounted(() => {
  form.house_id = houselist.rowData.house_id;
  form.address = houselist.rowData.address;
  form.price = houselist.rowData.price;
  form.name = houselist.rowData.userlist.nickname;
  form.user_id = houselist.rowData.user_id;
});

const submitForm = (form) => {
  console.log(JSON.stringify(form));
  // ajax 请求接口
  post("/api/addtopaid", form)
    .then((resp) => {
      if (resp.code == 200) {
        router.push("/topaid");
        ElMessage.success(resp.message);
      } else if (resp.code == 201) {
        ElMessage.error(resp.message);
      }
      console.log(resp);
    })
    .catch((msg) => {
      console.log("失败回调==》" + msg);
    });
  console.log(form);
};
</script>
      <style scoped>
.header {
  height: 30px;
  border: 1px solid #eee;
  padding: 10px;
  border-radius: 6px;
  margin-bottom: 10px;
}
.wrapper {
  width: 200px;
  height: 200px;
  position: absolute;
  top: 30%;
  left: 50%;
  transform: translate(-80%, -50%);
}
.btn {
  position: absolute;
  transform: translate(50%, 50%);
}
</style>