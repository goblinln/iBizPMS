import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppContextMenuBase } from '../app-common-control/app-contextmenu-base';
import './app-default-contextmenu.less';

/**
 * 上下文菜单部件
 *
 * @export
 * @class AppDefaultContextMenu
 * @extends {AppContextMenuBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultContextMenu extends AppContextMenuBase {}