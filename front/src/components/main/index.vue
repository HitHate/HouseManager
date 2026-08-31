<template>
  <!-- <el-carousel interval="2000" type="card" height="700px">
    <el-carousel-item v-for="item in imgData" :key="item">
      <img :src="item.url" alt="" />
    </el-carousel-item>
  </el-carousel> -->

  <el-calendar>
    <template #date-cell="{ data }">
      <p :class="data.isSelected ? 'is-selected' : ''">
        {{ data.day.split("-").slice(1).join("-") }}
        {{ data.isSelected ? "✔️" : "" }}
      </p>
    </template>
  </el-calendar>
</template>
  
<script setup>
const imgData = [
  { url: require("@/assets/img/lub/001.jpg") },
  { url: require("@/assets/img/lub/002.jpg") },
  { url: require("@/assets/img/lub/003.jpg") },
  { url: require("@/assets/img/lub/004.jpg") },
  { url: require("@/assets/img/lub/005.jpg") },
  { url: require("@/assets/img/lub/006.jpg") },
  { url: require("@/assets/img/lub/007.jpg") },
  { url: require("@/assets/img/lub/008.jpg") },
  { url: require("@/assets/img/lub/009.jpg") },
  { url: require("@/assets/img/lub/010.jpg") },
  { url: require("@/assets/img/lub/011.jpg") },
  { url: require("@/assets/img/lub/012.jpg") },
  { url: require("@/assets/img/lub/013.jpg") },
  { url: require("@/assets/img/lub/014.jpg") },
  { url: require("@/assets/img/lub/015.jpg") },
  { url: require("@/assets/img/lub/016.jpg") },
  { url: require("@/assets/img/lub/017.jpg") },
  { url: require("@/assets/img/lub/018.jpg") },
  { url: require("@/assets/img/lub/019.jpg") },
  { url: require("@/assets/img/lub/020.jpg") },
  { url: require("@/assets/img/lub/021.jpg") },
  { url: require("@/assets/img/lub/022.jpg") },
  { url: require("@/assets/img/lub/023.jpg") },
  { url: require("@/assets/img/lub/024.jpg") },
  { url: require("@/assets/img/lub/025.jpg") },
  { url: require("@/assets/img/lub/026.jpg") },
  { url: require("@/assets/img/lub/027.jpg") },
  { url: require("@/assets/img/lub/028.jpg") },
  { url: require("@/assets/img/lub/029.jpg") },
  { url: require("@/assets/img/lub/030.jpg") },
  { url: require("@/assets/img/lub/031.jpg") },
  { url: require("@/assets/img/lub/032.jpg") },
  { url: require("@/assets/img/lub/033.jpg") },
  { url: require("@/assets/img/lub/034.jpg") },
  { url: require("@/assets/img/lub/035.jpg") },
  { url: require("@/assets/img/lub/036.jpg") },
  { url: require("@/assets/img/lub/037.jpg") },
];
</script>

<style scoped>
img {
  width: 100%;
  height: 100%;
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
.is-selected {
  color: #1989fa;
}
</style>