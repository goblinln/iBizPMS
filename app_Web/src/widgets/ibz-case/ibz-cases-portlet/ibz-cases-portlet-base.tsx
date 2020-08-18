import { Prop, Provide, Emit, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { Watch, MainControlBase } from '@/studio-core';
import IbzCaseService from '@/service/ibz-case/ibz-case-service';
import IbzCasesService from './ibz-cases-portlet-service';
import IbzCaseUIService from '@/uiservice/ibz-case/ibz-case-ui-service';
import { Environment } from '@/environments/environment';


/**
 * dashboard_sysportlet2部件基类
 *
 * @export
 * @class MainControlBase
 * @extends {IbzCasesPortletBase}
 */
export class IbzCasesPortletBase extends MainControlBase {

    /**
     * 获取部件类型
     *
     * @protected
     * @type {string}
     * @memberof IbzCasesPortletBase
     */
    protected controlType: string = 'PORTLET';

    /**
     * 建构部件服务对象
     *
     * @type {IbzCasesService}
     * @memberof IbzCasesPortletBase
     */
    public service: IbzCasesService = new IbzCasesService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {IbzCaseService}
     * @memberof IbzCasesPortletBase
     */
    public appEntityService: IbzCaseService = new IbzCaseService({ $store: this.$store });

    /**
     * 应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof IbzCasesPortletBase
     */
    protected appDeName: string = 'ibzcase';

    /**
     * 应用实体中文名称
     *
     * @protected
     * @type {string}
     * @memberof IbzCasesPortletBase
     */
    protected appDeLogicName: string = '用例库用例';

    /**
     * 界面UI服务对象
     *
     * @type {IbzCaseUIService}
     * @memberof IbzCasesBase
     */  
    public appUIService:IbzCaseUIService = new IbzCaseUIService(this.$store);

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public dashboard_sysportlet2_ufebc8a7_click(params: any = {}, tag?: any, $event?: any) {
        // 取数
        let datas: any[] = [];
        let xData: any = null;
        // _this 指向容器对象
        const _this: any = this;
        let paramJO:any = {};
        let contextJO:any = {};
        xData = this;
        if (_this.getDatas && _this.getDatas instanceof Function) {
            datas = [..._this.getDatas()];
        }
        if(params){
          datas = [params];
        }
        // 界面行为
        this.Exit(datas, contextJO,paramJO,  $event, xData,this,"IbzCase");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public dashboard_sysportlet2_u7a48919_click(params: any = {}, tag?: any, $event?: any) {
        // 取数
        let datas: any[] = [];
        let xData: any = null;
        // _this 指向容器对象
        const _this: any = this;
        let paramJO:any = {};
        let contextJO:any = {};
        xData = this;
        if (_this.getDatas && _this.getDatas instanceof Function) {
            datas = [..._this.getDatas()];
        }
        if(params){
          datas = [params];
        }
        // 界面行为
        const curUIService:IbzCaseUIService  = new IbzCaseUIService();
        curUIService.IbzCase_Editcase(datas,contextJO, paramJO,  $event, xData,this,"IbzCase");
    }

    /**
     * 逻辑事件
     *
     * @param {*} [params={}]
     * @param {*} [tag]
     * @param {*} [$event]
     * @memberof 
     */
    public dashboard_sysportlet2_u4c7dd78_click(params: any = {}, tag?: any, $event?: any) {
        // 取数
        let datas: any[] = [];
        let xData: any = null;
        // _this 指向容器对象
        const _this: any = this;
        let paramJO:any = {};
        let contextJO:any = {};
        xData = this;
        if (_this.getDatas && _this.getDatas instanceof Function) {
            datas = [..._this.getDatas()];
        }
        if(params){
          datas = [params];
        }
        // 界面行为
        const curUIService:IbzCaseUIService  = new IbzCaseUIService();
        curUIService.IbzCase_Delete(datas,contextJO, paramJO,  $event, xData,this,"IbzCase");
    }

    /**
     * 返回
     *
     * @param {any[]} args 当前数据
     * @param {any} contextJO 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @memberof IbzCaseDashboardViewBase
     */
    public Exit(args: any[],contextJO?:any, params?: any, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
        this.closeView(args);
        if(window.parent){
            window.parent.postMessage([{ ...args }],'*');
        }
    }


    /**
     * 长度
     *
     * @type {number}
     * @memberof IbzCases
     */
    @Prop() public height?: number;

    /**
     * 宽度
     *
     * @type {number}
     * @memberof IbzCases
     */
    @Prop() public width?: number;

    /**
     * 操作栏模型数据
     *
     * @returns {any[]}
     * @memberof IbzCasesBase
     */
    public actionBarModelData:any[] =[
        { viewlogicname:"dashboard_sysportlet2_ufebc8a7_click",
        text: "返回",
        iconcls: "fa fa-sign-out",
        icon: "",
        noprivdisplaymode: 2,
        visabled:true,
        disabled:false
        },
        { viewlogicname:"dashboard_sysportlet2_u7a48919_click",
        text: "编辑",
        iconcls: null,
        icon: null,
        noprivdisplaymode: 2,
        visabled:true,
        disabled:false
        },
        { viewlogicname:"dashboard_sysportlet2_u4c7dd78_click",
        text: "删除",
        iconcls: "fa fa-remove",
        icon: "",
        noprivdisplaymode: 2,
        visabled:true,
        disabled:false
        }
    ];

    /**
     * 触发界面行为
     *
     * @memberof IbzCasesBase
     */
    public handleItemClick($event:any){
        if(Object.is($event,'dashboard_sysportlet2_ufebc8a7_click')){
            this.dashboard_sysportlet2_ufebc8a7_click(null);
        }
        if(Object.is($event,'dashboard_sysportlet2_u7a48919_click')){
            this.dashboard_sysportlet2_u7a48919_click(null);
        }
        if(Object.is($event,'dashboard_sysportlet2_u4c7dd78_click')){
            this.dashboard_sysportlet2_u4c7dd78_click(null);
        }
    }


    /**
     * 是否自适应大小
     *
     * @returns {boolean}
     * @memberof IbzCasesBase
     */
    @Prop({default: false})public isAdaptiveSize!: boolean;

    /**
     * 获取多项数据
     *
     * @returns {any[]}
     * @memberof IbzCasesBase
     */
    public getDatas(): any[] {
        return [];
    }

    /**
     * 获取单项树
     *
     * @returns {*}
     * @memberof IbzCasesBase
     */
    public getData(): any {
        return {};
    }

    /**
     * 获取高度
     *
     * @returns {any[]}
     * @memberof IbzCasesBase
     */
    get getHeight(): any{
        if(!this.$util.isEmpty(this.height) && !this.$util.isNumberNaN(this.height)){
            if(this.height == 0){
                return 'auto';
            } else {
                return this.height+'px';
            }
        } else {
            return 'auto';
        }
    }

    /**
     * vue 生命周期
     *
     * @memberof IbzCasesBase
     */
    public created() {
        this.afterCreated();
    }

    /**
     * 执行created后的逻辑
     *
     *  @memberof IbzCasesBase
     */    
    public afterCreated(){
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }) => {
                if (!Object.is(tag, this.name)) {
                    return;
                }
                const refs: any = this.$refs;
                Object.keys(refs).forEach((_name: string) => {
                    this.viewState.next({ tag: _name, action: action, data: data });
                });
            });
        }
    }

    /**
     * vue 生命周期
     *
     * @memberof IbzCasesBase
     */
    public destroyed() {
        this.afterDestroy();
    }

    /**
     * 执行destroyed后的逻辑
     *
     * @memberof IbzCasesBase
     */
    public afterDestroy() {
        if (this.viewStateEvent) {
            this.viewStateEvent.unsubscribe();
        }
    }


}
