import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ITask, Task } from '../../entities';
import keys from '../../entities/task/task-keys';
import { clone, mergeDeepLeft } from 'ramda';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';
import { GetUserConcatLogic } from '../../logic/entity/task/get-user-concat/get-user-concat-logic';

/**
 * 任务服务对象基类
 *
 * @export
 * @class TaskBaseService
 * @extends {EntityBaseService}
 */
export class TaskBaseService extends EntityBaseService<ITask> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'Task';
    protected APPDENAMEPLURAL = 'Tasks';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['id','name',];
    protected selectContextParam = {
        project: 'project',
    };

    newEntity(data: ITask): Task {
        return new Task(data);
    }

    async addLocal(context: IContext, entity: ITask): Promise<ITask | null> {
        return this.cache.add(context, new Task(entity) as any);
    }

    async createLocal(context: IContext, entity: ITask): Promise<ITask | null> {
        return super.createLocal(context, new Task(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ITask> {
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

    async updateLocal(context: IContext, entity: ITask): Promise<ITask> {
        return super.updateLocal(context, new Task(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ITask = {}): Promise<ITask> {
        if (_context.project && _context.project !== '') {
            const s = await ___ibz___.gs.getProjectService();
            const data = await s.getLocal2(_context, _context.project);
            if (data) {
                entity.projectname = data.name;
                entity.project = data.id;
            }
        }
        return new Task(entity);
    }

    protected async fillMinor(_context: IContext, _data: ITask): Promise<any> {
        if (_data.ibztaskteams) {
            await this.setMinorLocal('IBZTaskTeam', _context, _data.ibztaskteams);
            delete _data.ibztaskteams;
        }
        if (_data.ibztaskestimates) {
            await this.setMinorLocal('IBZTaskEstimate', _context, _data.ibztaskestimates);
            delete _data.ibztaskestimates;
        }
        this.addLocal(_context, _data);
        return _data;
    }

    protected async obtainMinor(_context: IContext, _data: ITask = new Task()): Promise<ITask> {
        const res = await this.GetTemp(_context, _data);
        if (res.ok) {
            _data = mergeDeepLeft(_data, this.filterEntityData(res.data)) as any;
        }
        const ibztaskteamsList = await this.getMinorLocal('IBZTaskTeam', _context, { root: _data.id });
        if (ibztaskteamsList?.length > 0) {
            _data.ibztaskteams = ibztaskteamsList;
        }
        const ibztaskestimatesList = await this.getMinorLocal('IBZTaskEstimate', _context, { task: _data.id });
        if (ibztaskestimatesList?.length > 0) {
            _data.ibztaskestimates = ibztaskestimatesList;
        }
        return _data;
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const oldData = clone(data);
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
            {
                let items: any[] = [];
                const s = await ___ibz___.gs.getIBZTaskTeamService();
                items = await s.selectLocal(context, { root: oldData.id });
                if (items) {
                    for (let i = 0; i < items.length; i++) {
                        const item = items[i];
                        const res = await s.DeepCopyTemp({ ...context, task: entity.srfkey }, item);
                        if (!res.ok) {
                            throw new Error(
                                `「Task(${oldData.srfkey})」关联实体「IBZTaskTeam(${item.srfkey})」拷贝失败。`,
                            );
                        }
                    }
                }
            }
            {
                let items: any[] = [];
                const s = await ___ibz___.gs.getIBZTaskEstimateService();
                items = await s.selectLocal(context, { task: oldData.id });
                if (items) {
                    for (let i = 0; i < items.length; i++) {
                        const item = items[i];
                        const res = await s.DeepCopyTemp({ ...context, task: entity.srfkey }, item);
                        if (!res.ok) {
                            throw new Error(
                                `「Task(${oldData.srfkey})」关联实体「IBZTaskEstimate(${item.srfkey})」拷贝失败。`,
                            );
                        }
                    }
                }
            }
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
     * GetUserConcat
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async GetUserConcat(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const appLogic = new GetUserConcatLogic(_context, _data);
        _data = await appLogic.onExecute();
        return new HttpResponse(_data);
    }
}
