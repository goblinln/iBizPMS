
import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppPortletBase } from '../app-common-control/app-portlet-base';
import './app-default-portlet.less';

/**
 * 门户部件部件
 *
 * @export
 * @class AppDefaultPortlet
 * @extends {AppPortletBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultPortlet extends AppPortletBase {}
