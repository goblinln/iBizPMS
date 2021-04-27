<template>
    <div class="app-custom-theme">
        <icon class='app-theme-icon' title="主题配置" @click="showDrawer = true" type='md-settings' :size="22" />
        <el-drawer
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
            <div class="font-family">
                <h3>字体</h3>
                <el-select v-model="selectFont" size="small" @change="fontChange">
                    <el-option v-for="font in fontsFamily" :key="font.value" :label="$t(`components.appTheme.fontFamilys.${font.label}`)" :value="font.value">
                        {{$t(`components.appTheme.fontFamilys.${font.label}`)}}
                    </el-option>
                </el-select>
            </div>
            <div class="split"></div>
            <div class="theme-setting">
                <el-tabs v-model="activeSetting">
                    <template v-for="type in themeTypes">
                        <el-tab-pane :key="type.value" :label="type.label" :name="type.value" :class="type.className ? type.className : ''">
                            <div v-if="type.items && type.items.length > 0" :class="{ 'setting': true, [`${type.value}-setting`]: true }">
                                <div v-for="(item, index) in type.items" :key="index" class="setting-item">
                                    <span>{{ item.label }}</span>
                                    <el-color-picker :show-alpha="item.showAlpha" size="small" v-model="themeOptions[item.cssName]"/>
                                </div>
                            </div>
                        </el-tab-pane>
                    </template>
                </el-tabs>
            </div>
            <div class="split"></div>
            <el-button type="primary" size="small" @click="previewTheme">预览</el-button>
            <el-button type="primary" size="small" @click="saveThemeOptions">保存配置</el-button>
        </el-drawer>
        
    </div>
</template>

<script lang="ts">
import { Vue, Component, Prop } from 'vue-property-decorator';
import { appConfig } from '@/config/appConfig';
import { themeConfig } from '@/config/themeConfig';

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
     * vue生命周期 -- created
     * 
     * @memberof AppCustomTheme
     */
    public created() {
        this.themeTypes = themeConfig.types;
        if (this.themeTypes.length > 0) {
            this.activeSetting = this.themeTypes[0].value;
        }
        this.initThemeOptions().then((options: any) => {
            this.selectTheme = this.getSelectTheme();
            const themeOptions = options ? options : localStorage.getItem('theme-options');
            if (themeOptions) {
                this.themeOptions = JSON.parse(themeOptions);
            } else {
                this.handleThemeOptions(this.selectTheme);
            }
            this.previewTheme();
        });
        this.initFontFamily();
    }

    /**
     * 初始化主题配置
     * 
     * @memberof AppCustomTheme
     */
    public async initThemeOptions() {
        try {
            const response = await this.$http.get('configs/custom/theme');
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
    public initFontFamily() {
        this.selectFont = localStorage.getItem('font-family') ? localStorage.getItem('font-family') : appConfig.defaultFont;
    }

    /**
     * 获取选中主题
     * 
     * @memberof AppCustomTheme
     */
    public getSelectTheme() {
        const _this: any = this;
        if (_this.$router.app.$store.state.selectTheme) {
            return _this.$router.app.$store.state.selectTheme;
        } else if (localStorage.getItem('theme-class')) {
            return localStorage.getItem('theme-class');
        } else {
            return this.viewStyle == 'STYLE2' ? 'app-theme-studio-dark' : 'app-theme-default';
        }
    }

    /**
     * 处理主题配置
     * 
     * @memberof AppCustomTheme
     */
    public handleThemeOptions(tag: any) {
        if (!tag || !tag.slice(10)) {
            return;
        }
        try {
            this.$http.get(`./assets/theme/${tag.slice(10)}.theme.less`).then((response: any) => {
                if ((response.status && response.status != 200) || !response.data) {
                    return;
                }
                const items: Array<any> = response.data.toString().match(/--[app|view|ctrl|item|menu|form]+[a-zA-Z-]+: ?[#()a-zA-Z0-9,. ]+/g) || [];
                items.forEach((item: any, index: number) => {
                    const splitVar = item.split(':');
                    if (splitVar && splitVar.length == 2) {
                        Object.assign(this.themeOptions, { [splitVar[0]]: splitVar[1].trim() });
                    }
                })
                this.$forceUpdate();
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
     * 切换字体
     * 
     * @memberof AppCustomTheme
     */
    public fontChange(val: any) {
        if (!Object.is(this.selectFont, val)) {
            const _this: any = this;
            _this.selectFont = val;
            localStorage.setItem('font-family', val);
            _this.$router.app.$store.commit('setCurrentSelectFont', val);
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
            _this.$router.app.$store.commit('setCurrentSelectTheme', this.selectTheme);
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
            this.$http.put(`/configs/custom/theme`, { model: JSON.stringify(this.themeOptions) }).then((res: any) => {
                if (res) {
                    const _this: any = this;
                    _this.$notify({
                        title: '保存成功',
                        message: '保存自定义主题成功',
                        type: 'success'
                    });
                    this.previewTheme();
                }
            });
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