<template>
  <div id="pdf">
    <form name="hetong" action="/hetong/updatehetong.action">
      <div class="title">房屋租赁合同</div>
      <input type="hidden" name="house_id" :value="hetong.house_id" />
      <p>
        出租方：<span name="chuzu">{{ hetong.chuzu }}</span
        >（以下简称甲方）；
      </p>
      　　　
      <p>
        承租方：<span name="zuke">{{ hetong.zuke }}</span
        >（以下简称乙方）。　
      </p>
      　
      <p>
        租赁房屋地址：<span address="zuke">{{ hetong.address }}</span
        >　
      </p>
      　
      <p>
        依据《中华人民共和国合同法》有关条款，甲乙双方经过充分协商，就乙方租赁甲方房屋事宜达成如下协议：
      </p>
      <p>一、乙方租赁甲方房屋，以下简称租赁房屋。</p>
      <p>
        二、起租时间：<span name="fromdate">{{ hetong.fromdate }}</span
        >，退租时间：<span name="todate">{{ hetong.todate }}</span
        >。 在合同签订之日，乙方需向甲方支付房屋租金￥<span name="price">{{
          hetong.price
        }}</span
        >。如续租，请提前3天通知甲方，并重新办理租赁手续。<br />
      </p>
      　　　　
      <p>三、违约处理：</p>
      　
      <p>甲方违约：</p>
      　
      <p>
        1．如甲方未能及时将押金退还乙方，甲方每天应按押金的2％向乙方支付违约金；
      </p>
      <p>
        2．如甲方提供的维修服务项目与协议不相符，乙方有权提前退租，甲方应全额退还剩余的租金。
      </p>
      <p>乙方违约：</p>
      　　
      <p>
        1．如乙方不按时缴纳租金，甲方有权在其押金中扣除，同时收回房屋，所有损失由乙方负责；
      </p>
      　
      <p>
        2．如果乙方在租赁期限未满时退租，甲方仅退还剩余租金的40％（租期未满一个月的不在此例）；
      </p>
      　
      <p>
        四、本协议书一式两份，甲乙双方各执一份；本协议甲乙双方签字后生效。补充协议亦属于合同的一部分。
      </p>
      　 　<br />　　甲方（盖章）：_________　　　　　　　　乙方（盖章）：_________　　<br />　
      　法定代表人（签字）：_________　　　　　法定代表人（签字）：_________　　<br />　　
      担保人（签章）：_________　　<br />　　
      _________年____月____日　　　　　　　　_________年____月____日　　<br />　　
      签订地点：_________　　　　　　　　　　签订地点：_________
    </form>
  </div>
  <div class="button">
    <el-button type="success" @click="editHetong()">修改合同</el-button>
    <el-button type="success" @click="getPdf('租赁合同', 'pdf')"
      >打印合同</el-button
    >
    <el-button type="success" onclick="history.go(-1)">返回</el-button>
  </div>
</template>

<script setup>
import { onMounted, reactive } from "vue";
import { adminStore } from "@/store/index";
import router from "@/router";
import { getPdf } from "@/utils/exportPDF";
const houselist = adminStore();
const hetong = reactive({
  house_id: "",
  chuzu: "",
  zuke: "",
  address: "",
  fromdate: "",
  todate: "",
  price: "",
});
onMounted(() => {
  console.log("自定义");
  console.log(houselist.rowData);
  hetong.house_id = houselist.rowData.house_id;
  Object.assign(hetong, houselist.rowData);
});
const editHetong = () => {
  router.push("/editHetong");
};
</script>

<style scoped>
.title {
  margin: 10px auto;
  text-align: center;
  font-size: 30px;
}
.button {
  margin: 10px auto;
  text-align: center;
}
span {
  color: red;
}
</style>
