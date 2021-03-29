import { IBizMdViewModel } from './ibiz-md-view-model';

/**
 * 实体多表单编辑视图模型类
 * 
 * @class IBizMEditViewModel
 */
export class IBizMEditViewModel extends IBizMdViewModel {
    /**
     * 多编辑视图面板
     *
     * @memberof IBizMEditViewModel
     */
    private $meditViewPanel: any = {};

    /**
     * 加载模型数据（部件）
     *
     * @memberof IBizMEditViewModel
     */
    public async loaded() {
        await super.loaded();
        this.$meditViewPanel = this.getControlByName('meditviewpanel');
    }

    /**
     * 获取多编辑视图面板部件
     * 
     * @memberof IBizMEditViewModel
     */
    get viewMeditViewPanel(){
        return this.$meditViewPanel;
    }
}
