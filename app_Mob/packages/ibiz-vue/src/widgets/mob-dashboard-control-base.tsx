import { AppServiceBase, Util, ModelTool } from "ibiz-core";
import { UtilServiceRegister } from "ibiz-service";
import { MainControlBase } from "./main-control-base";
import { IPSAppPortlet, IPSDEDashboard } from "@ibiz/dynamic-model-api";

/**
 * 数据看板部件基类
 *
 * @export
 * @class AppControlBase
 * @extends {MobDashboardControlBase}
 */
export class MobDashboardControlBase extends MainControlBase {
    /**
     * 部件模型实例对象
     *
     * @type {IBizMobDashboardModel}
     * @memberof MobDashboardControlBase
     */
    public controlInstance!: IPSDEDashboard;

    /**
     * 是否支持看板定制
     *
     * @public
     * @type {(boolean)}
     * @memberof MobDashboardControlBase
     */
    public isEnableCustomized!: boolean;

    /**
     * 是否已有看板定制
     *
     * @public
     * @type {(boolean)}
     * @memberof MobDashboardControlBase
     */
    public isHasCustomized: boolean = false;

    /**
     * 自定义模型数据
     *
     * @public
     * @type {(*)}
     * @memberof MobDashboardControlBase
     */
    public customModelData: any;

    /**
     * 渲染定义模型数据
     *
     * @public
     * @type {(*)}
     * @memberof MobDashboardControlBase
     */
    public renderCustomModelData: any;



    /**
     * modleId
     *
     * @type {string}
     * @memberof MobDashboardControlBase
     */
    public modelId: string = "";

    /**
     * 功能服务名称
     *
     * @type {string}
     * @memberof MobDashboardControlBase
     */
    public utilServiceName: string = "dynadashboard";

    /**
     * 动态设计水平列数
     *
     *  @memberof MobDashboardControlBase
     */
    public layoutColNum: number = 12;

    /**
     * 动态设计单元格高度，80px
     *
     *  @memberof MobDashboardControlBase
     */
    public layoutRowH: number = 80;

    /**
     * 所有的门户部件实例集合
     *
     * @type {*}
     * @memberof MobDashboardControlBase
     */
    public portletList: any = [];

    /**
     * 设置已经绘制完成状态
     *
     * @memberof MobDashboardControlBase
     */
    public setIsMounted(name: string = 'self') {
        super.setIsMounted(name);
        if ([...this.mountedMap.values()].indexOf(false) == -1) {
          // 执行通知方法
          this.$nextTick(() => {
            this.notifyState();
          })
        }
    }

    /**
     * 初始化数据看板部件实例
     *
     * @memberof AppDefaultDashboard
     */
    public async ctrlModelInit(opts: any) {
        await super.ctrlModelInit();
        this.isEnableCustomized = this.controlInstance.enableCustomized;
        const { codeName } = this.controlInstance;
        const appDataEntity = this.controlInstance.getPSAppDataEntity();
        this.modelId = `dashboard_${appDataEntity?.codeName?.toLowerCase() || 'app'}_${codeName?.toLowerCase()}`;
        if (this.isEnableCustomized && !(this.Environment?.isPreviewMode)){
            await this.loadPortletList(this.context, this.viewparams);
        }
    }

    /**
     * 加载门户部件集合
     *
     * @memberof AppDashboardDesignService
     */
    public async loadPortletList(context: any, viewparams: any): Promise<any> {
        const app = AppServiceBase.getInstance().getAppModelDataObject();
        let list: any = [];
        if(app.getAllPSAppPortlets?.()?.length){
            for(const portlet of app.getAllPSAppPortlets() as IPSAppPortlet[]){
                // 门户部件实例
                const portletInstance = portlet.getPSControl();
                let temp: any = {
                    portletCodeName: portlet.codeName,
                    modelData: portletInstance,
                }
                list.push(temp);
            }
        }
        this.portletList = list;
    }

    /**
     * 获取门户部件实例
     *
     * @param {*} layoutModel
     * @returns
     * @memberof MobDashboardControlBase
     */
    public getPortletInstance(layoutModel: any) {
        return this.portletList.find((item: any) => { return layoutModel.portletCodeName === item.portletCodeName })?.modelData;
    }

    /**
     * 部件初始化
     *
     * @param {*} [args]
     * @memberof MobDashboardControlBase
     */
    public ctrlInit(args?: any) {
        super.ctrlInit(args);
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }: any) => {
                if (!Object.is(tag, this.name)) {
                    return;
                }
                if (Object.is('load', action)) {
                    this.loadModel();
                }
            });
        }
    }

    /**
     *  通知状态
     *
     *  @memberof MobDashboardControlBase
     */
    public notifyState() {
        setTimeout(() => {
            if (this.viewState) {
                const refs: any = this.$refs;
                Object.keys(refs).forEach((name: string) => {
                    this.viewState.next({
                        tag: name,
                        action: "load",
                        data: JSON.parse(JSON.stringify(this.viewparams))
                    });
                });
            }
        }, 0);
    }

    /**
     * 加载布局与数据模型
     *
     * @memberof MobDashboardControlBase
     */
    public async loadModel() {
        try {
            if (this.isEnableCustomized) {
                const service = await UtilServiceRegister.getInstance().getService(this.context,this.utilServiceName);
                let res = await service.loadModelData(JSON.parse(JSON.stringify(this.context)), { modelid: this.modelId, utilServiceName: this.utilServiceName });
                if (res && res.status == 200) {
                    const data: any = res.data;
                    if (data && data.length > 0) {
                        this.customModelData = JSON.parse(JSON.stringify(data));
                        this.renderCustomModelData = data;
                        for (const model of this.renderCustomModelData) {
                            model.modelData = this.getPortletInstance(model);
                        }
                        this.isHasCustomized = true;
                        this.$forceUpdate();
                    } else {
                        throw new Error(this.$t('app.error.dataError') as string)
                    }
                } else {
                    throw new Error(this.$t('app.commonWords.serverException') as string)
                }
            }
        } catch (error) {
            console.error(this.$t('app.error.loadPanelError'));
            console.error(error);
            this.isHasCustomized = false;
        } finally {
            this.notifyState();
        }
    }


    /**
     * 处理私人定制按钮
     *
     * @memberof MobDashboardControlBase
     */
    public async handleClick() {
        const view: any = {
            viewname: 'app-customize',
        }
        const viewparams: any = {
            modelid: this.modelId,
            utilServiceName: this.utilServiceName,
            appdeNamePath: this.controlInstance?.getPSAppDataEntity()?.codeName || 'app',
        }
        const param = {
            customModel: this.customModelData,
            ...Util.getViewProps(this.context, viewparams)
        }
        const result: any = await this.$appmodal.openModal(view,this.context, param);
        if (result || Object.is(result.ret, 'OK')) {
            this.loadModel();
        }
    }
}