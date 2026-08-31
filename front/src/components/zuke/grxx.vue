<template>
  <el-button type="success" onclick="history.go(-1)" style="">返回</el-button>
  <div class="wrapper">
    <el-form
      :model="ruleForm"
      status-icon
      :rules="rules"
      ref="ruleForm"
      label-width="100px"
      class="demo-ruleForm"
    >
      <el-form-item label="电话" prop="phone">
        <el-input v-model="ruleForm.phone"></el-input>
      </el-form-item>
      <el-form-item label="证件号" prop="idcard">
        <el-input v-model="ruleForm.idcard"></el-input>
      </el-form-item>
      <el-form-item label="昵称" prop="nickname">
        <el-input v-model="ruleForm.nickname"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')"
          >提交</el-button
        >
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { get, post } from "@/api/request";
import { ElMessage } from "element-plus";

export default {
  data() {
    var checkAge = (rule, value, callback) => {

      if (!value) {
        callback();
      }
      setTimeout(() => {
        {
          if (value.length != 11) {
            callback(
              new Error("必须满11位——————" + `还差${11 - value.length}位`)
            );
          } else {
            callback();
          }
        }
      }, 1000);
    };

    var checkId = (rule, value, callback) => {

      console.log(this.$route.params.phone)

      if (!value) {
        callback();
      }
      setTimeout(() => {
        {
          if (value.length != 18) {
            callback(
              new Error("证件号必须满18位——————" + `还差${18 - value.length}位`)
            );
          } else {
            callback();
          }
        }
      }, 1000);
    };

    return {
      ruleForm: {
        phone: this.$route.query.phone,
        idcard: this.$route.query.idcard,
        nickname: this.$route.query.nickname,
      },
      rules: {
        phone: [{ validator: checkAge, trigger: "blur" }],
        idcard: [{ validator: checkId, trigger: "blur" }],
      },
    };
  },

  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 请求axios
          if (
            this.ruleForm.phone == "" &&
            this.ruleForm.idcard == "" &&
            this.ruleForm.nickname == ""
          ) {
            ElMessage.error("信息不能为空");
          } else {
            post("/api/editUser", this.ruleForm).then((resp) => {
              if (resp.code == 200) {
                ElMessage.success(resp.message);
                window.sessionStorage.removeItem("token-U");
                window.sessionStorage.removeItem("token");
                this.$router.push("/login");
              }
            });
          }
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
};
</script>

<style scoped>
.wrapper {
  width: 350px;
  height: 200px;
  position: absolute;
  top: 30%;
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>