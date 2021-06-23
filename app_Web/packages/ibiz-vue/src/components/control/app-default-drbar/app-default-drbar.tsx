import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppDrbarBase } from '../app-common-control/app-drbar-base';
import './app-default-drbar.less';


/**
 * 分页导航面板部件
 *
 * @export
 * @class AppDefaultDrtab
 * @extends {AppTabExpPanelBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultDrbar extends AppDrbarBase {}