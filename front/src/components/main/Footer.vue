<template>
  <div class="footer">
    <div class="left">
      <iframe
        scrolling="no"
        src="http://tianqiapi.com/api.php?style=tu&skin=pitaya&city=怀化"
        frameborder="0"
        width="350"
        height="24"
        allowtransparency="true"
      ></iframe>
    </div>
    <div @click="saying()" style="cursor: pointer">
      {{ content }} -- &lt&lt{{ where }}>>
    </div>
    <div class="right">望月</div>
  </div>
</template>

<script setup>
import { get } from "@/api/request";
import { onMounted, ref } from "vue";
// 每日一言
const content = ref(),author = ref(),where = ref();
const saying = () => {
  get("https://v1.hitokoto.cn", {}).then((resp) => {
    content.value = resp.hitokoto;
    where.value = resp.from;
  });
};
// 定时 一分钟刷新
setInterval(() => {
  saying();
}, 1000 * 60);

onMounted(() => {
  saying();
});

</script>

<style scoped>
.footer {
  height: 50px;
  border-top: 1px solid #e9e9e9;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
}
</style>