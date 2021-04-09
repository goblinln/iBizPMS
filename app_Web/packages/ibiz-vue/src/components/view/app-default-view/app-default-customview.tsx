import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppCustomViewBase } from '../app-common-view/app-customview-base';
/**
 * 应用自定义视图
 *
 * @export
 * @class AppDefaultCustomView
 * @extends {AppCustomViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultCustomView extends AppCustomViewBase {}