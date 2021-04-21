import { IPSExpBar } from '@ibiz/dynamic-model-api';
import { MainViewBase } from './mainview-base';

/**
 * 导航视图基类
 *
 * @export
 * @class ExpViewBase
 * @extends {MainViewBase}
 */
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
    public expBarInstance!: IPSExpBar;

    /**
     * 渲染视图主体内容区
     * 
     * @memberof ExpViewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.expBarInstance);
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.expBarInstance?.name, on: targetCtrlEvent });
    }
}