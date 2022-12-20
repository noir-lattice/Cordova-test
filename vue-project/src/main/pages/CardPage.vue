<template>
  <div class="detail">
    <div class="header">
      <div class="arrow" @click="goto('main')">
        <div class="arrow-icon" />
      </div>
      账户总览
      <div class="service" />
    </div>
    <div class="info">
      <div class="info-card">
        <div class="info-card-title">
          总资产(折算人民币元)
          <div class="close-eye" />
        </div>
        <div class="info-card-num">
          ******
          <div class="arrow-right" />
        </div>
        <div class="info-card-tip">
          昨日收益(元) ******
          <div class="arrow-right" />
        </div>
      </div>
    </div>
    <div class="card-tip">中行卡</div>
    <div class="card-groud">
      <div class="card-item">
        <div class="card-item-head">
          <div class="card-item-logo" />
          <div>
            <div>{{ info.cardNum }}</div>
            <div style="color: #666; font-size: 14px">
              长城电子借记卡 （I类账户）
            </div>
          </div>
        </div>
        <div class="card-item-amount">
          <div>账面余额</div>
          <div>
            人民币元 <span class="card-item-num">{{ info.cardBalance }}</span>
          </div>
        </div>
        <div class="card-item-actions">
          <div class="card-item-act" @click="goto('bill')">交易明细</div>
          <div class="card-item-act">转账</div>
          <div class="card-item-act">买理财</div>
        </div>
      </div>
    </div>
    <div class="add">
      <div class="add-card" />
    </div>
  </div>
</template>

<script setup lang="ts">
import type { UserInfo } from "../interface";
import { statusBarColor } from "@/bridge";
import { onMounted, onUnmounted } from "vue";

const props = defineProps<{
  info: UserInfo;
  goto: (target: "main" | "detail" | "bill") => void;
}>();

function setStatusBar() {
  statusBarColor("#ffffff").then();
}
onMounted(setStatusBar);
</script>

<style scoped>
.detail {
  background: #f3f3f3;
  height: 100vh;
  box-sizing: border-box;
  padding: 10px 0;
  padding-top: 60px;
}
.header {
  position: fixed;
  top: 0;
  background: #fff;
  width: 100vw;
  height: 50px;
  font-weight: 500;
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1;
}

.arrow {
  position: absolute;
  left: 10px;
}
.service {
  position: absolute;
  right: 10px;
  width: 20px;
  height: 20px;
  background-image: url("../images_1/service.png");
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
}
.arrow-icon {
  width: 20px;
  height: 20px;
  background-image: url("../images/arrow-left-bold.png");
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
}
.info {
  height: 140px;
  margin: 16px;
  box-shadow: 0px 0px 14px 0px rgba(0, 0, 0, 0.1);
}
.info-card {
  overflow: hidden;
  height: 100%;
  border-radius: 6px;
  background-image: url("../images_1/info-card.png");
  background-position: center;
  background-size: cover;
  background-repeat: no-repeat;
  color: #532f07;
  padding: 20px;
}
.info-card-title {
  margin-bottom: 10px;
  display: flex;
  position: relative;
  align-items: center;
}
.info-card-title::after {
  content: "";
  width: 26px;
  height: 26px;
  position: absolute;
  right: 0;
  background-image: url("../images_1/question-circle.png");
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
}
.info-card-num {
  margin-bottom: 2px;
  font-size: 30px;
  font-weight: 500;
  display: flex;
  position: relative;
  align-items: center;
}
.info-card-tip {
  display: flex;
  position: relative;
  align-items: center;
}
.close-eye {
  width: 20px;
  height: 20px;
  margin-left: 10px;
  background-image: url("../images_1/closed-eye.png");
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
}
.arrow-right {
  width: 20px;
  height: 20px;
  margin-left: 20px;
  background-image: url("../images_1/arrow-right-bold.png");
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
}
.card-tip {
  margin: 0 12px 6px;
  position: relative;
  color: #666;
}
.card-tip::after {
  content: "";
  width: 20px;
  height: 20px;
  position: absolute;
  right: 10px;
  background-image: url("../images_1/refresh.png");
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
}
.card-groud {
  display: flex;
  flex-direction: column;
}
.card-item {
  margin: 0 16px 16px;
  background: #fff;
  border-radius: 6px;
  box-shadow: 0px 0px 14px 0px rgba(0, 0, 0, 0.1);
}
.card-item-head {
  padding: 14px;
  display: flex;
}
.card-item-head::after {
  content: "";
  width: 50px;
  height: 20px;
  position: absolute;
  right: 40px;
  background-image: url("../images_1/card-tip.png");
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
}
.card-item-logo {
  width: 50px;
  height: 40px;
  margin-right: 10px;
  background-image: url("../images_1/card-logo.png");
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
}
.card-item-amount {
  padding: 14px;
  background: #faf8f2;
  color: #666;
  display: flex;
  justify-content: space-between;
}
.card-item-num {
  font-weight: 500;
  margin-left: 6px;
  color: #333;
}
.card-item-actions {
  display: flex;
  justify-content: space-around;
  margin: 20px 0;
}
.card-item-act {
  flex: 1;
  text-align: center;
}
.card-item-act:not(:last-child) {
  border-right: 1px solid #f3f3f3;
}
.add {
  height: 90px;
  margin: 0 16px;
  border-radius: 6px;
  background: #fff;
  box-shadow: 0px 0px 14px 0px rgba(0, 0, 0, 0.1);
}
.add-card {
  height: 100%;
  background-image: url("../images_1/add-card.png");
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
}
</style>
