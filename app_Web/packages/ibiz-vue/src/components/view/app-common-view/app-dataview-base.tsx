import { Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { DataViewBase } from '../../../view/dataview-base';
import { AppLayoutService } from '../../../app-service';


/**
 * 应用数据视图基类
 *
 * @export
 * @class AppDataViewExpViewBase
 * @extends {DataViewBase}
 */
export class AppDataViewBase extends DataViewBase {

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppDataViewBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 视图静态参数
     *
     * @type {string}
     * @memberof AppDataViewBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听视图动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppDataViewBase
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
     * @memberof AppDataViewBase
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
     * @memberof AppDataViewBase
     */
    public destroyed(){
        this.viewDestroyed();
    }

    /**
     * 数据视图渲染
     * 
     * @memberof AppDataViewBase
     */
    render(h: any) {
        if (!this.viewIsLoaded) {
            return null;
        }
        const targetViewLayoutComponent: any = AppLayoutService.getLayoutComponent(`${this.viewInstance.viewType}-${this.viewInstance.viewStyle}`);
        return h(targetViewLayoutComponent, {
            props: { viewInstance: this.viewInstance, viewparams: this.viewparams, context: this.context }
        }, [
            this.renderTopMessage(),
            this.renderToolBar(),
            this.renderQuickGroup(),
            this.renderQuickSearch(),
            this.renderQuickSearchForm(),
            this.viewInstance?.viewStyle == "STYLE2" ? [this.renderSearchForm(), this.renderSearchBar()] : null,
            this.renderBodyMessage(),
            this.renderMainContent(),
            this.renderBottomMessage()
        ]);
    }
}