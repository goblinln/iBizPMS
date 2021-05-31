import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppIndexPickupDataViewBase } from '../app-common-view/app-indexpickudataview-base';

/**
 * 实体索引关系选择数据视图（部件视图）
 *
 * @export
 * @class AppDefaultIndexPickupDataView
 * @extends {MDViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultIndexPickupDataView extends AppIndexPickupDataViewBase {}