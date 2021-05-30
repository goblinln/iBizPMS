import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppChartViewBase } from '../app-common-view/app-chartview-base';

/**
 * 应用图表视图
 *
 * @export
 * @class AppStyle2ChartView
 * @extends {AppChartViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppStyle2ChartView extends AppChartViewBase {}