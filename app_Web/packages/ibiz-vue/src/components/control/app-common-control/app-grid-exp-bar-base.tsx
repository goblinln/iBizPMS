import { Prop, Watch, Emit } from "vue-property-decorator";
import { Util } from 'ibiz-core';
import { GridExpBarControlBase } from '../../../widgets/grid-exp-bar-control-base';

/**
 * 表格导航栏基类
 *
 * @export
 * @class AppGridExpBarBase
 * @extends {GridExpBarControlBase}
 */
export class AppGridExpBarBase extends GridExpBarControlBase {
    
    /**
     * 部件静态参数
     *
     * @memberof AppGridExpBarBase
     */
    @Prop() public staticProps!: any;

    /**
     * 部件动态参数
     *
     * @memberof AppGridExpBarBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppGridExpBarBase
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
     * 监听静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppGridExpBarBase
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
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppGridExpBarBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void {}

    /**
     * 销毁视图回调
     *
     * @memberof AppGridExpBarBase
     */
    public destroyed(){
        this.ctrlDestroyed();
    }

    /**
     * 绘制表格导航栏
     *
     * @memberof AppGridExpBarBase
     */
    public render() {
        if(!this.controlIsLoaded) {
            return null;
        }
        return this.renderMainContent();
    }
}

