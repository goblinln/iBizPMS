import {  Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators/VueLifeCycleProcessing';
import { AppMobMeditViewPanelBase } from '../app-common-control/app-mob-meditviewpanel-base';
import './app-default-mob-meditviewpanel.less';

/**
 * 多编辑面板部件基类
 *
 * @export
 * @class AppDefaultMobMeditViewPanel
 * @extends {AppMobMeditViewPanelBase}
 */
 @Component({})
 @VueLifeCycleProcessing()
 export class AppDefaultMobMeditViewPanel extends AppMobMeditViewPanelBase { }