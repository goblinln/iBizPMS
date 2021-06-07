import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppMapBase } from '../app-common-control/app-map-base';
import './app-default-map.less';

/**
 * 实体地图部件
 *
 * @export
 * @class AppDefaultMap
 * @extends {AppMapBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultMap extends AppMapBase {}