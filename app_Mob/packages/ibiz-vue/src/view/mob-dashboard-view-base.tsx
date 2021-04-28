import { ModelTool, MobPortalViewEngine } from 'ibiz-core';
import { GlobalService } from 'ibiz-service';
import { MainViewBase } from './main-view-base';
import { IPSAppDEDashboardView, IPSDEDashboard } from '@ibiz/dynamic-model-api';

/**
 * 移动端实体数据看板视图基类
 *
 * @export
 * @class MobDashboardViewBase
 * @extends {MainViewBase}
 */
export class MobDashboardViewBase extends MainViewBase {

    /**
     * 数据视图视图实例
     * 
     * @memberof MobDashboardViewBase
     */
    public viewInstance!: IPSAppDEDashboardView;

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof MobDashboardViewBase
     */
    public engine: MobPortalViewEngine = new MobPortalViewEngine();

    /**
     * 数据看板实例
     *
     * @public
     * @type {IBizDashboardModel}
     * @memberof MobDashboardViewBase
     */
    public dashboardInstance !: IPSDEDashboard;

    /**
     * 引擎初始化
     *
     * @public
     * @memberof MobDashboardViewBase
     */
    public engineInit(opts: any = {}): void {
        if (this.Environment?.isPreviewMode) {
            return;
        }
        this.engine.init({
            view: this,
            dashboard: (this.$refs[this.dashboardInstance.name] as any).ctrl,
            p2k: '0',
            keyPSDEField: this.appDeCodeName.toLowerCase(),
            majorPSDEField: this.appDeMajorFieldName.toLowerCase(),
            isLoadDefault: this.viewInstance.loadDefault,
        });
    }

    /**
     * 初始化列表视图实例
     *
     * @memberof MobDashboardViewBase
     */
    public async viewModelInit() {
        this.viewInstance = (this.staticProps?.modeldata) as IPSAppDEDashboardView;
        await super.viewModelInit();
        this.dashboardInstance = ModelTool.findPSControlByName('dashboard', this.viewInstance.getPSControls() || []);
        this.appEntityService = await new GlobalService().getService(this.appDeCodeName);
    }

    /**
     *  视图挂载
     *
     * @memberof ViewBase
     */
    public viewMounted() { }

    /**
     * 加载数据
     * 
     * @memberof MobDashboardViewBase
     */
    public loadModel() {
        let _this: any = this;
        if (this.context[this.appDeCodeName.toLowerCase()]) {
            this.appEntityService.getDataInfo(JSON.parse(JSON.stringify(this.context)), {}, false).then((response: any) => {
                if (!response || response.status !== 200) {
                    return;
                }
                const { data: _data } = response;
                // _this.viewState.next({ tag: 'all-portlet', action: 'loadmodel', data: _data });
                if (_data[this.appDeMajorFieldName.toLowerCase()]) {
                    this.model.dataInfo = _data[this.appDeMajorFieldName.toLowerCase()];
                    if (_this.$route) {
                        _this.$route.meta.info = _this.model.dataInfo;
                    }
                }
                this.initNavCaption();
            })
        }
    }

    /**
     * 渲染视图主体内容区
     *
     * @memberof MobDashboardViewBase
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
     * @memberof MobDashboardViewBase
     */
    public onCtrlEvent(controlname: string, action: string, data: any) {
        if (action == 'controlIsMounted') {
            this.loadModel();
            this.engineInit();
        } else {
            super.onCtrlEvent(controlname, action, data);
        }
    }

}
