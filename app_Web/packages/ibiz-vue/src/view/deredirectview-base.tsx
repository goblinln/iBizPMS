
import { VNode } from 'vue';
import { DynamicInstanceConfig, IPSAppDataEntity, IPSAppDERedirectView, IPSAppDEView, IPSAppView, IPSAppViewRef, IPSNavigateContext, IPSNavigateParam } from "@ibiz/dynamic-model-api";
import { GetModelService, LogUtil, ModelTool, RedirectViewInterface, StringUtil, Util, ViewTool } from "ibiz-core";
import { MainViewBase } from "./mainview-base";

/**
 * 实体数据重定向视图基类
 *
 * @export
 * @class DeRedirectViewBase
 * @extends {MDViewBase}
 */
export class DeRedirectViewBase extends MainViewBase implements RedirectViewInterface {

  /**
   * 视图实例
   * 
   * @memberof DeRedirectViewBase
   */
  public viewInstance!: IPSAppDERedirectView;

  /**
   * 视图是否被导航部件引用
   * 
   * @memberof DeRedirectViewBase
   */
  public viewUseByExpBar: boolean = false;

  /**
   * 视图内容节点
   * 
   * @memberof DeRedirectViewBase
   */
  public viewNodeContent?: VNode;

  /**
   * 是否首次加载
   * 
   * @memberof DeRedirectViewBase
   */
  public isFirstLoad: boolean = true;

  /**
   * 监听静态参数变化
   * 
   * @memberof DeRedirectViewBase
   */
  public onStaticPropsChange(newVal: any, oldVal: any) {
    super.onStaticPropsChange(newVal, oldVal);
    this.viewUseByExpBar = newVal.viewUseByExpBar ? true : false;
  }

  /**
   * 监听动态参数变化
   * 
   * @memberof DeRedirectViewBase
   */
  public onDynamicPropsChange(newVal: any, oldVal: any) {
    super.onDynamicPropsChange(newVal, oldVal);
    if (!this.isFirstLoad && this.viewUseByExpBar) {
      this.executeRedirectLogic();
    }
    this.isFirstLoad = false;
  }

  /**
    * 初始化视图实例
    * 
    * @param opts 
    * @memberof DeRedirectViewBase
    */
  public async viewModelInit() {
    this.viewInstance = (this.staticProps.modeldata) as IPSAppDERedirectView;
    await super.viewModelInit();
    this.executeRedirectLogic();
  }

