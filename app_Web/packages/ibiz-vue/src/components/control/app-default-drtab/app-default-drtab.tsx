import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppDrtabBase } from '../app-common-control/app-drtab-base';
import './app-default-drtab.less';


/**
 * 分页导航面板部件
 *
 * @export
 * @class AppDefaultDrtab
 * @extends {AppTabExpPanelBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultDrtab extends AppDrtabBase {}