import { createApp } from "vue";
import "vant/lib/index.css";
import App from "./App.vue";
import axios from "axios";

let app = createApp(App);
app.config.globalProperties.$http = axios;
app.mount("#app");
