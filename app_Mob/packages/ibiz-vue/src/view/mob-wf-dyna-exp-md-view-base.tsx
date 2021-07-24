import { MobWFDynaExpMDViewEngine, Util, ModelTool } from 'ibiz-core';
import { GlobalService } from 'ibiz-service';
import { MDViewBase } from './md-view-base';
import { IPSAppDEMobWFDynaExpMDView, IPSDEMobMDCtrl } from '@ibiz/dynamic-model-api';

/**
 * 工作流动态导航多数据视图基类
 *
 * @export
 * @class MobWFDynaExpMdViewBase
 * @extends {ExpViewBase}
 */
export class MobWFDynaExpMdViewBase extends MDViewBase {

    /**
     * 视图实例
     * 
     * @memberof MobWFDynaExpMdViewBase
     */
    public viewInstance!: IPSAppDEMobWFDynaExpMDView;

    /**
     * 列表实例
     * 
     * @memberof MobWFDynaExpMdViewBase
     */
    public mdCtrlInstance!: IPSDEMobMDCtrl;

    /**
     * 工具栏模型数据
     * 
     * @memberof WfDynaExpGridViewBase
     */
    public linkModel: Array<any> = [];

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof MobWFDynaExpMdViewBase
     */
    public engine: MobWFDynaExpMDViewEngine = new MobWFDynaExpMDViewEngine();

    /**
     * 引擎初始化
     *
     * @public
     * @memberof MobWFDynaExpMdViewBase
     */
    public engineInit(opts: any = {}): void {
        if (this.Environment?.isPreviewMode) {
            return;
        }
        const mdctrl = ModelTool.findPSControlByName('mdctrl',this.viewInstance.getPSControls());
        const searchform = ModelTool.findPSControlByName('searchform',this.viewInstance.getPSControls());
        const engineOpts: any = ({
            view: this,
            parentContainer: this.$parent,
            p2k: '0',
            mdctrl: (this.$refs[mdctrl?.name] as any)?.ctrl,
            opendata: (args: any[], fullargs?: any[], params?: any, $event?: any, xData?: any) => {
                this.opendata(args, fullargs, params, $event, xData);
            },
            newdata: (args: any[], fullargs?: any[], params?: any, $event?: any, xData?: any) => {
                this.newdata(args, fullargs, params, $event, xData);
            },
            keyPSDEField: this.appDeCodeName.toLowerCase(),
            majorPSDEField: this.appDeMajorFieldName.toLowerCase(),
            isLoadDefault: this.viewInstance.loadDefault,
        });
        if (searchform?.name) {
            engineOpts.searchform = ((this.$refs[searchform.name] as any).ctrl);
        }
        this.engine.init(engineOpts);
    }

    /**
     * 初始化分页导航视图实例
     * 
     * @memberof MobWFDynaExpMdViewBase
     */
    public async viewModelInit() {
        this.viewInstance = (this.staticProps.modeldata) as IPSAppDEMobWFDynaExpMDView;
        this.viewRefData = await ModelTool.loadedAppViewRef(this.viewInstance);
        await super.viewModelInit();
        this.mdCtrlInstance = ModelTool.findPSControlByName('mdctrl',this.viewInstance.getPSControls());
        this.appEntityService = await new GlobalService().getService(this.appDeCodeName);
    }

    /**
     * 渲染视图主题内容
     * 
     * @memberof MobWFDynaExpMdViewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent }: { targetCtrlName: string, targetCtrlParam: any, targetCtrlEvent: any } = this.computeTargetCtrlData(this.mdCtrlInstance);
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.mdCtrlInstance?.name, on: targetCtrlEvent });
    }

    /**
     * 多数据特殊处理content内容
     * 
     * @memberof MobMDViewBase
     */
    public renderViewContent() {
        return <ion-content ref="ionScroll" slot="ioncontent" scroll-events={true} on-ionScroll={this.onScroll.bind(this)} on-ionScrollEnd={this.onScrollEnd.bind(this)}>
            {this.renderBodyMessage()}
            {this.renderMainContent()}
        </ion-content>
    }

    /**
     * 渲染视图头部按钮
     * 
     * @memberof MobWFDynaExpMdViewBase
     */
    public renderExpMdViewToolbar() {
        if (this.wfStepModel.length === 0) {
            return null
        }
        return <ion-toolbar slot="expmdviewtoolbar">
            <ion-segment scrollable={true} value={this.selectTabId} on-ionChange={this.tab_click.bind(this)}>
                {this.wfStepModel.map((item: any) => {
                    return <ion-segment-button value={item.userTaskId}>
                        <ion-label>{item.userTaskName}</ion-label>
                    </ion-segment-button>
                })}
            </ion-segment>
        </ion-toolbar>
    }

