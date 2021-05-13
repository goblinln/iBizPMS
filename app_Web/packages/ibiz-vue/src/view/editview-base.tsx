import { Subject, Subscription } from 'rxjs';
import { DataPanelEngine, EditViewEngine, LogUtil, ModelTool, ViewState } from 'ibiz-core';
import { MainViewBase } from './mainview-base';
import { IPSAppDEEditView, IPSDEForm } from '@ibiz/dynamic-model-api';
import { AppCenterService } from 'ibiz-vue';

/**
 * 编辑视图基类
 *
 * @export
 * @class EditViewBase
 * @extends {MainViewBase}
 */
export class EditViewBase extends MainViewBase {

    /**
     * 视图实例
     * 
     * @memberof ViewBase
     */
     public viewInstance!: IPSAppDEEditView;

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof EditViewBase
     */
    public engine: EditViewEngine = new EditViewEngine();

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof AccountInfoBase
     */
     public datapanel: DataPanelEngine = new DataPanelEngine();

    /**
     * 编辑表单实例
     *
     * @public
     * @type {IBizFormModel}
     * @memberof EditViewBase
     */
    public editFormInstance !:IPSDEForm;

    /**
     * 标题头信息表单部件实例
     *
     * @public
     * @type {IBizFormModel}
     * @memberof EditViewBase
     */
    public dataPanelInstance !:IPSDEForm;

    /**
     * 多编辑面板状态
     *
     * @public
     * @type {Subject<ViewState>}
     * @memberof EditViewBase
     */
    public panelState?: Subject<ViewState>;

    /**
     * 状态事件
     *
     * @public
     * @type {(Subscription | undefined)}
     * @memberof ViewBase
     */
    public panelStateStateEvent: Subscription | undefined;

    /**
     * 监听视图静态参数变化
     * 
     * @memberof EditViewBase 
     */
    public onStaticPropsChange(newVal: any, oldVal: any) {
        if (newVal.panelState) {
            this.panelState = newVal.panelState;
        }
        super.onStaticPropsChange(newVal, oldVal);
    }

    /**
     * 视图初始化
     * 
     * @memberof EditViewBase 
     */
    public viewInit() {
        super.viewInit();
        if (this.panelState) {
            this.panelStateStateEvent = this.panelState.subscribe(({ tag, action, data }: any) => {
                if (!Object.is(tag, this.viewInstance.name)) {
                    return;
                }
                if (action == 'save') {
                    this.viewState.next({ tag: this.editFormInstance.name, action: 'save', data: data });
                }
                if (action == "remove") {
                    this.viewState.next({ tag: this.editFormInstance.name, action: 'remove', data: data });
                }
            })
        }
    }

    /**
     * 引擎初始化
     *
     * @public
     * @memberof EditViewBase
     */
    public engineInit(): void {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        this.engine.init({
            view: this,
            parentContainer: this.$parent,
            form: (this.$refs[this.editFormInstance.name] as any).ctrl,
            p2k: '0',
            isLoadDefault: this.viewInstance.loadDefault,
            keyPSDEField: this.appDeCodeName.toLowerCase(),
            majorPSDEField: this.appDeMajorFieldName.toLowerCase(),
        });
        if(this.dataPanelInstance){
            this.datapanel.init({
                view: this,
                parentContainer: this.$parent,
                datapanel: (this.$refs[this.dataPanelInstance?.name] as any).ctrl,
                p2k: '0',
                isLoadDefault: this.viewInstance.loadDefault,
                keyPSDEField: this.appDeCodeName.toLowerCase(),
                majorPSDEField: this.appDeMajorFieldName.toLowerCase(),
            });
        }
    }

    /**
     * 初始化编辑视图实例
     * 
     * @memberof EditViewBase
     */
    public async viewModelInit() {
        await super.viewModelInit();
        this.editFormInstance = ModelTool.findPSControlByName('form',this.viewInstance.getPSControls()) as IPSDEForm;
        this.dataPanelInstance = ModelTool.findPSControlByName('datapanel',this.viewInstance.getPSControls()) as IPSDEForm;
    }

    /**
     * 渲染视图标题头信息表单部件
     * 
     * @memberof EditViewBase
     */
     public renderDataPanelInfo() {
        if (!this.dataPanelInstance) {
            return null;
        }
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.dataPanelInstance);
        return this.$createElement(targetCtrlName, { slot: 'datapanel', props: targetCtrlParam, ref: this.dataPanelInstance?.name, on: targetCtrlEvent });
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof EditViewBase
     */
    public renderMainContent() {
        if (!this.editFormInstance) {
            return null;
        }
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.editFormInstance);
        return this.$createElement(targetCtrlName, { slot: 'default', props: targetCtrlParam, ref: this.editFormInstance?.name, on: targetCtrlEvent });
    }

    /**
     * 将抄送任务标记为已读
     * 
     * @param data 业务数据
     * @memberof EditViewBase                
     */
     public readTask(data: any) {
        this.appEntityService.ReadTask(this.context, data).then((response:any) =>{
            if (!response || response.status !== 200) {
                LogUtil.warn("将抄送任务标记为已读失败");
                return;
            }
            AppCenterService.notifyMessage({ name: this.appDeCodeName, action: 'appRefresh', data: data });
        }).catch((error: any) => {
            LogUtil.warn("将抄送任务标记为已读失败");
        })
    }
    
    /**
     *  视图销毁
     *
     * @memberof EditViewBase
     */
     public viewDestroyed() {
        super.viewDestroyed();
        if (this.panelStateStateEvent) {
            this.panelStateStateEvent.unsubscribe();
        }
    }

}
