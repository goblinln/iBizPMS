import { MobTabExpViewEngine, Util, ModelTool} from "ibiz-core";
import { CounterServiceRegister } from "ibiz-service";
import { MainViewBase } from "./main-view-base";
import { GlobalService } from 'ibiz-service';
import { IPSAppDETabExplorerView, IPSTabExpPanel, IPSDETabViewPanel } from '@ibiz/dynamic-model-api';

/**
 * 分页导航视图基类
 *
 * @export
 * @class MobMobTabExpViewBase
 * @extends {MainViewBase}
 */
export class MobTabExpViewBase extends MainViewBase {

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof MobTabExpViewBase
     */
    public engine: MobTabExpViewEngine = new MobTabExpViewEngine();

    /**
     * 视图实例
     * 
     * @memberof MobTabExpViewBase
     */
    public viewInstance !: IPSAppDETabExplorerView;

    /**
     * 导航栏实例
     *
     * @public
     * @type {IBizMobTabExpPanelModel}
     * @memberof MobTabExpViewBase
     */
    public tabExpPanelInstance !: IPSTabExpPanel;

    /**
     * 引擎初始化
     *
     * @public
     * @memberof MobTabExpViewBase
     */
    public engineInit(opts: any = {}): void {
        if (this.Environment?.isPreviewMode) {
            return;
        }
        this.engine.init({
            view: this,
            parentContainer: this.$parent,
            form: (this.$refs[this.tabExpPanelInstance.name] as any).ctrl,
            p2k: '0',
            keyPSDEField: this.appDeCodeName.toLowerCase(),
            majorPSDEField: this.appDeMajorFieldName.toLowerCase(),
            isLoadDefault: this.staticProps.hasOwnProperty('isLoadDefault') ? this.staticProps.isLoadDefault : true,
        });
    }

    /**
     * 当前激活的分页视图面板
     *
     * @memberof MobTabExpViewBase
     */
    public activiedTabViewPanel = '';

    /**
     * 视图初始化
     *
     * @memberof MainViewBase
     */
    public viewInit() {
        super.viewInit();
        const allControls = this.tabExpPanelInstance.getPSControls() as IPSDETabViewPanel[];
        if (allControls?.length > 0) {
            this.activiedTabViewPanel = allControls[0].name;
        }
    }

    /**
     * 分页导航栏激活
     *
     * @param {*} $event
     * @returns {void}
     * @memberof ${srfclassname('${view.name}')}Base
     */
    public tabExpPanelChange($event: any): void {
        let { detail } = $event;
        if (!detail) {
            return;
        }
        let { value } = detail;
        if (!value) {
            return;
        }
        this.viewState.next({ tag: 'tabexppanel', action: 'active', data: { activeItem: value } });
    }

    /**
     * 初始化编辑视图实例
     * 
     * @memberof MobTabExpViewBase
     */
    public async viewModelInit() {
        this.viewInstance = (this.staticProps.modeldata) as IPSAppDETabExplorerView;
        this.tabExpPanelInstance = ModelTool.findPSControlByName('tabexppanel', this.viewInstance.getPSControls()) as IPSTabExpPanel;
        await super.viewModelInit();
        this.initViewToolBar();
        if (!(this.Environment?.isPreviewMode)) {
            this.appEntityService = await new GlobalService().getService(this.appDeCodeName);
        }
    }


    /**
     * 加载模型
     * 
     * @memberof TabExpviewBase
     */
    public loadModel() {
        const appDataEntity = this.viewInstance.getPSAppDataEntity();
        let _this: any = this;
        if (this.context[appDataEntity?.codeName?.toLowerCase() as string]) {
            this.appEntityService.getDataInfo(JSON.parse(JSON.stringify(this.context)), {}, false).then((response: any) => {
                if (!response || response.status !== 200) {
                    return;
                }
                const { data: _data } = response;
                const majorField = ModelTool.getAppEntityMajorField(appDataEntity);
                if (_data[majorField?.codeName.toLowerCase() as string]) {
                    this.model.dataInfo = _data[majorField?.codeName.toLowerCase() as string];
                    if (_this.$route) {
                        _this.$route.meta.info = _this.model.dataInfo;
                    }
                }
                this.initNavCaption();
            })
        }
    }

    /**
     * 绘制视图头部
     *
     * @returns
     * @memberof MobTabExpViewBase
     */
    public readerViewHeadersSegment() {
        const segment = this.tabExpPanelInstance.getPSControls() as IPSDETabViewPanel[];
        return (
            <ion-toolbar slot="segment">
                <ion-segment scrollable={true} value={this.activiedTabViewPanel} on-ionChange={($event: any) => { this.tabExpPanelChange($event) }}>
                    {
                        segment?.map((item: IPSDETabViewPanel) => {
                            let viewPanelCount: any = undefined;
                            if (item?.counterId) {
                                const targetCounterService: any = Util.findElementByField(this.counterServiceArray, 'id', item.getPSAppCounterRef()?.id)?.service;
                                viewPanelCount = targetCounterService?.counterData?.[item.counterId.toLowerCase()]
                            }
                            return <ion-segment-button value={item.name} layout="icon-start">
                                {item?.getPSSysImage()?.cssClass ? <app-mob-icon name={item.getPSSysImage()?.cssClass}></app-mob-icon> : null}
                                {item.caption}

                                {viewPanelCount ? viewPanelCount > 99 ? <div class="segmentCount" color="danger">99+</div> : <div class="segmentCount" color="danger">{viewPanelCount}</div> : null}
                            </ion-segment-button>
                        })
                    }
                </ion-segment>
            </ion-toolbar>
        )
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof AppDefaultEditView
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.tabExpPanelInstance);
        targetCtrlParam.dynamicProps.activiedTabViewPanel = this.activiedTabViewPanel;
        return this.$createElement(targetCtrlName, { slot: 'default', props: targetCtrlParam, ref: this.tabExpPanelInstance.name, on: targetCtrlEvent });
    }


    /**
     * 初始化计数器服务
     * 
     * @memberof ViewBase
     */
    public async initCounterService(param: any) {
        const appCounterRef =  this.tabExpPanelInstance.getPSAppCounterRefs();
        if (appCounterRef && appCounterRef.length > 0) {
            for (const counterRef of appCounterRef) {
                if(counterRef?.getPSAppCounter()){
                    const path = counterRef.getPSAppCounter()?.modelPath;
                    const targetCounterService: any = await CounterServiceRegister.getInstance().getService({ context: this.context, viewparams: this.viewparams }, path);
                    const tempData: any = { id: counterRef.id, path:path, service: targetCounterService };
                    this.counterServiceArray.push(tempData);
                }
            }
        }
    }
}
