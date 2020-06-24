import { Prop, Provide, Emit, Watch, Model } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { CtrlBase } from '@/studio-core';
import ProductService from '@/service/product/product-service';
import TestTabExpViewtabviewpanel2Service from './test-tab-exp-viewtabviewpanel2-tabviewpanel-service';


/**
 * tabviewpanel2部件基类
 *
 * @export
 * @class CtrlBase
 * @extends {TestTabExpViewtabviewpanel2TabviewpanelBase}
 */
export class TestTabExpViewtabviewpanel2TabviewpanelBase extends CtrlBase {

    /**
     * 建构部件服务对象
     *
     * @type {TestTabExpViewtabviewpanel2Service}
     * @memberof TestTabExpViewtabviewpanel2TabviewpanelBase
     */
    public service: TestTabExpViewtabviewpanel2Service = new TestTabExpViewtabviewpanel2Service({ $store: this.$store });

    /**
     * 实体服务对象
     *
     * @type {ProductService}
     * @memberof TestTabExpViewtabviewpanel2TabviewpanelBase
     */
    public appEntityService: ProductService = new ProductService({ $store: this.$store });


 /**
     * 获取多项数据
     *
     * @returns {any[]}
     * @memberof TestTabExpViewtabviewpanel2
     */
    public getDatas(): any[] {
        return [];
    }

    /**
     * 获取单项树
     *
     * @returns {*}
     * @memberof TestTabExpViewtabviewpanel2
     */
    public getData(): any {
        return null;
    }

    /**
     * 是否被激活
     *
     * @type {boolean}
     * @memberof TestTabExpViewtabviewpanel2
     */
    public isActivied: boolean = true;

    /**
     * 局部上下文
     *
     * @type {*}
     * @memberof TestTabExpViewtabviewpanel2
     */
    public localContext: any = null;

    /**
     * 局部视图参数
     *
     * @type {*}
     * @memberof TestTabExpViewtabviewpanel2
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
     * @memberof TestTabExpViewtabviewpanel2
     */
    public navfilter: string = "";
             
    /**
     * vue 生命周期
     *
     * @returns
     * @memberof TestTabExpViewtabviewpanel2
     */
    public created() {
        this.afterCreated();
    }

    /**
     * 执行created后的逻辑
     *
     *  @memberof TestTabExpViewtabviewpanel2
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
     * @memberof TestTabExpViewtabviewpanel2
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
     * @memberof  TestTabExpViewtabviewpanel2
     */
    public viewDatasChange($event:any){
        this.$emit('viewpanelDatasChange',$event);
    }

    /**
     * vue 生命周期
     *
     * @memberof TestTabExpViewtabviewpanel2
     */
    public destroyed() {
        this.afterDestroy();
    }

    /**
     * 执行destroyed后的逻辑
     *
     * @memberof TestTabExpViewtabviewpanel2
     */
    public afterDestroy() {
        if (this.viewStateEvent) {
            this.viewStateEvent.unsubscribe();
        }
    }
}