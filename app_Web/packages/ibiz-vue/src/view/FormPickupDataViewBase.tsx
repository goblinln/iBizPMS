import { MDViewBase } from './MDViewBase';
import { IBizDataViewModel, IBizFormPickupDataViewModel } from 'ibiz-core';

export class FormPickupDataViewBase extends MDViewBase {

    /**
     * 表单数据选择视图实例对象
     * 
     * @type {IBizFormPickupDataViewModel}
     * @memberof FormPickupDataViewBase
     */
    public viewInstance!: IBizFormPickupDataViewModel;

    /**
     * 数据视图部件实例对象
     * 
     * @type {IBizDataViewModel}
     * @memberof FormPickupDataViewBase
     */
    private dataviewInstance!: IBizDataViewModel;

    /**
     * 初始化表单选择数据视图实例
     * 
     * @memberof FormPickupDataViewBase
     */
    public async viewModelInit() {
        this.viewInstance = new IBizFormPickupDataViewModel(this.staticProps.modeldata, this.context);
        await this.viewInstance.loaded();
        await super.viewModelInit();
        this.dataviewInstance = this.viewInstance.getControl('dataview');
    }

    /**
     * 处理部件事件
     * 
     * @memberof FormPickupDataViewBase
     */
    public onCtrlEvent(controlname: string, action: string, data: any) {
        if(Object.is(action,"selectionchange")){
            this.$emit('view-event', {action: 'viewdataschange', data: data });
        }
    }

    /**
     *  视图挂载
     *
     * @memberof FormPickupDataViewBase
     */
    public viewMounted(){
        super.viewMounted();
        this.viewState.next({ tag: this.dataviewInstance.name, action: 'load', data: this.viewparams });
    }

    /**
     * 监听视图动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof FormPickupDataViewBase
     */
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        super.onDynamicPropsChange(newVal, oldVal);
    }

    /**
     * 监听视图静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof FormPickupDataViewBase
     */
    public onStaticPropsChange(newVal: any, oldVal: any) {
        this.isSingleSelect = newVal.isSingleSelect;
        super.onStaticPropsChange(newVal, oldVal);
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof FormPickupDataViewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.dataviewInstance);
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.dataviewInstance.name, on: targetCtrlEvent });
    }

    /**
     * 计算目标部件所需参数
     *
     * @param {string} [controlType]
     * @returns
     * @memberof FormPickupDataViewBase
     */
    public computeTargetCtrlData(controlInstance:any) {
        const { targetCtrlName, targetCtrlParam, targetCtrlEvent } = super.computeTargetCtrlData(controlInstance);
        Object.assign(targetCtrlParam.staticProps,{
            isSingleSelect: this.isSingleSelect,
        })
        return { targetCtrlName, targetCtrlParam, targetCtrlEvent };
    }

}