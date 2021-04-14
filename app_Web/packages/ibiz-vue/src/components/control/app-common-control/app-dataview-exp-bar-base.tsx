import { Util } from 'ibiz-core';
import { Prop, Watch } from 'vue-property-decorator';
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
     * 绘制数据视图导航栏
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppDataViewExpBarBase
     */
    public render() {
        return this.renderMainContent();
    }
}