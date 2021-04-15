import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppMEditViewBase } from '../app-common-view/app-meditview-base';


/**
 * 应用多表单编辑视图
 *
 * @export
 * @class AppDefaultMEditView
 * @extends {AppMEditViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultMEditView extends AppMEditViewBase {}