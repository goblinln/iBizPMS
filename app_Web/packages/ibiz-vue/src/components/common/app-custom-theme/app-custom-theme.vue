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
                    <el-tab-pane label="应用" name="app-tab">
                        <div class="setting app-setting">
                            <div class="setting-item bkcolor">
                                应用背景色<el-color-picker show-alpha size="small" v-model="themeOptions['--app-background-color']"/>
                            </div>
                            <div class="setting-item bkcolor-tint">
                                应用背景色（淡色）<el-color-picker show-alpha size="small" v-model="themeOptions['--app-background-color-tint']"/>
                            </div>
                            <div class="setting-item bkcolor-bright">
                                应用背景色（亮色）<el-color-picker show-alpha size="small" v-model="themeOptions['--app-background-color-bright']"/>
                            </div>
                            <div class="setting-item font-color">
                                字体颜色<el-color-picker show-alpha size="small" v-model="themeOptions['--app-font-color']"/>
                            </div>
                            <div class="setting-item font-color-tint">
                                字体颜色（淡色）<el-color-picker show-alpha size="small" v-model="themeOptions['--app-font-color-light']"/>
                            </div>
                            <div class="setting-item font-color-bright">
                                字体颜色（深色）<el-color-picker show-alpha size="small" v-model="themeOptions['--app-font-color-deep']"/>
                            </div>
                            <div class="setting-item font-color-bright">
                                字体激活<el-color-picker show-alpha size="small" v-model="themeOptions['--app-font-color-active']"/>
                            </div>
                            <div class="setting-item scroll-bar">
                                滚动条颜色<el-color-picker show-alpha size="small" v-model="themeOptions['--app-scroll-bar-color']"/>
                            </div>
                            <div class="setting-item dividing">
                                分割线颜色<el-color-picker show-alpha size="small" v-model="themeOptions['--app-dividing-line-color']"/>
                            </div>
                            <div class="setting-item dividing-tint">
                                分割线颜色（淡色）<el-color-picker show-alpha size="small" v-model="themeOptions['--app-dividing-line-color-tint']"/>
                            </div>
                            <div class="setting-item dividing-bright">
                                分割线颜色（亮色）<el-color-picker show-alpha size="small" v-model="themeOptions['--app-dividing-line-color-bright']"/>
                            </div>
                            <div class="setting-item app-scrollbar">
                                应用滚动条<el-color-picker show-alpha size="small" v-model="themeOptions['--app-scrollbar-thumb-background-color']"/>
                            </div>
                            <div class="setting-item theme-icon">
                                主题选择<el-color-picker show-alpha size="small" v-model="themeOptions['--app-theme-icon-color']"/>
                            </div>
                            <div class="setting-item header-bkcolor">
                                头部背景<el-color-picker show-alpha size="small" v-model="themeOptions['--app-header-background-color']"/>
                            </div>
                            <div class="setting-item header-bkcolor-active">
                                头部激活<el-color-picker show-alpha size="small" v-model="themeOptions['--app-header-background-color-active']"/>
                            </div>
                            <div class="setting-item header-font-color">
                                头部字体<el-color-picker show-alpha size="small" v-model="themeOptions['--app-header-color']"/>
                            </div>
                            <div class="setting-item header-font-color-bright">
                                头部字体（亮色）<el-color-picker show-alpha size="small" v-model="themeOptions['--app-header-color-bright']"/>
                            </div>
                            <div class="setting-item header-font-color-active">
                                头部字体激活<el-color-picker show-alpha size="small" v-model="themeOptions['--app-header-color-active']"/>
                            </div>
                            <div class="setting-item app-shadow">
                                应用阴影<el-color-picker show-alpha size="small" v-model="themeOptions['--app-shadow-color']"/>
                            </div>
                            <div class="setting-item app-loading">
                                应用加载<el-color-picker show-alpha size="small" v-model="themeOptions['--app-loading-background-color']"/>
                            </div>
                            <div class="setting-item font-color">
                                应用加载（亮色）<el-color-picker show-alpha size="small" v-model="themeOptions['--app-loading-background-color-bright']"/>
                            </div>
                            <div class="setting-item font-color">
                                应用加载蒙层<el-color-picker show-alpha size="small" v-model="themeOptions['--app-loading-masking-background-color']"/>
                            </div>
                        </div>
                    </el-tab-pane>
                    <el-tab-pane label="视图" name="view-tab">
                        <div class="setting view-setting">
                            <div class="setting-item bkcolor">
                                背景色<el-color-picker show-alpha size="small" v-model="themeOptions['--view-background-color']"/>
                            </div>
                            <div class="setting-item bkcolor-tint">
                                背景色（淡色）<el-color-picker show-alpha size="small" v-model="themeOptions['--view-background-color-tint']"/>
                            </div>
                            <div class="setting-item bkcolor-bright">
                                背景色（亮色）<el-color-picker show-alpha size="small" v-model="themeOptions['--view-background-color-bright-bright']"/>
                            </div>
                            <div class="setting-item bkcolor-active">
                                背景激活<el-color-picker show-alpha size="small" v-model="themeOptions['--view-background-color-active']"/>
                            </div>
                            <div class="setting-item ">
                                背景激活rgb<el-color-picker show-alpha size="small" v-model="themeOptions['--view-background-color-active-rgb']"/>
                            </div>
                            <div class="setting-item ">
                                背景悬浮<el-color-picker show-alpha size="small" v-model="themeOptions['--view-background-color-hover']"/>
                            </div>
                            <div class="setting-item ">
                                头部边框<el-color-picker show-alpha size="small" v-model="themeOptions['--view-header-border-color']"/>
                            </div>
                            <div class="setting-item ">
                                边框<el-color-picker show-alpha size="small" v-model="themeOptions['--view-border-color']"/>
                            </div>
                            <div class="setting-item ">
                                字体颜色<el-color-picker show-alpha size="small" v-model="themeOptions['--view-font-color']"/>
                            </div>
                            <div class="setting-item ">
                                字体颜色（淡色）<el-color-picker show-alpha size="small" v-model="themeOptions['--view-font-color-tint']"/>
                            </div>
                            <div class="setting-item ">
                                字体颜色（亮色）<el-color-picker show-alpha size="small" v-model="themeOptions['--view-font-color-bright']"/>
                            </div>
                            <div class="setting-item ">
                                字体激活<el-color-picker show-alpha size="small" v-model="themeOptions['--view-font-color-active']"/>
                            </div>
                            <div class="setting-item ">
                                字体悬浮<el-color-picker show-alpha size="small" v-model="themeOptions['--view-font-color-hover']"/>
                            </div>
                            <div class="setting-item ">
                                阴影<el-color-picker show-alpha size="small" v-model="themeOptions['--view-shadow-color']"/>
                            </div>
                            <div class="setting-item ">
                                按钮背景<el-color-picker show-alpha size="small" v-model="themeOptions['--view-button-background-color']"/>
                            </div>
                            <div class="setting-item ">
                                按钮激活<el-color-picker show-alpha size="small" v-model="themeOptions['--view-button-background-color-active']"/>
                            </div>
                            <div class="setting-item ">
                                按钮字体<el-color-picker show-alpha size="small" v-model="themeOptions['--view-button-color']"/>
                            </div>
                            <div class="setting-item ">
                                按钮激活字体<el-color-picker show-alpha size="small" v-model="themeOptions['--view-button-color-active']"/>
                            </div>
                            <div class="setting-item ">
                                按钮边框<el-color-picker show-alpha size="small" v-model="themeOptions['--view-button-border-color']"/>
                            </div>
                            <div class="setting-item ">
                                禁用按钮<el-color-picker show-alpha size="small" v-model="themeOptions['--view-button-background-color-disabled']"/>
                            </div>
                            <div class="setting-item ">
                                分割线<el-color-picker show-alpha size="small" v-model="themeOptions['--view-dividing-line-color']"/>
                            </div>
                            <div class="setting-item ">
                                分割线rgb<el-color-picker show-alpha size="small" v-model="themeOptions['--view-dividing-line-color-rgb']"/>
                            </div>
                            <div class="setting-item ">
                                分割线（淡色）<el-color-picker show-alpha size="small" v-model="themeOptions['--view-dividing-line-color-tint']"/>
                            </div>
                            <div class="setting-item ">
                                分割线（亮色）<el-color-picker show-alpha size="small" v-model="themeOptions['--view-dividing-line-color-bright']"/>
                            </div>
                        </div>
                    </el-tab-pane>
                    <el-tab-pane label="部件" name="widget-tab">
                        <div class="setting widget-setting">
                            <div class="setting-item">
                                背景色（淡色）<el-color-picker show-alpha size="small" v-model="themeOptions['--ctrl-background-color-tint']"/>
                            </div>
                            <div class="setting-item">
                                背景色（亮色）<el-color-picker show-alpha size="small" v-model="themeOptions['--ctrl-background-color-bright']"/>
                            </div>
                            <div class="setting-item">
                                背景激活<el-color-picker show-alpha size="small" v-model="themeOptions['--ctrl-background-color-active']"/>
                            </div>
                            <div class="setting-item">
                                背景激活（淡色）<el-color-picker show-alpha size="small" v-model="themeOptions['--ctrl-background-color-active-tint']"/>
                            </div>
                            <div class="setting-item">
                                背景悬浮<el-color-picker show-alpha size="small" v-model="themeOptions['--ctrl-background-color-hover']"/>
                            </div>
                            <div class="setting-item">
                                字体<el-color-picker show-alpha size="small" v-model="themeOptions['--ctrl-font-color']"/>
                            </div>
                            <div class="setting-item">
                                字体（淡色）<el-color-picker show-alpha size="small" v-model="themeOptions['--ctrl-font-color-tint']"/>
                            </div>
                            <div class="setting-item">
                                字体（深色）<el-color-picker show-alpha size="small" v-model="themeOptions['--ctrl-font-color-bright']"/>
                            </div>
                            <div class="setting-item">
                                字体激活<el-color-picker show-alpha size="small" v-model="themeOptions['--ctrl-font-color-active']"/>
                            </div>
                            <div class="setting-item">
                                字体悬浮<el-color-picker show-alpha size="small" v-model="themeOptions['--ctrl-font-color-hover']"/>
                            </div>
                            <div class="setting-item">
                                边框<el-color-picker show-alpha size="small" v-model="themeOptions['--ctrl-border-color']"/>
                            </div>
                            <div class="setting-item">
                                边框（淡色）<el-color-picker show-alpha size="small" v-model="themeOptions['--ctrl-border-color-tint']"/>
                            </div>
                            <div class="setting-item">
                                边框激活<el-color-picker show-alpha size="small" v-model="themeOptions['--ctrl-border-color-active']"/>
                            </div>
                            <div class="setting-item">
                                边框悬浮<el-color-picker show-alpha size="small" v-model="themeOptions['--ctrl-border-color-hover']"/>
                            </div>
                            <div class="setting-item">
                                阴影<el-color-picker show-alpha size="small" v-model="themeOptions['--ctrl-shadow-color']"/>
                            </div>
                        </div>
                    </el-tab-pane>
                    <el-tab-pane label="组件" name="control-tab">
                        <div class="setting control-setting">
                            <div class="setting-item">
                                背景色（淡色）<el-color-picker show-alpha size="small" v-model="themeOptions['--item-background-color-tint']"/>
                            </div>
                            <div class="setting-item">
                                背景色（亮色）<el-color-picker show-alpha size="small" v-model="themeOptions['--item-background-color-bright']"/>
                            </div>
                            <div class="setting-item">
                                背景激活<el-color-picker show-alpha size="small" v-model="themeOptions['--item-background-color-active']"/>
                            </div>
                            <div class="setting-item">
                                背景悬浮<el-color-picker show-alpha size="small" v-model="themeOptions['--item-background-color-hover']"/>
                            </div>
                            <div class="setting-item">
                                字体<el-color-picker show-alpha size="small" v-model="themeOptions['--item-font-color']"/>
                            </div>
                            <div class="setting-item">
                                字体（淡色）<el-color-picker show-alpha size="small" v-model="themeOptions['--item-font-color-tint']"/>
                            </div>
                            <div class="setting-item">
                                字体（深色）<el-color-picker show-alpha size="small" v-model="themeOptions['--item-font-color-bright']"/>
                            </div>
                            <div class="setting-item">
                                字体激活<el-color-picker show-alpha size="small" v-model="themeOptions['--item-font-color-active']"/>
                            </div>
                            <div class="setting-item">
                                字体悬浮<el-color-picker show-alpha size="small" v-model="themeOptions['--item-font-color-hover']"/>
                            </div>
                            <div class="setting-item">
                                边框<el-color-picker show-alpha size="small" v-model="themeOptions['--item-border-color']"/>
                            </div>
                            <div class="setting-item">
                                边框（淡色）<el-color-picker show-alpha size="small" v-model="themeOptions['--item-border-color-tint']"/>
                            </div>
                            <div class="setting-item">
                                边框激活<el-color-picker show-alpha size="small" v-model="themeOptions['--item-border-color-active']"/>
                            </div>
                            <div class="setting-item">
                                边框悬浮<el-color-picker show-alpha size="small" v-model="themeOptions['--item-border-color-hover']"/>
                            </div>
                            <div class="setting-item">
                                阴影<el-color-picker show-alpha size="small" v-model="themeOptions['--item-shadow-color']"/>
                            </div>
                        </div>
                    </el-tab-pane>
                    <el-tab-pane label="侧边栏" name="left-sidebar-tab">
                        <div class="setting left-sidebar-setting">
                            <div class="setting-item bkcolor">
                               背景色<el-color-picker show-alpha size="small" v-model="themeOptions['--app-left-sidebar-background-color']"/>
                            </div>
                            <div class="setting-item bkcolor-deep">
                                背景色（深色）<el-color-picker show-alpha size="small" v-model="themeOptions['--app-left-sidebar-background-color-deep']"/>
                            </div>
                            <div class="setting-item bkcolor-active">
                                背景激活<el-color-picker show-alpha size="small" v-model="themeOptions['--app-left-sidebar-background-color-active']"/>
                            </div>
                            <div class="setting-item font-color">
                                字体颜色<el-color-picker show-alpha size="small" v-model="themeOptions['--app-left-sidebar-font-color']"/>
                            </div>
                            <div class="setting-item font-color-active">
                                字体激活<el-color-picker show-alpha size="small" v-model="themeOptions['--app-left-sidebar-font-color-active']"/>
                            </div>
                        </div>
                    </el-tab-pane>
                    <el-tab-pane label="底部" name="bottom-tab">
                        <div class="setting bottom-setting">
                            <div class="setting-item bkcolor">
                               背景色<el-color-picker show-alpha size="small" v-model="themeOptions['--app-footer-background-color']"/>
                            </div>
                            <div class="setting-item bkcolor-active">
                                背景激活<el-color-picker show-alpha size="small" v-model="themeOptions['--app-footer-background-color']"/>
                            </div>
                            <div class="setting-item font-color">
                                字体颜色<el-color-picker show-alpha size="small" v-model="themeOptions['--app-footer-color']"/>
                            </div>
                            <div class="setting-item font-color-active">
                                字体激活<el-color-picker show-alpha size="small" v-model="themeOptions['--app-footer-font-color-active']"/>
                            </div>
                        </div>
                    </el-tab-pane>
                    <el-tab-pane label="悬浮菜单" name="hover-menu-tab">
                        <div class="setting hover-menu-setting">
                            <div class="setting-item bkcolor">
                                背景色<el-color-picker show-alpha size="small" v-model="themeOptions['--app-suspension-windows-background-color']"/>
                            </div>
                            <div class="setting-item bkcolor-active">
                                背景激活<el-color-picker show-alpha size="small" v-model="themeOptions['--app-suspension-windows-background-color-active']"/>
                            </div>
                            <div class="setting-item font-color">
                                字体颜色<el-color-picker show-alpha size="small" v-model="themeOptions['--app-suspension-windows-color']"/>
                            </div>
                            <div class="setting-item font-color-active">
                                字体激活<el-color-picker show-alpha size="small" v-model="themeOptions['--app-suspension-windows-font-color-active']"/>
                            </div>
                        </div>
                    </el-tab-pane>
                    <el-tab-pane label="表单" name="form-tab">
                        <div class="setting form-setting">
                            <div class="setting-item">
                                标签字体<el-color-picker show-alpha size="small" v-model="themeOptions['--form-label-font-color']"/>
                            </div>
                            <div class="setting-item">
                                编辑器字体<el-color-picker show-alpha size="small" v-model="themeOptions['--form-editor-font-color']"/>
                            </div>
                            <div class="setting-item">
                                编辑器placeholder字体<el-color-picker show-alpha size="small" v-model="themeOptions['--form-editor-placeholder-font-color']"/>
                            </div>
                            <div class="setting-item">
                                编辑器背景<el-color-picker show-alpha size="small" v-model="themeOptions['--form-editor-background-color']"/>
                            </div>
                            <div class="setting-item">
                                编辑器边框<el-color-picker show-alpha size="small" v-model="themeOptions['--form-editor-border-color']"/>
                            </div>
                            <div class="setting-item">
                                编辑器激活<el-color-picker show-alpha size="small" v-model="themeOptions['--form-editor-active-color']"/>
                            </div>
                            <div class="setting-item">
                                编辑器激活rgb<el-color-picker show-alpha size="small" v-model="themeOptions['--form-editor-active-color-rgb']"/>
                            </div>
                            <div class="setting-item">
                                禁用编辑器字体<el-color-picker show-alpha size="small" v-model="themeOptions['--form-disable-editor-font-color']"/>
                            </div>
                            <div class="setting-item">
                                禁用编辑器背景<el-color-picker show-alpha size="small" v-model="themeOptions['--form-disable-editor-background-color']"/>
                            </div>
                            <div class="setting-item">
                                编辑器下拉区背景<el-color-picker show-alpha size="small" v-model="themeOptions['--form-editor-dropdown-background-color']"/>
                            </div>
                        </div>
                    </el-tab-pane>
                    <el-tab-pane label="杂项" name="other-tab">
                        <div class="setting other-setting">
                        </div>
                    </el-tab-pane>
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
    public activeSetting: string = 'app-tab';

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