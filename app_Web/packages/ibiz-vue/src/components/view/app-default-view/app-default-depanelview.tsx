import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppDePanelViewBase } from '../app-common-view/app-depanelview-base';

/**
 * 应用实体面板视图
 *
 * @export
 * @class AppDefaultDePanelView
 * @extends {AppDePanelViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultDePanelView extends AppDePanelViewBase { }