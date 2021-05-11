import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ISubTask, SubTask } from '../../entities';
import keys from '../../entities/sub-task/sub-task-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';
import { GetUserConcatLogic } from '../../logic/entity/sub-task/get-user-concat/get-user-concat-logic';

/**
 * 任务服务对象基类
 *
 * @export
 * @class SubTaskBaseService
 * @extends {EntityBaseService}
 */
export class SubTaskBaseService extends EntityBaseService<ISubTask> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'SubTask';
    protected APPDENAMEPLURAL = 'SubTasks';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name','tasksn',];
    protected selectContextParam = {
        task: 'parent',
    };

    newEntity(data: ISubTask): SubTask {
        return new SubTask(data);
    }

    async addLocal(context: IContext, entity: ISubTask): Promise<ISubTask | null> {
        return this.cache.add(context, new SubTask(entity) as any);
    }

    async createLocal(context: IContext, entity: ISubTask): Promise<ISubTask | null> {
        return super.createLocal(context, new SubTask(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ISubTask> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.parent && entity.parent !== '') {
            const s = await ___ibz___.gs.getTaskService();
            const data = await s.getLocal2(context, entity.parent);
            if (data) {
                entity.parentname = data.name;
                entity.parent = data.tasksn;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: ISubTask): Promise<ISubTask> {
        return super.updateLocal(context, new SubTask(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ISubTask = {}): Promise<ISubTask> {
        if (_context.task && _context.task !== '') {
            const s = await ___ibz___.gs.getTaskService();
            const data = await s.getLocal2(_context, _context.task);
            if (data) {
                entity.parentname = data.name;
                entity.parent = data.tasksn;
            }
        }
        return new SubTask(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
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
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.story && _context.task && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.productplan && _context.task && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/getdraft`, _data);
            return res;
        }
        if (_context.project && _context.task && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/tasks/${_context.task}/subtasks/getdraft`, _data);
            return res;
        }
        if (_context.story && _context.task && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/stories/${_context.story}/tasks/${_context.task}/subtasks/getdraft`, _data);
            return res;
        }
        if (_context.productplan && _context.task && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/getdraft`, _data);
            return res;
        }
        if (_context.projectmodule && _context.task && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/getdraft`, _data);
            return res;
        }
        if (_context.task && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/tasks/${_context.task}/subtasks/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/subtasks/getdraft`, _data);
        return res;
    }
    /**
     * FetchChildTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async FetchChildTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchchildtask`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchchildtask`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchchildtask`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/fetchchildtask`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchchildtask`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchchildtask`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchchildtask`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/subtasks/fetchchildtask`, _data);
        }
        return this.http.post(`/subtasks/fetchchildtask`, _data);
    }
    /**
     * GetUserConcat
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async GetUserConcat(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        const appLogic = new GetUserConcatLogic(_context, _data);
        _data = await appLogic.onExecute();
        return new HttpResponse(_data);
    }
}
