import { MDViewBase } from './mdview-base';
import { FormPickupDataViewInterface, ModelTool } from 'ibiz-core';
import { IPSAppDEDataView, IPSDEDataView } from '@ibiz/dynamic-model-api';

/**
 * 表单数据选择视图
 *
 * @export
 * @class FormPickupDataViewBase
 * @extends {MDViewBase}
 * @implements {FormPickupDataViewInterface}
 */
export class FormPickupDataViewBase extends MDViewBase implements FormPickupDataViewInterface{

    /**
     * 表单数据选择视图实例对象
     * 
     * @type {IBizFormPickupDataViewModel}
     * @memberof FormPickupDataViewBase
     */
    public viewInstance!: IPSAppDEDataView;

    /**
     * 数据视图部件实例对象
     * 
     * @type {IBizDataViewModel}
     * @memberof FormPickupDataViewBase
     */
    private dataviewInstance!: IPSDEDataView;

    /**
     * 初始化表单选择数据视图实例
     * 
     * @memberof FormPickupDataViewBase
     */
    public async viewModelInit() {
      this.viewInstance = (this.staticProps?.modeldata) as IPSAppDEDataView;
        await super.viewModelInit();
        this.dataviewInstance = ModelTool.findPSControlByType("DATAVIEW",this.viewInstance.getPSControls());
    }

    /**
     * 处理部件事件
     * 
     * @memberof FormPickupDataViewBase
     */
    public onCtrlEvent(controlname: string, action: string, data: any) {
        if(Object.is(action,"controlIsMounted")){
            this.setIsMounted(controlname);
        }
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