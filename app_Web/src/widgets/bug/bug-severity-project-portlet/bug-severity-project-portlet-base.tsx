import { Prop, Provide, Emit, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { Watch, MainControlBase } from '@/studio-core';
import BugService from '@/service/bug/bug-service';
import BugSeverity_ProjectService from './bug-severity-project-portlet-service';
import BugUIService from '@/uiservice/bug/bug-ui-service';
import { Environment } from '@/environments/environment';
import UIService from '@/uiservice/ui-service';
import { ViewTool } from '@/utils';


/**
 * dashboard_BugSeverity部件基类
 *
 * @export
 * @class MainControlBase
 * @extends {BugSeverity_ProjectPortletBase}
 */
export class BugSeverity_ProjectPortletBase extends MainControlBase {

    /**
     * 获取部件类型
     *
     * @protected
     * @type {string}
     * @memberof BugSeverity_ProjectPortletBase
     */
    protected controlType: string = 'PORTLET';

    /**
     * 建构部件服务对象
     *
     * @type {BugSeverity_ProjectService}
     * @memberof BugSeverity_ProjectPortletBase
     */
    public service: BugSeverity_ProjectService = new BugSeverity_ProjectService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {BugService}
     * @memberof BugSeverity_ProjectPortletBase
     */
    public appEntityService: BugService = new BugService({ $store: this.$store });

    /**
     * 应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof BugSeverity_ProjectPortletBase
     */
    protected appDeName: string = 'bug';

    /**
     * 应用实体中文名称
     *
     * @protected
     * @type {string}
     * @memberof BugSeverity_ProjectPortletBase
     */
    protected appDeLogicName: string = 'Bug';

    /**
     * 界面UI服务对象
     *
     * @type {BugUIService}
     * @memberof BugSeverity_ProjectBase
     */  
    public appUIService:BugUIService = new BugUIService(this.$store);


    /**
     * 长度
     *
     * @type {number}
     * @memberof BugSeverity_Project
     */
    @Prop() public height?: number;

    /**
     * 宽度
     *
     * @type {number}
     * @memberof BugSeverity_Project
     */
    @Prop() public width?: number;

    /**
     * 门户部件类型
     *
     * @type {number}
     * @memberof BugSeverity_ProjectBase
     */
    public portletType: string = 'chart';

    /**
     * 界面行为模型数据
     *
     * @memberof BugSeverity_ProjectBase
     */
    public uiactionModel: any = {
    }



    /**
     * 是否自适应大小
     *
     * @returns {boolean}
     * @memberof BugSeverity_ProjectBase
     */
    @Prop({default: false})public isAdaptiveSize!: boolean;

    /**
     * 获取多项数据
     *
     * @returns {any[]}
     * @memberof BugSeverity_ProjectBase
     */
    public getDatas(): any[] {
        return [];
    }

    /**
     * 获取单项树
     *
     * @returns {*}
     * @memberof BugSeverity_ProjectBase
     */
    public getData(): any {
        return {};
    }

    /**
     * 获取高度
     *
     * @returns {any[]}
     * @memberof BugSeverity_ProjectBase
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
     * @memberof BugSeverity_ProjectBase
     */
    public created() {
        this.afterCreated();
    }

    /**
     * 执行created后的逻辑
     *
     *  @memberof BugSeverity_ProjectBase
     */    
    public afterCreated(){
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }) => {
                if(Object.is(tag, "all-portlet") && Object.is(action,'loadmodel')){
                   this.calcUIActionAuthState(data);
                }
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
     * @memberof BugSeverity_ProjectBase
     */
    public destroyed() {
        this.afterDestroy();
    }

    /**
     * 执行destroyed后的逻辑
     *
     * @memberof BugSeverity_ProjectBase
     */
    public afterDestroy() {
        if (this.viewStateEvent) {
            this.viewStateEvent.unsubscribe();
        }
    }

    /**
     * 计算界面行为权限
     *
     * @memberof BugSeverity_ProjectBase
     */
    public calcUIActionAuthState(data:any = {}) {
        //  如果是操作栏，不计算权限
        if(this.portletType && Object.is('actionbar', this.portletType)) {
            return;
        }
        let _this: any = this;
        let uiservice: any = _this.appUIService ? _this.appUIService : new UIService(_this.$store);
        if(_this.uiactionModel){
            ViewTool.calcActionItemAuthState(data,_this.uiactionModel,uiservice);
        }
    }


}
