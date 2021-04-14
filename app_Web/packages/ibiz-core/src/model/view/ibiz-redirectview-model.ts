import { IBizMainViewModel } from "./ibiz-main-view-model";

/**
 * 实体重定向视图模型类
 * 
 * @class IBizRedirectViewModel
 */
export class IBizRedirectViewModel extends IBizMainViewModel {

    /**
     * 启用工作流
     *
     * @memberof IBizRedirectViewModel
     */
    get enableWorkflow(){
        return this.viewModelData.enableWorkflow;
    }

    /**
     * 重定向视图引用集合
     *
     * @memberof IBizRedirectViewModel
     */
    get getRedirectPSAppViewRefs(){
        return this.viewModelData.getRedirectPSAppViewRefs;
    }
}