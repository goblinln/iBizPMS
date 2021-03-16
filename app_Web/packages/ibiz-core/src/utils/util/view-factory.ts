import { IBizCalendarExpViewModel, IBizCalendarViewModel, IBizChartViewModel, IBizDataViewExpViewModel, IBizDataViewViewModel, IBizDePortalViewModel, IBizEditViewModel, IBizFormPickupDataViewModel, IBizGanttViewModel, IBizGridExpViewModel, IBizGridViewModel, IBizIndexViewModel, IBizKanbanViewModel, IBizListExpViewModel, IBizListViewModel, IBizMEditViewModel, IBizMPickUpViewModel, IBizOptViewModel, IBizPickupGridViewModel, IBizPickUpViewModel, IBizRedirectViewModel, IBizTabExpViewModel, IBizTreeExpViewModel, IBizTreeGridExViewModel, IBizTreeViewModel, IBizWFDynaEditViewModel, IBizWfDynaExpGridViewModel } from "../../model";

/**
 * 视图模型实例工厂
 *
 * @export
 * @class ViewFactory
 */
export class ViewFactory {
    /**
     * 获取对应的模型实例
     *
     * @static
     * @param {*} modelData
     * @memberof ViewFactory
     */
    public static getInstance(modelData: any, args: any){
        switch (modelData.viewType) {
            case 'DEEDITVIEW':
                return new IBizEditViewModel(modelData,args);
            case 'APPINDEXVIEW':
                return new IBizIndexViewModel(modelData,args);
            case 'DEGRIDVIEW':
                return new IBizGridViewModel(modelData,args);
            case 'DELISTVIEW':
                return new IBizListViewModel(modelData,args);
            case 'DECHARTVIEW':
                return new IBizChartViewModel(modelData,args);
            case 'DEDATAVIEW':
                return new IBizDataViewViewModel(modelData,args);
            case 'DECALENDARVIEW':
                return new IBizCalendarViewModel(modelData,args);
            case 'DEKANBANVIEW':
                return new IBizKanbanViewModel(modelData,args);
            case 'DEPORTALVIEW':
                return new IBizDePortalViewModel(modelData,args);
            case 'DETABEXPVIEW':
                return new IBizTabExpViewModel(modelData,args);
            case 'DEMEDITVIEW9':
                return new IBizMEditViewModel(modelData,args);
            case 'DEGANTTVIEW':
                return new IBizGanttViewModel(modelData,args);
            case 'DETREEVIEW':
                return new IBizTreeViewModel(modelData,args);
            case 'DETREEGRIDEXVIEW':
                return new IBizTreeGridExViewModel(modelData,args);
            case 'DELISTEXPVIEW':
                return new IBizListExpViewModel(modelData,args);
            case 'DEGRIDEXPVIEW':
                return new IBizGridExpViewModel(modelData,args);
            case 'DEDATAVIEWEXPVIEW':
                return new IBizDataViewExpViewModel(modelData,args);
            case 'DEPICKUPGRIDVIEW':
                return new IBizPickupGridViewModel(modelData,args);
            case 'DEMPICKUPVIEW':
                return new IBizMPickUpViewModel(modelData,args);
            case 'DEPICKUPVIEW':
                return new IBizPickUpViewModel(modelData,args);
            case 'DECALENDAREXPVIEW':
                return new IBizCalendarExpViewModel(modelData,args);
            case 'DETREEEXPVIEW':
                return new IBizTreeExpViewModel(modelData,args);
            case 'DEOPTVIEW':
                return new IBizOptViewModel(modelData,args);
            case 'DEEDITVIEW9':
                return new IBizEditViewModel(modelData,args);
            case 'DEWFDYNAEDITVIEW':
                return new IBizWFDynaEditViewModel(modelData,args);
            case 'DEWFDYNAEXPGRIDVIEW':
                return new IBizWfDynaExpGridViewModel(modelData,args);
            case 'DEFORMPICKUPDATAVIEW':
                return new IBizFormPickupDataViewModel(modelData,args);
            case 'DEGRIDVIEW9':
                return new IBizGridViewModel(modelData,args);
            case 'DEREDIRECTVIEW':
                return new IBizRedirectViewModel(modelData,args);
            default:
                return undefined;
        }
    }
}