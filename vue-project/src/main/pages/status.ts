import { ref } from "vue";
export const isLogin = ref(false);
export const currentPage = ref<
"main" | "detail" | "bill" | "card" | "mine" | "welcom" | "home" | "login"
>("welcom");