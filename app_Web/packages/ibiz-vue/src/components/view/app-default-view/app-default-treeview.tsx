import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppTreeViewBase } from '../app-common-view/app-treeview-base';

/**
 * 应用实体树视图
 *
 * @export
 * @class AppDefaultTreeView
 * @extends {AppTreeViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultTreeView extends AppTreeViewBase {}
