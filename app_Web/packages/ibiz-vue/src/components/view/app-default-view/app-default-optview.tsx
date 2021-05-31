import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppOptViewBase } from '../app-common-view/app-optview-base';

/**
 * 应用实体操作视图
 *
 * @export
 * @class AppDefaultOptView
 * @extends {AppOptViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultOptView extends AppOptViewBase {}