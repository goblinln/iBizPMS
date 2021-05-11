import { IPSAppDEWFDynaEditView, IPSAppView, IPSDEDRTab, IPSDEDRTabPage, IPSDEForm } from '@ibiz/dynamic-model-api';
import { WFDynaEditViewEngine, Util, ModelTool, GetModelService, AppModelService } from 'ibiz-core';
import { AppCenterService } from '../app-service';
import { MainViewBase } from './mainview-base';

/**
 * 工作流动态编辑视图3基类
 *
 * @export
 * @class WFDynaEditView3Base
 * @extends {MainViewBase}
 */
export class WFDynaEditView3Base extends MainViewBase {

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof WFDynaEditView3Base
     */
    public engine: WFDynaEditViewEngine = new WFDynaEditViewEngine();

    /**
     * 视图实例
     * 
     * @memberof WFDynaEditView3Base
     */
    public viewInstance!: IPSAppDEWFDynaEditView;

    /**
     * 编辑表单实例
     *
     * @public
     * @type {IBizFormModel}
     * @memberof WFDynaEditView3Base
     */
    public editFormInstance !: IPSDEForm;

    /**
     * 数据关系分页部件实例
     *
     * @public
     * @type {IPSDEDRTab}
     * @memberof WFDynaEditView3Base
     */
    public drtabInstance !: IPSDEDRTab;

    /**
     * 工具栏模型数据
     * 
     * @memberof WFDynaEditView3Base                
     */
    public linkModel: Array<any> = [];

    /**
     * 视图引用数据
     * 
     * @memberof WFDynaEditView3Base                
     */
    public viewRefData: any = {};

    /**
     * 关系数据分页部件分页
     * 
     * @type {IPSDEDRTabPage[] | null}
     * @memberof WFDynaEditView3Base
     */
    public deDRTabPages: IPSDEDRTabPage[] | null = [];

    /**
     * 工作流附加功能类型映射关系对象
     * 
     * @memberof WFDynaEditView3Base                
     */
    public wfAddiFeatureRef: any = {
        "reassign": { featureTag: "REASSIGN", action: "TransFerTask" },
        "addstepbefore": { featureTag: "ADDSTEPBEFORE", action: "BeforeSign" },
        "sendback": { featureTag: "SENDBACK", action: "SendBack" },
        "sendcopy": { featureTag: "SENDCOPY", action: "sendCopy" }
    };

