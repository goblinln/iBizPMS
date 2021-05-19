<template>
    <div class="app-custom-theme" @click="showDrawer = true">
        <span ><icon class='app-theme-icon' title="主题配置" type='md-settings' :size="15" /></span>
        <span class="title">
            主题配置
        </span>
        <el-drawer
            append-to-body
            title="自定义主题"
            :show-close="true"
            :visible.sync="showDrawer"
            :with-header="false"
            custom-class="app-custom-theme-drawaer"
            @open="drawerOpen">
            <div class="theme-color">
                <h3>主题色</h3>
                <span 
                    v-for="(theme, index) in defaultThemes" 
                    :key="index" 
                    :class="{ 
                        'theme-tag': true,
                        [theme.tag]: true,
                        'is-select': selectTheme && selectTheme == theme.tag ? true : false
                    }"
                    :title="theme.title"
                    :style="{ 'background-color': theme.color }"
                    @click="themeChange(theme.tag)"/>
            </div>
            <div class="split"></div>
            <div class="theme-setting">
                <el-tabs v-model="activeSetting">
                    <template v-for="type in themeTypes">
                        <el-tab-pane v-if="!type.disable" :key="type.value" :label="type.label" :name="type.value" :class="type.className ? type.className : ''">
                            <div v-if="type.items && type.items.length > 0" :class="{ 'setting': true, [`${type.value}-setting`]: true }">
                                <template v-if="type.value == 'app'">
                                    <div class="font-family setting-item">
                                        字体
                                        <el-select v-model="selectFont" size="small">
                                            <el-option v-for="font in fontsFamily" :key="font.value" :label="$t(`components.appTheme.fontFamilys.${font.label}`)" :value="font.value">
                                                {{$t(`components.appTheme.fontFamilys.${font.label}`)}}
                                            </el-option>
                                        </el-select>
                                    </div>
                                </template>
                                <template v-for="(item, index) in type.items">
                                    <div v-if="!item.disable" :key="index" class="setting-item">
                                        <span>{{ item.label }}</span>
                                        <el-color-picker :show-alpha="item.showAlpha" size="small" v-model="themeOptions[item.cssName]"/>
                                    </div>
                                </template>
                            </div>
                        </el-tab-pane>
                    </template>
                </el-tabs>
            </div>
            <div class="split"></div>
            <el-button type="primary" size="small" @click="previewTheme">预览</el-button>
            <el-button type="primary" size="small" @click="saveThemeOptions">保存配置</el-button>
            <el-button type="primary" size="small" @click="reset">重置</el-button>
        </el-drawer>
        
    </div>
</template>

<script lang="ts">
import { Vue, Component, Prop } from 'vue-property-decorator';
import { appConfig } from '@/config/appConfig';
import { themeConfig } from '@/config/themeConfig';
import { AppServiceBase } from 'ibiz-core';

@Component({})
export default class AppCustomTheme extends Vue {

    /**
     * 视图样式
     * 
     * @type {string}
     * @memberof AppCustomTheme
     */
    @Prop({ default: 'DEFAULT' }) public viewStyle!: string;

    /**
     * 主题css变量配置
     * 
     * @type {string}
     * @memberof AppCustomTheme
     */
    public themeTypes: Array<any> = [];

    /**
     * 默认样式集合
     * 
     * @type {any[]}
     * @memberof AppCustomTheme
     */
    public defaultThemes: any[] = appConfig.themes;

    /**
     * 默认字体集合
     * 
     * @type {any[]}
     * @memberof AppCustomTheme
     */
    public fontsFamily: any[] = appConfig.fonts;

    /**
     * 环境配置
     * 
     * @type {any}
     * @memberof AppCustomTheme
     */
    public Environment: any = AppServiceBase.getInstance().getAppEnvironment();

    /**
     * 当前选中样式
     * 
     * @type {string}
     * @memberof AppCustomTheme
     */
    public selectTheme: string = '';

    /**
     * 当前选中字体
     * 
     * @type {any}
     * @memberof AppCustomTheme
     */
    public selectFont: any = {};

    /**
     * 是否显示抽屉
     * 
     * @type {boolean}
     * @memberof AppCustomTheme
     */
    public showDrawer: boolean = false;

    /**
     * 当前激活分页
     * 
     * @type {string}
     * @memberof AppCustomTheme
     */
    public activeSetting: string = '';

    /**
     * 主题配置
     * 
     * @type {any}
     * @memberof AppCustomTheme
     */
    public themeOptions: any = {};

    /**
     * 状态管理对象
     * 
     * @type {any}
     * @memberof AppCustomTheme
     */
    public store: any;

    /**
     * vue生命周期 -- created
     * 
     * @memberof AppCustomTheme
     */
    public created() {
        this.store = AppServiceBase.getInstance().getAppStore();
        this.themeTypes = themeConfig.types;
        if (this.themeTypes.length > 0) {
            this.activeSetting = this.themeTypes[0].value;
        }
        this.initThemeOptions().then((options: any) => {
            this.selectTheme = this.getSelectTheme();
            this.initFontFamily(options);
            const themeOptions = options && options.cssValue ? options.cssValue : localStorage.getItem('theme-options');
            if (themeOptions !== null && themeOptions !== undefined && themeOptions !== '') {
                this.themeOptions = JSON.parse(themeOptions);
                this.previewTheme();
            } else {
                this.handleThemeOptions(this.selectTheme).then(() => {
                    this.previewTheme();
                });
            }
        });
    }

    /**
     * 初始化主题配置
     * 
     * @memberof AppCustomTheme
     */
    public async initThemeOptions() {
        try {
            const response = await this.$http.get(`configs/${this.Environment.SysName}-${this.Environment.AppName}/theme-setting`);
            if (response.status && response.status == 200) {
                return response.data && response.data.model ? response.data.model : null;
            }
            return null;
        } catch {
            return null;
        }
    }

