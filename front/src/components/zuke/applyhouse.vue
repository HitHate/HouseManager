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
        <!-- <el-input v-model="form.housed" readonly /> -->
      </el-form-item>

      <el-form-item label="地址:">
        <nav>{{ form.address }}</nav>
      </el-form-item>

      <el-form-item label="面积:">
        <nav>{{ form.area }}平方米</nav>
      </el-form-item>

      <el-form-item label="租金:">
        <nav>{{ form.price }}元</nav>
      </el-form-item>

      <el-form-item
        label="承租方（乙方）:"
        :rules="[{ required: true, message: '承租方不能为空' }]"
      >
        <!-- <el-input v-model="form.zuke" placeholder="请输入承租方姓名" /> -->
        <nav>{{ form.zuke  }}</nav>
      </el-form-item>

      <el-form-item
        label="承租方（乙方）证件号码:"
        :rules="[{ required: true, message: '证件号码不能为空' }]"
      >
        <nav>{{ form.zuke_idcard }}</nav>
        <!-- <el-input v-model="form.zukeIdcard" placeholder="请输入承租方证件号" /> -->
      </el-form-item>

      <el-form-item label="起租时间:" required="true"
        ><div class="block">
          <el-date-picker
            v-model="form.fromdate"
            type="date"
            placeholder="选择日期"
            format="YYYY/MM/DD"
            value-format="YYYY-MM-DD"
            :disabledDate="disablesDate"
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
            :disabledDate="disablesDate"
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
import { userStore } from "@/store/index";
import { userInfo } from "@/store/info";
import { useRouter } from "vue-router";
const router = useRouter();
const houselist = userStore();
const userInfo1 = userInfo();
function disablesDate(time) {
  const _minTime = Date.now() + 24 * 60 * 60 * 1000 * 14; //最小时间可选前七天
  const _maxTime = Date.now() - 24 * 60 * 60 * 1000 * 1; //最大时间可选今天
  return time.getTime() <= _maxTime || time.getTime() > _minTime;
}
const form = reactive({
  house_id: "",
  address: "",
  zuke: "",
  area: "",
  zuke_idcard: "",
  fromdate: "",
  todate: "",
  price: "",
  payday:""
});
onMounted(() => {

  console.log(userInfo1.Info);
  console.log(houselist.rowData);
  form.house_id = houselist.rowData.houseid;
  form.address = houselist.rowData.address;
  form.area = houselist.rowData.area;
  form.price = houselist.rowData.price;
  form.zuke = userInfo1.Info.nickname;
  form.zuke_idcard = userInfo1.Info.idcard;
});

const submitForm = (form) => {
  console.log(JSON.stringify(form));
  if (form.todate == "" || form.fromdate == "") {
    ElMessage.error("日期不能为空");
  } else {
    let day =
      (new Date(form.todate) - new Date(form.fromdate)) / 1000 / 60 / 60 / 24;
    form.price = day * form.price;

    form.payday = day

    // ajax 请求接口
    post("/api/applycheckuserlist", form)
      .then((resp) => {
        if (resp.code === 200) {
          router.push("/uapplylist");
          ElMessage.success(resp.message);
        } else {
          ElMessage.error(resp.message);
        }
        console.log(resp);
      })
      .catch((msg) => {
        console.log("失败回调==》" + msg);
      });
  }

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