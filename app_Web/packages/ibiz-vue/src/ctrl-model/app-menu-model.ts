import { IBizAppMenuModel } from 'ibiz-core';
/**
 * AppMenuModel 部件模型
 * 
 * @export
 * @class AppMenuModel
 */
export class AppMenuModel {

    /**
    * 菜单实例对象
    *
    * @memberof AppMenuModel
    */
    public MenuInstance !: IBizAppMenuModel;

    /**
    * Creates an instance of AppMenuModel.
    * 
    * @param {*} [opts={}]
    * @memberof AppGridModel
    */
    constructor(opts: any) {
        this.MenuInstance = opts;
    }
}