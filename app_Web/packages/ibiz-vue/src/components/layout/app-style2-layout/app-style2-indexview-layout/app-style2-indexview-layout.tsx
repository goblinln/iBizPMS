import { Component } from 'vue-property-decorator';
import './app-style2-indexview-layout.less';
import { AppServiceBase } from "ibiz-core";
import { AppLoadingService, AppNavHistory } from "../../../../app-service";
import { AppStyle2DefaultLayout } from "../app-style2-default-layout/app-style2-default-layout";
import { IPSAppMenu } from '@ibiz/dynamic-model-api';

@Component({})
export class AppStyle2IndexViewLayout extends AppStyle2DefaultLayout {

    /**
     * 应用loading服务
     *
     * @memberof AppLayout
     */
     public appLoadingService = AppLoadingService.getInstance();
     
    /**
     * 菜单实例
     * 
     * @memberof AppStyle2IndexViewLayout
     */
    public menuInstance!: IPSAppMenu;

    /**
     * 抽屉状态
     *
     * @type {boolean}
     * @memberof AppStyle2IndexViewLayout
     */
    public contextMenuDragVisiable: boolean = false;

    /**
     * 导航服务
     * 
     * @memberof TabPageExpStyle2
     */
    protected navHistory: AppNavHistory = AppServiceBase.getInstance().getAppNavDataService();

    /**
     * 路由列表
     * 
     * @memberof AppStyle2IndexViewLayout 
     */
    get routerList() {
        if(this.navHistory?.historyList?.length > 0){
            let temp: any = [];
            this.navHistory?.historyList.forEach((item: any)=>{
                temp.push(item.to.fullPath);
            })
            return temp;
        }else{
            return [];
        }
    }

    /**
     * 当前主题
     *
     * @memberof AppStyle2IndexViewLayout
     */
    public selectTheme() {
        let _this: any = this;
        if (_this.$router.app.$store.state.selectTheme) {
            return _this.$router.app.$store.state.selectTheme;
        } else if (localStorage.getItem('theme-class')) {
            return localStorage.getItem('theme-class');
        } else {
            return 'app-theme-studio-dark';
        }
    }

    /**
     * 路由键值
     * 
     * @memberof AppStyle2IndexViewLayout 
     */
    get routerViewKey() {
        let _this: any = this;
        return _this.$route.fullPath;
    }

    /**
     * 初始化
     * 
     * @memberof AppStyle2IndexViewLayout
     */
    public created() {
        document.getElementsByTagName('html')[0].className = this.selectTheme();
        this.$uiState.changeLayoutState({
            styleMode: 'DEFAULT'
        });
    }

    /**
     * 初始化完毕
     * 
     * @memberof AppStyle2IndexViewLayout
     */
    public mounted() {
        setTimeout(() => {
            const el = document.getElementById('app-loading-x');
            if (el) {
                el.style.display = 'none';
            }
        }, 300);
    }

    /**
     * 绘制内容
     * 
     * @memberof AppStyle2IndexViewLayout
     */
    public render(h: any): any {
        const viewClassNames = this.initRenderClassNames();
        if (this.viewInstance && this.viewInstance.mainMenuAlign && Object.is(this.viewInstance.mainMenuAlign, "CENTER")) {
            const { codeName, title } = this.viewInstance;
            return (<studio-view
                viewName={codeName?.toLowerCase()}
                viewTitle={title}
                viewInstance={this.viewInstance}
                viewparams={this.viewparams}
                context={this.context}
                class={viewClassNames}>
                {this.$slots.default}
            </studio-view>)
        } else {
            const styleMode: any = this.$uiState.layoutState.styleMode;
            let leftContent: any;
            switch (styleMode) {
                case 'DEFAULT':
                    leftContent = this.$slots.leftExp;
                    break;
                case 'STYLE2':
                    leftContent = this.$slots.leftNavMenu;
            }
            return (
                <app-layout ref="appLayout" class={viewClassNames}>
                    <template slot="header">
                        <app-header>
                            <template slot="header_left">
                                <div class="title">
                                    {this.viewInstance.enableAppSwitch ? <span class="menuicon" on-click={() => this.contextMenuDragVisiable = !this.contextMenuDragVisiable}><icon type="md-menu" />&nbsp;</span> : null}
                                    {this.viewInstance.caption}
                                </div>
                            </template>
                            <template slot="header_right">
                                {this.$slots.headerMenus}
                                {this.$topRenderService.rightItemsRenders.map((fun: any) => fun(h))}
                                {<app-orgsector />}
                                {<app-user />}
                            </template>
                        </app-header>
                        {this.$slots.default}
                        {this.viewInstance.enableAppSwitch ? <context-menu-drag contextMenuDragVisiable={this.contextMenuDragVisiable}></context-menu-drag> : null}
                    </template>
                    <app-content>
                        {leftContent ?
                            <template slot="content_left">
                                {leftContent}
                            </template> : null}
                        {styleMode === 'DEFAULT' ? this.$slots.tabPageExp : null}
                        <div class="view-warp" on-click={() => this.contextMenuDragVisiable = false}>
                            <app-keep-alive routerList={this.routerList}>
                                <router-view key={this.routerViewKey}></router-view>
                            </app-keep-alive>
                        </div>
                        {this.$slots.bootomExp ?
                            <template slot="content_bottom">
                                {this.$slots.bootomExp}
                            </template> : null}
                    </app-content>
                    <template slot="footer">
                        { this.viewInstance.defaultPage 
                            ? <app-footer v-notification-signal={this.appLoadingService.isLoading} ref="footer" />
                            : <app-footer ref="footer" />
                        }
                    </template>
                </app-layout>
            );
        }

    }
}