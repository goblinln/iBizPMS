import { IPSAppDECustomView } from '@ibiz/dynamic-model-api';
import { MainViewBase } from "./mainview-base";

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
    public viewInstance!: IPSAppDECustomView;

    /**
      * 初始化图表视图实例
      * 
      * @param opts 
      * @memberof CustomViewBase
      */
    public async viewModelInit() {
        await super.viewModelInit();
    }


}
