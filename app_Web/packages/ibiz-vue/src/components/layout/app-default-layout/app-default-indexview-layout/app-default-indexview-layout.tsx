import { AppDefaultViewLayout } from "../app-default-view-layout/app-default-view-layout";
import { Component } from 'vue-property-decorator';
import { Util, AppServiceBase } from "ibiz-core";
import { AppLoadingService } from "../../../../app-service/loading-service/app-loading-service";
import { IPSAppIndexView, IPSLanguageRes } from "@ibiz/dynamic-model-api";
import './app-default-indexview-layout.less';

@Component({})
export class AppDefaultIndexViewLayout extends AppDefaultViewLayout {
    /**
     * 应用loading服务
     *
     * @memberof AppLayout
     */
    public appLoadingService = AppLoadingService.getInstance();

    /**
     * 是否满屏
     *
     * @type {boolean}
     * @memberof IndexViewBase
     */
    public isFullScreen: boolean = false;

    /**
     * 菜单收缩变化
     *
     * @type {boolean}
     * @memberof AppDefaultIndexViewLayout
     */
    public collapseChange: boolean = false;

    /**
     * 抽屉状态
     *
     * @type {boolean}
     * @memberof AppDefaultIndexViewLayout
     */
    public contextMenuDragVisiable: boolean = false;

    /**
     * 当前主题
     *
     * @memberof AppDefaultIndexViewLayout
     */
    public selectTheme() {
        let _this: any = this;
        if (_this.$router.app.$store.state.selectTheme) {
            return _this.$router.app.$store.state.selectTheme;
        } else if (localStorage.getItem('theme-class')) {
            return localStorage.getItem('theme-class');
        } else {
            return 'app-theme-default';
        }
    }

    /**
     * 获取当前语言环境
     *
     * @memberof AppDefaultIndexViewLayout
     */
    public getSelectLanguage() { }

    /**
     * 当前字体
     *
     * @memberof AppDefaultIndexViewLayout
     */
    get selectFont() {
        let _this: any = this;
        if (_this.$router.app.$store.state.selectFont) {
            return _this.$router.app.$store.state.selectFont;
        } else if (localStorage.getItem('font-family')) {
            return localStorage.getItem('font-family');
        } else {
            return 'Microsoft YaHei';
        }
    }

    /**
     * 菜单收缩
     *
     * @memberof AppDefaultIndexViewLayout
     */
    public collapseMenus() {
        if (this.$store.getters['getCustomParamByTag']('srffullscreen')) {
            this.isFullScreen = !this.isFullScreen;
            if (this.isFullScreen) {
                this.collapseChange = true;
            } else {
                this.collapseChange = false;
            }
        } else {
            this.collapseChange = !this.collapseChange;
        }
        this.$emit('onCollapseChange', this.collapseChange);
    }

    /**
     * 路由列表
     * 
     * @memberof AppDefaultIndexViewLayout 
     */
    get routerList() {
        return this.$store.state.historyPathList;
    }

    /**
     * 路由键值
     * 
     * @memberof AppDefaultIndexViewLayout 
     */
    get routerViewKey() {
        let _this: any = this;
        return _this.$route.fullPath;
    }

    /**
     * 初始化
     * 
     * @memberof AppDefaultIndexViewLayout 
     */
    public created() {
        document.getElementsByTagName('html')[0].className = this.selectTheme();
        this.isFullScreen = Boolean(this.$store.getters['getCustomParamByTag']('srffullscreen'));
    }

    /**
     * 渲染左侧菜单样式
     * 
     * @memberof AppDefaultIndexViewLayout
     */
    public renderContentLeft() {
        let contentClass = {
            'index_content': true,
            'index_tab_content': Object.is(this.viewInstance.viewStyle, 'DEFAULT'),
            'index_route_content': !Object.is(this.viewInstance.viewStyle, 'DEFAULT')
        }
        return (
            <layout style={{ 'font-family': this.selectFont, 'height': '100vh' }}>
                <header class="index_header">
                    <div class="header-left" >
                        <div class="page-logo">
                            <div class="page-logo-left">
                            {(this.viewInstance as IPSAppIndexView).enableAppSwitch ? <span class="page-logo-menuicon" on-click={() => this.contextMenuDragVisiable = !this.contextMenuDragVisiable}><icon type="md-menu" />&nbsp;</span>: null}
                                {(this.viewInstance as IPSAppIndexView).appIconPath ? <img class="page-logo-image" src={(this.viewInstance as IPSAppIndexView).appIconPath}></img> : null}
                                <span class="page-logo-title">{this.model.srfCaption}</span>
                                {(this.viewInstance as IPSAppIndexView).enableAppSwitch ? <context-menu-drag viewStyle={this.viewInstance.viewStyle} contextMenuDragVisiable={this.contextMenuDragVisiable}></context-menu-drag> : null}
                            </div>
                            {!this.collapseChange ? <i class="ivu-icon el-icon-s-fold" on-click={() => this.collapseMenus()}></i> : null}
                            {this.collapseChange ? <i class="ivu-icon el-icon-s-unfold" on-click={() => this.collapseMenus()}></i> : null}
                            {Object.is(this.viewInstance.viewStyle, 'STYLE4') ? <app-breadcrumb indexViewTag={this.viewInstance.codeName} /> : null}
                        </div>
                    </div>
                    <div class="header-right" style="display: flex;align-items: center;justify-content: space-between;">
                        <app-header-menus />
                        <app-lang style='font-size: 15px;padding: 0 10px;' />
                        <app-orgsector />
                        <app-user viewStyle={this.viewInstance.viewStyle} />
                        <app-message-popover />
                    </div>
                </header>
                <layout>
                    <sider class="index_sider" width={this.isFullScreen ? 0 : this.collapseChange ? 68 : 200} hide-trigger value={this.collapseChange}>
                        {this.$slots.default}
                    </sider>
                    <content class={contentClass} >
                    {Object.is(this.viewInstance.viewStyle, 'DEFAULT') ? <tab-page-exp modelService={this.modelService}></tab-page-exp> : null}
                        <app-keep-alive routerList={this.routerList}>
                            <router-view key={this.routerViewKey}></router-view>
                        </app-keep-alive>
                    </content>
                </layout>
            </layout>
        );
    }

