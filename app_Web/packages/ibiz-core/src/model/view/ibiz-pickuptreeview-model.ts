import { IBizMdViewModel } from "./ibiz-md-view-model";

/**
 * 选择树视图模型类
 * 
 * @class IBizPickupTreeViewModel
 */
export class IBizPickupTreeViewModel extends IBizMdViewModel {

    /**
     * 加载模型数据（实体）
     * 
     * @memberof IBizPickupTreeViewModel
     */
    public async loaded() {
        await super.loaded();
    }

}