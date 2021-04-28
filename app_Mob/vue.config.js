/**
 * vue配置文件
 * 
 * @vue.config.js
 */
const path = require('path');
const os = require('os');
const Timestamp = new Date().getTime();
function resolve(dir) {
    return path.join(__dirname, dir);
}

module.exports = {
    publicPath: './',
    // 去除 map 文件 1
    productionSourceMap: false,
    outputDir: process.env.VUE_APP_OUTPUTDIR,
    devServer: {
        host: '0.0.0.0',
        port: 8111,
        compress: true,
        disableHostCheck: true,
        // proxy: "http://127.0.0.1:8080/Mob/",
        historyApiFallback: {
            rewrites: [
            ]
        }
    },
    pages: {
        appindexview: {
            // page 的入口
            entry: 'src/main.ts',
            // 模板来源
            template: 'src/template.html',
            // 在 dist/index.html 的输出
            filename: 'index.html',
            // 当使用 title 选项时，
            // template 中的 title 标签需要是 <title><%= htmlWebpackPlugin.options.title %></title>
            title: 'iBiz软件生产管理',
            // 在这个页面中包含的块，默认情况下会包含
            // 提取出来的通用 chunk 和 vendor chunk。
            // chunks: ['chunk-vendors', 'chunk-common', 'index']
        },
    },
    // 多核打包
    parallel: os.cpus().length > 1,
    chainWebpack: (config) => {
        // 删除自动计算预加载资源
        config.plugins.delete('preload-appindexview');
        // 删除预加载资源
        config.plugins.delete('prefetch-appindexview');
        config.resolve.alias
            .set('@pages', resolve('src/pages'))
            .set('@components', resolve('src/components'))
            .set('@widgets', resolve('src/widgets'))
            .set('@engine', resolve('src/engine'))
            .set('@interface', resolve('src/interface'))
            .set('@locale', resolve('src/locale'))
            .set('@mock', resolve('src/mock'))
            .set('@codelist', resolve('src/codelist'))
            .set('@app-core', resolve('src/app-core'))
            .set('@ibiz-core', resolve('src/ibiz-core'))
            .set('@ui-service', resolve('src/ui-service'))
            .set('@global-ui-service', resolve('src/global-ui-service'));
    },
    configureWebpack: config => {
        let ForkTsCheckerPlugin; 
        Object.assign(config, {
            output: {
                ...config.output,
                filename: `js/[name].${Timestamp}.js`,
                chunkFilename: `js/[name].${Timestamp}.js`
            },
        });
        if (config.plugins.length > 0) {
            ForkTsCheckerPlugin = config.plugins.find(element =>{
                return  element.workersNumber && element.memoryLimit;
            })
        }
        if (Object.is(config.mode, 'production')) {
            // 最大进程数
            ForkTsCheckerPlugin.workersNumber = os.cpus().length > 4 ? 4 : os.cpus().length; // 会占用额外内存不释放，不建议开发阶段使用
            // 单个进程最大使用内存
            ForkTsCheckerPlugin.memoryLimit = 4096;
        } else {
            // 最大进程数
            // ForkTsCheckerPlugin.workersNumber = os.cpus().length > 4 ? 4 : os.cpus().length; // 会占用额外内存不释放，不建议开发阶段使用
            // 单个进程最大使用内存
            ForkTsCheckerPlugin.memoryLimit = 4096;
        }
    },
    pluginOptions: {
        'style-resources-loader': {
            preProcessor: 'less', 
            patterns: [path.resolve(__dirname, 'src/styles/var.less')]
        }
    },
};