    /**
     * 渲染顶部菜单样式
     * 
     * @memberof AppDefaultIndexViewLayout
     */
    public renderContentTop() {
        return (
            <layout style={{ 'font-family': this.selectFont, 'height': '100vh' }}>
                <header class="index_header" >
                    <div class="header-left">
                        <div class="page-logo">
                            {(this.viewInstance as IPSAppIndexView).appIconPath ? <img class="page-logo-image" src={(this.viewInstance as IPSAppIndexView).appIconPath}></img> : null}
                            <span style="display: inline-block;margin-left: 10px;font-size: 22px;">{this.model.srfCaption}</span>
                        </div>
                        <div style="margin-left: 50px;">
                            {this.$slots.default}
                        </div>
                    </div>
                    <div class="header-right" style="display: flex;align-items: center;justify-content: space-between;">
                        <app-header-menus />
                        <app-lang style='font-size: 15px;padding: 0 10px;'></app-lang>
                        <app-orgsector></app-orgsector>
                        <app-user></app-user>
                        <app-message-popover></app-message-popover>
                        <app-lock-scren />
                        <app-full-scren />
                        <app-theme style="width:45px;display: flex;justify-content: center;"></app-theme>
                    </div>
                </header>
                <content class="app-horizontal-layout" style="height:calc(100vh - 50px);" on-click={() => this.contextMenuDragVisiable = false}>
                    <router-view></router-view>
                </content>
            </layout>
        );
    }

    /**
     * 渲染中间菜单样式
     * 
     * @memberof AppDefaultIndexViewLayout
     */
    public renderContentMiddle() {
        let cardClass = {
            'view-card': true
        };
        return (
            <card class={cardClass} disHover={true} bordered={false}>
                <div slot='title' class='header-container' key='view-header'>
                    <span class='caption-info'>{this.model.srfCaption}</span>
                </div>
                <div class='content-container'>
                    {this.$slots.default}
                </div>
            </card>
        );
    }

    /**
     * 渲染分页导航菜单样式
     *
     * @return {*} 
     * @memberof AppDefaultIndexViewLayout
     */
    public renderContentTabexpView() {
        return (
            <div class='view-container view-default tabexp-container'>
                {this.$slots.default}
            </div>
        );
    }

    /**
     * 绘制布局
     * 
     * @memberof AppDefaultIndexViewLayout
     */
    public render(h: any) {
        let viewClass = {
            'view-container': ((!Object.is((this.viewInstance as IPSAppIndexView).mainMenuAlign, "LEFT") && !Object.is((this.viewInstance as IPSAppIndexView).mainMenuAlign, "TOP"))) ? true : false,
            'inner_indexview': Object.is((this.viewInstance as IPSAppIndexView).mainMenuAlign, "CENTER") ? true : false,
            'view-default': true,
            [this.viewInstance.viewType.toLowerCase()]: true,
            [Util.srfFilePath2(this.viewInstance.codeName)]: true
        };
        return (
            <div class={viewClass}>
                {(this.viewInstance as IPSAppIndexView)?.defaultPage && <div class="loading-bar" v-notification-signal={this.appLoadingService.isLoading}></div>}
                <app-studioaction
                    viewInstance={this.viewInstance}
                    context={this.context}
                    viewparams={this.viewparams}
                    viewName={this.viewInstance.codeName.toLowerCase()}
                    viewTitle={this.model?.srfCaption} />
                { (Object.is(this.viewInstance.mainMenuAlign, "LEFT") || !this.viewInstance.mainMenuAlign) ? this.renderContentLeft() : null}
                { Object.is(this.viewInstance.mainMenuAlign, "TOP") ? this.renderContentTop() : null}
                { Object.is(this.viewInstance.mainMenuAlign, "CENTER") ? this.renderContentMiddle() : null}
                { Object.is(this.viewInstance.mainMenuAlign, 'TABEXP_LEFT') ? this.renderContentTabexpView() : null}
                { Object.is(this.viewInstance.mainMenuAlign, 'TABEXP_TOP') ? this.renderContentTabexpView() : null}
                { Object.is(this.viewInstance.mainMenuAlign, 'TABEXP_RIGHT') ? this.renderContentTabexpView() : null}
                { Object.is(this.viewInstance.mainMenuAlign, 'TABEXP_BOTTOM') ? this.renderContentTabexpView() : null}
            </div>
        );
    }


}