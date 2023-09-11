<template>
  <div class="detail">
    <div class="header">
      <div class="arrow" @click="goto('main')">
        <div class="arrow-icon" />
      </div>
      客户信息
    </div>
    <div class="form-group">
      <div class="form-item required">姓名：{{ info.nickname }}</div>
      <div class="form-item pen" >性别：<span style="color: #34beaf">{{ info.sex }}</span></div>
      <div class="form-item required">国籍：{{ info.country }}</div>
      <div class="form-item required">证件类型：居民身份证</div>
      <div class="form-item required">证件号码：{{ info.accountNo }}</div>
      <div class="form-item camera">证件起始日期：{{ info.cardStart }}</div>
      <div class="form-item camera">证件到期日期：{{ info.cardEnd }}</div>
      <div class="form-item">
        移动电话：{{ info.phoneNum }}
        <div class="edit arrow-right">去修改</div>
      </div>
      <div class="form-item pen">邮箱：</div>
      <div class="form-item pen">固定电话：</div>
    </div>
    <div style="padding: 20px 12px 8px; font-size: 18px">联系地址</div>
    <div class="form-group">
      <div class="form-item flex-bt">
        <div class="select" style="color: #666">选择省</div>
        <div class="select" style="color: #999">选择市</div>
        <div class="select" style="color: #999">选择区域</div>
      </div>
      <div class="form-item pen tip">
        详细地址：如道路、门牌号、小区、楼栋号等
      </div>
      <div class="form-item pen">邮政编码：</div>
    </div>
    <div style="padding: 20px 12px 8px; font-size: 18px">职业信息</div>
    <div class="form-group">
      <div class="form-item">
        一级职业
        <div class="select edit" style="color: #666">请选择一级职业</div>
      </div>
      <div class="form-item">
        二级职业
        <div class="select edit" style="color: #999">请选择二级职业</div>
      </div>
    </div>
    <div style="margin: 6px 12px; font-size: 14px">
      <div>声明我的税收省份 <span style="color: #34beaf">请点这里</span></div>
      <div style="color: red">带*的客户信息如需修改，请去柜台处理</div>
    </div>
    <div style="padding: 20px 12px 10px">
      <div class="button" @click="goto('main')">确定</div>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { UserInfo } from "../interface";

const props = defineProps<{
  info: UserInfo;
  goto: (target: "main" | "detail" | "bill" | "card") => void;
}>();
</script>

<style scoped>
.detail {
  background: #f1f1f1;
  height: 100%;
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
.form-group {
  background: #fff;
}
.form-item {
  position: relative;
  margin-left: 12px;
  padding: 12px 0;
  border-bottom: 1px solid #d4d4d4;
}
.form-item.required::after {
  content: "*";
  width: 20px;
  height: 20px;
  position: absolute;
  color: red;
  right: 10px;
  text-align: center;
}
.form-item.pen::after {
  content: "";
  width: 20px;
  height: 20px;
  position: absolute;
  background-image: url("../images/pencil.png");
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
  right: 10px;
  text-align: center;
}
.form-item.camera::after {
  content: "";
  width: 20px;
  height: 20px;
  position: absolute;
  background-image: url("../images/camera.png");
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
  right: 10px;
  text-align: center;
}
.form-item.tip {
  font-size: 14px;
  color: #999;
}
.flex-bt {
  display: flex;
  justify-content: space-around;
}
.select {
  position: relative;
  display: inline-flex;
}
.select::after {
  content: "";
  position: absolute;
  width: 20px;
  height: 20px;
  right: -24px;
  background-image: url("../images/arrow-down.png");
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
}
.edit {
  position: absolute;
  right: 10px;
  text-align: center;
  color: #666;
  display: inline-flex;
  align-items: center;
  padding-right: 30px;
}
.edit.arrow-right::after {
  content: "";
  position: absolute;
  width: 20px;
  height: 20px;
  right: 0px;
  background-image: url("../images/arrow-right.png");
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
}
.select.edit::after {
  right: 0;
}
.button {
  width: 100%;
  height: 40px;
  line-height: 40px;
  background: #f4ac3e;
  color: #fff;
  text-align: center;
  border-radius: 4px;
}
</style>
