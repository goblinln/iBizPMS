import { Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { AppLayoutService } from '../../../app-service';
import { IndexViewBase } from '../../../view/indexview-base';

/**
 * 应用首页视图基类
 *
 * @export
 * @class AppIndexViewBase
 * @extends {IndexViewBase}
 */
export class AppIndexViewBase extends IndexViewBase {

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppIndexViewBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 视图静态参数
     *
     * @type {string}
     * @memberof AppIndexViewBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听视图动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppIndexViewBase
     */
    @Watch('dynamicProps',{
        immediate: true,
    })
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        if (newVal && !Util.isFieldsSame(newVal,oldVal)) {
           super.onDynamicPropsChange(newVal,oldVal);
        }
    }

    /**
     * 监听视图静态参数变化
     * 
     * @memberof AppIndexViewBase
     */
    @Watch('staticProps', {
        immediate: true,
    })
    public onStaticPropsChange(newVal: any, oldVal: any) {
        if (newVal && !Util.isFieldsSame(newVal,oldVal)) {
            super.onStaticPropsChange(newVal,oldVal);
        }
    }

    /**
     * 销毁视图回调
     *
     * @memberof AppIndexViewBase
     */
    public destroyed(){
        this.viewDestroyed();
    }

    /**
     * 应用首页视图渲染
     * 
     * @memberof AppIndexViewBase
     */
    render(h: any){
        if(!this.viewIsLoaded){
            return null;
        }
        const targetViewLayoutComponent: any = AppLayoutService.getLayoutComponent(`${this.viewInstance?.viewType}-${this.viewInstance?.viewStyle}`);
        return h(targetViewLayoutComponent, {
            props: { viewInstance: this.viewInstance, model: this.model, modelService: this.modelService, viewparams: this.viewparams, context: this.context },
            on: {
                onCollapseChange: (collapseChange: any)=>{this.collapseChange = collapseChange},
            }
        }, [
            this.renderMainContent()
        ]);
    }
}
