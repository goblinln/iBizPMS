import { IBizMdViewModel } from "./ibiz-md-view-model";

/**
 * 实体树视图模型类
 * 
 * @class IBizWizardViewModel
 */
export class IBizWizardViewModel extends IBizMdViewModel{
    
    /**
     * 加载模型数据（实体）
     * 
     * @memberof IBizWizardViewModel
     */
    public async loaded(){
        await super.loaded();
    }
}
