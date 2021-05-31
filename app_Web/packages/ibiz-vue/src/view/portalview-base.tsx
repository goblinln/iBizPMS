import { IPSAppPortalView, IPSDEDashboard } from '@ibiz/dynamic-model-api';
import { ModelTool } from 'ibiz-core';
import { ViewBase } from './view-base';

/**
 * 应用视图基类
 *
 * @export
 * @class PortalViewBase
 * @extends {MainViewBase}
 */
export class PortalViewBase extends ViewBase {

    /**
     * 数据视图视图实例
     * 
     * @memberof GanttViewBase
     */
    public viewInstance!: IPSAppPortalView;

    /**
     * 数据看板实例
     *
     * @public
     * @type {IPSDEDashboard}
     * @memberof PortalViewBase
     */
    public dashboardInstance !:IPSDEDashboard;

    /**
     * 初始化列表视图实例
     *
     * @memberof PortalViewBase
     */
    public async viewModelInit() {
        await super.viewModelInit();
        this.dashboardInstance = ModelTool.findPSControlByName('dashboard',this.viewInstance.getPSControls()) as IPSDEDashboard;      
    }

    /**
     *  视图挂载
     *
     * @memberof ViewBase
     */
     public viewMounted() {
        this.viewState.next({ tag: 'dashboard', action: 'load', data: {} });
    }

    /**
     * 渲染视图主体内容区
     *
     * @memberof PortalViewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.dashboardInstance);
        return this.$createElement(targetCtrlName, {
            props: targetCtrlParam,
            ref: this.dashboardInstance.name,
            on: targetCtrlEvent,
        });
    }

    /**
     * 部件事件
     * @param ctrl 部件 
     * @param action  行为
     * @param data 数据
     * 
     * @memberof ViewBase
     */
    public onCtrlEvent(controlname: string, action: string, data: any) {
        super.onCtrlEvent(controlname, action, data);
    }

}
