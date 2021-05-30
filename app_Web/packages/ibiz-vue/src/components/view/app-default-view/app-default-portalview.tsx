import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppPortalViewBase } from '../app-common-view/app-portalview-base';

/**
 * 应用门户视图
 *
 * @export
 * @class AppDefaultPortalView
 * @extends {AppPortalViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultPortalView extends AppPortalViewBase {}
