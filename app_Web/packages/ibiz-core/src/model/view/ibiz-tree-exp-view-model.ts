import { IBizMdViewModel } from "./ibiz-md-view-model";

/**
 * 树导航导航视图模型
 * 
 * @class IBizPickUpViewModel
 */
export class IBizTreeExpViewModel extends IBizMdViewModel {

    /**
     * 获取树视图部件模型数据
     *
     * @returns
     * @memberof IBizTreeViewModel
     */
    public getTreeExpBar(){
        return this.getControlByName('treeexpbar');
    } 
}