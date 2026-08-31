<template>
  <div class="header">
    <span style="line-height: 30px"
      >修改合同信息，请确定合同信息正确无误再提交！</span
    >
  </div>
  <el-button type="success" onclick="history.go(-1)">返回</el-button>
  <div class="wrapper">
    <el-form :model="form" label-width="180px" style="width: 400px">
      <el-form-item label="房屋ID:">
        <nav>{{ form.house_id }}</nav>
        <!-- <el-input v-model="form.house_id" readonly /> -->
      </el-form-item>

      <el-form-item
        label="地址:"
        :rules="[{ required: true, message: '地址不能为空' }]"
      >
        <el-cascader
          :placeholder="form.address"
          size="default"
          :options="regionData"
          v-model="selectedOptions"
          @change="handleChange"
        >
        </el-cascader>
      </el-form-item>
      <el-form-item label="详细地址:">
        <el-input
          v-model="form.dizi"
          :autosize="{ minRows: 1, maxRows: 2 }"
          maxlength="30"
          show-word-limit
          type="textarea"
          placeholder="请输入内容"
      /></el-form-item>

      <el-form-item
        label="出租方(甲方):"
        :rules="[{ required: true, message: '出租方不能为空' }]"
      >
        <el-input v-model="form.chuzu" placeholder="请输入出租方姓名" />
      </el-form-item>

      <el-form-item
        label="出租方（甲方）证件号码:"
        :rules="[{ required: true, message: '证件号码不能为空' }]"
      >
        <el-input
          v-model="form.chuzu_idcard"
          placeholder="请输入出租方证件号"
        />
      </el-form-item>

      <el-form-item
        label="承租方（乙方）:"
        :rules="[{ required: true, message: '承租方不能为空' }]"
      >
        <el-input v-model="form.zuke_idcard" placeholder="请输入承租方姓名" />
      </el-form-item>

      <el-form-item
        label="承租方（乙方）证件号码:"
        :rules="[{ required: true, message: '证件号码不能为空' }]"
      >
        <el-input v-model="form.zuke" placeholder="请输入承租方证件号" />
      </el-form-item>

      <el-form-item label="起租时间:" required="true"
        ><div class="block">
          <el-date-picker
            v-model="form.fromdate"
            type="date"
            placeholder="选择日期"
            format="YYYY/MM/DD"
            value-format="YYYY-MM-DD"
          />
        </div>
      </el-form-item>

      <el-form-item label="退租日期:" required="true"
        ><div class="block">
          <el-date-picker
            v-model="form.todate"
            type="date"
            placeholder="选择日期"
            format="YYYY/MM/DD"
            value-format="YYYY-MM-DD"
          />
        </div>
      </el-form-item>

      <el-form-item
        label="每月租金:"
        :rules="[
          { required: true, message: '租金不能为空' },
          { type: 'number', message: '租金必须为数字值' },
        ]"
      >
        <el-input
          v-model="form.price"
          placeholder="请输入每月租金"
          type="number"
        />
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
import { onMounted, reactive } from "vue";
import { regionData, CodeToText } from "element-china-area-data";
import { ElMessage } from "element-plus";
import { get, post } from "@/api/request";
import { adminStore } from "@/store/index";
import { useRouter } from "vue-router";
const router = useRouter();
const houselist = adminStore();

const form = reactive({
  house_id: "",
  address: "",
  dizi: "",
  chuzu: "",
  chuzu_idcard: "",
  zuke: "",
  zuke_idcard: "",
  fromdate: "",
  todate: "",
  price: "",
  payday: 10,
  initialize:""
});

// form.house_id = houselist.rowData.house_id;

const handleChange = (value) => {
  form.address =
    CodeToText[value[0]] + CodeToText[value[1]] + CodeToText[value[2]];
    form.initialize = form.address;
};
onMounted(() => {
  console.log("数据");
  Object.assign(form, houselist.rowData);
  console.log(houselist.rowData);
  console.log(form);
});

const submitForm = (form) => {
  console.log(JSON.stringify(form));
  form.address += form.dizi;
  // ajax 请求接口
  post("/api/edithetong", form)
    .then((resp) => {
      form.address = form.initialize;
      if (resp.code === 200) {
        router.push("/zulist");
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