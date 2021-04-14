import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppTreeExpBarBase } from '../app-common-control/app-tree-exp-bar-base';
import './app-default-tree-exp-bar.less';

/**
 * 树视图导航栏部件
 *
 * @export
 * @class AppDefaultTreeExpBar
 * @extends {AppTreeExpBarBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultTreeExpBar extends AppTreeExpBarBase {}