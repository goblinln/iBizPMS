import { IBizMainViewModel } from "./ibiz-main-view-model";

/**
 * 选择视图模型类
 * 
 * @class IBizPickUpViewModel
 */
export class IBizPickUpViewModel extends IBizMainViewModel {

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