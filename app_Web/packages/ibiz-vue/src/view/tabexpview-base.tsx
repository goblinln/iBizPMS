import { IPSAppDETabExplorerView, IPSTabExpPanel, IPSDEEditForm } from '@ibiz/dynamic-model-api';
import { DataPanelEngine, ModelTool, TabExpViewEngine } from 'ibiz-core';
import { MainViewBase } from './mainview-base';

/**
 * 分页导航视图基类
 *
 * @export
 * @class TabexpviewBase
 * @extends {MainViewBase}
 */
export class TabExpViewBase extends MainViewBase {

    /**
     * 视图实例
     * 
     * @memberof TabExpviewBase
     */
    public viewInstance!: IPSAppDETabExplorerView;

    /**
     * 分页导航面板实例
     * 
     * @memberof TabExpviewBase
     */
    public tabExpPanelInstance!: IPSTabExpPanel;

     /**
     * 数据面板表单实例
     * 
     * @memberof TabExpviewBase
     */
    public dataPanelInstance?: IPSDEEditForm;

    /**
     * 视图引擎
     *
     * @public
     * @type {TabExpViewEngine}
     * @memberof TabExpviewBase
     */
    public engine: TabExpViewEngine = new TabExpViewEngine();

    /**
     * 数据面板引擎
     *
     * @public
     * @type {DataPanelEngine}
     * @memberof TabExpviewBase
     */
    public dataPanelEngine: DataPanelEngine = new DataPanelEngine();

    /**
     * 加载模型
     * 
     * @memberof TabExpviewBase
     */
    public loadModel() {
        let _this: any = this;
        if (this.context[this.appDeCodeName.toLowerCase()]) {
            this.appEntityService?.getDataInfo(JSON.parse(JSON.stringify(this.context)), {}, false).then((response: any) => {
                if (!response || response.status !== 200) {
                    return;
                }
                const { data: _data } = response;
                if(_data.srfopprivs){
                    this.$store.commit('authresource/setSrfappdeData', { key: `${this.deName}-${_data[this.appDeKeyFieldName.toLowerCase()]}`, value: _data.srfopprivs });
                }
                this.engine.computeToolbarState(false, _data);
                this.viewState.next({ tag: 'tabexppanel', action: 'loadmodel', data: _data });
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
     * 引擎初始化
     *
     * @public
     * @memberof TabExpviewBase
     */
    public engineInit(): void {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        let viewEngineOpts = ({
            view: this,
            parentContainer: this.$parent,
            p2k: '0',
            keyPSDEField: this.appDeCodeName.toLowerCase(),
            majorPSDEField: this.appDeMajorFieldName.toLowerCase(),
            isLoadDefault: this.viewInstance?.loadDefault,
        });
        this.engine.init(viewEngineOpts);
        if (this.dataPanelInstance) {
            let dataPanelEngineOpts = ({
                view: this,
                datapanel: (this.$refs[this.dataPanelInstance?.name] as any).ctrl,
                keyPSDEField: this.appDeCodeName.toLowerCase(),
                majorPSDEField: this.appDeMajorFieldName.toLowerCase(),
                isLoadDefault: true,
            });
            this.dataPanelEngine.init(dataPanelEngineOpts);
        }
    }

    /**
     * 初始化分页导航视图实例
     * 
     * @memberof TabExpviewBase
     */
    public async viewModelInit() {
        this.viewInstance = (this.staticProps?.modeldata) as IPSAppDETabExplorerView;
        await super.viewModelInit();
        this.tabExpPanelInstance = ModelTool.findPSControlByType("TABEXPPANEL",this.viewInstance.getPSControls()) as IPSTabExpPanel;
        this.dataPanelInstance = ModelTool.findPSControlByName("DATAPANEL",this.viewInstance.getPSControls()) as IPSDEEditForm;
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof TabExpviewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.tabExpPanelInstance);
        return this.$createElement(targetCtrlName, { slot: 'default', props: targetCtrlParam, ref: this.tabExpPanelInstance?.name, on: targetCtrlEvent });
    }

}