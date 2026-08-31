<template>
  <div class="layout">
    <el-container class="container">
      <el-aside class="aside">
        <div class="head">
          <el-affix offset="15">
            <span>房屋出租系统WY</span>
          </el-affix>
        </div>
        <div class="line" />
        <el-menu
          background-color="#222832"
          default-active="/"
          text-color="#fff"
          active-text-color="red"
          :router="true"
        >
          <el-menu-item index="/">
            <el-icon><icon-menu /></el-icon>
            <h1>首页</h1>
          </el-menu-item>
          <el-sub-menu index="1">
            <template #title>
              <span>房源菜单</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/houselist"
                ><el-icon></el-icon>房源列表</el-menu-item
              >
              <el-menu-item index="/addhouse"
                ><el-icon></el-icon>添加房源</el-menu-item
              >
            </el-menu-item-group>
          </el-sub-menu>
          <el-sub-menu index="2">
            <template #title>
              <span>租赁及合同信息</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/zulist"
                ><el-icon></el-icon>在租列表</el-menu-item
              >
              <el-menu-item index="/checkout"
                ><el-icon></el-icon>已退租列表</el-menu-item
              >
            </el-menu-item-group>
          </el-sub-menu>
          <el-sub-menu index="3">
            <template #title>
              <span>看房及退租记录</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/applylist"
                ><el-icon></el-icon>看房记录</el-menu-item
              >
              <el-menu-item index="/applyout"
                ><el-icon></el-icon>退租记录</el-menu-item
              >
            </el-menu-item-group>
          </el-sub-menu>
          <el-sub-menu index="4">
            <template #title>
              <span>保障模块</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/wrong"
                ><el-icon></el-icon>待处理保障</el-menu-item
              >
              <el-menu-item index="/solve"
                ><el-icon></el-icon>已处理保障</el-menu-item
              >
            </el-menu-item-group>
          </el-sub-menu>
          <el-sub-menu index="5">
            <template #title>
              <span>租金信息</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/showddpaid"
                ><el-icon></el-icon>我要收租</el-menu-item
              >
              <el-menu-item index="/topaid"
                ><el-icon></el-icon>租客待缴租金</el-menu-item
              >
              <el-menu-item index="/paid"
                ><el-icon></el-icon>租客已缴租金</el-menu-item
              >
            </el-menu-item-group>
          </el-sub-menu>
          <el-sub-menu index="6">
            <template #title>
              <span>我的日程</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/schedule"
                ><el-icon></el-icon>查看日程</el-menu-item
              >
              <el-menu-item index="/addschedule"
                ><el-icon></el-icon>添加日程</el-menu-item
              >
            </el-menu-item-group>
          </el-sub-menu>
          <el-menu-item index="/admin">个人信息</el-menu-item>
        </el-menu>
      </el-aside>
      <el-container class="content">
        <Header />
        <div class="main">
          <router-view />
        </div>
        <Footer />
      </el-container>
    </el-container>
  </div>
  <div id="aplayer"></div>
</template>
  
<script setup>
import Header from "@/components/main/Header.vue";
import Footer from "@/components/main/Footer.vue";
import { get } from "@/api/request";
import { onMounted } from "vue";

// 音乐播放器
onMounted(() => {
  musicdata();
});
// 音乐
const musicdata = () => {
  get("/api/music").then((result) => {
    console.log(result.data);
    var ap = new APlayer({
      container: document.getElementById("aplayer"),
      mini: true,
      fixed: true,
      order: "random",
      audio: result.data,
    });
    ap.play();
  });
};
</script>
  
  <style scoped>
.layout {
  min-height: 100vh;
  background-color: #ffffff;
}
.container {
  height: 100vh;
}
.aside {
  width: 200px !important;
  background-color: #222832;
}
.head {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 50px;

  /* background-image: radial-gradient(transparent 1px #fff 1px);
  background-size: 4px 4px;
  backdrop-filter: saturate(50%) blur(4px); */
}
.head > div {
  display: flex;
  align-items: center;
}
.head img {
  width: 50px;
  height: 50px;
  margin-right: 10px;
}
.head span {
  font-size: 20px;
  color: #ffffff;
}
.line {
  border-top: 1px solid hsla(0, 0%, 100%, 0.05);
  border-bottom: 1px solid rgba(0, 0, 0, 0.2);
}
.content {
  display: flex;
  flex-direction: column;
  max-height: 100vh;
  overflow: hidden;
}
.main {
  height: calc(100vh - 100px);
  overflow: auto;
  padding: 10px;
}
body {
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}
.el-menu {
  border-right: none !important;
}
.el-submenu {
  border-top: 1px solid hsla(0, 0%, 100%, 0.05);
  border-bottom: 1px solid rgba(0, 0, 0, 0.2);
}
.el-submenu:first-child {
  border-top: none;
}
.el-submenu [class^="el-icon-"] {
  vertical-align: -1px !important;
}
a {
  color: #409eff;
  text-decoration: none;
}
.el-pagination {
  text-align: center;
  margin-top: 20px;
}
.el-popper__arrow {
  display: none;
}
/* 侧边栏滚动条隐藏 */
::-webkit-scrollbar {
  display: none;
}
</style>