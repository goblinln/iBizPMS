import {
    AppModelService,
    GetModelService,
    LogUtil,
    MobWFDynaEditViewEngine,
    MobWFDynaEditViewInterface,
    ModelTool,
    Util,
} from 'ibiz-core';
import { GlobalService } from 'ibiz-service';
import { MainViewBase } from './main-view-base';
import { IPSAppDEMobWFDynaEditView, IPSAppView, IPSDEForm } from '@ibiz/dynamic-model-api';
import { AppCenterService } from '../app-service';

/**
 * 工作流动态编辑视图基类
 *
 * @export
 * @class MobWFDynaEditViewBase
 * @extends {MainViewBase}
 */

export class MobWFDynaEditViewBase extends MainViewBase
    implements MobWFDynaEditViewInterface {
    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof MobWFDynaEditViewBase
     */
    public engine: MobWFDynaEditViewEngine = new MobWFDynaEditViewEngine();

    /**
     * 视图实例
     *
     * @memberof MobWFDynaEditViewBase
     */
    public viewInstance!: IPSAppDEMobWFDynaEditView;

    /**
     * 编辑表单实例
     *
     * @public
     * @type {IBizFormModel}
     * @memberof EditViewBase
     */
    public editFormInstance!: IPSDEForm;

    /**
     * 工具栏模型数据
     *
     * @memberof MobWFDynaEditViewBase
     */
    public linkModel: Array<any> = [];

    /**
     * 工作流审批意见控件绑定值
     *
     * @memberof MobWFDynaEditViewBase
     */
    public srfwfmemo: string = '';

    /**
     * 激活表单
     *
     * @memberof MobWFDynaEditViewBase
     */
    public activeForm: any = {};

    /**
     * 视图引用数据
     *
     * @memberof MobWFDynaEditViewBase
     */
    public viewRefData: any = {};

    /**
     * 工作流按钮显示状态
     *
     * @memberof MobWFDynaEditView3Base
     */
    public toolBarVisible = false;

    /**
     * 所有表单数据
     *
     * @memberof MobWFDynaEditViewBase
     */
    public allForm: any = {
        // TODO
    };

    /**
     * 初始化所有表单数据
     *
     * @memberof MobWFDynaEditViewBase
     */
    public initAllForm() {
        // TODO
    }

    /**
     * 工作流附加功能类型映射关系对象
     *
     * @memberof MobWFDynaEditViewBase
     */
    public wfAddiFeatureRef: any = {
        reassign: { featureTag: 'REASSIGN', action: 'TransFerTask' },
        addstepbefore: { featureTag: 'ADDSTEPBEFORE', action: 'BeforeSign' },
        sendback: { featureTag: 'SENDBACK', action: 'SendBack' },
        sendcopy: { featureTag: 'SENDCOPY', action: 'sendCopy' },
    };

    /**
     * 工具栏分组是否显示的方法
     *
     * @memberof MobWFDynaEditViewBase
     */
    public toolBarVisibleChange() {
        this.toolBarVisible = !this.toolBarVisible;
    }

    /**
     * 初始化挂载状态集合
     *
     * @memberof WFDynaEditViewBase
     */
    public initMountedMap() {
        this.mountedMap.set('self', false);
    }

    /**
     * 设置已经绘制完成状态
     *
     * @memberof WFDynaEditViewBase
     */
    public setIsMounted(name: string = 'self') {
        super.setIsMounted(name);
        if (this.editFormInstance?.name == name) {
            this.viewState.next({
                tag: this.editFormInstance.name,
                action: 'autoload',
                data: {
                    srfkey: this.context[this.appDeCodeName.toLowerCase()],
                },
            });
        }
    }

    /**
     * 引擎初始化
     *
     * @public
     * @memberof MobWFDynaEditViewBase
     */
    public engineInit(opts: any = {}): void {
        if (this.Environment?.isPreviewMode) {
            return;
        }
        this.engine.init({
            view: this,
            parentContainer: this.$parent,
            keyPSDEField: this.appDeCodeName.toLowerCase(),
            majorPSDEField: this.appDeMajorFieldName.toLowerCase(),
            isLoadDefault: this.viewInstance.loadDefault,
        });
    }

    /**
     * 初始化编辑视图实例
     *
     * @memberof MobWFDynaEditViewBase
     */
    public async viewModelInit() {
        this.viewInstance = this.staticProps
            .modeldata as IPSAppDEMobWFDynaEditView;
        this.viewRefData = await ModelTool.loadedAppViewRef(this.viewInstance);
        await super.viewModelInit();
        this.appEntityService = await new GlobalService().getService(
            this.appDeCodeName
        );
        this.initAllForm();
    }

    /**
     * 初始化工具栏数据
     *
     * @memberof MobWFDynaEditView3Base
     */
    public renderToolBar() {
        return (
            <div class="wf-button-content">
                <div class="toolbar-container" on-click={() => { this.toolBarVisibleChange() }}>
                    <ion-fab-button>
                        <ion-icon name="chevron-back-circle-outline"></ion-icon>
                    </ion-fab-button>
                </div>
            </div>
        );
    }

    /**
     * 绘制工作流按钮
     *
     * @return {*} 
     * @memberof MobWFDynaEditView3Base
     */
    public renderToolBarInfo() {
        return (
            <van-action-sheet actions={this.linkModel} cancel-text="取消" class="wf-action-sheet" get-container="#app" value={this.toolBarVisible} on-select={(item: any) => { this.dynamic_toolbar_click(item), this.toolBarVisibleChange() }} on-cancel={() => { this.toolBarVisibleChange() }} on-click-overlay={() => { this.toolBarVisibleChange() }}>
            </van-action-sheet>
        )
    }

    /**
     * 绘制底部按钮
     *
     * @memberof MobWFDynaEditViewBase
     */
    public renderFooter() {
        return (
            <div class="fab_container" slot="footer">
                {
                    [
                        this.renderToolBar(),
                        this.renderToolBarInfo()
                    ]
                }
            </div>
        )
    }

    /**
     * 渲染视图主体内容区
     *
     * @memberof MobWFDynaEditViewBase
     */
    public renderMainContent() {
        if (!this.editFormInstance) {
            return;
        }
        let {
            targetCtrlName,
            targetCtrlParam,
            targetCtrlEvent,
        } = this.computeTargetCtrlData(this.editFormInstance);
        Object.assign(targetCtrlParam.staticProps, {
            isautoload: true,
        });
        return this.$createElement(targetCtrlName, {
            slot: 'default',
            props: targetCtrlParam,
            ref: this.editFormInstance.name,
            on: targetCtrlEvent,
        });
    }

    /**
     * 获取工具栏按钮
     *
     * @returns {Promise<any>}
     * @memberof MobWFDynaEditViewBase
     */
    public async getWFLinkModel(arg: any): Promise<any> {
        return new Promise((resolve: any, reject: any) => {
            let datas: any[] = [];
            if (Object.keys(this.viewparams).length > 0) {
                Object.assign(datas, { 'processDefinitionKey': this.viewparams.processDefinitionKey });
                Object.assign(datas, { 'taskDefinitionKey': this.viewparams.taskDefinitionKey });
            }
            if (arg && Object.keys(arg).length > 0) {
                Object.assign(datas, { activedata: arg });
            }
            this.appEntityService.GetWFLink(JSON.parse(JSON.stringify(this.context)), datas, true).then((response: any) => {
                if (response && response.status === 200) {
                    this.linkModel = response.data;
                    this.initLinkModel();
                }
            }).catch((response: any) => {

            });
        })
    }

    /**
     * 
     *
     * @memberof MobWFDynaEditView3Base
     */
    public initLinkModel() {
        this.linkModel.forEach((item: any) => {
            item.name = item.sequenceFlowName;
        })
    }

    /**
     * 动态工具栏点击
     *
     * @param {*} linkItem
     * @param {*} $event
     * @memberof MobWFDynaEditViewBase
     */
    /**
     * 动态工具栏点击
     *
     * @memberof MobWFDynaEditViewBase
     */
    public dynamic_toolbar_click(linkItem: any) {
        const _this: any = this;
        let datas: any[] = [];
        let xData: any = (this.$refs[this.editFormInstance.name] as any).ctrl;
        if (xData.getDatas && xData.getDatas instanceof Function) {
            datas = [...xData.getDatas()];
        }
        const submit: Function = (submitData: any, linkItem: any) => {
            xData.wfsubmit(submitData, linkItem).then((response: any) => {
                if (!response || response.status !== 200) {
                    return;
                }
                const { data: _data } = response;
                if (_this.viewdata) {
                    _this.$emit('view-event', {
                        viewName: this.viewInstance.name,
                        action: 'viewdataschange',
                        data: [{ ..._data }],
                    });
                    _this.$emit('view-event', {
                        viewName: this.viewInstance.name,
                        action: 'close',
                        data: null,
                    });
                } else {
                    this.closeView([{ ..._data }]);
                }
            });
        };
        const submitAction: Function = () => {
            if (linkItem && linkItem.sequenceflowmobview) {
                const targetViewRef: any = this.viewRefData.find(
                    (item: any) => {
                        return (
                            item.name ===
                            `WFACTION@${linkItem.sequenceflowmobview}`
                        );
                    }
                );
                if (targetViewRef) {
                    let tempContext: any = Util.deepCopy(_this.context);
                    if (datas && datas[0].srfkey) {
                        Object.assign(tempContext, {
                            [this.appDeCodeName.toLowerCase()]:
                                datas && datas[0].srfkey,
                        });
                    }
                    let tempViewParam: any = {
                        actionView: linkItem.sequenceflowmobview,
                        actionForm: linkItem.sequenceflowmobform,
                    };
                    Object.assign(tempContext, {
                        viewpath: targetViewRef?.getRefPSAppView?.path,
                    });
                    GetModelService(tempContext).then(
                        (modelService: AppModelService) => {
                            modelService
                                .getPSAppView(
                                    targetViewRef?.getRefPSAppView?.path
                                )
                                .then((viewResult: IPSAppView) => {
                                    _this.$appmodal.openModal(
                                        {
                                            viewname: 'app-view-shell',
                                            title: viewResult.title,
                                            height: viewResult.height,
                                            width: viewResult.width,
                                        },
                                        tempContext,
                                        tempViewParam
                                    ).then((result: any) => {
                                        if (
                                            !result ||
                                            !Object.is(result.ret, 'OK')
                                        ) {
                                            return;
                                        }
                                        let tempSubmitData: any = Util.deepCopy(
                                            datas[0]
                                        );
                                        if (result.datas && result.datas[0]) {
                                            const resultData: any =
                                                result.datas[0];
                                            if (
                                                Object.keys(resultData).length >
                                                0
                                            ) {
                                                let tempData: any = {};
                                                Object.keys(resultData).forEach(
                                                    (key: any) => {
                                                        if (
                                                            resultData[key] &&
                                                            key !== 'srfuf'
                                                        )
                                                            tempData[key] =
                                                                resultData[key];
                                                    }
                                                );
                                                Object.assign(
                                                    tempSubmitData,
                                                    tempData
                                                );
                                            }
                                        }
                                        submit([tempSubmitData], linkItem);
                                    });
                                });
                        }
                    );
                }
            } else {
                submit(datas, linkItem);
            }
        };
        if (linkItem && linkItem.type) {
            if (Object.is(linkItem.type, 'finish')) {
                submitAction();
            } else {
                this.handleWFAddiFeature(linkItem);
            }
        } else {
            submitAction();
        }
    }

    /**
     * 处理工作流辅助功能
     *
     * @memberof MobWFDynaEditViewBase
     */
    public handleWFAddiFeature(linkItem: any) {
        let featureTag: string = this.wfAddiFeatureRef[linkItem?.type]
            ?.featureTag;
        if (!featureTag) return;
        let targetViewRef: any = this.viewRefData.find((item: any) => {
            return item.name === `WFUTILACTION@${featureTag}`;
        });
        if (!targetViewRef) {
            LogUtil.warn(this.$t('app.warn.dynaViewNotFound'));
            return;
        }
        // 准备参数
        let datas: any[] = [];
        let xData: any = (this.$refs[this.editFormInstance.name] as any).ctrl;
        if (xData.getDatas && xData.getDatas instanceof Function) {
            datas = [...xData.getDatas()];
        }
        let tempContext: any = Util.deepCopy(this.context);
        // Object.assign(tempContext, {
        //     [this.appDeCodeName.toLowerCase()]: datas && datas[0].srfkey,
        // });
        let tempViewParam: any = {
            actionView: linkItem.sequenceflowmobview,
            actionForm: linkItem.sequenceflowmobform,
        };
        Object.assign(tempContext, {
            viewpath: targetViewRef?.getRefPSAppView?.path,
        });
        GetModelService(tempContext).then((modelService: AppModelService) => {
            modelService
                .getPSAppView(targetViewRef?.getRefPSAppView?.path)
                .then((viewResult: IPSAppView) => {
                    const appmodal = this.$appmodal.openModal(
                        {
                            viewname: 'app-view-shell',
                            title: viewResult.title,
                            height: viewResult.height,
                            width: viewResult.width,
                        },
                        tempContext,
                        tempViewParam
                    );
                    appmodal.subscribe((result: any) => {
                        if (!result || !Object.is(result.ret, 'OK')) {
                            return;
                        }
                        let tempSubmitData: any = Util.deepCopy(datas[0]);
                        if (result.datas && result.datas[0]) {
                            const resultData: any = result.datas[0];
                            if (Object.keys(resultData).length > 0) {
                                let tempData: any = {};
                                Object.keys(resultData).forEach((key: any) => {
                                    if (resultData[key] && key !== 'srfuf')
                                        tempData[key] = resultData[key];
                                });
                                Object.assign(tempSubmitData, tempData);
                            }
                            this.submitWFAddiFeature(linkItem, tempSubmitData);
                        }
                    });
                });
        });
    }

    /**
     * 提交工作流辅助功能
     *
     * @memberof MobWFDynaEditViewBase
     */
     public submitWFAddiFeature(linkItem: any, submitData: any) {
        let tempSubmitData: any = Object.assign(linkItem, {
            activedata: submitData,
        });
        let action: string = this.wfAddiFeatureRef[linkItem?.type]?.action;
        if (!action) return;
        this.appEntityService[action](
            Util.deepCopy(this.context),
            tempSubmitData
        )
            .then((response: any) => {
                const { data: data } = response;
                if (!response || response.status !== 200) {
                    this.$Notice.error(response);
                    return;
                }
                let _this: any = this;
                if (_this.viewdata) {
                    _this.$emit('view-event', {
                        viewName: this.viewInstance.name,
                        action: 'viewdataschange',
                        data: [{ ...data }],
                    });
                    _this.$emit('view-event', {
                        viewName: this.viewInstance.name,
                        action: 'close',
                        data: null,
                    });
                } else {
                    this.closeView([{ ...data }]);
                }
                AppCenterService.notifyMessage({
                    name: this.appDeCodeName,
                    action: 'appRefresh',
                    data: data,
                });
                this.$Notice.success(
                    data?.message
                        ? data.message
                        : this.$t('app.success.submitSuccess')
                );
            })
            .catch((error: any) => {
                this.$Notice.error(error);
            });
    }

    /**
     * 计算激活表单
     *
     * @memberof MobWFDynaEditViewBase
     */
    public computeActivedForm(inputForm: any) {
        if (!inputForm) {
            this.editFormInstance = ModelTool.findPSControlByName(
                'form',
                this.viewInstance.getPSControls()
            );
        } else {
            this.editFormInstance = ModelTool.findPSControlByName(
                `wfform_${inputForm.toLowerCase()}`,
                this.viewInstance.getPSControls()
            );
        }
        this.$forceUpdate();
    }

    /**
     * 获取动态表单模型
     *
     * @memberof WFDynaEditViewBase
     */
    public getFormModel(): Promise<any> {
        return new Promise((resolve: any, reject: any) => {
            let datas: any[] = [];
            if (Object.keys(this.viewparams).length > 0) {
                Object.assign(datas, {
                    processDefinitionKey: this.viewparams.processDefinitionKey,
                });
                Object.assign(datas, {
                    taskDefinitionKey: this.viewparams.taskDefinitionKey,
                });
            }
            this.appEntityService
                .getWFStep(
                    JSON.parse(JSON.stringify(this.context)),
                    datas,
                    true
                )
                .then((response: any) => {
                    if (response && response.status === 200) {
                        const data = response.data;
                        if (data && data['process-mobform']) {
                            this.computeActivedForm(data['process-mobform']);
                        } else {
                            this.computeActivedForm(null);
                        }
                    }
                })
                .catch((response: any) => {
                    console.error(response);
                });
        });
    }
}
