<template>
  <div class="detail">
    <div class="header">
      <div class="arrow" @click="goto('main')">
        <div class="arrow-icon" />
      </div>
      银行卡管理
    </div>
    <div style="margin: 12px">我的银行卡(1张)</div>
    <div class="card-groud">
      <div class="card-item">
        <div class="card-item-left">
          <div class="bank-icon"><div class="logo" /></div>
        </div>
        <div>
          <div>中国农业银行</div>
          <div style="margin: 4px 0; font-weight: 300">储蓄卡</div>
          <div>{{ info.cardNum }}</div>
        </div>
      </div>
    </div>
    <div class="add">
      <div style="color: #f6a92d">
        <span class="add-icon"> + </span>
        添加银行卡
      </div>
      <div style="color: #666; margin-top: 4px; font-size: 14px">
        支持绑定多家银行卡
      </div>
    </div>
    <div class="buttons">
      <div class="button-item account" />
      <div class="button-item credit" />
    </div>
  </div>
</template>

<script setup lang="ts">
import type { UserInfo } from "../interface";

const props = defineProps<{
  info: UserInfo;
  goto: (target: "main" | "detail" | "bill") => void;
}>();
</script>

<style scoped>
.detail {
  background: #f7f7f7;
  height: 100vh;
  box-sizing: border-box;
  padding: 10px 0;
  padding-top: 60px;
}
.header {
  position: fixed;
  top: 0;
  background: #fff;
  width: 100vw;
  height: 50px;
  font-weight: 500;
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1;
}
.arrow {
  position: absolute;
  left: 10px;
}
.arrow-icon {
  width: 20px;
  height: 20px;
  background-image: url("../images/arrow-left-bold.png");
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
}
.card-groud {
  margin: 12px;
  display: flex;
  flex-direction: column;
}
.card-item {
  height: 140px;
  width: 100%;
  background-image: url("../images/card-bg.png");
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
  color: #fff;
  display: flex;
  padding: 30px 20px;
}
.card-item-left {
  margin-right: 10px;
}
.bank-icon {
  width: 40px;
  height: 40px;
  border-radius: 100%;
  background: #fff;
  padding: 5px;
  box-sizing: border-box;
}
.logo {
  width: 30px;
  height: 30px;
  background-image: url("../images/logo.png");
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
}
.add {
  padding: 12px;
  margin: 20px 12px;
  border: 1px dashed #cacaca;
  border-radius: 4px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.add-icon {
  font-size: 22px;
  margin-right: 4px;
  display: inline-block;
  line-height: 1;
}
.buttons {
  margin: 20px 12px;
  display: flex;
}
.button-item {
  height: 100px;
  flex: 1;
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
}
.button-item.account {
  background-image: url("../images/account-card.png");
  margin-right: 8px;
}
.button-item.credit {
  background-image: url("../images/credit-card.png");
  margin-left: 8px;
}
</style>
