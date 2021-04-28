
import { Component, Watch } from 'vue-property-decorator';
import { VueLifeCycleProcessing,AppControlBase } from 'ibiz-vue';
import { AppDefaultChart } from 'ibiz-vue/src/components/control/app-default-chart/app-default-chart';


/**
 * 年度统计饼图插件插件类
 *
 * @export
 * @class PiePlugin
 * @class PiePlugin
 * @extends {Vue}
 */
@Component({})
@VueLifeCycleProcessing()
export class PiePlugin extends AppDefaultChart {

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
                {
                    this.chartRenderOption ? 
                    <chart-form-legend chartOption={this.chartRenderOption} originId={this.chartId} chartUserParams={this.chartUserParams}></chart-form-legend> : undefined
                }
            </div>
        )
    }

}

