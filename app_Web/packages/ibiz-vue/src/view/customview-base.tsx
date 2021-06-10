import { IPSAppDECustomView } from '@ibiz/dynamic-model-api';
import { CustomViewInterface } from 'ibiz-core';
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
      * 初始化图表视图实例
      * 
      * @param opts 
      * @memberof CustomViewBase
      */
    public async viewModelInit() {
        await super.viewModelInit();
    }


}
