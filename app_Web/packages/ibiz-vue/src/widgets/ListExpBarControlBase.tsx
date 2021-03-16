import { IBizListExpBarModel } from "ibiz-core";
import { ExpBarControlBase } from './ExpBarControlBase';
/**
 * 列表导航栏部件基类
 *
 * @export
 * @class ListExpBarControlBase
 * @extends {MainControlBase}
 */
export class ListExpBarControlBase extends ExpBarControlBase {

    /**
     * 分割宽度
     *
     * @type {number}
     * @memberof ExpBarControlBase
     */
    public split: number = 0.2;
    
    /**
     * 列表导航栏的模型对象
     *
     * @type {*}
     * @memberof ListExpBarControlBase
     */
    public controlInstance!: IBizListExpBarModel;


    /**
    * 执行搜索
    *
    * @memberof ListExpBarControlBase
    */
    public onSearch($event: any) {
        const list: any = (this.$refs[`${this.$xDataControl.name.toLowerCase()}`] as any).ctrl;
        if(list) {
            this.viewState.next({ tag: this.$xDataControl.name, action: "load", data: {query : this.searchText}});
        }
    }

}
