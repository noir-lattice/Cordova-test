<template>
  <div class="detail">
    <div class="header">
      <div class="arrow" @click="goto('main')">
        <div class="arrow-icon" />
      </div>
      交易明细
    </div>
    <div class="content-head">
      <div class="card-item">
        <div class="card-item-head">
          <div class="card-item-logo" />
          <div>
            <div>{{ userInfo.cardNum }}</div>
            <div style="color: #666; font-size: 14px">
              长城电子借记卡 （I类账户）
            </div>
          </div>
        </div>
      </div>
      <div class="filter">
        <div class="filter-left">近6个月明细</div>
        <div class="filter-right">筛选</div>
      </div>
    </div>
    <div class="bills">
      <div v-for="(record, i) of info" :key="i" class="month-bill">
        <div class="month-bill-num">{{ record.date }}</div>
        <div v-for="(month, j) of record.records" :key="j" class="day-bill">
          <div class="day-bill-num">
            <div style="font-size: 20px; line-height: 1">
              {{ month.day.slice(-2) }}
            </div>
            <div style="font-size: 12px; color: #888">
              {{ week[new Date(month.day).getDay()] }}
            </div>
          </div>
          <div style="flex: 1">
            <div v-for="(bill, k) of month.list" :key="k" class="bill-item">
              <div class="bill-item-main">
                <span style="font-size: 18px">{{ bill.title }}</span>
                <span style="color: #888">{{ bill.desc }}</span>
              </div>
              <div class="bill-item-sub">
                <span style="color: #888">余额{{ bill.balance }}</span>
                <span style="font-weight: 500">
                  人民币元
                  <span
                    :class="{ amount: true, outcome: isOutcome(bill.amount) }"
                    >{{ bill.amount }}</span
                  >
                </span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { BillInfo, UserInfo } from "../interface";

const props = defineProps<{
  info: BillInfo[];
  userInfo: UserInfo;
  goto: (target: "main" | "detail" | "bill" | "card") => void;
}>();

const week = ["周日", "周一", "周二", "周三", "周四", "周五", "周六"];

const isOutcome = (amount: string) => {
  return amount.includes("-");
};
</script>

<style scoped>
.detail {
  box-sizing: border-box;
  padding: 10px 0;
  padding-top: 185px;
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
  z-index: 2;
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
.content-head {
  background: #e9e8ec;
  position: fixed;
  top: 50px;
  width: 100vw;
  height: 135px;
  z-index: 2;
}
.card-item {
  margin: 12px 12px 0;
  background: #fff;
  border-radius: 6px;
}
.card-item-head {
  padding: 14px;
  display: flex;
  position: relative;
}
.card-item-head::after {
  content: "";
  width: 14px;
  height: 20px;
  position: absolute;
  right: 25px;
  top: 25px;
  background-image: url("../images_1/arrow-right-bold.png");
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
}
.card-item-logo {
  width: 50px;
  height: 40px;
  margin-right: 10px;
  background-image: url("../images_1/card-logo.png");
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
}

.filter {
  display: flex;
  justify-content: space-between;
  padding: 12px;
  width: 100%;
}
.filter-left {
  color: #666;
}
.filter-right {
  color: #666;
  margin-right: 32px;
  position: relative;
}
.filter-right::after {
  content: "";
  width: 18px;
  height: 18px;
  right: -26px;
  top: 2px;
  position: absolute;
  background-image: url("../images_1/filter.png");
  background-position: center;
  background-size: contain;
}
.month-bill-num {
  padding: 8px 12px;
  line-height: 1;
  font-size: 14px;
  color: #666;
  border-top: 1px solid #eee;
  border-bottom: 1px solid #eee;
  position: sticky;
  top: 185px;
  background: #f9f9f9;
  z-index: 1;
}
.day-bill {
  display: flex;
}

.day-bill-num {
  padding: 14px 12px;
  background: #f9f9f9;
}
.bill-item {
  padding: 12px;
  background: #fff;
  position: relative;
}

.bill-item::after {
  content: '';
  position: absolute;
  left:0;
  bottom: -1px;
  height: 1px;
  width: 100%;
  background: #eee;
  z-index: 1;
}
.bill-item-main,
.bill-item-sub {
  display: flex;
  justify-content: space-between;
}
.bill-item-sub {
  font-size: 16px;
}
.amount {
  color: #b64862;
}
.amount.outcome {
  color: #2e836c;
}
</style>
