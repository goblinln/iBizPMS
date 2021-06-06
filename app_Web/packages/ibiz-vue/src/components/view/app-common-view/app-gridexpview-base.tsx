import { GridExpViewBase } from '../../../view/gridexpview-base';
import { Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { AppLayoutService } from '../../../app-service';

/**
 * 应用实体表格导航视图基类
 *
 * @export
 * @class AppGanttViewBase
 * @extends {GanttViewBase}
 */
export class AppGridExpViewBase extends GridExpViewBase {
    
    /**
     * 传入视图上下文
     *
     * @type {string}
     * @memberof AppGridExpViewBase
     */
    @Prop() public staticProps!: any;

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppGridExpViewBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppGridExpViewBase
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
     * @memberof AppGridExpViewBase
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
     * @memberof AppGridExpViewBase
     */
    public destroyed(){
        this.viewDestroyed();
    }

    /**
     * 表格导航视图渲染
     * 
     * @memberof AppGridExpViewBase
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