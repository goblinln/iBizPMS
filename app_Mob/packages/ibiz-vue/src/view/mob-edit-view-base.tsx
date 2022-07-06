import { IPSAppDEMobEditView, IPSDEForm } from "@ibiz/dynamic-model-api";
import {  MobEditViewEngine, MobEditViewInterface, ModelTool } from "ibiz-core";
import { MainViewBase } from "./main-view-base";

/**
 * 编辑视图基类
 *
 * @export
 * @class MobEditViewBase
 * @extends {MainViewBase}
 */
export class MobEditViewBase extends MainViewBase implements MobEditViewInterface {

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof MobEditViewBase
     */
    public engine: MobEditViewEngine = new MobEditViewEngine();

    /**
     * 视图实例
     * 
     * @memberof MobEditViewBase
     */
    public viewInstance !: IPSAppDEMobEditView;

    /**
     * 编辑表单实例
     *
     * @public
     * @type {IBizMobFormModel}
     * @memberof MobEditViewBase
     */
    public editFormInstance !: IPSDEForm;

    /**
     * 表单数据是否变化
     *
     * @public
     * @type {Engine}
     * @memberof MobEditViewBase
     */
    public dataChange: boolean = false;  

    /**
     * 引擎初始化
     *
     * @public
     * @memberof MobEditViewBase
     */
    public engineInit(opts: any = {}): void {
        if (this.Environment?.isPreviewMode) {
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
    }

    /**
     * 初始化编辑视图实例
     * 
     * @memberof MobEditViewBase
     */
    public async viewModelInit() {
        await super.viewModelInit();
        this.editFormInstance = ModelTool.findPSControlByName('form',this.viewInstance.getPSControls()) as IPSDEForm;
        this.initViewToolBar()
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof MobEditViewBase
     */
    public renderMainContent() {
        if (!this.editFormInstance) {
            return null;
        }
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.editFormInstance);
        return this.$createElement(targetCtrlName, { slot: 'default', props: targetCtrlParam, ref: this.editFormInstance.name, on: targetCtrlEvent });
    }

    /**
     * 部件事件
     * @param ctrl 部件 
     * @param action  行为
     * @param data 数据
     * 
     * @memberof MobEditViewBase
     */
    public onCtrlEvent(controlname: string, action: string, data: any) {
        if (action == 'dataChange') {
          this.dataChange = data;
        }
        super.onCtrlEvent(controlname,action,data);
    }

    /**
     * 检查表单是否修改
     *
     * @param {any[]} args
     * @memberof MobEditViewBase
     */
    public async cheackChange(): Promise<any> {
        if (this.dataChange) {
            const title: any = this.$t('app.tabpage.sureclosetip.title');
            const contant: any = this.$t('app.tabpage.sureclosetip.content');
            const result = await this.$Notice.confirm.call(this,title, contant);
            if (result) {
                this.dataChange = false;
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    /**
     *  关闭视图
     *
     * @memberof MobEditViewBase
     */
    public async closeView(args?: any[]) {
        let result = await this.cheackChange();
        if(result){
          super.closeView(args);
        }
    }

    /**
     * 绘制头部标题栏
     * 
     * @memberof MobEditViewBase
     */
    public renderViewHeaderCaptionBar() {
        if (this.showCaption === false) {
            return null;
        }
        const srfCaption = this.model.srfCaption;
        const dataInfo: string = this.model.dataInfo ? (`-${this.model.dataInfo}`) : '';
        const showInfoBar: boolean = (this.viewInstance as IPSAppDEMobEditView).showDataInfoBar;
        const psimage = this.viewInstance.getPSSysImage();
        return <ion-toolbar v-show="titleStatus" class="ionoc-view-header" slot="captionbar">
            <ion-buttons slot="start">
                {this.isShowBackButton &&
                    <ion-button on-click={this.closeView.bind(this)}>
                        <ion-icon name="chevron-back" />
            {this.$t('app.button.back')}
          </ion-button>}
            </ion-buttons>
            <ion-title class="view-title"><app-ps-sys-image imageModel={psimage}></app-ps-sys-image>{dataInfo && showInfoBar ? srfCaption + dataInfo : srfCaption}</ion-title>
        </ion-toolbar>
    }

}