  /**
    * 执行重定向逻辑
    * 
    * @memberof DeRedirectViewBase
    */
  public async executeRedirectLogic() {
    let tempContext: any = Util.deepCopy(this.context);
    let tempViewParams: any = Util.deepCopy(this.viewparams);
    let localParams: any = {};
    if (this.viewparams && this.viewparams.srfwf) {
      localParams = { srfwf: this.viewparams.srfwf.toLowerCase() };
    } else {
      const appDataEntity = this.viewInstance.getPSAppDataEntity() as IPSAppDataEntity;
      localParams = { enableWorkflow: this.viewInstance?.enableWorkflow && appDataEntity?.enableWFActions };
    }
    let dataSetParams: any = {};
    if (this.viewUseByExpBar && this.viewInstance.enableCustomGetDataAction) {
      const action = this.viewInstance.getGetDataPSAppDEAction();
      if (action) {
        Object.assign(dataSetParams, {
          action: action.codeName
        });
      }
    }
    this.appUIService.getRDAppView(this.context, this.context[this.appDeCodeName.toLowerCase()], localParams, dataSetParams).then(async (result: any) => {
      if (!result) {
        return;
      }
      let targetOpenViewRef: IPSAppViewRef | null | undefined = null;
      if (this.viewUseByExpBar) {
        //  重定向视图识别属性
        const typeFieldCodeName = this.viewInstance.getTypePSAppDEField?.()?.codeName;
        if (typeFieldCodeName && result.srfdata && result.srfdata[typeFieldCodeName.toLowerCase()]) {
          targetOpenViewRef = (this.viewInstance.getRedirectPSAppViewRefs() as IPSAppViewRef[]).find((item: any) => {
            return item.name.toLowerCase() === result.srfdata[typeFieldCodeName.toLowerCase()].toLowerCase();
          })
        }
      } else {
        if (this.viewparams && this.viewparams.srfwf) {
          targetOpenViewRef = (this.viewInstance.getRedirectPSAppViewRefs() as IPSAppViewRef[]).find((item: any) => {
            return item.name === `${result.param.split(":")[0]}:${this.viewparams.srfwf.toUpperCase()}`;
          })
        }
        if (!targetOpenViewRef) {
          targetOpenViewRef = (this.viewInstance.getRedirectPSAppViewRefs() as IPSAppViewRef[]).find((item: any) => {
            return item.name === result.param.split(":")[0];
          })
        }
      }
      if (!targetOpenViewRef) {
        return;
      }
      //  导航上下文
      if (
        targetOpenViewRef.getPSNavigateContexts() &&
        (targetOpenViewRef.getPSNavigateContexts() as IPSNavigateContext[]).length > 0
      ) {
          let localContextRef: any = Util.formatNavParam(targetOpenViewRef.getPSNavigateContexts(), true);
          let _context: any = Util.computedNavData(result, tempContext, tempViewParams, localContextRef);
          //  填充字符串数据
          if (_context && Object.keys(_context).length > 0) {
            for (const key of Object.keys(_context)) {
              _context[key] = StringUtil.fillStrData(_context[key], tempContext, result);
            }
          }
          Object.assign(tempContext, _context);
      }
      //  导航视图参数
      if (
        targetOpenViewRef.getPSNavigateParams() &&
        (targetOpenViewRef.getPSNavigateParams() as IPSNavigateParam[]).length > 0
      ) {
          let localViewParamsRef: any = Util.formatNavParam(targetOpenViewRef.getPSNavigateParams(), true);
          let _viewParams: any = Util.computedNavData(result, tempContext, tempViewParams, localViewParamsRef);
          //  填充字符串数据
          if (_viewParams && Object.keys(_viewParams).length > 0) {
            for (const key of Object.keys(_viewParams)) {
              _viewParams[key] = StringUtil.fillStrData(_viewParams[key], tempContext, result);
            }
          }
          Object.assign(tempViewParams, _viewParams);
      }
      // 存在动态实例
      let splitArray: Array<any> = result.param.split(":");
      if (splitArray && (splitArray.length == 3)) {
        let curDynaInst: DynamicInstanceConfig = (await GetModelService({ instTag: splitArray[2], instTag2: splitArray[1] }))?.getDynaInsConfig();
        if (curDynaInst) {
          Object.assign(tempContext, { srfdynainstid: curDynaInst.id });
        }
      }
      if (result && result.hasOwnProperty('srfsandboxtag')) {
        Object.assign(tempContext, { 'srfsandboxtag': result['srfsandboxtag'] });
        Object.assign(tempViewParams, { 'srfsandboxtag': result['srfsandboxtag'] });
      }
      if (targetOpenViewRef.getRefPSAppView()) {
        let targetOpenView: IPSAppView | null = targetOpenViewRef.getRefPSAppView();
        if (!targetOpenView) {
          return;
        }
        await targetOpenView.fill(true);
        const view: any = {
          viewname: Util.srfFilePath2(targetOpenView.codeName),
          height: targetOpenView.height,
          width: targetOpenView.width,
          title: targetOpenView.title
        };
        let parameters: Array<any> = [];
        if (!targetOpenView.openMode || targetOpenView.openMode == 'INDEXVIEWTAB') {
          if (targetOpenView.getPSAppDataEntity()) {
            parameters = [
              { pathName: Util.srfpluralize((targetOpenView.getPSAppDataEntity() as IPSAppDataEntity)?.codeName).toLowerCase(), parameterName: (targetOpenView.getPSAppDataEntity() as IPSAppDataEntity)?.codeName.toLowerCase() },
              { pathName: "views", parameterName: ((targetOpenView as IPSAppDEView).getPSDEViewCodeName() as string).toLowerCase() },
            ];
          } else {
            parameters = [
              { pathName: targetOpenView.codeName.toLowerCase(), parameterName: targetOpenView.codeName.toLowerCase() }
            ];
          }
        } else {
          if (targetOpenView.getPSAppDataEntity()) {
            parameters = [{ pathName: Util.srfpluralize((targetOpenView.getPSAppDataEntity() as IPSAppDataEntity)?.codeName).toLowerCase(), parameterName: (targetOpenView.getPSAppDataEntity() as IPSAppDataEntity)?.codeName.toLowerCase() }];
          }
          if (targetOpenView && targetOpenView.modelPath) {
            Object.assign(tempContext, { viewpath: targetOpenView.modelPath });
          }
        }
        if (this.viewUseByExpBar) {
          this.openTargetViewByExpBar(targetOpenView, view, tempContext, tempViewParams, [], parameters, []);
        } else {
          this.openTargetView(targetOpenView, view, tempContext, tempViewParams, [], parameters, []);
        }
      }
    })
  }

  /**
   * 打开目标视图
   *
   * @memberof DeRedirectViewBase
   */
  public openTargetView(openView: any, view: any, tempContext: any, data: any, deResParameters: any, parameters: any, args: any) {
    if (tempContext.srfdynainstid) {
      Object.assign(data, { srfdynainstid: tempContext.srfdynainstid });
    }
    const routePath = ViewTool.buildUpRoutePath(this.$route, tempContext, deResParameters, parameters, args, data);
    this.closeRedirectView(args);
    this.$router.replace({ path: routePath }).catch((error: any) => {
      LogUtil.log("重定向跳转......");
    })
  }

  /**
   * 打开目标视图（被导航栏引用的情况）
   *
   * @memberof DeRedirectViewBase
   */
  public openTargetViewByExpBar(openView: any, view: any, tempContext: any, data: any, deResParameters: any, parameters: any, args: any) {
    this.viewNodeContent = undefined;
    if (openView && openView.modelPath) {
      Object.assign(tempContext, { viewpath: openView.modelPath });
    }
    const content = this.$createElement('app-view-shell', {
      key: Util.createUUID(),
      props: {
        staticProps:{
            viewDefaultUsage: false,
        },
        dynamicProps:{
            viewdata: JSON.stringify(tempContext),
            viewparam: JSON.stringify(data)
        }
      },
      class: 'viewcontainer3',
    })
    this.viewNodeContent = content;
    this.$forceUpdate();
  }

  /**
   * 关闭当前重定向视图
   *
   * @memberof DeRedirectViewBase
   */
  public closeRedirectView(args: Array<any>) {
    let view: any = this;
    if (view.viewdata) {
      view.$emit('view-event', { action: 'viewdataschange', data: Array.isArray(args) ? args : [args] });
      view.$emit('view-event', { action: 'close', data: Array.isArray(args) ? args : [args] });
    } else {
      if (this.viewInstance && this.viewInstance.viewStyle && Object.is(this.viewInstance.viewStyle, "STYLE2")) {
        this.closeViewWithStyle2(view);
      } else {
        this.closeViewWithDefault(view);
      }
    }
  }

  /**
   * 渲染视图内容节点
   *
   * @memberof DeRedirectViewBase
   */
  public renderContent() {
    if (this.viewNodeContent) {
      return this.viewNodeContent;
    }
  }

}
