import { IBizDashboardModel, IBizDePortalViewModel, PortalViewEngine } from 'ibiz-core';
import { GlobalService } from 'ibiz-service';
import { MainViewBase } from './mainview-base';

/**
 * 实体数据看板视图基类
 *
 * @export
 * @class DashboardViewBase
 * @extends {MainViewBase}
 */
export class DashboardViewBase extends MainViewBase {

    /**
     * 数据视图视图实例
     * 
     * @memberof GanttViewBase
     */
    public viewInstance!: IBizDePortalViewModel;

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
     * @type {IBizDashboardModel}
     * @memberof DashboardViewBase
     */
    public dashboardInstance !:IBizDashboardModel;

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
        const dashboard = this.viewInstance?.getControl('dashboard');
        this.engine.init({
            view: this,
            dashboard: (this.$refs[dashboard.name] as any).ctrl,
            p2k: '0',
            keyPSDEField: this.viewInstance?.appDataEntity?.codeName?.toLowerCase(),
            majorPSDEField: this.viewInstance?.appDataEntity?.majorField?.codeName?.toLowerCase(),
            isLoadDefault: this.viewInstance?.loadDefault,
        });
    }

    /**
     * 初始化列表视图实例
     *
     * @memberof DashboardViewBase
     */
    public async viewModelInit() {
        this.viewInstance = new IBizDePortalViewModel(this.staticProps?.modeldata, this.context);
        await this.viewInstance.loaded();
        await super.viewModelInit();
        this.dashboardInstance = this.viewInstance.getControl('dashboard');
        if (!(this.Environment && this.Environment.isPreviewMode)) {
            this.appEntityService = await new GlobalService().getService(this.viewInstance?.appDataEntity?.codeName);
        }
    }

    /**
     *  视图挂载
     *
     * @memberof ViewBase
     */
    public viewMounted() {}

    /**
     * 加载数据
     * 
     * @memberof DashboardViewBase
     */
    public loadModel(){
        const { appDataEntity } = this.viewInstance;
        let _this: any = this;
        if (this.context[appDataEntity?.codeName?.toLowerCase()]) {
            this.appEntityService.getDataInfo(JSON.parse(JSON.stringify(this.context)), {}, false).then((response: any) => {
                if (!response || response.status !== 200) {
                    return;
                }
                const { data: _data } = response;
                _this.viewState.next({ tag: 'all-portlet', action: 'loadmodel', data:_data});
                if (_data[appDataEntity.majorField.codeName.toLowerCase()]) {
                    this.model.dataInfo = _data[appDataEntity.majorField.codeName.toLowerCase()];
                    if (_this.$tabPageExp) {
                        _this.$tabPageExp.setCurPageCaption({
                            caption: _this.$t(_this.model.srfCaption),
                            title: _this.$t(_this.model.srfCaption),
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
        if(action == 'controlIsMounted'){
            this.loadModel();
            this.engineInit();
        }else{
            super.onCtrlEvent(controlname, action, data);
        }
    }

}
