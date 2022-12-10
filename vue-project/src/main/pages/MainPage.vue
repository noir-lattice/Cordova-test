<template>
  <div class="main-container">
    <div class="header">
      <img style="margin-left: -1px" src="../images_2/p1-header-l.png" />
      <img src="../images_2/p1-header-r.png" />
    </div>
    <div class="header-2" @click="goto('detail')">
      <img style="margin-left: -1px" src="../images_2/p1-header-2.png" />
      <img class="dynamic-avatar" :src="info.avatar" />
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
        style="position: absolute; left: 32px"
        src="../images_2/item-1-tag.png"
      />
      <img src="../images_2/item-1-text.png" />
    </div>

    <div class="item-2">
      <img src="../images_2/item-2-1.png" />
      <img src="../images_2/item-2-2.png" />
      <img src="../images_2/item-2-3.png" />
      <img src="../images_2/item-2-4.png" />
    </div>

    <div class="item-3">
      <img src="../images_2/item-3-1.png" />
      <img src="../images_2/item-3-2.png" />
    </div>

    <div class="item-4" @click="goto('bill')">
      <img src="../images_2/item-4-1.png" />
      <img src="../images_2/item-4-2.png" />
    </div>

    <div class="item-5">
      <div>
        <img src="../images_2/item-5-1.png" />
        <img src="../images_2/item-5-2.png" />
      </div>
      <img src="../images_2/item-5-3.png" />
    </div>

    <div class="item-7">
      <div class="item-7-top">
        <img src="../images_2/item-7-top-1.png" />
        <img src="../images_2/item-7-top-2.png" />
      </div>
      <div class="item-7-img">
        <img src="../images_2/item-7-image.png" />
      </div>

      <div class="item-7-btm">
        <img src="../images_2/item-7-btm-1.png" />
        <img src="../images_2/item-7-btm-2.png" />
        <img src="../images_2/item-7-btm-3.png" />
      </div>
    </div>

    <div class="item-8">
      <img src="../images_2/item-8-1.png" />
      <img src="../images_2/item-8-2.png" />
    </div>
  </div>
  <div class="bottom">
    <img src="../images_2/btm-1.png" />
    <img src="../images_2/btm-2.png" />
    <img src="../images_2/btm-3.png" />
    <img src="../images_2/btm-4.png" />
    <img src="../images_2/btm-5.png" />
  </div>
</template>

<script setup lang="ts">
import { onMounted, onUnmounted } from "vue";
import { statusBarColor } from "@/bridge";
import type { UserInfo } from "../interface";
import { dateFormat } from "../utils";

const props = defineProps<{
  info: UserInfo;
  goto: (target: "main" | "detail" | "bill" | "card") => void;
}>();

function setStatusBar() {
  statusBarColor("#3875F6").then();
}
function cleanStatusBar() {
  statusBarColor("lightcontent").then();
}
onMounted(setStatusBar);
onUnmounted(cleanStatusBar);
</script>

<style scoped>
.header {
  display: flex;
  justify-content: space-between;
  background-image: url(../images_2/p1-header-fill.png);
  background-size: contain;
}
.header img {
  height: 72px;
}

.header-2 {
  display: flex;
  justify-content: left;
  background: repeating-linear-gradient(to left, #c92d3e, #cd3947);
}
.header-2 img {
  height: 66px;
}

.header-3 {
  padding-top: 16px;
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
  background-color: rgb(241, 241, 241);
  height: 100vh;
  overflow: hidden;
  height: calc(100vh - 50px);
}

.item-1 {
  display: flex;
  justify-content: center;
  background: white;
  margin: 7px;
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
  margin: 7px;
  border-radius: 7px;
  justify-content: space-between;
}

.item-2 img {
  height: 74px;
}

.item-3 {
  display: flex;
  justify-content: center;
  background: white;
  margin: 7px;
  border-radius: 7px;
  justify-content: space-between;
}

.item-3 img {
  height: 96px;
}

.item-4 {
  display: flex;
  justify-content: center;
  background: white;
  margin: 7px;
  border-radius: 7px;
  justify-content: space-between;
  background-color: white;
  background-image: url(../images_2/item-4-b.png);
  background-size: contain;
}

.item-4 img {
  background-color: white;
  height: 109px;
}

.item-5 {
  display: flex;
  justify-content: center;
  background: white;
  margin: 7px;
  border-radius: 7px;
  justify-content: space-between;
  background-color: white;
  background-image: url(../images_2/item-5-fill.png);
  background-size: contain;
  height: 127px;
}

.item-5 img {
  background-color: white;
  height: 127px;
}

.item-7 {
  background: white;
  margin: 7px;
  border-radius: 7px;
  background-color: white;
  height: 200px;
}

.item-7-top {
  display: flex;
  justify-content: space-between;
  height: 42px;
}

.item-7-top img {
  height: 42px;
}

.item-7-img {
  display: flex;
  justify-content: center;
  height: 111px;
}

.item-7-img img {
  height: 111px;
}

.item-7-btm {
  display: flex;
  justify-content: space-between;
}

.item-7-btm img {
  height: 42px;
}

.item-8 {
  display: flex;
  justify-content: center;
  background: white;
  margin: 7px;
  border-radius: 7px;
  justify-content: space-between;
  background-color: white;
  background-image: url(../images_2/item-8-b.png);
  background-size: contain;
  height: 138px;
}

.item-8 img {
  background-color: white;
  height: 138px;
}

.bottom {
  display: flex;
  justify-content: space-between;
  width: 100%;
  background-color: #fafbfe;
  background-image: url(../images_2/btm-b.png);
  background-size: contain;
}
.bottom img {
  height: 50px;
}

.dynamic-avatar {
  position: absolute;
  top: 73px;
  left: 19px;
  width: 64px;
  height: 64px !important;
  border-radius: 50%;
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