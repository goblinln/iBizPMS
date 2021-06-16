import { IPSAppDEDashboardView, IPSDEDashboard } from '@ibiz/dynamic-model-api';
import { ModelTool, PortalViewEngine, DashboardViewInterface, Util } from 'ibiz-core';
import { GlobalService } from 'ibiz-service';
import { MainViewBase } from './mainview-base';

/**
 * 实体数据看板视图基类
 *
 * @export
 * @class DashboardViewBase
 * @extends {MainViewBase}
 * @implements {DashboardViewInterface}
 */
export class DashboardViewBase extends MainViewBase implements DashboardViewInterface {

    /**
     * 数据视图视图实例
     * 
     * @memberof DashboardViewBase
     */
    public viewInstance!: IPSAppDEDashboardView;

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof DashboardViewBase
     */
    public engine: PortalViewEngine = new PortalViewEngine();

    /**
     * 数据看板实例
     *
     * @public
     * @type {IPSDEDashboard}
     * @memberof DashboardViewBase
     */
    public dashboardInstance !:IPSDEDashboard;

    /**
     * 引擎初始化
     *
     * @public
     * @memberof DashboardViewBase
     */
    public engineInit(): void {
        if (this.Environment && this.Environment.isPreviewMode) {
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
     * @memberof DashboardViewBase
     */
    public async viewModelInit() {
        await super.viewModelInit();
        this.dashboardInstance = ModelTool.findPSControlByName('dashboard',this.viewInstance.getPSControls()) as IPSDEDashboard;
        if (!(this.Environment && this.Environment.isPreviewMode)) {
            this.appEntityService = await new GlobalService().getService(this.appDeCodeName);
        }
    }

    /**
     *  视图挂载
     *
     * @memberof ViewBase
     */
    public viewMounted() {
        this.loadModel();
        this.engineInit();
    }

    /**
     * 加载数据
     * 
     * @memberof DashboardViewBase
     */
    public loadModel(){
        let _this: any = this;
        if (this.context[this.appDeCodeName.toLowerCase()]) {
            let tempContext: any = Util.deepCopy(this.context);
            if (tempContext && tempContext.srfsessionid) {
                tempContext.srfsessionkey = tempContext.srfsessionid;
                delete tempContext.srfsessionid;
            }
            this.appEntityService?.getDataInfo?.(tempContext, {}, false).then((response: any) => {
                if (!response || response.status !== 200) {
                    return;
                }
                const { data: _data } = response;
                if(_data.srfopprivs){
                    this.$store.commit('authresource/setSrfappdeData', { key: `${this.deName}-${_data[this.appDeKeyFieldName.toLowerCase()]}`, value: _data.srfopprivs });
                }
                _this.viewState.next({ tag: 'all-portlet', action: 'loadmodel', data:_data});
                if (_data[this.appDeMajorFieldName.toLowerCase()]) {
                    this.model.dataInfo = _data[this.appDeMajorFieldName.toLowerCase()];
                    if (_this.$tabPageExp) {
                        _this.$tabPageExp.setCurPageCaption({
                            caption: _this.model.srfCaption,
                            title: _this.model.srfCaption,
                            info: _this.model.dataInfo,
                            viewtag: this.viewtag
                        });
                    }
                    if (_this.$route) {
                        _this.$route.meta.info = _this.model.dataInfo;
                    }
                }
            })
        }
    }

    /**
     * 渲染视图主体内容区
     *
     * @memberof DashboardViewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.dashboardInstance);
        return this.$createElement(targetCtrlName, {
            props: targetCtrlParam,
            ref: this.dashboardInstance?.name,
            on: targetCtrlEvent,
        });
    }

    /**
     * 部件事件
     * @param ctrl 部件 
     * @param action  行为
     * @param data 数据
     * 
     * @memberof DashboardViewBase
     */
    public onCtrlEvent(controlname: string, action: string, data: any) {
        if (action == 'refreshAll') {
            this.loadModel();
        }
        super.onCtrlEvent(controlname, action, data);
    }

}
