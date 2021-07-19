import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppMapViewBase } from '../app-common-view/app-mapview-base';


/**
 * 地图视图
 *
 * @export
 * @class AppStyle2MapView
 * @extends {AppMapViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppStyle2MapView extends AppMapViewBase {}

