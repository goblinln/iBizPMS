import { AppDefaultViewLayout } from "../app-default-view-layout/app-default-view-layout";
import { Component } from 'vue-property-decorator';
import { Util, AppServiceBase } from "ibiz-core";
import { AppLoadingService } from "../../../../app-service/loading-service/app-loading-service";
import { IPSAppIndexView } from "@ibiz/dynamic-model-api";
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
     * 导航模式
     * 
     * AppDefaultIndexViewLayout
     */
    public navModel: any;

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
        this.collapseChange = !this.collapseChange;
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
        this.navModel = Object.is(this.viewInstance.viewStyle, 'DEFAULT') ? 'tab' : 'route';
        this.isFullScreen = Boolean(sessionStorage.getItem("srffullscreen"));
    }

    /**
     * 鼠标拖动事件
     *
     * @param {*} val
     * @returns {*}
     * @memberof AppDefaultIndexViewLayout
     */
    public mouse_move() {
        let move_axis: any = document.getElementById("move_axis");
        let left_move: any = document.getElementById("left_move");
        let right_move: any = document.getElementById("right_move");
        let movebox: any = document.getElementById("movebox");
        let leftWidth: number = parseInt(left_move.style.width);
        move_axis.onmousedown = (e: any) => {
            let startX = e.clientX;
            move_axis.left = move_axis.offsetLeft;
            document.onmousemove = (e: any) => {
                let endX = e.clientX;
                let moveLen = move_axis.left + (endX - startX);
                let maxT = movebox.clientWidth - move_axis.offsetWidth;
                if (moveLen < 150) moveLen = 150;
                if (moveLen > maxT - 150) moveLen = maxT - 150;
                move_axis.style.left = moveLen;
                left_move.style.width = moveLen + "px";
                right_move.style.width = (movebox.clientWidth - moveLen - 5) + "px";
                if (moveLen > 500) {
                    left_move.style.width = 500 + 'px';
                }

                let left_width: number = parseInt(left_move.style.width);
                move_axis.style.left = left_width - 5 + 'px';
                if (left_width < leftWidth) {
                    move_axis.style.left = leftWidth - 5 + 'px';
                }
            }
            document.onmouseup = (evt) => {
                document.onmousemove = null;
                document.onmouseup = null;
                move_axis.releaseCapture && move_axis.releaseCapture();
            }
            move_axis.setCapture && move_axis.setCapture();
            return false;
        }
    }

    /**
     * 渲染左侧菜单样式
     * 
     * @memberof AppDefaultIndexViewLayout
     */
    public renderContentLeft() {
        let contentClass = {
            'index_content': true,
            'index_tab_content': (Object.is(this.navModel, 'tab') && !this.isFullScreen),
            'index_route_content': Object.is(this.navModel, 'route')
        }
        return (
            <layout style={{ 'font-family': this.selectFont, 'height': '100vh' }}>
                <layout id="movebox">
                    {!this.isFullScreen ? <sider class="index_sider" width={this.collapseChange ? 64 : 200} hide-trigger value={this.collapseChange} id="left_move">
                        <div class="sider-top">
                            <div class="page-logo">
                                {(this.viewInstance as IPSAppIndexView).enableAppSwitch ? <span class="menuicon" on-click={() => this.contextMenuDragVisiable = !this.contextMenuDragVisiable}><icon type="md-menu" /></span> : null}
                                <span style="overflow-x: hidden;text-overflow: ellipsis;white-space: nowrap;display: block;text-align: center;font-weight: 300;font-size: 20px;">{!this.collapseChange ? AppServiceBase.getInstance().getAppModelDataObject().name : null}</span>
                            </div>
                        </div>
                        {this.$slots.default}
                        {(this.viewInstance as IPSAppIndexView).enableAppSwitch ? <context-menu-drag contextMenuDragVisiable={this.contextMenuDragVisiable}></context-menu-drag> : null}
                    </sider> : null}
                    {!this.collapseChange && !this.isFullScreen ? <div id="move_axis"></div> : null}
                    <layout id="right_move">
                        <header class="index_header">
                            <div class="header-left" >
                                {!this.isFullScreen ? <div class="page-logo">
                                    {!this.collapseChange ? <i class="ivu-icon el-icon-s-fold" on-click={() => this.collapseMenus()}></i> : null}
                                    {this.collapseChange ? <i class="ivu-icon el-icon-s-unfold" on-click={() => this.collapseMenus()}></i> : null}
                                    <app-breadcrumb navModel={this.navModel} indexViewTag="app-index-view" />
                                </div> : <span class={{ "fullscreen_logo": true }}>{this.viewInstance.caption}</span>}
                            </div>
                            <div class="header-right" style="display: flex;align-items: center;justify-content: space-between;">
                                <app-header-menus />
                                <app-lang style='font-size: 15px;padding: 0 10px;' />
                                <app-orgsector />
                                <app-user />
                                <app-message-popover />
                                <app-lock-scren />
                                <app-full-scren />
                                <app-theme style="width:45px;display: flex;justify-content: center;"></app-theme>
                            </div>
                        </header>
                        <content class={contentClass} >
                            {Object.is(this.navModel, 'tab') && !this.isFullScreen ? <tab-page-exp></tab-page-exp> : null}
                            <app-keep-alive routerList={this.routerList}>
                                <router-view key={this.routerViewKey}></router-view>
                            </app-keep-alive>
                        </content>
                    </layout>
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
                            <img src="../../../assets/img/logo.png" height="32" />
                            <span style="display: inline-block;margin-left: 10px;font-size: 22px;">{this.viewInstance.title}</span>
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
                    <span class='caption-info'>{this.viewInstance.caption}</span>
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
            <div class='view-container view-default tabexp-container' style={{ margin: '0 12px', height: 'calc(100% - 65px)' }}>
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
            'view-container': Object.is((this.viewInstance as IPSAppIndexView).mainMenuAlign, "CENTER") ? true : false,
            'inner_indexview': Object.is((this.viewInstance as IPSAppIndexView).mainMenuAlign, "CENTER") ? true : false,
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
                    viewTitle={this.viewInstance.title} />
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