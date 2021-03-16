import { DynamicService } from '../../../service';
import { ControlFactory } from '../../../utils';
import { IBizExpBarModel } from '../ibiz-exp-bar-model/ibiz-exp-bar-model';
import { IBizTreeModel } from '../ibiz-tree-model/ibiz-tree-model';

/**
 * 树视图导航部件模型
 *
 * @export
 * @class IBizTreeExpBarModel
 */
export class IBizTreeExpBarModel extends IBizExpBarModel {

    /**
     * Creates an instance of IBizTreeExpBarModel.
     * IBizTreeExpBarModel 实例
     * 
     * @param {*} [opts={}]
     * @memberof IBizTreeExpBarModel
     */
    constructor(opts: any = {}, viewRef?: any, parentRef?: any, runtimeData?: any) {
        super(opts, viewRef, parentRef, runtimeData);
    }

    /**
     * 加载模型数据(应用实体)
     *
     * @memberof IBizExpBarModel
     */
    public async loaded() {
        await super.loaded();
    }

}