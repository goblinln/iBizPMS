
import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppMobTabExpPanelBase } from '../app-common-control/app-mob-tabexppanel-base';
import './app-default-mob-tabexppanel.less';

/**
 * 分页导航栏部件
 *
 * @export
 * @class AppDefaultMobTabExpPanel
 * @extends {AppDefaultMobTabExpPanelBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultMobTabExpPanel extends AppMobTabExpPanelBase {}
