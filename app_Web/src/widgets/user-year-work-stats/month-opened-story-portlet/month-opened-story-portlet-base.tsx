import { Prop, Provide, Emit, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { Watch, MainControlBase } from '@/studio-core';
import UserYearWorkStatsService from '@/service/user-year-work-stats/user-year-work-stats-service';
import MonthOpenedStoryService from './month-opened-story-portlet-service';
import UserYearWorkStatsUIService from '@/uiservice/user-year-work-stats/user-year-work-stats-ui-service';
import { Environment } from '@/environments/environment';
import UIService from '@/uiservice/ui-service';
import { ViewTool } from '@/utils';


/**
 * dashboard_sysportlet5部件基类
 *
 * @export
 * @class MainControlBase
 * @extends {MonthOpenedStoryPortletBase}
 */
export class MonthOpenedStoryPortletBase extends MainControlBase {

    /**
     * 获取部件类型
     *
     * @protected
     * @type {string}
     * @memberof MonthOpenedStoryPortletBase
     */
    protected controlType: string = 'PORTLET';

    /**
     * 建构部件服务对象
     *
     * @type {MonthOpenedStoryService}
     * @memberof MonthOpenedStoryPortletBase
     */
    public service: MonthOpenedStoryService = new MonthOpenedStoryService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {UserYearWorkStatsService}
     * @memberof MonthOpenedStoryPortletBase
     */
    public appEntityService: UserYearWorkStatsService = new UserYearWorkStatsService({ $store: this.$store });

    /**
     * 应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof MonthOpenedStoryPortletBase
     */
    protected appDeName: string = 'useryearworkstats';

    /**
     * 应用实体中文名称
     *
     * @protected
     * @type {string}
     * @memberof MonthOpenedStoryPortletBase
     */
    protected appDeLogicName: string = '用户年度工作内容统计';

    /**
     * 界面UI服务对象
     *
     * @type {UserYearWorkStatsUIService}
     * @memberof MonthOpenedStoryBase
     */  
    public appUIService:UserYearWorkStatsUIService = new UserYearWorkStatsUIService(this.$store);

    /**
     * 长度
     *
     * @type {number}
     * @memberof MonthOpenedStory
     */
    @Prop() public height?: number;

    /**
     * 宽度
     *
     * @type {number}
     * @memberof MonthOpenedStory
     */
    @Prop() public width?: number;

    /**
     * 门户部件类型
     *
     * @type {number}
     * @memberof MonthOpenedStoryBase
     */
    public portletType: string = 'chart';

    /**
     * 界面行为模型数据
     *
     * @memberof MonthOpenedStoryBase
     */
    public uiactionModel: any = {
    }



    /**
     * 是否自适应大小
     *
     * @returns {boolean}
     * @memberof MonthOpenedStoryBase
     */
    @Prop({default: false})public isAdaptiveSize!: boolean;

    /**
     * 获取多项数据
     *
     * @returns {any[]}
     * @memberof MonthOpenedStoryBase
     */
    public getDatas(): any[] {
        return [];
    }

    /**
     * 获取单项树
     *
     * @returns {*}
     * @memberof MonthOpenedStoryBase
     */
    public getData(): any {
        return {};
    }

    /**
     * 获取高度
     *
     * @returns {any[]}
     * @memberof MonthOpenedStoryBase
     */
    get getHeight(): any{
        if(!this.$util.isEmpty(this.height) && !this.$util.isNumberNaN(this.height)){
            if(this.height == 0){
                return 'auto';
            } else {
                return this.height+'px';
            }
        } else {
            return '350px';
        }
    }

    /**
     * vue 生命周期
     *
     * @memberof MonthOpenedStoryBase
     */
    public created() {
        this.afterCreated();
    }

    /**
     * 执行created后的逻辑
     *
     *  @memberof MonthOpenedStoryBase
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
     * @memberof MonthOpenedStoryBase
     */
    public destroyed() {
        this.afterDestroy();
    }

    /**
     * 执行destroyed后的逻辑
     *
     * @memberof MonthOpenedStoryBase
     */
    public afterDestroy() {
        if (this.viewStateEvent) {
            this.viewStateEvent.unsubscribe();
        }
    }

    /**
     * 计算界面行为权限
     *
     * @memberof MonthOpenedStoryBase
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
