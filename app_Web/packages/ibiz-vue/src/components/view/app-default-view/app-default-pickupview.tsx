import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppPickupViewBase } from '../app-common-view/app-pickupview-base';


/**
 * 应用实体数据选择视图
 *
 * @export
 * @class AppDefaultPickupView
 * @extends {AppPickupViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultPickupView extends AppPickupViewBase { }