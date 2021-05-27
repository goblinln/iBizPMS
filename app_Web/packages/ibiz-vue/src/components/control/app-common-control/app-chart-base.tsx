import { Prop, Watch, Emit } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { ChartControlBase } from '../../../widgets';

/**
 * 图表部件基类
 *
 * @export
 * @class AppChartBase
 * @extends {ChartControlBase}
 */
export class AppChartBase extends ChartControlBase {

    /**
     * 部件静态参数
     *
     * @memberof AppChartBase
     */
    @Prop() public staticProps!: any;

    /**
     * 部件动态参数
     *
     * @memberof AppChartBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppChartBase
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
     * @memberof AppChartBase
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
     * @memberof AppChartBase
     */
    public destroyed(){
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppChartBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void {}

    /**
     * 绘制图表
     *
     * @returns {*}
     * @memberof AppChartBase
     */
    public render() {
        if (!this.controlIsLoaded) {
            return;
        }
        let chartClassName = {
            'app-data-chart': true,
            'app-chart-empty': this.items.length <= 0,
            ...this.renderOptions.controlClassNames,
        };
        return (
            <div class={chartClassName} >
                {this.isNoData ?
                    <div v-show="isNoData" class="chart-no-data"><i class="el-icon-data-analysis"></i>{this.$t('app.commonwords.nodata')}</div> :
                    <div class="app-charts" style='height: 100%; padding: 6px 0' id={this.chartId}></div>}
            </div>
        )

    }
}