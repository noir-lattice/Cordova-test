<template>
  <div class="detail">
    <div class="header">
      <div class="arrow" @click="goto('main')">
        <div class="arrow-icon" />
      </div>
      我的个人信息
    </div>
    <div class="info">
      <div class="name">
        {{ info.realName }}/
        {{ info.spell }}
      </div>
    </div>
    <div class="content" />
  </div>
</template>

<script setup lang="ts">
import { statusBarColor } from "@/bridge";
import { onMounted, onUnmounted } from "vue";
import type { UserInfo } from "../interface";
function setStatusBar() {
  statusBarColor("#ffffff").then();
}
onMounted(setStatusBar);

const props = defineProps<{
  info: UserInfo;
  goto: (target: "main" | "detail" | "bill" | "card") => void;
}>();
</script>

<style scoped>
.detail {
  background: #f0eff4;
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
.arrow-icon {
  width: 20px;
  height: 20px;
  background-image: url("../images/arrow-left-bold.png");
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
}
.info {
  height: 200px;
  margin: 0 10px;
  background-image: url("../images_1/info-bg.png");
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
  display: flex;
  align-items: center;
  justify-content: center;
}
.name {
  color: #fff;
  font-size: 20px;
  font-weight: bold;
  margin-top: 50px;
}
.content {
  width: 100%;
  height: 340px;
  background-image: url("../images_1/info.png");
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
}
</style>
