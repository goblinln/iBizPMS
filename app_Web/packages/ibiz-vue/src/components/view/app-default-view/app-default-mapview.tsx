import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppMapViewBase } from '../app-common-view/app-mapview-base';


/**
 * 地图视图
 *
 * @export
 * @class AppDefaultMapView
 * @extends {AppMapViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultMapView extends AppMapViewBase {}

