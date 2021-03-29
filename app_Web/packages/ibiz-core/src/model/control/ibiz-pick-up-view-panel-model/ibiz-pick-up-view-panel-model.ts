import { IBizMainControlModel } from '../ibiz-main-control-model';
import { DynamicService } from '../../../../../ibiz-core/src/service';

/**
 * 选择视图面板部件
 */
export class IBizPickUpViewPanelModel extends IBizMainControlModel {

  /**
   * 显示标题栏
   * 
   * @memberof IBizPickUpViewPanelModel
   */
  get showTitleBar() {
    return this.controlModelData.showTitleBar;
  }

  /**
   * 标题
   * 
   * @memberof IBizPickUpViewPanelModel
   */
  get title() {
    return this.controlModelData.name;
  }

  /**
   * 加载模型参数
   *
   * @private
   * @memberof IBizPickUpViewPanelModel
   */
  public async loaded() {
    await super.loaded();
    if (this.controlModelData.getEmbeddedPSAppDEView && this.controlModelData.getEmbeddedPSAppDEView.modelref && this.controlModelData.getEmbeddedPSAppDEView.path) {
      const res = await DynamicService.getInstance(this.context).getAppViewModelJsonData(this.controlModelData.getEmbeddedPSAppDEView.path);
      Object.assign(this.controlModelData.getEmbeddedPSAppDEView, res)
    }
  }

  /**
   * 内嵌视图
   *
   * @private
   * @memberof IBizPickUpViewPanelModel
   */
  get getEmbeddedPSAppDEView() {
    return this.controlModelData.getEmbeddedPSAppDEView;
  }

}
