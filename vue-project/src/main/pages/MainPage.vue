<template>
  <div class="header" @click="goto('detail')">
    <img class="dynamic-avatar" :src="info.avatar" />
    <img style="margin-left: -1px" src="/header.png" />
    <img src="/header-act.png" />
    <div class="nickname">{{ info.nickname }}</div>
    <div class="phone-num">{{ info.phoneNum }}</div>
  </div>

  <div class="bar-1">
    <img src="/p1-bar-1-l.png" />
    <img src="/p1-bar-1-r.png" />
  </div>

  <div class="bar-2" @click="goto('bill')">
    <img src="/p1-bar-2-l.png" />
    <img src="/p1-bar-2-r.png" />
  </div>

  <div class="bar-3">
    <img src="/p1-bar-3-l.png" style="margin-top: -2px" />
    <img src="/p1-bar-3-r.png" />
    <div class="bar-3-repair"></div>
  </div>

  <div class="bar-4">
    <img src="/p1-bar-4-l.png" />
    <img src="/p1-bar-4-r.png" />
  </div>

  <div class="bottom">
    <img src="/1.png" />
    <img src="/2.png" />
    <img src="/3.png" />
    <img src="/4.png" />
    <img src="/5.png" />
  </div>
</template>

<script setup lang="ts">
import { onMounted, onUnmounted } from "vue";
import { statusBarColor } from "@/bridge";
import type { UserInfo } from "../interface";

const props = defineProps<{
  info: UserInfo;
  goto: (target: "main" | "detail" | "bill") => void;
}>();

function setStatusBar() {
  statusBarColor("#1578ff").then();
}
onMounted(setStatusBar);
</script>

<style scoped>
.header {
  display: flex;
  justify-content: space-between;
  background-image: url(/header-fill.png);
  background-size: contain;
}

.header img {
  height: 125px;
}

.bar-1 {
  margin-top: -13px;
  display: flex;
  justify-content: space-between;
  background-image: url(/p1-bar-1-fill.png);
  background-size: contain;
}

.bar-1 img {
  height: 150px;
  border-radius: 10%;
}

.bar-2 {
  display: flex;
  justify-content: space-between;
  background-image: url(/p1-bar-2-fill.png);
  background-size: contain;
}

.bar-2 img {
  height: 280px;
}

.bar-3-repair {
  background-color: whitesmoke;
  position: absolute;
  height: 100%;
  width: 12px;
  left: 0px;
}

.bar-3 {
  display: flex;
  justify-content: space-between;
  background-image: url(/p1-bar-3-fill.png);
  background-size: contain;
}

.bar-3 img {
  height: 137px;
}

.bar-4 {
  display: flex;
  justify-content: space-between;
  background-image: url(/p1-bar-4-fill.png);
  background-size: contain;
}

.bar-4 img {
  height: 175px;
}

.dynamic-avatar {
  position: absolute;
  top: 51px;
  left: 15px;
  width: 45px;
  height: 46px !important;
  border-radius: 6px;
}

.nickname {
  font-family: "Roboto", "Noto Sans SC";
  font-style: normal;
  font-size: 16px;
  line-height: 18px;
  position: absolute;
  left: 73px;
  top: 57px;
  background-color: #1578ff;
  color: white;
  min-width: 50px;
}

.phone-num {
  font-family: "Roboto", "Noto Sans SC";
  font-style: normal;
  position: absolute;
  left: 72px;
  top: 78px;
  font-size: 12px;
  color: #a0c8ff;
  background-color: #1578ff;
}

.bottom {
  display: flex;
  justify-content: space-between;
  position: fixed;
  bottom: 0;
  width: 100%;
  background-color: #fafbfe;
}

.bottom img {
  height: 55px;
}
</style>
