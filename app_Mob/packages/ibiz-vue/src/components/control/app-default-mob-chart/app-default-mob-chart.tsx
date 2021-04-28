import { Component } from 'vue-property-decorator';
import './app-default-mob-chart.less';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppMobChartBase } from '../app-common-control/app-mob-chart-base';

/**
 * 图表部件基类
 *
 * @export
 * @class AppDefaultMobChart
 * @extends {AppDefaultMobChartBase}
 */
 @Component({})
 @VueLifeCycleProcessing()
 export class AppDefaultMobChart extends AppMobChartBase {}