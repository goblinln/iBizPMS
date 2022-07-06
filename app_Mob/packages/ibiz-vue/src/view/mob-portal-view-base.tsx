import { ViewBase } from './view-base';
import { IPSAppPortalView, IPSDEDashboard } from '@ibiz/dynamic-model-api';
import { MobPortalViewInterface, ModelTool } from 'ibiz-core';

/**
 * 应用看板视图基类
 *
 * @export
 * @class MobPortalViewBase
 * @extends {MainViewBase}
 */
export class MobPortalViewBase extends ViewBase implements MobPortalViewInterface {

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
     * @type {IBizMobDashboardModel}
     * @memberof MobPortalViewBase
     */
    public dashboardInstance !: IPSDEDashboard;

    /**
     * 初始化列表视图实例
     *
     * @memberof MobPortalViewBase
     */
    public async viewModelInit() {
        await super.viewModelInit();
        this.dashboardInstance = ModelTool.findPSControlByName('dashboard',this.viewInstance.getPSControls()) as IPSDEDashboard;     
    }

    /**
     * 渲染视图主体内容区
     *
     * @memberof MobPortalViewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.dashboardInstance);
        return this.$createElement(targetCtrlName, {
            slot: 'default',
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
        // if (action == 'controlIsMounted') {
        //     this.viewState.next({ tag: 'dashboard', action: 'load', data: {} });
        // } else {
        //     super.onCtrlEvent(controlname, action, data);
        // }
    }

}
