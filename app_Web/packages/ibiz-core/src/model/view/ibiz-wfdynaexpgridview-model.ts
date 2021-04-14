import { IBizMdViewModel } from "./ibiz-md-view-model";

/**
 * 实体工作流动态导航表格视图模型类
 * 
 * @class IBizWfDynaExpGridViewModel
 */
export class IBizWfDynaExpGridViewModel extends IBizMdViewModel {

    /**
     * 加载模型数据（实体表格导航）
     * 
     * @memberof IBizWfDynaExpGridViewModel
     */
    public async loaded(){
        await super.loaded();
    }

}