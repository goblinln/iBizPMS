import { MobDePanelViewInterface, LogUtil, ModelTool } from 'ibiz-core';
import { MainViewBase } from './main-view-base';
import { IPSAppDEPanelView, IPSPanel } from '@ibiz/dynamic-model-api';

/**
 * 移动端实体数据看板视图基类
 *
 * @export
 * @class MobPanelViewBase
 * @extends {MainViewBase}
 */
export class MobPanelViewBase extends MainViewBase implements MobDePanelViewInterface {

    /**
     * 数据视图视图实例
     * 
     * @memberof MobPanelViewBase
     */
    public viewInstance!: IPSAppDEPanelView;


    /**
     * 面板部件实例对象
     * 
     * @memberof MobPanelViewBase
     */
    public panelInstance?: IPSPanel;

    /**
     *  视图初始化
     *
     * @memberof DePanelViewBase
     */
    public viewInit() {
        const that = this;
        super.viewInit();
        if (that.panelState) {
            that.panelStateEvent = that.panelState.subscribe((res: any) => {
                if (!Object.is(res.tag, that.viewInstance.name)) {
                    return;
                }
                if (Object.is(res.action, 'load')) {
                    that.viewState.next({
                        tag: that.panelInstance?.name,
                        action: "load",
                        data: res.data
                    });
                }
            })
        }
    }

    /**
     * 初始化列表视图实例
     *
     * @memberof MobPanelViewBase
     */
    public async viewModelInit() {
        this.viewInstance = (this.staticProps?.modeldata) as IPSAppDEPanelView;
        await super.viewModelInit();
        this.panelInstance = ModelTool.findPSControlByType('PANEL', this.viewInstance?.getPSControls() || []);
    }

    /**
     * 视图刷新
     *
     * @param {*} args
     * @memberof MobPanelViewBase
     */
    public refresh(args?: any): any {
        const refs: any = this.$refs;
        try {
            if (refs && this.panelInstance && this.panelInstance.name && refs[this.panelInstance.name]) {
                refs[this.panelInstance.name]?.ctrl?.refresh(args);
            }
        } catch (error) {
            LogUtil.log(refs, this.panelInstance)
        }
    }


}
