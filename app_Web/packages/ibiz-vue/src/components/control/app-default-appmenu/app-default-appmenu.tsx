import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppmenuBase } from '../app-common-control/app-appmenu-base';
import './app-default-appmenu.less';
/**
 * 应用菜单
 *
 * @export
 * @class AppDefaultAppmenu
 * @extends {AppmenuBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultAppmenu extends AppmenuBase {}
