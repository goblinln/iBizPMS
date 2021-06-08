import { ViewEngine } from './view-engine';

/**
 * 实体选择视图(分页关系)
 *
 * @export
 * @class PickupView3Engine
 * @extends {ViewEngine}
 */
export class PickupView3Engine extends ViewEngine {

    /**
     * Creates an instance of PickupView3Engine.
     * 
     * @memberof PickupView3Engine
     */
    constructor() {
        super();
    }

    public pickupViewPanelModels: any[] = [];

    public init(options: any) {
        this.pickupViewPanelModels = options.pickupViewPanelModels;
        super.init(options);
    }

    public load() {
        this.view.viewSelections = [];
        super.load();
        const activedPickupViewPanel = this.view.activedPickupViewPanel ? this.view.activedPickupViewPanel
            : this.pickupViewPanelModels.length > 0
            ? this.pickupViewPanelModels[0].name : '';
        if (activedPickupViewPanel) {
            this.setViewState2({ tag: activedPickupViewPanel, action: 'load', viewdata: this.view.viewparams });
        }
    }

    public onCtrlEvent(ctrlName: string, eventName: string, args: any): void {
        super.onCtrlEvent(ctrlName, eventName, args);
        if (Object.is(eventName, 'selectionchange')) {
            this.onSelectionChange(args);
        }
        if (Object.is(eventName, 'activated')) {
            this.emitViewEvent('viewdatasactivated', args);
        }
    }

    public onSelectionChange(args: any[]) {
        this.view.viewSelections = [];
        this.view.viewSelections = [...args]
        const _disabled: boolean = this.view.viewSelections.length > 0 ? false : true;
        this.view.containerModel.view_okbtn.disabled = _disabled;
        if(!this.view.isShowButton){
            this.emitViewEvent('viewdataschange', [...args]);
        }
    }
}