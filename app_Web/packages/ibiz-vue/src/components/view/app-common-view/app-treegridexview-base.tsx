import { Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { TreeGridExView } from '../../../view/TreeGridExView';
import { AppLayoutService } from '../../../app-service';

/**
 * 应用树表格视图基类
 *
 * @export
 * @class AppTreeGridExViewBase
 * @extends {TreeGridExView}
 */
export class AppTreeGridExViewBase extends TreeGridExView {

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppTreeGridExViewBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 视图静态参数
     *
     * @type {string}
     * @memberof AppTreeGridExViewBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听视图动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppTreeGridExViewBase
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
     * 监听视图静态参数变化
     * 
     * @memberof AppTreeGridExViewBase
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
     * @memberof AppTreeGridExViewBase
     */
    public destroyed() {
        this.viewDestroyed();
    }

    /**
     * 编辑视图渲染
     * 
     * @memberof AppTreeGridExViewBase
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
            this.renderQuickSearch(),
            this.renderMainContent(),
            this.renderBottomMessage()
        ]);
    }

}