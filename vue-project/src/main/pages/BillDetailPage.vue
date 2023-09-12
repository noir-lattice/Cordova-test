<template>
  <div class="header">
    <img @click="goto('main')" src="/p3-header.png" />
    <!-- <img @click="goto('main')" src="/p3-header-l.png" />
    <img src="/p3-header-r.png" /> -->
  </div>

  <div v-for="(item, idx) of info" :key="idx" class="month-card">
    <div class="month-header">
      <div class="month-text">{{ item.month }}</div>
      <div class="month-outcome-text">{{ item.outcome }}</div>
      <div class="month-income-text">{{ item.income }}</div>
      <img src="/p3-month-head-1.png" />
      <img src="/p3-month-head-2.png" />
      <!-- <img v-if="idx == 0" class="month-patch" src="/p3-month-patch.png" /> -->
    </div>
    <div class="month-item" v-for="(record, i) of item.records" :key="i">
      <img src="/p3-item-1.png" />
      <img src="/p3-item-2.png" />
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
  width: 100%;
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
  position: relative;
  display: flex;
  justify-content: space-between;
  background-image: url(/p3-month-head-3.png);
  background-size: contain;
}

.month-header img {
  margin-left: -1px;
  height: 120px;
}

.month-header .month-text {
  position: absolute;
  font-family: "Roboto", "Noto Sans SC";
  font-style: normal;
  font-size: 30px;
  line-height: 39px;
  left: 15px;
  margin-top: 15px;
  background-color: white;
}

.month-header .month-outcome-text {
  position: absolute;
  font-family: "Roboto", "Noto Sans SC";
  font-style: normal;
  font-size: 13px;
  line-height: 14px;
  left: 68px;
  min-width: 50px;
  margin-top: 65px;
  background-color: white;
  color: #333333;
}

.month-header .month-income-text {
  position: absolute;
  font-family: "Roboto", "Noto Sans SC";
  font-style: normal;
  font-size: 13px;
  line-height: 14px;
  left: 173px;
  min-width: 50px;
  margin-top: 65px;
  background-color: white;
  color: #333333;
}

.month-item {
  position: relative;
  display: flex;
  justify-content: space-between;
  background-color: white;
  background-image: url(/p3-card-item-fill.png);
  background-size: contain;
}

.month-item img {
  padding: 0 1px;
  height: 95px;
}

.month-item .month-item-title {
  position: absolute;
  font-family: "Roboto", "Noto Sans SC";
  font-style: normal;
  font-size: 13px;
  line-height: 14.7px;
  left: 59px;
  font-weight: 300;
  margin-top: 20px;
  background: white;
  min-width: 150px;
}

.month-item .month-item-desc {
  position: absolute;
  font-family: "Roboto", "Noto Sans SC";
  font-style: normal;
  font-size: 11px;
  line-height: 12.7px;
  left: 59px;
  font-weight: 300;
  margin-top: 42px;
  background: white;
  color: #999999;
  min-width: 150px;
}

.month-item .month-item-happen-time {
  position: absolute;
  font-family: "Roboto", "Noto Sans SC";
  font-style: normal;
  font-size: 12px;
  line-height: 12px;
  left: 59px;
  font-weight: lighter;
  margin-top: 63px;
  background: white;
  color: #999999;
  min-width: 150px;
  word-spacing: 5px;
}

.month-item .month-item-amount {
  position: absolute;
  font-family: "Roboto", "Noto Sans SC";
  font-style: normal;
  font-size: 15px;
  line-height: 18px;
  margin-top: 19px;
  background: white;
  color: #333333;
  right: 23px;
}

.month-item .month-item-image {
  position: absolute;
  margin-top: 17px;
  height: 25px;
  left: 21px;
}

.month-patch {
  height: 109px !important;
  position: absolute;
  right: 0;
  margin-top: 13px;
}
</style>
