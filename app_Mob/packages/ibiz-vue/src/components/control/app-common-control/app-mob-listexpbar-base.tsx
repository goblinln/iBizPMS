import { Util } from "ibiz-core";
import { Prop, Watch, Emit, Component } from "vue-property-decorator";
import { MobListExpBarControlBase } from "../../../widgets";

/**
 * 实体列表导航栏部件基类
 *
 * @export
 * @class AppMobListExpBar
 * @extends {ListExpBarControlBase}
 */
@Component({})
export class AppMobListExpBarBase extends MobListExpBarControlBase {

    /**
     * 部件静态参数
     *
     * @memberof AppMobListExpBar
     */
    @Prop() public staticProps!: any;

    /**
     * 部件动态参数
     *
     * @memberof AppMobListExpBar
     */
    @Prop() public dynamicProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppMobListExpBar
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
     * @memberof AppMobListExpBar
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
     * @memberof AppMobListExpBar
     */
    public ctrlInit() {
        super.ctrlInit();
        this.initCtrlToolBar();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppMobListExpBar
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void {}

    /**
     * 绘制列表导航栏
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppMobListExpBar
     */
    public render() {
        if (!this.controlIsLoaded) {
            return null;
        }
        return (
            <div class="app-mob-listexpbar">
                <div class="listexpbar-container">
                    <div class="listexpbar_list">
                        {this.renderXDataControl()}
                    </div> 
                    {this.selection && this.selection.view && this.selection.view.viewname && this.selection.view.viewname != '' ?
                    this.renderNavView() : null}
                </div>
            </div>
        )
    }
}
