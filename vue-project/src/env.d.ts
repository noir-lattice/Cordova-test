/// <reference types="vite/client" />

interface ImportMetaEnv {
    readonly FM_potal: string
    // 更多环境变量...
}

interface ImportMeta {
    readonly env: ImportMetaEnv
}