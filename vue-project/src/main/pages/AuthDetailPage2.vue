<template>
    <div class="page-2-container">
        <div class="header">
            <img @click="goto('detail')" src="/info-header-1.png" />
            <img src="/info-header-2.png" />
        </div>

        <div class="bar-1">
            <div class="accountNo">{{ info.accountNo }}</div>
            <div class="email">{{ info.email }}</div>
            <div class="realName">{{ info.realName }}</div>
            <img src="/info-content-1.png" />
            <img src="/info-content-2.png" />
        </div>

        <div class="bar-2">
            <img src="/info-bottom-1.png" />
            <img src="/info-bottom-2.png" />
            <div class="bottom-text">账号挂失、解限、举报等安全服务</div>
        </div>

        <!-- <div class="bar-1">
        <img src="/p2-content-1.png" />
        <img src="/p2-content-3.png" />
        <img class="avatar" :src="info.avatar" />
        <div class="nickname">{{ info.nickname }}</div>
        <div class="phoneNum">{{ info.accountNo }}</div>
      </div> -->


    </div>
</template>
  
<script setup lang="ts">
import type { UserInfo } from "../interface";
import { statusBarColor } from "@/bridge";
import { ElMessage } from "element-plus";

const props = defineProps<{
    info: UserInfo;
    goto: (target: "main" | "detail" | "bill") => void;
}>();

function setStatusBar() {
  statusBarColor("#000000").then();
}
onMounted(setStatusBar);
</script>
  
<style scoped>
.header {
    margin-top: 5px;
    margin-left: -1px;
    display: flex;
    justify-content: space-between;
    background-color: #f5f5f5;
}

.email {
    position: absolute;
    right: 41px;
    margin-top: 64px;
    color: #9e9e9e;
    background: white;
}

.realName {
    position: absolute;
    right: 41px;
    margin-top: 281px;
    background: white;
    color: #9c9c9c;
    width: 100px;
    padding-left: 20px;
}

.accountNo {
    position: absolute;
    right: 38px;
    margin-top: 16px;
    font-size: 14.7px;
    color: #9f9f9f;
    background: white;
    padding-left: 20px;
    padding-right: 5px;
}

.header img {
    height: 60px;
}

.bar-1 {
    margin-left: -1px;
    display: flex;
    justify-content: space-between;
    background-image: url(/info-content-3.png);
    background-size: contain;
}

.bar-1 img {
    height: 425px;
}

.bar-1 .avatar {
    position: absolute;
    width: 39px;
    height: 39px !important;
    border-radius: 5px;
    top: 65px;
    right: 46px;
}

.bar-1 .nickname {
    position: absolute;
    font-family: "Roboto", "Noto Sans SC";
    font-style: normal;
    font-size: 14px;
    line-height: 14px;
    right: 45px;
    top: 136px;
    color: #999999;
    background: white;
    min-width: 100px;
    text-align: right;
}

.bottom-text {
    position: absolute;
    margin-top: 41px;
    margin-left: 26px;
    background: white;
    font-size: 12px;
    color: #9f9f9f;
}

.bar-1 .phoneNum {
    position: absolute;
    font-family: "Roboto", "Noto Sans SC";
    font-style: normal;
    font-size: 14px;
    line-height: 14px;
    right: 43px;
    top: 185px;
    color: #999999;
    background: white;
}

.page-2-container {
    background-color: whitesmoke;
}

.bar-2 {
    margin-left: -2px;
    display: flex;
    justify-content: space-between;
    width: 100%;
    margin-top: -2px;
    background-image: url(/info-bottom-3.png);
    background-size: contain;
}

.bar-2 img {
    height: 135px;
}

.bar-2 .bar2-text {
    position: absolute;
    font-family: "Roboto", "Noto Sans SC";
    font-style: normal;
    color: #999999;
    font-size: 14px;
    top: 336px;
    background-color: white;
    padding: 0 10px;
}

.bar-3 .real-name {
    font-size: 15px;
    line-height: 15px;
    position: absolute;
    top: 405px;
    font-family: "Roboto", "Noto Sans SC";
    font-style: normal;
    color: #999999;
    right: 10px;
    background-color: white;
}

.bar-3 {
    display: flex;
    justify-content: space-between;
    background-image: url(/p2-bar-3-fill.png);
    background-size: contain;
}

.bar-3 img {
    height: 251px;
}

.bar-4 {
    margin-top: 12px;
    display: flex;
    justify-content: space-between;
    background-image: url(/p2-bar-4-fill.png);
    background-size: contain;
}

.bar-4 img {
    height: 99px;
}
</style>
  