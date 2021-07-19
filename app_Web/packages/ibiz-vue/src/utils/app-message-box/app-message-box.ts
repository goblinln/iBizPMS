import { Subject } from 'rxjs';
import Vue from 'vue';
import { MessageBoxOptions } from './interface/message-box-options';
import appMessageBox from "./app-message-box.vue";
import { AppServiceBase } from 'ibiz-core';

/**
 * 提示信息
 *
 * @export
 * @class AppMessageBox
 */
export class AppMessageBox {
    /**
     * 唯一实例
     *
     * @private
     * @static
     * @memberof AppMessageBox
     */
    private static readonly instance = new AppMessageBox();

    /**
     * vue 实例
     *
     * @private
     * @type {Vue}
     * @memberof AppModal
     */
    private vueExample!: Vue;


    /**
     * 引用对象
     *
     * @private
     * @type {*}
     * @memberof AppMessageBox
     */
    private refs: any;

    /**
     * vue全局对象
     *
     * @type {*}
     * @memberof AppMessageBox
     */
    public store: any;


    /**
     * 多语言
     *
     * @type {*}
     * @memberof AppMessageBox
     */
    public i18n: any;

    /**
     * 获取唯一实例
     *
     * @static
     * @return {*}  {AppMessageBox}
     * @memberof AppMessageBox
     */
    public static getInstance(): AppMessageBox {
        return AppMessageBox.instance;
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
    }

    /**
     * 打开提示信息
     *
     * @param {*} options
     * @return {*}
     * @memberof AppMessageBox
     */
    public open(options: any): Subject<any> {
        return this.createVueExample(options)
    }

    /**
     * 创建vue 实例
     *
     * @private
     * @param {ModalConfirmOptions} opt
     * @return {*}  {Subject<any>}
     * @memberof AppMessageBox
     */
    private createVueExample(opt: MessageBoxOptions): Subject<any> {
        const self: any = this;
        if (!self.store || !self.i18n) {
            self.initBasicData();
        }
        try {
            let props = { ...opt };
            let component = appMessageBox;
            const vm = new Vue({
                store: this.store,
                i18n: this.i18n,
                render(h) {
                    return h(component, { props, class: opt.customClass });
                }
            }).$mount();
            this.vueExample = vm;
            document.body.appendChild(vm.$el);
            this.refs = vm.$children[0];
            return this.refs.getSubject();
        } catch (error) {
            console.error(error);
            return new Subject<any>();
        }
    }

    /**
     * 关闭
     *
     * @memberof AppMessageBox
     */
    public close() {
        if (this.refs) {
            this.refs.close();
        }
    }
}
