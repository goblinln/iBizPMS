import Vue from 'vue';
import store from '../../store';
import i18n from '@/locale';
import AppModalCompponent from "./app-modal.vue";
import { modalController } from '@ionic/core';

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
        let props = { view: view, context: context, viewparams: viewparams, uuid: uuid };
        let component = AppModalCompponent;
        let vm: any = new Vue({
            store: store,
            i18n: i18n,
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
        let _context: any = {};
        Object.assign(_context, context);
        const uuid = this.getUUID();
        const result: any = await this.createVueExample(view, _context, viewparams, uuid);
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