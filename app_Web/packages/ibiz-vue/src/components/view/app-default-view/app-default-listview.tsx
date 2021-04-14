import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppListViewBase } from '../app-common-view/app-listview-base';


/**
 * 列表视图
 *
 * @export
 * @class AppDefaultListView
 * @extends {AppListViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultListView extends AppListViewBase {}

