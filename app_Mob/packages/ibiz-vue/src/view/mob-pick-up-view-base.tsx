import { MobPickupViewEngine, MobPickupViewInterface, ModelTool } from 'ibiz-core'
import { MainViewBase } from './main-view-base';
import { IPSAppDEPickupView, IPSDEPickupViewPanel } from '@ibiz/dynamic-model-api';

/**
 * 选择视图基类
 *
 * @export
 * @class MobPickUpViewBase
 * @extends {MainViewBase}
 */
export class MobPickUpViewBase extends MainViewBase implements MobPickupViewInterface {

    /**
     * 视图实例
     * 
     * @memberof MobPickUpViewBase
     */
    public viewInstance!: IPSAppDEPickupView;

    /**
     * 数据选择面板实例
     * 
     * @memberof MobPickUpViewBase
     */
    public viewPickUpViewPanelInstance!: IPSDEPickupViewPanel;

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof MobPickUpViewBase
     */
    public engine: MobPickupViewEngine = new MobPickupViewEngine();

    /**
     * 视图选中数据
     *
     * @type {any[]}
     * @memberof ${srfclassname('${view.name}')}
     */
    public viewSelections: any[] = [];

    /**
     * 引擎初始化
     *
     * @param {*} [opts={}] 引擎参数
     * @memberof MobPickUpViewBase
     */
    public engineInit(opts: any = {}): void {
        if (this.Environment?.isPreviewMode) {
            return;
        }
        if (this.engine && this.viewPickUpViewPanelInstance) {
            let engineOpts = Object.assign({
                pickupViewPanel: (this.$refs[this.viewPickUpViewPanelInstance.name] as any).ctrl,
            }, opts)
            super.engineInit(engineOpts);
        }
    }

    /**
     * 初始化数据选择视图实例
     * 
     * @memberof MobPickUpViewBase
     */
    public async viewModelInit() {
        this.viewInstance = (this.staticProps?.modeldata) as IPSAppDEPickupView;
        await super.viewModelInit();
        this.viewPickUpViewPanelInstance = ModelTool.findPSControlByName("pickupviewpanel",this.viewInstance.getPSControls());
    }

    /**
     * 初始化数据选择视图实例
     * 
     * @memberof MobPickUpViewBase
     */
    public renderFooter() {
        return <ion-toolbar slot="footer" style="text-align: center;">
            <div class="mobpickupview_button">
                <app-mob-button
                    class="pick-btn"
                    color="medium"
                    text={this.$t('app.button.cancel')}
                    on-click={() => { this.onClickCancel() }} />
                <app-mob-button
                    class="pick-btn"
                    text={this.$t('app.button.confirm')}
                    disabled={this.viewSelections.length === 0}
                    on-click={() => { this.onClickOk() }} />
            </div>
        </ion-toolbar>
    }

    /**
     * 确定
     *
     * @memberof MobPickUpViewBase
     */
    public onClickOk(): void {
        this.$emit('view-event', { viewName: this.viewInstance.codeName, action: 'viewDatasChange', data: this.viewSelections })
        this.$emit('view-event', { viewName: this.viewInstance.codeName, action: 'close', data: this.viewSelections })
    }

    /**
     * 取消
     *
     * @memberof MobPickUpViewBase
     */
    public onClickCancel(): void {
        this.$emit('view-event', { viewName: this.viewInstance.codeName, action: 'close', data: null })
    }


    /**
     * 渲染视图主体内容区
     * 
     * @memberof MobPickUpViewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent }: { targetCtrlName: string, targetCtrlParam: any, targetCtrlEvent: any } = this.computeTargetCtrlData(this.viewPickUpViewPanelInstance);
        Object.assign(targetCtrlParam.staticProps, {
            isSingleSelect: true
        });
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.viewPickUpViewPanelInstance.name, on: targetCtrlEvent });
    }

}
