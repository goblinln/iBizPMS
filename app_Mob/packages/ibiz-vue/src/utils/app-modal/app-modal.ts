import Vue from 'vue';
import AppModalCompponent from "./app-modal.vue";
import { modalController } from '@ionic/core';
import { AppServiceBase } from 'ibiz-core';

/**
 * 模态框工具
 *
 * @export
 * @class AppModal
 */
export class AppModal {

    /**
     * 实例对象
     *
     * @private
     * @static
     * @memberof AppModal
     */
    private static modal = new AppModal();

    /**
     * store对象
     *
     * @private
     * @memberof AppModal
     */
    private store: any;

    /**
     * i18n对象
     *
     * @private
     * @memberof AppModal
     */
    private i18n: any;

    /**
     * 路由对象
     *
     * @private
     * @memberof AppModal
     */
    private router: any;

    /**
     * Creates an instance of AppModal.
     * 
     * @memberof AppModal
     */
    private constructor() {
        if (AppModal.modal) {
            return AppModal.modal;
        }
    }

    /**
     * 初始化基础数据
     * 
     * @memberof AppModal
     */
    private initBasicData() {
        const appService = AppServiceBase.getInstance();
        this.store = appService.getAppStore();
        this.i18n = appService.getI18n();
        this.router = appService.getRouter();
    }

    /**
     * 获取单例对象
     *
     * @static
     * @returns {AppModal}
     * @memberof AppModal
     */
    public static getInstance(): AppModal {
        if (!AppModal.modal) {
            AppModal.modal = new AppModal();
        }
        return AppModal.modal;
    }

    /**
     * 创建 Vue 实例对象
     *
     * @private
     * @param {{ viewname: string, title: string, width?: number, height?: number, isfullscreen?: boolean }} view
     * @param {*} [context={}]
     * @param {*} [viewparams={}]
     * @param {string} uuid
     * @returns {Promise<any>}
     * @memberof AppModal
     */
    private async createVueExample(view: { viewname: string, title: string, width?: number, height?: number, isfullscreen?: boolean }, context: any = {}, viewparams: any = {}, uuid: string): Promise<any> {
        const self: any = this;
        if (!self.store || !self.i18n) {
            self.initBasicData();
        }
        let props = { view: view, context: context, viewparams: viewparams, uuid: uuid };
        let component = AppModalCompponent;
        let vm: any = new Vue({
            store: this.store,
            i18n: this.i18n,
            router: this.router,
            render(h) {
                return h(component, { props });
            },
        }).$mount();
        let currentModal: any = await this.createModal(vm.$el);
        const comp: any = vm.$children[0];
        return new Promise((reaolve, reject) => {
            const sub = comp.getSubject();
            sub.subscribe((result: any) => {
                if (currentModal) {
                    currentModal.dismiss();
                    currentModal = null;
                    vm = null;
                    reaolve(result);
                }
            }, () => {
                if (currentModal) {
                    currentModal.dismiss();
                    currentModal = null;
                    vm = null;
                }
            }, () => {
                if (currentModal) {
                    currentModal.dismiss();
                    currentModal = null;
                    vm = null;
                }
            });
        });
    }

    /**
     * 打开 ionic 模式模态框
     *
     * @private
     * @param {Element} ele
     * @returns {Promise<any>}  
     * @memberof AppModal
     */
    private async createModal(ele: any): Promise<any> {
        const modal = await modalController.create({
            component: ele
        });
        await modal.present();
        return modal;
    }

    /**
     * 打开模态视图
     *
     * @param {{ viewname: string, title: string, width?: number, height?: number }} view
     * @param {*} [context={}]
     * @param {*} [viewparams={}]
     * @returns {Promise<any>}
     * @memberof AppModal
     */
    public async openModal(view: { viewname: string, title: string, width?: number, height?: number }, context: any = {}, viewparams: any = {}): Promise<any> {
        const uuid = this.getUUID();
        const result: any = await this.createVueExample(view, context, viewparams, uuid);
        return result;
    }

    /**
     * 获取节点标识
     *
     * @private
     * @returns {string}
     * @memberof AppModal
     */
    private getUUID(): string {
        function s4() {
            return Math.floor((1 + Math.random()) * 0x10000).toString(16).substring(1);
        }
        return s4() + s4() + '-' + s4() + '-' + s4() + '-' + s4() + '-' + s4() + s4() + s4();
    }

}