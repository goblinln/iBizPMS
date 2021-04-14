import { IBizViewModelBase } from './ibiz-view-model-base';

/**
 * 应用门户视图模型类
 * 
 * @class IBizPortalViewModel
 */
export class IBizPortalViewModel extends IBizViewModelBase{

    /**
     * 加载模型数据
     * 
     * @memberof IBizPortalViewModel
     */
    public async loaded(){
        await super.loaded();
    }

    /**
     * 是否是应用起始页
     *
     * @readonly
     * @memberof IBizPortalViewModel
     */
    get defaultPage(){
        return this.viewModelData.defaultPage;
    }
}
