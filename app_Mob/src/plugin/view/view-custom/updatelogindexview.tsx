
import { ViewBase, VueLifeCycleProcessing } from 'ibiz-vue';
import { Component } from 'vue-property-decorator';
import { IndexViewBase, AppLayoutService } from 'ibiz-vue';
import { Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';


/**
 * 首页更新日志插件类
 *
 * @export
 * @class UPDATELOGINDEXVIEW
 * @extends {Vue}
 */
@Component({})
@VueLifeCycleProcessing()
export class UPDATELOGINDEXVIEW extends IndexViewBase {

        /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof UPDATELOGINDEXVIEW
     */
    @Prop() public dynamicProps!: any;

    /**
     * 视图静态参数
     *
     * @type {string}
     * @memberof UPDATELOGINDEXVIEW
     */
    @Prop() public staticProps!: any;

    /**
     * 监听视图动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof UPDATELOGINDEXVIEW
     */
    @Watch("dynamicProps", {
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
     * @memberof UPDATELOGINDEXVIEW
     */
    @Watch("staticProps", {
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
     * @memberof UPDATELOGINDEXVIEW
     */
    public destroyed() {
        this.viewDestroyed();
    }

    /**
     * 绘制视图内容
     *
     *
     * @memberof UPDATELOGINDEXVIEW
     */
    public render(h: any) {
        if (!this.viewIsLoaded) {
            return null;
        }
        const targetViewLayoutComponent: any = AppLayoutService.getLayoutComponent(
            `${this.viewInstance.viewType}-DEFAULT`
        );
        return h(
            targetViewLayoutComponent,
            {
                props: { viewInstance: this.viewInstance },
            },
            [this.renderMainContent(), this.renderUpdateLog()]
        );
    }

    /**
     * 绘制更新内容
     *
     *
     * @memberof UPDATELOGINDEXVIEW
     */    
    public renderUpdateLog() {
        if ( !(localStorage.getItem("updateLogStatus") == 'true') ) {
            return <app-update-log></app-update-log>;
        }
    }

}
