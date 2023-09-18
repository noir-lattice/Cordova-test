<template>
    <div ref="welcomContentRef" style="height: 100%;">
        <span class="phoneNum" :style="{ top: top3 + 'px', left: left + 'px' }">{{ info.phoneNum }}</span>
        <img class="head-img" :style="{ top: top + 'px' }" src="../images/login-top.jpg" />
        <img class="middle-img" :style="{ top: top2 + 'px' }" src="../images/login-middle.png" />
        <div class="active-block" v-if="!keyboardVisible" :style="{ top: top4 + 'px' }" @click="openKeyboard"></div>
        <input ref="passwdRef" :value="passwd" type="password" class="passwd-input"
            :style="{ top: top5 + 'px', left: left2 + 'px', position: keyboardVisible ? 'fixed' : 'none' }" />

        <div class="submit-block" v-if="keyboardVisible" :style="{ top: top6 + 'px' }" @click="submit"></div>
        <div class="back-block" :style="{ top: '0px' }" @click="back"></div>
    </div>

    <div style="display: flex;justify-content: center;">
        <img class="line-img" src="../images/login-line.jpg" />
        <img class="wx-img" src="../images/login-wx.png" />
    </div>

    <img v-if="keyboardVisible" class="keyboard-img" @click="inputPasswd" src="../images/login-keyboard.png" />
</template>

<style scoped>
.back-block {
    position: fixed;
    height: 50px;
    width: 100%;
}
.submit-block {
    position: fixed;
    height: 50px;
    width: 100%;
}

.passwd-input {
    border: none;
    height: 35px;
}

.active-block {
    position: fixed;
    height: 50px;
    width: 100%;
}

.keyboard-img {
    position: fixed;
    margin-left: -1px;
    bottom: 0px;
    z-index: 999;
    width: 100%;
}

.phoneNum {
    padding: 8px;
    background: white;
    position: fixed;
    z-index: 999;
    font-size: 24px;
    letter-spacing: 1px;
}

.wx-img {
    position: fixed;
    bottom: 50px;
    height: 36px;
    z-index: 19;
}

.line-img {
    position: fixed;
    bottom: 100px;
    width: 100%;
    z-index: 19;
}

.bottom-img {
    position: fixed;
    bottom: 0px;
    width: 100%;
    z-index: 999;
}

.head-img {
    width: 100%;
    position: fixed;
}

.middle-img {
    position: fixed;
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

<script setup lang="ts">
import { onMounted, ref } from "vue";
import { isLogin } from "./status";
import type { UserInfo } from "../interface";

const props = defineProps<{
    goto: (target: "main" | "detail" | "bill" | "card" | "mine" | 'home') => void;
    info: UserInfo;
}>();
const welcomContentRef = ref<any>(null)
const type = ref<any>(null);
const bottomPos = ref(0)
const top = ref(0);
const top2 = ref(0)
const top3 = ref(0)
const top4 = ref(0)
const top5 = ref(0)
const top6 = ref(0)
const left = ref(0)
const left2 = ref(0)
onMounted(() => {
    setTimeout(() => {
        console.log(welcomContentRef.value.clientHeight)
        const rate = welcomContentRef.value.clientWidth / 375;
        const h = welcomContentRef.value.clientHeight / rate
        console.log(welcomContentRef.value.clientHeight, h)
        top.value = -34 * rate;
        top2.value = 350 * rate;
        top3.value = (109 * rate) - 8;
        left.value = (22 * rate) - 8;

        top4.value = 205 * rate;
        top5.value = 214 * rate;
        top6.value = 285 * rate;
        left2.value = 22 * rate;
        bottomPos.value = rate * 42;
    }, 10);
})

const passwd = ref('')
const passwdRef = ref<any>(null)
const keyboardVisible = ref(false);
function openKeyboard() {
    passwdRef.value.setAttribute("readonly", "readonly")
    passwdRef.value.focus()
    keyboardVisible.value = true;
    setTimeout(() => {
        passwdRef.value.removeAttribute("readonly")
    }, 20);
}

function inputPasswd() {
    passwd.value += '1';
    passwdRef.value.setAttribute("readonly", "readonly")
    passwdRef.value.focus()
    setTimeout(() => {
        passwdRef.value.removeAttribute("readonly")
    }, 20);
}

function submit() {
    isLogin.value = true;
    props.goto('home')
}

function back() {
    props.goto('home')
}

</script>