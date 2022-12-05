<template>
    <div v-for="(item, idx) of info" :key="idx">
        <div>{{item.month}}</div>

        <div>支出 ¥{{item.outcome}}</div>
        <div>收入 ¥{{item.income}}</div>
        <div>收支分析</div>
        <div v-for="(record, i) of item.records" :key="i">
            <img style="width: 100px" :src="record.b64icon" />
            <div>{{record.title}}</div>
            <div>{{record.desc}}</div>
            <div>{{record.happenTime}}</div>
            <div>{{record.amount}}</div>
        </div>
     </div>
    <button @click="goto('main')">返回主页</button>
</template>

<script setup lang="ts">
import type { BillInfo } from '../interface';

const props = defineProps<{
  info: BillInfo[];
  goto: (target: "main" | "detail" | "bill") => void;
}>();


</script>
