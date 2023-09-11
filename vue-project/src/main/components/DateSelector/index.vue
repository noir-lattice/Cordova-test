<template>
  <Popup
    v-model:show="show"
    position="bottom"
    teleport="#app"
    :style="{ height: '40%' }"
  >
    <div class="btn-group">
      <div class="btn-item cancel-btn" @click="show = false">取消</div>
      <div class="btn-item comfirm-btn" @click="submit">确定</div>
    </div>
    <div v-if="$slots.tip" class="popup-tip">
      <slot name="tip"></slot>
    </div>
    <Tabs v-model:active="active" class="popup-tabs" line-width="50px">
      <Tab title="月份选择" name="1"></Tab>
      <Tab title="自定义" name="2"></Tab>
    </Tabs>
    <DatePicker
      v-if="active === '1'"
      v-model="currentDate"
      class="ym-picker"
      :min-date="minDate"
      :max-date="maxDate"
      :columns-type="columnsType"
      :show-toolbar="false"
      :visible-option-num="3"
    >
      <template v-slot:option="option">
        {{ Number(option.value) }}
      </template>
    </DatePicker>
    <PickerGroup
      v-else
      :tabs="['开始日期', '结束日期']"
      class="range-picker"
      v-model:active-tab="activeTab"
    >
      <template v-slot:toolbar>
        <div
          :class="{ 'date-item': true, active: activeTab === 0 }"
          @click="activeTab = 0"
        >
          {{ startDate.join(".") }}<Icon name="play" />
        </div>
        <Icon name="minus" />
        <div
          :class="{ 'date-item': true, active: activeTab === 1 }"
          @click="activeTab = 1"
        >
          {{ endDate.join(".") }}<Icon name="play" />
        </div>
      </template>
      <DatePicker
        v-model="startDate"
        :min-date="minDate"
        :max-date="maxDate"
        :visible-option-num="3"
      />
      <DatePicker
        v-model="endDate"
        :min-date="minDate"
        :max-date="maxDate"
        :visible-option-num="3"
      />
    </PickerGroup>
  </Popup>
</template>

<script setup lang="ts">
import { Popup, Tabs, Tab, DatePicker, PickerGroup, Icon } from "vant";
import { defineProps, defineEmits, computed } from "vue";

const props = defineProps<{
  value: Boolean;
  mode: String;
}>();
const emits = defineEmits(["update:value", "submit"]);

const show = computed({
  get: () => props.value,
  set: (val) => {
    emits("update:value", val);
  },
});
const active = ref(props.mode);

const now = new Date();
const thisYear = now.getFullYear();
const thisMonth = now.getMonth() + 1;
const thisDay = now.getDate();
const currentDate = ref([thisYear, thisMonth]);
const minDate = new Date(thisYear - 2, thisMonth - 2, 1);
const maxDate = new Date(thisYear, thisMonth - 1, thisDay);
const columnsType = ["year", "month"];

const activeTab = ref(0);
const startDate = ref([thisYear - 2, thisMonth - 2, "01"]);
const endDate = ref([thisYear, thisMonth, thisDay]);

const submit = () => {
  emits(
    "submit",
    active.value,
    active.value === "1"
      ? [currentDate.value]
      : [startDate.value, endDate.value]
  );
  show.value = false;
};
</script>

<style scoped>
.btn-group {
  background-color: #f1f1f1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 48px;
}
.btn-item {
  padding: 0 16px;
}
.comfirm-btn {
  color: #faa72f;
}
.popup-tip {
  background-color: #fdf8e6;
  color: #666;
  padding: 4px 10px;
}
.popup-tabs {
  width: 150px;
  margin: 20px auto;
}
/deep/ .van-tab--active {
  font-weight: normal;
}
/deep/ .van-tabs__line {
  background: #faa72f;
}
.ym-picker /deep/ .van-picker__columns {
  justify-content: center;
}
.ym-picker /deep/ .van-picker-column {
  width: 100px;
  flex: unset;
}
.range-picker /deep/ .van-picker__toolbar {
  justify-content: center;
}
.range-picker /deep/ .van-picker__toolbar .van-icon-minus {
  color: #ccc;
}
.range-picker .date-item {
  width: 160px;
  margin: 0 10px;
  border: 1px solid #ccc;
  padding: 10px 14px;
  border-radius: 40px;
  position: relative;
}
.range-picker .date-item.active {
  border-color: #faa72f;
}
.range-picker .date-item /deep/ .van-icon-play {
  position: absolute;
  right: 14px;
  transform: rotate(90deg) rotateY(45deg);
}
.range-picker /deep/ .van-tabs__wrap {
  display: none;
}
</style>
