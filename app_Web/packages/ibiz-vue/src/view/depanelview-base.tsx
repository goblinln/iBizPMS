import { IPSAppDEPanelView, IPSPanel } from '@ibiz/dynamic-model-api';
import { DePanelViewInterface, ModelTool } from 'ibiz-core';
import { MainViewBase } from './mainview-base';

/**
 * 实体面板视图基类
 *
 * @export
 * @class DePanelViewBase
 * @extends {MainViewBase}
 * @implements {DePanelViewInterface}
 */
export class DePanelViewBase extends MainViewBase implements DePanelViewInterface {

    /**
     * 实体面板视图实例对象
     * 
     * @memberof DePanelViewBase
     */
    public viewInstance!: IPSAppDEPanelView;

    /**
     * 面板部件实例对象
     * 
     * @memberof DePanelViewBase
     */
    public panelInstance?: IPSPanel;

    /**
     * 视图模型初始化
     * 
     * @memberof DePanelViewBase
     */
    public async viewModelInit() {
        this.viewInstance = (this.staticProps.modeldata) as IPSAppDEPanelView;
        await super.viewModelInit();
        this.panelInstance = ModelTool.findPSControlByType('PANEL', this.viewInstance?.getPSControls() || []);
    }

}