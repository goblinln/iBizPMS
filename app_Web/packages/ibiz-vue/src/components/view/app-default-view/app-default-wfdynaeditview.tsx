import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppWFDynaEditViewBase } from '../app-common-view/app-wfdynaeditview-base';

/**
 * 应用实体工作流动态编辑视图
 *
 * @export
 * @class AppDefaultWFDynaEditView
 * @extends {AppWFDynaEditViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultWFDynaEditView extends AppWFDynaEditViewBase { }
