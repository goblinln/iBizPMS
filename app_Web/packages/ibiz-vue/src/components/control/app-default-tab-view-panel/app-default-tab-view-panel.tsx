import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppTabViewPanelBase } from '../app-common-control/app-tab-view-panel-base';
import './app-default-tab-view-panel.less';

/**
 * 分页视图面板部件
 *
 * @export
 * @class AppDefaultTabViewPanel
 * @extends {AppTabViewPanelBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultTabViewPanel extends AppTabViewPanelBase {}