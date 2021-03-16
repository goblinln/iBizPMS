import { IBizMainViewModel } from "./ibiz-main-view-model";

/**
 * 数据多项选择视图模型类
 * 
 * @class IBizMPickUpViewModel
 */
export class IBizMPickUpViewModel extends IBizMainViewModel {

    /**
     * 选择视图面板
     * 
     * @memberof IBizMPickUpViewModel
     */
    private $pickUpViewPanel: any = {};

    /**
     * 加载模型数据（选择视图面板）
     * 
     * @memberof IBizMPickUpViewModel
     */
    public async loaded() {
        await super.loaded();
        this.$pickUpViewPanel = this.getControlByName('pickupviewpanel');
    }

    /**
     * 获取选择视图面板
     * 
     * @memberof IBizMPickUpViewModel
     */
    get viewPickUpViewPanel() {
        return this.$pickUpViewPanel;
    }

}