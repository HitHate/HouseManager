<template>
  <div class="header">
    <span style="line-height: 30px">添加日程</span>
  </div>
  <div class="wrapper">
    <el-form :model="form" label-width="80px" style="width: 400px">
      <el-form-item
        label="日期:"
        :rules="[{ required: true, message: '日期不能为空' }]"
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
      <el-form-item
        label="日程:"
        :rules="[{ required: true, message: '日程不能为空' }]"
      >
        <el-input
          v-model="form.content"
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
import { post } from "@/api/request";
import { ElMessage } from "element-plus";
import router from "@/router";
import { adminStore } from "@/store";
const adminStore1 = adminStore();
const form = reactive({
  date: "",
  content: "",
});
onMounted(() => {
  Object.assign(form, adminStore1.rowData);
});
const submitForm = (form) => {
  if (form.date == "" || form.content == "") {
    ElMessage.error("内容不能为空");
  } else {
    post("/api/editschedule", form).then((resp) => {
      if (resp.code == 200) {
        router.push("/schedule");
        ElMessage.success(resp.message);
      }
      console.log(resp);
    });
  }
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