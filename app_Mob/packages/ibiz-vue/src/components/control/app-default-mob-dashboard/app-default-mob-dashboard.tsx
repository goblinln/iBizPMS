import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppMobDashboardBase } from '../app-common-control/app-mob-dashboard-base';
import './app-default-mob-dashboard.less';
/**
 * 应用看板
 *
 * @export
 * @class ViewToolbar
 * @extends {Vue}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultMobDashboard extends AppMobDashboardBase { }
