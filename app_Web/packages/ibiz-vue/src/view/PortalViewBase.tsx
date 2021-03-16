import { IBizDashboardModel, IBizPortalViewModel } from 'ibiz-core';
import { ViewBase } from './ViewBase';

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
    public viewInstance!: IBizPortalViewModel;

    /**
     * 数据看板实例
     *
     * @public
     * @type {IBizDashboardModel}
     * @memberof PortalViewBase
     */
    public dashboardInstance !:IBizDashboardModel;

    /**
     * 初始化列表视图实例
     *
     * @memberof PortalViewBase
     */
    public async viewModelInit() {
        this.viewInstance = new IBizPortalViewModel(this.staticProps.modeldata, this.context);
        await this.viewInstance.loaded();
        await super.viewModelInit();
        this.dashboardInstance = this.viewInstance.getControl('dashboard');        
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
        if(action == 'controlIsMounted'){
            this.viewState.next({ tag: 'dashboard', action: 'load', data: {} });
        }else{
            super.onCtrlEvent(controlname, action, data);
        }
    }

}
