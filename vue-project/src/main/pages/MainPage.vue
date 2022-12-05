<template>
  <div>
    <div>我的</div>
    <div>图标1 图标2</div>
  </div>
  <div>
    <img style="width: 100px;" :src="info.avatar"/>
    <div>
        <div>{{info.nickname}}</div>
        <div>{{info.phoneNum}}</div>
    </div>
  </div>
  <button @click="goto('detail')">去认证信息</button>
  <button @click="goto('bill')">去记录</button>
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
  statusBarColor("#3875F6").then();
}
function cleanStatusBar() {
    statusBarColor("lightcontent").then();
}
onMounted(setStatusBar);
onUnmounted(cleanStatusBar);
</script>
