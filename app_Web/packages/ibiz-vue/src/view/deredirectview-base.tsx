
import { AppServiceBase, DynamicService, IBizAppModel, IBizRedirectViewModel, Util, ViewFactory, ViewTool } from "ibiz-core";
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
  public viewInstance!: IBizRedirectViewModel;

  /**
    * 初始化视图实例
    * 
    * @param opts 
    * @memberof DeRedirectViewBase
    */
  public async viewModelInit() {
    this.viewInstance = new IBizRedirectViewModel(this.staticProps.modeldata, this.context);
    await this.viewInstance.loaded();
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
    this.appUIService.getRDAppView(this.context[this.viewInstance?.appDeCodeName?.toLowerCase()], this.viewInstance?.enableWorkflow).then(async (result: any) => {
      if (!result) {
        return;
      }
      let targetOpenViewRef: any = this.viewInstance.getRedirectPSAppViewRefs.find((item: any) => {
        return item.name === result.split(":")[0];
      })
      // 存在动态实例
      let splitArray: Array<any> = result.split(":");
      if (splitArray && (splitArray.length == 3)) {
        let appModelDataObj: IBizAppModel = AppServiceBase.getInstance().getAppModelDataObject();
        if (appModelDataObj && (appModelDataObj.getPSDynaInsts.length > 0)) {
          let curDynaInst: any = appModelDataObj.getPSDynaInsts.find((item: any) => {
            return (item.instTag == splitArray[2]) && (item.instTag2 == splitArray[1]);
          })
          if (curDynaInst) {
            Object.assign(tempContext, { srfdynainstid: curDynaInst.id });
          }
        }
      }
      if (targetOpenViewRef.getRefPSAppView?.modelref && targetOpenViewRef.getRefPSAppView.path) {
        let targetAppView = await DynamicService.getInstance(this.context).getAppViewModelJsonData(targetOpenViewRef.getRefPSAppView.path);
        let targetOpenView: any = ViewFactory.getInstance(targetAppView, this.context);
        await targetOpenView.loaded()
        const view: any = {
          viewname: Util.srfFilePath2(targetOpenView.codeName),
          height: targetOpenView.height,
          width: targetOpenView.width,
          title: targetOpenView.title
        };
        let parameters: Array<any> = [];
        if (!targetOpenView.openMode || targetOpenView.openMode == 'INDEXVIEWTAB') {
          if (targetOpenView.appDataEntity) {
            parameters = [
              { pathName: Util.srfpluralize(targetOpenView.appDataEntity.codeName).toLowerCase(), parameterName: targetOpenView.appDataEntity.codeName.toLowerCase() },
              { pathName: "views", parameterName: targetOpenView.getPSDEViewCodeName.toLowerCase() },
            ];
          } else {
            parameters = [
              { pathName: targetOpenView.codeName.toLowerCase(), parameterName: targetOpenView.codeName.toLowerCase() }
            ];
          }
        } else {
          if (targetOpenView.appDataEntity) {
            parameters = [{ pathName: Util.srfpluralize(targetOpenView.appDataEntity.codeName).toLowerCase(), parameterName: targetOpenView.appDataEntity.codeName.toLowerCase() }];
          }
          if (targetOpenView && targetOpenView.dynaModelFilePath) {
            Object.assign(tempContext, { viewpath: targetOpenView.dynaModelFilePath });
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
      console.log("重定向跳转......");
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
