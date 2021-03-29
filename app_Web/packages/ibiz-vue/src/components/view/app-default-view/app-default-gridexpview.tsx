import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppGridExpViewBase } from '../app-common-view/app-gridexpview-base';

/**
 * 应用实体表格导航视图
 *
 * @export
 * @class AppDefaultGridExpView
 * @extends {AppGridExpViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultGridExpView extends AppGridExpViewBase {}