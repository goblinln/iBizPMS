import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppFormPickupDataViewBase } from '../app-common-view/app-formpickudataview-base';

/**
 * 应用实体选择表格视图
 *
 * @export
 * @class AppDefaultFormPickupDataView
 * @extends {AppFormPickupDataViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultFormPickupDataView extends AppFormPickupDataViewBase {}