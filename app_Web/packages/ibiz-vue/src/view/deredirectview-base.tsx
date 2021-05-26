
import { DynamicInstanceConfig, IPSAppDataEntity, IPSAppDERedirectView, IPSAppDEView, IPSAppView, IPSAppViewRef } from "@ibiz/dynamic-model-api";
import { GetModelService, LogUtil, Util, ViewTool } from "ibiz-core";
import { MainViewBase } from "./mainview-base";

/**
 * 实体数据重定向视图基类
 *
 * @export
 * @class DeRedirectViewBase
 * @extends {MDViewBase}
 */
export class DeRedirectViewBase extends MainViewBase {

  /**
   * 视图实例
   * 
   * @memberof DeRedirectViewBase
   */
  public viewInstance!: IPSAppDERedirectView;

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
    this.appUIService.getRDAppView(this.context[this.appDeCodeName.toLowerCase()], { enableWorkflow: this.viewInstance?.enableWorkflow }).then(async (result: any) => {
      if (!result) {
        return;
      }
      let targetOpenViewRef: IPSAppViewRef | null | undefined = (this.viewInstance.getRedirectPSAppViewRefs() as IPSAppViewRef[]).find((item: any) => {
        return item.name === result.param.split(":")[0];
      })
      if(!targetOpenViewRef){
        return;
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
        if(!targetOpenView){
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
              { pathName: "views", parameterName: ((targetOpenView  as IPSAppDEView).getPSDEViewCodeName() as string).toLowerCase() },
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
        this.openTargetView(targetOpenView, view, tempContext, tempViewParams, [], parameters, []);
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
    this.$router.replace({ path: routePath }).catch((error:any) =>{
      LogUtil.log("重定向跳转......");
    })
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

}
