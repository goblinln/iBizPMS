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
    protected quickSearchFields = ['id','name',];
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
                entity.parent = data.id;
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
                entity.parent = data.id;
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
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && _context.subtask) {
            return this.http.get(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/select`);
        }
        if (_context.product && _context.story && _context.task && _context.subtask) {
            return this.http.get(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/select`);
        }
        if (_context.product && _context.productplan && _context.task && _context.subtask) {
            return this.http.get(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/select`);
        }
        if (_context.project && _context.task && _context.subtask) {
            return this.http.get(`/projects/${_context.project}/tasks/${_context.task}/subtasks/${_context.subtask}/select`);
        }
        if (_context.story && _context.task && _context.subtask) {
            return this.http.get(`/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/select`);
        }
        if (_context.productplan && _context.task && _context.subtask) {
            return this.http.get(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/select`);
        }
        if (_context.projectmodule && _context.task && _context.subtask) {
            return this.http.get(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/select`);
        }
        if (_context.task && _context.subtask) {
            return this.http.get(`/tasks/${_context.task}/subtasks/${_context.subtask}/select`);
        }
        return this.http.get(`/subtasks/${_context.subtask}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks`, _data);
        }
        if (_context.project && _context.task && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks`, _data);
        }
        if (_context.story && _context.task && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks`, _data);
        }
        if (_context.productplan && _context.task && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks`, _data);
        }
        if (_context.task && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/tasks/${_context.task}/subtasks`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/subtasks`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}`, _data);
        }
        if (_context.product && _context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}`, _data);
        }
        if (_context.product && _context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}`, _data);
        }
        if (_context.project && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/tasks/${_context.task}/subtasks/${_context.subtask}`, _data);
        }
        if (_context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}`, _data);
        }
        if (_context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}`, _data);
        }
        if (_context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}`, _data);
        }
        if (_context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/tasks/${_context.task}/subtasks/${_context.subtask}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/subtasks/${_context.subtask}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && _context.subtask) {
            return this.http.delete(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}`);
        }
        if (_context.product && _context.story && _context.task && _context.subtask) {
            return this.http.delete(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}`);
        }
        if (_context.product && _context.productplan && _context.task && _context.subtask) {
            return this.http.delete(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}`);
        }
        if (_context.project && _context.task && _context.subtask) {
            return this.http.delete(`/projects/${_context.project}/tasks/${_context.task}/subtasks/${_context.subtask}`);
        }
        if (_context.story && _context.task && _context.subtask) {
            return this.http.delete(`/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}`);
        }
        if (_context.productplan && _context.task && _context.subtask) {
            return this.http.delete(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}`);
        }
        if (_context.projectmodule && _context.task && _context.subtask) {
            return this.http.delete(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}`);
        }
        if (_context.task && _context.subtask) {
            return this.http.delete(`/tasks/${_context.task}/subtasks/${_context.subtask}`);
        }
        return this.http.delete(`/subtasks/${_context.subtask}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && _context.subtask) {
            const res = await this.http.get(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}`);
            return res;
        }
        if (_context.product && _context.story && _context.task && _context.subtask) {
            const res = await this.http.get(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}`);
            return res;
        }
        if (_context.product && _context.productplan && _context.task && _context.subtask) {
            const res = await this.http.get(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}`);
            return res;
        }
        if (_context.project && _context.task && _context.subtask) {
            const res = await this.http.get(`/projects/${_context.project}/tasks/${_context.task}/subtasks/${_context.subtask}`);
            return res;
        }
        if (_context.story && _context.task && _context.subtask) {
            const res = await this.http.get(`/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}`);
            return res;
        }
        if (_context.productplan && _context.task && _context.subtask) {
            const res = await this.http.get(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}`);
            return res;
        }
        if (_context.projectmodule && _context.task && _context.subtask) {
            const res = await this.http.get(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}`);
            return res;
        }
        if (_context.task && _context.subtask) {
            const res = await this.http.get(`/tasks/${_context.task}/subtasks/${_context.subtask}`);
            return res;
        }
        const res = await this.http.get(`/subtasks/${_context.subtask}`);
        return res;
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
     * Activate
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async Activate(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/activate`, _data);
        }
        if (_context.product && _context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/activate`, _data);
        }
        if (_context.product && _context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/activate`, _data);
        }
        if (_context.project && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/${_context.subtask}/activate`, _data);
        }
        if (_context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/activate`, _data);
        }
        if (_context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/activate`, _data);
        }
        if (_context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/activate`, _data);
        }
        if (_context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/subtasks/${_context.subtask}/activate`, _data);
        }
        return this.http.post(`/subtasks/${_context.subtask}/activate`, _data);
    }
    /**
     * AssignTo
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async AssignTo(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/assignto`, _data);
        }
        if (_context.product && _context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/assignto`, _data);
        }
        if (_context.product && _context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/assignto`, _data);
        }
        if (_context.project && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/${_context.subtask}/assignto`, _data);
        }
        if (_context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/assignto`, _data);
        }
        if (_context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/assignto`, _data);
        }
        if (_context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/assignto`, _data);
        }
        if (_context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/subtasks/${_context.subtask}/assignto`, _data);
        }
        return this.http.post(`/subtasks/${_context.subtask}/assignto`, _data);
    }
    /**
     * Cancel
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async Cancel(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/cancel`, _data);
        }
        if (_context.product && _context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/cancel`, _data);
        }
        if (_context.product && _context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/cancel`, _data);
        }
        if (_context.project && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/${_context.subtask}/cancel`, _data);
        }
        if (_context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/cancel`, _data);
        }
        if (_context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/cancel`, _data);
        }
        if (_context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/cancel`, _data);
        }
        if (_context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/subtasks/${_context.subtask}/cancel`, _data);
        }
        return this.http.post(`/subtasks/${_context.subtask}/cancel`, _data);
    }
    /**
     * Close
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async Close(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/close`, _data);
        }
        if (_context.product && _context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/close`, _data);
        }
        if (_context.product && _context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/close`, _data);
        }
        if (_context.project && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/${_context.subtask}/close`, _data);
        }
        if (_context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/close`, _data);
        }
        if (_context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/close`, _data);
        }
        if (_context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/close`, _data);
        }
        if (_context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/subtasks/${_context.subtask}/close`, _data);
        }
        return this.http.post(`/subtasks/${_context.subtask}/close`, _data);
    }
    /**
     * ConfirmStoryChange
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async ConfirmStoryChange(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/confirmstorychange`, _data);
        }
        if (_context.product && _context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/confirmstorychange`, _data);
        }
        if (_context.product && _context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/confirmstorychange`, _data);
        }
        if (_context.project && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/${_context.subtask}/confirmstorychange`, _data);
        }
        if (_context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/confirmstorychange`, _data);
        }
        if (_context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/confirmstorychange`, _data);
        }
        if (_context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/confirmstorychange`, _data);
        }
        if (_context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/subtasks/${_context.subtask}/confirmstorychange`, _data);
        }
        return this.http.post(`/subtasks/${_context.subtask}/confirmstorychange`, _data);
    }
    /**
     * CreateCycleTasks
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async CreateCycleTasks(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/createcycletasks`, _data);
        }
        if (_context.product && _context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/createcycletasks`, _data);
        }
        if (_context.product && _context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/createcycletasks`, _data);
        }
        if (_context.project && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/${_context.subtask}/createcycletasks`, _data);
        }
        if (_context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/createcycletasks`, _data);
        }
        if (_context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/createcycletasks`, _data);
        }
        if (_context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/createcycletasks`, _data);
        }
        if (_context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/subtasks/${_context.subtask}/createcycletasks`, _data);
        }
        return this.http.post(`/subtasks/${_context.subtask}/createcycletasks`, _data);
    }
    /**
     * DeleteEstimate
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async DeleteEstimate(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/deleteestimate`, _data);
        }
        if (_context.product && _context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/deleteestimate`, _data);
        }
        if (_context.product && _context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/deleteestimate`, _data);
        }
        if (_context.project && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/${_context.subtask}/deleteestimate`, _data);
        }
        if (_context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/deleteestimate`, _data);
        }
        if (_context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/deleteestimate`, _data);
        }
        if (_context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/deleteestimate`, _data);
        }
        if (_context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/subtasks/${_context.subtask}/deleteestimate`, _data);
        }
        return this.http.post(`/subtasks/${_context.subtask}/deleteestimate`, _data);
    }
    /**
     * EditEstimate
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async EditEstimate(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/editestimate`, _data);
        }
        if (_context.product && _context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/editestimate`, _data);
        }
        if (_context.product && _context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/editestimate`, _data);
        }
        if (_context.project && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/${_context.subtask}/editestimate`, _data);
        }
        if (_context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/editestimate`, _data);
        }
        if (_context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/editestimate`, _data);
        }
        if (_context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/editestimate`, _data);
        }
        if (_context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/subtasks/${_context.subtask}/editestimate`, _data);
        }
        return this.http.post(`/subtasks/${_context.subtask}/editestimate`, _data);
    }
    /**
     * Finish
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async Finish(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/finish`, _data);
        }
        if (_context.product && _context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/finish`, _data);
        }
        if (_context.product && _context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/finish`, _data);
        }
        if (_context.project && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/${_context.subtask}/finish`, _data);
        }
        if (_context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/finish`, _data);
        }
        if (_context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/finish`, _data);
        }
        if (_context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/finish`, _data);
        }
        if (_context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/subtasks/${_context.subtask}/finish`, _data);
        }
        return this.http.post(`/subtasks/${_context.subtask}/finish`, _data);
    }
    /**
     * GetNextTeamUser
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async GetNextTeamUser(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/getnextteamuser`, _data);
        }
        if (_context.product && _context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/getnextteamuser`, _data);
        }
        if (_context.product && _context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/getnextteamuser`, _data);
        }
        if (_context.project && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/tasks/${_context.task}/subtasks/${_context.subtask}/getnextteamuser`, _data);
        }
        if (_context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/getnextteamuser`, _data);
        }
        if (_context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/getnextteamuser`, _data);
        }
        if (_context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/getnextteamuser`, _data);
        }
        if (_context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/tasks/${_context.task}/subtasks/${_context.subtask}/getnextteamuser`, _data);
        }
        return this.http.put(`/subtasks/${_context.subtask}/getnextteamuser`, _data);
    }
    /**
     * GetTeamUserLeftActivity
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async GetTeamUserLeftActivity(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/getteamuserleftactivity`, _data);
        }
        if (_context.product && _context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/getteamuserleftactivity`, _data);
        }
        if (_context.product && _context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/getteamuserleftactivity`, _data);
        }
        if (_context.project && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/tasks/${_context.task}/subtasks/${_context.subtask}/getteamuserleftactivity`, _data);
        }
        if (_context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/getteamuserleftactivity`, _data);
        }
        if (_context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/getteamuserleftactivity`, _data);
        }
        if (_context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/getteamuserleftactivity`, _data);
        }
        if (_context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/tasks/${_context.task}/subtasks/${_context.subtask}/getteamuserleftactivity`, _data);
        }
        return this.http.put(`/subtasks/${_context.subtask}/getteamuserleftactivity`, _data);
    }
    /**
     * GetTeamUserLeftStart
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async GetTeamUserLeftStart(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/getteamuserleftstart`, _data);
        }
        if (_context.product && _context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/getteamuserleftstart`, _data);
        }
        if (_context.product && _context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/getteamuserleftstart`, _data);
        }
        if (_context.project && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/tasks/${_context.task}/subtasks/${_context.subtask}/getteamuserleftstart`, _data);
        }
        if (_context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/getteamuserleftstart`, _data);
        }
        if (_context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/getteamuserleftstart`, _data);
        }
        if (_context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/getteamuserleftstart`, _data);
        }
        if (_context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/tasks/${_context.task}/subtasks/${_context.subtask}/getteamuserleftstart`, _data);
        }
        return this.http.put(`/subtasks/${_context.subtask}/getteamuserleftstart`, _data);
    }
    /**
     * GetUsernames
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async GetUsernames(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/getusernames`, _data);
        }
        if (_context.product && _context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/getusernames`, _data);
        }
        if (_context.product && _context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/getusernames`, _data);
        }
        if (_context.project && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/tasks/${_context.task}/subtasks/${_context.subtask}/getusernames`, _data);
        }
        if (_context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/getusernames`, _data);
        }
        if (_context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/getusernames`, _data);
        }
        if (_context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/getusernames`, _data);
        }
        if (_context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/tasks/${_context.task}/subtasks/${_context.subtask}/getusernames`, _data);
        }
        return this.http.put(`/subtasks/${_context.subtask}/getusernames`, _data);
    }
    /**
     * LinkPlan
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async LinkPlan(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/linkplan`, _data);
        }
        if (_context.product && _context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/linkplan`, _data);
        }
        if (_context.product && _context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/linkplan`, _data);
        }
        if (_context.project && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/${_context.subtask}/linkplan`, _data);
        }
        if (_context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/linkplan`, _data);
        }
        if (_context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/linkplan`, _data);
        }
        if (_context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/linkplan`, _data);
        }
        if (_context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/subtasks/${_context.subtask}/linkplan`, _data);
        }
        return this.http.post(`/subtasks/${_context.subtask}/linkplan`, _data);
    }
    /**
     * OtherUpdate
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async OtherUpdate(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/otherupdate`, _data);
        }
        if (_context.product && _context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/otherupdate`, _data);
        }
        if (_context.product && _context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/otherupdate`, _data);
        }
        if (_context.project && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/tasks/${_context.task}/subtasks/${_context.subtask}/otherupdate`, _data);
        }
        if (_context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/otherupdate`, _data);
        }
        if (_context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/otherupdate`, _data);
        }
        if (_context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/otherupdate`, _data);
        }
        if (_context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/tasks/${_context.task}/subtasks/${_context.subtask}/otherupdate`, _data);
        }
        return this.http.put(`/subtasks/${_context.subtask}/otherupdate`, _data);
    }
    /**
     * Pause
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async Pause(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/pause`, _data);
        }
        if (_context.product && _context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/pause`, _data);
        }
        if (_context.product && _context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/pause`, _data);
        }
        if (_context.project && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/${_context.subtask}/pause`, _data);
        }
        if (_context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/pause`, _data);
        }
        if (_context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/pause`, _data);
        }
        if (_context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/pause`, _data);
        }
        if (_context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/subtasks/${_context.subtask}/pause`, _data);
        }
        return this.http.post(`/subtasks/${_context.subtask}/pause`, _data);
    }
    /**
     * RecordEstimate
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async RecordEstimate(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/recordestimate`, _data);
        }
        if (_context.product && _context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/recordestimate`, _data);
        }
        if (_context.product && _context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/recordestimate`, _data);
        }
        if (_context.project && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/${_context.subtask}/recordestimate`, _data);
        }
        if (_context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/recordestimate`, _data);
        }
        if (_context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/recordestimate`, _data);
        }
        if (_context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/recordestimate`, _data);
        }
        if (_context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/subtasks/${_context.subtask}/recordestimate`, _data);
        }
        return this.http.post(`/subtasks/${_context.subtask}/recordestimate`, _data);
    }
    /**
     * Restart
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async Restart(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/restart`, _data);
        }
        if (_context.product && _context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/restart`, _data);
        }
        if (_context.product && _context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/restart`, _data);
        }
        if (_context.project && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/${_context.subtask}/restart`, _data);
        }
        if (_context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/restart`, _data);
        }
        if (_context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/restart`, _data);
        }
        if (_context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/restart`, _data);
        }
        if (_context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/subtasks/${_context.subtask}/restart`, _data);
        }
        return this.http.post(`/subtasks/${_context.subtask}/restart`, _data);
    }
    /**
     * SendMessage
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async SendMessage(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/sendmessage`, _data);
        }
        if (_context.product && _context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/sendmessage`, _data);
        }
        if (_context.product && _context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/sendmessage`, _data);
        }
        if (_context.project && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/${_context.subtask}/sendmessage`, _data);
        }
        if (_context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/sendmessage`, _data);
        }
        if (_context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/sendmessage`, _data);
        }
        if (_context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/sendmessage`, _data);
        }
        if (_context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/subtasks/${_context.subtask}/sendmessage`, _data);
        }
        return this.http.post(`/subtasks/${_context.subtask}/sendmessage`, _data);
    }
    /**
     * SendMsgPreProcess
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async SendMsgPreProcess(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/sendmsgpreprocess`, _data);
        }
        if (_context.product && _context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/sendmsgpreprocess`, _data);
        }
        if (_context.product && _context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/sendmsgpreprocess`, _data);
        }
        if (_context.project && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/${_context.subtask}/sendmsgpreprocess`, _data);
        }
        if (_context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/sendmsgpreprocess`, _data);
        }
        if (_context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/sendmsgpreprocess`, _data);
        }
        if (_context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/sendmsgpreprocess`, _data);
        }
        if (_context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/subtasks/${_context.subtask}/sendmsgpreprocess`, _data);
        }
        return this.http.post(`/subtasks/${_context.subtask}/sendmsgpreprocess`, _data);
    }
    /**
     * Start
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async Start(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/start`, _data);
        }
        if (_context.product && _context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/start`, _data);
        }
        if (_context.product && _context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/start`, _data);
        }
        if (_context.project && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/${_context.subtask}/start`, _data);
        }
        if (_context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/start`, _data);
        }
        if (_context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/start`, _data);
        }
        if (_context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/start`, _data);
        }
        if (_context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/subtasks/${_context.subtask}/start`, _data);
        }
        return this.http.post(`/subtasks/${_context.subtask}/start`, _data);
    }
    /**
     * TaskFavorites
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async TaskFavorites(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/taskfavorites`, _data);
        }
        if (_context.product && _context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/taskfavorites`, _data);
        }
        if (_context.product && _context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/taskfavorites`, _data);
        }
        if (_context.project && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/${_context.subtask}/taskfavorites`, _data);
        }
        if (_context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/taskfavorites`, _data);
        }
        if (_context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/taskfavorites`, _data);
        }
        if (_context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/taskfavorites`, _data);
        }
        if (_context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/subtasks/${_context.subtask}/taskfavorites`, _data);
        }
        return this.http.post(`/subtasks/${_context.subtask}/taskfavorites`, _data);
    }
    /**
     * TaskForward
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async TaskForward(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/taskforward`, _data);
        }
        if (_context.product && _context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/taskforward`, _data);
        }
        if (_context.product && _context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/taskforward`, _data);
        }
        if (_context.project && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/${_context.subtask}/taskforward`, _data);
        }
        if (_context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/taskforward`, _data);
        }
        if (_context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/taskforward`, _data);
        }
        if (_context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/taskforward`, _data);
        }
        if (_context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/subtasks/${_context.subtask}/taskforward`, _data);
        }
        return this.http.post(`/subtasks/${_context.subtask}/taskforward`, _data);
    }
    /**
     * TaskNFavorites
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async TaskNFavorites(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/tasknfavorites`, _data);
        }
        if (_context.product && _context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/tasknfavorites`, _data);
        }
        if (_context.product && _context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/tasknfavorites`, _data);
        }
        if (_context.project && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/${_context.subtask}/tasknfavorites`, _data);
        }
        if (_context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/tasknfavorites`, _data);
        }
        if (_context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/tasknfavorites`, _data);
        }
        if (_context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/tasknfavorites`, _data);
        }
        if (_context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/subtasks/${_context.subtask}/tasknfavorites`, _data);
        }
        return this.http.post(`/subtasks/${_context.subtask}/tasknfavorites`, _data);
    }
    /**
     * UpdateStoryVersion
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async UpdateStoryVersion(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/updatestoryversion`, _data);
        }
        if (_context.product && _context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/updatestoryversion`, _data);
        }
        if (_context.product && _context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/updatestoryversion`, _data);
        }
        if (_context.project && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/tasks/${_context.task}/subtasks/${_context.subtask}/updatestoryversion`, _data);
        }
        if (_context.story && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/stories/${_context.story}/tasks/${_context.task}/subtasks/${_context.subtask}/updatestoryversion`, _data);
        }
        if (_context.productplan && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/${_context.subtask}/updatestoryversion`, _data);
        }
        if (_context.projectmodule && _context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/${_context.subtask}/updatestoryversion`, _data);
        }
        if (_context.task && _context.subtask) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/tasks/${_context.task}/subtasks/${_context.subtask}/updatestoryversion`, _data);
        }
        return this.http.put(`/subtasks/${_context.subtask}/updatestoryversion`, _data);
    }
    /**
     * FetchAssignedToMyTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async FetchAssignedToMyTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchassignedtomytask`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchassignedtomytask`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchassignedtomytask`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/fetchassignedtomytask`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchassignedtomytask`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchassignedtomytask`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchassignedtomytask`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/subtasks/fetchassignedtomytask`, _data);
        }
        return this.http.post(`/subtasks/fetchassignedtomytask`, _data);
    }
    /**
     * FetchAssignedToMyTaskPc
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async FetchAssignedToMyTaskPc(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchassignedtomytaskpc`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchassignedtomytaskpc`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchassignedtomytaskpc`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/fetchassignedtomytaskpc`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchassignedtomytaskpc`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchassignedtomytaskpc`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchassignedtomytaskpc`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/subtasks/fetchassignedtomytaskpc`, _data);
        }
        return this.http.post(`/subtasks/fetchassignedtomytaskpc`, _data);
    }
    /**
     * FetchBugTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async FetchBugTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchbugtask`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchbugtask`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchbugtask`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/fetchbugtask`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchbugtask`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchbugtask`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchbugtask`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/subtasks/fetchbugtask`, _data);
        }
        return this.http.post(`/subtasks/fetchbugtask`, _data);
    }
    /**
     * FetchByModule
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async FetchByModule(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchbymodule`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchbymodule`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchbymodule`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/fetchbymodule`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchbymodule`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchbymodule`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchbymodule`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/subtasks/fetchbymodule`, _data);
        }
        return this.http.post(`/subtasks/fetchbymodule`, _data);
    }
    /**
     * FetchChildDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async FetchChildDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchchilddefault`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchchilddefault`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchchilddefault`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/fetchchilddefault`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchchilddefault`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchchilddefault`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchchilddefault`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/subtasks/fetchchilddefault`, _data);
        }
        return this.http.post(`/subtasks/fetchchilddefault`, _data);
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
     * FetchChildTaskTree
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async FetchChildTaskTree(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchchildtasktree`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchchildtasktree`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchchildtasktree`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/fetchchildtasktree`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchchildtasktree`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchchildtasktree`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchchildtasktree`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/subtasks/fetchchildtasktree`, _data);
        }
        return this.http.post(`/subtasks/fetchchildtasktree`, _data);
    }
    /**
     * FetchCurFinishTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async FetchCurFinishTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchcurfinishtask`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchcurfinishtask`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchcurfinishtask`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/fetchcurfinishtask`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchcurfinishtask`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchcurfinishtask`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchcurfinishtask`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/subtasks/fetchcurfinishtask`, _data);
        }
        return this.http.post(`/subtasks/fetchcurfinishtask`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchdefault`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchdefault`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchdefault`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/fetchdefault`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchdefault`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchdefault`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchdefault`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/subtasks/fetchdefault`, _data);
        }
        return this.http.post(`/subtasks/fetchdefault`, _data);
    }
    /**
     * FetchDefaultRow
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async FetchDefaultRow(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchdefaultrow`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchdefaultrow`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchdefaultrow`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/fetchdefaultrow`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchdefaultrow`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchdefaultrow`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchdefaultrow`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/subtasks/fetchdefaultrow`, _data);
        }
        return this.http.post(`/subtasks/fetchdefaultrow`, _data);
    }
    /**
     * FetchESBulk
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async FetchESBulk(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchesbulk`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchesbulk`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchesbulk`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/fetchesbulk`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchesbulk`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchesbulk`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchesbulk`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/subtasks/fetchesbulk`, _data);
        }
        return this.http.post(`/subtasks/fetchesbulk`, _data);
    }
    /**
     * FetchMyAgentTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async FetchMyAgentTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchmyagenttask`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchmyagenttask`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchmyagenttask`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/fetchmyagenttask`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchmyagenttask`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchmyagenttask`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchmyagenttask`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/subtasks/fetchmyagenttask`, _data);
        }
        return this.http.post(`/subtasks/fetchmyagenttask`, _data);
    }
    /**
     * FetchMyAllTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async FetchMyAllTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchmyalltask`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchmyalltask`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchmyalltask`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/fetchmyalltask`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchmyalltask`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchmyalltask`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchmyalltask`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/subtasks/fetchmyalltask`, _data);
        }
        return this.http.post(`/subtasks/fetchmyalltask`, _data);
    }
    /**
     * FetchMyCompleteTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async FetchMyCompleteTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchmycompletetask`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchmycompletetask`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchmycompletetask`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/fetchmycompletetask`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchmycompletetask`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchmycompletetask`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchmycompletetask`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/subtasks/fetchmycompletetask`, _data);
        }
        return this.http.post(`/subtasks/fetchmycompletetask`, _data);
    }
    /**
     * FetchMyCompleteTaskMobDaily
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async FetchMyCompleteTaskMobDaily(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchmycompletetaskmobdaily`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchmycompletetaskmobdaily`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchmycompletetaskmobdaily`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/fetchmycompletetaskmobdaily`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchmycompletetaskmobdaily`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchmycompletetaskmobdaily`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchmycompletetaskmobdaily`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/subtasks/fetchmycompletetaskmobdaily`, _data);
        }
        return this.http.post(`/subtasks/fetchmycompletetaskmobdaily`, _data);
    }
    /**
     * FetchMyCompleteTaskMobMonthly
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async FetchMyCompleteTaskMobMonthly(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchmycompletetaskmobmonthly`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchmycompletetaskmobmonthly`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchmycompletetaskmobmonthly`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/fetchmycompletetaskmobmonthly`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchmycompletetaskmobmonthly`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchmycompletetaskmobmonthly`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchmycompletetaskmobmonthly`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/subtasks/fetchmycompletetaskmobmonthly`, _data);
        }
        return this.http.post(`/subtasks/fetchmycompletetaskmobmonthly`, _data);
    }
    /**
     * FetchMyCompleteTaskMonthlyZS
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async FetchMyCompleteTaskMonthlyZS(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchmycompletetaskmonthlyzs`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchmycompletetaskmonthlyzs`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchmycompletetaskmonthlyzs`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/fetchmycompletetaskmonthlyzs`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchmycompletetaskmonthlyzs`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchmycompletetaskmonthlyzs`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchmycompletetaskmonthlyzs`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/subtasks/fetchmycompletetaskmonthlyzs`, _data);
        }
        return this.http.post(`/subtasks/fetchmycompletetaskmonthlyzs`, _data);
    }
    /**
     * FetchMyCompleteTaskZS
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async FetchMyCompleteTaskZS(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchmycompletetaskzs`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchmycompletetaskzs`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchmycompletetaskzs`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/fetchmycompletetaskzs`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchmycompletetaskzs`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchmycompletetaskzs`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchmycompletetaskzs`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/subtasks/fetchmycompletetaskzs`, _data);
        }
        return this.http.post(`/subtasks/fetchmycompletetaskzs`, _data);
    }
    /**
     * FetchMyFavorites
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async FetchMyFavorites(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchmyfavorites`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchmyfavorites`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchmyfavorites`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/fetchmyfavorites`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchmyfavorites`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchmyfavorites`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchmyfavorites`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/subtasks/fetchmyfavorites`, _data);
        }
        return this.http.post(`/subtasks/fetchmyfavorites`, _data);
    }
    /**
     * FetchMyPlansTaskMobMonthly
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async FetchMyPlansTaskMobMonthly(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchmyplanstaskmobmonthly`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchmyplanstaskmobmonthly`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchmyplanstaskmobmonthly`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/fetchmyplanstaskmobmonthly`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchmyplanstaskmobmonthly`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchmyplanstaskmobmonthly`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchmyplanstaskmobmonthly`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/subtasks/fetchmyplanstaskmobmonthly`, _data);
        }
        return this.http.post(`/subtasks/fetchmyplanstaskmobmonthly`, _data);
    }
    /**
     * FetchMyTomorrowPlanTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async FetchMyTomorrowPlanTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchmytomorrowplantask`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchmytomorrowplantask`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchmytomorrowplantask`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/fetchmytomorrowplantask`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchmytomorrowplantask`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchmytomorrowplantask`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchmytomorrowplantask`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/subtasks/fetchmytomorrowplantask`, _data);
        }
        return this.http.post(`/subtasks/fetchmytomorrowplantask`, _data);
    }
    /**
     * FetchMyTomorrowPlanTaskMobDaily
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async FetchMyTomorrowPlanTaskMobDaily(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchmytomorrowplantaskmobdaily`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchmytomorrowplantaskmobdaily`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchmytomorrowplantaskmobdaily`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/fetchmytomorrowplantaskmobdaily`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchmytomorrowplantaskmobdaily`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchmytomorrowplantaskmobdaily`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchmytomorrowplantaskmobdaily`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/subtasks/fetchmytomorrowplantaskmobdaily`, _data);
        }
        return this.http.post(`/subtasks/fetchmytomorrowplantaskmobdaily`, _data);
    }
    /**
     * FetchNextWeekCompleteTaskMobZS
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async FetchNextWeekCompleteTaskMobZS(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchnextweekcompletetaskmobzs`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchnextweekcompletetaskmobzs`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchnextweekcompletetaskmobzs`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/fetchnextweekcompletetaskmobzs`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchnextweekcompletetaskmobzs`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchnextweekcompletetaskmobzs`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchnextweekcompletetaskmobzs`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/subtasks/fetchnextweekcompletetaskmobzs`, _data);
        }
        return this.http.post(`/subtasks/fetchnextweekcompletetaskmobzs`, _data);
    }
    /**
     * FetchNextWeekCompleteTaskZS
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async FetchNextWeekCompleteTaskZS(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchnextweekcompletetaskzs`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchnextweekcompletetaskzs`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchnextweekcompletetaskzs`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/fetchnextweekcompletetaskzs`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchnextweekcompletetaskzs`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchnextweekcompletetaskzs`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchnextweekcompletetaskzs`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/subtasks/fetchnextweekcompletetaskzs`, _data);
        }
        return this.http.post(`/subtasks/fetchnextweekcompletetaskzs`, _data);
    }
    /**
     * FetchNextWeekPlanCompleteTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async FetchNextWeekPlanCompleteTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchnextweekplancompletetask`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchnextweekplancompletetask`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchnextweekplancompletetask`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/fetchnextweekplancompletetask`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchnextweekplancompletetask`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchnextweekplancompletetask`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchnextweekplancompletetask`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/subtasks/fetchnextweekplancompletetask`, _data);
        }
        return this.http.post(`/subtasks/fetchnextweekplancompletetask`, _data);
    }
    /**
     * FetchPlanTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async FetchPlanTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchplantask`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchplantask`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchplantask`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/fetchplantask`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchplantask`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchplantask`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchplantask`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/subtasks/fetchplantask`, _data);
        }
        return this.http.post(`/subtasks/fetchplantask`, _data);
    }
    /**
     * FetchProjectAppTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async FetchProjectAppTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchprojectapptask`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchprojectapptask`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchprojectapptask`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/fetchprojectapptask`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchprojectapptask`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchprojectapptask`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchprojectapptask`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/subtasks/fetchprojectapptask`, _data);
        }
        return this.http.post(`/subtasks/fetchprojectapptask`, _data);
    }
    /**
     * FetchProjectTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async FetchProjectTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchprojecttask`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchprojecttask`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchprojecttask`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/fetchprojecttask`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchprojecttask`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchprojecttask`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchprojecttask`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/subtasks/fetchprojecttask`, _data);
        }
        return this.http.post(`/subtasks/fetchprojecttask`, _data);
    }
    /**
     * FetchRootTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async FetchRootTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchroottask`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchroottask`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchroottask`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/fetchroottask`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchroottask`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchroottask`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchroottask`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/subtasks/fetchroottask`, _data);
        }
        return this.http.post(`/subtasks/fetchroottask`, _data);
    }
    /**
     * FetchTaskLinkPlan
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async FetchTaskLinkPlan(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchtasklinkplan`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchtasklinkplan`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchtasklinkplan`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/fetchtasklinkplan`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchtasklinkplan`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchtasklinkplan`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchtasklinkplan`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/subtasks/fetchtasklinkplan`, _data);
        }
        return this.http.post(`/subtasks/fetchtasklinkplan`, _data);
    }
    /**
     * FetchThisMonthCompleteTaskChoice
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async FetchThisMonthCompleteTaskChoice(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchthismonthcompletetaskchoice`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchthismonthcompletetaskchoice`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchthismonthcompletetaskchoice`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/fetchthismonthcompletetaskchoice`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchthismonthcompletetaskchoice`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchthismonthcompletetaskchoice`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchthismonthcompletetaskchoice`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/subtasks/fetchthismonthcompletetaskchoice`, _data);
        }
        return this.http.post(`/subtasks/fetchthismonthcompletetaskchoice`, _data);
    }
    /**
     * FetchThisWeekCompleteTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async FetchThisWeekCompleteTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchthisweekcompletetask`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchthisweekcompletetask`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchthisweekcompletetask`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/fetchthisweekcompletetask`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchthisweekcompletetask`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchthisweekcompletetask`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchthisweekcompletetask`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/subtasks/fetchthisweekcompletetask`, _data);
        }
        return this.http.post(`/subtasks/fetchthisweekcompletetask`, _data);
    }
    /**
     * FetchThisWeekCompleteTaskChoice
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async FetchThisWeekCompleteTaskChoice(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchthisweekcompletetaskchoice`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchthisweekcompletetaskchoice`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchthisweekcompletetaskchoice`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/fetchthisweekcompletetaskchoice`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchthisweekcompletetaskchoice`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchthisweekcompletetaskchoice`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchthisweekcompletetaskchoice`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/subtasks/fetchthisweekcompletetaskchoice`, _data);
        }
        return this.http.post(`/subtasks/fetchthisweekcompletetaskchoice`, _data);
    }
    /**
     * FetchThisWeekCompleteTaskMobZS
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async FetchThisWeekCompleteTaskMobZS(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchthisweekcompletetaskmobzs`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchthisweekcompletetaskmobzs`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchthisweekcompletetaskmobzs`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/fetchthisweekcompletetaskmobzs`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchthisweekcompletetaskmobzs`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchthisweekcompletetaskmobzs`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchthisweekcompletetaskmobzs`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/subtasks/fetchthisweekcompletetaskmobzs`, _data);
        }
        return this.http.post(`/subtasks/fetchthisweekcompletetaskmobzs`, _data);
    }
    /**
     * FetchThisWeekCompleteTaskZS
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async FetchThisWeekCompleteTaskZS(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchthisweekcompletetaskzs`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchthisweekcompletetaskzs`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchthisweekcompletetaskzs`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/fetchthisweekcompletetaskzs`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchthisweekcompletetaskzs`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchthisweekcompletetaskzs`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchthisweekcompletetaskzs`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/subtasks/fetchthisweekcompletetaskzs`, _data);
        }
        return this.http.post(`/subtasks/fetchthisweekcompletetaskzs`, _data);
    }
    /**
     * FetchTodoListTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async FetchTodoListTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchtodolisttask`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchtodolisttask`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchtodolisttask`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/fetchtodolisttask`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchtodolisttask`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchtodolisttask`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchtodolisttask`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/subtasks/fetchtodolisttask`, _data);
        }
        return this.http.post(`/subtasks/fetchtodolisttask`, _data);
    }
    /**
     * FetchTypeGroup
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async FetchTypeGroup(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchtypegroup`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchtypegroup`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchtypegroup`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/fetchtypegroup`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchtypegroup`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchtypegroup`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchtypegroup`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/subtasks/fetchtypegroup`, _data);
        }
        return this.http.post(`/subtasks/fetchtypegroup`, _data);
    }
    /**
     * FetchTypeGroupPlan
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof SubTaskService
     */
    async FetchTypeGroupPlan(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchtypegroupplan`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchtypegroupplan`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchtypegroupplan`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/subtasks/fetchtypegroupplan`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/subtasks/fetchtypegroupplan`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/subtasks/fetchtypegroupplan`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/subtasks/fetchtypegroupplan`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/subtasks/fetchtypegroupplan`, _data);
        }
        return this.http.post(`/subtasks/fetchtypegroupplan`, _data);
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
