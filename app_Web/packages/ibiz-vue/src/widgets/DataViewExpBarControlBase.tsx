import { IBizDataViewExpBarModel, Util } from 'ibiz-core';
import { ExpBarControlBase } from './ExpBarControlBase';
/**
 * 卡片导航栏部件基类
 *
 * @export
 * @class FormControlBase
 * @extends {MainControlBase}
 */
export class DataViewExpBarControlBase extends ExpBarControlBase {
    /**
     * 卡片视图导航栏的模型对象
     *
     * @type {*}
     * @memberof ListExpBarControlBase
     */
    public controlInstance!: IBizDataViewExpBarModel;

    /**
     * 部件事件处理
     *
     * @param {string} controlname
     * @param {string} action
     * @param {*} data
     * @memberof DataViewExpBarControlBase
     */
    public onCtrlEvent(controlname: string, action: string, data: any) {
        if(controlname != this.$xDataControl.name) {
            return;
        }
        super.onCtrlEvent(controlname, action, data);
    }

    /**
    * 执行搜索
    *
    * @memberof DataViewExpBarControlBase
    */
   public onSearch($event: any) {
    const dataview: any = (this.$refs[`${this.$xDataControl.name.toLowerCase()}`] as any).ctrl;
    if(dataview) {
        this.viewState.next({ tag: this.$xDataControl.name, action: "filter", data: {query : this.searchText}});
    }
}

}
