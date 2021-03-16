import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppMEditViewPanelBase } from '../app-common-control/app-meditviewpanel-base';
import './app-default-meditviewpanel.less';

/**
 * 多编辑面板部件
 *
 * @export
 * @class AppDefaultMEditViewPanel
 * @extends {AppMEditViewPanelBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultMEditViewPanel extends AppMEditViewPanelBase{}