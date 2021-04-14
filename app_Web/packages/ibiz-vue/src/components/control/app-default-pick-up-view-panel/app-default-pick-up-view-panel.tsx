import { Component } from "vue-property-decorator";
import { VueLifeCycleProcessing } from "../../../decorators";
import { AppPickUpViewPanelBase } from "../app-common-control/app-pick-up-view-panel-base";
import "./app-default-pick-up-view-panel.less";

/**
 * 选择视图面板部件
 *
 * @export
 * @class AppDefaultPickUpViewPanel
 * @extends {AppPickUpViewPanelBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultPickUpViewPanel extends AppPickUpViewPanelBase {}