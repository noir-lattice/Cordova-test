<template>
  <div class="page-2-container">
    <div class="header">
      <img @click="goto('main')" src="/p2-header-l.png" />
      <img src="/p2-header-m.png" />
      <img src="/p2-header-r.png" />
    </div>

    <div class="bar-1">
      <img src="/p2-bar-1-l.png" />
      <img src="/p2-bar-1-r.png" />
      <img class="avatar" :src="info.avatar" />
      <div class="nickname">{{ info.nickname }}</div>
      <div class="phoneNum">{{ info.accountNo }}</div>
    </div>

    <div class="bar-2">
      <img src="/p2-bar-2-l.png" />
      <div class="bar2-text">
        根据相关法律法规要求，请保持身份信息完善，以便使用支付宝支付相关功能。
      </div>
    </div>

    <div class="bar-3">
      <div class="real-name">{{ info.realName }}</div>
      <img src="/p2-bar-3-l.png" />
      <img src="/p2-bar-3-r.png" />
    </div>

    <div class="bar-4">
      <img src="/p2-bar-4-l.png" />
      <img src="/p2-bar-4-r.png" />
  </div>
  </div>
</template>

<script setup lang="ts">
import type { UserInfo } from "../interface";
import { statusBarColor } from "@/bridge";
import { ElMessage } from "element-plus";

const props = defineProps<{
  info: UserInfo;
  goto: (target: "main" | "detail" | "bill") => void;
}>();

function setStatusBar() {
  statusBarColor("f5f5f5").then();
}
onMounted(setStatusBar);
</script>

<style scoped>
.header {
  display: flex;
  justify-content: space-between;
  background-color: #f5f5f5;
}

.header img {
  height: 55px;
}

.bar-1 {
  display: flex;
  justify-content: space-between;
  background-image: url(/p2-bar-1-fill.png);
  background-size: contain;
}

.bar-1 img {
  height: 220px;
}

.bar-1 .avatar {
  position: absolute;
  width: 39px;
  height: 39px !important;
  border-radius: 5px;
  top: 71px;
  right: 42px;
}

.bar-1 .nickname {
  position: absolute;
  font-family: "Roboto", "Noto Sans SC";
  font-style: normal;
  font-size: 14px;
  line-height: 14px;
  right: 35px;
  top: 145px;
  color: #999999;
  background: white;
}

.bar-1 .phoneNum {
  position: absolute;
  font-family: "Roboto", "Noto Sans SC";
  font-style: normal;
  font-size: 14px;
  line-height: 14px;
  right: 35px;
  top: 195px;
  color: #999999;
  background: white;
}

.page-2-container {
  background-color: whitesmoke;
}

.bar-2 {
  display: flex;
  justify-content: left;
  width: 100%;
  margin-top: 12px;
  background-color: white;
}

.bar-2 img {
  height: 99px;
}

.bar-2 .bar2-text {
  position: absolute;
  font-family: "Roboto", "Noto Sans SC";
  font-style: normal;
  color: #999999;
  font-size: 14px;
  top: 336px;
  background-color: white;
  padding: 0 10px;
}

.bar-3 .real-name {
  font-size: 15px;
    line-height: 15px;
    position: absolute;
    top: 405px;
    font-family: "Roboto", "Noto Sans SC";
    font-style: normal;
    color: #999999;
    right: 10px;
    background-color: white;
}

.bar-3 {
  display: flex;
  justify-content: space-between;
  background-image: url(/p2-bar-3-fill.png);
  background-size: contain;
}

.bar-3 img {
  height: 251px;
}

.bar-4 {
  margin-top: 12px;
  display: flex;
  justify-content: space-between;
  background-image: url(/p2-bar-4-fill.png);
  background-size: contain;
}

.bar-4 img {
  height: 99px;
}
</style>
