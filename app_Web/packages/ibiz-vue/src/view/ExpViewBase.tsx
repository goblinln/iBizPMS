import { IBizControlBaseModel } from 'ibiz-core';
import { MainViewBase } from './MainViewBase';

export class ExpViewBase extends MainViewBase {
    /**
     * 视图唯一标识
     *
     * @type {string}
     * @memberof ExpViewBase
     */
    public viewUID: string = '';

    /**
     * 导航栏实例
     * 
     * @memberof ExpViewBase
     */
    public expBarInstance!: IBizControlBaseModel;

    /**
     * 渲染视图主体内容区
     * 
     * @memberof ExpViewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.expBarInstance);
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.expBarInstance.name, on: targetCtrlEvent });
    }
}