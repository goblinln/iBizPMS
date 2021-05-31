import { MainControlBase } from "./main-control-base";
import { IPSAppDEView, IPSDETabViewPanel } from "@ibiz/dynamic-model-api";

/**
 * 分页视图面板基类
 *
 * @export
 * @class AppControlBase
 * @extends {TabViewPanelControlBase}
 */
export class MobTabViewPanelControlBase extends MainControlBase {

    /**
     * 部件模型
     *
     * @type {AppTabViewPanelBase}
     * @memberof MobTabViewPanelControlBase
     */
    public controlInstance!: IPSDETabViewPanel;

    /**
     * 是否激活
     *
     * @memberof MobTabViewPanelControlBase
     */
    public isActivied = false;

    /**
     * 部件挂载
     *
     * @param {*} [args]
     * @memberof MobTabViewPanelControlBase
     */
    public ctrlMounted(args?: any) {
        this.isActivied = this.dynamicProps.isActivied;
    }

    /**
     * 部件模型数据初始化实例
     *
     * @memberof MobTabViewPanelControlBase
     */
    public async ctrlModelInit(args?: any) {
        await super.ctrlModelInit();
        const embedView: IPSAppDEView = this.controlInstance.getEmbeddedPSAppDEView() as IPSAppDEView;
        if (embedView && !embedView.name) {
            await embedView.fill();
        }
    }    

    /**
     * 部件初始化
     *
     * @param {*} [args]
     * @memberof MobTabViewPanelControlBase
     */
    public ctrlInit(args?: any) {
        super.ctrlInit(args);
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }: any) => {
                if (data.activeItem === this.staticProps.modelData.name) {
                    this.$nextTick(() => {
                        this.actived();
                    });
                } else {
                    this.isActivied = false;
                }
            });
        }
    }

    /**
     * 激活
     *
     * @param {*} [args]
     * @memberof MobTabViewPanelControlBase
     */
    public async actived(){
        const embedView: IPSAppDEView = this.controlInstance.getEmbeddedPSAppDEView() as IPSAppDEView;
        if (embedView && !embedView.name) {
            await embedView.fill();
        }
        this.isActivied = true;
    }

}