import { Prop, Watch } from 'vue-property-decorator';
import { Util, ViewFactory } from 'ibiz-core';
import { MainViewBase } from '../../../view/MainViewBase';

/**
 * 应用视图基类
 *
 * @export
 * @class AppViewBase
 * @extends {MainViewBase}
 */
export class AppViewBase extends MainViewBase {

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppViewBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 视图静态参数
     *
     * @type {string}
     * @memberof AppViewBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听视图动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppViewBase
     */
    @Watch('dynamicProps',{
        immediate: true,
    })
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        if (newVal && !Util.isFieldsSame(newVal,oldVal)) {
           super.onDynamicPropsChange(newVal,oldVal);
        }
    }

    /**
     * 监听视图静态参数变化
     * 
     * @memberof AppViewBase
     */
    @Watch('staticProps', {
        immediate: true,
    })
    public onStaticPropsChange(newVal: any, oldVal: any) {
        if (newVal && !Util.isFieldsSame(newVal,oldVal)) {
            super.onStaticPropsChange(newVal,oldVal);
        }
    }

        /**
     * 初始化编辑视图实例
     * 
     * @memberof EditViewBase
     */
    public async viewModelInit() {
        this.viewInstance = ViewFactory.getInstance(this.staticProps.modeldata, this.context);
        await this.viewInstance.loaded();
        await super.viewModelInit();
    }

    /**
     * 销毁视图回调
     *
     * @memberof AppViewBase
     */
    public destroyed(){
        this.viewDestroyed();
    }

}
