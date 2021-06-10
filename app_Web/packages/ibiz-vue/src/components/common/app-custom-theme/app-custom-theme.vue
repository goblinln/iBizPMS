<template>
    <div class="app-custom-theme" @click="showDrawer = true">
        <span ><icon class='app-theme-icon' :title="$t('components.apptheme.config')" type='md-settings' :size="15" /></span>
        <span class="title">
            {{$t('components.apptheme.config')}}
        </span>
        <el-drawer
            append-to-body
            :title="$t('components.apptheme.customtheme')"
            :show-close="true"
            :visible.sync="showDrawer"
            :with-header="false"
            custom-class="app-custom-theme-drawaer"
            @open="drawerOpen">
            <div class="theme-color">
                <h3>{{$t('components.apptheme.color')}}</h3>
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
                                        {{$t('components.apptheme.caption.font')}}
                                        <el-select v-model="selectFont" size="small">
                                            <el-option v-for="font in fontsFamily" :key="font.value" :label="$t(`components.apptheme.fontfamilys.${font.label}`)" :value="font.value">
                                                {{$t(`components.apptheme.fontfamilys.${font.label}`)}}
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
            <el-button type="primary" size="small" @click="previewTheme">{{$t('components.apptheme.preview')}}</el-button>
            <el-button type="primary" size="small" @click="saveThemeOptions">{{$t('components.apptheme.save')}}</el-button>
            <el-button type="primary" size="small" @click="reset">{{$t('components.apptheme.reset')}}</el-button>
            <el-button type="primary" size="small" @click="share">{{$t('components.apptheme.share')}}</el-button>
        </el-drawer>
    </div>
</template>

