import { Component } from "vue-property-decorator";
import { VueLifeCycleProcessing } from "../../../decorators";
import { AppListExpBarBase } from "../app-common-control/app-list-exp-bar-base";
import "./app-default-list-exp-bar.less";

/**
 * 实体列表导航栏部件
 *
 * @export
 * @class AppDefaultListExpBar
 * @extends {AppListExpBarBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultListExpBar extends AppListExpBarBase {}
