import { IPSAppCounterRef, IPSDETabViewPanel, IPSTabExpPanel, IPSSysImage, IPSAppDETabSearchView } from '@ibiz/dynamic-model-api';
import { ModelTool, TabSearchViewEngine, TabSearchViewInterface, Util } from 'ibiz-core';
import { MDViewBase } from './mdview-base';

/**
 * 实体分页搜索视图基类
 *
 * @export
 * @class TabSearchViewBase
 * @extends {MDViewBase}
 * @implements {TabSearchViewInterface}
 */
export class TabSearchViewBase extends MDViewBase implements TabSearchViewInterface{

    /**
     * 视图实例
     * 
     * @memberof TabSearchViewBase
     */
    public viewInstance!: IPSAppDETabSearchView;

    /**
     * 分页导航面板实例
     * 
     * @memberof TabSearchViewBase
     */
    public tabExpPanelInstance!: IPSTabExpPanel;

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof TabSearchViewBase
     */
    public engine: TabSearchViewEngine = new TabSearchViewEngine();

    /**
     * 当前激活分页视图面板
     * 
     * @memberof TabSearchViewBase
     */
    public activiedTabViewPanel: any = {};

    /**
     * 分页面板权限标识存储对象
     * 
     * @memberof TabSearchViewBase
     */
    public authResourceObject: any;

    /**
     * 分页标题绘制状态
     * 
     * @memberof TabSearchViewBase
     */
    public tabsHeaderRenderState: boolean = false;

    /**
     * 加载模型
     * 
     * @memberof TabSearchViewBase
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
     * @memberof TabSearchViewBase
     */
    public engineInit(): void {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        let engineOpts = ({
            view: this,
            parentContainer: this.$parent,
            p2k: '0',
            keyPSDEField: this.appDeCodeName.toLowerCase(),
            majorPSDEField: this.appDeMajorFieldName.toLowerCase(),
            isLoadDefault: this.viewInstance?.loadDefault,
        });
        this.engine.init(engineOpts);
    }

    /**
     * 初始化分页导航视图实例
     * 
     * @memberof TabSearchViewBase
     */
    public async viewModelInit() {
        this.viewInstance = (this.staticProps?.modeldata) as IPSAppDETabSearchView;
        await super.viewModelInit();
        this.tabExpPanelInstance = ModelTool.findPSControlByType("TABEXPPANEL",this.viewInstance.getPSControls()) as IPSTabExpPanel;
        const tabViewPanels: any[] = this.tabExpPanelInstance.getPSControls() || [];
        if (tabViewPanels.length > 0) {
            this.activiedTabViewPanel = tabViewPanels[0].name;
        }
    }

    /**
     * 分页面板点击
     * 
     * @memberof TabSearchViewBase
     */
    public tabPanelClick(event: any) {
        if (event) {
            this.viewState.next({ tag: this.tabExpPanelInstance?.name, action: 'changeActivedTab', data: event });
        }
    }

    /**
     * 初始化分页导航面板标题
     * 
     * @memberof TabSearchViewBase
     */
    public initTabExpHeader(data: any) {
        const getActivedTabViewPanel = () => {
            const tabViewPanels: any[] = this.tabExpPanelInstance.getPSControls() || [];
            if (tabViewPanels.length > 0) {
                return tabViewPanels[0].name;
            }
            return null;
        }
        this.activiedTabViewPanel = data?.activiedTabViewPanel || getActivedTabViewPanel();
        this.authResourceObject = data?.authResourceObject;
        this.tabsHeaderRenderState = this.viewInstance.viewStyle == 'DEFAULT' ? true : false;
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof TabSearchViewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.tabExpPanelInstance);
        return this.$createElement(targetCtrlName, { slot: 'default', props: targetCtrlParam, ref: this.tabExpPanelInstance?.name, on: targetCtrlEvent });
    }

    /**
     * 绘制分页面板项
     * 
     * @memberof TabSearchViewBase
     */
    public renderTabPaneContent(tabViewPanel: IPSDETabViewPanel, index: number) {
        const appCounterRef: IPSAppCounterRef = tabViewPanel.getPSAppCounterRef() as IPSAppCounterRef;
        let viewPanelCount: any = undefined;
        if (appCounterRef && tabViewPanel.counterId) {
            const targetCounterService: any = Util.findElementByField(this.counterServiceArray, 'id', appCounterRef.id)?.service;
            viewPanelCount = targetCounterService?.counterData?.[tabViewPanel.counterId.toLowerCase()]
        }
        const tabsName = `${this.appDeCodeName}_${this.viewInstance.codeName}_tabexpheader`;
        let disabled = this.authResourceObject && this.authResourceObject[tabViewPanel.name]?.disabled;
        const IPSSysImage: IPSSysImage = tabViewPanel.getPSSysImage() as IPSSysImage;
        return (
            <tab-pane lazy={true} name={tabViewPanel.name} tab={tabsName} disabled={disabled}
                label={(h: any) => {
                    return h('div', [
                        IPSSysImage ? IPSSysImage.imagePath ?
                            h('img', {
                                src: IPSSysImage.imagePath,
                                style: {
                                    'margin-right': '2px'
                                }
                            }) :
                            h('i', {
                                class: IPSSysImage.cssClass,
                                style: {
                                    'margin-right': '2px'
                                }
                            }) : '',
                        h('span', tabViewPanel.caption),
                        h('Badge', {
                            props: {
                                count: viewPanelCount,
                                type: 'primary'
                            }
                        })
                    ])
                }}>
            </tab-pane>
        )
    }

    /**
     * 绘制分页面板标题栏
     * 
     * @memberof TabSearchViewBase
     */
    public renderTabsHeader() {
        if (!this.tabsHeaderRenderState) {
            return;
        }
        const IPSDETabViewPanel = this.tabExpPanelInstance?.getPSControls() as IPSDETabViewPanel[];
        const tabsName = `${this.appDeCodeName}_${this.viewInstance.codeName}_tabexpheader`;
        return (
            <div class={{ 'tabviewpanel-header': true }} slot="tabsHeader" >
                <tabs value={this.activiedTabViewPanel} animated={false} class='tabexppanel' name={tabsName} on-on-click={($event: any) => this.tabPanelClick($event)}>
                    {
                        IPSDETabViewPanel?.map((tabViewPanel: IPSDETabViewPanel, index: number) => {
                            return this.authResourceObject && this.authResourceObject[tabViewPanel.name]?.visabled ?
                                this.renderTabPaneContent(tabViewPanel, index) : null
                        })
                    }
                </tabs>
            </div>
        );
    }

    /**
     * 部件事件处理
     * 
     * @memberof TabSearchViewBase
     */
    public onCtrlEvent(controlName: any, action: any, data: any) {
        super.onCtrlEvent(controlName, action, data);
        if (action == 'tabexppanelIsMounted') {
            this.initTabExpHeader(data);
        }
        if (action == 'viewPanelIsChange') {
            this.activiedTabViewPanel = data;
        }
    }
}