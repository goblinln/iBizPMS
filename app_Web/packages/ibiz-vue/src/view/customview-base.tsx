import { IPSAppDataEntity, IPSAppDECustomView, IPSAppDEField, IPSAppViewEngine, IPSControl, IPSDEToolbar, IPSDEToolbarItem } from '@ibiz/dynamic-model-api';
import { CommonViewEngine, CustomViewInterface, throttle, ModelTool } from 'ibiz-core';
import { MainViewBase } from "./mainview-base";


/**
 * 自定义视图基类
 *
 * @export
 * @class CustomViewBase
 * @extends {MainViewBase}
 * @implements {CustomViewInterface}
 */
export class CustomViewBase extends MainViewBase implements CustomViewInterface {

  /**
   * 视图实例
   * 
   * @memberof CustomViewBase
   */
  public viewInstance!: IPSAppDECustomView;

  /**
   * 视图引擎
   *
   * @public
   * @type {CommonViewEngine}
   * @memberof CustomViewBase
   */
  public engine: CommonViewEngine = new CommonViewEngine();

  /**
    * 初始化图表视图实例
    * 
    * @param opts 
    * @memberof CustomViewBase
    */
  public async viewModelInit() {
    await super.viewModelInit();
  }

  /**
   * 绘制目标部件
   * 
   * @memberof CustomViewBase
   */
  public renderTargetControl(control: IPSControl) {
    if (Object.is(control.controlType, 'TOOLBAR')) {
      const viewToolBar: IPSDEToolbar = control as IPSDEToolbar;
      const targetViewToolbarItems: any[] = [];
      if (viewToolBar && viewToolBar.getPSDEToolbarItems()) {
        viewToolBar.getPSDEToolbarItems()?.forEach((toolbarItem: IPSDEToolbarItem) => {
          targetViewToolbarItems.push(this.initToolBarItems(toolbarItem));
        });
      }
      return (
        <view-toolbar
          slot={`layout-${control.name}`}
          mode={this.viewInstance?.viewStyle || 'DEFAULT'}
          counterServiceArray={this.counterServiceArray}
          isViewLoading={this.viewLoadingService?.isLoading}
          toolbarModels={targetViewToolbarItems}
          on-item-click={(data: any, $event: any) => {
            throttle(this.handleItemClick, [data, $event], this);
          }}
        ></view-toolbar>
      );
    } else {
      let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(control);
      if (Object.is(control.controlType, 'SEARCHFORM') || Object.is(control.controlType, 'SEARCHBAR')) {
        Object.assign(targetCtrlParam.dynamicProps, { isExpandSearchForm: true });
      }
      return this.$createElement(targetCtrlName, { slot: `layout-${control.name}`, props: targetCtrlParam, ref: control?.name, on: targetCtrlEvent });
    }
  }

  /**
 * 引擎初始化
 *
 * @param {*} [opts={}] 引擎参数
 * @memberof ChartViewBase
 */
  public engineInit(opts: any = {}): void {
    if (this.Environment && this.Environment.isPreviewMode) {
      return;
    }
    const engineOptions: any = {
      view: this,
      parentContainer: this.$parent,
      keyPSDEField: (ModelTool.getViewAppEntityCodeName(this.viewInstance) as string).toLowerCase(),
      majorPSDEField: (ModelTool.getAppEntityMajorField(this.viewInstance.getPSAppDataEntity() as IPSAppDataEntity) as IPSAppDEField)?.codeName.toLowerCase()
    };
    if (this.viewInstance.getPSControls() && (this.viewInstance.getPSControls() as IPSControl[]).length > 0) {
      const ctrlArray: Array<any> = [];
      (this.viewInstance.getPSControls() as IPSControl[]).forEach((item: IPSControl) => {
        ctrlArray.push({ name: item.name, ctrl: (this.$refs[item.name] as any).ctrl });
      })
      Object.assign(engineOptions, { ctrl: ctrlArray });
    }
    if (this.viewInstance.getPSAppViewEngines() && (this.viewInstance.getPSAppViewEngines() as IPSAppViewEngine[]).length > 0) {
      const engineArray: Array<any> = [];
      (this.viewInstance.getPSAppViewEngines() as IPSAppViewEngine[]).forEach((item: IPSAppViewEngine) => {
        if (Object.is(item.engineCat, 'CTRL')) {
          engineArray.push(item.M);
        }
      })
      Object.assign(engineOptions, { engine: engineArray });
    }
    this.engine.init(engineOptions);
  }

}