    /**
     * 初始化默认字体
     * 
     * @memberof AppCustomTheme
     */
    public initFontFamily(options?: any) {
        if (options && options.fontFamily) {
            this.selectFont = options.fontFamily;
        } else {
            this.selectFont = localStorage.getItem('font-family') ? localStorage.getItem('font-family') : appConfig.defaultFont;
        }
    }

    /**
     * 获取选中主题
     * 
     * @memberof AppCustomTheme
     */
    public getSelectTheme() {
        if (this.store && this.store.state && this.store.state.selectTheme) {
            return this.store.state.selectTheme;
        } else if (localStorage.getItem('theme-class')) {
            return localStorage.getItem('theme-class');
        } else {
            return this.viewStyle == 'STYLE2' || this.viewStyle == 'STYLE3' ? 'app-theme-studio-dark' : 'app-theme-default';
        }
    }

    /**
     * 处理主题配置
     * 
     * @memberof AppCustomTheme
     */
    public async handleThemeOptions(tag: any) {
        if (!tag || !tag.slice(10)) {
            return;
        }
        try {
            const response = await this.$http.get(`./assets/theme/${tag.slice(10)}.theme.less`);
            if ((response.status && response.status != 200) || !response.data) {
                return;
            }
            const items: Array<any> = response.data.toString().match(/--[a-zA-Z-]+: ?[#()a-zA-Z0-9,. ]+/g) || [];
            items.forEach((item: any, index: number) => {
                const splitVar = item.split(':');
                if (splitVar && splitVar.length == 2) {
                    Object.assign(this.themeOptions, { [splitVar[0]]: splitVar[1].trim() });
                }
            })
        } catch {

        }
    }
    
    /**
     * 切换主题
     * 
     * @memberof AppCustomTheme
     */
    public themeChange(tag: string) {
        if (tag && this.selectTheme != tag) {
            this.selectTheme = tag;
            this.handleThemeOptions(tag);
        }
    }

    /**
     * 主题应用
     * 
     * @memberof AppCustomTheme
     */
    public previewTheme() {
        if (this.selectTheme) {
            this.setStyle();
            const _this: any = this;
            localStorage.setItem('theme-class', this.selectTheme);
            AppServiceBase.getInstance().getAppStore().set
            if (this.store) {
                this.store.commit('setCurrentSelectTheme', this.selectTheme);
            }
            const dom = document.body.parentElement;
            if (dom) {
                dom.classList.forEach((val: string) => {
                    if (val.indexOf('app-theme') === 0) {
                        dom.classList.remove(val);
                    }
                });
                dom.classList.add(this.selectTheme);
            }
        }
        if (this.selectFont) {
            const _this: any = this;
            localStorage.setItem('font-family', this.selectFont);
            if (this.store) {
                this.store.commit('setCurrentSelectFont', this.selectFont);
            }
        }
        localStorage.setItem('theme-options', JSON.stringify(this.themeOptions));
    }

    /**
     * 设置变量
     * 
     * @memberof AppCustomTheme
     */
    public setStyle() {
        let content = ''
        for (const key of Object.keys(this.themeOptions)) {
            if (key && this.themeOptions[key]) {
                content += key + ': ' + this.themeOptions[key] + ';';
            }
        }
        const cssText = `.${this.selectTheme}:root { ${content} }`;
        let dom: any = null;
        for (let i = document.head.childNodes.length - 1; i >= 0; i--) {
            const children: any = document.head.childNodes[i]
            if (children.nodeName == "STYLE" && children.getAttribute('title') && children.getAttribute('title') == 'custom-theme-css') {
                dom = children;
            }
        }
        if (dom) {
            dom.innerText = cssText;
        } else {
            const styleDom = document.createElement('style');
            styleDom.type = "text/css";
            styleDom.setAttribute('title', 'custom-theme-css');
            styleDom.innerText = cssText;
            document.head.appendChild(styleDom);
        }
    }

    /**
     * 抽屉打开回调
     * 
     * @memberof AppCustomTheme
     */
    public drawerOpen() {
        this.selectTheme = this.getSelectTheme();
        const themeOptions = localStorage.getItem('theme-options');
        if (themeOptions) {
            this.themeOptions = JSON.parse(themeOptions);
        } else {
            this.handleThemeOptions(this.selectTheme);
        }
    }

    /**
     * 保存主题配置
     * 
     * @memberof AppCustomTheme
     */
    public saveThemeOptions() {
        if (this.themeOptions) {
            this.$http.put(`/configs/${this.Environment.SysName}-${this.Environment.AppName}/theme-setting`, 
                { model: { cssValue: JSON.stringify(this.themeOptions), fontFamily: this.selectFont } }).then((res: any) => {
                if (res) {
                    const _this: any = this;
                    _this.$success('保存自定义主题成功','saveThemeOptions');
                    this.previewTheme();
                }
            });
        }
    }

    /**
     * 重置主题配置
     * 
     * @memberof AppCustomTheme
     */
    public reset() {
        if (this.selectTheme) {
            this.handleThemeOptions(this.selectTheme).then(() => {
                this.$http.put(`/configs/${this.Environment.SysName}-${this.Environment.AppName}/theme-setting`, { }).then((res: any) => {
                    this.previewTheme();
                });
            })
        }
    }

    /**
     * vue生命周期 -- destroyed
     * 
     * @memberof AppCustomTheme
     */
    public destroyed() {
        localStorage.removeItem('theme-options');
    }

}

</script>
<style lang="less">
@import './app-custom-theme.less';
</style>