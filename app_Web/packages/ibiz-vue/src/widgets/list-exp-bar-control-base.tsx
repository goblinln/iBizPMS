import { IPSAppView, IPSDEList, IPSListExpBar } from '@ibiz/dynamic-model-api';
import { ListExpBarControlInterface } from 'ibiz-core';
import { ExpBarControlBase } from './expbar-control-base';
/**
 * 列表导航栏部件基类
 *
 * @export
 * @class ListExpBarControlBase
 * @extends {MainControlBase}
 */
export class ListExpBarControlBase extends ExpBarControlBase implements ListExpBarControlInterface {

    /**
     * 列表导航栏的模型对象
     *
     * @type {*}
     * @memberof ListExpBarControlBase
     */
    public controlInstance!: IPSListExpBar;

    /**
     * 数据部件
     *
     * @memberof ListExpBarControlBase
     */
    protected $xDataControl!: IPSDEList;

    /**
     * 处理数据部件参数
     *
     * @memberof GridExpBarControlBase
     */
    public async handleXDataCtrlOptions() {
        //TODO 导航关系
        const navPSAppView: IPSAppView = await this.$xDataControl?.getNavPSAppView()?.fill() as IPSAppView;
        if (navPSAppView) {
            this.navViewName = navPSAppView.modelFilePath;
        }
        this.navFilter = this.$xDataControl?.navFilter ? this.$xDataControl.navFilter : "";
        // this.navPSDer = this.$xDataControl?.getNavPSDER ? this.$xDataControl.navPSDer : "";
    }

    /**
    * 执行搜索
    *
    * @memberof ListExpBarControlBase
    */
    public onSearch() {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        const list: any = (this.$refs[`${this.xDataControlName.toLowerCase()}`] as any).ctrl;
        if (list) {
            this.viewState.next({ tag: this.xDataControlName, action: "load", data: { query: this.searchText } });
        }
    }

}
