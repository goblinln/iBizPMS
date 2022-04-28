import { Util } from 'ibiz-core';
import { Prop, Watch, Emit  } from 'vue-property-decorator';
import { DataViewExpBarControlBase } from '../../../widgets';

/**
 * 数据视图导航栏部件基类
 *
 * @export
 * @class AppDataViewExpBarBase
 * @extends {DataViewExpBarControlBase}
 */
export class AppDataViewExpBarBase extends DataViewExpBarControlBase {

    /**
     * 部件静态参数
     *
     * @memberof AppDataViewExpBarBase
     */
    @Prop() public staticProps!: any;

    /**
     * 部件动态参数
     *
     * @memberof AppDataViewExpBarBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppDataViewExpBarBase
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
     * @memberof AppDataViewExpBarBase
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
     * @param 抛出参数 
     * @memberof AppDataViewBase
     */
     @Emit('ctrl-event')
     public ctrlEvent({ controlname, action, data }: { controlname: string, action: string, data: any }): void { }

    /**
     * 绘制数据视图导航栏
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppDataViewExpBarBase
     */
    public render() {
        if(!this.controlIsLoaded) {
            return null;
        }
        return this.renderMainContent();
    }
}