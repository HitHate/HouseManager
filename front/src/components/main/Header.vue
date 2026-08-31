<template>
  <div class="header">
    <div class="left">
      <span style="font-size: 20px">{{ state.name }}</span>
    </div>
    <div class="right">
      <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        :ellipsis="false"
        @select="handleSelect"
        style="height: 50.67px"
      >
        <el-menu-item @click="out()">退出登录</el-menu-item>
      </el-menu>
    </div>
  </div>
</template>

<script setup>
import { onMounted, reactive } from "vue";
import { useRouter } from "vue-router";
import { get } from "@/api/request";
import { ElMessage } from "element-plus";

const router = useRouter();
const state = reactive({
  name: "",
});
onMounted(() => {
  let str = window.sessionStorage.getItem("token-U");
  let user = JSON.parse(str);
  state.name = user.nickname;
});
// 退出登录
const out = () => {
  let u = window.sessionStorage.getItem("token-U");
  u = JSON.parse(u);


   
    window.sessionStorage.removeItem("token-U");
    window.sessionStorage.removeItem("token");
    router.push("/login");

};
</script>

<style scoped>
.header {
  height: 50px;
  border-bottom: 1px solid #e9e9e9;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
}
</style>
