<template>
  <div class="header">
    <span style="line-height: 30px">添加报障信息</span>
  </div>
  <el-button type="success" onclick="history.go(-1)">返回</el-button>
  <div class="wrapper">
    <el-form :model="form" label-width="180px" style="width: 400px">
      <el-form-item label="房屋ID:">
        <nav>{{ form.house_id }}</nav>
      </el-form-item>

      <el-form-item label="地址:">
        <nav>{{ form.address }}</nav>
      </el-form-item>

      <el-form-item label="租客姓名:">
        <nav>{{ form.name }}</nav>
      </el-form-item>

      <el-form-item label="报障日期:" required="true">
        <div class="block">
          <el-date-picker
            v-model="form.date"
            type="datetime"
            placeholder="选择日期时间"
            format="YYYY/MM/DD HH:mm:ss"
            value-format="YYYY-MM-DD HH:mm:ss"
          />
        </div>
      </el-form-item>

      <el-form-item label="报障内容:">
        <el-input
          v-model="form.detail"
          :autosize="{ minRows: 4, maxRows: 8 }"
          maxlength="100"
          show-word-limit
          type="textarea"
          placeholder="请输入内容"
      /></el-form-item>

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
import { userStore } from "@/store/index";
import { useRouter } from "vue-router";
const router = useRouter();
const houselist = userStore();

const form = reactive({
  houseId: "",
  address: "",
  name: "",
  date: "",
  detail: "",
  user_id: "",
});
onMounted(() => {
  form.house_id = houselist.rowData.house_id;
  form.address = houselist.rowData.address;
  form.name = houselist.rowData.userlist.nickname;
  form.user_id = houselist.rowData.user_id;
});

const submitForm = (form) => {
  console.log(JSON.stringify(form));
  // ajax 请求接口
  post("/api/addwrong", form)
    .then((resp) => {
      if (resp.code === 200) {
        ElMessage.success(resp.message);
        router.push("/uwrong");
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
  transform: translate(100%, 50%);
}
</style>