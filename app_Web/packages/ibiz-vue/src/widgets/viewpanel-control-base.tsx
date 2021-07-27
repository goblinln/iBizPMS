import { MainControlBase } from "./main-control-base";
import { IPSAppView, IPSDEViewPanel } from '@ibiz/dynamic-model-api';

/**
 * 嵌入视图面板部件基类
 *
 * @export
 * @class ViewPanelControlBase
 * @extends {MDControlBase}
 */
export class ViewPanelControlBase extends MainControlBase {

    /**
     * 面板的模型对象
     *
     * @type {*}
     * @memberof ViewPanelControlBase
     */
    public controlInstance!: IPSDEViewPanel;

    /**
     * 缓存UUID
     *
     * @type {*}
     * @memberof ViewPanelControlBase
     */
    public cacheUUID: any;

    /**
     * 嵌入视图模型路径
     *
     * @type {string}
     * @memberof ViewPanelControlBase
     */
    public embedViewPath: string = '';

    /**
     * 部件模型初始化
     *
     * @memberof ViewPanelControlBase
     */
    public async ctrlModelInit(args?: any) {
        await super.ctrlModelInit(args);
        await this.initEmbedViewPath();
    }

    /**
     * 初始化嵌入视图模型路径
     *
     * @memberof ViewPanelControlBase
     */
    public async initEmbedViewPath() {
        const embedView = this.controlInstance.getEmbeddedPSAppDEView?.() as IPSAppView;
        await embedView?.fill?.(true);
        this.embedViewPath = embedView.modelPath || '';
    }

}