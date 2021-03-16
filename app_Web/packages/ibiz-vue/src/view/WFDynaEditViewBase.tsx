import { WFDynaEditViewEngine, IBizWFDynaEditViewModel, Util, IBizToolBarItemModel, IBizFormModel } from 'ibiz-core';
import { GlobalService } from 'ibiz-service';
import { MainViewBase } from './MainViewBase';

/**
 * 工作流动态编辑视图基类
 *
 * @export
 * @class WFDynaEditViewBase
 * @extends {MainViewBase}
 */
export class WFDynaEditViewBase extends MainViewBase {

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof WFDynaEditViewBase
     */
    public engine: WFDynaEditViewEngine = new WFDynaEditViewEngine();

    /**
     * 视图实例
     * 
     * @memberof WFDynaEditViewBase
     */
    public viewInstance!: IBizWFDynaEditViewModel;

    /**
     * 编辑表单实例
     *
     * @public
     * @type {IBizFormModel}
     * @memberof EditViewBase
     */
    public editFormInstance !: IBizFormModel;

    /**
     * 工具栏模型数据
     * 
     * @memberof WFDynaEditViewBase                
     */
    public linkModel: Array<any> = [];

    /**
     * 视图引用数据
     * 
     * @memberof WFDynaEditViewBase                
     */
    public viewRefData: any = {};

    /**
     * 引擎初始化
     *
     * @public
     * @memberof WFDynaEditViewBase
     */
    public engineInit(): void {
        this.engine.init({
            view: this,
            parentContainer: this.$parent,
            keyPSDEField: (this.viewInstance.appDataEntity.codeName).toLowerCase(),
            majorPSDEField: (this.viewInstance.appDataEntity.majorField.codeName).toLowerCase(),
            isLoadDefault: this.viewInstance.loadDefault,
        });
    }

    /**
     * 初始化编辑视图实例
     * 
     * @memberof WFDynaEditViewBase
     */
    public async viewModelInit() {
        this.viewInstance = new IBizWFDynaEditViewModel(this.staticProps.modeldata, this.context);
        await this.viewInstance.loaded();
        this.viewRefData = await this.viewInstance.loadedAppViewRef();
        await super.viewModelInit();
        this.appEntityService = await new GlobalService().getService(this.viewInstance.appDataEntity.codeName);
    }

    /**
     * 初始化工具栏数据 
     * 
     * @memberof WFDynaEditViewBase
     */
    public renderToolBar() {
        return (
            <div slot="toolbar">
                <div class='toolbar-container'>
                    {
                        this.linkModel.map((linkItem: any) => {
                            return (
                                <tooltip transfer={true} max-width={600} >
                                    <i-button on-click={(event: any) => { this.dynamic_toolbar_click(linkItem, event) }}>
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
     * @memberof WFDynaEditViewBase
     */
    public renderMainContent() {
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
     * 获取工具栏按钮
     * 
     * @memberof WFDynaEditViewBase                
     */
    public getWFLinkModel(): Promise<any> {
        return new Promise((resolve: any, reject: any) => {
            let datas: any[] = [];
            if (Object.keys(this.viewparams).length > 0) {
                Object.assign(datas, { 'processDefinitionKey': this.viewparams.processDefinitionKey });
                Object.assign(datas, { 'taskDefinitionKey': this.viewparams.taskDefinitionKey });
            }
            this.appEntityService.GetWFLink(JSON.parse(JSON.stringify(this.context)), datas, true).then((response: any) => {
                if (response && response.status === 200) {
                    this.linkModel = response.data;
                    if (response.headers && response.headers['process-form']) {
                        this.computeActivedForm(response.headers['process-form']);
                    } else {
                        this.computeActivedForm(null);
                    }
                    resolve(response.data);
                }
            }).catch((response: any) => {
                if (response && response.status) {
                    this.$Notice.error({ title: '错误', desc: response.message });
                    return;
                }
                if (!response || !response.status || !response.data) {
                    this.$Notice.error({ title: '错误', desc: '系统异常' });
                    return;
                }
            });
        });
    }

    /**
     * 计算激活表单
     * 
     * @memberof WFDynaEditViewBase                
     */
    public computeActivedForm(inputForm: any) {
        if (!inputForm) {
            this.editFormInstance = this.viewInstance.getControl('form');
        } else {
            this.editFormInstance = this.viewInstance.getControl(`wfform_${inputForm.toLowerCase()}`);
        }
        setTimeout(() => {
            this.viewState.next({ tag: this.editFormInstance.name, action: 'autoload', data: { srfkey: this.context[this.viewInstance.appDeCodeName.toLowerCase()] } });
        }, 0);
    }

    /**
     * 动态工具栏点击
     * 
     * @memberof WFDynaEditViewBase                
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
                } else if (_this.$tabPageExp) {
                    _this.$tabPageExp.onClose(_this.$route.fullPath);
                }
            });
        }
        const { appDataEntity } = this.viewInstance;
        if (linkItem && linkItem.sequenceflowview) {
            const targetView: any = this.viewRefData.find((item: any) => {
                return item.name === `WFACTION@${linkItem.sequenceflowview}`;
            })
            if (targetView) {
                let tempContext: any = Util.deepCopy(_this.context);
                Object.assign(tempContext, { [appDataEntity.codeName.toLowerCase()]: datas && datas[0].srfkey });
                let tempViewParam: any = { actionView: linkItem.sequenceflowview, actionForm: linkItem.sequenceflowform };
                Object.assign(tempContext, { viewpath: targetView.dynaModelFilePath });
                const appmodal = _this.$appmodal.openModal({ viewname: 'app-view-shell', title: targetView.title, height: targetView.height, width: targetView.width }, tempContext, tempViewParam);
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
                                if (resultData[key]) tempData[key] = resultData[key];
                            })
                            Object.assign(tempSubmitData, tempData);
                            Object.assign(tempSubmitData, datas[0]);
                        }
                    }
                    submit([tempSubmitData], linkItem);
                });
            }
        } else {
            submit(datas, linkItem);
        }
    }
}
