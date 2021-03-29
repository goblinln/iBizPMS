import { IBizMainControlModel } from '../ibiz-main-control-model';
import { IBizPortletModel } from '../ibiz-portlet-model/ibiz-portlet-model';

/**
 * 数据看板模型
 *
 * @export
 * @class IBizDashboardModel
 */
export class IBizDashboardModel extends IBizMainControlModel{

    /**
     * 获取布局设置
     *
     * @readonly
     * @memberof IBizPortletModel
     */
    get layout(){
        return this.controlModelData.getPSLayout;
    }

    /**
     * 是否支持自定义
     *
     * @readonly
     * @memberof IBizDashboardModel
     */
    get enableCustomized(){
        return this.controlModelData.enableCustomized;
    }

    /**
     * 是否显示处理提示
     *
     * @readonly
     * @memberof IBizDashboardModel
     */
    get showBusyIndicator(){
        return this.controlModelData?.getPSControlParam?.showBusyIndicator;
    }
}