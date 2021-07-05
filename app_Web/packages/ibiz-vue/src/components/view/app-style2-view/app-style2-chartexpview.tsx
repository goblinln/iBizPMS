import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppChartExpViewBase } from '../app-common-view/app-chartexpview-base';

/**
 * 应用图表视图
 *
 * @export
 * @class AppStyle2ChartExpView
 * @extends {AppChartExpViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppStyle2ChartExpView extends AppChartExpViewBase {}