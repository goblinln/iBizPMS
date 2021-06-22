import { Component } from "vue-property-decorator";
import { VueLifeCycleProcessing } from "../../../decorators";
import { AppMobMapBase } from "../app-common-control/app-mob-map-base";
import './app-default-mob-map.less';

/**
 * 实体地图部件基类
 *
 * @export
 * @class AppDefaultMobMap
 * @extends {AppMobMapBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultMobMap extends AppMobMapBase { }
