import { IBizCustomViewModel } from 'ibiz-core';
import { MainViewBase } from "./MainViewBase";

/**
 * 自定义视图基类
 *
 * @export
 * @class CustomViewBase
 * @extends {MDViewBase}
 */
export class CustomViewBase extends MainViewBase {

    /**
     * 视图实例
     * 
     * @memberof CustomViewBase
     */
    public viewInstance!: IBizCustomViewModel;

    /**
      * 初始化图表视图实例
      * 
      * @param opts 
      * @memberof CustomViewBase
      */
    public async viewModelInit() {
        this.viewInstance = new IBizCustomViewModel(this.staticProps.modeldata, this.context);
        await this.viewInstance.loaded();
        await super.viewModelInit();
    }


}
