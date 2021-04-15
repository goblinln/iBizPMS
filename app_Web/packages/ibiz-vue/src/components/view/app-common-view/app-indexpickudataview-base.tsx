import { Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { AppLayoutService } from '../../../app-service';
import { IndexPickupDataViewBase } from '../../../view/indexpickupdataview-base';

/**
 * 实体索引关系选择数据视图（部件视图）基类
 *
 * @export
 * @class AppIndexPickupDataViewBase
 * @extends {IndexPickupDataViewBase}
 */
export class AppIndexPickupDataViewBase extends IndexPickupDataViewBase {

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppIndexPickupDataViewBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 视图静态参数
     *
     * @type {string}
     * @memberof AppIndexPickupDataViewBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听视图动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppIndexPickupDataViewBase
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
     * @memberof AppIndexPickupDataViewBase
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
     * @memberof AppIndexPickupDataViewBase
     */
    public destroyed(){
        this.viewDestroyed();
    }

    /**
     * 表格选择视图渲染
     * 
     * @memberof AppIndexPickupDataViewBase
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
            this.renderBodyMessage(),
            this.renderToolBar(),
            this.renderQuickGroup(),
            this.renderQuickSearch(),
            this.renderSearchForm(),
            this.renderMainContent(),
            this.renderBottomMessage()
        ]);
    }
}