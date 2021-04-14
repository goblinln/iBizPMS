import { DynamicService } from '../../../service';
import { IBizCodeListModel } from '../../common/ibiz-codelist-model';
import { IBizEntityModel } from '../../entity/ibiz-entity-model';
import { IBizMainControlModel } from '../ibiz-main-control-model';

/**
 * 上下文菜单部件模型
 *
 * @export
 * @class IBizContextMenuModel
 */
export class IBizContextMenuModel extends IBizMainControlModel {

    /**
     * Creates an instance of IBizContextMenuModel.
     * IBizContextMenuModel 实例
     * 
     * @param {*} [opts={}]
     * @memberof IBizContextMenuModel
     */
    constructor(opts: any = {}, viewRef?: any, parentRef?: any, runtimeData?: any) {
        super(opts, viewRef, parentRef, runtimeData);
    }

    /**
     * 获取菜单项集合
     *
     * @readonly
     * @memberof IBizContextMenuModel
     */
    get getPSDEToolbarItems() {
        return this.controlModelData.getPSDEToolbarItems;
    }

}