<template>
  <div class="loading-container" v-if="!config" v-loading="!config" />
  <div class="un-reject-container" v-if="config && config.code != 0">
    <Form @submit="submitRegister">
      <CellGroup inset>
        <Field
          v-model="registerForm.code"
          placeholder="请输入激活码"
          :rules="[{ required: true, message: '请输入激活码' }]"
        />
      </CellGroup>
      <div style="margin: 16px">
        <Button round block type="primary" native-type="submit"> 提交 </Button>
      </div>
    </Form>
  </div>
  <div class="main-view-container" v-if="config && config.code == 0">
    <main-page
      v-if="currentPage == 'main'"
      :goto="goto"
      :info="config.body.userInfo"
    />
    <card-page
      v-if="currentPage == 'card'"
      :goto="goto"
      :info="config.body.userInfo"
    />
    <auth-detail-page
      v-if="currentPage == 'detail'"
      :goto="goto"
      :info="config.body.userInfo"
    />
    <bill-detail-page
      v-if="currentPage == 'bill'"
      :goto="goto"
      :info="config.body.billInfos"
    />
  </div>
</template>

<style scoped>
.loading-container {
  width: 100%;
  height: 100%;
}
.un-reject-container {
  height: 100%;
  padding: 0 25px;
}
</style>

<script setup lang="ts">
import { Form, CellGroup, Field, Button, showToast } from "vant";
import { ref, onMounted, onUnmounted } from "vue";
import { loadLocalConfig, register, check } from "@/bridge";
import type { ConfigResp } from "@/bridge";
import type { PageConfig } from "./interface";
import MainPage from "./pages/MainPage.vue";
import AuthDetailPage from "./pages/AuthDetailPage.vue";
import BillDetailPage from "./pages/BillDetailPage.vue";
import CardPage from "./pages/CardPage.vue";

/**
 * 配置加载及定时刷新（确认是否激活）
 */
const config = ref<ConfigResp<PageConfig>>();
async function refreshConfig() {
  try {
    const res = await loadLocalConfig<PageConfig>();
    config.value = res;
  } catch (e) {
    showToast("e=" + e);
  }
}
let refreshInterval: any;
function startRefresher() {
  check().then(() => {
    refreshInterval = setInterval(() => {
      if (!config.value || config.value.code == 0) {
        refreshConfig().then();
      }
    }, 5000);
  });
}
function stopRefresher() {
  clearInterval(refreshInterval);
}
onMounted(startRefresher);
onUnmounted(stopRefresher);

/**
 * 输入注册表单
 */
const registerForm = ref({ code: "" });
const registerLoading = ref(false);
async function submitRegister() {
  registerLoading.value = true;
  try {
    const state = await register(registerForm.value.code);
    if (state) {
      showToast("激活成功");
      await refreshConfig();
    } else {
      showToast("激活码验证失败");
    }
  } catch (e) {
    showToast("e=" + e);
  } finally {
    registerLoading.value = false;
  }
}

const currentPage = ref<"main" | "detail" | "bill" | "card">("main");
function goto(target: "main" | "detail" | "bill" | "card") {
  currentPage.value = target;
}
</script>
<style>
* {
  box-sizing: border-box;
}

#app {
  --van-overlay-background: rgba(0, 0, 0, 0.2);
}
</style>
