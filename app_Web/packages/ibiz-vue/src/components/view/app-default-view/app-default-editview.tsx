import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppEditViewBase } from '../app-common-view/app-editview-base';

/**
 * 应用实体编辑视图
 *
 * @export
 * @class AppDefaultEditView
 * @extends {AppEditViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultEditView extends AppEditViewBase {}
