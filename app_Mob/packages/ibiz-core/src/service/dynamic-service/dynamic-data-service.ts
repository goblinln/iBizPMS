import { DynamicCache } from "../../utils";
import { Http } from "../../utils/net/http";
import { AppServiceBase } from "../app-service/app-base.service";

/**
 * 动态模型数据服务类
 * 
 * @memberof DynamicDataService
 */
export class DynamicDataService {

    /**
     * Http对象
     * @type {Http}
     * 
     * @memberof DynamicDataService
     */
    private http: Http = Http.getInstance();

    /**
     * 应用上下文
     *
     * @private
     * @type {*}
     * @memberof DynamicDataService
     */
    private context: any = {};

    /**
     * 缓存模型数据
     * @type {DynamicCache}
     * 
     * @memberof DynamicDataService
     */
    private dynamicCacheService: DynamicCache = new DynamicCache();

    /**
     * 前端应用映射本地路径
     * @type {Map<string,string>}
     * 
     * @memberof DynamicDataService
     */
    protected appMappingMap: Map<string, string> = new Map();

    /**
     * 应用视图映射本地路径
     * @type {Map<string,string>}
     * 
     * @memberof DynamicDataService
     */
    protected appViewMappingMap: Map<string, string> = new Map();

    /**
     * 应用部件映射本地路径
     * @type {Map<string,string>}
     * 
     * @memberof DynamicDataService
     */
    protected appCtrlMappingMap: Map<string, string> = new Map();

    /**
     * 应用代码表映射本地路径
     * @type {Map<string,string>}
     * 
     * @memberof DynamicDataService
     */
    protected appCodeListMappingMap: Map<string, string> = new Map();

    /**
     * 应用实体映射本地路径
     * @type {Map<string,string>}
     * 
     * @memberof DynamicDataService
     */
    protected appEntityMappingMap: Map<string, string> = new Map();

    /**
     * 异常Json本地路径
     * @type {Map<string,string>}
     * 
     * @memberof DynamicDataService
     */
    protected appErrorMappingMap: Map<string, string> = new Map();

    /**
     * 初始化DynamicDataService
     * @param opts 额外参数
     * 
     * @memberof DynamicDataService
     */
    public constructor(opts: any = {}) {
        this.context = opts;
        this.registerAppMappingMap();
        this.registerAppViewMappingMap();
        this.registerAppCtrlMappingMap();
        this.registerAppCodeListMappingMap();
        this.registerAppEntityMappingMap();
        this.registerAppErrorMappingMap();
    }

    /**
     * 获取模型数据
     * 
     * @param path 动态路径
     * @param param 额外参数
     * 
     * @memberof DynamicDataService
     */
	public async getModelData(path: string, type: string, param: any) {
        let enableDynamic:boolean = AppServiceBase.getInstance().getAppEnvironment().bDynamic;
		path = enableDynamic ? path : this.computedTargetPath(type, path);
        if (!path && Object.is(type,"APP")){
            path = "./assets/json/app/app.json";
            enableDynamic = false;
        }
		if (this.dynamicCacheService.has(path)) {
			return this.dynamicCacheService.get(path);
		} else {
			let targetData: any;
			if (enableDynamic) {
				targetData = (await this.getRemoteModelData(path))['data'];
			} else {
				targetData = (await this.getLocalModelData(path))['data'];
			}
			this.dynamicCacheService.add(path, targetData);
			return targetData;
		}
	}

    /**
     * 获取远端模型数据
     * 
     * @param path 动态路径
     * @param enableDynamicMode 是否为动态模型(是则调用远端数据)
     * @param param 额外参数
     * 
     * @memberof DynamicDataService
     */
    public async getRemoteModelData(path: string) {
        const tempRemote: string = AppServiceBase.getInstance().getAppEnvironment().remoteDynaPath;
        let Url: string = `${tempRemote}${path}`;
        return this.http.get(Url);
    }

    /**
     * 获取本地模型数据
     * 
     * @param path 动态路径
     * @param enableDynamicMode 是否为动态模型(是则调用远端数据)
     * @param param 额外参数
     * 
     * @memberof DynamicDataService
     */
    public async getLocalModelData(path: string) {
        return this.http.get(path);
    }

    /**
     * 计算本地目标路径
     * 
     * @param type 类型
     * @param path 动态路径
     * 
     * @memberof DynamicDataService
     */
    public computedTargetPath(type: string, path: string): any {
        switch (type) {
            case "APP":
                return this.appMappingMap.get(path);
            case "VIEW":
                return this.appViewMappingMap.get(path);
            case "CTRL":
                return this.appCtrlMappingMap.get(path);
            case "ENTITY":
                return this.appEntityMappingMap.get(path);
            case "CODELIST":
                return this.appCodeListMappingMap.get(path);
            case "ERROR":
                return this.appErrorMappingMap.get(path);
            default:
                return path;
        }
    }

    /**
     * 注册ERROR映射本地路径
     * 
     * @memberof DynamicDataService
     */
    public registerAppErrorMappingMap() {
        this.appErrorMappingMap.set("APP", "./assets/json/error/app.json");
        this.appErrorMappingMap.set("ENTITY", "./assets/json/error/entity.json");
        this.appErrorMappingMap.set("VIEW", "./assets/json/error/view.json");
        this.appErrorMappingMap.set("CTRL", "./assets/json/error/ctrl.json");
        this.appErrorMappingMap.set("CODELIST", "./assets/json/error/codeList.json");
    }

    /**
     * 注册前端应用映射本地路径
     * 
     * @memberof DynamicDataService
     */
    public registerAppMappingMap() {
        this.appMappingMap.set("PSSYSAPPS/Mob/PSSYSAPP.json","./assets/json/app/app.json");
    }

