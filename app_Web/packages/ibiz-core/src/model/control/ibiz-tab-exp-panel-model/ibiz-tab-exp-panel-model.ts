import { IBizMainControlModel } from '../ibiz-main-control-model';
import { IBizTabViewPanelModel } from '../ibiz-tab-view-panel-model/ibiz-tab-view-panel-model';

/**
 * 分页导航面板部件
 */
export class IBizTabExpPanelModel extends IBizMainControlModel {

    /**
     * 所有部件
     *
     * @readonly
     * @memberof IBizTabExpPanelModel
     */
    get allControls() {
        return this.controlModelData.getPSControls;
    }

    /**
     * 分页视图面板集合
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IBizTabExpPanelModel
     */
    private $tabViewPanels: Map<string, any> = new Map();

    /**
     * 模型初始化
     * 
     * @memberof IBizTabExpPanelModel
     */
    public async loaded() {
        // 不调用父类loaded 以免加载冗余请求
        await this.loadAppDataEntity();
        if (this.allControls.length > 0) {
            for (let index = 0; index < this.allControls.length; index++) {
                const tabViewPanel = this.allControls[index];
                tabViewPanel['isActivied'] = index === 0 ? true : false;
                const tabViewPanelModel = new IBizTabViewPanelModel(tabViewPanel, this.getView(), this, { context: this.context })
                this.$tabViewPanels.set(tabViewPanelModel.codeName, tabViewPanelModel);
            }
        }
    }

    /**
     * 获取分页视图面板集合
     *
     * @readonly
     * @memberof IBizTabExpPanelModel
     */
    get tabViewPanels() {
        return [...this.$tabViewPanels.values()];
    }
}
