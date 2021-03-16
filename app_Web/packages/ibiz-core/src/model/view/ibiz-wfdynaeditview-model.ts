import { IBizMainViewModel } from "./ibiz-main-view-model";

/**
 * 工作流动态编辑视图模型类
 * 
 * @class IBizWFDynaEditViewModel
 */
export class IBizWFDynaEditViewModel extends IBizMainViewModel {

    /**
     * 加载模型数据（实体表单）
     * 
     * @memberof IBizWFDynaEditViewModel
     */
    public async loaded() {
        await super.loaded();
    }
    
}