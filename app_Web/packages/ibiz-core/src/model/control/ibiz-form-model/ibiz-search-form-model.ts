import { IBizFormModel } from './ibiz-form-model';

/**
 * 搜索表单部件
 */
export class IBizSearchFormModel extends IBizFormModel {

    /**
     * 获取搜索表单搜索行为
     * 
     * @memberof IBizSearchFormModel
     */
    get searchAction(){
        return this.findHandlerActionMethodCodeName("search");
    }
}
