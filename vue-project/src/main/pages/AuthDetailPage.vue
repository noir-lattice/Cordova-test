<template>
  <img style="width: 100px" :src="info.avatar" />
  <div>
    <div>{{ info.nickname }}</div>
    <div>{{ info.accountNo }}</div>
  </div>
  <button @click="goto('main')">返回主页</button>
</template>

<script setup lang="ts">
import type { UserInfo } from "../interface";

const props = defineProps<{
  info: UserInfo;
  goto: (target: "main" | "detail" | "bill") => void;
}>();
</script>
