
import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppPickupTreeViewBase } from '../app-common-view/app-pickuptreeview-base';

/**
 * 应用实体选择树视图（部件视图）
 *
 * @export
 * @class AppDefaultPickupTreeView
 * @extends {AppPickupTreeViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultPickupTreeView extends AppPickupTreeViewBase {}