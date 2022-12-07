<template>
  <div class="main">
    <div class="header header-top">
      <div>
        <div class="header-icon logout" />
      </div>
      <div class="flex">
        <div class="header-icon search" />
        <div class="header-icon setting" />
        <div class="header-icon more" />
      </div>
    </div>
    <div class="header user-info flex">
      <div class="flex" @click="goto('detail')">
        <div class="avatar" />
        <div class="username">
          <div>{{ info.nickname }}</div>
          <div class="login-tip">上次登录 今天</div>
        </div>
      </div>
      <div class="level" />
    </div>
    <div class="header asset-detail">
      <div class="card" @click="goto('card')" />
    </div>

    <div class="header asset-card">
      <div class="card-header">
        <div class="my-asset" />
      </div>
      <div class="card-content">
        <div class="asset">
          <div class="asset-title">我的资产</div>
          <div>******</div>
        </div>
        <div class="asset">
          <div class="asset-title">我的负债</div>
          <div>******</div>
        </div>
      </div>
      <div class="card-footer">数据截至 {{ dateFormat(new Date()) }}</div>
    </div>
    <div class="header asset-card" @click="goto('bill')">
      <div class="card-header">
        <div class="month-balance" />
      </div>
      <div class="card-content">
        <div class="asset">
          <div class="asset-title">支出</div>
          <div>******</div>
        </div>
        <div class="asset">
          <div class="asset-title">收入</div>
          <div>******</div>
        </div>
      </div>
    </div>
    <div class="header asset-card">
      <div class="order" />
    </div>
    <div class="tab">
      <div class="tab-icon" />
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, onUnmounted } from "vue";
import { statusBarColor } from "@/bridge";
import type { UserInfo } from "../interface";
import { dateFormat } from "../utils";

const props = defineProps<{
  info: UserInfo;
  goto: (target: "main" | "detail" | "bill" | "card") => void;
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

<style scoped>
.main {
  background: #fff;
  padding-top: 70px;
  padding-bottom: 50px;
  position: relative;
}
.flex {
  display: flex;
}
.header-top {
  position: fixed;
  top: 0;
  background: #fff;
  width: 100vw;
}
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 10px;
  box-sizing: border-box;
  margin-bottom: 10px;
}
.header-icon {
  width: 50px;
  height: 50px;
  background-position: center;
  background-size: contain;
}
.header-icon.logout {
  background-image: url("../images/logout-icon.jpg");
}
.header-icon.search {
  background-image: url("../images/search-icon.jpg");
}
.header-icon.setting {
  background-image: url("../images/setting-icon.jpg");
}
.header-icon.more {
  background-image: url("../images/more-icon.jpg");
}
.avatar {
  width: 80px;
  height: 80px;
  border-radius: 100%;
  margin-right: 16px;
  background-image: url("../images/avatar.png");
  background-position: center;
  background-size: cover;
  background-repeat: no-repeat;
}
.username {
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.login-tip {
  color: #757575;
  font-size: 12px;
}
.level {
  width: 80px;
  height: 40px;
  background-image: url("../images/level-icon.jpg");
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
}
.asset-detail {
  height: 110px;
  background-image: url("../images/asset-icon.png");
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
}
.card {
  width: 110px;
  height: 110px;
}

.asset-card {
  flex-direction: column;
  align-items: baseline;
  margin: 16px;
  margin-bottom: 30px;
  padding: 0;
  overflow: hidden;
  border-radius: 16px;
  box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.2);
  color: #969696;
}
.card-header {
  width: 100%;
  height: 46px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.card-content {
  width: 100%;
  padding: 20px 16px;
  box-sizing: border-box;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.card-footer {
  width: 100%;
  padding: 16px;
  padding-top: 0;
  box-sizing: border-box;
  font-size: 12px;
}
.my-asset {
  width: 100%;
  height: 100px;
  background-image: url("../images/my-asset.png");
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
}
.month-balance {
  width: 100%;
  height: 100px;
  background-image: url("../images/month-balance.png");
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
}
.asset {
  letter-spacing: 1px;
}
.asset-title {
  font-size: 14px;
  margin-bottom: 6px;
}
.order {
  width: 100%;
  height: 134px;
  background-image: url("../images/order.png");
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
}
.tab {
  position: fixed;
  bottom: 0;
  width: 100vw;
  height: 55px;
  border-top: 1px solid #ececec;
}
.tab-icon {
  width: 100%;
  height: 100%;
  background-image: url("../images/tab.png");
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
}
</style>
