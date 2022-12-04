## 项目结构:
整体分为两个部分：
 * cordova 作为跨平台基础框架，负责:
   * 读取配置文件
   * 获取android权限
   * 读取激活状态
   * 保存激活日志文件
 * 前端项目负责:
    * 页面渲染交互
    * 定时拉取对应配置
    * 对于未生效的状态切换至未激活


具体目录结构:
```
.
|-- config.xml
|-- key-pair.text
|-- platforms
|-- plugins
|-- utils
|   `-- KeyGen.java
|-- vue-project
`-- www
```
* config.xml: 平台配置文件，管理插件及应用基础信息（名称、图标等等）
* key-pair.text: 这里放用于签名的公私密钥对
* platforms: 临时文件目录，不需要提交
* plugins: cordova插件目录，需要提交，目前自定义的平台逻辑在这里
* utils/KeyGen.java: 生成激活码的工具
* vue-project: vue渲染前端项目
* www: vue-project打包生成的结果目录

## Quick start
分为两个部分开始，先从vue项目为入口:

### vue-project
从该目录打开项目当作前端项目开发即可
```
npm i
npm run dev 
npm run build
```
`前端调试时，可以使用src/bridge/mock.json模拟数据`

项目结构说明
```
.
|-- index.html
|-- src
|   |-- bridge
|   |   |-- index.ts
|   |   |-- interface.ts
|   |   `-- mock.json
|   `-- main
|       |-- App.vue
|       |-- interface.ts
|       `-- main.ts
|-- tsconfig.json
|-- tsconfig.vite-config.json
`-- vite.config.ts
```
* index.html: 入口html(不要删除cordova.js的引用，忽略就好)
* src/bridge: 与平台api的交互逻辑
  * index.ts: 接口入口
  * mock.json: 进行数据模拟配置, 在dev时启用
* main: 主逻辑都扔在这里了，需要具体分页面就再说


### android project
1. 添加android平台
    ```
    cordova platform add android
    ```
2. 启动调试
    ```
    cordova run android
    ```
3. 打包
    ```
    cordova run build
    ```
4. 开发注意  

     * 不要改platforms下的代码，那里是生成目录
     * 主要逻辑在plugin下实现，进行修改就好