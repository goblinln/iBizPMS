import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppMobDrtabBase } from '../app-common-control/app-mob-drtab-base';
import './app-default-mob-drtab.less';


/**
 * 分页导航面板部件
 *
 * @export
 * @class AppDefaultMobDrtab
 * @extends {AppTabExpPanelBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultMobDrtab extends AppMobDrtabBase {}