    /**
     * 注册应用视图映射本地路径
     * 
     * @memberof DynamicDataService
     */
    public registerAppViewMappingMap() {
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPINDEXVIEWS/AppIndexView.json","./assets/json/view/ungroup/app-index-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskTeamMobEditView9.json","./assets/json/view/ibiz/task-team-mob-edit-view9.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskActiveMobTask.json","./assets/json/view/zentao/task-active-mob-task.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzWeeklyMobEditView.json","./assets/json/view/report/ibz-weekly-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzWeeklyMobMDView.json","./assets/json/view/report/ibz-weekly-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProjectTeamProjectTeamMobEditView.json","./assets/json/view/zentao/project-team-project-team-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BugUsr3MobMPickupLeftView.json","./assets/json/view/zentao/bug-usr3-mob-mpickup-left-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/StoryEditMobEditView.json","./assets/json/view/ibiz/story-edit-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProductTestMobMDView.json","./assets/json/view/zentao/product-test-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TestTaskMobOptionViewClose.json","./assets/json/view/zentao/test-task-mob-option-view-close.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProductModuleMobPickupView.json","./assets/json/view/ibiz/product-module-mob-pickup-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzWeeklyUsr2MobTabExpView.json","./assets/json/view/report/ibz-weekly-usr2-mob-tab-exp-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzDailyDailyMobMDView.json","./assets/json/view/report/ibz-daily-daily-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TodoNewMobEditView.json","./assets/json/view/ibiz/todo-new-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzReportlyMainInfoMobEditView.json","./assets/json/view/report/ibz-reportly-main-info-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzDailyDailyReportSubmitMobMDView.json","./assets/json/view/report/ibz-daily-daily-report-submit-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BugEditNewMobEditView.json","./assets/json/view/ibiz/bug-edit-new-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/SysEmployeeheadPortraitMobEditView.json","./assets/json/view/ou/sys-employeehead-portrait-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BugMobEditView.json","./assets/json/view/ibiz/bug-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/StoryLinkStoryMobPickupMDView.json","./assets/json/view/ibiz/story-link-story-mob-pickup-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BugUsr3MobMDView.json","./assets/json/view/zentao/bug-usr3-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProjectMobTabExpView.json","./assets/json/view/zentao/project-mob-tab-exp-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/CaseMobEditView.json","./assets/json/view/ibiz/case-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzMonthlyCreateMobEditView.json","./assets/json/view/report/ibz-monthly-create-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/CaseMobMDView.json","./assets/json/view/ibiz/case-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/StoryMobEditView.json","./assets/json/view/ibiz/story-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BugResolveMobEditView.json","./assets/json/view/ibiz/bug-resolve-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/StoryACMobOptionView.json","./assets/json/view/ibiz/story-acmob-option-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TodoMobMDView.json","./assets/json/view/ibiz/todo-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BuildMobMPickupView.json","./assets/json/view/ibiz/build-mob-mpickup-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ReleaseMobMDView.json","./assets/json/view/ibiz/release-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/StoryUsr2MobMDView_5219.json","./assets/json/view/zentao/story-usr2-mob-mdview-5219.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/SysEmployeeMpkMobPickupTreeView.json","./assets/json/view/ou/sys-employee-mpk-mob-pickup-tree-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/DocLibProjectDocLibMobTreeView.json","./assets/json/view/zentao/doc-lib-project-doc-lib-mob-tree-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskMyCompleteTaskMobMDViewWeekly.json","./assets/json/view/report/task-my-complete-task-mob-mdview-weekly.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProjectMobMDView.json","./assets/json/view/ibiz/project-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProductMobTabExpView.json","./assets/json/view/ibiz/product-mob-tab-exp-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProductMobPickupView.json","./assets/json/view/ibiz/product-mob-pickup-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzMonthlyMyReceivedMobTabExpView.json","./assets/json/view/report/ibz-monthly-my-received-mob-tab-exp-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ActionMobMDView9.json","./assets/json/view/ibiz/action-mob-mdview9.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProjectMobPickupView.json","./assets/json/view/ibiz/project-mob-pickup-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskEditMobEditView.json","./assets/json/view/ibiz/task-edit-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BugAssignToMobEditView.json","./assets/json/view/ibiz/bug-assign-to-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProductMobMDView.json","./assets/json/view/ibiz/product-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzReportlyCreateMobEditView.json","./assets/json/view/report/ibz-reportly-create-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskCancelMobOptionView.json","./assets/json/view/ibiz/task-cancel-mob-option-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BuildMobEditView.json","./assets/json/view/ibiz/build-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ModuleMobPickupMDView.json","./assets/json/view/ibiz/module-mob-pickup-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/SysEmployeeTreeMobPickupView.json","./assets/json/view/ou/sys-employee-tree-mob-pickup-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/StoryLinkStoryMobMPickupView.json","./assets/json/view/ibiz/story-link-story-mob-mpickup-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskAssMobMDView9.json","./assets/json/view/zentao/task-ass-mob-mdview9.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BuildLogMobEditView.json","./assets/json/view/ibiz/build-log-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BugAssMobMDView.json","./assets/json/view/ibiz/bug-ass-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/FileMobMDView9.json","./assets/json/view/zentao/file-mob-mdview9.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzMonthlyMyReceivedMobEditView.json","./assets/json/view/report/ibz-monthly-my-received-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProductLineMobPickupView.json","./assets/json/view/ibiz/product-line-mob-pickup-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProductStatsMobMDView.json","./assets/json/view/ibiz/product-stats-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzReportlyReportlyMobMDView.json","./assets/json/view/report/ibz-reportly-reportly-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzWeeklyMobEditViewMian.json","./assets/json/view/report/ibz-weekly-mob-edit-view-mian.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BuildEditMobEditView.json","./assets/json/view/ibiz/build-edit-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ModuleMobPickupView.json","./assets/json/view/ibiz/module-mob-pickup-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProductPlanMobEditView.json","./assets/json/view/ibiz/product-plan-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BugUsr2MobMDView.json","./assets/json/view/zentao/bug-usr2-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskTeamMobMDView9.json","./assets/json/view/ibiz/task-team-mob-mdview9.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BugColseMobEditView.json","./assets/json/view/ibiz/bug-colse-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProjectMobEditView.json","./assets/json/view/ibiz/project-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProductPlanMobTabExpView.json","./assets/json/view/zentao/product-plan-mob-tab-exp-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/SysEmployeeMobPickupTreeView.json","./assets/json/view/ou/sys-employee-mob-pickup-tree-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/SysEmployeeUserTreeMobMPickupView.json","./assets/json/view/ou/sys-employee-user-tree-mob-mpickup-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BuildMobPickupView.json","./assets/json/view/ibiz/build-mob-pickup-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TestTaskMobEditView.json","./assets/json/view/ibiz/test-task-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BugUsr3MobPickupBuildResolvedMDView.json","./assets/json/view/zentao/bug-usr3-mob-pickup-build-resolved-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProjectActiviteMobEditView.json","./assets/json/view/ibiz/project-activite-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskFavoriteMobMDView.json","./assets/json/view/ibiz/task-favorite-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/CaseUsr2MobMPickupView.json","./assets/json/view/zentao/case-usr2-mob-mpickup-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BugCloseMobOptionView.json","./assets/json/view/ibiz/bug-close-mob-option-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TestTaskNewMobEditView.json","./assets/json/view/ibiz/test-task-new-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProductStatsTestMobMDView.json","./assets/json/view/ibiz/product-stats-test-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzMonthlyEditMobEditView.json","./assets/json/view/report/ibz-monthly-edit-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzWeeklyMobEditViewMainMyTijiao.json","./assets/json/view/report/ibz-weekly-mob-edit-view-main-my-tijiao.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzDailyDailyCreateMobEditView.json","./assets/json/view/report/ibz-daily-daily-create-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzMonthlyMobEditView.json","./assets/json/view/report/ibz-monthly-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TestTaskMobOptionViewActivite.json","./assets/json/view/zentao/test-task-mob-option-view-activite.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/StoryAsMobOptionView.json","./assets/json/view/ibiz/story-as-mob-option-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzDailyDailyMobEditView.json","./assets/json/view/report/ibz-daily-daily-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/StoryAssMobMDView9.json","./assets/json/view/zentao/story-ass-mob-mdview9.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/StoryAssMoreMobMDView.json","./assets/json/view/ibiz/story-ass-more-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskMobOptionView.json","./assets/json/view/ibiz/task-mob-option-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BugUsr3MobMPickupView.json","./assets/json/view/zentao/bug-usr3-mob-mpickup-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/StoryUsr3MobMDView.json","./assets/json/view/zentao/story-usr3-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskFavoriteMobMDView9.json","./assets/json/view/zentao/task-favorite-mob-mdview9.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TodoMobOptionView.json","./assets/json/view/zentao/todo-mob-option-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzMyTerritoryMobMDView9.json","./assets/json/view/ibiz/ibz-my-territory-mob-mdview9.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProductLineMobPickupMDView.json","./assets/json/view/ibiz/product-line-mob-pickup-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProjectProjectTeamManageMobEditView.json","./assets/json/view/zentao/project-project-team-manage-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/StoryNewMobEditView.json","./assets/json/view/zentao/story-new-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TestSuiteMobTabExpView.json","./assets/json/view/zentao/test-suite-mob-tab-exp-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskEstimateMobMDView.json","./assets/json/view/ibiz/task-estimate-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProductCloseMobEditView.json","./assets/json/view/zentao/product-close-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskTeamMobMEditView9.json","./assets/json/view/ibiz/task-team-mob-medit-view9.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ActionALLMobMDView9.json","./assets/json/view/ibiz/action-allmob-mdview9.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BugUsr4MobMDView.json","./assets/json/view/zentao/bug-usr4-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskNewMobEditView.json","./assets/json/view/zentao/task-new-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzDailyDailyCompleteTaskMobMDView.json","./assets/json/view/report/ibz-daily-daily-complete-task-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/CaseUsr2MobPickupMDView.json","./assets/json/view/zentao/case-usr2-mob-pickup-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskMonthlyPlansTaskMobMDView.json","./assets/json/view/report/task-monthly-plans-task-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BugUsr6MobMDView.json","./assets/json/view/zentao/bug-usr6-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ReleaseMobPickupMDView.json","./assets/json/view/ibiz/release-mob-pickup-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/StoryUsr3MobPickupMDView.json","./assets/json/view/zentao/story-usr3-mob-pickup-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzDailyReportReceivedMobMDView.json","./assets/json/view/report/ibz-daily-report-received-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/CaseCreateCaseMobEditView.json","./assets/json/view/zentao/case-create-case-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BugAssMobMDView9.json","./assets/json/view/zentao/bug-ass-mob-mdview9.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProjectTeamMobMDView.json","./assets/json/view/ibiz/project-team-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPPORTALVIEWS/AppPortalView.json","./assets/json/view/ungroup/app-portal-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzWeeklyMobEditViewMainReceived.json","./assets/json/view/report/ibz-weekly-mob-edit-view-main-received.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzMonthlyMyReceivedMobMDView.json","./assets/json/view/report/ibz-monthly-my-received-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskMyCompleteTaskMobMDView1.json","./assets/json/view/zentao/task-my-complete-task-mob-mdview1.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/StoryFavoriteMobMDView.json","./assets/json/view/ibiz/story-favorite-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ReleaseMobTabExpView.json","./assets/json/view/zentao/release-mob-tab-exp-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzDailyDailyMobTabExpView.json","./assets/json/view/report/ibz-daily-daily-mob-tab-exp-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProductMobEditView.json","./assets/json/view/ibiz/product-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzDailyMyDailyMobTabExpView.json","./assets/json/view/report/ibz-daily-my-daily-mob-tab-exp-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskDailyDoneTaskMobMDView.json","./assets/json/view/report/task-daily-done-task-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzMonthlyMainInfoMobTabExpView.json","./assets/json/view/report/ibz-monthly-main-info-mob-tab-exp-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TestSuiteNewMobEditView.json","./assets/json/view/ibiz/test-suite-new-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/StoryUsr2MobPickupMDBuildView.json","./assets/json/view/zentao/story-usr2-mob-pickup-mdbuild-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BugUsr5MobMDView.json","./assets/json/view/zentao/bug-usr5-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BuildMobTabExpView.json","./assets/json/view/zentao/build-mob-tab-exp-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProjectModuleMobPickupMDView.json","./assets/json/view/ibiz/project-module-mob-pickup-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/SysUpdateFeaturesMobEditView.json","./assets/json/view/ibiz/sys-update-features-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzMyTerritoryMobTabExpView.json","./assets/json/view/ibiz/ibz-my-territory-mob-tab-exp-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProjectSupMobEditView.json","./assets/json/view/ibiz/project-sup-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/StoryMobPickupMDView.json","./assets/json/view/ibiz/story-mob-pickup-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/SysUpdateFeaturesYMobMDView9.json","./assets/json/view/ibiz/sys-update-features-ymob-mdview9.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzWeeklyUsr2MobTabExpViewMyTiJiao.json","./assets/json/view/report/ibz-weekly-usr2-mob-tab-exp-view-my-ti-jiao.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskSTARTMobOptionView.json","./assets/json/view/ibiz/task-startmob-option-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TodoMobListView.json","./assets/json/view/zentao/todo-mob-list-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ActionMoreMobMDView.json","./assets/json/view/zentao/action-more-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskMonthlyMyCompleteTaskMobMDView.json","./assets/json/view/report/task-monthly-my-complete-task-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TestSuiteMobEditView.json","./assets/json/view/ibiz/test-suite-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BugConfirmMobEditView.json","./assets/json/view/ibiz/bug-confirm-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BugRMobOptionView.json","./assets/json/view/ibiz/bug-rmob-option-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/DocLibMobProductTreeView.json","./assets/json/view/ibiz/doc-lib-mob-product-tree-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzMyTerritoryMobDashboardView.json","./assets/json/view/ibiz/ibz-my-territory-mob-dashboard-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BugNewMobEditView.json","./assets/json/view/zentao/bug-new-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskMobPickupMDView.json","./assets/json/view/ibiz/task-mob-pickup-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzWeeklyUsr2MobTabExpViewMyReceived.json","./assets/json/view/report/ibz-weekly-usr2-mob-tab-exp-view-my-received.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProjectMobChartView.json","./assets/json/view/zentao/project-mob-chart-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzWeeklyMobEditViewCreate.json","./assets/json/view/report/ibz-weekly-mob-edit-view-create.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/UserMobMPickupView.json","./assets/json/view/ibiz/user-mob-mpickup-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ReleaseEditMobEditView.json","./assets/json/view/ibiz/release-edit-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskEstimateMobMEditView9.json","./assets/json/view/zentao/task-estimate-mob-medit-view9.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzMonthlyMonthlyMobMDView.json","./assets/json/view/report/ibz-monthly-monthly-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskEstimateMobEditView9.json","./assets/json/view/ibiz/task-estimate-mob-edit-view9.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/StoryMobMDViewCurProject.json","./assets/json/view/ibiz/story-mob-mdview-cur-project.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzWeeklyUsr2MobEditView.json","./assets/json/view/report/ibz-weekly-usr2-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/StoryFavoriteMoreMobMDView.json","./assets/json/view/ibiz/story-favorite-more-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BuildMobPickupMDView.json","./assets/json/view/ibiz/build-mob-pickup-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzMyTerritoryDailyMobTabExpView.json","./assets/json/view/report/ibz-my-territory-daily-mob-tab-exp-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/CaseMobMDView_TestTask.json","./assets/json/view/ibiz/case-mob-mdview-test-task.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProductStatsMobTabExpView.json","./assets/json/view/ibiz/product-stats-mob-tab-exp-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BugUsr2MobMPickupView.json","./assets/json/view/zentao/bug-usr2-mob-mpickup-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BugUsr3MobPickupMDView.json","./assets/json/view/zentao/bug-usr3-mob-pickup-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskWeeklyPlansTaskMobMDView.json","./assets/json/view/report/task-weekly-plans-task-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BugAssMobOptionView.json","./assets/json/view/ibiz/bug-ass-mob-option-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskStopMobOptionView.json","./assets/json/view/ibiz/task-stop-mob-option-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/StoryLogMobMDView9.json","./assets/json/view/ibiz/story-log-mob-mdview9.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzMonthlyMainInfoMobEditView.json","./assets/json/view/report/ibz-monthly-main-info-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzMonthlyMySubmitMobMDView.json","./assets/json/view/report/ibz-monthly-my-submit-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/StoryMobPickupView.json","./assets/json/view/ibiz/story-mob-pickup-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/StoryRMobOptionView.json","./assets/json/view/ibiz/story-rmob-option-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TestTaskEditNewMobEditView.json","./assets/json/view/ibiz/test-task-edit-new-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BugACMobOptionView.json","./assets/json/view/ibiz/bug-acmob-option-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BuildLogMobMDView.json","./assets/json/view/ibiz/build-log-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/StoryMobMDView.json","./assets/json/view/zentao/story-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskGSMobOptionView.json","./assets/json/view/ibiz/task-gsmob-option-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzDailyMobEditView.json","./assets/json/view/report/ibz-daily-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/SysUpdateFeaturesMobMDView.json","./assets/json/view/ibiz/sys-update-features-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TestSuiteMobMDView.json","./assets/json/view/ibiz/test-suite-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BugCMobOptionView.json","./assets/json/view/ibiz/bug-cmob-option-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskUsr2MobOptionView.json","./assets/json/view/zentao/task-usr2-mob-option-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BugTestMobMDView.json","./assets/json/view/ibiz/bug-test-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzWeeklyUsr2MobMDView.json","./assets/json/view/report/ibz-weekly-usr2-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/StoryUsr4MobMDView.json","./assets/json/view/zentao/story-usr4-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/UserUserCenterMobEditView.json","./assets/json/view/zentao/user-user-center-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzFavoritesMobTabExpView.json","./assets/json/view/ibiz/ibz-favorites-mob-tab-exp-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskEstimateMobMDView9.json","./assets/json/view/ibiz/task-estimate-mob-mdview9.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzMyTerritoryMobCalendarView.json","./assets/json/view/ibiz/ibz-my-territory-mob-calendar-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskWeeklylyDoneTaskMobMDView.json","./assets/json/view/report/task-weeklyly-done-task-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProductProdMobTabExpView.json","./assets/json/view/zentao/product-prod-mob-tab-exp-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProjectCloseMobEditView.json","./assets/json/view/ibiz/project-close-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzDailyMyMobMDView.json","./assets/json/view/report/ibz-daily-my-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BugPlanMobMDView9.json","./assets/json/view/ibiz/bug-plan-mob-mdview9.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzDailyMyReMobEditView.json","./assets/json/view/report/ibz-daily-my-re-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProductPlanEditMobEditView.json","./assets/json/view/ibiz/product-plan-edit-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskComMobOptionView.json","./assets/json/view/ibiz/task-com-mob-option-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzMyTerritoryReportMobTabExpView.json","./assets/json/view/zentao/ibz-my-territory-report-mob-tab-exp-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskMobEditView.json","./assets/json/view/ibiz/task-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskAssMobMDView.json","./assets/json/view/ibiz/task-ass-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/StoryChangeMobOptionView.json","./assets/json/view/ibiz/story-change-mob-option-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskEstimateMobOptionView.json","./assets/json/view/ibiz/task-estimate-mob-option-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/SysUpdateFeaturesMobMDView9.json","./assets/json/view/ibiz/sys-update-features-mob-mdview9.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskMonthlyDoneTaskMobMDView.json","./assets/json/view/report/task-monthly-done-task-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/SysEmployeeLoginMobEditView.json","./assets/json/view/ou/sys-employee-login-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TodoMobEditView.json","./assets/json/view/ibiz/todo-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskMyCompleteTaskMobMDView.json","./assets/json/view/zentao/task-my-complete-task-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ReleaseMobPickupView.json","./assets/json/view/ibiz/release-mob-pickup-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BugUsr3MobMPickupBuildCreateBugView.json","./assets/json/view/zentao/bug-usr3-mob-mpickup-build-create-bug-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProjectModuleMobPickupView.json","./assets/json/view/ibiz/project-module-mob-pickup-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TestTaskMobOptionViewBlock.json","./assets/json/view/zentao/test-task-mob-option-view-block.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzReportMobMDView.json","./assets/json/view/report/ibz-report-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProductPlanMobMDView.json","./assets/json/view/ibiz/product-plan-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TestTaskMobTabExpView.json","./assets/json/view/zentao/test-task-mob-tab-exp-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/StoryUsr2MobMPickupView.json","./assets/json/view/zentao/story-usr2-mob-mpickup-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProductPlanNewMobEditView.json","./assets/json/view/ibiz/product-plan-new-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TestTaskMobMDView.json","./assets/json/view/ibiz/test-task-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzDailyDailyPlansTomorrowTaskMobMDView.json","./assets/json/view/report/ibz-daily-daily-plans-tomorrow-task-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProductPlanUsr2MobPickupMDView.json","./assets/json/view/zentao/product-plan-usr2-mob-pickup-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BugMobMDView.json","./assets/json/view/ibiz/bug-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/SysUpdateLogMobEditView.json","./assets/json/view/ibiz/sys-update-log-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BugUsr3MobPickupMDView1.json","./assets/json/view/zentao/bug-usr3-mob-pickup-mdview1.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BugActivationMobEditView.json","./assets/json/view/ibiz/bug-activation-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BuildNewMobEditView.json","./assets/json/view/ibiz/build-new-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskFavoriteMoreMobMDView.json","./assets/json/view/ibiz/task-favorite-more-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/StoryMobMDView9.json","./assets/json/view/ibiz/story-mob-mdview9.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskCloseMobOptionView.json","./assets/json/view/ibiz/task-close-mob-option-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ActionMobMapView.json","./assets/json/view/zentao/action-mob-map-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/StoryFavoriteMobMDView9.json","./assets/json/view/zentao/story-favorite-mob-mdview9.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/CaseMobMDView_TestSuite.json","./assets/json/view/ibiz/case-mob-mdview-test-suite.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskMobMDView.json","./assets/json/view/zentao/task-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ReleaseNewMobEditView.json","./assets/json/view/zentao/release-new-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProductNewMobEditView.json","./assets/json/view/ibiz/product-new-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskDailyPlansTaskMobMDView.json","./assets/json/view/report/task-daily-plans-task-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProductMobPickupMDView.json","./assets/json/view/ibiz/product-mob-pickup-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProjectTeamProjectTeamMobMEditView.json","./assets/json/view/zentao/project-team-project-team-mob-medit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/CaseStepMobMDView9.json","./assets/json/view/zentao/case-step-mob-mdview9.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskMyPlansTomorrowTaskMobMDView.json","./assets/json/view/report/task-my-plans-tomorrow-task-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskMobPickupView.json","./assets/json/view/ibiz/task-mob-pickup-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TestTaskMobOptionViewStart.json","./assets/json/view/zentao/test-task-mob-option-view-start.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/StoryUsr2MobMDView.json","./assets/json/view/zentao/story-usr2-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/StoryUsr2MobMPickupBuildView.json","./assets/json/view/zentao/story-usr2-mob-mpickup-build-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskMyCompleteTaskMobMDViewNextPlanWeekly.json","./assets/json/view/report/task-my-complete-task-mob-mdview-next-plan-weekly.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/StoryMobOptionView.json","./assets/json/view/zentao/story-mob-option-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzReportlyMobEditView.json","./assets/json/view/report/ibz-reportly-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/DocLibMobEditView.json","./assets/json/view/ibiz/doc-lib-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProductMobChartView.json","./assets/json/view/ibiz/product-mob-chart-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/StoryUsr3MobMPickupView.json","./assets/json/view/zentao/story-usr3-mob-mpickup-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BugUsr2MobPickupMDView.json","./assets/json/view/zentao/bug-usr2-mob-pickup-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BugAssMoreMobMDView.json","./assets/json/view/ibiz/bug-ass-more-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BuildMobMDView.json","./assets/json/view/ibiz/build-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzDailyDailyInfoMobEditView.json","./assets/json/view/report/ibz-daily-daily-info-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/StoryAssMobMDView.json","./assets/json/view/ibiz/story-ass-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProductModuleMobPickupMDView.json","./assets/json/view/ibiz/product-module-mob-pickup-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ReleaseMobEditView.json","./assets/json/view/ibiz/release-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskAssMoreMobMDView.json","./assets/json/view/ibiz/task-ass-more-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/UserMobPickupMDView.json","./assets/json/view/ibiz/user-mob-pickup-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/StoryMobListView.json","./assets/json/view/ibiz/story-mob-list-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProjectNewMobEditView.json","./assets/json/view/zentao/project-new-mob-edit-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProjectMobPickupMDView.json","./assets/json/view/ibiz/project-mob-pickup-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/BugLogMobMDView9.json","./assets/json/view/ibiz/bug-log-mob-mdview9.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/SysUpdateLogMobMDView.json","./assets/json/view/ibiz/sys-update-log-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/TaskMonthlyMyPlansTaskMobMDView.json","./assets/json/view/report/task-monthly-my-plans-task-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/StoryUsr2MobPickupMDView.json","./assets/json/view/zentao/story-usr2-mob-pickup-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/ProductMobChartView9.json","./assets/json/view/zentao/product-mob-chart-view9.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPPORTALVIEWS/AppPortalView2.json","./assets/json/view/ungroup/app-portal-view2.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/StoryCMobOptionView.json","./assets/json/view/zentao/story-cmob-option-view.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/IbzReportMyReMobMDView.json","./assets/json/view/report/ibz-report-my-re-mob-mdview.json");
        this.appViewMappingMap.set("PSSYSAPPS/Mob/PSAPPDEVIEWS/UserMobPickupView.json","./assets/json/view/zentao/user-mob-pickup-view.json");
    }