    /**
     * 引擎初始化
     *
     * @public
     * @memberof WFDynaEditView3Base
     */
    public engineInit(): void {
        if (this.Environment && this.Environment.isPreviewMode) {
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
     * 初始化挂载状态集合
     *
     * @memberof WFDynaEditView3Base
     */
    public initMountedMap() {
        this.mountedMap.set('self', false);
    }

    /**
     * 设置已经绘制完成状态
     *
     * @memberof WFDynaEditView3Base
     */
    public setIsMounted(name: string = 'self') {
        super.setIsMounted(name);
        if (this.editFormInstance?.name == name) {
            this.viewState.next({ tag: this.editFormInstance.name, action: 'autoload', data: { srfkey: this.context[this.appDeCodeName.toLowerCase()] } });
        }
    }

    /**
     * 初始化编辑视图实例
     * 
     * @memberof WFDynaEditView3Base
     */
    public async viewModelInit() {
        await super.viewModelInit();
        this.viewRefData = await ModelTool.loadedAppViewRef(this.viewInstance);
        this.drtabInstance = ModelTool.findPSControlByName('drtab', this.viewInstance.getPSControls()) as IPSDEDRTab;
        this.deDRTabPages = this.drtabInstance?.getPSDEDRTabPages();
    }

    /**
     * 初始化工具栏数据 
     * 
     * @memberof WFDynaEditView3Base
     */
    public renderToolBar() {
        return (
            <div slot="toolbar">
                <div class='toolbar-container'>
                    {
                        this.linkModel.map((linkItem: any) => {
                            return (
                                <tooltip transfer={true} max-width={600} >
                                    <i-button on-click={(event: any) => { this.dynamic_toolbar_click(linkItem, event) }} loading={this.viewLoadingService.isLoading}>
                                        <span class='caption'>{linkItem.sequenceFlowName}</span>
                                    </i-button>
                                    <div slot='content'>{linkItem.sequenceFlowName}</div>
                                </tooltip>
                            )
                        })
                    }
                </div >
            </div >
        )
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof WFDynaEditView3Base
     */
    public renderMainContent() {
        if (!this.editFormInstance) {
            return;
        }
        if (this.deDRTabPages && this.deDRTabPages.length > 0) {
            const tempContext = Util.deepCopy(this.context);
            const tabsName = `${this.appDeCodeName?.toLowerCase()}_${this.viewInstance.codeName.toLowerCase()}`;
            return (
                <tabs animated={false} name={tabsName} class="workflow-tabs-container">
                    <tab-pane tab={tabsName} label={this.drtabInstance.M?.editItemCaption ? this.drtabInstance.M?.editItemCaption : this.editFormInstance.logicName}>
                        {this.renderFormContent()}
                    </tab-pane>
                    {this.deDRTabPages.map((deDRTabPage: IPSDEDRTabPage) => {
                        return (
                            <tab-pane tab={tabsName} label={deDRTabPage.caption}>
                                { this.$createElement('app-view-shell', {
                                    props: {
                                        staticProps: {
                                            viewDefaultUsage: false,
                                            appDeCodeName: this.appDeCodeName
                                        },
                                        dynamicProps: {
                                            viewdata: JSON.stringify(Object.assign(tempContext, { viewpath: deDRTabPage?.M?.getPSAppView?.path })),
                                            viewparam: JSON.stringify(this.viewparams),
                                        },
                                    }
                                })}
                            </tab-pane>
                        )
                    })}
                </tabs>
            )
        } else {
            return this.renderFormContent();
        }
    }

    /**
     * 渲染流程表单内容区
     * 
     * @memberof WFDynaEditView3Base
     */
    public renderFormContent() {
        if (!this.editFormInstance) {
            return;
        }
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.editFormInstance);
        Object.assign(targetCtrlParam.staticProps, {
            isautoload: true
        });
        return (
            this.$createElement(targetCtrlName, { slot: 'default', props: targetCtrlParam, ref: this.editFormInstance.name, on: targetCtrlEvent })
        );
    }

    /**
     * 获取动态表单模型
     * 
     * @memberof WFDynaEditView3Base                
     */
    public getFormModel(): Promise<any> {
        return new Promise((resolve: any, reject: any) => {
            let datas: any[] = [];
            if (Object.keys(this.viewparams).length > 0) {
                Object.assign(datas, { 'processDefinitionKey': this.viewparams.processDefinitionKey });
                Object.assign(datas, { 'taskDefinitionKey': this.viewparams.taskDefinitionKey });
            }
            this.appEntityService.getWFStep(JSON.parse(JSON.stringify(this.context)), datas, true).then((response: any) => {
                if (response && response.status === 200) {
                    const data = response.data;
                    if (data && data['process-form']) {
                        this.computeActivedForm(data['process-form']);
                    } else {
                        this.computeActivedForm(null);
                    }
                }
            }).catch((response: any) => {
                this.$throw(response);
            });
        });
    }

    /**
     * 获取工具栏按钮
     * 
     * @memberof WFDynaEditView3Base                
     */
    public getWFLinkModel(arg: any): Promise<any> {
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
                }
            }).catch((response: any) => {
                this.$throw(response);
            });
        })
    }

    /**
     * 计算激活表单
     * 
     * @memberof WFDynaEditView3Base                
     */
    public computeActivedForm(inputForm: any) {
        if (!inputForm) {
            this.editFormInstance = ModelTool.findPSControlByName('form', this.viewInstance.getPSControls()) as IPSDEForm;
        } else {
            this.editFormInstance = ModelTool.findPSControlByName(`wfform_${inputForm.toLowerCase()}`, this.viewInstance.getPSControls()) as IPSDEForm;
        }
        this.mountedMap.set(this.editFormInstance.name, false);
        this.$forceUpdate();
    }

    /**
     * 动态工具栏点击
     * 
     * @memberof WFDynaEditView3Base                
     */
    public dynamic_toolbar_click(linkItem: any, $event: any) {
        const _this: any = this
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
                    _this.$emit('view-event', { viewName: this.viewInstance.name, action: 'viewdataschange', data: [{ ..._data }] });
                    _this.$emit('view-event', { viewName: this.viewInstance.name, action: 'close', data: null });
                } else {
                    this.closeView([{ ..._data }]);
                }
            });
        }
        const submitAction: Function = () => {
            if (linkItem && linkItem.sequenceflowview) {
                const targetViewRef: any = this.viewRefData.find((item: any) => {
                    return item.name === `WFACTION@${linkItem.sequenceflowview}`;
                })
                if (targetViewRef) {
                    let tempContext: any = Util.deepCopy(_this.context);
                    Object.assign(tempContext, { [this.appDeCodeName.toLowerCase()]: datas && datas[0].srfkey });
                    let tempViewParam: any = { actionView: linkItem.sequenceflowview, actionForm: linkItem.sequenceflowform };
                    Object.assign(tempContext, { viewpath: targetViewRef?.getRefPSAppView?.path });
                    GetModelService(tempContext).then((modelService: AppModelService) => {
                        modelService.getPSAppView(targetViewRef?.getRefPSAppView?.path).then((viewResult: IPSAppView) => {
                            const appmodal = _this.$appmodal.openModal({ viewname: 'app-view-shell', title: viewResult.title, height: viewResult.height, width: viewResult.width }, tempContext, tempViewParam);
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
                                            if (resultData[key] && (key !== "srfuf")) tempData[key] = resultData[key];
                                        })
                                        Object.assign(tempSubmitData, tempData);
                                    }
                                }
                                submit([tempSubmitData], linkItem);
                            });
                        })
                    })
                }
            } else {
                submit(datas, linkItem);
            }
        }
        if (linkItem && linkItem.type) {
            if (Object.is(linkItem.type, "finish")) {
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
     * @memberof WFDynaEditView3Base                
     */
    public handleWFAddiFeature(linkItem: any) {
        let featureTag: string = this.wfAddiFeatureRef[linkItem?.type]?.featureTag;
        if (!featureTag) return;
        let targetViewRef: any = this.viewRefData.find((item: any) => {
            return item.name === `WFUTILACTION@${featureTag}`;
        })
        if (!targetViewRef) {
            console.warn("未找到流程功能操作视图");
            return;
        }
        // 准备参数
        let datas: any[] = [];
        let xData: any = (this.$refs[this.editFormInstance.name] as any).ctrl;
        if (xData.getDatas && xData.getDatas instanceof Function) {
            datas = [...xData.getDatas()];
        }
        let tempContext: any = Util.deepCopy(this.context);
        Object.assign(tempContext, { [this.appDeCodeName.toLowerCase()]: datas && datas[0].srfkey });
        let tempViewParam: any = { actionView: linkItem.sequenceflowview, actionForm: linkItem.sequenceflowform };
        Object.assign(tempContext, { viewpath: targetViewRef?.getRefPSAppView?.path });
        GetModelService(tempContext).then((modelService: AppModelService) => {
            modelService.getPSAppView(targetViewRef?.getRefPSAppView?.path).then((viewResult: IPSAppView) => {
                const appmodal = this.$appmodal.openModal({ viewname: 'app-view-shell', title: viewResult.title, height: viewResult.height, width: viewResult.width }, tempContext, tempViewParam);
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
                                if (resultData[key] && (key !== "srfuf")) tempData[key] = resultData[key];
                            })
                            Object.assign(tempSubmitData, tempData);
                        }
                        this.submitWFAddiFeature(linkItem, tempSubmitData);
                    }
                });
            })
        })
    }

    /**
     * 提交工作流辅助功能
     * 
     * @memberof WFDynaEditView3Base                
     */
    public submitWFAddiFeature(linkItem: any, submitData: any) {
        let tempSubmitData: any = Object.assign(linkItem, { "activedata": submitData });
        let action: string = this.wfAddiFeatureRef[linkItem?.type]?.action;
        if (!action) return;
        this.appEntityService[action](Util.deepCopy(this.context), tempSubmitData).then((response: any) => {
            const { data: data } = response;
            if (!response || response.status !== 200) {
                this.$throw(response);
                return;
            }
            let _this: any = this;
            if (_this.viewdata) {
                _this.$emit('view-event', { viewName: this.viewInstance.name, action: 'viewdataschange', data: [{ ...data }] });
                _this.$emit('view-event', { viewName: this.viewInstance.name, action: 'close', data: null });
            } else {
                this.closeView([{ ...data }]);
            }
            AppCenterService.notifyMessage({ name: this.appDeCodeName, action: 'appRefresh', data: data });
            this.$Notice.success({ title: '成功', desc: data?.message ? data.message : '提交数据成功' });
        }).catch((error: any) => {
            this.$throw(error);
        })
    }

    /**
     * 将待办任务标记为已读
     * 
     * @param data 业务数据
     * @memberof WFDynaEditView3Base                
     */
    public readTask(data: any) {
        this.appEntityService.ReadTask(this.context, data).then((response: any) => {
            if (!response || response.status !== 200) {
                console.warn("将待办任务标记为已读失败");
                return;
            }
            AppCenterService.notifyMessage({ name: this.appDeCodeName, action: 'appRefresh', data: data });
        }).catch((error: any) => {
            console.warn("将待办任务标记为已读失败");
        })
    }
}
