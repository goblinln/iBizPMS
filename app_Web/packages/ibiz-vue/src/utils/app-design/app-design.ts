import Vue from 'vue';
import { Subject } from 'rxjs';
import AppDesignCompponent from "./app-design.vue";
import { LogUtil } from 'ibiz-core';

export class AppDesign {

    /**
     * 实例对象
     *
     * @private
     * @static
     * @memberof AppDesign
     */
    private static readonly $design = new AppDesign();

    /**
     * 构造方法
     * 
     * @memberof AppDesign
     */
    constructor() {
        if (AppDesign.$design) {
            return AppDesign.$design;
        }
    }

    /**
     * vue 实例
     *
     * @private
     * @type {Vue}
     * @memberof AppDesign
     */
    private vueExample!: Vue;

    /**
     * 获取实例对象
     *
     * @static
     * @returns
     * @memberof AppDesign
     */
    public static getInstance() {
        return AppDesign.$design;
    }

    /**
     * 创建 Vue 实例对象
     *
     * @memberof AppDesign
     */
    private createVueExample(params:any): Subject<any> {
        if(this.vueExample){
            this.vueExample.$destroy(); 
        }
        try {
            let component = AppDesignCompponent;
            const vm = new Vue({
                render(h) {
                    return h(component, {props:params} );
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
     * @memberof AppDesign
     */
    public openDrawer(params:any): Subject<any> {
        try {
            const subject = this.createVueExample(params);
            return subject;
        } catch (error) {
            LogUtil.log(error);
            return new Subject<any>();
        }
    }

}