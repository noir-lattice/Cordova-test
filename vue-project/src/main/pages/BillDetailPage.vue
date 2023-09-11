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
        <div class="filter-item" @click="showBottom = true">{{ showDate }}</div>
        <div class="filter-item">全部账号</div>
      </div>
      <div>
        <div class="filter-item" style="margin-right: 16px">筛选</div>
      </div>
    </div>
    <div class="content">
      <div class="balance-header">
        <div v-if="dateMode === '1'" class="show-month-tip">
          <span class="month">
            <span class="month-num">{{ Number(date[0][1]) }}</span> 月
          </span>
          <span class="ans">分析</span>
        </div>
        <div v-else class="balance-header-tip">
          <span>您在这段时间:</span>
        </div>
        <div class="balance-header-content">
          <div class="outcome">
            <div class="amount">{{ record?.outcome }}</div>
            <div class="balance-tip">支出(元)</div>
          </div>
          <div class="income">
            <div class="amount">{{ record?.income }}</div>
            <div class="balance-tip">收入(元)</div>
          </div>
        </div>
      </div>
      <div class="bills">
        <div v-for="(record, i) of records" :key="i" class="bill-item">
          <div
            :class="{
              'bill-item-main': true,
              'is-outcome': record.type === 'out',
            }"
          >
            <div class="overflow">{{ record.title }}</div>
            <div class="amount">
              {{ record.type === "out" ? "-" : "+" }}￥{{ record.amount }}
            </div>
          </div>
          <div class="bill-item-sub">
            <div>{{ record.happenTime }}</div>
            <div>{{ record.desc }}</div>
          </div>
        </div>
      </div>
    </div>
    <date-selector
      v-model:value="showBottom"
      :mode="dateMode"
      @submit="handleChangeDate"
    >
      <template v-slot:tip>
        <div>
          若查询时间为{{ thisYear - 2 }}年{{ thisMonth - 1 }}月之前,请
          <span class="click-txt"> 点击此处 </span>
          跳转至明细
        </div>
      </template>
    </date-selector>
  </div>
</template>

<script setup lang="ts">
import type { BillInfo } from "../interface";
import DateSelector from "@/main/components/DateSelector/index.vue";

const props = defineProps<{
  info: BillInfo;
  goto: (target: "main" | "detail" | "bill" | "card") => void;
}>();
const bills = computed(() => props.info);

function formatNumberWithCommas(number) {
  // 判断输入是否为数字
  if (typeof number !== "number") {
    return "Invalid Input";
  }

  // 将数字转换为字符串，并按小数点分割
  const parts = number.toFixed(2).toString().split(".");

  // 将整数部分加上千分位逗号
  parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");

  // 返回格式化后的数字
  return parts.join(".");
}

const showBottom = ref(false);

const now = new Date();
const thisYear = now.getFullYear();
const thisMonth = now.getMonth() + 1;
const dateMode = ref("1");
const date = ref([[`${thisYear}`, `0${thisMonth}`.slice(-2)]]);

const showDate = computed(() =>
  dateMode.value === "1"
    ? date.value[0].join("-")
    : date.value[0].join(".") + "-" + date.value[1].join(".")
);

const records = computed(() => {
  const list = bills.value?.records ?? [];
  if (dateMode.value === "1") {
    const [[year, month]] = date.value;
    return list.filter(
      (i) => i.year === year && i.happenTime.startsWith(month)
    );
  } else {
    const [[sy, sm, sd], [ey, em, ed]] = date.value;
    return list.filter((i) => {
      const date = new Date(`${i.year}-${i.happenTime}`).getTime();
      const sdate = new Date(+sy, +sm, +sd, 0, 0, 0).getTime();
      const edate = new Date(+ey, +em, +ed + 1, 0, 0, 0).getTime();
      return date >= sdate && date < edate;
    });
  }
});

const record = computed(() => {
  return {
    income: formatNumberWithCommas(
      records.value
        .filter((i) => i.type === "in")
        .reduce((pre, cur) => pre + Number(cur.amount), 0)
    ),
    outcome: formatNumberWithCommas(
      records.value
        .filter((i) => i.type === "out")
        .reduce((pre, cur) => pre + Number(cur.amount), 0)
    ),
  };
});

const handleChangeDate = (mode, data) => {
  dateMode.value = mode;
  date.value = data;
};
</script>

<style scoped>
.detail {
  box-sizing: border-box;
  padding-top: 100px;
  background: #fefefe;
  min-height: 100vh;
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
.balance-header-tip::after,
.month::after {
  content: "";
  width: 16px;
  height: 16px;
  position: absolute;
  right: 0;
  bottom: 2px;
  background-image: url("../images/info.png");
  background-position: center;
  background-size: contain;
}
.balance-header-content {
  display: flex;
  justify-content: space-between;
}
.show-month-tip {
  display: flex;
  align-items: baseline;
  justify-content: space-between;
  margin-bottom: 14px;
}
.month {
  font-size: 17px;
  position: relative;
  padding-right: 20px;
}
.month-num {
  font-size: 26px;
  font-family: "Times New Roman", Times, serif;
}
.ans {
  display: inline-block;
  border: 1px solid #333;
  border-radius: 30px;
  font-size: 12px;
  padding: 1px 8px;
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
.click-txt {
  color: #faa72f;
}
</style>
