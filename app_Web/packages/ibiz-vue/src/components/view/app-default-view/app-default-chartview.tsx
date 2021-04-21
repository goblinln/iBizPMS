import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppChartViewBase } from '../app-common-view/app-chartview-base';

/**
 * 应用图表视图
 *
 * @export
 * @class AppDefaultChartView
 * @extends {AppChartViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultChartView extends AppChartViewBase {}