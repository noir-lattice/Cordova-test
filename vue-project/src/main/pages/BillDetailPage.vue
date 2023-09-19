<template>
  <div ref="headerRef" class="header">
    <img @click="goto('main')" src="/p3-header.png" />
    <!-- <img @click="goto('main')" src="/p3-header-l.png" />
    <img src="/p3-header-r.png" /> -->
  </div>

  <div v-if="topCardIndex >= 0" class="month-fixed" :style="{ top: headerHeight + 'px', width: headerWidth + 'px' }">
    <img src="/month-fixed-1.png" />
    <img src="/month-fixed-2.png" />
    <div class="month-text">{{ info[topCardIndex].month }}</div>
    <div class="month-outcome-text">¥{{ info[topCardIndex].outcome }}</div>
    <div class="month-income-text">¥{{ info[topCardIndex].income }}</div>
  </div>

  <div ref="cardRef">
    <div v-for="(item, idx) of info" :key="idx" class="month-card" :style="idx == 0 ? { marginTop: '110px' } : {}">
      <div class="month-header">
        <div class="month-text">{{ item.month }}</div>
        <div class="month-outcome-text">¥ {{ item.outcome }}</div>
        <div class="month-income-text">¥ {{ item.income }}</div>
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
        <div class="month-item-amount" :style="{ color: record.amount.includes('+') ? 'red' : '' }">{{ record.amount }}
        </div>
        <img class="month-item-image" :src="record.b64icon" />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import { statusBarColor } from "@/bridge";
import type { BillInfo } from "../interface";

const cardRef: any = ref(null);

const props = defineProps<{
  info: BillInfo[];
  goto: (target: "main" | "detail" | "bill") => void;
}>();

const topCardIndex = ref(-1);
const headerRef = ref(null);
const headerHeight = ref(0);
const headerWidth = ref(0);
function setStatusBar() {
  statusBarColor("#000000").then();
  const elms = cardRef.value.getElementsByClassName('month-income-text');
  setTimeout(() => {
    console.log(headerRef.value.clientHeight)
    headerHeight.value = headerRef.value.clientHeight - 1
    headerWidth.value = headerRef.value.clientWidth
  }, 100);
  setInterval(() => {
    let newTopCardIndex = -1;
    for (let i = 0; i < elms.length; i++) {
      const elm = elms[i];
      if (elm.getBoundingClientRect().top > headerHeight.value + 53) {
        newTopCardIndex = i - 1;
        break;
      }
    }
    topCardIndex.value = newTopCardIndex;
  }, 100)
}
onMounted(setStatusBar);
</script>

<style scoped>
.header {
  position: fixed;
  top: 0px;
  z-index: 99999;
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

.month-fixed {
  position: fixed;
  z-index: 99999;
  display: flex;
  justify-content: space-between;
  background-color: white;
  background-image: url(/month-fixed-3.png);
  background-size: contain;
}

.month-fixed img {
  height: 53px;
}

.month-fixed .month-text {
  position: absolute;
  font-family: "Roboto", "Noto Sans SC";
  font-style: normal;
  font-size: 14px;
  left: 17px;
  margin-top: -2px;
  background-color: rgb(244, 244, 244);
  min-width: 16px;
  text-align: right;
}

.month-fixed .month-outcome-text {
  position: absolute;
  font-family: "Roboto", "Noto Sans SC";
  font-style: normal;
  font-size: 13px;
  line-height: 14px;
  left: 53px;
  min-width: 70px;
  margin-top: 25px;
  background-color: rgb(244, 244, 244);
  color: #333333;
}

.month-fixed .month-income-text {
  position: absolute;
  font-family: "Roboto", "Noto Sans SC";
  font-style: normal;
  font-size: 13px;
  line-height: 14px;
  left: 159px;
  min-width: 70px;
  margin-top: 25px;
  background-color: rgb(244, 244, 244);
  color: #333333;
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
  height: 115px;
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
  min-width: 31px;
  text-align: end;
}

.month-header .month-outcome-text {
  position: absolute;
  font-family: "Roboto", "Noto Sans SC";
  font-style: normal;
  font-size: 13px;
  line-height: 13px;
  left: 55px;
  min-width: 50px;
  margin-top: 63px;
  background-color: white;
  color: #333333;
  min-width: 60px;
}

.month-header .month-income-text {
  position: absolute;
  font-family: "Roboto", "Noto Sans SC";
  font-style: normal;
  font-size: 13px;
  line-height: 14px;
  left: 156px;
  min-width: 50px;
  margin-top: 62px;
  background-color: white;
  color: #333333;
}

.month-item {
  margin: 0 -1px;
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
  font-weight: 500;
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
  color: #666;
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
  color: #666;
  min-width: 150px;
  word-spacing: 5px;
}

.month-item .month-item-amount {
  position: absolute;
  font-family: "Roboto", "Noto Sans SC";
  font-style: normal;
  font-size: 15px;
  font-weight: 600;
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
