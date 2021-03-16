import { Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { TreeViewBase } from '../../../view/TreeViewBase';
import { AppLayoutService } from '../../../app-service';

/**
 * 应用实体树视图基类
 *
 * @export
 * @class AppTreeViewBase
 * @extends {TreeViewBase}
 */
export class AppTreeViewBase extends TreeViewBase {

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppTreeViewBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 视图静态参数
     *
     * @type {string}
     * @memberof AppTreeViewBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听视图动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppTreeViewBase
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
     * @memberof AppTreeViewBase
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
     * @memberof AppTreeViewBase
     */
    public destroyed(){
        this.viewDestroyed();
    }

    /**
     * 实体树视图渲染
     *
     * @memberof AppTreeViewBase
     */
    render(h: any) {
        if (!this.viewIsLoaded) {
            return null;
        }
        const targetViewLayoutComponent:any = AppLayoutService.getLayoutComponent(`${this.viewInstance.viewType}-${this.viewInstance.viewStyle}`);
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
