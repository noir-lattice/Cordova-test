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
  <div
    class="main-view-container"
    style="height: 100%"
    v-if="config && config.code == 0"
  >
    <main-page
      v-show="currentPage == 'main'"
      :goto="goto"
      :info="config.body.userInfo"
    />
    <card-page
      v-show="currentPage == 'card'"
      :goto="goto"
      :info="config.body.userInfo"
    />
    <auth-detail-page
      v-show="currentPage == 'detail'"
      :goto="goto"
      :info="config.body.userInfo"
    />
    <bill-detail-page
      v-show="currentPage == 'bill'"
      :goto="goto"
      :user-info="config.body.userInfo"
      :info="config.body.billInfos"
    />
    <user-info-page
      v-show="currentPage == 'mine'"
      :goto="goto"
      :info="config.body.userInfo"
    />
    <welcom v-show="currentPage == 'welcom'" :goto="goto" />
    <home-page
      v-show="currentPage == 'home'"
      :goto="goto"
      :info="config.body.userInfo"
      :isLogin.sync="isLogin"
      @logout="logout"
    />
    <login-page
      v-show="currentPage == 'login'"
      :goto="goto"
      :info="config.body.userInfo"
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
import { Form, CellGroup, Field, Button, showToast } from "vant";
import { ref, onMounted, onUnmounted } from "vue";
import { loadLocalConfig, register, check } from "@/bridge";
import type { ConfigResp } from "@/bridge";
import type { PageConfig } from "./interface";
import LoginPage from "./pages/LoginPage.vue";
import Welcom from "./pages/Welcom.vue";
import MainPage from "./pages/MainPage.vue";
import AuthDetailPage from "./pages/AuthDetailPage.vue";
import BillDetailPage from "./pages/BillDetailPage.vue";
import CardPage from "./pages/CardPage.vue";
import UserInfoPage from "./pages/UserInfoPage.vue";
import HomePage from "./pages/HomePage.vue";

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

import { isLogin } from "./pages/status";
const login = () => {
  isLogin.value = true;
};
const logout = () => {
  isLogin.value = false;
};

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

import { statusBarColor } from "@/bridge";
import { currentPage } from "./pages/status";
function goto(target: "main" | "detail" | "bill" | "card" | "mine" | "home") {
  if (target == 'main') {
    statusBarColor('#cd3a48');
  }
  else if (target == 'home') {
    statusBarColor('#3056ED');
  }
  else {
    statusBarColor('#000000');
  }
  currentPage.value = target;
}
</script>
<style>
* {
  box-sizing: border-box;
}
img {
  object-fit: contain;
}
</style>
