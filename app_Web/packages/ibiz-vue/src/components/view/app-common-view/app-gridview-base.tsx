import { Prop, Watch } from 'vue-property-decorator';
import { GridViewBase } from '../../../view/gridview-base';
import { AppLayoutService } from '../../../app-service';
import { Util } from 'ibiz-core';
import { CreateElement } from 'vue';

/**
 * 应用实体表格视图基类
 *
 * @export
 * @class AppGridViewBase
 * @extends {GridViewBase}
 */
export class AppGridViewBase extends GridViewBase {
    
    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppGridViewBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 视图静态参数
     *
     * @type {string}
     * @memberof AppGridViewBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听视图动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppGridViewBase
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
     * @memberof AppGridViewBase
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
     * @memberof AppGridViewBase
     */
    public destroyed(){
        this.viewDestroyed();
    }

    /**
     * 编辑视图渲染
     * 
     * @memberof AppGridViewBase
     */
    render(h: CreateElement) {
        if (!this.viewIsLoaded) {
            return null;
        }
        const targetViewLayoutComponent: any = AppLayoutService.getLayoutComponent(`${this.viewInstance.viewType}-${this.viewInstance.viewStyle}`);
        return h(targetViewLayoutComponent, {
            props: { viewInstance: this.viewInstance, model: this.model, modelService: this.modelService, viewparams: this.viewparams, context: this.context }
        }, [
            this.renderTopMessage(),
            this.renderToolBar(),
            this.renderQuickGroup(),
            this.renderQuickSearch(),
            this.renderQuickSearchForm(),
            !(this.viewInstance?.viewStyle == "DEFAULT" && this.viewInstance?.enableQuickSearch) ? [this.renderSearchForm(), this.renderSearchBar()] : null,
            this.renderBodyMessage(),
            this.renderMainContent(),
            this.renderBottomMessage()
        ]);
    }

}