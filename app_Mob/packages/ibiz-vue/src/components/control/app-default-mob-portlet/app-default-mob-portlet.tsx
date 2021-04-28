
import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppMobPortletBase } from '../app-common-control/app-mob-portlet-base';
import './app-default-mob-portlet.less';

/**
 * 门户部件部件
 *
 * @export
 * @class AppDefaultMobPortlet
 * @extends {AppDefaultMobPortletBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultMobPortlet extends AppMobPortletBase { }
