import { IPSAppView, IPSDEList, IPSListExpBar } from "@ibiz/dynamic-model-api";
import { MobListExpBarControlInterface } from "../../../ibiz-core/src";
import { MobExpBarControlBase } from './mob-expbar-control-base';
/**
 * 列表导航栏部件基类
 *
 * @export
 * @class MobListExpBarControlBase
 * @extends {MainControlBase}
 */
export class MobListExpBarControlBase extends MobExpBarControlBase implements MobListExpBarControlInterface{

    /**
     * 列表导航栏的模型对象
     *
     * @type {*}
     * @memberof MobListExpBarControlBase
     */
    public controlInstance!: IPSListExpBar;

    /**
     * 数据部件
     *
     * @memberof ListExpBarControlBase
     */
    protected $xDataControl!: IPSDEList;

    /**
     * 计算目标部件所需参数
     *
     * @param {string} [controlType]
     * @returns
     * @memberof MobListExpBarControlBase
     */
    public computeTargetCtrlData(controlInstance: any) {
        const { targetCtrlName, targetCtrlParam, targetCtrlEvent } = super.computeTargetCtrlData(controlInstance);
        Object.assign(targetCtrlParam.staticProps, {
            listMode: "LISTEXPBAR"
        });
        return { targetCtrlName: targetCtrlName, targetCtrlParam: targetCtrlParam, targetCtrlEvent: targetCtrlEvent };
    }

    /**
     * load完成事件
     * 
     * @memberof MobListExpBarControlBase
     */
    public onLoad(args: any, tag?: string, $event2?: any) {
        if (!this.selection.view) {
            this.onSelectionChange(args);
        }
        if (this.$xDataControl) {
            this.$emit('ctrl-event', { controlname: this.$xDataControl.name, action: "load", data: args });
        }
    }

    /**
     * 处理数据部件参数
     *
     * @memberof GridExpBarControlBase
     */
    public async handleXDataCtrlOptions() {
        //TODO 导航关系
        const navPSAppView: IPSAppView = await this.$xDataControl?.getNavPSAppView()?.fill() as IPSAppView;
        if (navPSAppView) {
            this.navView = navPSAppView;
        }
        this.navFilter = this.$xDataControl?.navFilter ? this.$xDataControl.navFilter : "";
        // this.navPSDer = this.$xDataControl?.getNavPSDER ? this.$xDataControl.navPSDer : "";
    }

    /**
    * 执行搜索
    *
    * @memberof MobListExpBarControlBase
    */
    public onSearch() {
        const list: any = (this.$refs[`${this.$xDataControl.name.toLowerCase()}`] as any).ctrl;
        if (list) {
            this.viewState.next({ tag: this.$xDataControl.name, action: "load", data: { query: this.searchText } });
        }
    }

}
