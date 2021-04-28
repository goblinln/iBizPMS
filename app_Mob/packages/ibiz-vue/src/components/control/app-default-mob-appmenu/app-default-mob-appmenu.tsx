import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppMobMenuBase } from '../app-common-control/app-mob-appmenu-base';
import './app-default-mob-appmenu.less';
/**
 * 应用菜单
 *
 * @export
 * @class ViewToolbar
 * @extends {Vue}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultMobAppMenu extends AppMobMenuBase { }
