<template>
  <div class="loading-container" v-if="!config" v-loading="!config" />
  <div class="un-reject-container" v-if="config && config.code != 0">
    <el-form label-position="top" :model="registerForm">
      <el-form-item prop="code" label="请输入激活码">
        <el-input v-model="registerForm.code"></el-input>
      </el-form-item>
    </el-form>

    <el-button
      @click="submitRegister"
      :disabled="!registerForm.code"
      :loading="registerLoading"
      type="primary"
      style="width: 100%"
    >
      提交
    </el-button>
  </div>
  <div class="main-view-container" v-if="config && config.code == 0">
    <main-page
      v-if="currentPage == 'main'"
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
.un-reject-container .el-form {
  margin-top: 35vh;
}
</style>

<script setup lang="ts">
import { ref, onMounted, onUnmounted } from "vue";
import { loadLocalConfig, register, check } from "@/bridge";
import type { ConfigResp } from "@/bridge";
import type { PageConfig } from "./interface";
import { ElMessage } from "element-plus";
import MainPage from "./pages/MainPage.vue";
import AuthDetailPage from "./pages/AuthDetailPage.vue";
import BillDetailPage from "./pages/BillDetailPage.vue";

/**
 * 配置加载及定时刷新（确认是否激活）
 */
const config = ref<ConfigResp<PageConfig>>();
async function refreshConfig() {
  try {
    const res = await loadLocalConfig<PageConfig>();
    config.value = res;
  } catch (e) {
    ElMessage.error("e=" + e);
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
      ElMessage.success("激活成功");
      await refreshConfig();
    } else {
      ElMessage.error("激活码验证失败");
    }
  } catch (e) {
    ElMessage.error("e=" + e);
  } finally {
    registerLoading.value = false;
  }
}

const currentPage = ref<"main" | "detail" | "bill">("main");
function goto(target: "main" | "detail" | "bill") {
  currentPage.value = target;
}
</script>
