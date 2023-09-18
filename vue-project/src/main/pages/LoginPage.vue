<template>
  <div class="detail">
    <div ref="welcomContentRef" style="height: 100%; background: white">
      <span class="phoneNum" :style="{ top: top3 + 'px', left: left + 'px' }">{{
        info.phoneNum
      }}</span>
      <img
        class="head-img"
        :style="{ top: top + 'px' }"
        src="../images/login-top.jpg"
      />
      <img
        class="middle-img"
        :style="{ top: top2 + 'px' }"
        src="../images/login-middle.png"
      />
      <div
        class="active-block"
        v-show="!keyboardVisible"
        :style="{ top: top4 + 'px', 'z-index': '999' }"
        @click="openKeyboard"
      ></div>
      <input
        ref="passwdRef"
        v-model="passwd"
        type="password"
        class="passwd-input"
        :style="{
          top: top5 + 'px',
          left: left2 + 'px',
          position: keyboardVisible ? 'absolute' : 'none',
        }"
      />

      <div
        class="submit-block"
        :style="{ top: top6 + 'px' }"
        @click="submit"
      ></div>
      <div class="back-block" :style="{ top: '0px' }" @click="back"></div>
    </div>

    <div style="display: flex; justify-content: center">
      <img class="line-img" src="../images/login-line.jpg" />
      <img class="wx-img" src="../images/login-wx.png" />
    </div>

    <div v-show="keyboardVisible" class="keyboard">
      <div class="keyboard-close" @click="closeKeyboard"></div>
      <img
        class="keyboard-img"
        @click="inputPasswd"
        src="../images/login-keyboard.png"
      />
    </div>
  </div>

  <Dialog
    class-name="loading-dialog"
    v-model:show="loading"
    :show-confirm-button="false"
  >
    <img src="../images_2/loading-tip.png" />
    <Circle
      class="half-circle"
      size="32px"
      layer-color="transparent"
      stroke-width="160"
      color="#f76e74"
      :current-rate="16"
    ></Circle>
  </Dialog>
</template>

<style scoped>
.detail {
  background: #fff;
  width: 100vw;
  height: 100vh;
  position: fixed;
}
.back-block {
  position: absolute;
  height: 50px;
  width: 100%;
}
.submit-block {
  position: absolute;
  height: 50px;
  width: 100%;
}

.passwd-input {
  border: none;
  height: 35px;
  background-color: white;
  color: black !important;
}

.active-block {
  position: absolute;
  height: 50px;
  width: 100%;
}

.keyboard {
  position: absolute;
  margin-left: -1px;
  bottom: 0px;
  z-index: 999;
}
.keyboard-img {
  width: 100%;
}
.keyboard-close {
  position: absolute;
  top: 0;
  right: 0;
  width: 50px;
  height: 50px;
}

.phoneNum {
  padding: 8px;
  background: white;
  color: black !important;
  position: absolute;
  z-index: 999;
  font-size: 24px;
  letter-spacing: 1px;
}

.wx-img {
  position: absolute;
  bottom: 50px;
  height: 36px;
  z-index: 19;
}

.line-img {
  position: absolute;
  bottom: 100px;
  width: 100%;
  z-index: 19;
}

.bottom-img {
  position: absolute;
  bottom: 0px;
  width: 100%;
  z-index: 999;
}

.head-img {
  width: 100%;
  position: absolute;
}

.middle-img {
  position: absolute;
  width: 100%;
}

.bottom-img-c {
  bottom: 0px;
  width: 100%;
  z-index: 999;
}

.head-img-c {
  width: 100%;
}
</style>

<style>
.loading-dialog,
.loading-dialog img {
  width: 160px;
  height: 50px;
  border-radius: 4px;
}
.half-circle {
  position: absolute;
  left: 19px;
  top: 9px;
  animation: rotation 2s linear infinite;
}
@keyframes rotation {
  from {
    rotate: 0deg;
  }
  to {
    rotate: 360deg;
  }
}
</style>

<script setup lang="ts">
import { Dialog, Circle } from "vant";
import { onMounted, ref } from "vue";
import { isLogin } from "./status";
import type { UserInfo } from "../interface";
import { delay } from "../utils";

const props = defineProps<{
  goto: (target: "main" | "detail" | "bill" | "card" | "mine" | "home") => void;
  info: UserInfo;
}>();
const welcomContentRef = ref<any>(null);
const type = ref<any>(null);
const bottomPos = ref(0);
const top = ref(0);
const top2 = ref(0);
const top3 = ref(0);
const top4 = ref(0);
const top5 = ref(0);
const top6 = ref(0);
const left = ref(0);
const left2 = ref(0);
onMounted(() => {
  setTimeout(() => {
    console.log(welcomContentRef.value.clientHeight);
    const rate = welcomContentRef.value.clientWidth / 375;
    const h = welcomContentRef.value.clientHeight / rate;
    console.log(welcomContentRef.value.clientHeight, h);
    top.value = -34 * rate;
    top2.value = 350 * rate;
    top3.value = 109 * rate - 8;
    left.value = 22 * rate - 8;

    top4.value = 205 * rate;
    top5.value = 214 * rate;
    top6.value = 285 * rate;
    left2.value = 22 * rate;
    bottomPos.value = rate * 42;
  }, 10);
});

const passwd = ref("");
const passwdRef = ref<any>(null);
const keyboardVisible = ref(false);
function openKeyboard() {
  passwdRef.value.setAttribute("readonly", "readonly");
  passwdRef.value.focus();
  keyboardVisible.value = true;
  setTimeout(() => {
    passwdRef.value.removeAttribute("readonly");
  }, 20);
}
function closeKeyboard() {
  keyboardVisible.value = false;
}

function inputPasswd() {
  passwd.value += "1";
  passwdRef.value.setAttribute("readonly", "readonly");
  passwdRef.value.focus();
  setTimeout(() => {
    passwdRef.value.removeAttribute("readonly");
  }, 20);
}

const loading = ref(false);
async function submit() {
  if (!passwd.value) {
    return;
  }
  loading.value = true;
  await delay();
  loading.value = false;
  isLogin.value = true;
  keyboardVisible.value = false;
  passwd.value = "";
  props.goto("home");
}

function back() {
  props.goto("home");
}
</script>
