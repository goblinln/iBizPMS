import { Prop, Provide, Emit, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { Watch, MainControlBase } from '@/studio-core';
import StoryService from '@/service/story/story-service';
import StoryRelatedService from './story-related-portlet-service';
import StoryUIService from '@/uiservice/story/story-ui-service';
import { Environment } from '@/environments/environment';
import UIService from '@/uiservice/ui-service';
import { ViewTool } from '@/utils';


/**
 * dashboard_sysportlet5部件基类
 *
 * @export
 * @class MainControlBase
 * @extends {StoryRelatedPortletBase}
 */
export class StoryRelatedPortletBase extends MainControlBase {

    /**
     * 获取部件类型
     *
     * @protected
     * @type {string}
     * @memberof StoryRelatedPortletBase
     */
    protected controlType: string = 'PORTLET';

    /**
     * 建构部件服务对象
     *
     * @type {StoryRelatedService}
     * @memberof StoryRelatedPortletBase
     */
    public service: StoryRelatedService = new StoryRelatedService({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {StoryService}
     * @memberof StoryRelatedPortletBase
     */
    public appEntityService: StoryService = new StoryService({ $store: this.$store });

    /**
     * 应用实体名称
     *
     * @protected
     * @type {string}
     * @memberof StoryRelatedPortletBase
     */
    protected appDeName: string = 'story';

    /**
     * 应用实体中文名称
     *
     * @protected
     * @type {string}
     * @memberof StoryRelatedPortletBase
     */
    protected appDeLogicName: string = '需求';

    /**
     * 界面UI服务对象
     *
     * @type {StoryUIService}
     * @memberof StoryRelatedBase
     */  
    public appUIService:StoryUIService = new StoryUIService(this.$store);


    /**
     * 长度
     *
     * @type {number}
     * @memberof StoryRelated
     */
    @Prop() public height?: number;

    /**
     * 宽度
     *
     * @type {number}
     * @memberof StoryRelated
     */
    @Prop() public width?: number;

    /**
     * 门户部件类型
     *
     * @type {number}
     * @memberof StoryRelatedBase
     */
    public portletType: string = 'view';

    /**
     * 界面行为模型数据
     *
     * @memberof StoryRelatedBase
     */
    public uiactionModel: any = {
    }



    /**
     * 是否自适应大小
     *
     * @returns {boolean}
     * @memberof StoryRelatedBase
     */
    @Prop({default: false})public isAdaptiveSize!: boolean;

    /**
     * 获取多项数据
     *
     * @returns {any[]}
     * @memberof StoryRelatedBase
     */
    public getDatas(): any[] {
        return [];
    }

    /**
     * 获取单项树
     *
     * @returns {*}
     * @memberof StoryRelatedBase
     */
    public getData(): any {
        return {};
    }

    /**
     * 获取高度
     *
     * @returns {any[]}
     * @memberof StoryRelatedBase
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
     * @memberof StoryRelatedBase
     */
    public created() {
        this.afterCreated();
    }

    /**
     * 执行created后的逻辑
     *
     *  @memberof StoryRelatedBase
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
     * @memberof StoryRelatedBase
     */
    public destroyed() {
        this.afterDestroy();
    }

    /**
     * 执行destroyed后的逻辑
     *
     * @memberof StoryRelatedBase
     */
    public afterDestroy() {
        if (this.viewStateEvent) {
            this.viewStateEvent.unsubscribe();
        }
    }

    /**
     * 计算界面行为权限
     *
     * @memberof StoryRelatedBase
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
