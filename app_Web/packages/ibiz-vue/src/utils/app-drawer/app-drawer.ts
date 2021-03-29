import Vue from 'vue';
import { Subject } from 'rxjs';
import AppDrawerCompponent from "./app-drawer.vue";
import { appTopDrawerContainer } from '../app-top-drawer/app-top-drawer-container';
import { AppServiceBase } from 'ibiz-core';

export class AppDrawer {

    /**
     * 实例对象
     *
     * @private
     * @static
     * @memberof AppDrawer
     */
    private static readonly $drawer = new AppDrawer();

    /**
     * store对象
     *
     * @private
     * @memberof AppDrawer
     */
    private store: any;

    /**
     * i18n对象
     *
     * @private
     * @memberof AppDrawer
     */
    private i18n: any;

    /**
     * 构造方法
     * 
     * @memberof AppDrawer
     */
    constructor() {
        this.initBasicData();
        if (AppDrawer.$drawer) {
            return AppDrawer.$drawer;
        }
    }

    /**
     * 初始化基础数据
     * 
     * @memberof AppDrawer
     */
    private initBasicData(){
        const appService = AppServiceBase.getInstance();
        this.store = appService.getAppStore();
        this.i18n = appService.getI18n();
    }

    /**
     * vue 实例
     *
     * @private
     * @type {Vue}
     * @memberof AppDrawer
     */
    private vueExample!: Vue;

    /**
     * 获取实例对象
     *
     * @static
     * @returns
     * @memberof AppDrawer
     */
    public static getInstance() {
        return AppDrawer.$drawer;
    }

    /**
     * 创建 Vue 实例对象
     *
     * @private
     * @param {{ viewname: string, title: string, width?: number, height?: number, placement?: any }} view
     * @param {*} [context={}] 应用上下文参数
     * @param {*} [viewparams={}] 视图参数
     * @param {string} uuid 标识
     * @returns {Subject<any>}
     * @memberof AppDrawer
     */
    private createVueExample(view: { viewname: string, title: string, width?: number, height?: number, placement?: any }, dynamicProps: any = {}, staticProps: any = {}, uuid: string): Subject<any> {
        const self: any = this;
        if(!self.store || !self.i18n) {
            self.initBasicData();
        }
        try {
            let props = { view: view, dynamicProps: dynamicProps, staticProps: staticProps, uuid: uuid };
            let component = AppDrawerCompponent;
            const vm = new Vue({
                store: this.store,
                i18n: this.i18n,
                render(h) {
                    return h(component, { props });
                }
            }).$mount();
            this.vueExample = vm;
            document.body.appendChild(vm.$el);
            const comp: any = vm.$children[0];
            return comp.getSubject();
        } catch (error) {
            console.error(error);
            return new Subject<any>();
        }
    }

    /**
     * 打开抽屉
     *
     * @param {({ viewname: string, title: string, width?: number, height?: number, placement?: 'DRAWER_LEFT' | 'DRAWER_RIGHT' })} view 视图
     * @param {*} [context={}] 应用上下文参数
     * @param {any[]} deResParameters 关系实体参数对象
     * @param {any[]} parameters 当前应用视图参数对象
     * @param {any[]} args 多项数据
     * @param {*} [data={}] 行为参数
     * @returns {Subject<any>}
     * @memberof AppDrawer
     */
    public openDrawer(view: { viewname: string, title: string, width?: number, height?: number, placement?: 'DRAWER_LEFT' | 'DRAWER_RIGHT' }, dynamicProps: any = {}, staticProps: any = {}): Subject<any> {
        if (view.placement && Object.is(view.placement, 'DRAWER_TOP')) {
            return appTopDrawerContainer.openDrawer(view, dynamicProps, staticProps) as any;
        }
        try {
            const uuid = this.getUUID();
            const subject = this.createVueExample(view, dynamicProps, staticProps, uuid);
            return subject;
        } catch (error) {
            console.log(error);
            return new Subject<any>();
        }
    }

    /**
     * 打开上方抽屉
     *
     * @param {({ viewname: string, title: string, width?: number, height?: number, placement?: 'DRAWER_LEFT' | 'DRAWER_RIGHT' })} view 视图
     * @param {*} [context={}] 应用上下文参数
     * @param {any[]} deResParameters 关系实体参数对象
     * @param {any[]} parameters 当前应用视图参数对象
     * @param {any[]} args 多项数据
     * @param {*} [data={}] 行为参数
     * @returns {Subject<any>}
     * @memberof AppDrawer
     */
    public openTopDrawer(view: { viewname: string, title: string, width?: number, height?: number, placement?: 'DRAWER_TOP' }, dynamicProps: any = {}, staticProps: any = {}): Subject<any> {
        return appTopDrawerContainer.openDrawer(view, dynamicProps, staticProps) as any;
    }

    /**
     * 生成uuid
     *
     * @private
     * @returns {string}
     * @memberof AppDrawer
     */
    private getUUID(): string {
        function s4() {
            return Math.floor((1 + Math.random()) * 0x10000).toString(16).substring(1);
        }
        return s4() + s4() + '-' + s4() + '-' + s4() + '-' + s4() + '-' + s4() + s4() + s4();
    }


}