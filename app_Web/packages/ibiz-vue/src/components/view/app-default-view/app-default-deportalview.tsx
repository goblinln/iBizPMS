import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppDePortalViewBase } from '../app-common-view/app-deportalview-base';

/**
 * 应用数据看板视图
 *
 * @export
 * @class AppDefaultDePortalView
 * @extends {AppDePortalViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultDePortalView extends AppDePortalViewBase {}
