/**
 * 与cordova的通讯逻辑就放这里
 */
// Wait for the deviceready event before using any of Cordova's device APIs.

import type { ConfigResp } from "./interface";
export type { ConfigResp } from "./interface";
import { ElMessage } from "element-plus";

// See https://cordova.apache.org/docs/en/latest/cordova/events/events.html#deviceready
let handler;
let ready = import.meta.env.DEV;
const processingHolder: (() => void)[] = [];
function onDeviceReady() {
    ready = true;
    handler = (window as any).cordova;
    for (const processor of processingHolder) {
        processor()
    }
}
document.addEventListener('deviceready', onDeviceReady, false);


function submit(fn: () => void) {
    if (ready) {
        fn();
        return;
    }

    processingHolder.push(fn);
}

export function loadLocalConfig<T>(): Promise<ConfigResp<T>> {
    return new Promise((resolve, reject) => {
        if (import.meta.env.DEV) {
            import('./mock.json').then(data => {
                console.log('ret mock config =', data.default)
                resolve(data.default as any);
                ElMessage.warning("正在使用模拟数据")
            }).catch(reject);
            return;
        }
        submit(() => {
            const successCallback = (message: string) => {
                try {
                    resolve(JSON.parse(message));
                } catch (e) {
                    reject("e=" + e)
                }
            };
            const failCallback = (message: string) => {
                reject(message)
            };

            handler.exec(successCallback, failCallback, "ConfigCenter", "get", []);
        });
    });
}

export function register(code: string): Promise<boolean> {
    return new Promise((resolve, reject) => {
        submit(() => {
            const successCallback = (message: string) => {
                resolve(JSON.parse(message));
            };
            const failCallback = (message: string) => {
                reject(message)
            };
            handler.exec(successCallback, failCallback, "ConfigCenter", "register", [code]);
        });
    });
}

export function check(): Promise<void> {
    return new Promise((resolve, reject) => {
        if (import.meta.env.DEV) {
            resolve();
            return;
        }
        submit(() => {
            const successCallback = () => {
                resolve();
            };
            const failCallback = (message: string) => {
                reject(message)
            };
            handler.exec(successCallback, failCallback, "ConfigCenter", "check", []);
        });
    });
}
