import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppWFActionViewBase } from '../app-common-view';

/**
 * 应用实体工作流操作视图
 *
 * @export
 * @class AppDefaultWFActionView
 * @extends {AppWFActionViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultWFActionView extends AppWFActionViewBase { }