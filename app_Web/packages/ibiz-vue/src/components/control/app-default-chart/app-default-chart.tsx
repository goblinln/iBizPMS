import { Component } from 'vue-property-decorator';
import './app-default-chart.less';
import { AppChartBase } from '../app-common-control/app-chart-base';
import { VueLifeCycleProcessing } from '../../../decorators';

/**
 * 图表部件
 *
 * @export
 * @class AppDefaultChart
 * @extends {AppChartBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultChart extends AppChartBase {}