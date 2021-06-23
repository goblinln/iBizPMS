import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppEditView2Base } from '../app-common-view/app-editview2-base';

/**
 * 应用实体编辑视图
 *
 * @export
 * @class AppDefaultEditView
 * @extends {AppEditViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultEditView2 extends AppEditView2Base {}
