import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppTabExpPanelBase } from '../app-common-control/app-tab-exp-panel-base';
import './app-default-tab-exp-panel.less';


/**
 * 分页导航面板部件
 *
 * @export
 * @class AppDefaultTabExpPanel
 * @extends {AppTabExpPanelBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultTabExpPanel extends AppTabExpPanelBase {}