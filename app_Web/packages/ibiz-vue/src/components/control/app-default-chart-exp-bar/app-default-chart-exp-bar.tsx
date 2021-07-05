import { Component } from 'vue-property-decorator';
import { AppChartExpBarBase } from '../app-common-control/app-chart-exp-bar-base';
import { VueLifeCycleProcessing } from '../../../decorators';
import './app-default-chart-exp-bar.less';

/**
 * 图表部件
 *
 * @export
 * @class AppDefaultChartExpBar
 * @extends {AppChartExpBarBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultChartExpBar extends AppChartExpBarBase {}