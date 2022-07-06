import { IPSDEMultiEditViewPanel } from '@ibiz/dynamic-model-api';
import { MobMEditView9Engine, MobMEditViewInterface, ModelTool } from 'ibiz-core'
import { MDViewBase } from "./md-view-base";

/**
 * 多编辑表单视图基类
 *
 * @export
 * @class MobMeditViewBase
 * @extends {MDViewBase}
 */
export class MobMeditViewBase extends MDViewBase implements MobMEditViewInterface {

    /**
     * 视图实例
     * 
     * @memberof MobMeditViewBase
     */
    public viewInstance!: any;

    /**
     * 编辑面板实例
     * 
     * @memberof MobMeditViewBase
     */
    public meditViewPanelInstance!: IPSDEMultiEditViewPanel;

    /**
     * 视图引擎
     *
     * @public
     * @type {*}
     * @memberof MainViewBase
     */
    public engine: MobMEditView9Engine = new MobMEditView9Engine();

    /**
     * 引擎初始化
     *
     * @param {*} [opts={}] 引擎参数
     * @memberof MobMeditViewBase
     */
    public engineInit(opts: any = {}): void {
        if (this.Environment?.isPreviewMode) {
            return;
        }
        if (this.engine && this.meditViewPanelInstance) {
            let engineOpts = Object.assign({
                meditviewpanel: (this.$refs[this.meditViewPanelInstance.name] as any).ctrl,
            }, opts)
            super.engineInit(engineOpts);
        }
    }

    /**
     * 初始化列表视图实例
     * 
     * @memberof MobMeditViewBase
     */
    public async viewModelInit() {
        await super.viewModelInit();
        this.meditViewPanelInstance = ModelTool.findPSControlByName('meditviewpanel', this.viewInstance.getPSControls()) as IPSDEMultiEditViewPanel;
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof MobMeditViewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent }: { targetCtrlName: string, targetCtrlParam: any, targetCtrlEvent: any } = this.computeTargetCtrlData(this.meditViewPanelInstance);
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.meditViewPanelInstance.name, on: targetCtrlEvent });
    }

    /**
      * 多表单编辑视图挂载
      *
      * @memberof MobMeditViewBase
      */
    public viewMounted() {
        super.viewMounted();
        if (this.formDruipartState) {
            this.$emit('view-event', { viewName: this.viewCodeName, action: 'viewLoaded', data: null });
        }
    }

    /**
     * 关系数据变化
     *
     * @param {*} $event
     * @memberof MEditViewBase
     */
    public onViewDataDirty($event: any) {
        this.$emit('view-event', { viewName: this.viewCodeName, action: 'drdatachange', data: $event });
    }

    /**
     * 关系数据保存执行完成
     *
     * @param {*} $event
     * @memberof MEditViewBase
     */
    public onDRDataSaved($event: any) {
        this.$emit('view-event', { viewName: this.viewCodeName, action: 'drdatasaved', data: $event });

    }

    /**
     * 多表单编辑视图初始化
     *
     * @memberof MEditViewBase
     */
    public async viewInit() {
        super.viewInit()
    }


    /**
     * 关系表单订阅事件
     *
     * @memberof MEditViewBase
     */
    public formDruipartStatEvent() {
        if (this.formDruipartState) {
            this.formDruipartStateEvent = this.formDruipartState.subscribe(({ action }: any) => {
                if (Object.is(action, 'save')) {
                    this.viewState.next({ tag: 'meditviewpanel', action: 'save', data: this.viewparams });
                }
                if (Object.is(action, 'remove')) {
                    this.viewState.next({ tag: 'meditviewpanel', action: 'remove', data: this.viewparams });
                }
                if (Object.is(action, 'load')) {
                    this.viewState.next({ tag: 'meditviewpanel', action: 'load', data: this.viewparams });
                }
            });
        }
    }

    /**
     * 部件事件监听
     *
     * @memberof MEditViewBase
     */
    public onCtrlEvent(controlname: string, action: string, data: any) {
        if (Object.is(controlname, this.meditViewPanelInstance?.name) && action) {
            
            switch (action) {
                case 'drdatasaved':
                    this.onDRDataSaved(data);
                    break;
                case 'drdatachange':
                    this.onViewDataDirty(data);
            }
        }
        super.onCtrlEvent(controlname, action, data);
    }
}
