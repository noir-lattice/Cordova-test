<template>
  <div class="detail">
    <div class="header">
      <div class="arrow" @click="goto('main')">
        <div class="arrow-icon" />
      </div>
      个人信息
      <div style="position: absolute; right: 10px; font-size: 14px">修改</div>
    </div>
    <div class="tip" />
    <div style="padding: 14px; color: #6d6d6d">基本信息</div>
    <div class="form-group">
      <div class="form-item">
        <span>电子银行<br />客户序号</span>{{ info.cardNo }}
      </div>
      <div class="form-item"><span>姓名</span>{{ info.cardProvince }}</div>
      <div class="form-item"><span>英文/拼音姓名</span>{{ info.spell }}</div>
      <div class="form-item"><span>证件类型</span>居民身份证</div>
      <div class="form-item"><span>证件号码</span>{{ info.accountNo }}</div>
      <div class="form-item"><span>国家/地区</span>{{ info.country }}</div>
      <div class="form-item"><span>名族</span>{{ info.national }}</div>
      <div class="form-item"><span>性别</span>{{ info.sex }}</div>
      <div class="form-item"><span>出生日期</span>{{ info.both }}</div>
      <div class="form-item"><span>出生地</span></div>
    </div>
    <div style="padding: 14px; color: #6d6d6d">证件地址信息</div>
    <div class="form-group">
      <div class="form-item"><span>国家/地区</span>{{ info.cardCountry }}</div>
      <div class="form-item"><span>省/市/区</span>{{ info.cardProvince }}</div>
      <div class="form-item"><span>详细地址</span>{{ info.cardAddress }}</div>
    </div>
    <div style="padding: 14px; color: #6d6d6d">本人常住地址信息</div>
    <div class="form-group">
      <div class="form-item"><span>国家/地区</span>{{ info.country }}</div>
      <div class="form-item"><span>省/市/区</span>{{ info.province }}</div>
      <div class="form-item"><span>详细地址</span>{{ info.address }}</div>
      <div class="form-item"><span>邮编</span>{{ info.poster }}</div>
    </div>
    <div style="padding: 14px; color: #6d6d6d">工作信息</div>
    <div class="form-group">
      <div class="form-item"><span>职业</span></div>
      <div class="form-item"><span>工作单位名称</span></div>
      <div class="form-item"><span>单位所属行业</span></div>
      <div class="form-item"><span>个人月收入区间</span></div>
    </div>
    <div style="padding: 14px; color: #6d6d6d">联系信息</div>
    <div class="form-group">
      <div class="form-item"><span>手机号码</span>+86 {{ info.phoneNum }}</div>
    </div>
    <div class="tip1" />
  </div>
</template>

<script setup lang="ts">
import { statusBarColor } from "@/bridge";
import { onMounted, onUnmounted } from "vue";
import type { UserInfo } from "../interface";
function setStatusBar() {
  statusBarColor("#ffffff").then();
}
onMounted(setStatusBar);

const props = defineProps<{
  info: UserInfo;
  goto: (target: "main" | "detail" | "bill" | "card" | "mine") => void;
}>();
</script>

<style scoped>
.detail {
  background: #f4f4f4;
  box-sizing: border-box;
  padding: 10px 0;
  padding-top: 50px;
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
.tip{
  width: 100%;
  height: 58px;
  background-image: url("../images_1/tip.png");
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat; 
}
.tip1{
  width: 100%;
  height: 106px;
  background-image: url("../images_1/tip1.png");
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat; 
}
.form-group {
  background: #fff;
}
.form-item {
  position: relative;
  padding: 16px 14px;
  border-bottom: 1px solid #f6f6f6;
  display: flex;
  align-items: center;
}
.form-item span {
  display: inline-block;
  width: 120px;
  color: #6d6d6d;
}
</style>
