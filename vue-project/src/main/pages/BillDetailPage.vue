<template>
  <div class="detail">
    <div class="header">
      <div class="arrow" @click="goto('main')">
        <div class="arrow-icon" />
      </div>
      收支
      <div class="suffix">
        <div class="plus-icon" />
        <div class="more-icon" />
      </div>
    </div>
    <div class="filter">
      <div class="filter-left">
        <div class="filter-item">{{ date }}</div>
        <div class="filter-item">全部账号</div>
      </div>
      <div>
        <div class="filter-item" style="margin-right: 16px">筛选</div>
      </div>
    </div>
    <div class="content">
      <div class="balance-header">
        <div class="balance-header-tip">
          <span>您在这段时间:</span>
        </div>
        <div class="balance-header-content">
          <div class="outcome">
            <divv class="amount">{{ info.outcome }}</divv>
            <div class="balance-tip">支出(元)</div>
          </div>
          <div class="income">
            <div class="amount">{{ info.income }}</div>
            <div class="balance-tip">收入(元)</div>
          </div>
        </div>
      </div>
      <div class="bills">
        <div v-for="(record, i) of info.records" :key="i" class="bill-item">
          <div
            :class="{
              'bill-item-main': true,
              'is-outcome': isOutcome(record.amount),
            }"
          >
            <div class="overflow">{{ record.title }}</div>
            <div class="amount">{{ record.amount }}</div>
          </div>
          <div class="bill-item-sub">
            <div>{{ record.happenTime }}</div>
            <div>{{ record.desc }}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { BillInfo } from "../interface";

const props = defineProps<{
  info: BillInfo;
  goto: (target: "main" | "detail" | "bill" | "card") => void;
}>();
const bills = computed(() => props.info);
const date = computed(() => bills.value.date);

const isOutcome = (amount: string) => {
  return amount.includes("-");
};
</script>

<style scoped>
.detail {
  box-sizing: border-box;
  padding: 10px 0;
  padding-top: 100px;
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
.suffix {
  position: absolute;
  right: 10px;
  display: flex;
}
.plus-icon {
  width: 20px;
  height: 20px;
  background-image: url("../images/plus.png");
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
  margin-right: 6px;
}
.more-icon {
  width: 20px;
  height: 20px;
  background-image: url("../images/more_light.png");
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
}
.filter {
  display: flex;
  justify-content: space-between;
  padding: 16px;
  position: fixed;
  top: 50px;
  background: #fff;
  width: 100vw;
  height: 50px;
  z-index: 1;
  font-size: 14px;
}
.filter-left {
  display: flex;
}
.filter-item {
  margin-right: 30px;
  position: relative;
}
.filter-item::after {
  content: "";
  width: 20px;
  height: 20px;
  position: absolute;
  background-image: url("../images/arrow-sort.png");
  background-position: center;
  background-size: contain;
}
.content {
  background: #fefefe;
  margin: 16px;
  margin-top: 0;
}
.balance-header {
  width: 100%;
  height: 130px;
  background-image: url("../images/balance-bg.png");
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
  padding: 24px;
}
.balance-header-tip {
  position: relative;
  font-size: 16px;
  margin-bottom: 14px;
}
.balance-header-tip::after {
  content: "";
  width: 16px;
  height: 16px;
  position: absolute;
  right: 0;
  top: 4px;
  background-image: url("../images/info.png");
  background-position: center;
  background-size: contain;
}
.balance-header-content {
  display: flex;
  justify-content: space-between;
}
.outcome,
.income {
  display: flex;
  flex-direction: column;
}
.outcome {
  align-items: start;
}
.income {
  align-items: end;
}
.amount {
  font-size: 16px;
  font-weight: 500;
}
.balance-tip {
  color: #666;
  font-size: 14px;
}
.bills {
  background-color: #fff;
}
.bill-item {
  position: relative;
  padding: 16px 40px;
}
.bill-item::after {
  content: "";
  position: absolute;
  left: 0;
  bottom: 0;
  height: 1px;
  width: 100%;
  background: #f7f7f7;
}
.bill-item-main,
.bill-item-sub {
  position: relative;
  display: flex;
  justify-content: space-between;
}
.bill-item-main {
  font-size: 15px;
  margin-bottom: 4px;
}
.bill-item-sub {
  color: #666;
  font-size: 14px;
}
.bill-item-main::before {
  content: "";
  width: 20px;
  height: 20px;
  position: absolute;
  top: 2px;
  left: -26px;
  background-image: url("../images/income.png");
  background-position: center;
  background-size: contain;
}
.bill-item-main::after {
  content: "";
  width: 20px;
  height: 20px;
  position: absolute;
  top: 2px;
  right: -26px;
  background-image: url("../images/arrow-right.png");
  background-position: center;
  background-size: contain;
}
.bill-item-main .amount {
  color: #faa72f;
  text-align: right;
  min-width: 110px;
  margin-left: 10px;
}
.bill-item-main.is-outcome .amount {
  color: #333;
}
.bill-item-main.is-outcome::before {
  background-image: url("../images/outcome.png");
}
.overflow {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
</style>