<script lang="ts">
import { Vue, Component, Prop } from 'vue-property-decorator';
import { appConfig } from '@/config/appConfig';
import { themeConfig } from '@/config/themeConfig';
import { AppServiceBase, textCopy, Util } from 'ibiz-core';
import qs from 'qs';

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
     * 系统-应用标识
     * 
     * @type {string}
     * @memberof AppCustomTheme
     */
    public AppTag: any;

    /**
     * 主题配置标识
     * 
     * @type {string}
     * @memberof AppCustomTheme
     */
    public themeOptionId: string = '';

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
        this.AppTag = `${this.Environment.SysName}-${this.Environment.AppName}`;
        this.themeTypes = themeConfig.types;
        if (this.themeTypes.length > 0) {
            this.activeSetting = this.themeTypes[0].value;
        }
        if (this.isShare()) {
            this.getShareThemeOptions(this.themeOptionId).then((options: any) => {
                if (options) {
                    this.initThemeOptions(options);
                    this.saveThemeOptions(true);
                } else {
                    this.getUserThemeOption().then((options: any) => {
                        this.initThemeOptions(options);
                    });
                }
            });
        } else {
            this.getUserThemeOption().then((options: any) => {
                this.initThemeOptions(options);
            });
        }
    }

    /**
     * 获取用户主题配置
     * 
     * @memberof AppCustomTheme
     */
    public async getUserThemeOption() {
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
     * 获取分享主题配置
     * 
     * @memberof AppCustomTheme
     */
    public async getShareThemeOptions(themeOptionId: any) {
        try {
            const res = await this.$http.get(`/configs/share/${themeOptionId}`);
            if (res.status == 200 && res.data && res.data.model) {
                return res.data.model;
            } else {
                return null;
            }
        } catch (error: any) {
            this.$throw(this.$t('components.apptheme.error.getshareurl'));
            return null;
        }
    }

    /**
     * 初始化主题配置
     * 
     * @memberof AppCustomTheme
     */
    public initThemeOptions(options: any) {
        this.selectTheme = this.getSelectTheme();
        this.initFontFamily(options);
        const themeOptions = options && options.cssValue ? options.cssValue : localStorage.getItem(`${this.AppTag}-theme-options`);
        if (themeOptions !== null && themeOptions !== undefined && themeOptions !== '') {
            this.themeOptions = JSON.parse(themeOptions);
            this.previewTheme();
        } else {
            this.handleThemeOptions(this.selectTheme).then(() => {
                this.previewTheme();
            });
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
        localStorage.setItem(`${this.AppTag}-theme-options`, JSON.stringify(this.themeOptions));
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
        const themeOptions = localStorage.getItem(`${this.AppTag}-theme-options`);
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
    public saveThemeOptions(isShare: boolean = false) {
        if (this.themeOptions) {
            this.$http.put(`/configs/${this.AppTag}/theme-setting`, 
                { model: { cssValue: JSON.stringify(this.themeOptions), fontFamily: this.selectFont } }).then((res: any) => {
                if (res) {
                    const _this: any = this;
                    _this.$success(isShare ? this.$t('components.apptheme.applytheme') : this.$t('components.apptheme.success.savethemeoption'));
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
                this.$http.put(`/configs/${this.AppTag}/theme-setting`, { }).then((res: any) => {
                    this.previewTheme();
                });
            })
        }
    }

    /**
     * 分享主题
     * 
     * @memberof AppCustomTheme
     */
    public share() {
        this.saveThemeOptions();
        try {
            this.$http.get(`/configs/share/${this.AppTag}/theme-setting`).then((res: any) => {
                if (res.status == 200 && res.data) {
                    const shareUrl = this.generateShareUrl(res.data);
                    const _this: any = this;
                    const h = this.$createElement('el-input', {
                        props: {
                            value: shareUrl,
                            disabled: true,
                            size: 'small'
                        }
                    })
                    _this.$alert(h, this.$t('components.apptheme.createurl'), {
                        confirmButtonText: this.$t('components.apptheme.configbutton'),
                        customClass: 'share-theme-box',
                        callback: (action: any) => {
                            _this.copyShareUrl(action, shareUrl);
                        }
                    })
                } else {
                    this.$throw(this.$t('components.apptheme.error.generateshareurl'));
                }
            })
        } catch(error: any) {
            this.$throw(this.$t('components.apptheme.error.generateshareurl'));
        }
    }

    /**
     * 生成分享链接
     * 
     * @memberof AppCustomTheme
     */
    public generateShareUrl(themeOptionId: any) {
        const href: string = window.location.href;
        const userName = this.$store.getters.getAppData().context?.srfusername;
        console.log(userName);
        const baseStr = window.btoa(`applyThemeOption=true&themeOptionId=${themeOptionId}`);
        const param = `#/appsharepage?theme=${baseStr}&shareUserName=${encodeURIComponent(userName)}`;
        return href.replace(/#\/\S*/, param);
    }

    /**
     * 拷贝分享链接
     * 
     * @memberof AppCustomTheme
     */
    public copyShareUrl(action: string, shareUrl: any) {
        if (action == 'cancel') {
            return;
        }
        textCopy.copy(shareUrl);
        this.$success(this.$t('components.apptheme.success.copyurl'), 'saveShareThemeUrlSuccess');
    }

    /**
     * 处理链接参数
     * 
     * @memberof AppCustomTheme
     */
    public parseViewParam(urlStr: string): any {
        let tempViewParam: any = {};
        const tempViewparam: any = urlStr.slice(urlStr.lastIndexOf('?') + 1);
        const viewparamArray: Array<string> = decodeURIComponent(tempViewparam).split(';');
        if (viewparamArray.length > 0) {
            viewparamArray.forEach((item: any) => {
                Object.assign(tempViewParam, qs.parse(item));
            });
        }
        return tempViewParam;
    }

    /**
     * 是否是分享链接打开
     * 
     * @memberof AppCustomTheme
     */
    public isShare(): boolean {
        const urlParams = this.parseViewParam(window.location.href);
        if (Object.keys(urlParams).length == 0) {
            return false;
        }
        if (urlParams.hasOwnProperty('theme') && urlParams['theme']) {
            try {
                const tempParam: any = this.parseViewParam(window.atob(urlParams['theme']));
                if (tempParam.hasOwnProperty('applyThemeOption')
                    && tempParam['applyThemeOption'] == 'true'
                    && tempParam.hasOwnProperty('themeOptionId')
                    && tempParam['themeOptionId'] != ''
                    && tempParam['themeOptionId'] != null) {
                        this.themeOptionId = tempParam['themeOptionId'];
                        return true;
                }
            } catch (error: any) {
                return false;
            }
        }
        return false;
    }

    /**
     * vue生命周期 -- destroyed
     * 
     * @memberof AppCustomTheme
     */
    public destroyed() {
        localStorage.removeItem(`${this.AppTag}-theme-options`);
    }

}

</script>
<style lang="less">
@import './app-custom-theme.less';
</style>