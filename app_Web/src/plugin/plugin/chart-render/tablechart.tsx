
import { Component, Watch } from 'vue-property-decorator';
import { VueLifeCycleProcessing,AppControlBase } from 'ibiz-vue';
import { AppDefaultChart } from 'ibiz-vue/src/components/control/app-default-chart/app-default-chart';


/**
 * 图表插件（表格图例）插件类
 *
 * @export
 * @class Tablechart
 * @class Tablechart
 * @extends {Vue}
 */
@Component({})
@VueLifeCycleProcessing()
export class Tablechart extends AppDefaultChart {

/**
     * 绘制图表
     *
     * @returns {*}
     * @memberof Tablechart
     */
    public render() {
        if (!this.controlIsLoaded) {
            return;
        }
        let chartClassName = {
            'app-data-chart': true,
            'app-chart-empty': !this.chartRenderOption,
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

