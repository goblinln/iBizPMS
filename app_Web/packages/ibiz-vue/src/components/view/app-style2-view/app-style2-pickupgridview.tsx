import { Component, Prop, Watch } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppPickupGridViewBase } from '../app-common-view/app-pickupgridview-base';

/**
 * 应用实体选择表格视图
 *
 * @export
 * @class AppStyle2PickupGridView
 * @extends {AppPickupGridViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppStyle2PickupGridView extends AppPickupGridViewBase {}