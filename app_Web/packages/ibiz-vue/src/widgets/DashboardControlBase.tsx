import { AppServiceBase, IBizDashboardModel, IBizEntityModel, IBizPortletModel, Util } from "ibiz-core";
import { UtilService } from "ibiz-service";
import { throwError } from "rxjs";
import { MainControlBase } from "./MainControlBase";

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
     * @type {IBizDashboardModel}
     * @memberof DashboardControlBase
     */
    public controlInstance!: IBizDashboardModel;

    /**
     * 是否支持看板定制
     *
     * @public
     * @type {(boolean)}
     * @memberof DashboardControlBase
     */
    public isEnableCustomized!:boolean;

    /**
     * 是否已有看板定制
     *
     * @public
     * @type {(boolean)}
     * @memberof DashboardControlBase
     */
    public isHasCustomized:boolean = false;

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
     * 建构功能服务对象
     *
     * @type {UtilService}
     * @memberof DashboardControlBase
     */
    public utilService:UtilService = new UtilService();

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
     * 初始化数据看板部件实例
     *
     * @memberof AppDefaultDashboard
     */
    public async ctrlModelInit(opts: any) {
        await super.ctrlModelInit();
        this.isEnableCustomized = this.controlInstance.enableCustomized;
        const { appDataEntity, codeName } = this.controlInstance;
        this.modelId = `dashboard_${appDataEntity?.codeName?.toLowerCase() || 'app'}_${codeName?.toLowerCase()}`;
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
        if(app.getAllPSAppPortlets?.length > 0){
            for(const portlet of app.getAllPSAppPortlets){
                // 门户部件实例
                let modelData = portlet.getPSControl;
                const portletInstance = new IBizPortletModel(modelData,null,null,{context: context})
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

    /**
     * 设置绘制完成状态
     *
     * @memberof DashboardControlBase
     */
    public setIsMounted(){
        this.ctrlEvent({
            controlname: this.controlInstance.name,
            action: 'controlIsMounted',
            data: true
        })
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
                let service = await this.utilService.getService(this.utilServiceName);
                let res = await service.loadModelData(JSON.parse(JSON.stringify(this.context)),{modelid:this.modelId,utilServiceName:this.utilServiceName});
                if(res && res.status == 200){
                    const data:any = res.data;
                    if(data && data.length >0){
                        for(const model of data){
                            let instance = this.getPortletInstance(model);
                            await instance.loaded();
                            model.modelData = instance;
                        }
                        this.customModelData = data;
                        this.isHasCustomized = true;
                        this.$forceUpdate();
                    }else{
                        throw new Error('data数据异常')
                    }
                }else{
                    throw new Error('服务器异常')
                }
            }
        } catch (error) {
            console.error("加载面板模型异常");
            console.error(error);
            this.isHasCustomized = false;
        } finally{
            this.notifyState();
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
            appdeNamePath: this.controlInstance?.appDataEntity?.path || 'app',
        }
        const dynamicProps = {
            customModel: this.customModelData,
            ...Util.getViewProps(this.context, viewparams)
        }
        const appdrawer = this.$appdrawer.openDrawer(view, dynamicProps);
        appdrawer.subscribe((result: any) => {
            if(Object.is(result.ret,'OK')){
                for(const model of result.datas){
                    model.modelData = new IBizPortletModel(model.modelData,null,null,{context: this.context})
                    model.modelData.loaded();
                }
                this.customModelData = [...result.datas];
                this.$forceUpdate();
                this.notifyState();
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