import { Subject } from 'rxjs';
import { ViewState, ViewTool, Util, ModelTool, LogUtil, MEditViewPanelControlInterface } from 'ibiz-core';
import { MDControlBase } from './md-control-base';
import { AppMEditViewPanelService } from '../ctrl-service';
import { IPSAppDEField, IPSAppView, IPSDEMultiEditViewPanel, IPSAppDEView } from '@ibiz/dynamic-model-api';
/**
 * 多编辑视图面板部件
 * 
 */
export class MEditViewPanelControlBase extends MDControlBase implements MEditViewPanelControlInterface{
    
    /**
     * 多编辑视图面板部件实例
     * 
     * @memberof MEditViewPanelControlBase
     */
    public controlInstance!: IPSDEMultiEditViewPanel;

    /**
     * 多编辑表单面板样式(默认行记录)
     * 
     * @memberof MEditViewPanelControlBase
     */
    public panelStyle: string = 'ROW';

    /**
     * 面板状态订阅对象
     *
     * @public
     * @type {Subject<{action: string, data: any}>}
     * @memberof MEditViewPanelControlBase
     */
    public panelState: Subject<ViewState> = new Subject();

    /**
     * 视图参数对象集合
     *
     * @type {any[]}
     * @memberof MEditViewPanelControlBase
     */
    public items: any[] = [];

    /**
     * 计数器
     *
     * @type number
     * @memberof MEditViewPanelControlBase
     */
    public count: number = 0;

     /**
     * 关系实体参数对象
     *
     * @public
     * @type {any[]}
     * @memberof MEditViewPanelControlBase
     */
    public deResParameters: any[] = [];

    /**
     * 当前应用视图参数对象
     *
     * @public
     * @type {any[]}
     * @memberof MEditViewPanelControlBase
     */
    public parameters: any[] = [];

    /**
     * 是否显示底部按钮
     *
     * @public
     * @type {any[]}
     * @memberof MEditViewPanelControlBase
     */
     public showButton: boolean = true;

    /**
     * 部件模型数据初始化
     *
     * @memberof MEditViewPanelControlBase
     */
    public async ctrlModelInit(args?:any) {
        await super.ctrlModelInit();
        if (!(this.Environment && this.Environment.isPreviewMode)) {
            this.service = new AppMEditViewPanelService(this.controlInstance, this.context);
        }
        // 加载嵌入视图的数据
        await this.controlInstance.getEmbeddedPSAppView()?.fill();
        this.initParameters();
    }

