import { IBizMdViewModel } from "./ibiz-md-view-model";

/**
 * 实体树视图模型类
 * 
 * @class IBizTreeViewModel
 */
export class IBizTreeViewModel extends IBizMdViewModel{
    
    /**
     * 加载模型数据（实体）
     * 
     * @memberof IBizTreeViewModel
     */
    public async loaded(){
        await super.loaded();
    }
}
