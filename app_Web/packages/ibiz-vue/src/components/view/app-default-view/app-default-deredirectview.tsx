import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppDeRedirectViewBase } from '../app-common-view/app-deredirectview-base';
/**
 * 实体数据重定向视图
 *
 * @export
 * @class AppDeRedirectView
 * @extends {AppDeRedirectViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultDeRedirectView extends AppDeRedirectViewBase {}