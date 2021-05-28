import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IProjectTaskReport, ProjectTaskReport } from '../../entities';
import keys from '../../entities/project-task-report/project-task-report-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';
import { GetUserConcatLogic } from '../../logic/entity/project-task-report/get-user-concat/get-user-concat-logic';

/**
 * 任务服务对象基类
 *
 * @export
 * @class ProjectTaskReportBaseService
 * @extends {EntityBaseService}
 */
export class ProjectTaskReportBaseService extends EntityBaseService<IProjectTaskReport> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'ProjectTaskReport';
    protected APPDENAMEPLURAL = 'ProjectTaskReports';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['id','name',];
    protected selectContextParam = {
        project: 'project',
    };

    newEntity(data: IProjectTaskReport): ProjectTaskReport {
        return new ProjectTaskReport(data);
    }

    async addLocal(context: IContext, entity: IProjectTaskReport): Promise<IProjectTaskReport | null> {
        return this.cache.add(context, new ProjectTaskReport(entity) as any);
    }

    async createLocal(context: IContext, entity: IProjectTaskReport): Promise<IProjectTaskReport | null> {
        return super.createLocal(context, new ProjectTaskReport(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IProjectTaskReport> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.project && entity.project !== '') {
            const s = await ___ibz___.gs.getProjectService();
            const data = await s.getLocal2(context, entity.project);
            if (data) {
                entity.projectname = data.name;
                entity.project = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IProjectTaskReport): Promise<IProjectTaskReport> {
        return super.updateLocal(context, new ProjectTaskReport(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IProjectTaskReport = {}): Promise<IProjectTaskReport> {
        if (_context.project && _context.project !== '') {
            const s = await ___ibz___.gs.getProjectService();
            const data = await s.getLocal2(_context, _context.project);
            if (data) {
                entity.projectname = data.name;
                entity.project = data.id;
            }
        }
        return new ProjectTaskReport(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTaskReportService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }

    protected getAssignedToMyTaskCond() {
        return this.condCache.get('assignedToMyTask');
    }

    protected getAssignedToMyTaskPcCond() {
        return this.condCache.get('assignedToMyTaskPc');
    }

    protected getBugTaskCond() {
        return this.condCache.get('bugTask');
    }

    protected getByModuleCond() {
        return this.condCache.get('byModule');
    }

    protected getChildDefaultCond() {
        return this.condCache.get('childDefault');
    }

    protected getChildDefaultMoreCond() {
        return this.condCache.get('childDefaultMore');
    }

    protected getChildTaskCond() {
        return this.condCache.get('childTask');
    }

    protected getChildTaskTreeCond() {
        return this.condCache.get('childTaskTree');
    }

    protected getCurFinishTaskCond() {
        return this.condCache.get('curFinishTask');
    }

    protected getCurProjectTaskQueryCond() {
        return this.condCache.get('curProjectTaskQuery');
    }

    protected getDefaultCond() {
        return this.condCache.get('default');
    }

    protected getDefaultRowCond() {
        return this.condCache.get('defaultRow');
    }

    protected getESBulkCond() {
        return this.condCache.get('eSBulk');
    }

    protected getMyAgentTaskCond() {
        return this.condCache.get('myAgentTask');
    }

    protected getMyAllTaskCond() {
        return this.condCache.get('myAllTask');
    }

    protected getMyCompleteTaskCond() {
        return this.condCache.get('myCompleteTask');
    }

    protected getMyCompleteTaskMobDailyCond() {
        return this.condCache.get('myCompleteTaskMobDaily');
    }

    protected getMyCompleteTaskMobMonthlyCond() {
        return this.condCache.get('myCompleteTaskMobMonthly');
    }

    protected getMyCompleteTaskMonthlyZSCond() {
        return this.condCache.get('myCompleteTaskMonthlyZS');
    }

    protected getMyCompleteTaskZSCond() {
        return this.condCache.get('myCompleteTaskZS');
    }

    protected getMyCreateOrPartakeCond() {
        if (!this.condCache.has('myCreateOrPartake')) {
            const strCond: any[] = ['AND', ['OR', ['EQ', 'OPENEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'LASTEDITEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'CLOSEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'FINISHEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'CANCELEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}], ['EQ', 'ASSIGNEDTO',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}]]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('myCreateOrPartake', cond);
            }
        }
        return this.condCache.get('myCreateOrPartake');
    }

    protected getMyFavoritesCond() {
        return this.condCache.get('myFavorites');
    }

    protected getMyPlansTaskMobMonthlyCond() {
        return this.condCache.get('myPlansTaskMobMonthly');
    }

    protected getMyReProjectCond() {
        if (!this.condCache.has('myReProject')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('myReProject', cond);
            }
        }
        return this.condCache.get('myReProject');
    }

    protected getMyTomorrowPlanTaskCond() {
        return this.condCache.get('myTomorrowPlanTask');
    }

    protected getMyTomorrowPlanTaskMobDailyCond() {
        return this.condCache.get('myTomorrowPlanTaskMobDaily');
    }

    protected getNextWeekCompleteTaskMobZSCond() {
        return this.condCache.get('nextWeekCompleteTaskMobZS');
    }

    protected getNextWeekCompleteTaskZSCond() {
        return this.condCache.get('nextWeekCompleteTaskZS');
    }

    protected getNextWeekPlanCompleteTaskCond() {
        return this.condCache.get('nextWeekPlanCompleteTask');
    }

    protected getPlanTaskCond() {
        return this.condCache.get('planTask');
    }

    protected getProjectAppTaskCond() {
        return this.condCache.get('projectAppTask');
    }

    protected getProjectTaskCond() {
        return this.condCache.get('projectTask');
    }

    protected getRootTaskCond() {
        return this.condCache.get('rootTask');
    }

    protected getSimpleCond() {
        return this.condCache.get('simple');
    }

    protected getTaskLinkPlanCond() {
        if (!this.condCache.has('taskLinkPlan')) {
            const strCond: any[] = ['AND', ['OR', ['NOTEQ', 'PLAN',{ type: 'WEBCONTEXT', value: 'productplan'}], ['ISNULL', 'PLAN','']]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('taskLinkPlan', cond);
            }
        }
        return this.condCache.get('taskLinkPlan');
    }

    protected getThisMonthCompleteTaskChoiceCond() {
        return this.condCache.get('thisMonthCompleteTaskChoice');
    }

    protected getThisWeekCompleteTaskCond() {
        return this.condCache.get('thisWeekCompleteTask');
    }

    protected getThisWeekCompleteTaskChoiceCond() {
        return this.condCache.get('thisWeekCompleteTaskChoice');
    }

    protected getThisWeekCompleteTaskMobZSCond() {
        return this.condCache.get('thisWeekCompleteTaskMobZS');
    }

    protected getThisWeekCompleteTaskZSCond() {
        return this.condCache.get('thisWeekCompleteTaskZS');
    }

    protected getTodoListTaskCond() {
        return this.condCache.get('todoListTask');
    }

    protected getTypeGroupCond() {
        return this.condCache.get('typeGroup');
    }

    protected getTypeGroupPlanCond() {
        return this.condCache.get('typeGroupPlan');
    }

    protected getViewCond() {
        return this.condCache.get('view');
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTaskReportService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/projecttaskreports/fetchdefault`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * GetUserConcat
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectTaskReportService
     */
    async GetUserConcat(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const appLogic = new GetUserConcatLogic(_context, _data);
        _data = await appLogic.onExecute();
        return new HttpResponse(_data);
    }
}
