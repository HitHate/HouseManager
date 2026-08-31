<template>
  <div class="header">
    <span style="line-height: 30px">添加房源</span>
  </div>
  <el-button type="success" onclick="history.go(-1)">返回</el-button>
  <div class="wrapper">
    <el-form :model="form" label-width="80px" style="width: 300px">
      <el-form-item label="房屋ID:" :rules="[
        { required: true, message: 'ID不能为空' },
        { type: 'number', message: 'ID必须为数字值' },
      ]">
        <el-input v-model="form.houseid" />
      </el-form-item>

      <el-form-item label="面积:" :rules="[
        { required: true, message: '面积不能为空' },
        { type: 'number', message: '面积必须为数字值' },
      ]">
        <el-input v-model="form.area" type="number" />
      </el-form-item>

      <el-form-item label="租金:" :rules="[
        { required: true, message: '租金不能为空' },
        { type: 'number', message: '租金必须为数字值' },
      ]">
        <el-input v-model="form.price" />
      </el-form-item>

      <el-form-item label="地址:" :rules="[{ required: true, message: '地址不能为空' }]">
        <el-cascader placeholder="请选择地区" size="default" :options="regionData" v-model="selectedOptions"
          @change="handleChange">
        </el-cascader>
      </el-form-item>
      <el-form-item label="详细地址:">
        <el-input v-model="form.dizi" :autosize="{ minRows: 4, maxRows: 8 }" maxlength="100" show-word-limit
          type="textarea" placeholder="请输入内容" /></el-form-item>
      <el-form-item label="状态:" :rules="[{ required: true, message: '状态不能为空' }]">
        <el-select v-model="form.status" placeholder="请选择">
          <el-option label="未租赁" value="未租赁" />
          <el-option label="已租赁" value="已租赁" />
        </el-select>
      </el-form-item>

      <div class="btn">
        <el-button type="primary" @click="submitForm(form)" style="margin-right: 50px">提交</el-button>
        <el-button type="warning" native-type="reset">重置</el-button>
      </div>
    </el-form>
  </div>
</template>

<script setup>
import { reactive } from "vue";
import { regionData, CodeToText } from "element-china-area-data";
import { ElMessage } from "element-plus";
import { post } from "@/api/request";

const form = reactive({
  houseid: "",
  area: "",
  price: "",
  address: "",
  dizi: "",
  status: "",
  initialize:""
});


const handleChange = (value) => {
  form.address = CodeToText[value[0]] + CodeToText[value[1]] + CodeToText[value[2]];
  form.initialize = form.address
};


const submitForm = async (form) => {
  console.log(JSON.stringify(form));
  form.address += form.dizi;
  // ajax 请求接口
  post("/api/addhouse", form)
    .then((resp) => {
      form.address = form.initialize;
      if (resp.code === 200) {
        ElMessage.success(resp.message);
      } else if(resp.code === 100) {

        form.houseid = ""
        ElMessage.error(resp.message);
      }else{
        form.dizi = ""
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
  transform: translate(-50%, -50%);
}

.btn {
  position: absolute;
  transform: translate(50%, 50%);
}
</style>