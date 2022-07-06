import { IPSExpBar } from '@ibiz/dynamic-model-api';
import { MobExpViewInterface } from 'ibiz-core';
import { MainViewBase } from './main-view-base';

export class MobExpViewBase extends MainViewBase implements MobExpViewInterface {
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
    public expBarInstance!: IPSExpBar;

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