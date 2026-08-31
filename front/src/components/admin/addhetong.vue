<template>
  <div class="header">
    <span style="line-height: 30px"
      >添加合同信息，请确定合同信息正确无误再提交！</span
    >
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

      <el-form-item label="租金:">
        <nav>{{ form.price }}元</nav>
      </el-form-item>

      <el-form-item label="出租方(甲方):">
        <nav>{{ form.chuzu }}</nav>
      </el-form-item>

      <el-form-item label="出租方（甲方）证件号码:">
        <nav>{{ form.chuzu_idcard }}</nav>
      </el-form-item>

      <el-form-item
        label="每月交租日（数字）:"
        :rules="[{ required: true, message: '交租日不能为空' }]"
      >
        <el-input
          v-model="form.payday"
          placeholder="请输入每月交租日"
          type="number"
          max="31"
          min="1"
        />
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
import { reactive, onMounted } from "vue";
import { ElMessage } from "element-plus";
import { get, post } from "@/api/request";
import { adminStore } from "@/store/index";
import { adminInfo } from "@/store/info";

import { useRouter } from "vue-router";
const router = useRouter();
const adminInfo1 = adminInfo();
const houselist = adminStore();

const form = reactive({
  house_id: "",
  address: "",
  dizi: "",
  chuzu: "",
  chuzu_idcard: "",
  price: "",
  payday: 10,
});

onMounted(() => {
  console.log(adminInfo1.Info);
  form.house_id = houselist.rowData.house_id;
  form.address = houselist.rowData.address;
  form.price = houselist.rowData.price;
  form.chuzu = adminInfo1.Info.nickname;
  form.chuzu_idcard = adminInfo1.Info.idcard;
});

const submitForm = (form) => {
  console.log(JSON.stringify(form));
  // ajax 请求接口
  post("/api/addhetong", form)
    .then((resp) => {
      if (resp.code === 200) {
        router.push("/applylist");
        ElMessage.success(resp.message);
      } else {
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