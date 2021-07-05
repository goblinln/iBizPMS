import { Component } from "vue-property-decorator";
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppMapExpBarBase } from '../app-common-control/app-map-exp-bar-base';
import './app-default-map-exp-bar.less';

/**
 * 地图导航部件基类
 *
 * @export
 * @class AppDefaultMapExpBar
 * @extends {AppMapExpBarBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultMapExpBar extends AppMapExpBarBase{}