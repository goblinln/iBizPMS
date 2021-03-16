import { IBizTabExpViewModel, IBizTabExpPanelModel, TabExpViewEngine } from 'ibiz-core';
import { GlobalService } from 'ibiz-service';
import { MainViewBase } from './MainViewBase';

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
    public viewInstance!: IBizTabExpViewModel;

    /**
     * 列表实例
     * 
     * @memberof TabExpviewBase
     */
    public tabExpPanelInstance!: IBizTabExpPanelModel;

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof TabExpviewBase
     */
    public engine: TabExpViewEngine = new TabExpViewEngine();

    /**
     * 加载模型
     * 
     * @memberof TabExpviewBase
     */
    public loadModel() {
        const { appDataEntity } = this.viewInstance;
        let _this: any = this;
        if (this.context[appDataEntity?.codeName?.toLowerCase()]) {
            this.appEntityService.getDataInfo(JSON.parse(JSON.stringify(this.context)), {}, false).then((response: any) => {
                if (!response || response.status !== 200) {
                    return;
                }
                const { data: _data } = response;
                this.engine.computeToolbarState(false, _data);
                this.viewState.next({ tag: 'tabexppanel', action: 'loadmodel', data: _data });
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
     * 引擎初始化
     *
     * @public
     * @memberof TabExpviewBase
     */
    public engineInit(): void {
        let engineOpts = ({
            view: this,
            parentContainer: this.$parent,
            p2k: '0',
            keyPSDEField: (this.viewInstance.appDataEntity.codeName).toLowerCase(),
            majorPSDEField: (this.viewInstance.appDataEntity.majorField.codeName).toLowerCase(),
            isLoadDefault: this.viewInstance.loadDefault,
        });
        this.engine.init(engineOpts);
    }

    /**
     * 初始化分页导航视图实例
     * 
     * @memberof TabExpviewBase
     */
    public async viewModelInit() {
        this.viewInstance = new IBizTabExpViewModel(this.staticProps.modeldata, this.context);
        await this.viewInstance.loaded();
        const { appDataEntity } = this.viewInstance
        await super.viewModelInit();
        this.appEntityService = await new GlobalService().getService(this.viewInstance.appDataEntity?.codeName);
        this.tabExpPanelInstance = this.viewInstance.getControl('tabexppanel');
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof TabExpviewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.tabExpPanelInstance);
        return this.$createElement(targetCtrlName, { slot: 'default', props: targetCtrlParam, ref: this.viewInstance.viewTabExpPanel.name, on: targetCtrlEvent });
    }

}