import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppGridViewBase } from '../app-common-view/app-gridview-base';

/**
 * 应用实体表格视图
 *
 * @export
 * @class AppDefaultGridView
 * @extends {GridViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultGridView extends AppGridViewBase {}