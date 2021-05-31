import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppListBase } from '../app-common-control/app-list-base';
import './app-default-list.less';

/**
 * 实体列表部件
 *
 * @export
 * @class AppDefaultList
 * @extends {AppListBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultList extends AppListBase {}