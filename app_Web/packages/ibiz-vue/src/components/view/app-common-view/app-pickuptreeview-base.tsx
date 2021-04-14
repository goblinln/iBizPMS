import { PickupTreeViewBase } from '../../../view';
import { Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { AppLayoutService } from '../../../app-service';

/**
 * 应用实体选择树视图（部件视图）基类
 *
 * @export
 * @class AppPickupTreeViewBase
 * @extends {PickupTreeViewBase}
 */
export class AppPickupTreeViewBase extends PickupTreeViewBase {

    /**
     * 传入视图上下文
     *
     * @type {string}
     * @memberof AppPickupTreeViewBase
     */
    @Prop() public staticProps!: any;

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppPickupTreeViewBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppPickupTreeViewBase
     */
    @Watch('dynamicProps', {
        immediate: true,
    })
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        if (newVal && !Util.isFieldsSame(newVal, oldVal)) {
            super.onDynamicPropsChange(newVal, oldVal);
        }
    }

    /**
     * 监听当前视图环境参数变化
     * 
     * @memberof AppPickupTreeViewBase
     */
    @Watch('staticProps', {
        immediate: true,
    })
    public onStaticPropsChange(newVal: any, oldVal: any) {
        if (newVal && !Util.isFieldsSame(newVal, oldVal)) {
            super.onStaticPropsChange(newVal, oldVal);
        }
    }

    /**
     * 销毁视图回调
     *
     * @memberof AppPickupTreeViewBase
     */
    public destroyed(){
        this.viewDestroyed();
    }

    /**
     * 表格选择视图渲染
     * 
     * @memberof AppPickupTreeViewBase
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
            this.renderQuickSearch(),
            this.renderMainContent(),
            this.renderBottomMessage()
        ]);
    }
}