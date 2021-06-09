import { IPSDataViewExpBar, IPSDEDataView } from '@ibiz/dynamic-model-api';
import { DataViewExpBarControlInterface } from 'ibiz-core';
import { ExpBarControlBase } from './expbar-control-base';
/**
 * 卡片导航栏部件基类
 *
 * @export
 * @class FormControlBase
 * @extends {MainControlBase}
 */
export class DataViewExpBarControlBase extends ExpBarControlBase implements DataViewExpBarControlInterface {
    /**
     * 卡片视图导航栏的模型对象
     *
     * @type {*}
     * @memberof DataViewExpBarControlBase
     */
    public controlInstance!: IPSDataViewExpBar;

    /**
     * 数据部件
     *
     * @memberof DataViewExpBarControlBase
     */
    protected $xDataControl!: IPSDEDataView;

    /**
     * 部件事件处理
     *
     * @param {string} controlname 部件
     * @param {string} action 行为
     * @param {*} data 数据
     * @memberof DataViewExpBarControlBase
     */
    public onCtrlEvent(controlname: string, action: string, data: any) {
        if (controlname != this.xDataControlName) {
            return;
        }
        super.onCtrlEvent(controlname, action, data);
    }

    /**
    * 执行搜索
    *
    * @memberof DataViewExpBarControlBase
    */
    public onSearch() {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        const dataview: any = (this.$refs[this.xDataControlName?.toLowerCase()] as any).ctrl;
        if (dataview) {
            this.viewState.next({ tag: this.xDataControlName, action: "filter", data: { query: this.searchText } });
        }
    }

}
