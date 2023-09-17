<template>
  <div class="main-container">
    <div class="header">
      <img src="../images_2/p1-header.png" />
    </div>
    <div class="header-2" @click="goto('detail')">
      <div class="avatar-content">
        <img class="dynamic-avatar" :src="info.avatar" />
        <span class="avatar-setting">设置</span>
      </div>
      <div class="title">{{ info.title }}</div>
      <div class="desc">{{ info.desc }}</div>
    </div>
    <div class="header-3">
      <div class="header-3-inner">
        <img src="../images_2/p1-header-3-inner.png" />
      </div>
    </div>

    <div class="item-1">
      <img
        style="position: absolute; left: 16px; height: 20px"
        src="../images_2/item-1-tag.png"
      />
      <img
        style="position: absolute; left: 66px"
        src="../images_2/item-1-text.png"
      />
    </div>

    <div class="item-2">
      <div class="item-2-1" @click="goto('card')">
        <img src="../images_2/item-2-1.png" />
        <span class="item-2-1-num">1</span>
      </div>

      <img src="../images_2/item-2-2.png" />
      <img src="../images_2/item-2-3.png" />
      <img src="../images_2/item-2-4.png" />
    </div>

    <div class="item-3">
      <div class="item-3-date" v-if="isShowDate" @click="getCurrentDate">
        {{ currentDate }}
        <img :class="{ loading }" src="../images_2/loading.png" />
      </div>

      <img
        v-if="isShow"
        class="item-3-title"
        @click="triggerShow"
        src="../images_2/item-3-1-2.png"
      />
      <img
        v-else
        class="item-3-title"
        @click="triggerShow"
        src="../images_2/item-3-1-1.png"
      />
      <img src="../images_2/item-3-2-1.png" />
      <img src="../images_2/item-3-3-1.png" />
      <div class="item-3-2-num">
        <span v-if="isShow">￥{{ info.cardBalance }}</span>
        <img v-else class="hide" src="../images_2/hide.png" />
      </div>
      <div class="item-3-3-num">
        <span v-if="isShow">￥{{ info.lastIncome }}</span>
        <img v-else class="hide" src="../images_2/hide.png" />
      </div>
    </div>

    <div class="item-4">
      <img class="item-4-title" src="../images_2/item-4-1-1.png" />
      <img style="width: 100%" src="../images_2/item-4-2-1.png" />
      <div class="item-4-2-num">
        <span v-if="isShow">￥{{ info.income }}</span>
        <img v-else class="hide" src="../images_2/hide.png" />
      </div>
      <div class="item-4-3-num">
        <span v-if="isShow">￥{{ info.outcome }}</span>
        <img v-else class="hide" src="../images_2/hide.png" />
      </div>
    </div>

    <div class="item-5">
      <img class="item-5-title" src="../images_2/item-5-1-1.png" />
      <img style="width: 100%" src="../images_2/item-5-2-1.png" />
    </div>

    <div class="item-6">
      <img class="item-6-title" src="../images_2/item-6-1-1.png" />
      <img class="item-6-arrow" src="../images_2/arrow.png" />
      <img style="width: 100%" src="../images_2/item-6-2-1.png" />
      <div class="item-6-content">
        <div class="item-6-name">
          {{ info.outlets.name }}<img src="../images_2/arrow.png" />
        </div>
        <div>{{ info.outlets.address }}</div>
        <div>
          距您 <span style="color: #333">{{ info.outlets.distance }}</span>
          <span class="item-6-tip">离您最近</span>
        </div>
      </div>
    </div>

    <div class="item-7">
      <img class="item-7-title" src="../images_2/item-7-1-1.png" />
      <img style="width: 100%" src="../images_2/item-7-2-1.png" />
    </div>

    <div class="item-8">
      <img src="../images_2/item-8-1.png" />
    </div>

    <div class="bottom">
      <img src="../images_2/btm-1.png" />
      <img src="../images_2/btm-2.png" />
      <img src="../images_2/btm-3.png" />
      <img src="../images_2/btm-4.png" />
      <img src="../images_2/btm-5.png" />
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, onUnmounted } from "vue";
import { statusBarColor } from "@/bridge";
import type { UserInfo } from "../interface";
import { dateFormat, delay } from "../utils";

const props = defineProps<{
  info: UserInfo;
  goto: (target: "main" | "detail" | "bill" | "card") => void;
}>();

const isShow = ref(false);
const triggerShow = () => {
  isShow.value = !isShow.value;
  if (isShow.value) {
    getCurrentDate();
  }
};

const loading = ref(false);
const isShowDate = ref(false);
const currentDate = ref("");
const getCurrentDate = async () => {
  loading.value = true;
  await delay();
  currentDate.value = dateFormat(new Date(), "YYYY/MM/DD HH:mm:ss");
  isShowDate.value = true;
  loading.value = false;
};

function setStatusBar() {
  statusBarColor("#000000").then();
}
onMounted(setStatusBar);
</script>

<style scoped>
.header {
  display: flex;
}
.header img {
  height: 54px;
  width: 100%;
}

