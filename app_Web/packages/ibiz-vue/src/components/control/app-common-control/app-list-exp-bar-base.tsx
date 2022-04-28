import { Util } from "ibiz-core";
import { Prop, Watch, Emit } from "vue-property-decorator";
import { ListExpBarControlBase } from "../../../widgets";

/**
 * 实体列表导航栏部件基类
 *
 * @export
 * @class AppListExpBarBase
 * @extends {ListExpBarControlBase}
 */
export class AppListExpBarBase extends ListExpBarControlBase {

    /**
     * 部件静态参数
     *
     * @memberof AppListExpBarBase
     */
    @Prop() public staticProps!: any;

    /**
     * 部件动态参数
     *
     * @memberof AppListExpBarBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppListExpBarBase
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
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppListExpBarBase
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
     * 初始化部件
     *
     * @memberof AppListExpBarBase
     */
    public ctrlInit() {
        super.ctrlInit();
        this.initCtrlToolBar();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppListExpBarBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void {}

    /**
     * 绘制列表导航栏
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppListExpBarBase
     */
    public render() {
        if(!this.controlIsLoaded) {
            return null;
        }
        return this.renderMainContent();
    }
}
