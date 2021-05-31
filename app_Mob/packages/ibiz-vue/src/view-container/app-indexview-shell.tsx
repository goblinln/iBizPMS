import { Component, Emit, Prop, Watch, Inject } from 'vue-property-decorator';
import { ViewContainerBase } from './view-container-base';

/**
 * 视图壳
 *
 * @export
 * @class AppIndexViewShell
 * @extends {ViewContainerBase}
 */
@Component({})
export class AppIndexViewShell extends ViewContainerBase {

    /**
    * 数据变化
    *
    * @param {*} val
    * @returns {*}
    * @memberof AppIndexViewShell
    */
    @Emit()
    public viewDatasChange(val: any): any {
        return val;
    }

    /**
     * 视图静态参数
     *
     * @type {string}
     * @memberof AppIndexViewShell
     */
    @Prop() public staticProps!: any;

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppIndexViewShell
     */
    @Prop() public dynamicProps!: any;

    /**
     * 视图默认使用
     *
     * @type {string}
     * @memberof AppIndexViewShell
     */
    @Inject({ from: 'navModel', default: 'tab' })
    public navModel!: string;

    /**
     * Vue声明周期
     *
     * @memberof AppIndexViewShell
     */
    public created() {
        if (this.$route && this.$route.matched && this.$route.matched.length > 0) {
            let indexRoute: any = this.$route.matched.find((item: any) => {
                return item?.meta?.dynaModelFilePath;
            })
            if (indexRoute) {
                this.dynaModelFilePath = indexRoute.meta.dynaModelFilePath;
            }
        }
        this.loadDynamicModelData();
    }

    /**
     * 视图绘制
     *
     * @memberof AppIndexViewShell
     */
    public render(h: any) {
        if (!this.viewContainerName) {
            return;
        }
        return h(this.viewContainerName, {
            props: { dynamicProps: this.dynamicProps, staticProps: this.viewContext },
            on: {
                'view-event': this.handleViewEvent.bind(this)
            }
        })
    }

}
