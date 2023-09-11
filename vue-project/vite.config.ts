import { fileURLToPath, URL } from 'url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'
import AutoImport from "unplugin-auto-import/vite";
const pkg = require('./package.json')
const { resolve } = require('path')
const path = require('path')

// https://vitejs.dev/config/
export default defineConfig({
  base: '/',
  plugins: [vue(), vueJsx(), AutoImport({
    imports: [
      'vue',
      'pinia',
      'vue-router',
      {
        '@/hooks/global/useCommon': ['useCommon'],
        '@/hooks/global/useVueRouter': ['useVueRouter'],
        '@/utils/axiosReq': ['axiosReq']
      }
    ],
    eslintrc: {
      enabled: true, // Default `false`
      filepath: './.eslintrc-auto-import.json', // Default `./.eslintrc-auto-import.json`
      globalsPropValue: true // Default `true`, (true | false | 'readonly' | 'readable' | 'writable' | 'writeable')
    },
    dts: true //auto generation auto-imports.d.ts file
  })],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  envDir: 'env',
  envPrefix: ['FM_', 'VITE_'],
  build: {
    outDir: path.join(__dirname, '..', 'www'),
    assetsDir: '',
    rollupOptions: {
      input: {
        index: resolve(__dirname, 'index.html'),
      }
    }
  },
  server: {
    proxy: {
      '/account': {
        target: 'http://localhost:8080/',
        changeOrigin: true,
      }
    }
  }
})
