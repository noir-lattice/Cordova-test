<template>
    <div ref="welcomContentRef" style="height: 100%;">
        <div v-if="type == 'raw'">
            <img class="head-img" :style="{ bottom: bottomPos + 'px' }" src="../images/splash_loading_bg.png" />
            <img class="bottom-img" src="../images/splash_loading_biggerscreen.jpg" />
        </div>
        <div v-if="type == 'combination'">
            <img class="head-img-c" src="../images/welcom-head.png" />
            <img class="bottom-img-c" src="../images/welcom-bottom.png" />
        </div>
    </div>
</template>

<style scoped>
.bottom-img {
    position: fixed;
    bottom: 0px;
    width: 100%;
    z-index: 999;
}

.head-img {
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
import { statusBarColor } from "@/bridge";
import { onMounted, ref } from "vue";

const props = defineProps<{
    goto: (target: "main" | "detail" | "bill" | "card" | "mine" | "home") => void;
}>();
const welcomContentRef = ref<any>(null)
const type = ref<any>(null);
const bottomPos = ref(0)
onMounted(() => {
    statusBarColor("#000000").then();
    setTimeout(() => {
        console.log(welcomContentRef.value.clientHeight)
        const rate = welcomContentRef.value.clientWidth / 375;
        const h = welcomContentRef.value.clientHeight / rate
        console.log(welcomContentRef.value.clientHeight, h)
        if (h > 667) {
            type.value = 'combination'
        } else {
            type.value = 'raw'
        }

        bottomPos.value = rate * 42;
    }, 100);
    setTimeout(() => {
        props.goto('home');
    }, 1500);
})


</script>