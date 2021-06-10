import { Component } from 'vue-property-decorator';
import { AppReportPanelBase } from '../app-common-control/app-reportpanel-base';
import { VueLifeCycleProcessing } from '../../../decorators';
import './app-default-reportpanel.less';

/**
 * 报表面板部件
 *
 * @export
 * @class AppDefaultReportPanel
 * @extends {AppGridBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultReportPanel extends AppReportPanelBase { }