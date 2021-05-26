import { IPSAppPortlet, IPSDEDashboard } from "@ibiz/dynamic-model-api";
import { AppServiceBase, LogUtil, ModelTool, Util } from "ibiz-core";
import { UtilServiceRegister } from "ibiz-service";
import { MainControlBase } from "./main-control-base";

/**
 * 数据看板部件基类
 *
 * @export
 * @class ControlBase
 * @extends {DashboardControlBase}
 */
export class DashboardControlBase extends MainControlBase {
    /**
     * 部件模型实例对象
     *
     * @type {IPSDEDashboard}
     * @memberof DashboardControlBase
     */
    public controlInstance!: IPSDEDashboard;

    /**
     * 是否支持看板定制
     *
     * @public
     * @type {(boolean)}
     * @memberof DashboardControlBase
     */
    public isEnableCustomized!:boolean;

    /**
     * 看板定制状态描述
     *
     * @type {string} unknown 未确定， default 默认， custom 自定义
     * @memberof DashboardControlBase
     */
    public dashboardType:string = 'unknown';

    /**
     * 自定义模型数据
     *
     * @public
     * @type {(*)}
     * @memberof DashboardControlBase
     */
    public customModelData:any;

    /**
     * modleId
     *
     * @type {string}
     * @memberof DashboardControlBase
     */
    public modelId:string = "";

    /**
     * 功能服务名称
     *
     * @type {string}
     * @memberof DashboardControlBase
     */
    public utilServiceName:string = "dynadashboard";

    /**
     * 动态设计水平列数
     *
     *  @memberof DashboardControlBase
     */   
    public layoutColNum:number = 12;

    /**
     * 动态设计单元格高度，80px
     *
     *  @memberof DashboardControlBase
     */ 
    public layoutRowH:number = 80;

    /**
     * 所有的门户部件实例集合
     *
     * @type {*}
     * @memberof DashboardControlBase
     */
    public portletList:any = [];

    /**
     * 看板初始化时
     *
     * @memberof DashboardControlBase
     */
    public initMountedMap(){
        this.mountedMap.set('self', false);
    }

    /**
     * 初始化挂载状态集合(默认看板时)
     *
     * @memberof ControlBase
     */
    public initStaticMountedMap() {
        let controls = ModelTool.getAllPortlets(this.controlInstance);
        controls?.forEach((item: any) => {
            if (!(item.controlType == "PORTLET" && item.portletType == "CONTAINER")) {
                this.mountedMap.set(item.name, false);
            }
        })
    }

    /**
     * 初始化挂载状态集合(自定义动态看板时)
     *
     * @memberof ControlBase
     */
     public initDynamicMountedMap() {
        this.mountedMap.clear();
        this.customModelData.forEach((item: any)=>{
            let portlet = item.modelData;
            this.mountedMap.set(portlet.name, false)
        })
    }

    /**
     * 设置已经绘制完成状态
     *
     * @memberof ControlBase
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
        this.modelId = `dashboard_${this.appDeCodeName.toLowerCase() || 'app'}_${codeName?.toLowerCase()}`;
        if(this.isEnableCustomized){
            await this.loadPortletList(this.context,this.viewparams);
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
                const portletInstance = portlet.getPSControl()
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
     * @memberof DashboardControlBase
     */
    public getPortletInstance(layoutModel: any){
        return this.portletList.find((item: any)=>{ return layoutModel.i === item.portletCodeName})?.modelData;
    }

    /**
     * 部件初始化
     *
     * @param {*} [args]
     * @memberof DashboardControlBase
     */
    public ctrlInit(args?: any){
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

    public ctrlMounted(args?: any){
        super.ctrlMounted(args);
        // 不支持自定义时为默认看板
        if(!this.isEnableCustomized){
            this.dashboardType = 'default';
        }
    }


    /**
     *  通知状态
     *
     *  @memberof DashboardControlBase
     */    
    public notifyState(){
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
     * @memberof DashboardControlBase
     */
    public async loadModel(){
        try {
            if(this.isEnableCustomized){
                let service = await UtilServiceRegister.getInstance().getService(this.context,this.utilServiceName);
                let res = await service.loadModelData(JSON.parse(JSON.stringify(this.context)),{modelid:this.modelId,utilServiceName:this.utilServiceName});
                if(res && res.status == 200){
                    const data:any = res.data;
                    if(data && data.length >0){
                        for(const model of data){
                            model.modelData = this.getPortletInstance(model);
                        }
                        this.customModelData = data;
                        this.initDynamicMountedMap();
                        this.dashboardType = 'custom';
                        this.$forceUpdate();
                    }else{
                        throw new Error(this.$t('app.dashBoard.dataError'));
                    }
                }else{
                    throw new Error(this.$t('app.dashBoard.serviceError'));
                }
            }
        } catch (error) {
            LogUtil.warn(this.$t('app.dashBoard.loadError')+error);
            this.initStaticMountedMap();
            this.dashboardType = "default";
        }
    }

    /**
     * 处理私人定制按钮
     *
     * @memberof DashboardControlBase
     */
    public handleClick(){
        const view:any ={
            viewname: 'app-portal-design',
            title: (this.$t('app.dashBoard.handleClick.title')),
            width: 1600,
            placement: 'DRAWER_RIGHT'
        }
        const viewparams:any ={
            modelid: this.modelId,
            utilServiceName: this.utilServiceName,
            appdeNamePath: this.controlInstance?.getPSAppDataEntity()?.codeName || 'app',
        }
        const dynamicProps = {
            customModel: this.customModelData,
            ...Util.getViewProps(this.context, viewparams)
        }
        const appdrawer = this.$appdrawer.openDrawer(view, dynamicProps);
        appdrawer.subscribe((result: any) => {
            if(Object.is(result.ret,'OK')){
                if(result?.datas.length > 0){
                    for(const model of result.datas){
                        model.modelData = this.getPortletInstance(model);
                    }
                    this.customModelData = [...result.datas];
                    this.initDynamicMountedMap();
                    this.dashboardType = 'custom';
                }else{
                    this.initStaticMountedMap();
                    this.dashboardType = "default";
                }
                this.$forceUpdate();
            }
        });
    }

    /**
     * 部件事件
     * 
     * @param {string} controlname
     * @param {string} action
     * @param {*} data
     * @memberof KanbanControlBase
     */
    public onCtrlEvent(controlname: string, action: string, data: any) {
        if (action == 'refreshAll') {
            this.viewState.next({
                tag: 'all-portlet',
                action: "refreshAll",
                data: data
            });
        }else{
            super.onCtrlEvent(controlname, action, data);
        }
    }
}