    /**
     * 注册应用部件映射本地路径
     * 
     * @memberof DynamicDataService
     */
    public registerAppCtrlMappingMap() {
        this.appCtrlMappingMap.set("AppIndexView","./assets/json/widgets/app/app-index-view-appmenu.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/ibztaskteam/PSFORMS/MobMain.json","./assets/json/widgets/ibztaskteam/mob-main-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSFORMS/MobActiviteForm.json","./assets/json/widgets/task/mob-activite-form-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzWeekly/PSFORMS/MobWaitRead.json","./assets/json/widgets/ibz-weekly/mob-wait-read-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzWeekly/PSMOBMDCTRLS/WeeklyMyRecevied.json","./assets/json/widgets/ibz-weekly/weekly-my-recevied-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/ProjectTeam/PSFORMS/ProjectTeamMob.json","./assets/json/widgets/project-team/project-team-mob-form.json");
        this.appCtrlMappingMap.set("Usr3MobMPickupLeftViewpickupviewpanel","./assets/json/widgets/bug/usr3-mob-mpickup-left-viewpickupviewpanel-pickupviewpanel.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSFORMS/MobMainEdit.json","./assets/json/widgets/story/mob-main-edit-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Product/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/product/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Product/PSMOBMDCTRLS/Mob.json","./assets/json/widgets/product/mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/TestTask/PSFORMS/MobClose.json","./assets/json/widgets/test-task/mob-close-form.json");
        this.appCtrlMappingMap.set("MobPickupViewpickupviewpanel","./assets/json/widgets/product-module/mob-pickup-viewpickupviewpanel-pickupviewpanel.json");
        this.appCtrlMappingMap.set("Usr2MobTabExpViewtabviewpanel","./assets/json/widgets/ibz-weekly/usr2-mob-tab-exp-viewtabviewpanel-tabviewpanel.json");
        this.appCtrlMappingMap.set("Usr2MobTabExpViewtabviewpanel2","./assets/json/widgets/ibz-weekly/usr2-mob-tab-exp-viewtabviewpanel2-tabviewpanel.json");
        this.appCtrlMappingMap.set("Usr2MobTabExpViewtabviewpanel3","./assets/json/widgets/ibz-weekly/usr2-mob-tab-exp-viewtabviewpanel3-tabviewpanel.json");
        this.appCtrlMappingMap.set("Usr2MobTabExpViewtabexppanel","./assets/json/widgets/ibz-weekly/usr2-mob-tab-exp-viewtabexppanel-tabexppanel.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzDaily/PSMOBMDCTRLS/Mob.json","./assets/json/widgets/ibz-daily/mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Todo/PSFORMS/MobNew.json","./assets/json/widgets/todo/mob-new-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzReportly/PSFORMS/MobReportlyDetail.json","./assets/json/widgets/ibz-reportly/mob-reportly-detail-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzDaily/PSMOBMDCTRLS/DailyReportSubmitMob.json","./assets/json/widgets/ibz-daily/daily-report-submit-mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug/PSFORMS/MobMainDataEdit.json","./assets/json/widgets/bug/mob-main-data-edit-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/SysEmployee/PSFORMS/MobMain.json","./assets/json/widgets/sys-employee/mob-main-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug/PSFORMS/MobMain.json","./assets/json/widgets/bug/mob-main-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/story/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSMOBMDCTRLS/MOB_DuoShuJuChoice_Story.json","./assets/json/widgets/story/mob-duo-shu-ju-choice-story-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug/PSMOBMDCTRLS/MOB_Release_YiLiuBug.json","./assets/json/widgets/bug/mob-release-yi-liu-bug-mobmdctrl.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabviewpanel2","./assets/json/widgets/project/mob-tab-exp-viewtabviewpanel2-tabviewpanel.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabviewpanel4","./assets/json/widgets/project/mob-tab-exp-viewtabviewpanel4-tabviewpanel.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabviewpanel5","./assets/json/widgets/project/mob-tab-exp-viewtabviewpanel5-tabviewpanel.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabviewpanel6","./assets/json/widgets/project/mob-tab-exp-viewtabviewpanel6-tabviewpanel.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabviewpanel3","./assets/json/widgets/project/mob-tab-exp-viewtabviewpanel3-tabviewpanel.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabexppanel","./assets/json/widgets/project/mob-tab-exp-viewtabexppanel-tabexppanel.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Case/PSFORMS/MobMain.json","./assets/json/widgets/case/mob-main-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzMonthly/PSFORMS/MobNew.json","./assets/json/widgets/ibz-monthly/mob-new-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Case/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/case/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Case/PSMOBMDCTRLS/Mob.json","./assets/json/widgets/case/mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSFORMS/MobMain.json","./assets/json/widgets/story/mob-main-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug/PSFORMS/ResolveMob.json","./assets/json/widgets/bug/resolve-mob-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSFORMS/ActiviteMob.json","./assets/json/widgets/story/activite-mob-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Todo/PSMOBMDCTRLS/Mob.json","./assets/json/widgets/todo/mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Build/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/build/mob-def-searchform.json");
        this.appCtrlMappingMap.set("MobMPickupViewpickupviewpanel","./assets/json/widgets/build/mob-mpickup-viewpickupviewpanel-pickupviewpanel.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Release/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/release/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Release/PSMOBMDCTRLS/Mob.json","./assets/json/widgets/release/mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSMOBMDCTRLS/Mob_ProductPlan_Story.json","./assets/json/widgets/story/mob-product-plan-story-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/SysEmployee/PSTREEVIEWS/EmpTreeMpk.json","./assets/json/widgets/sys-employee/emp-tree-mpk-treeview.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/DocLib/PSTREEVIEWS/DocLibTreeProjectMob.json","./assets/json/widgets/doc-lib/doc-lib-tree-project-mob-treeview.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSMOBMDCTRLS/MyCompleteTaskMonthly.json","./assets/json/widgets/task/my-complete-task-monthly-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Project/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/project/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Project/PSMOBMDCTRLS/Mob.json","./assets/json/widgets/project/mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabviewpanel","./assets/json/widgets/product/mob-tab-exp-viewtabviewpanel-tabviewpanel.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabviewpanel2","./assets/json/widgets/product/mob-tab-exp-viewtabviewpanel2-tabviewpanel.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabviewpanel4","./assets/json/widgets/product/mob-tab-exp-viewtabviewpanel4-tabviewpanel.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabviewpanel5","./assets/json/widgets/product/mob-tab-exp-viewtabviewpanel5-tabviewpanel.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabexppanel","./assets/json/widgets/product/mob-tab-exp-viewtabexppanel-tabexppanel.json");
        this.appCtrlMappingMap.set("MobPickupViewpickupviewpanel","./assets/json/widgets/product/mob-pickup-viewpickupviewpanel-pickupviewpanel.json");
        this.appCtrlMappingMap.set("MyReceivedMobTabExpViewtabviewpanel2","./assets/json/widgets/ibz-monthly/my-received-mob-tab-exp-viewtabviewpanel2-tabviewpanel.json");
        this.appCtrlMappingMap.set("MyReceivedMobTabExpViewtabviewpanel","./assets/json/widgets/ibz-monthly/my-received-mob-tab-exp-viewtabviewpanel-tabviewpanel.json");
        this.appCtrlMappingMap.set("MyReceivedMobTabExpViewtabviewpanel3","./assets/json/widgets/ibz-monthly/my-received-mob-tab-exp-viewtabviewpanel3-tabviewpanel.json");
        this.appCtrlMappingMap.set("MyReceivedMobTabExpViewtabexppanel","./assets/json/widgets/ibz-monthly/my-received-mob-tab-exp-viewtabexppanel-tabexppanel.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Action/PSMOBMDCTRLS/Mob.json","./assets/json/widgets/action/mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("MobPickupViewpickupviewpanel","./assets/json/widgets/project/mob-pickup-viewpickupviewpanel-pickupviewpanel.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSFORMS/MobMainEdit.json","./assets/json/widgets/task/mob-main-edit-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug/PSFORMS/AssignToMob.json","./assets/json/widgets/bug/assign-to-mob-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Product/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/product/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Product/PSMOBMDCTRLS/Mob.json","./assets/json/widgets/product/mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzReportly/PSFORMS/MobCreate.json","./assets/json/widgets/ibz-reportly/mob-create-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSFORMS/ClosePauseCancelFormMob.json","./assets/json/widgets/task/close-pause-cancel-form-mob-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Build/PSFORMS/MobMain.json","./assets/json/widgets/build/mob-main-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Module/PSMOBMDCTRLS/Mob.json","./assets/json/widgets/module/mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("TreeMobPickupViewpickupviewpanel","./assets/json/widgets/sys-employee/tree-mob-pickup-viewpickupviewpanel-pickupviewpanel.json");
        this.appCtrlMappingMap.set("LinkStoryMobMPickupViewpickupviewpanel","./assets/json/widgets/story/link-story-mob-mpickup-viewpickupviewpanel-pickupviewpanel.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/task/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSMOBMDCTRLS/AssMobDASHBOARD.json","./assets/json/widgets/task/ass-mob-dashboard-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Build/PSFORMS/LogMain.json","./assets/json/widgets/build/log-main-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/bug/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug/PSMOBMDCTRLS/AssMOBDASHBOARD.json","./assets/json/widgets/bug/ass-mobdashboard-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/File/PSMOBMDCTRLS/MobList.json","./assets/json/widgets/file/mob-list-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzMonthly/PSFORMS/MobMainInfo.json","./assets/json/widgets/ibz-monthly/mob-main-info-form.json");
        this.appCtrlMappingMap.set("MobPickupViewpickupviewpanel","./assets/json/widgets/product-line/mob-pickup-viewpickupviewpanel-pickupviewpanel.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/ProductStats/PSMOBMDCTRLS/Mob.json","./assets/json/widgets/product-stats/mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzReportly/PSMOBMDCTRLS/MyReportly.json","./assets/json/widgets/ibz-reportly/my-reportly-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzWeekly/PSFORMS/MobWeekEdit.json","./assets/json/widgets/ibz-weekly/mob-week-edit-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Build/PSFORMS/MobEditForm.json","./assets/json/widgets/build/mob-edit-form-form.json");
        this.appCtrlMappingMap.set("MobPickupViewpickupviewpanel","./assets/json/widgets/module/mob-pickup-viewpickupviewpanel-pickupviewpanel.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/ProductPlan/PSFORMS/MobMain.json","./assets/json/widgets/product-plan/mob-main-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug/PSMOBMDCTRLS/MOB_ReleaseLink_reBug.json","./assets/json/widgets/bug/mob-release-link-re-bug-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/TaskTeam/PSMOBMDCTRLS/Mob.json","./assets/json/widgets/task-team/mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug/PSFORMS/CloseMob.json","./assets/json/widgets/bug/close-mob-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Project/PSFORMS/MobMain.json","./assets/json/widgets/project/mob-main-form.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabviewpanel3","./assets/json/widgets/product-plan/mob-tab-exp-viewtabviewpanel3-tabviewpanel.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabviewpanel2","./assets/json/widgets/product-plan/mob-tab-exp-viewtabviewpanel2-tabviewpanel.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabviewpanel4","./assets/json/widgets/product-plan/mob-tab-exp-viewtabviewpanel4-tabviewpanel.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabexppanel","./assets/json/widgets/product-plan/mob-tab-exp-viewtabexppanel-tabexppanel.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/SysEmployee/PSTREEVIEWS/EmpTree.json","./assets/json/widgets/sys-employee/emp-tree-treeview.json");
        this.appCtrlMappingMap.set("UserTreeMobMPickupViewpickupviewpanel","./assets/json/widgets/sys-employee/user-tree-mob-mpickup-viewpickupviewpanel-pickupviewpanel.json");
        this.appCtrlMappingMap.set("MobPickupViewpickupviewpanel","./assets/json/widgets/build/mob-pickup-viewpickupviewpanel-pickupviewpanel.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/TestTask/PSFORMS/MobMain.json","./assets/json/widgets/test-task/mob-main-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/bug/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug/PSMOBMDCTRLS/MOB_BuildLink_Bug.json","./assets/json/widgets/bug/mob-build-link-bug-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Project/PSFORMS/ActiviteMob.json","./assets/json/widgets/project/activite-mob-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/task/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSMOBMDCTRLS/FavoriteMOBDas.json","./assets/json/widgets/task/favorite-mobdas-mobmdctrl.json");
        this.appCtrlMappingMap.set("Usr2MobMPickupViewpickupviewpanel","./assets/json/widgets/case/usr2-mob-mpickup-viewpickupviewpanel-pickupviewpanel.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug/PSFORMS/CloseMob.json","./assets/json/widgets/bug/close-mob-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/TestTask/PSFORMS/MobEditTable.json","./assets/json/widgets/test-task/mob-edit-table-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/ProductStats/PSMOBMDCTRLS/Mob.json","./assets/json/widgets/product-stats/mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzMonthly/PSFORMS/MobNew.json","./assets/json/widgets/ibz-monthly/mob-new-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzWeekly/PSFORMS/MobInfoMain2.json","./assets/json/widgets/ibz-weekly/mob-info-main2-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzDaily/PSFORMS/MobDailyEdit.json","./assets/json/widgets/ibz-daily/mob-daily-edit-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzMonthly/PSFORMS/MonthlyInfoDingDing.json","./assets/json/widgets/ibz-monthly/monthly-info-ding-ding-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/TestTask/PSFORMS/MobActivite.json","./assets/json/widgets/test-task/mob-activite-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSFORMS/AssignToMob.json","./assets/json/widgets/story/assign-to-mob-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzDaily/PSFORMS/MobDailyEdit.json","./assets/json/widgets/ibz-daily/mob-daily-edit-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/story/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSMOBMDCTRLS/AssMOBDASHBOARD.json","./assets/json/widgets/story/ass-mobdashboard-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/story/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSMOBMDCTRLS/AssMOB.json","./assets/json/widgets/story/ass-mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSFORMS/AssignFormMob.json","./assets/json/widgets/task/assign-form-mob-form.json");
        this.appCtrlMappingMap.set("Usr3MobMPickupViewpickupviewpanel","./assets/json/widgets/bug/usr3-mob-mpickup-viewpickupviewpanel-pickupviewpanel.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSMOBMDCTRLS/MOB_Release_Story.json","./assets/json/widgets/story/mob-release-story-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/task/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSMOBMDCTRLS/FavoriteMOBDas.json","./assets/json/widgets/task/favorite-mobdas-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Todo/PSFORMS/AssMob.json","./assets/json/widgets/todo/ass-mob-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzMyTerritory/PSMOBMDCTRLS/Mob.json","./assets/json/widgets/ibz-my-territory/mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/ProductLine/PSMOBMDCTRLS/Mob.json","./assets/json/widgets/product-line/mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Project/PSFORMS/MobProjectTeamManage.json","./assets/json/widgets/project/mob-project-team-manage-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSFORMS/MobNewForm.json","./assets/json/widgets/story/mob-new-form-form.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabviewpanel","./assets/json/widgets/test-suite/mob-tab-exp-viewtabviewpanel-tabviewpanel.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabviewpanel2","./assets/json/widgets/test-suite/mob-tab-exp-viewtabviewpanel2-tabviewpanel.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabexppanel","./assets/json/widgets/test-suite/mob-tab-exp-viewtabexppanel-tabexppanel.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/TaskEstimate/PSMOBMDCTRLS/Mob.json","./assets/json/widgets/task-estimate/mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Product/PSFORMS/MobClose.json","./assets/json/widgets/product/mob-close-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/ibztaskteam/PSMULTIEDITVIEWPANELS/Main.json","./assets/json/widgets/ibztaskteam/main-multieditviewpanel.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Action/PSMOBMDCTRLS/TrendsMob.json","./assets/json/widgets/action/trends-mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug/PSMOBMDCTRLS/MOB_Build_ResolvedBug.json","./assets/json/widgets/bug/mob-build-resolved-bug-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSFORMS/MobNewFrom.json","./assets/json/widgets/task/mob-new-from-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSMOBMDCTRLS/MyCompleteTaskMob.json","./assets/json/widgets/task/my-complete-task-mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Case/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/case/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Case/PSMOBMDCTRLS/Mob_Task_Case.json","./assets/json/widgets/case/mob-task-case-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSMOBMDCTRLS/MyPlansTaskMonthly.json","./assets/json/widgets/task/my-plans-task-monthly-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug/PSMOBMDCTRLS/Mob_Plan.json","./assets/json/widgets/bug/mob-plan-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Release/PSMOBMDCTRLS/Mob_2622.json","./assets/json/widgets/release/mob-2622-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSMOBMDCTRLS/MOB_ProjectLinkStory.json","./assets/json/widgets/story/mob-project-link-story-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzDaily/PSMOBMDCTRLS/ReportReceivedMob.json","./assets/json/widgets/ibz-daily/report-received-mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Case/PSFORMS/Createmob.json","./assets/json/widgets/case/createmob-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/bug/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug/PSMOBMDCTRLS/AssMOBDASHBOARD.json","./assets/json/widgets/bug/ass-mobdashboard-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/ProjectTeam/PSMOBMDCTRLS/Mob.json","./assets/json/widgets/project-team/mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("AppPortalView_db","./assets/json/widgets/app/app-portal-view-db-dashboard.json");
        this.appCtrlMappingMap.set("ImgswipeStyleMenu","./assets/json/widgets/app/imgswipe-style-menu-portlet.json");
        this.appCtrlMappingMap.set("ImgswipeStyleMenu","./assets/json/widgets/app/imgswipe-style-menu-appmenu.json");
        this.appCtrlMappingMap.set("IconStyleMenu","./assets/json/widgets/app/icon-style-menu-portlet.json");
        this.appCtrlMappingMap.set("IconStyleMenu","./assets/json/widgets/app/icon-style-menu-appmenu.json");
        this.appCtrlMappingMap.set("ListMenu","./assets/json/widgets/app/list-menu-portlet.json");
        this.appCtrlMappingMap.set("ListMenu","./assets/json/widgets/app/list-menu-appmenu.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzWeekly/PSFORMS/MobInfoMain2.json","./assets/json/widgets/ibz-weekly/mob-info-main2-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzMonthly/PSMOBMDCTRLS/MyReceived.json","./assets/json/widgets/ibz-monthly/my-received-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSMOBMDCTRLS/MyCompleteTaskMob.json","./assets/json/widgets/task/my-complete-task-mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/story/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSMOBMDCTRLS/FavoriteMOBDas.json","./assets/json/widgets/story/favorite-mobdas-mobmdctrl.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabviewpanel2","./assets/json/widgets/release/mob-tab-exp-viewtabviewpanel2-tabviewpanel.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabviewpanel4","./assets/json/widgets/release/mob-tab-exp-viewtabviewpanel4-tabviewpanel.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabviewpanel5","./assets/json/widgets/release/mob-tab-exp-viewtabviewpanel5-tabviewpanel.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabviewpanel3","./assets/json/widgets/release/mob-tab-exp-viewtabviewpanel3-tabviewpanel.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabexppanel","./assets/json/widgets/release/mob-tab-exp-viewtabexppanel-tabexppanel.json");
        this.appCtrlMappingMap.set("DailyMobTabExpViewtabviewpanel","./assets/json/widgets/ibz-daily/daily-mob-tab-exp-viewtabviewpanel-tabviewpanel.json");
        this.appCtrlMappingMap.set("DailyMobTabExpViewtabviewpanel2","./assets/json/widgets/ibz-daily/daily-mob-tab-exp-viewtabviewpanel2-tabviewpanel.json");
        this.appCtrlMappingMap.set("DailyMobTabExpViewtabviewpanel3","./assets/json/widgets/ibz-daily/daily-mob-tab-exp-viewtabviewpanel3-tabviewpanel.json");
        this.appCtrlMappingMap.set("DailyMobTabExpViewtabexppanel","./assets/json/widgets/ibz-daily/daily-mob-tab-exp-viewtabexppanel-tabexppanel.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Product/PSFORMS/MobMain.json","./assets/json/widgets/product/mob-main-form.json");
        this.appCtrlMappingMap.set("MyDailyMobTabExpViewtabviewpanel","./assets/json/widgets/ibz-daily/my-daily-mob-tab-exp-viewtabviewpanel-tabviewpanel.json");
        this.appCtrlMappingMap.set("MyDailyMobTabExpViewtabviewpanel2","./assets/json/widgets/ibz-daily/my-daily-mob-tab-exp-viewtabviewpanel2-tabviewpanel.json");
        this.appCtrlMappingMap.set("MyDailyMobTabExpViewtabviewpanel3","./assets/json/widgets/ibz-daily/my-daily-mob-tab-exp-viewtabviewpanel3-tabviewpanel.json");
        this.appCtrlMappingMap.set("MyDailyMobTabExpViewtabexppanel","./assets/json/widgets/ibz-daily/my-daily-mob-tab-exp-viewtabexppanel-tabexppanel.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSMOBMDCTRLS/MyCompleteTaskMob.json","./assets/json/widgets/task/my-complete-task-mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("MainInfoMobTabExpViewtabviewpanel2","./assets/json/widgets/ibz-monthly/main-info-mob-tab-exp-viewtabviewpanel2-tabviewpanel.json");
        this.appCtrlMappingMap.set("MainInfoMobTabExpViewtabviewpanel","./assets/json/widgets/ibz-monthly/main-info-mob-tab-exp-viewtabviewpanel-tabviewpanel.json");
        this.appCtrlMappingMap.set("MainInfoMobTabExpViewtabviewpanel3","./assets/json/widgets/ibz-monthly/main-info-mob-tab-exp-viewtabviewpanel3-tabviewpanel.json");
        this.appCtrlMappingMap.set("MainInfoMobTabExpViewtabexppanel","./assets/json/widgets/ibz-monthly/main-info-mob-tab-exp-viewtabexppanel-tabexppanel.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/TestSuite/PSFORMS/MobEditTable.json","./assets/json/widgets/test-suite/mob-edit-table-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/story/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSMOBMDCTRLS/MOB_BuildLink_Story.json","./assets/json/widgets/story/mob-build-link-story-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug/PSMOBMDCTRLS/MOB_Build_CreateBug.json","./assets/json/widgets/bug/mob-build-create-bug-mobmdctrl.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabviewpanel2","./assets/json/widgets/build/mob-tab-exp-viewtabviewpanel2-tabviewpanel.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabviewpanel4","./assets/json/widgets/build/mob-tab-exp-viewtabviewpanel4-tabviewpanel.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabviewpanel3","./assets/json/widgets/build/mob-tab-exp-viewtabviewpanel3-tabviewpanel.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabviewpanel5","./assets/json/widgets/build/mob-tab-exp-viewtabviewpanel5-tabviewpanel.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabexppanel","./assets/json/widgets/build/mob-tab-exp-viewtabexppanel-tabexppanel.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/ProjectModule/PSMOBMDCTRLS/Mob.json","./assets/json/widgets/project-module/mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/SysUpdateFeatures/PSFORMS/MobMain.json","./assets/json/widgets/sys-update-features/mob-main-form.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabviewpanel4","./assets/json/widgets/ibz-my-territory/mob-tab-exp-viewtabviewpanel4-tabviewpanel.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabviewpanel","./assets/json/widgets/ibz-my-territory/mob-tab-exp-viewtabviewpanel-tabviewpanel.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabviewpanel5","./assets/json/widgets/ibz-my-territory/mob-tab-exp-viewtabviewpanel5-tabviewpanel.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabexppanel","./assets/json/widgets/ibz-my-territory/mob-tab-exp-viewtabexppanel-tabexppanel.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Project/PSFORMS/SuspendNCloseMob.json","./assets/json/widgets/project/suspend-nclose-mob-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/story/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSMOBMDCTRLS/Mob_3817.json","./assets/json/widgets/story/mob-3817-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/SysUpdateFeatures/PSMOBMDCTRLS/MOBY.json","./assets/json/widgets/sys-update-features/moby-mobmdctrl.json");
        this.appCtrlMappingMap.set("Usr2MobTabExpViewMyTiJiaotabviewpanel","./assets/json/widgets/ibz-weekly/usr2-mob-tab-exp-view-my-ti-jiaotabviewpanel-tabviewpanel.json");
        this.appCtrlMappingMap.set("Usr2MobTabExpViewMyTiJiaotabviewpanel2","./assets/json/widgets/ibz-weekly/usr2-mob-tab-exp-view-my-ti-jiaotabviewpanel2-tabviewpanel.json");
        this.appCtrlMappingMap.set("Usr2MobTabExpViewMyTiJiaotabviewpanel3","./assets/json/widgets/ibz-weekly/usr2-mob-tab-exp-view-my-ti-jiaotabviewpanel3-tabviewpanel.json");
        this.appCtrlMappingMap.set("Usr2MobTabExpViewMyTiJiaotabexppanel","./assets/json/widgets/ibz-weekly/usr2-mob-tab-exp-view-my-ti-jiaotabexppanel-tabexppanel.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSFORMS/MobStartForm.json","./assets/json/widgets/task/mob-start-form-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Todo/PSMOBMDCTRLS/Mob.json","./assets/json/widgets/todo/mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Action/PSMOBMDCTRLS/TrendsMobMore.json","./assets/json/widgets/action/trends-mob-more-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSMOBMDCTRLS/MyCompleteTaskMonthly.json","./assets/json/widgets/task/my-complete-task-monthly-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/TestSuite/PSFORMS/MobMain.json","./assets/json/widgets/test-suite/mob-main-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug/PSFORMS/ConfirmMob.json","./assets/json/widgets/bug/confirm-mob-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug/PSFORMS/ResolveMob.json","./assets/json/widgets/bug/resolve-mob-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/DocLib/PSTREEVIEWS/DocLibTreeProductMob.json","./assets/json/widgets/doc-lib/doc-lib-tree-product-mob-treeview.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzMyTerritory/PSDASHBOARDS/MobHome.json","./assets/json/widgets/ibz-my-territory/mob-home-dashboard.json");
        this.appCtrlMappingMap.set("MyWork","./assets/json/widgets/ibz-my-territory/my-work-portlet.json");
        this.appCtrlMappingMap.set("AllTrendsMob","./assets/json/widgets/action/all-trends-mob-portlet.json");
        this.appCtrlMappingMap.set("MyFavoriteTask","./assets/json/widgets/task/my-favorite-task-portlet.json");
        this.appCtrlMappingMap.set("MyStory","./assets/json/widgets/story/my-story-portlet.json");
        this.appCtrlMappingMap.set("MyTaskMob","./assets/json/widgets/task/my-task-mob-portlet.json");
        this.appCtrlMappingMap.set("MobDashboardViewdashboard_container6","./assets/json/widgets/ibz-my-territory/mob-dashboard-viewdashboard-container6-portlet.json");
        this.appCtrlMappingMap.set("MobDashboardViewdashboard_container7","./assets/json/widgets/ibz-my-territory/mob-dashboard-viewdashboard-container7-portlet.json");
        this.appCtrlMappingMap.set("MyBugMob","./assets/json/widgets/bug/my-bug-mob-portlet.json");
        this.appCtrlMappingMap.set("MobDashboardViewdashboard_container8","./assets/json/widgets/ibz-my-territory/mob-dashboard-viewdashboard-container8-portlet.json");
        this.appCtrlMappingMap.set("MobDashboardViewdashboard_container9","./assets/json/widgets/ibz-my-territory/mob-dashboard-viewdashboard-container9-portlet.json");
        this.appCtrlMappingMap.set("MOBMyFavoriteStory","./assets/json/widgets/story/mobmy-favorite-story-portlet.json");
        this.appCtrlMappingMap.set("MobDashboardViewdashboard_container5","./assets/json/widgets/ibz-my-territory/mob-dashboard-viewdashboard-container5-portlet.json");
        this.appCtrlMappingMap.set("MobDashboardViewdashboard_container4","./assets/json/widgets/ibz-my-territory/mob-dashboard-viewdashboard-container4-portlet.json");
        this.appCtrlMappingMap.set("MobDashboardViewdashboard_container3","./assets/json/widgets/ibz-my-territory/mob-dashboard-viewdashboard-container3-portlet.json");
        this.appCtrlMappingMap.set("MobDashboardViewdashboard_container2","./assets/json/widgets/ibz-my-territory/mob-dashboard-viewdashboard-container2-portlet.json");
        this.appCtrlMappingMap.set("ProjectStatusBarMob","./assets/json/widgets/project/project-status-bar-mob-portlet.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Project/PSCHARTS/ProjectStatusBarMob.json","./assets/json/widgets/project/project-status-bar-mob-chart.json");
        this.appCtrlMappingMap.set("MobDashboardViewdashboard_container1","./assets/json/widgets/ibz-my-territory/mob-dashboard-viewdashboard-container1-portlet.json");
        this.appCtrlMappingMap.set("ProductStatusChartMob","./assets/json/widgets/product/product-status-chart-mob-portlet.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Product/PSCHARTS/ProductStatusPieMob.json","./assets/json/widgets/product/product-status-pie-mob-chart.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug/PSFORMS/MobNewFROM.json","./assets/json/widgets/bug/mob-new-from-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/task/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSMOBMDCTRLS/Mob_3335.json","./assets/json/widgets/task/mob-3335-mobmdctrl.json");
        this.appCtrlMappingMap.set("Usr2MobTabExpViewMyReceivedtabviewpanel","./assets/json/widgets/ibz-weekly/usr2-mob-tab-exp-view-my-receivedtabviewpanel-tabviewpanel.json");
        this.appCtrlMappingMap.set("Usr2MobTabExpViewMyReceivedtabviewpanel2","./assets/json/widgets/ibz-weekly/usr2-mob-tab-exp-view-my-receivedtabviewpanel2-tabviewpanel.json");
        this.appCtrlMappingMap.set("Usr2MobTabExpViewMyReceivedtabviewpanel3","./assets/json/widgets/ibz-weekly/usr2-mob-tab-exp-view-my-receivedtabviewpanel3-tabviewpanel.json");
        this.appCtrlMappingMap.set("Usr2MobTabExpViewMyReceivedtabexppanel","./assets/json/widgets/ibz-weekly/usr2-mob-tab-exp-view-my-receivedtabexppanel-tabexppanel.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Project/PSCHARTS/ProjectStatusBarMob.json","./assets/json/widgets/project/project-status-bar-mob-chart.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzWeekly/PSFORMS/MobWeekEdit.json","./assets/json/widgets/ibz-weekly/mob-week-edit-form.json");
        this.appCtrlMappingMap.set("MobMPickupViewpickupviewpanel","./assets/json/widgets/user/mob-mpickup-viewpickupviewpanel-pickupviewpanel.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Release/PSFORMS/MobEditForm.json","./assets/json/widgets/release/mob-edit-form-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzTaskestimate/PSMULTIEDITVIEWPANELS/Main.json","./assets/json/widgets/ibz-taskestimate/main-multieditviewpanel.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzMonthly/PSMOBMDCTRLS/MyMonthly.json","./assets/json/widgets/ibz-monthly/my-monthly-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzTaskestimate/PSFORMS/NewForm.json","./assets/json/widgets/ibz-taskestimate/new-form-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/story/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSMOBMDCTRLS/MOBPorject.json","./assets/json/widgets/story/mobporject-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzWeekly/PSFORMS/MobInfoMain2.json","./assets/json/widgets/ibz-weekly/mob-info-main2-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/story/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSMOBMDCTRLS/FavoriteMOB.json","./assets/json/widgets/story/favorite-mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Build/PSMOBMDCTRLS/Mob.json","./assets/json/widgets/build/mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("DailyMobTabExpViewtabviewpanel3","./assets/json/widgets/ibz-my-territory/daily-mob-tab-exp-viewtabviewpanel3-tabviewpanel.json");
        this.appCtrlMappingMap.set("DailyMobTabExpViewtabexppanel","./assets/json/widgets/ibz-my-territory/daily-mob-tab-exp-viewtabexppanel-tabexppanel.json");
        this.appCtrlMappingMap.set("DailyMobTabExpViewtabviewpanel","./assets/json/widgets/ibz-my-territory/daily-mob-tab-exp-viewtabviewpanel-tabviewpanel.json");
        this.appCtrlMappingMap.set("DailyMobTabExpViewtabviewpanel2","./assets/json/widgets/ibz-my-territory/daily-mob-tab-exp-viewtabviewpanel2-tabviewpanel.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Case/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/case/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Case/PSMOBMDCTRLS/Exp_TestTask.json","./assets/json/widgets/case/exp-test-task-mobmdctrl.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabviewpanel","./assets/json/widgets/product-stats/mob-tab-exp-viewtabviewpanel-tabviewpanel.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabviewpanel2","./assets/json/widgets/product-stats/mob-tab-exp-viewtabviewpanel2-tabviewpanel.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabviewpanel4","./assets/json/widgets/product-stats/mob-tab-exp-viewtabviewpanel4-tabviewpanel.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabviewpanel5","./assets/json/widgets/product-stats/mob-tab-exp-viewtabviewpanel5-tabviewpanel.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabviewpanel3","./assets/json/widgets/product-stats/mob-tab-exp-viewtabviewpanel3-tabviewpanel.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabexppanel","./assets/json/widgets/product-stats/mob-tab-exp-viewtabexppanel-tabexppanel.json");
        this.appCtrlMappingMap.set("Usr2MobMPickupViewpickupviewpanel","./assets/json/widgets/bug/usr2-mob-mpickup-viewpickupviewpanel-pickupviewpanel.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/bug/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug/PSMOBMDCTRLS/MOB_ReleaseLink_ResolvedBug.json","./assets/json/widgets/bug/mob-release-link-resolved-bug-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSMOBMDCTRLS/MyPlansTaskMonthly.json","./assets/json/widgets/task/my-plans-task-monthly-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug/PSFORMS/AssignToMob.json","./assets/json/widgets/bug/assign-to-mob-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSFORMS/ClosePauseCancelFormMob.json","./assets/json/widgets/task/close-pause-cancel-form-mob-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSMOBMDCTRLS/MOBLog.json","./assets/json/widgets/story/moblog-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzMonthly/PSFORMS/MobMainInfo.json","./assets/json/widgets/ibz-monthly/mob-main-info-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzMonthly/PSMOBMDCTRLS/MySubmit.json","./assets/json/widgets/ibz-monthly/my-submit-mobmdctrl.json");
        this.appCtrlMappingMap.set("MobPickupViewpickupviewpanel","./assets/json/widgets/story/mob-pickup-viewpickupviewpanel-pickupviewpanel.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSFORMS/ReviewMob.json","./assets/json/widgets/story/review-mob-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/TestTask/PSFORMS/MobEditForm.json","./assets/json/widgets/test-task/mob-edit-form-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug/PSFORMS/ActivationMob.json","./assets/json/widgets/bug/activation-mob-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Build/PSMOBMDCTRLS/MobLog.json","./assets/json/widgets/build/mob-log-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/story/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSMOBMDCTRLS/Mob.json","./assets/json/widgets/story/mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSFORMS/EstimateMob.json","./assets/json/widgets/task/estimate-mob-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzDaily/PSFORMS/DailyInfoDingDing.json","./assets/json/widgets/ibz-daily/daily-info-ding-ding-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/SysUpdateFeatures/PSMOBMDCTRLS/Mob.json","./assets/json/widgets/sys-update-features/mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/TestSuite/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/test-suite/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/TestSuite/PSMOBMDCTRLS/Mob.json","./assets/json/widgets/test-suite/mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug/PSFORMS/ConfirmMob.json","./assets/json/widgets/bug/confirm-mob-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSFORMS/MobStartForm.json","./assets/json/widgets/task/mob-start-form-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/bug/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug/PSMOBMDCTRLS/Mob.json","./assets/json/widgets/bug/mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzWeekly/PSMOBMDCTRLS/MyWeekly.json","./assets/json/widgets/ibz-weekly/my-weekly-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSMOBMDCTRLS/MOB_Build_Story.json","./assets/json/widgets/story/mob-build-story-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/User/PSFORMS/UserCenter.json","./assets/json/widgets/user/user-center-form.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabviewpanel","./assets/json/widgets/ibz-favorites/mob-tab-exp-viewtabviewpanel-tabviewpanel.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabviewpanel2","./assets/json/widgets/ibz-favorites/mob-tab-exp-viewtabviewpanel2-tabviewpanel.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabexppanel","./assets/json/widgets/ibz-favorites/mob-tab-exp-viewtabexppanel-tabexppanel.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/TaskEstimate/PSMOBMDCTRLS/Mob.json","./assets/json/widgets/task-estimate/mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzMyTerritory/PSCALENDARS/MyWork.json","./assets/json/widgets/ibz-my-territory/my-work-calendar.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSMOBMDCTRLS/MyCompleteTaskMonthly.json","./assets/json/widgets/task/my-complete-task-monthly-mobmdctrl.json");
        this.appCtrlMappingMap.set("ProdMobTabExpViewtabviewpanel2","./assets/json/widgets/product/prod-mob-tab-exp-viewtabviewpanel2-tabviewpanel.json");
        this.appCtrlMappingMap.set("ProdMobTabExpViewtabviewpanel3","./assets/json/widgets/product/prod-mob-tab-exp-viewtabviewpanel3-tabviewpanel.json");
        this.appCtrlMappingMap.set("ProdMobTabExpViewtabviewpanel4","./assets/json/widgets/product/prod-mob-tab-exp-viewtabviewpanel4-tabviewpanel.json");
        this.appCtrlMappingMap.set("ProdMobTabExpViewtabviewpanel6","./assets/json/widgets/product/prod-mob-tab-exp-viewtabviewpanel6-tabviewpanel.json");
        this.appCtrlMappingMap.set("ProdMobTabExpViewtabexppanel","./assets/json/widgets/product/prod-mob-tab-exp-viewtabexppanel-tabexppanel.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Project/PSFORMS/SuspendNCloseMob.json","./assets/json/widgets/project/suspend-nclose-mob-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzDaily/PSMOBMDCTRLS/Mob.json","./assets/json/widgets/ibz-daily/mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/bug/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug/PSMOBMDCTRLS/Mob_Plan.json","./assets/json/widgets/bug/mob-plan-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzDaily/PSFORMS/DailyInfoMob.json","./assets/json/widgets/ibz-daily/daily-info-mob-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/ProductPlan/PSFORMS/MobMainTable.json","./assets/json/widgets/product-plan/mob-main-table-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSFORMS/CompleteFormMob.json","./assets/json/widgets/task/complete-form-mob-form.json");
        this.appCtrlMappingMap.set("ReportMobTabExpViewtabexppanel","./assets/json/widgets/ibz-my-territory/report-mob-tab-exp-viewtabexppanel-tabexppanel.json");
        this.appCtrlMappingMap.set("ReportMobTabExpViewtabviewpanel","./assets/json/widgets/ibz-my-territory/report-mob-tab-exp-viewtabviewpanel-tabviewpanel.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSFORMS/MobMain.json","./assets/json/widgets/task/mob-main-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/task/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSMOBMDCTRLS/AssMobDASHBOARD.json","./assets/json/widgets/task/ass-mob-dashboard-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSFORMS/MobChageForm.json","./assets/json/widgets/story/mob-chage-form-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/TaskEstimate/PSFORMS/MobMain.json","./assets/json/widgets/task-estimate/mob-main-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/SysUpdateFeatures/PSMOBMDCTRLS/Mob.json","./assets/json/widgets/sys-update-features/mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSMOBMDCTRLS/MyCompleteTaskMonthly.json","./assets/json/widgets/task/my-complete-task-monthly-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/SysEmployee/PSFORMS/MobInfo.json","./assets/json/widgets/sys-employee/mob-info-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Todo/PSFORMS/MobMain.json","./assets/json/widgets/todo/mob-main-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSMOBMDCTRLS/MyCompleteTaskMob.json","./assets/json/widgets/task/my-complete-task-mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("MobPickupViewpickupviewpanel","./assets/json/widgets/release/mob-pickup-viewpickupviewpanel-pickupviewpanel.json");
        this.appCtrlMappingMap.set("Usr3MobMPickupBuildCreateBugViewpickupviewpanel","./assets/json/widgets/bug/usr3-mob-mpickup-build-create-bug-viewpickupviewpanel-pickupviewpanel.json");
        this.appCtrlMappingMap.set("MobPickupViewpickupviewpanel","./assets/json/widgets/project-module/mob-pickup-viewpickupviewpanel-pickupviewpanel.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/TestTask/PSFORMS/Mobblock.json","./assets/json/widgets/test-task/mobblock-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzReport/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/ibz-report/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzReport/PSMOBMDCTRLS/MySubmit.json","./assets/json/widgets/ibz-report/my-submit-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/ProductPlan/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/product-plan/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/ProductPlan/PSMOBMDCTRLS/Mob.json","./assets/json/widgets/product-plan/mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabviewpanel","./assets/json/widgets/test-task/mob-tab-exp-viewtabviewpanel-tabviewpanel.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabviewpanel2","./assets/json/widgets/test-task/mob-tab-exp-viewtabviewpanel2-tabviewpanel.json");
        this.appCtrlMappingMap.set("MobTabExpViewtabexppanel","./assets/json/widgets/test-task/mob-tab-exp-viewtabexppanel-tabexppanel.json");
        this.appCtrlMappingMap.set("Usr2MobMPickupViewpickupviewpanel","./assets/json/widgets/story/usr2-mob-mpickup-viewpickupviewpanel-pickupviewpanel.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/ProductPlan/PSFORMS/MobNewForm.json","./assets/json/widgets/product-plan/mob-new-form-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/TestTask/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/test-task/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/TestTask/PSMOBMDCTRLS/Mob.json","./assets/json/widgets/test-task/mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSMOBMDCTRLS/MyPlansTomorrowTaskMob.json","./assets/json/widgets/task/my-plans-tomorrow-task-mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/ProductPlan/PSMOBMDCTRLS/MOB_ProjectLinkStory.json","./assets/json/widgets/product-plan/mob-project-link-story-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/bug/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug/PSMOBMDCTRLS/Mob.json","./assets/json/widgets/bug/mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/SysUpdateLog/PSFORMS/MobMain.json","./assets/json/widgets/sys-update-log/mob-main-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/bug/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug/PSMOBMDCTRLS/MOB_ReleaseLink_LeftBug.json","./assets/json/widgets/bug/mob-release-link-left-bug-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug/PSFORMS/ActivationMob.json","./assets/json/widgets/bug/activation-mob-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Build/PSFORMS/MobNewForm.json","./assets/json/widgets/build/mob-new-form-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/task/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSMOBMDCTRLS/FavoriteMOB.json","./assets/json/widgets/task/favorite-mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/story/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSMOBMDCTRLS/Mob_Plan.json","./assets/json/widgets/story/mob-plan-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSFORMS/ClosePauseCancelFormMob.json","./assets/json/widgets/task/close-pause-cancel-form-mob-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Action/PSMAPS/UserLoginPosition.json","./assets/json/widgets/action/user-login-position-map.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/story/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSMOBMDCTRLS/FavoriteMOBDas.json","./assets/json/widgets/story/favorite-mobdas-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Case/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/case/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Case/PSMOBMDCTRLS/Exp_TestSuite.json","./assets/json/widgets/case/exp-test-suite-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/task/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSMOBMDCTRLS/Mob.json","./assets/json/widgets/task/mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Release/PSFORMS/MobNewForm.json","./assets/json/widgets/release/mob-new-form-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Product/PSFORMS/MobNewFROM.json","./assets/json/widgets/product/mob-new-from-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSMOBMDCTRLS/MyPlansTomorrowTaskMob.json","./assets/json/widgets/task/my-plans-tomorrow-task-mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Product/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/product/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Product/PSMOBMDCTRLS/Mob.json","./assets/json/widgets/product/mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IBZPROJECTTEAM/PSMULTIEDITVIEWPANELS/ProjectTeamMob.json","./assets/json/widgets/ibzprojectteam/project-team-mob-multieditviewpanel.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/CaseStep/PSMOBMDCTRLS/Mob.json","./assets/json/widgets/case-step/mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSMOBMDCTRLS/MyPlansTomorrowTaskMob.json","./assets/json/widgets/task/my-plans-tomorrow-task-mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("MobPickupViewpickupviewpanel","./assets/json/widgets/task/mob-pickup-viewpickupviewpanel-pickupviewpanel.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/TestTask/PSFORMS/MobStart.json","./assets/json/widgets/test-task/mob-start-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSMOBMDCTRLS/Mob.json","./assets/json/widgets/story/mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("Usr2MobMPickupBuildViewpickupviewpanel","./assets/json/widgets/story/usr2-mob-mpickup-build-viewpickupviewpanel-pickupviewpanel.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSMOBMDCTRLS/MyCompleteTaskMonthly.json","./assets/json/widgets/task/my-complete-task-monthly-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSFORMS/MobAccordingToPlan.json","./assets/json/widgets/story/mob-according-to-plan-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzReportly/PSFORMS/MobReportlyDetail.json","./assets/json/widgets/ibz-reportly/mob-reportly-detail-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/DocLib/PSFORMS/MobMain.json","./assets/json/widgets/doc-lib/mob-main-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Product/PSCHARTS/ProductStatusPieMob.json","./assets/json/widgets/product/product-status-pie-mob-chart.json");
        this.appCtrlMappingMap.set("Usr3MobMPickupViewpickupviewpanel","./assets/json/widgets/story/usr3-mob-mpickup-viewpickupviewpanel-pickupviewpanel.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug/PSMOBMDCTRLS/MOB_ProductPlanLink_Bug.json","./assets/json/widgets/bug/mob-product-plan-link-bug-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/bug/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug/PSMOBMDCTRLS/Mob_My.json","./assets/json/widgets/bug/mob-my-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Build/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/build/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Build/PSMOBMDCTRLS/Mob.json","./assets/json/widgets/build/mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzDaily/PSFORMS/DailyInfoMob.json","./assets/json/widgets/ibz-daily/daily-info-mob-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/story/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSMOBMDCTRLS/AssMOBDASHBOARD.json","./assets/json/widgets/story/ass-mobdashboard-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/ProductModule/PSMOBMDCTRLS/Mob.json","./assets/json/widgets/product-module/mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Release/PSFORMS/MobMain.json","./assets/json/widgets/release/mob-main-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/task/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSMOBMDCTRLS/AssMob.json","./assets/json/widgets/task/ass-mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/User/PSMOBMDCTRLS/Mob.json","./assets/json/widgets/user/mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSMOBMDCTRLS/AssMOBDASHBOARD.json","./assets/json/widgets/story/ass-mobdashboard-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Project/PSFORMS/MobNewForm.json","./assets/json/widgets/project/mob-new-form-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Project/PSMOBMDCTRLS/Mob.json","./assets/json/widgets/project/mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug/PSMOBMDCTRLS/MobLog.json","./assets/json/widgets/bug/mob-log-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/SysUpdateLog/PSMOBMDCTRLS/Mob.json","./assets/json/widgets/sys-update-log/mob-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task/PSMOBMDCTRLS/MyPlansTaskMonthly.json","./assets/json/widgets/task/my-plans-task-monthly-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/story/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSMOBMDCTRLS/MOB_Release_Story.json","./assets/json/widgets/story/mob-release-story-mobmdctrl.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Product/PSCHARTS/ProductStatusPieMob.json","./assets/json/widgets/product/product-status-pie-mob-chart.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Product/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/product/mob-def-searchform.json");
        this.appCtrlMappingMap.set("AppPortalView2_db","./assets/json/widgets/app/app-portal-view2-db-dashboard.json");
        this.appCtrlMappingMap.set("My","./assets/json/widgets/app/my-portlet.json");
        this.appCtrlMappingMap.set("My","./assets/json/widgets/app/my-appmenu.json");
        this.appCtrlMappingMap.set("reportNew","./assets/json/widgets/app/report-new-portlet.json");
        this.appCtrlMappingMap.set("reportNew","./assets/json/widgets/app/report-new-appmenu.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story/PSFORMS/CloseMob.json","./assets/json/widgets/story/close-mob-form.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzReport/PSSEARCHFORMS/MobDef.json","./assets/json/widgets/ibz-report/mob-def-searchform.json");
        this.appCtrlMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzReport/PSMOBMDCTRLS/MyRe.json","./assets/json/widgets/ibz-report/my-re-mobmdctrl.json");
        this.appCtrlMappingMap.set("MobPickupViewpickupviewpanel","./assets/json/widgets/user/mob-pickup-viewpickupviewpanel-pickupviewpanel.json");
    }

    /**
     * 注册应用代码表映射本地路径
     * 
     * @memberof DynamicDataService
     */
    public registerAppCodeListMappingMap() {
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/ActionManner.json","./assets/json/codelist/action-manner.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/UserRealName_valueofid.json","./assets/json/codelist/user-real-name-valueofid.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Story__closed_reason.json","./assets/json/codelist/story-closed-reason.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/UserRealNameProject.json","./assets/json/codelist/user-real-name-project.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/NeedNotReview.json","./assets/json/codelist/need-not-review.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Task__color.json","./assets/json/codelist/task-color.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Pri.json","./assets/json/codelist/pri.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/ProjectProductPlan.json","./assets/json/codelist/project-product-plan.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/CodeList.json","./assets/json/codelist/code-list.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/MyCompleteTask.json","./assets/json/codelist/my-complete-task.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/ReportStatus.json","./assets/json/codelist/report-status.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Project__status.json","./assets/json/codelist/project-status.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/ProductBranch_Cache.json","./assets/json/codelist/product-branch-cache.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/SysOperator.json","./assets/json/codelist/sys-operator.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/StoryPoints.json","./assets/json/codelist/story-points.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Bug__os.json","./assets/json/codelist/bug-os.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Bug__severity.json","./assets/json/codelist/bug-severity.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Bug__browser.json","./assets/json/codelist/bug-browser.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Todo__status.json","./assets/json/codelist/todo-status.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Project__type.json","./assets/json/codelist/project-type.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Story__status.json","./assets/json/codelist/story-status.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Action__read.json","./assets/json/codelist/action-read.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/ProductBranch.json","./assets/json/codelist/product-branch.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Testtask__pri.json","./assets/json/codelist/testtask-pri.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Story__pri.json","./assets/json/codelist/story-pri.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Bug__severity_mob.json","./assets/json/codelist/bug-severity-mob.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/NeedNotReviewNew.json","./assets/json/codelist/need-not-review-new.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Story__type.json","./assets/json/codelist/story-type.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Testcase__color.json","./assets/json/codelist/testcase-color.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Action__object_type.json","./assets/json/codelist/action-object-type.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Task__pri.json","./assets/json/codelist/task-pri.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Release__status.json","./assets/json/codelist/release-status.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Realease_sort.json","./assets/json/codelist/realease-sort.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/TestCaseStatusGrid.json","./assets/json/codelist/test-case-status-grid.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Product.json","./assets/json/codelist/product.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/UserRealName.json","./assets/json/codelist/user-real-name.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Zt__delta.json","./assets/json/codelist/zt-delta.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/BugCodeList2.json","./assets/json/codelist/bug-code-list2.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Date_disable.json","./assets/json/codelist/date-disable.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Story__stage.json","./assets/json/codelist/story-stage.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/MonthlyCompleteTaskChoice.json","./assets/json/codelist/monthly-complete-task-choice.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Bug__pri.json","./assets/json/codelist/bug-pri.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/SYS_UPDATE_LOG_TYPE.json","./assets/json/codelist/sys-update-log-type.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Bug__status.json","./assets/json/codelist/bug-status.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Testsuite__type.json","./assets/json/codelist/testsuite-type.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Testcase__stage.json","./assets/json/codelist/testcase-stage.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Casestep__type.json","./assets/json/codelist/casestep-type.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Product__acl.json","./assets/json/codelist/product-acl.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/UserRealNameUnAssignTo_Gird.json","./assets/json/codelist/user-real-name-un-assign-to-gird.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Role.json","./assets/json/codelist/role.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/CurProductBuild.json","./assets/json/codelist/cur-product-build.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Type.json","./assets/json/codelist/type.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Zt__productplan.json","./assets/json/codelist/zt-productplan.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/UserRealName_Gird.json","./assets/json/codelist/user-real-name-gird.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/YesNo.json","./assets/json/codelist/yes-no.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/RelatedStory.json","./assets/json/codelist/related-story.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/CaseTestTaskQuickpachet.json","./assets/json/codelist/case-test-task-quickpachet.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Product__type.json","./assets/json/codelist/product-type.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Story__review_result.json","./assets/json/codelist/story-review-result.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/CurProductPlan.json","./assets/json/codelist/cur-product-plan.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/UserRealNameProductTeam.json","./assets/json/codelist/user-real-name-product-team.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Task__status.json","./assets/json/codelist/task-status.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/YesNo2.json","./assets/json/codelist/yes-no2.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/MyPlanTask.json","./assets/json/codelist/my-plan-task.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/UserRealNameTaskTeam.json","./assets/json/codelist/user-real-name-task-team.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/TaskStatusCK.json","./assets/json/codelist/task-status-ck.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Product__status.json","./assets/json/codelist/product-status.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/ConfigManagementstatus.json","./assets/json/codelist/config-managementstatus.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/CycleType.json","./assets/json/codelist/cycle-type.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/CurStory.json","./assets/json/codelist/cur-story.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/ProductPlan.json","./assets/json/codelist/product-plan.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Task__type.json","./assets/json/codelist/task-type.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/CurCaseVersion.json","./assets/json/codelist/cur-case-version.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Project__acl.json","./assets/json/codelist/project-acl.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Bug__type.json","./assets/json/codelist/bug-type.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Team__type.json","./assets/json/codelist/team-type.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/BugModule.json","./assets/json/codelist/bug-module.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Action__type.json","./assets/json/codelist/action-type.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/User__gender.json","./assets/json/codelist/user-gender.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/MobTestQuickpacket.json","./assets/json/codelist/mob-test-quickpacket.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/BeginendDropList.json","./assets/json/codelist/beginend-drop-list.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Task_quickpacket.json","./assets/json/codelist/task-quickpacket.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/MobBugQuickGroup.json","./assets/json/codelist/mob-bug-quick-group.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Task__closed_reason.json","./assets/json/codelist/task-closed-reason.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Story__color.json","./assets/json/codelist/story-color.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/ReportType.json","./assets/json/codelist/report-type.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Testcase__type.json","./assets/json/codelist/testcase-type.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/MobStoryQuickGroup.json","./assets/json/codelist/mob-story-quick-group.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Testcase__status.json","./assets/json/codelist/testcase-status.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Testrun__result.json","./assets/json/codelist/testrun-result.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/YesNo3.json","./assets/json/codelist/yes-no3.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Story__source.json","./assets/json/codelist/story-source.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Testcase__pri.json","./assets/json/codelist/testcase-pri.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/CaseQuickpachet.json","./assets/json/codelist/case-quickpachet.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Testcase__result.json","./assets/json/codelist/testcase-result.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Bug__resolution.json","./assets/json/codelist/bug-resolution.json");
        this.appCodeListMappingMap.set("PSSYSAPPS/Mob/PSAPPCODELISTS/Testtask__status.json","./assets/json/codelist/testtask-status.json");
    }

    /**
     * 注册应用实体映射本地路径
     * 
     * @memberof DynamicDataService
     */
    public registerAppEntityMappingMap() {
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzMonthly.json","./assets/json/entity/ibz-monthly.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/SysEmployee.json","./assets/json/entity/sys-employee.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/TaskTeam.json","./assets/json/entity/task-team.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzMyTerritory.json","./assets/json/entity/ibz-my-territory.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Action.json","./assets/json/entity/action.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/SysTeam.json","./assets/json/entity/sys-team.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/TaskEstimate.json","./assets/json/entity/task-estimate.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzFavorites.json","./assets/json/entity/ibz-favorites.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/SysPost.json","./assets/json/entity/sys-post.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/SysDepartment.json","./assets/json/entity/sys-department.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/CaseStep.json","./assets/json/entity/case-step.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzReportly.json","./assets/json/entity/ibz-reportly.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/DocContent.json","./assets/json/entity/doc-content.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/TestModule.json","./assets/json/entity/test-module.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/ProjectStats.json","./assets/json/entity/project-stats.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/ibztaskteam.json","./assets/json/entity/ibztaskteam.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzTaskestimate.json","./assets/json/entity/ibz-taskestimate.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Project.json","./assets/json/entity/project.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/SysTeamMember.json","./assets/json/entity/sys-team-member.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/TestSuite.json","./assets/json/entity/test-suite.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/ProjectTeam.json","./assets/json/entity/project-team.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/File.json","./assets/json/entity/file.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/StorySpec.json","./assets/json/entity/story-spec.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Branch.json","./assets/json/entity/branch.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Release.json","./assets/json/entity/release.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Case.json","./assets/json/entity/case.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/ProjectModule.json","./assets/json/entity/project-module.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzDaily.json","./assets/json/entity/ibz-daily.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/DocLib.json","./assets/json/entity/doc-lib.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Module.json","./assets/json/entity/module.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Build.json","./assets/json/entity/build.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Bug.json","./assets/json/entity/bug.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Product.json","./assets/json/entity/product.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/ProductPlan.json","./assets/json/entity/product-plan.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Doc.json","./assets/json/entity/doc.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/SysUpdateLog.json","./assets/json/entity/sys-update-log.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/User.json","./assets/json/entity/user.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/DynaDashboard.json","./assets/json/entity/dyna-dashboard.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/ProductModule.json","./assets/json/entity/product-module.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzReport.json","./assets/json/entity/ibz-report.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/ProductStats.json","./assets/json/entity/product-stats.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IBzDoc.json","./assets/json/entity/ibz-doc.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/UserContact.json","./assets/json/entity/user-contact.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/SysUpdateFeatures.json","./assets/json/entity/sys-update-features.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Task.json","./assets/json/entity/task.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Todo.json","./assets/json/entity/todo.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/SysOrganization.json","./assets/json/entity/sys-organization.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/DocLibModule.json","./assets/json/entity/doc-lib-module.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/TestTask.json","./assets/json/entity/test-task.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IbzWeekly.json","./assets/json/entity/ibz-weekly.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/IBZPROJECTTEAM.json","./assets/json/entity/ibzprojectteam.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/Story.json","./assets/json/entity/story.json");
        this.appEntityMappingMap.set("PSSYSAPPS/Mob/PSAPPDATAENTITIES/ProductLine.json","./assets/json/entity/product-line.json");
    }



}