<template>
  <div class="main-container">
    <div class="header">
      <img v-show="isLogin" src="../images_2/h-header-1.png" />
      <img v-show="!isLogin" src="../images_2/h-header.png" />
    </div>
    <div class="header-btn" @click="handleClick"></div>

    <div class="content">
      <img src="../images_2/h-content-1.png" />
      <img src="../images_2/h-content-2.png" />
    </div>
    <div class="bottom">
      <img src="../images_2/h-foot.png" />
      <div class="mine-block" @click="toMain"></div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted } from "vue";
import { statusBarColor } from "@/bridge";
import { showConfirmDialog } from "vant";
import type { UserInfo } from "../interface";

const props = defineProps<{
  info: UserInfo;
  goto: (target: any) => void;
  isLogin?: boolean;
}>();

const emits = defineEmits(["logout"]);

const handleClick = () => {
  if (!props.isLogin) {
    props.goto("login");
  } else {
    showConfirmDialog({
      className: "logout-dialog",
      message: "请确认是否退出当前登录账号？",
    }).then(() => {
      emits("logout");
    });
  }
};

function toMain() {
  props.goto("main");
}

function setStatusBar() {
  statusBarColor("#3056ED").then();
}
onMounted(setStatusBar);
</script>

<style scoped>
.main-container {
  background-color: #fff;
  padding-bottom: 50px;
  overflow-x: hidden;
}

.header {
  display: flex;
  position: relative;
}

.header img,
.content img,
.bottom img {
  width: 100%;
  height: auto;
}

.header-btn {
  position: absolute;
  left: 0;
  top: 0;
  width: 54px;
  height: 54px;
}

.bottom {
  position: fixed;
  bottom: 0;
  display: flex;
  justify-content: space-between;
  width: 100vw;
  height: auto;
  background-color: #fafbfe;
  background-image: url(../images_2/btm-b.png);
  background-size: contain;
}
.mine-block {
  position: fixed;
  height: 70px;
  width: 70px;
  right: 0;
  bottom: 0;
}
</style>
<style>
.logout-dialog {
  border-radius: 4px;
}

.logout-dialog .van-dialog__cancel {
  color: #dd394c;
}

.logout-dialog .van-dialog__confirm,
.logout-dialog .van-dialog__confirm:active {
  color: #fff;
  background-color: #dd394c;
}
</style>
