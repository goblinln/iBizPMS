import { Prop, Provide, Emit, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { Watch, CtrlBase } from '@/studio-core';
import ProductService from '@/service/product/product-service';
import TestTabExpViewtabviewpanel4Service from './test-tab-exp-viewtabviewpanel4-tabviewpanel-service';


/**
 * tabviewpanel4部件基类
 *
 * @export
 * @class CtrlBase
 * @extends {TestTabExpViewtabviewpanel4TabviewpanelBase}
 */
export class TestTabExpViewtabviewpanel4TabviewpanelBase extends CtrlBase {

    /**
     * 建构部件服务对象
     *
     * @type {TestTabExpViewtabviewpanel4Service}
     * @memberof TestTabExpViewtabviewpanel4TabviewpanelBase
     */
    public service: TestTabExpViewtabviewpanel4Service = new TestTabExpViewtabviewpanel4Service({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {ProductService}
     * @memberof TestTabExpViewtabviewpanel4TabviewpanelBase
     */
    public appEntityService: ProductService = new ProductService({ $store: this.$store });


 /**
     * 获取多项数据
     *
     * @returns {any[]}
     * @memberof TestTabExpViewtabviewpanel4
     */
    public getDatas(): any[] {
        return [];
    }

    /**
     * 获取单项树
     *
     * @returns {*}
     * @memberof TestTabExpViewtabviewpanel4
     */
    public getData(): any {
        return null;
    }

    /**
     * 是否被激活
     *
     * @type {boolean}
     * @memberof TestTabExpViewtabviewpanel4
     */
    public isActivied: boolean = true;

    /**
     * 局部上下文
     *
     * @type {*}
     * @memberof TestTabExpViewtabviewpanel4
     */
    public localContext: any = null;

    /**
     * 局部视图参数
     *
     * @type {*}
     * @memberof TestTabExpViewtabviewpanel4
     */
    public localViewParam: any = null;

    /**
     * 传入上下文
     *
     * @type {string}
     * @memberof TabExpViewtabviewpanel
     */
    public viewdata: string = JSON.stringify(this.context);

    /**
     * 传入视图参数
     *
     * @type {string}
     * @memberof PickupViewpickupviewpanel
     */
    public viewparam: string = JSON.stringify(this.viewparams);

    /**
     * 视图面板过滤项
     *
     * @type {string}
     * @memberof TestTabExpViewtabviewpanel4
     */
    public navfilter: string = "";
             
    /**
     * vue 生命周期
     *
     * @returns
     * @memberof TestTabExpViewtabviewpanel4
     */
    public created() {
        this.afterCreated();
    }

    /**
     * 执行created后的逻辑
     *
     *  @memberof TestTabExpViewtabviewpanel4
     */    
    public afterCreated(){
        this.initNavParam();
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }) => {
                if (!Object.is(tag, this.name)) {
                    return;
                }
                this.$forceUpdate();
                this.initNavParam();
            });
        }
    }

    /**
     * 初始化导航参数
     *
     * @memberof TestTabExpViewtabviewpanel4
     */
    public initNavParam(){
        if(!Object.is(this.navfilter,"")){
            Object.assign(this.viewparams,{[this.navfilter]:this.context['majorentity']})
        }
        if(this.localContext && Object.keys(this.localContext).length >0){
            let _context:any = this.$util.computedNavData({},this.context,this.viewparams,this.localContext);
            Object.assign(this.context,_context);
        }
        if(this.localViewParam && Object.keys(this.localViewParam).length >0){
            let _param:any = this.$util.computedNavData({},this.context,this.viewparams,this.localViewParam);
            Object.assign(this.viewparams,_param);
        }
        this.viewdata =JSON.stringify(this.context);
        this.viewparam = JSON.stringify(this.viewparams);
    }

    /**
     * 视图数据变化
     *
     * @memberof  TestTabExpViewtabviewpanel4
     */
    public viewDatasChange($event:any){
        this.$emit('viewpanelDatasChange',$event);
    }

    /**
     * vue 生命周期
     *
     * @memberof TestTabExpViewtabviewpanel4
     */
    public destroyed() {
        this.afterDestroy();
    }

    /**
     * 执行destroyed后的逻辑
     *
     * @memberof TestTabExpViewtabviewpanel4
     */
    public afterDestroy() {
        if (this.viewStateEvent) {
            this.viewStateEvent.unsubscribe();
        }
    }
}