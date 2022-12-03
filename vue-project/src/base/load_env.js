const axios = require('axios');
const fs = require('fs');
const path = require('path');
const child_process = require('child_process');

const appDirectory = fs.realpathSync(process.cwd());
const resolveApp = relativePath => path.resolve(appDirectory, relativePath);

const args = process.argv.splice(2)

const lightHouse = 'lighthouse'
const pre = 'pre'
const prod = 'prod'

const configId = args[0] === pre ? '625e84865643296a224fb672@admin_url' : args[0] === prod ? '' : ''

function writeFile(data, configFilePath) {
    let lessVar = [],
        sassVar = [];
    for (let i in data) {
        if (/^@/.test(i)) {
            data[i] && lessVar.push(`${i}: ${data[i] || ""};`);
        }
        else if (/^$/.test(i)) {
            data[i] && sassVar.push(`${i}: ${data[i] || ""};`);
        }
    }
    // if (lessVar.length > 0) {
    //   fs.writeFileSync(
    //     resolveApp('src/variable.less'),
    //     lessVar.join('\n')
    //   );
    // }
    // if (sassVar.length > 0) {
    //   fs.writeFileSync(
    //     resolveApp('src/variable.scss'),
    //     sassVar.join('\n')
    //   );
    // }


    fs.writeFileSync(
        resolveApp(configFilePath || 'env/configCenter.json'),
        JSON.stringify(data, null, 2)
    );

    const envPropText = Object.entries(data).map(([key, value]) => `FM_${key}=${JSON.stringify(value)}`).join('\n');
    fs.writeFileSync(
        resolveApp(configFilePath || 'env/configCenter.json'),
        JSON.stringify(data, null, 2)
    );

    if (args[0] === prod) {
        fs.writeFileSync(
            resolveApp(configFilePath || 'env/.env.production'),
            `NODE_ENV=production\n${envPropText}`
        );
    } else if (args[0] === pre) {
        fs.writeFileSync(
            resolveApp(configFilePath || 'env/.env.pre'),
            `NODE_ENV=production\n${envPropText}`
        );
    } else {
        console.log('未知环境参数')
    }
}

function getConfig(props) {
    const {
        configId,
        configDomain = 'http://xbird.lizhi.fm',
        configFilePath = '',
        callback
    } = props;
    const { XBIRD_CONFIG_ID: configIdFromEnv } = process.env;

    if (!configId && !configIdFromEnv) {
        throw new Error('XBirdConfigWebpackPlugin: 请传入configId！');
    }

    const realId = configIdFromEnv ? configIdFromEnv : configId ? configId.split('@').shift() : ''
    const _this = {}
    _this.configId = realId;

    _this.configDomain = configDomain;
    _this.configFilePath = configFilePath;
    fetchConfig.call(_this, callback)
}


function fetchConfig(callback) {
    axios.get(
        `${this.configDomain}/api/v1/runtime/config/${this.configId}?flat=yes`
    ).then(res => res.data).then((res) => {
        if (res.rCode === 0) {
            if (!res.data) {
                console.log(`请注意，在配置中心中找不到配置`);
                callback && callback();
            } else {
                console.log(`配置中心的配置获取成功`);
                setTimeout(() => {
                    writeFile(res.data, this.configFilePath);
                    callback && callback();
                }, 0);
            }
        } else {
            console.log(`请注意，请求配置配置中心时返回错误信息：${res.msg}`);
            callback && callback();
        }
    }).catch((e) => {
        console.log(`请注意，请求配置中心出错`);
        console.error(e);
        callback && callback();
    });
};

getConfig({
    configId,
    callback: () => {
        console.log('启动 Vite 构建 💡')
        const buildProcess = child_process.spawnSync('vite', ['build'], {
            cwd: process.cwd(),
            env: process.env,
            stdio: [process.stdin, process.stdout, process.stderr],
            encoding: 'utf-8'
        })
        console.log(buildProcess.output)
    }
});