import { MobWFDynaEditViewEngine, ModelTool } from 'ibiz-core';
import { GlobalService } from 'ibiz-service';
import { MainViewBase } from './main-view-base';
import { IPSAppDEMobWFDynaEditView, IPSDEForm } from '@ibiz/dynamic-model-api';

/**
 * 工作流动态编辑视图基类
 *
 * @export
 * @class MobWFDynaEditViewBase
 * @extends {MainViewBase}
 */
export class MobWFDynaEditViewBase extends MainViewBase {

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
    public editFormInstance !: IPSDEForm;

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
    public srfwfmemo: string = "";

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
     * 工具栏分组是否显示的条件
     *
     * @type {boolean}
     * @memberof MobWFDynaEditViewBase
     */
    public showGrop = false;

    /**
     * 工具栏分组是否显示的方法
     *
     * @memberof MobWFDynaEditViewBase
     */
    public popUpGroup(falg: boolean = false) {
        this.showGrop = falg;
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
        this.viewInstance = (this.staticProps.modeldata) as IPSAppDEMobWFDynaEditView;
        this.viewRefData = await ModelTool.loadedAppViewRef(this.viewInstance);
        await super.viewModelInit();
        this.appEntityService = await new GlobalService().getService(this.appDeCodeName);
        this.initAllForm();
    }

    /**
     * 绘制底部按钮
     * 
     * @memberof MobWFDynaEditViewBase
     */
    public renderFooter() {
        return <div class="fab_container" slot="footer">
            {this.linkModel.length > 0
                ? <app-mob-button
                    iconName="chevron-up-circle-outline"
                    class="app-view-toolbar-button wf_btns"
                    style={this.button_style}
                    on-click={() => this.popUpGroup(true)} />
                : null}
            {this.showGrop
                ? <van-popup class="popup" round position="bottom">
                    <div class="container">
                        {this.linkModel.map((linkItem: any) => {
                            return <div class='sub-item'>
                                <app-mob-button text={linkItem?.sequenceFlowName} on-click={($event: any) => this.dynamic_toolbar_click(linkItem, $event)} />
                            </div>
                        })}
                    </div>
                </van-popup>
                : null}
        </div>
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
     * @returns {Promise<any>}
     * @memberof MobWFDynaEditViewBase
     */
    public async getWFLinkModel(): Promise<any> {
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
                    this.$Notice.error(response.message);
                    return;
                }
                if (!response || !response.status || !response.data) {
                    this.$Notice.error('错误,系统异常');
                    return;
                }
            });
        });
    }

    /**
     * 动态工具栏点击
     *
     * @param {*} linkItem
     * @param {*} $event
     * @memberof MobWFDynaEditViewBase
     */
    public async dynamic_toolbar_click(linkItem: any, $event: any) {
        let datas: any;
        let xData: any = (this.$refs[this.editFormInstance.name] as any).ctrl;
        if (xData.getDatas && xData.getDatas instanceof Function) {
            datas = xData.getDatas()[0];
        }
        let falg = await this.openSrfwfmemo(linkItem, datas);
        if (!falg) {
            return
        }
        let res = await xData.save();
        if (!res || res.status !== 200) {
            return;
        }
        let response = await xData.wfsubmit(this.context, linkItem, datas);
        if (!response || response.status !== 200) {
            return;
        }
        const { data: _data } = response;
        if (this.viewparams) {
            this.$emit('view-event', { viewName: this.viewInstance.name, action: 'viewdataschange', data: [{ ..._data }] });
            this.closeView(_data);
        }
        // } else if (this.$tabPageExp) {
        //     this.$tabPageExp.onClose(this.$route.fullPath);
        // }
    }

    /**
     * 工作流审批意见
     *
     * @protected
     * @param {*} linkItem
     * @returns {Promise<any>}
     * @memberof MobWFDynaEditViewBase
     */
    public async openSrfwfmemo(linkItem: any, data: any): Promise<any> {
        // if(linkItem && linkItem.sequenceflowmobview && this.viewRefData[`WFACTION@${r'${linkItem.sequenceflowmobview}'}`]){
        //     let tempContext:any = this.$util.deepCopy(this.context);
        //     let tempViewParam:any = {actionView:linkItem.sequenceflowmobview,actionForm:linkItem.sequenceflowmobform};
        //     let targetView:any = this.viewRefData[`WFACTION@${r'${linkItem.sequenceflowmobview}'}`];
        //     const result: any = await this.$appmodal.openModal(targetView,tempContext,tempViewParam);
        //     if (result || Object.is(result.ret, 'OK')) {
        //         const { datas } = result;
        //         if(datas[0].srfwfmemo){
        //             this.srfwfmemo = datas[0].srfwfmemo;
        //         }
        //     }
        // }
        return true;
    }

    /**
     * 计算激活表单
     * 
     * @memberof MobWFDynaEditViewBase
     */
    public computeActivedForm(inputForm: any) {
        if (!inputForm) {
            this.editFormInstance = ModelTool.findPSControlByName('form',this.viewInstance.getPSControls());
        } else {
            this.editFormInstance = ModelTool.findPSControlByName(`wfform_${inputForm.toLowerCase()}`,this.viewInstance.getPSControls());
        }
        setTimeout(() => {
            this.viewState.next({ tag: this.editFormInstance.name, action: 'autoload', data: { srfkey: this.context[this.appDeCodeName.toLowerCase()] } });
        }, 0);
    }

}
