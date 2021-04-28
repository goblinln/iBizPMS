import { MobMPickupViewEngine, ModelTool } from 'ibiz-core'
import { MainViewBase } from './main-view-base';
import { IPSAppDEPickupView, IPSDEPickupViewPanel } from '@ibiz/dynamic-model-api';

/**
 * 多选择视图基类
 *
 * @export
 * @class MobMPickUpViewBase
 * @extends {MainViewBase}
 */
export class MobMPickUpViewBase extends MainViewBase {

    /**
     * 视图实例
     * 
     * @memberof MobMPickUpViewBase
     */
    public viewInstance!: IPSAppDEPickupView;

    /**
     * 数据选择面板实例
     * 
     * @memberof MobMPickUpViewBase
     */
    public viewPickUpViewPanelInstance!: IPSDEPickupViewPanel;

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof MobMPickUpViewBase
     */
    public engine: MobMPickupViewEngine = new MobMPickupViewEngine();

    /**
     * 视图选中数据
     *
     * @type {any[]}
     * @memberof MobMPickUpViewBase
     */
    public viewSelections: any[] = [];

    /**
     * 引擎初始化
     *
     * @param {*} [opts={}] 引擎参数
     * @memberof MobMPickUpViewBase
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
     * @memberof MobMPickUpViewBase
     */
    public async viewModelInit() {
        this.viewInstance = (this.staticProps?.modeldata) as IPSAppDEPickupView;
        await super.viewModelInit();
        this.viewPickUpViewPanelInstance = ModelTool.findPSControlByName("pickupviewpanel",this.viewInstance.getPSControls());
    }

    /**
     * 初始化数据选择视图实例
     * 
     * @memberof MobMPickUpViewBase
     */
    public renderFooter() {
        return this.viewInstance.viewType === 'DEMOBMPICKUPVIEW'
        ? <div class="mpicker_buttons" slot="footer">
            <div class="demobmpickupview_button">
              <div class="selectedCount">已选择：{this.viewSelections.length}
                  <app-mob-icon name="chevron-up-outline"></app-mob-icon>
              </div>
              <app-mob-button 
                  class="pick-btn" 
                  text={this.$t('app.button.confirm')}
                  disabled={this.viewSelections.length === 0} 
                  on-click={() => { this.onClickOk() }}/>
            </div>
        </div>
        :<ion-toolbar slot="footer" style="text-align: center;">
            <div class="mobpickupview_button">
                <app-mob-button
                    class="pick-btn"
                    color="medium"
                    text="取消"
                    on-click={() => { this.onClickCancel() }} />
                <app-mob-button
                    class="pick-btn"
                    text="确认"
                    disabled={this.viewSelections.length === 0}
                    on-click={() => { this.onClickOk() }} />
            </div>
        </ion-toolbar>
    }

    /**
     * 确定
     *
     * @memberof MobMPickUpViewBase
     */
    public onClickOk(): void {
        this.$emit('view-event', { viewName: this.viewInstance.codeName, action: 'viewDatasChange', data: this.viewSelections })
        this.$emit('view-event', { viewName: this.viewInstance.codeName, action: 'close', data: this.viewSelections })
    }

    /**
     * 取消
     *
     * @memberof MobMPickUpViewBase
     */
    public onClickCancel(): void {
        this.$emit('view-event', { viewName: this.viewInstance.codeName, action: 'close', data: this.viewSelections })
    }


    /**
     * 渲染视图主体内容区
     * 
     * @memberof MobMPickUpViewBase
     */
    public renderMainContent() {

        let { targetCtrlName, targetCtrlParam, targetCtrlEvent }: { targetCtrlName: string, targetCtrlParam: any, targetCtrlEvent: any } = this.computeTargetCtrlData(this.viewPickUpViewPanelInstance);
        Object.assign(targetCtrlParam.staticProps, {
            isSingleSelect: false
        });
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.viewPickUpViewPanelInstance.name, on: targetCtrlEvent });
    }

}
