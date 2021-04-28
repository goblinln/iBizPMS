import { Prop, Watch, Emit, Component } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { MobChartControlBase } from '../../../widgets';

/**
 * 图表部件基类
 *
 * @export
 * @class AppMobChartBase
 * @extends {MobChartControlBase}
 */
@Component({})
export class AppMobChartBase extends MobChartControlBase {

    /**
     * 部件静态参数
     *
     * @memberof AppMobChartBase
     */
    @Prop() public staticProps!: any;

    /**
     * 部件动态参数
     *
     * @memberof AppMobChartBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppMobChartBase
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
     * @memberof AppMobChartBase
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
     * @memberof AppMobChartBase
     */
    public destroyed() {
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppMobChartBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void { }

    /**
     * 绘制图表
     *
     * @returns {*}
     * @memberof AppMobChartBase
     */
    public render() {
        if (!this.controlIsLoaded) {
            return;
        }
        let chartClassName = {
            'app-data-chart': true,
            ...this.renderOptions.controlClassNames,
        };
        const { width, height } = this.controlInstance;
        return (
            <div class={chartClassName} >
                {this.isNoData ?
                    <div v-show="isNoData" class="chart-no-data"><i class="el-icon-data-analysis"></i>{'无数据'}</div> :
                    <div class="app-charts" id={this.chartId} style={{ padding: '6px 0', width: width ? `${width}px` : '340px', height: height ? `${height}px` : '50vh' }}></div>}
            </div>
        )

    }
}