    /**
     * 计算目标部件所需参数
     *
     * @param {string} [controlType]
     * @returns
     * @memberof MobWFDynaExpMdViewBase
     */
    public computeTargetCtrlData(controlInstance: any) {
        const { targetCtrlName, targetCtrlParam, targetCtrlEvent } = super.computeTargetCtrlData(controlInstance);
        if (controlInstance?.controlType == 'SEARCHFORM') {
            Object.assign(targetCtrlParam.staticProps, {
                isExpandSearchForm: this.isExpandSearchForm
            });
        } else {
            Object.assign(targetCtrlParam.staticProps, {
                opendata: this.opendata,
                newdata: this.newdata,
            });
        }
        return { targetCtrlName: targetCtrlName, targetCtrlParam: targetCtrlParam, targetCtrlEvent: targetCtrlEvent };
    }

    /**
     * 视图引用数据
     * 
     * @memberof MobWFDynaExpMdViewBase
     */
    public viewRefData: any = {}

    /**
     * 当前选中tab
     *
     * @type {string}
     * @memberof MobWFDynaExpMdViewBase
     */
    public selectTabId: string = "";

    /**
     * tab切换
     *
     * @memberof MobWFDynaExpMdViewBase
     */
    public tab_click(item: any) {
        const { detail } = item;
        if (!detail) {
            return
        }
        const { value } = detail;
        const selectValue: any = this.wfStepModel.find((item: any) => { return item.userTaskId === value });
        this.curSelectedNode = selectValue;
        Object.assign(this.viewparams,
            {
                'userTaskId': this.curSelectedNode['userTaskId'],
                'processDefinitionKey': this.curSelectedNode['processDefinitionKey'],
            }
        );
        (this.$refs[this.mdCtrlInstance.name] as any).ctrl.load(this.viewparams);
        this.selectTabId = this.curSelectedNode.userTaskId;
    }

    /**
     * 树导航栏数据
     *
     * @type {any}
     * @memberof MobWFDynaExpMdViewBase
     */
    public wfStepModel: Array<any> = [];

    /**
     * 当前选中节点
     *
     * @type {any}
     * @memberof  MobWFDynaExpMdViewBase
     */
    public curSelectedNode: any;

    /**
     * 获取树导航栏数据
     *
     * @returns {Promise<any>}
     * @memberof MobWFDynaExpMdViewBase
     */
    public async getWFStepModel(): Promise<any> {
        const _this = this;
        return new Promise((resolve: any, reject: any) => {
            _this.appEntityService?.WFGetWFStep().then((response: any) => {
                if (response && response.status === 200) {
                    _this.wfStepModel = response.data;
                    if (_this.wfStepModel && _this.wfStepModel.length > 0) {

                        if (!_this.curSelectedNode) {
                            _this.curSelectedNode = _this.wfStepModel[0];
                        } else {
                            let tempCopySelectedNode: any = Util.deepCopy(_this.curSelectedNode);
                            _this.curSelectedNode = _this.wfStepModel.find((item: any) => {
                                return item.userTaskId === tempCopySelectedNode.userTaskId && item.processDefinitionKey === tempCopySelectedNode.processDefinitionKey;
                            })
                        }
                    }
                    this.selectTabId = _this.curSelectedNode.userTaskId;
                    if (_this.curSelectedNode) {
                        Object.assign(_this.viewparams, { 'userTaskId': _this.curSelectedNode['userTaskId'], 'processDefinitionKey': _this.curSelectedNode['processDefinitionKey'] });
                        _this.getWFLinkModel({ 'userTaskId': _this.curSelectedNode['userTaskId'], 'processDefinitionKey': _this.curSelectedNode['processDefinitionKey'] });
                    }
                    resolve(response.data);
                }
            }).catch((response: any) => {
                if (response && response.status) {
                    _this.$Notice.error(response.message);
                    return;
                }
                if (!response || !response.status || !response.data) {
                    _this.$Notice.error(this.$t('app.commonWords.sysException'));
                    return;
                }
            });
        })
    }

    /**
     * 快速搜索值
     *
     * @memberof MobWFDynaExpMdViewBase
     */
    public quickValue = "";

    /**
     * 快速搜索
     *
     * @memberof MobWFDynaExpMdViewBase
     */
    public async quickValueChange(event: any) {
        const mdctrl: any = (this.$refs[this.mdCtrlInstance?.name] as any)?.ctrl;
        if (mdctrl) {
            mdctrl.quickSearch(this.quickValue);
        }
    }

    /**
     * 获取工具栏按钮
     * 
     * @memberof WfDynaExpGridViewBase
     */
    public getWFLinkModel(data: any) {
        this.appEntityService?.getWFLinks(JSON.parse(JSON.stringify(this.context)), data, true).then((response: any) => {
            if (response && response.status === 200) {
                this.linkModel = response.data;
                if (this.linkModel.length > 0) {
                    this.linkModel.forEach((item: any) => {
                        item.disabled = true;
                    })
                }
            }
        }).catch((response: any) => {
            if (response && response.status) {
                this.$Notice.error(response.message);
                return;
            }
            if (!response || !response.status || !response.data) {
                this.$Notice.error(this.$t('app.commonWords.sysException'));
                return;
            }
        });
    }
}