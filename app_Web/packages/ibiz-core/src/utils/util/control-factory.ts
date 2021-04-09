import { IBizAppMenuModel, IBizCalendarExpBarModel, IBizCalendarModel, IBizChartModel, IBizDashboardModel, IBizDataViewExpBarModel, IBizDataViewModel, IBizEditFormModel, IBizGanttModel, IBizGridExpBarModel, IBizGridModel, IBizKanbanModel, IBizListExpBarModel, IBizListModel, IBizMEditViewPanelModel, IBizPickUpViewPanelModel, IBizPortletModel, IBizSearchBarModel, IBizSearchFormModel, IBizTabExpPanelModel, IBizTreeExpBarModel, IBizTreeGridExModel, IBizTreeModel, IBizContextMenuModel, IBizWizardPanelModel } from "../../model/control";

/**
 * 部件模型实例工厂
 *
 * @export
 * @class ControlFactory
 */
export class ControlFactory {
    /**
     * 获取对应的模型实例
     *
     * @static
     * @param {*} modelData
     * @memberof ControlFactory
     */
    public static getInstance(modelData: any, ...args: any) {
        switch (modelData.controlType) {
            case 'APPMENU':
                return new IBizAppMenuModel(...arguments);
            case 'TREEVIEW':
                return new IBizTreeModel(...arguments);
            case 'TREEEXPBAR':
                return new IBizTreeExpBarModel(...arguments);
            case 'CALENDAR':
                return new IBizCalendarModel(...arguments);
            case 'CALENDAREXPBAR':
                return new IBizCalendarExpBarModel(...arguments);
            case 'CHART':
                return new IBizChartModel(...arguments);
            case 'DASHBOARD':
                return new IBizDashboardModel(...arguments);
            case 'PORTLET':
                return new IBizPortletModel(...arguments);
            case 'DATAVIEWEXPBAR':
                return new IBizDataViewExpBarModel(...arguments);
            case 'DATAVIEW':
                return new IBizDataViewModel(...arguments);
            case 'FORM':
                return new IBizEditFormModel(...arguments);
            case 'SEARCHFORM':
                return new IBizSearchFormModel(...arguments);
            case 'GANTT':
                return new IBizGanttModel(...arguments);
            case 'GRID':
                return new IBizGridModel(...arguments);
            case 'GRIDEXPBAR':
                return new IBizGridExpBarModel(...arguments);
            case 'KANBAN':
                return new IBizKanbanModel(...arguments);
            case 'LISTEXPBAR':
                return new IBizListExpBarModel(...arguments);
            case 'LIST':
                return new IBizListModel(...arguments);
            case 'MULTIEDITVIEWPANEL':
                return new IBizMEditViewPanelModel(...arguments);
            case 'PICKUPVIEWPANEL':
                return new IBizPickUpViewPanelModel(...arguments);
            case 'TABEXPPANEL':
                return new IBizTabExpPanelModel(...arguments);
            case 'TREEGRIDEX':
                return new IBizTreeGridExModel(...arguments);
            case 'SEARCHBAR':
                return new IBizSearchBarModel(...arguments);
            case 'CONTEXTMENU':
                return new IBizContextMenuModel(...arguments);
            case 'WIZARDPANEL':
                return new IBizWizardPanelModel(...arguments);
            default:
                return undefined;
        }
    }
}