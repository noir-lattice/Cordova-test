<template>
  <div class="page-2-container">
    <div class="header">
      <img @click="goto('main')" src="/p2-header-l.png" />
      <img src="/p2-header-m.png" />
      <img src="/p2-header-r.png" />
    </div>

    <div class="bar-1">
      <img src="/p2-content-1.png" />
      <img src="/p2-content-3.png" />
      <img class="avatar" :src="info.avatar" />
      <div class="nickname">{{ info.nickname }}</div>
      <div class="phoneNum">{{ info.accountNo }}</div>
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
  margin-top: -1px;
  margin-left: -1px;
  display: flex;
  justify-content: space-between;
  background-color: #f5f5f5;
}

.header img {
  height: 55px;
}

.bar-1 {
  margin-left: -1px;
  display: flex;
  justify-content: space-between;
  background-image: url(/p2-content-2.png);
  background-size: contain;
}

.bar-1 img {
  height: 372px;
}

.bar-1 .avatar {
  position: absolute;
  width: 39px;
  height: 39px !important;
  border-radius: 5px;
  top: 65px;
  right: 46px;
}

.bar-1 .nickname {
  position: absolute;
  font-family: "Roboto", "Noto Sans SC";
  font-style: normal;
  font-size: 14px;
  line-height: 14px;
  right: 45px;
  top: 136px;
  color: #999999;
  background: white;
  min-width: 100px;
  text-align: right;
}

.bar-1 .phoneNum {
  position: absolute;
  font-family: "Roboto", "Noto Sans SC";
  font-style: normal;
  font-size: 14px;
  line-height: 14px;
  right: 43px;
  top: 185px;
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
