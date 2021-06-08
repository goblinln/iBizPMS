import { ModelTool } from 'ibiz-core';
import { MDViewBase } from './mdview-base';
import { IPSAppDEReportView, IPSDEReportPanel } from '@ibiz/dynamic-model-api';

/**
 * 实体报表视图基类
 *
 * @export
 * @class DeReportViewBase
 * @extends {MainViewBase}
 */
export class DeReportViewBase extends MDViewBase {

    /**
     * 视图实例
     * 
     * @memberof DeReportViewBase
     */
    public viewInstance!: IPSAppDEReportView;

    /**
     * 报表面板实例
     * 
     * @memberof DeReportViewBase
     */
    public reportPanelInstance!: IPSDEReportPanel;

    /**
     * 初始化表格视图实例
     * 
     * @param opts 
     * @memberof DeReportViewBase
     */
    public async viewModelInit() {
        this.viewInstance = (this.staticProps?.modeldata) as IPSAppDEReportView;
        await super.viewModelInit();
        this.reportPanelInstance = ModelTool.findPSControlByName('reportpanel', this.viewInstance.getPSControls()) as IPSDEReportPanel;       
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof DeReportViewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent }: { targetCtrlName: string, targetCtrlParam: any, targetCtrlEvent: any } = this.computeTargetCtrlData(this.reportPanelInstance);
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.reportPanelInstance?.name, on: targetCtrlEvent },);
    }
}