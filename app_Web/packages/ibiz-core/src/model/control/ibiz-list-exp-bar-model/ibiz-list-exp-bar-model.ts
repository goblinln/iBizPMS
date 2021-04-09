import { DynamicService } from '../../../../../ibiz-core/src/service';
import { IBizExpBarModel } from '../ibiz-exp-bar-model/ibiz-exp-bar-model';

/**
 * 列表导航面板部件
 */
export class IBizListExpBarModel extends IBizExpBarModel {
    /**
     * 列表部件模型
     */
    public $list!: any;

    /**
     * 导航视图
     */
    public $navView: any = {};

    /**
     * 工具栏
     */

    private $toolbar: any = {};

    /**
     * 加载模型参数
     *
     * @private
     * @memberof IBizListExpBarModel
     */
    public async loaded() {
        await super.loaded();

        // list模型
        for (let index = 0; index < this.controlModelData?.getPSControls?.length; index++) {
            const control: any = this.controlModelData?.getPSControls[index];
            if ((control?.name == 'listexpbar_list')) {
                if (control.path) {
                    const res = await DynamicService.getInstance(this.context).getAppCtrlModelJsonData(control.path);
                    Object.assign(control, res);
                }
            }
        }
        this.$list = this.controlModelData.getPSControls?.find((item: any) => {
            return Object.is(item.controlType, 'LIST');
        });
        if (this.$list && this.$list.getPSAppDataEntity) {
            const res = await DynamicService.getInstance(this.context).getAppEntityModelJsonData(this.$list.getPSAppDataEntity.path);
            Object.assign(this.$list.getPSAppDataEntity, res);
        }

        // 导航视图
        if (this.controlModelData.getXDataPSControl) {
            const res = await DynamicService.getInstance(this.context).getAppCtrlModelJsonData(this.controlModelData.getXDataPSControl.path);
            Object.assign(this.controlModelData.getXDataPSControl, res);
        }
        if (this.controlModelData.getXDataPSControl?.getNavPSAppView) {
            const res = await DynamicService.getInstance(this.context).getAppViewModelJsonData(this.controlModelData.getXDataPSControl.getNavPSAppView.path);
            this.$navView = Object.assign(this.controlModelData.getXDataPSControl.getNavPSAppView, res);
        }

        // 工具栏
        this.$toolbar = this.controls.find((item: any) => {
            return item.controlType == 'TOOLBAR';
        });
    }

    /**
     * 列表
     */

    get list() {
        return this.$list;
    }

    /**
     * 导航视图
     */
    get navView() {
        return this.$navView;
    }

    /**
     * 工具栏
     */
    get toolbar() {
        return this.$toolbar;
    }
}
