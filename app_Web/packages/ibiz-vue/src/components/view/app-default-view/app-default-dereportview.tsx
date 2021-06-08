import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppDeReportViewBase } from '../app-common-view/app-dereportview-base';

/**
 * 应用实体报表视图
 *
 * @export
 * @class AppDefaultDeReportView
 * @extends {AppDeReportViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultDeReportView extends AppDeReportViewBase {}