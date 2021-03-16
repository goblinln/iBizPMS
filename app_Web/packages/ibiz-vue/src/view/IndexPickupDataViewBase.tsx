import { MDViewBase } from './MDViewBase';
import { IBizDataViewModel, IBizIndexPickupDataViewModel } from 'ibiz-core';

export class IndexPickupDataViewBase extends MDViewBase {

    /**
     * 实体索引关系选择数据视图（部件视图）
     * 
     * @type {IBizIndexPickupDataViewModel}
     * @memberof IndexPickupDataViewBase
     */
    public viewInstance!: IBizIndexPickupDataViewModel;

    /**
     * 数据视图部件实例对象
     * 
     * @type {IBizDataViewModel}
     * @memberof IndexPickupDataViewBase
     */
    private dataviewInstance!: IBizDataViewModel;

    /**
     * 初始化表单选择数据视图实例
     * 
     * @memberof IndexPickupDataViewBase
     */
    public async viewModelInit() {
        this.viewInstance = new IBizIndexPickupDataViewModel(this.staticProps.modeldata, this.context);
        await this.viewInstance.loaded();
        await super.viewModelInit();
        this.dataviewInstance = this.viewInstance.getControl('dataview');
    }

    /**
     * 处理部件事件
     * 
     * @memberof IndexPickupDataViewBase
     */
    public onCtrlEvent(controlname: string, action: string, data: any) {
        if(Object.is(action,"selectionchange")){
            this.$emit('view-event', {action: 'viewdataschange', data: data });
        }
    }

    /**
     *  视图挂载
     *
     * @memberof IndexPickupDataViewBase
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
     * @memberof IndexPickupDataViewBase
     */
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        super.onDynamicPropsChange(newVal, oldVal);
    }

    /**
     * 监听视图静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof IndexPickupDataViewBase
     */
    public onStaticPropsChange(newVal: any, oldVal: any) {
        this.isSingleSelect = newVal.isSingleSelect;
        super.onStaticPropsChange(newVal, oldVal);
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof IndexPickupDataViewBase
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
     * @memberof IndexPickupDataViewBase
     */
    public computeTargetCtrlData(controlInstance:any) {
        const { targetCtrlName, targetCtrlParam, targetCtrlEvent } = super.computeTargetCtrlData(controlInstance);
        Object.assign(targetCtrlParam.staticProps,{
            isSingleSelect: this.isSingleSelect,
        })
        return { targetCtrlName, targetCtrlParam, targetCtrlEvent };
    }

}