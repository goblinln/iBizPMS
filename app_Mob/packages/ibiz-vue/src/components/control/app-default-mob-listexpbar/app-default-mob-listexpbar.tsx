import { Component } from "vue-property-decorator";
import { VueLifeCycleProcessing } from "../../../decorators";
import { AppMobListExpBarBase } from "../app-common-control/app-mob-listexpbar-base";
import './app-default-mob-listexpbar.less';

/**
 * 实体列表导航栏部件基类
 *
 * @export
 * @class AppDefaultMobListExpBar
 * @extends {ListExpBarControlBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultMobListExpBar extends AppMobListExpBarBase { }
