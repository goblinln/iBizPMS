import { DynamicService, Util } from '../../..';
import { IBizMainControlModel } from '../ibiz-main-control-model';

/**
 * 分页视图面板部件
 */
export class IBizTabViewPanelModel extends IBizMainControlModel {

    /**
     * 视图json
     *
     * @private
     * @type {*}
     * @memberof IBizTabViewPanelModel
     */
    private $embedView: any;


    /**
     * 模型初始化
     */
    public async loaded() {
        super.loaded();
        await this.getEmbedViewModel();
    }

    /**
     * 加载视图json
     *
     * @memberof IBizTabViewPanelModel
     */
    public async getEmbedViewModel() {
        const { getEmbeddedPSAppDEView: embedView } = this.controlModelData;
        if (embedView && embedView.path && embedView.modelref) {
            const embedViewData = await DynamicService.getInstance(this.context).getAppViewModelJsonData(embedView.path);
            embedViewData.modelref = false;
            this.$embedView = embedViewData;
        }
    }

    /**
     * 获取视图json
     * 
     * @readonly
     * @memberof IBizTabViewPanelModel
     */
    get embedView() {
        return this.$embedView;
    }

    /**
     * 过滤项
     * 
     * @readonly
     * @memberof IBizTabViewPanelModel
     */
    get navFilter() {
        return this.controlModelData.navFilter;
    }

    /**
     * 激活项
     * 
     * @readonly
     * @memberof IBizTabViewPanelModel
     */
    get isActivied() {
        return this.controlModelData.isActivied;
    }

    /**
     * 视图名称
     * 
     * @readonly
     * @memberof IBizTabViewPanelModel
     */
    get viewName() {
        return this.$embedView ? Util.srfFilePath2(this.$embedView.codeName) : "";
    }

    /**
     * tab标题
     * 
     * @readonly
     * @memberof IBizTabViewPanelModel
     */
    get caption(){
        return this.controlModelData.caption;
    }

    /**
     * 计数器
     * 
     * @readonly
     * @memberof IBizTabViewPanelModel
     */
    get getPSSysCounterRef(){
        return this.controlModelData.getPSSysCounterRef;
    }

    /**
     * 计数器标识
     * 
     * @readonly
     * @memberof IBizTabViewPanelModel
     */
    get counterId(){
        return this.controlModelData.counterId;
    }

    /**
     * 导航上下文参数集合
     * 
     * @readonly
     * @memberof IBizTabViewPanelModel
     */
    get getPSNavigateContexts(){
        return this.controlModelData.getPSNavigateContexts;
    }

    /**
     * 导航参数集合
     * 
     * @memberof IBizTabViewPanelModel
     */
    get getPSNavigateParams() {
        return this.controlModelData.getPSNavigateParams;
    }

    /**
     * 获取系统图片对象
     *
     * @readonly
     * @memberof IBizViewModelBase
     */
    get getPSSysImage(){
        return this.controlModelData.getPSSysImage;
    }

    /**
     * 内嵌视图
     *
     * @memberof IBizViewModelBase
     */
     get getEmbeddedPSAppDEView(){
        return this.controlModelData.getEmbeddedPSAppDEView;
    }
}
