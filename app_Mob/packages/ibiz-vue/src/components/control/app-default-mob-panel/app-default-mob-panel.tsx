import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from 'ibiz-vue/src/decorators';
import { AppMobPanelBase } from '../app-common-control/app-mob-panel-base';
import './app-default-mob-panel.less';

/**
 * 面板部件
 *
 * @export
 * @class AppDefaultMobPanel
 * @extends {AppDefaultMobPanelBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultMobPanel extends AppMobPanelBase { }