.header-2 {
  height: 106px;
  margin: -1px 0;
  display: flex;
  justify-content: left;
  background: repeating-linear-gradient(to left, #c92d3e, #cd3947);
}

.header-3 {
  display: flex;
  justify-content: center;
  background: repeating-linear-gradient(to left, #c92d3e, #cd3947);
  border-bottom-left-radius: 50% 15px;
  border-bottom-right-radius: 50% 15px;
  overflow: hidden;
}

.header-3-inner {
  display: flex;
  justify-content: center;
  border-radius: 10px;
  height: 45px;
  width: 88%;
  background-color: #fefdf1;
}

.header-3-inner img {
  height: 29px;
}

.main-container {
  background-color: #fff;
  padding-bottom: 50px;
  overflow-x: hidden;
}

.item-1 {
  display: flex;
  justify-content: center;
  align-items: center;
  background: white;
  margin: 7px 16px;
  border-radius: 7px;
  height: 41px;
}

.item-1 img {
  height: 41px;
}

.item-2 {
  display: flex;
  justify-content: center;
  background: white;
  margin: 7px 16px;
  border-radius: 7px;
  justify-content: space-between;
  box-shadow: 0px 0px 2px rgba(0, 0, 0, 0.1);
}
.item-2-1 {
  width: 80px;
  height: 80px;
  position: relative;
}
.item-2-1-num {
  position: absolute;
  width: 80px;
  height: 80px;
  top: 18px;
  left: 34px;
  font-size: 21px;
  color: #333;
}

.item-2 img {
  height: 80px;
}

.item-3,
.item-4,
.item-5,
.item-6,
.item-7 {
  position: relative;
  display: flex;
  justify-content: center;
  background: white;
  margin: 7px 16px;
  margin-top: 52px;
  border-radius: 7px;
  justify-content: space-between;
  box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.1);
}

.item-3 img {
  height: 70px;
}

.item-4 img,
.item-5 img {
  height: 90px;
}

img.item-3-title,
img.item-4-title,
img.item-5-title,
img.item-6-title,
img.item-7-title {
  position: absolute;
  top: -32px;
  width: 96px;
  height: 24px;
}

.item-3-date {
  position: absolute;
  top: -32px;
  right: 0;
  width: 170px;
  height: 22px;
  font-size: 14px;
  color: #888;
  display: flex;
  justify-content: flex-end;
  align-items: center;
}
.item-3-date img {
  width: 22px;
  height: 22px;
  margin-left: 4px;
  border-radius: 100%;
}
.item-3-date img.loading {
  animation: rotation 2s linear infinite;
}

.item-3-2-num {
  position: absolute;
  bottom: 12px;
  left: 8px;
}
.item-3-3-num {
  position: absolute;
  bottom: 12px;
  right: 8px;
}

.item-4-2-num {
  position: absolute;
  bottom: 30px;
  left: 8px;
}
.item-4-3-num {
  position: absolute;
  bottom: 30px;
  right: 8px;
}

img.hide {
  height: 14px;
}

@keyframes rotation {
  from {
    rotate: 0deg;
  }
  to {
    rotate: 360deg;
  }
}

.item-5 {
  box-shadow: unset;
}
.item-6 {
  box-shadow: unset;
}
.item-6-arrow {
  position: absolute;
  top: -26px;
  right: 0;
  width: 8px;
  height: 14px;
}
.item-6-content {
  position: absolute;
  font-size: 14px;
  left: 18px;
  top: 18px;
  color: #666;
}
.item-6-name {
  font-size: 16px;
  font-weight: bold;
  color: #333;
  display: flex;
  align-items: center;
}
.item-6-tip {
  padding: 0 4px;
  margin-left: 4px;
  display: inline-block;
  background: rgb(200 200 200 / 10%);
}
.item-6-content div {
  margin-bottom: 4px;
}
.item-6-name img {
  margin-left: 4px;
  width: 8px;
  height: 14px;
}
.item-7 {
  box-shadow: unset;
  height: 240px;
}

.item-8 {
  display: flex;
  justify-content: center;
  background-color: #f4f4f4;
}

.item-8 img {
  height: 80px;
}

.bottom {
  position: fixed;
  bottom: 0;
  display: flex;
  justify-content: space-between;
  width: 100vw;
  background-color: #fafbfe;
  background-image: url(../images_2/btm-b.png);
  background-size: contain;
}
.bottom img {
  height: 50px;
}

.avatar-content {
  position: absolute;
  top: 73px;
  left: 19px;
  width: 64px;
  height: 64px;
  border-radius: 50%;
  overflow: hidden;
}
.dynamic-avatar {
  width: 64px;
  height: 64px;
}
.avatar-setting {
  width: 100%;
  padding: 2px;
  font-weight: 200;
  font-size: 14px;
  position: absolute;
  bottom: 0;
  left: 0;
  text-align: center;
  color: #fff;
  background-color: rgba(0, 0, 0, 0.3);
}

.title {
  font-family: "Roboto", "Noto Sans SC";
  font-style: normal;
  font-size: 18px;
  line-height: 18px;
  position: absolute;
  left: 98px;
  top: 85px;
  background: repeating-linear-gradient(to left, #cf3847, #cc3242);
  color: white;
  min-width: 100px;
}

.desc {
  font-family: "Roboto", "Noto Sans SC";
  font-style: normal;
  font-size: 11px;
  background: repeating-linear-gradient(to left, #cd3647, #cc3040);
  position: absolute;
  line-height: 11px;
  left: 97px;
  top: 111px;
  color: #f8cac7;
}
</style>
