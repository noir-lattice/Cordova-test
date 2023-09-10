<template>
  <Popup
    v-model:show="show"
    position="bottom"
    teleport="#app"
    :style="{ height: '40%' }"
  >
    <div class="btn-group">
      <div class="btn-item cancel-btn">取消</div>
      <div class="btn-item comfirm-btn">确定</div>
    </div>
    <div v-if="$slots.tip" class="popup-tip">
      <slot name="tip"></slot>
    </div>
    <Tabs v-model:active="active" class="popup-tabs" line-width="50px">
      <Tab title="月份选择" name="1"></Tab>
      <Tab title="自定义" name="2"></Tab>
    </Tabs>
  </Popup>
</template>

<script setup lang="ts">
import { Popup, Tabs, Tab } from "vant";
import { defineProps, defineEmits, computed } from "vue";

const props = defineProps<{
  value: Boolean;
  mode: String;
}>();
const emits = defineEmits();

const show = computed({
  get: () => props.value,
  set: (val) => {
    emits("update:value", val);
  },
});
const active = computed({
  get: () => props.mode,
  set: (val) => {
    emits("update:mode", val);
  },
});
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
  margin: 20px auto 10px;
}
/deep/ .van-tabs__line {
  background: #faa72f;
}
</style>
