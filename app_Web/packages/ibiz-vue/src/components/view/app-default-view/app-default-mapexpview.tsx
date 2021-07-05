import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppMapExpViewBase } from '../app-common-view/app-mapexpview-base';

/**
 * 应用实体地图导航视图
 *
 * @export
 * @class AppDefaultMapExpView
 * @extends {AppMapExpViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultMapExpView extends AppMapExpViewBase {}