import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppViewPanelBase } from '../app-common-control/app-viewpanel-base';
import './app-default-viewpanel.less';

/**
 * 嵌入视图面板部件
 *
 * @export
 * @class AppDefaultViewPanel
 * @extends {AppViewPanelBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultViewPanel extends AppViewPanelBase {}
