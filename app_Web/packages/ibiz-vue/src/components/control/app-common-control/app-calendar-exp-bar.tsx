import { Emit, Prop, Watch } from "vue-property-decorator";
import { CalendarExpBarControlBase } from "../../../widgets";
import { Util } from "ibiz-core";

/**
 * 日历导航部件基类
 *
 * @export
 * @class AppCalendarExpBarBase
 * @extends {CalendarExpBarControlBase}
 */
export class AppCalendarExpBarBase extends CalendarExpBarControlBase{

    /**
     * 部件动态参数
     *
     * @memberof AppCalendarExpBarBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 部件静态参数
     *
     * @memberof AppCalendarExpBarBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听部件动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppCalendarExpBarBase
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
     * 监听部件静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppCalendarExpBarBase
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
     * @memberof AppCalendarExpBarBase
     */
    public destroyed(){
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppCalendarExpBarBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void {}

    /**
     * 绘制日历导航部件
     * 
     * @memberof AppCalendarExpBarBase
     */
    public render(){
        if(!this.controlIsLoaded) {
            return null;
        }
        return this.renderMainContent();
    }

}