import { WFStepTraceViewInterface } from 'ibiz-core';
import { ViewBase } from './view-base';

/**
 * 流程跟踪视图基类
 *
 * @export
 * @class WfStepTraceViewBase
 * @extends {ViewBase}
 * @implements {WFStepTraceViewInterface}
 */
export class WfStepTraceViewBase extends ViewBase implements WFStepTraceViewInterface {

    /**
      * 初始化流程跟踪视图实例
      * 
      * @param opts 
      * @memberof WfStepTraceViewBase
      */
    public async viewModelInit() {
        await super.viewModelInit();
    }


}
