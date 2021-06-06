import { Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { ListExpViewBase } from '../../../view/listexpview-base';
import { AppLayoutService } from '../../../app-service';

/**
 * 应用列表导航视图基类
 *
 * @export
 * @class AppListExpViewBase
 * @extends {ListExpViewBase}
 */
export class AppListExpViewBase extends ListExpViewBase {


    /**
     * 传入视图上下文
     *
     * @type {string}
     * @memberof AppListExpViewBase
     */
    @Prop() public staticProps!: any;

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppListExpViewBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppListExpViewBase
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
     * 监听静态参数变化
     * 
     * @memberof AppListExpViewBase
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
     * @memberof AppListExpViewBase
     */
    public destroyed(){
        this.viewDestroyed();
    }

    /**
     * 分页导航视图渲染
     * 
     * @memberof AppListExpViewBase
     */
    render(h: any) {
        if (!this.viewIsLoaded) {
            return null;
        }
        const targetViewLayoutComponent: any = AppLayoutService.getLayoutComponent(`${this.viewInstance.viewType}-${this.viewInstance.viewStyle}`);
        return h(targetViewLayoutComponent, {
                props: { viewInstance: this.viewInstance, model: this.model, modelService: this.modelService, viewparams: this.viewparams, context: this.context }
            }, [
                this.renderTopMessage(),
                this.renderBodyMessage(),
                this.renderToolBar(),
                this.renderMainContent(),
                this.renderBottomMessage(),
        ]);
    }
}
