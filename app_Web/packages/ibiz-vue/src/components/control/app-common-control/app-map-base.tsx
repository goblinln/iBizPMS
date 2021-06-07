import { Prop, Watch, Emit } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { MapControlBase } from '../../../widgets';

/**
 * 实体地图部件基类
 *
 * @export
 * @class AppMapBase
 * @extends {MapControlBase}
 */
export class AppMapBase extends MapControlBase {

    /**
     * 部件静态参数
     *
     * @memberof AppMapBase
     */
    @Prop() public staticProps!: any;

    /**
     * 部件动态参数
     *
     * @memberof AppMapBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppMapBase
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
     * 监听静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppMapBase
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
     * @memberof AppMapBase
     */
    public destroyed() {
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     * 
     * @param 抛出参数 
     * @memberof AppMapBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string, action: string, data: any }): void {}

    /**
     * 绘制地图
     *
     * @returns {*}
     * @memberof AppMapBase
     */
    public render() {
        if (!this.controlIsLoaded) {
            return null;
        }
        let mapClassName = {
            'app-map': true,
            'map-container': true,
            ...this.renderOptions.controlClassNames,
        };
        return <div class={mapClassName} >
                    <div ref="map" class="map"></div>
               </div>
    }
}