import { Util } from "ibiz-core";
import { Prop, Watch, Emit, Component } from "vue-property-decorator";
import { MobMapControlBase } from "../../../widgets";

/**
 * 实体地图部件基类
 *
 * @export
 * @class AppMobMapBase
 * @extends {ListExpBarControlBase}
 */
@Component({})
export class AppMobMapBase extends MobMapControlBase {

    /**
     * 部件静态参数
     *
     * @memberof AppMobMapBase
     */
    @Prop() public staticProps!: any;

    /**
     * 部件动态参数
     *
     * @memberof AppMobMapBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppMobMapBase
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
     * @memberof AppMobMapBase
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
     * @memberof AppMobMapBase
     */
    public ctrlInit() {
        super.ctrlInit();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppMobMapBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void {}

    /**
     * 绘制地图
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppMobMapBase
     */
    public render() {
        if (!this.controlIsLoaded) {
            return null;
        }
        return <div ref={this.mapId} class="map"></div>
    }
}