    /**
     * 多编辑视图面板初始化
     *
     * @memberof MEditViewPanelControlBase
     */    
    public ctrlInit(){
        super.ctrlInit();
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }: any) => {
                if (!Object.is(tag, this.name)) {
                    return;
                }
                if (Object.is(action, 'load')) {
                    this.load(data);
                }
                if (Object.is(action, 'save')) {
                    this.saveData(data);
                }
            });
        }
    }   

    /**
     * 初始化嵌入应用视图及实体参数对象
     *
     * @memberof MEditViewPanelControlBase
     */  
    public initParameters() {
        const emView = this.controlInstance.getEmbeddedPSAppView() as IPSAppView;
        const emViewEntity = emView?.getPSAppDataEntity();
        const ctrlParams = this.controlInstance.getPSControlParam()?.ctrlParams;
        if (ctrlParams && ctrlParams.SHOWBUTTON) {
          this.showButton = Object.is(ctrlParams.SHOWBUTTON,"false") ? false : true;
        }
        if (emView && emViewEntity){
            this.deResParameters = Util.formatAppDERSPath(this.context, (emView as IPSAppDEView).getPSAppDERSPaths());
            this.parameters = [{
                pathName : Util.srfpluralize(emViewEntity.codeName).toLowerCase(),
                parameterName: emViewEntity.codeName?.toLowerCase(),
                srfmajortext: (ModelTool.getAppEntityMajorField(emViewEntity) as IPSAppDEField).codeName?.toLowerCase(),
            }];
        } else {
            this.deResParameters = [];
            this.parameters = [];
        }
    }

    /**
     * 保存数据
     *
     * @param {*} [data] 数据
     * @memberof MEditViewPanelControlBase
     */
    public saveData(data?: any) {
        this.count = 0;
        if(this.items.length >0){
            Object.assign(data,{showResultInfo:false});
            this.panelState.next({ tag: this.controlInstance.getEmbeddedPSAppView()?.name || '', action: 'save', data: data });
        }else{
            this.ctrlEvent({controlname: this.controlInstance.name, action: "drdatasaved", data: {action:'drdatasaved'}});
        }
    }

    /**
     * 数据加载
     *
     * @public
     * @param {*} data 额外参数
     * @memberof MEditViewPanelControlBase
     */
    public load(data: any): void {
        if(!this.fetchAction){
            this.$throw(this.$t('app.multieditview.notconfig.fetchaction'),'load');
            return;
        }
        let arg: any = {};
        Object.assign(arg, data,{viewparams:this.viewparams});
        // 清空数据
        this.items = [];
        if (this.service) {
            let tempContext:any = JSON.parse(JSON.stringify(this.context));
            this.onControlRequset('load', tempContext, arg);
            const promice: Promise<any> = this.service.get(this.fetchAction, tempContext, arg, this.showBusyIndicator);
            promice.then((response: any) => {
                this.onControlResponse('load', response);
                if (!response.status || response.status !== 200) {
                    this.$throw(response,'load');
                    return;
                }
                if (response?.data?.length > 0) {
                    const items = Util.deepCopy(response.data);
                    this.doItems(items);
                }
                this.isControlLoaded = true;
                this.ctrlEvent({ controlname: this.controlInstance.name, action: "load", data: this.items });
            }).catch((response: any) => {
                this.onControlResponse('load', response);
                this.$throw(response,'load');
            });
        }
    }

    /**
     * 增加数据
     * 
     * @memberof MEditViewPanelControlBase
     */
    public handleAdd(){
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        if(!this.loaddraftAction){
            this.$throw(this.$t('app.multieditview.notconfig.loaddraftaction'),'handleAdd');
            return;
        }
        let tempContext: any = JSON.parse(JSON.stringify(this.context));
        let viewparams: any = JSON.parse(JSON.stringify(this.viewparams));
        this.onControlRequset('handleAdd', tempContext, viewparams);
        const promice: Promise<any> = this.service.loadDraft(this.loaddraftAction, tempContext, {viewparams: viewparams}, this.showBusyIndicator);
        promice.then((response: any) => {
            this.onControlResponse('handleAdd', response);
            if (!response.status || response.status !== 200) {
                this.$throw(response,'handleAdd');
                return;
            }
            const data: any = response.data;
            this.doItems([data]);
        }).catch((response: any) => {
            this.onControlResponse('handleAdd', response);
            this.$throw(response,'handleAdd');
        });
    }

     /**
     * 视图数据变更事件
     *
     * @param {*} $event 回调对象
     * @return {*} 
     * @memberof MEditViewPanelControlBase
     */
    public viewDataChange($event:any){
        // todo 统一标准后修改
        if(!$event){
            return
        }
        try{
            $event = JSON.parse($event);
        }catch(error){
            return;
        }
        if(Object.is($event.action,'save')){
            this.count++;
            if (this.items.length === this.count) {
                this.ctrlEvent({ controlname: this.controlInstance.name, action: "drdatasaved", data: {action:'save'} });
            }
        }
        if(Object.is($event.action,'remove')){
            // todo R7模板都错的，遇到场景在修改吧
            if ($event.data) {
                let resultIndex = this.items.findIndex((value:any, index:any, arr:any) => {
                    return value['viewdata']['orderdetailtestid'] === $event.data['orderdetailtestid'];
                });
                if (resultIndex !== -1) {
                    this.items.splice(resultIndex, 1);
                }
            }
        }            
    }

    /**
     * 视图加载完成
     *
     * @param {*} $event 回调对象
     * @memberof MEditViewPanelControlBase
     */
    public viewload($event:any){
        LogUtil.log(this.$t('components.appformdruipart.loadcomp'));
    }

    /**
     * 处理数据
     *
     * @public
     * @param {any[]} datas 数据集合
     * @memberof MEditViewPanelControlBase
     */
     public doItems(datas: any[]): void {
        // todo 同类数据处理可以抽个工具类
        const [{ pathName, parameterName }] = this.parameters;
        datas.forEach((arg: any) => {
            let id: string = arg[parameterName] ? arg[parameterName] : this.$util.createUUID();
            let item: any = { id: id, viewdata: {}, viewparam: {} };
            Object.assign(item.viewdata, ViewTool.getIndexViewParam());
            Object.assign(item.viewdata, this.context);

            // 关系应用实体参数
            this.deResParameters.forEach(({ pathName, parameterName }: { pathName: string, parameterName: string }) => {
                if (this.context[parameterName] && !Object.is(this.context[parameterName], '')) {
                    Object.assign(item.viewdata, { [parameterName]: this.context[parameterName] });
                } else if (arg[parameterName] && !Object.is(arg[parameterName], '')) {
                    Object.assign(item.viewdata, { [parameterName]: arg[parameterName] });
                }
            });
            
            // 当前视图参数（应用实体视图）
            this.parameters.forEach(({ pathName, parameterName, srfmajortext }: { pathName: string, parameterName: string, srfmajortext: string }) => {
                if (arg[parameterName] && !Object.is(arg[parameterName], '')) {
                    Object.assign(item.viewdata, { [parameterName]: arg[parameterName] });
                }
                // 当前页面实体主信息
                if (arg[srfmajortext] && !Object.is(arg[srfmajortext], '')) {
                    Object.assign(item, {srfmajortext: arg[srfmajortext]});
                }
            });

            //合并视图参数
            Object.assign(item.viewparam, this.viewparams);
            this.items.push(item);        
        });
    }

}