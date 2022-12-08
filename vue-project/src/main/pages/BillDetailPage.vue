<template>
  <div class="header">
    <img @click="goto('main')" src="/p3-header-l.png" />
    <img src="/p3-header-r.png" />
  </div>

  <div class="header-2">
    <img src="/p3-header-2-1.png" />
    <img src="/p3-header-2-2.png" />
    <img src="/p3-header-2-3.png" />
    <img src="/p3-header-2-4.png" />
  </div>

  <div v-for="(item, idx) of info" :key="idx" class="month-card">
    <div class="month-header">
      <div class="month-text">{{ item.month }}</div>
      <div class="month-outcome-text">{{ item.outcome }}</div>
      <div class="month-income-text">{{ item.income }}</div>
      <img src="/p3-month-card-l.png" />
      <img src="/p3-month-card-r.png" />
      <img v-if="idx == 0" class="month-patch" src="/p3-month-patch.png" />
    </div>
    <div class="month-item" v-for="(record, i) of item.records" :key="i">
      <img src="/p3-card-item-l.png" />
      <img src="/p3-card-item-r.png" />
      <div class="month-item-title">{{ record.title }}</div>
      <div class="month-item-desc">{{ record.desc }}</div>
      <div class="month-item-happen-time">{{ record.happenTime }}</div>
      <div class="month-item-amount">{{ record.amount }}</div>
      <img class="month-item-image" :src="record.b64icon" />
    </div>
  </div>
</template>

<script setup lang="ts">
import { statusBarColor } from "@/bridge";
import type { BillInfo } from "../interface";

const props = defineProps<{
  info: BillInfo[];
  goto: (target: "main" | "detail" | "bill") => void;
}>();

function setStatusBar() {
  statusBarColor("#ffffff").then();
}
onMounted(setStatusBar);
</script>

<style scoped>
.header {
  display: flex;
  justify-content: space-between;
  background-color: white;
  background-image: url(/p3-header-fill.png);
  background-size: contain;
}
.header img {
  height: 66px;
  background-color: white;
}

.header-2 {
  display: flex;
  justify-content: space-between;
  background-color: white;
}

.header-2 img {
  height: 30px;
}

.month-card {
  margin-top: 10px;
}

.month-header {
  display: flex;
  justify-content: space-between;
  background-color: white;
}

.month-header img {
  height: 120px;
}

.month-header .month-text {
  position: absolute;
  font-family: "Roboto", "Noto Sans SC";
  font-style: normal;
  font-size: 39px;
  line-height: 39px;
  left: 15px;
  margin-top: 16px;
  background-color: white;
}

.month-header .month-outcome-text {
  position: absolute;
  font-family: "Roboto", "Noto Sans SC";
  font-style: normal;
  font-size: 13px;
  line-height: 14px;
  left: 56px;
  min-width: 50px;
  margin-top: 67px;
  background-color: white;
  color: #333333;
}

.month-header .month-income-text {
  position: absolute;
  font-family: "Roboto", "Noto Sans SC";
  font-style: normal;
  font-size: 13px;
  line-height: 14px;
  left: 158px;
  min-width: 50px;
  margin-top: 67px;
  background-color: white;
  color: #333333;
}

.month-item {
  display: flex;
  justify-content: space-between;
  background-color: white;
  background-image: url(/p3-card-item-fill.png);
  background-size: contain;
}

.month-item img {
  height: 95px;
}

.month-item .month-item-title {
  position: absolute;
  font-family: "Roboto", "Noto Sans SC";
  font-style: normal;
  font-size: 14.7px;
  line-height: 14.7px;
  left: 61px;
  font-weight: 300;
  margin-top: 20px;
  background: white;
  min-width: 150px;
}

.month-item .month-item-desc {
  position: absolute;
  font-family: "Roboto", "Noto Sans SC";
  font-style: normal;
  font-size: 12.7px;
  line-height: 12.7px;
  left: 61px;
  font-weight: 300;
  margin-top: 44px;
  background: white;
  color: #999999;
  min-width: 150px;
}

.month-item .month-item-happen-time {
  position: absolute;
  font-family: "Roboto", "Noto Sans SC";
  font-style: normal;
  font-size: 12.5px;
  line-height: 12px;
  left: 61px;
  font-weight: lighter;
  margin-top: 66px;
  background: white;
  color: #999999;
  min-width: 150px;
  word-spacing: 5px;
}

.month-item .month-item-amount {
  position: absolute;
  font-family: "Roboto", "Noto Sans SC";
  font-style: normal;
  font-size: 18px;
  line-height: 18px;
  font-weight: 800;
  margin-top: 17px;
  background: white;
  color: #333333;
  right: 16px;
}

.month-item .month-item-image {
  position: absolute;
  margin-top: 13px;
  height: 35px;
  left: 10px;
}

.month-patch {
  height: 109px !important;
  position: absolute;
  right: 0;
  margin-top: 13px;
}
</style>
