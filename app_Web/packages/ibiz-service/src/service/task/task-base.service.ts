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
        projectmodule: 'module',
        productplan: 'plan',
        story: 'story',
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
        if (entity && entity.module && entity.module !== '') {
            const s = await ___ibz___.gs.getProjectModuleService();
            const data = await s.getLocal2(context, entity.module);
            if (data) {
                entity.modulename = data.name;
                entity.module = data.id;
                entity.projectmodule = data;
            }
        }
        if (entity && entity.plan && entity.plan !== '') {
            const s = await ___ibz___.gs.getProductPlanService();
            const data = await s.getLocal2(context, entity.plan);
            if (data) {
                entity.planname = data.title;
                entity.plan = data.id;
            }
        }
        if (entity && entity.story && entity.story !== '') {
            const s = await ___ibz___.gs.getStoryService();
            const data = await s.getLocal2(context, entity.story);
            if (data) {
                entity.storyname = data.title;
                entity.story = data.id;
                entity.story = data;
            }
        }
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
        if (_context.projectmodule && _context.projectmodule !== '') {
            const s = await ___ibz___.gs.getProjectModuleService();
            const data = await s.getLocal2(_context, _context.projectmodule);
            if (data) {
                entity.modulename = data.name;
                entity.module = data.id;
                entity.projectmodule = data;
            }
        }
        if (_context.productplan && _context.productplan !== '') {
            const s = await ___ibz___.gs.getProductPlanService();
            const data = await s.getLocal2(_context, _context.productplan);
            if (data) {
                entity.planname = data.title;
                entity.plan = data.id;
            }
        }
        if (_context.story && _context.story !== '') {
            const s = await ___ibz___.gs.getStoryService();
            const data = await s.getLocal2(_context, _context.story);
            if (data) {
                entity.storyname = data.title;
                entity.story = data.id;
                entity.story = data;
            }
        }
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
     * @memberof TaskService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task) {
            return this.http.get(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/select`);
        }
        if (_context.product && _context.story && _context.task) {
            return this.http.get(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/select`);
        }
        if (_context.product && _context.productplan && _context.task) {
            return this.http.get(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/select`);
        }
        if (_context.project && _context.task) {
            return this.http.get(`/projects/${_context.project}/tasks/${_context.task}/select`);
        }
        if (_context.story && _context.task) {
            return this.http.get(`/stories/${_context.story}/tasks/${_context.task}/select`);
        }
        if (_context.productplan && _context.task) {
            return this.http.get(`/productplans/${_context.productplan}/tasks/${_context.task}/select`);
        }
        if (_context.projectmodule && _context.task) {
            return this.http.get(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/select`);
        }
        return this.http.get(`/tasks/${_context.task}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks`, _data);
        }
        if (_context.product && _context.story && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks`, _data);
        }
        if (_context.product && _context.productplan && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks`, _data);
        }
        if (_context.project && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projects/${_context.project}/tasks`, _data);
        }
        if (_context.story && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/stories/${_context.story}/tasks`, _data);
        }
        if (_context.productplan && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/productplans/${_context.productplan}/tasks`, _data);
        }
        if (_context.projectmodule && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/tasks`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}`, _data);
        }
        if (_context.product && _context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}`, _data);
        }
        if (_context.product && _context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}`, _data);
        }
        if (_context.project && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/tasks/${_context.task}`, _data);
        }
        if (_context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/stories/${_context.story}/tasks/${_context.task}`, _data);
        }
        if (_context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/productplans/${_context.productplan}/tasks/${_context.task}`, _data);
        }
        if (_context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/tasks/${_context.task}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task) {
            return this.http.delete(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}`);
        }
        if (_context.product && _context.story && _context.task) {
            return this.http.delete(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}`);
        }
        if (_context.product && _context.productplan && _context.task) {
            return this.http.delete(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}`);
        }
        if (_context.project && _context.task) {
            return this.http.delete(`/projects/${_context.project}/tasks/${_context.task}`);
        }
        if (_context.story && _context.task) {
            return this.http.delete(`/stories/${_context.story}/tasks/${_context.task}`);
        }
        if (_context.productplan && _context.task) {
            return this.http.delete(`/productplans/${_context.productplan}/tasks/${_context.task}`);
        }
        if (_context.projectmodule && _context.task) {
            return this.http.delete(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}`);
        }
        return this.http.delete(`/tasks/${_context.task}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task) {
            const res = await this.http.get(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}`);
        if (res.ok && res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
            return res;
        }
        if (_context.product && _context.story && _context.task) {
            const res = await this.http.get(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}`);
        if (res.ok && res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
            return res;
        }
        if (_context.product && _context.productplan && _context.task) {
            const res = await this.http.get(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}`);
        if (res.ok && res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
            return res;
        }
        if (_context.project && _context.task) {
            const res = await this.http.get(`/projects/${_context.project}/tasks/${_context.task}`);
        if (res.ok && res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
            return res;
        }
        if (_context.story && _context.task) {
            const res = await this.http.get(`/stories/${_context.story}/tasks/${_context.task}`);
        if (res.ok && res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
            return res;
        }
        if (_context.productplan && _context.task) {
            const res = await this.http.get(`/productplans/${_context.productplan}/tasks/${_context.task}`);
        if (res.ok && res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
            return res;
        }
        if (_context.projectmodule && _context.task) {
            const res = await this.http.get(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}`);
        if (res.ok && res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
            return res;
        }
        const res = await this.http.get(`/tasks/${_context.task}`);
        if (res.ok && res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/getdraft`, _data);
        if (res.ok && res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
            return res;
        }
        if (_context.product && _context.story && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/stories/${_context.story}/tasks/getdraft`, _data);
        if (res.ok && res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
            return res;
        }
        if (_context.product && _context.productplan && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/productplans/${_context.productplan}/tasks/getdraft`, _data);
        if (res.ok && res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
            return res;
        }
        if (_context.project && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/tasks/getdraft`, _data);
        if (res.ok && res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
            return res;
        }
        if (_context.story && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/stories/${_context.story}/tasks/getdraft`, _data);
        if (res.ok && res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
            return res;
        }
        if (_context.productplan && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/productplans/${_context.productplan}/tasks/getdraft`, _data);
        if (res.ok && res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
            return res;
        }
        if (_context.projectmodule && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projectmodules/${_context.projectmodule}/tasks/getdraft`, _data);
        if (res.ok && res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/tasks/getdraft`, _data);
        if (res.ok && res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
        return res;
    }
    /**
     * Activate
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async Activate(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/activate`, _data);
        }
        if (_context.product && _context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/activate`, _data);
        }
        if (_context.product && _context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/activate`, _data);
        }
        if (_context.project && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/activate`, _data);
        }
        if (_context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/activate`, _data);
        }
        if (_context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/activate`, _data);
        }
        if (_context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/activate`, _data);
        }
        return this.http.post(`/tasks/${_context.task}/activate`, _data);
    }
    /**
     * AssignTo
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async AssignTo(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/assignto`, _data);
        }
        if (_context.product && _context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/assignto`, _data);
        }
        if (_context.product && _context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/assignto`, _data);
        }
        if (_context.project && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/assignto`, _data);
        }
        if (_context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/assignto`, _data);
        }
        if (_context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/assignto`, _data);
        }
        if (_context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/assignto`, _data);
        }
        return this.http.post(`/tasks/${_context.task}/assignto`, _data);
    }
    /**
     * Cancel
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async Cancel(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/cancel`, _data);
        }
        if (_context.product && _context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/cancel`, _data);
        }
        if (_context.product && _context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/cancel`, _data);
        }
        if (_context.project && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/cancel`, _data);
        }
        if (_context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/cancel`, _data);
        }
        if (_context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/cancel`, _data);
        }
        if (_context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/cancel`, _data);
        }
        return this.http.post(`/tasks/${_context.task}/cancel`, _data);
    }
    /**
     * Close
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async Close(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/close`, _data);
        }
        if (_context.product && _context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/close`, _data);
        }
        if (_context.product && _context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/close`, _data);
        }
        if (_context.project && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/close`, _data);
        }
        if (_context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/close`, _data);
        }
        if (_context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/close`, _data);
        }
        if (_context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/close`, _data);
        }
        return this.http.post(`/tasks/${_context.task}/close`, _data);
    }
    /**
     * ComputeBeginAndEnd
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async ComputeBeginAndEnd(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/computebeginandend`, _data);
        }
        if (_context.product && _context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/computebeginandend`, _data);
        }
        if (_context.product && _context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/computebeginandend`, _data);
        }
        if (_context.project && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/computebeginandend`, _data);
        }
        if (_context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/computebeginandend`, _data);
        }
        if (_context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/computebeginandend`, _data);
        }
        if (_context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/computebeginandend`, _data);
        }
        return this.http.post(`/tasks/${_context.task}/computebeginandend`, _data);
    }
    /**
     * ComputeWorkingHours
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async ComputeWorkingHours(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/computeworkinghours`, _data);
        }
        if (_context.product && _context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/computeworkinghours`, _data);
        }
        if (_context.product && _context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/computeworkinghours`, _data);
        }
        if (_context.project && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/computeworkinghours`, _data);
        }
        if (_context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/computeworkinghours`, _data);
        }
        if (_context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/computeworkinghours`, _data);
        }
        if (_context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/computeworkinghours`, _data);
        }
        return this.http.post(`/tasks/${_context.task}/computeworkinghours`, _data);
    }
    /**
     * ConfirmStoryChange
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async ConfirmStoryChange(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/confirmstorychange`, _data);
        }
        if (_context.product && _context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/confirmstorychange`, _data);
        }
        if (_context.product && _context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/confirmstorychange`, _data);
        }
        if (_context.project && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/confirmstorychange`, _data);
        }
        if (_context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/confirmstorychange`, _data);
        }
        if (_context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/confirmstorychange`, _data);
        }
        if (_context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/confirmstorychange`, _data);
        }
        return this.http.post(`/tasks/${_context.task}/confirmstorychange`, _data);
    }
    /**
     * CreateByCycle
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async CreateByCycle(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/createbycycle`, _data);
        }
        if (_context.product && _context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/createbycycle`, _data);
        }
        if (_context.product && _context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/createbycycle`, _data);
        }
        if (_context.project && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/createbycycle`, _data);
        }
        if (_context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/createbycycle`, _data);
        }
        if (_context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/createbycycle`, _data);
        }
        if (_context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/createbycycle`, _data);
        }
        return this.http.post(`/tasks/${_context.task}/createbycycle`, _data);
    }
    /**
     * CreateCycleTasks
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async CreateCycleTasks(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/createcycletasks`, _data);
        }
        if (_context.product && _context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/createcycletasks`, _data);
        }
        if (_context.product && _context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/createcycletasks`, _data);
        }
        if (_context.project && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/createcycletasks`, _data);
        }
        if (_context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/createcycletasks`, _data);
        }
        if (_context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/createcycletasks`, _data);
        }
        if (_context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/createcycletasks`, _data);
        }
        return this.http.post(`/tasks/${_context.task}/createcycletasks`, _data);
    }
    /**
     * Delete
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async Delete(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/delete`, _data);
        }
        if (_context.product && _context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/delete`, _data);
        }
        if (_context.product && _context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/delete`, _data);
        }
        if (_context.project && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/delete`, _data);
        }
        if (_context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/delete`, _data);
        }
        if (_context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/delete`, _data);
        }
        if (_context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/delete`, _data);
        }
        return this.http.post(`/tasks/${_context.task}/delete`, _data);
    }
    /**
     * DeleteEstimate
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async DeleteEstimate(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/deleteestimate`, _data);
        }
        if (_context.product && _context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/deleteestimate`, _data);
        }
        if (_context.product && _context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/deleteestimate`, _data);
        }
        if (_context.project && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/deleteestimate`, _data);
        }
        if (_context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/deleteestimate`, _data);
        }
        if (_context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/deleteestimate`, _data);
        }
        if (_context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/deleteestimate`, _data);
        }
        return this.http.post(`/tasks/${_context.task}/deleteestimate`, _data);
    }
    /**
     * EditEstimate
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async EditEstimate(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/editestimate`, _data);
        }
        if (_context.product && _context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/editestimate`, _data);
        }
        if (_context.product && _context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/editestimate`, _data);
        }
        if (_context.project && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/editestimate`, _data);
        }
        if (_context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/editestimate`, _data);
        }
        if (_context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/editestimate`, _data);
        }
        if (_context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/editestimate`, _data);
        }
        return this.http.post(`/tasks/${_context.task}/editestimate`, _data);
    }
    /**
     * Finish
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async Finish(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/finish`, _data);
        }
        if (_context.product && _context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/finish`, _data);
        }
        if (_context.product && _context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/finish`, _data);
        }
        if (_context.project && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/finish`, _data);
        }
        if (_context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/finish`, _data);
        }
        if (_context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/finish`, _data);
        }
        if (_context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/finish`, _data);
        }
        return this.http.post(`/tasks/${_context.task}/finish`, _data);
    }
    /**
     * GetNextTeamUser
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async GetNextTeamUser(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/getnextteamuser`, _data);
        }
        if (_context.product && _context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/getnextteamuser`, _data);
        }
        if (_context.product && _context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/getnextteamuser`, _data);
        }
        if (_context.project && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/tasks/${_context.task}/getnextteamuser`, _data);
        }
        if (_context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/stories/${_context.story}/tasks/${_context.task}/getnextteamuser`, _data);
        }
        if (_context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/productplans/${_context.productplan}/tasks/${_context.task}/getnextteamuser`, _data);
        }
        if (_context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/getnextteamuser`, _data);
        }
        return this.http.put(`/tasks/${_context.task}/getnextteamuser`, _data);
    }
    /**
     * GetTeamUserLeftActivity
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async GetTeamUserLeftActivity(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/getteamuserleftactivity`, _data);
        }
        if (_context.product && _context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/getteamuserleftactivity`, _data);
        }
        if (_context.product && _context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/getteamuserleftactivity`, _data);
        }
        if (_context.project && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/tasks/${_context.task}/getteamuserleftactivity`, _data);
        }
        if (_context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/stories/${_context.story}/tasks/${_context.task}/getteamuserleftactivity`, _data);
        }
        if (_context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/productplans/${_context.productplan}/tasks/${_context.task}/getteamuserleftactivity`, _data);
        }
        if (_context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/getteamuserleftactivity`, _data);
        }
        return this.http.put(`/tasks/${_context.task}/getteamuserleftactivity`, _data);
    }
    /**
     * GetTeamUserLeftStart
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async GetTeamUserLeftStart(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/getteamuserleftstart`, _data);
        }
        if (_context.product && _context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/getteamuserleftstart`, _data);
        }
        if (_context.product && _context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/getteamuserleftstart`, _data);
        }
        if (_context.project && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/tasks/${_context.task}/getteamuserleftstart`, _data);
        }
        if (_context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/stories/${_context.story}/tasks/${_context.task}/getteamuserleftstart`, _data);
        }
        if (_context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/productplans/${_context.productplan}/tasks/${_context.task}/getteamuserleftstart`, _data);
        }
        if (_context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/getteamuserleftstart`, _data);
        }
        return this.http.put(`/tasks/${_context.task}/getteamuserleftstart`, _data);
    }
    /**
     * GetUsernames
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async GetUsernames(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/getusernames`, _data);
        }
        if (_context.product && _context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/getusernames`, _data);
        }
        if (_context.product && _context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/getusernames`, _data);
        }
        if (_context.project && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/tasks/${_context.task}/getusernames`, _data);
        }
        if (_context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/stories/${_context.story}/tasks/${_context.task}/getusernames`, _data);
        }
        if (_context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/productplans/${_context.productplan}/tasks/${_context.task}/getusernames`, _data);
        }
        if (_context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/getusernames`, _data);
        }
        return this.http.put(`/tasks/${_context.task}/getusernames`, _data);
    }
    /**
     * LinkPlan
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async LinkPlan(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/linkplan`, _data);
        }
        if (_context.product && _context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/linkplan`, _data);
        }
        if (_context.product && _context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/linkplan`, _data);
        }
        if (_context.project && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/linkplan`, _data);
        }
        if (_context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/linkplan`, _data);
        }
        if (_context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/linkplan`, _data);
        }
        if (_context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/linkplan`, _data);
        }
        return this.http.post(`/tasks/${_context.task}/linkplan`, _data);
    }
    /**
     * OtherUpdate
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async OtherUpdate(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/otherupdate`, _data);
        }
        if (_context.product && _context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/otherupdate`, _data);
        }
        if (_context.product && _context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/otherupdate`, _data);
        }
        if (_context.project && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/tasks/${_context.task}/otherupdate`, _data);
        }
        if (_context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/stories/${_context.story}/tasks/${_context.task}/otherupdate`, _data);
        }
        if (_context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/productplans/${_context.productplan}/tasks/${_context.task}/otherupdate`, _data);
        }
        if (_context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/otherupdate`, _data);
        }
        return this.http.put(`/tasks/${_context.task}/otherupdate`, _data);
    }
    /**
     * Pause
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async Pause(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/pause`, _data);
        }
        if (_context.product && _context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/pause`, _data);
        }
        if (_context.product && _context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/pause`, _data);
        }
        if (_context.project && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/pause`, _data);
        }
        if (_context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/pause`, _data);
        }
        if (_context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/pause`, _data);
        }
        if (_context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/pause`, _data);
        }
        return this.http.post(`/tasks/${_context.task}/pause`, _data);
    }
    /**
     * RecordEstimate
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async RecordEstimate(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/recordestimate`, _data);
        }
        if (_context.product && _context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/recordestimate`, _data);
        }
        if (_context.product && _context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/recordestimate`, _data);
        }
        if (_context.project && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/recordestimate`, _data);
        }
        if (_context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/recordestimate`, _data);
        }
        if (_context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/recordestimate`, _data);
        }
        if (_context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/recordestimate`, _data);
        }
        return this.http.post(`/tasks/${_context.task}/recordestimate`, _data);
    }
    /**
     * RecordTimZeroLeftAfterContinue
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async RecordTimZeroLeftAfterContinue(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/recordtimzeroleftaftercontinue`, _data);
        }
        if (_context.product && _context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/recordtimzeroleftaftercontinue`, _data);
        }
        if (_context.product && _context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/recordtimzeroleftaftercontinue`, _data);
        }
        if (_context.project && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/recordtimzeroleftaftercontinue`, _data);
        }
        if (_context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/recordtimzeroleftaftercontinue`, _data);
        }
        if (_context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/recordtimzeroleftaftercontinue`, _data);
        }
        if (_context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/recordtimzeroleftaftercontinue`, _data);
        }
        return this.http.post(`/tasks/${_context.task}/recordtimzeroleftaftercontinue`, _data);
    }
    /**
     * RecordTimateZeroLeft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async RecordTimateZeroLeft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/recordtimatezeroleft`, _data);
        }
        if (_context.product && _context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/recordtimatezeroleft`, _data);
        }
        if (_context.product && _context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/recordtimatezeroleft`, _data);
        }
        if (_context.project && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/recordtimatezeroleft`, _data);
        }
        if (_context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/recordtimatezeroleft`, _data);
        }
        if (_context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/recordtimatezeroleft`, _data);
        }
        if (_context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/recordtimatezeroleft`, _data);
        }
        return this.http.post(`/tasks/${_context.task}/recordtimatezeroleft`, _data);
    }
    /**
     * RecordTimateZeroLeftAfterStart
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async RecordTimateZeroLeftAfterStart(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/recordtimatezeroleftafterstart`, _data);
        }
        if (_context.product && _context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/recordtimatezeroleftafterstart`, _data);
        }
        if (_context.product && _context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/recordtimatezeroleftafterstart`, _data);
        }
        if (_context.project && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/recordtimatezeroleftafterstart`, _data);
        }
        if (_context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/recordtimatezeroleftafterstart`, _data);
        }
        if (_context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/recordtimatezeroleftafterstart`, _data);
        }
        if (_context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/recordtimatezeroleftafterstart`, _data);
        }
        return this.http.post(`/tasks/${_context.task}/recordtimatezeroleftafterstart`, _data);
    }
    /**
     * Restart
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async Restart(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/restart`, _data);
        }
        if (_context.product && _context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/restart`, _data);
        }
        if (_context.product && _context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/restart`, _data);
        }
        if (_context.project && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/restart`, _data);
        }
        if (_context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/restart`, _data);
        }
        if (_context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/restart`, _data);
        }
        if (_context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/restart`, _data);
        }
        return this.http.post(`/tasks/${_context.task}/restart`, _data);
    }
    /**
     * SendMessage
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async SendMessage(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/sendmessage`, _data);
        }
        if (_context.product && _context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/sendmessage`, _data);
        }
        if (_context.product && _context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/sendmessage`, _data);
        }
        if (_context.project && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/sendmessage`, _data);
        }
        if (_context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/sendmessage`, _data);
        }
        if (_context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/sendmessage`, _data);
        }
        if (_context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/sendmessage`, _data);
        }
        return this.http.post(`/tasks/${_context.task}/sendmessage`, _data);
    }
    /**
     * SendMsgPreProcess
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async SendMsgPreProcess(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/sendmsgpreprocess`, _data);
        }
        if (_context.product && _context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/sendmsgpreprocess`, _data);
        }
        if (_context.product && _context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/sendmsgpreprocess`, _data);
        }
        if (_context.project && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/sendmsgpreprocess`, _data);
        }
        if (_context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/sendmsgpreprocess`, _data);
        }
        if (_context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/sendmsgpreprocess`, _data);
        }
        if (_context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/sendmsgpreprocess`, _data);
        }
        return this.http.post(`/tasks/${_context.task}/sendmsgpreprocess`, _data);
    }
    /**
     * Start
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async Start(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/start`, _data);
        }
        if (_context.product && _context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/start`, _data);
        }
        if (_context.product && _context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/start`, _data);
        }
        if (_context.project && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/start`, _data);
        }
        if (_context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/start`, _data);
        }
        if (_context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/start`, _data);
        }
        if (_context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/start`, _data);
        }
        return this.http.post(`/tasks/${_context.task}/start`, _data);
    }
    /**
     * TaskFavorites
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async TaskFavorites(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/taskfavorites`, _data);
        }
        if (_context.product && _context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/taskfavorites`, _data);
        }
        if (_context.product && _context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/taskfavorites`, _data);
        }
        if (_context.project && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/taskfavorites`, _data);
        }
        if (_context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/taskfavorites`, _data);
        }
        if (_context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/taskfavorites`, _data);
        }
        if (_context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/taskfavorites`, _data);
        }
        return this.http.post(`/tasks/${_context.task}/taskfavorites`, _data);
    }
    /**
     * TaskForward
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async TaskForward(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/taskforward`, _data);
        }
        if (_context.product && _context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/taskforward`, _data);
        }
        if (_context.product && _context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/taskforward`, _data);
        }
        if (_context.project && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/taskforward`, _data);
        }
        if (_context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/taskforward`, _data);
        }
        if (_context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/taskforward`, _data);
        }
        if (_context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/taskforward`, _data);
        }
        return this.http.post(`/tasks/${_context.task}/taskforward`, _data);
    }
    /**
     * TaskNFavorites
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async TaskNFavorites(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/tasknfavorites`, _data);
        }
        if (_context.product && _context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/tasknfavorites`, _data);
        }
        if (_context.product && _context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/tasknfavorites`, _data);
        }
        if (_context.project && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/tasknfavorites`, _data);
        }
        if (_context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/tasknfavorites`, _data);
        }
        if (_context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/tasknfavorites`, _data);
        }
        if (_context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/tasknfavorites`, _data);
        }
        return this.http.post(`/tasks/${_context.task}/tasknfavorites`, _data);
    }
    /**
     * UpdateRelatedPlanStatus
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async UpdateRelatedPlanStatus(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/updaterelatedplanstatus`, _data);
        }
        if (_context.product && _context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/updaterelatedplanstatus`, _data);
        }
        if (_context.product && _context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/updaterelatedplanstatus`, _data);
        }
        if (_context.project && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/tasks/${_context.task}/updaterelatedplanstatus`, _data);
        }
        if (_context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/stories/${_context.story}/tasks/${_context.task}/updaterelatedplanstatus`, _data);
        }
        if (_context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/productplans/${_context.productplan}/tasks/${_context.task}/updaterelatedplanstatus`, _data);
        }
        if (_context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/updaterelatedplanstatus`, _data);
        }
        return this.http.put(`/tasks/${_context.task}/updaterelatedplanstatus`, _data);
    }
    /**
     * UpdateStoryVersion
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async UpdateStoryVersion(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/updatestoryversion`, _data);
        }
        if (_context.product && _context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/updatestoryversion`, _data);
        }
        if (_context.product && _context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/updatestoryversion`, _data);
        }
        if (_context.project && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/tasks/${_context.task}/updatestoryversion`, _data);
        }
        if (_context.story && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/stories/${_context.story}/tasks/${_context.task}/updatestoryversion`, _data);
        }
        if (_context.productplan && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/productplans/${_context.productplan}/tasks/${_context.task}/updatestoryversion`, _data);
        }
        if (_context.projectmodule && _context.task) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/updatestoryversion`, _data);
        }
        return this.http.put(`/tasks/${_context.task}/updatestoryversion`, _data);
    }
    /**
     * FetchAssignedToMyTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchAssignedToMyTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchassignedtomytask`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchassignedtomytask`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchassignedtomytask`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchassignedtomytask`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchassignedtomytask`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchassignedtomytask`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchassignedtomytask`, _data);
        }
        return this.http.post(`/tasks/fetchassignedtomytask`, _data);
    }
    /**
     * FetchAssignedToMyTaskPc
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchAssignedToMyTaskPc(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchassignedtomytaskpc`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchassignedtomytaskpc`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchassignedtomytaskpc`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchassignedtomytaskpc`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchassignedtomytaskpc`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchassignedtomytaskpc`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchassignedtomytaskpc`, _data);
        }
        return this.http.post(`/tasks/fetchassignedtomytaskpc`, _data);
    }
    /**
     * FetchBugTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchBugTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchbugtask`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchbugtask`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchbugtask`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchbugtask`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchbugtask`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchbugtask`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchbugtask`, _data);
        }
        return this.http.post(`/tasks/fetchbugtask`, _data);
    }
    /**
     * FetchByModule
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchByModule(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchbymodule`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchbymodule`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchbymodule`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchbymodule`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchbymodule`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchbymodule`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchbymodule`, _data);
        }
        return this.http.post(`/tasks/fetchbymodule`, _data);
    }
    /**
     * FetchChildDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchChildDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchchilddefault`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchchilddefault`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchchilddefault`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchchilddefault`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchchilddefault`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchchilddefault`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchchilddefault`, _data);
        }
        return this.http.post(`/tasks/fetchchilddefault`, _data);
    }
    /**
     * FetchChildTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchChildTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchchildtask`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchchildtask`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchchildtask`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchchildtask`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchchildtask`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchchildtask`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchchildtask`, _data);
        }
        return this.http.post(`/tasks/fetchchildtask`, _data);
    }
    /**
     * FetchChildTaskTree
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchChildTaskTree(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchchildtasktree`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchchildtasktree`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchchildtasktree`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchchildtasktree`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchchildtasktree`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchchildtasktree`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchchildtasktree`, _data);
        }
        return this.http.post(`/tasks/fetchchildtasktree`, _data);
    }
    /**
     * FetchCurFinishTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchCurFinishTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchcurfinishtask`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchcurfinishtask`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchcurfinishtask`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchcurfinishtask`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchcurfinishtask`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchcurfinishtask`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchcurfinishtask`, _data);
        }
        return this.http.post(`/tasks/fetchcurfinishtask`, _data);
    }
    /**
     * FetchCurProjectTaskQuery
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchCurProjectTaskQuery(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchcurprojecttaskquery`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchcurprojecttaskquery`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchcurprojecttaskquery`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchcurprojecttaskquery`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchcurprojecttaskquery`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchcurprojecttaskquery`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchcurprojecttaskquery`, _data);
        }
        return this.http.post(`/tasks/fetchcurprojecttaskquery`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchdefault`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchdefault`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchdefault`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchdefault`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchdefault`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchdefault`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchdefault`, _data);
        }
        return this.http.post(`/tasks/fetchdefault`, _data);
    }
    /**
     * FetchDefaultRow
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchDefaultRow(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchdefaultrow`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchdefaultrow`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchdefaultrow`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchdefaultrow`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchdefaultrow`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchdefaultrow`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchdefaultrow`, _data);
        }
        return this.http.post(`/tasks/fetchdefaultrow`, _data);
    }
    /**
     * FetchESBulk
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchESBulk(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchesbulk`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchesbulk`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchesbulk`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchesbulk`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchesbulk`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchesbulk`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchesbulk`, _data);
        }
        return this.http.post(`/tasks/fetchesbulk`, _data);
    }
    /**
     * FetchMyAgentTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchMyAgentTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchmyagenttask`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchmyagenttask`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchmyagenttask`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchmyagenttask`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchmyagenttask`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchmyagenttask`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchmyagenttask`, _data);
        }
        return this.http.post(`/tasks/fetchmyagenttask`, _data);
    }
    /**
     * FetchMyAllTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchMyAllTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchmyalltask`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchmyalltask`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchmyalltask`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchmyalltask`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchmyalltask`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchmyalltask`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchmyalltask`, _data);
        }
        return this.http.post(`/tasks/fetchmyalltask`, _data);
    }
    /**
     * FetchMyCompleteTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchMyCompleteTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchmycompletetask`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchmycompletetask`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchmycompletetask`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchmycompletetask`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchmycompletetask`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchmycompletetask`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchmycompletetask`, _data);
        }
        return this.http.post(`/tasks/fetchmycompletetask`, _data);
    }
    /**
     * FetchMyCompleteTaskMobDaily
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchMyCompleteTaskMobDaily(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchmycompletetaskmobdaily`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchmycompletetaskmobdaily`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchmycompletetaskmobdaily`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchmycompletetaskmobdaily`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchmycompletetaskmobdaily`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchmycompletetaskmobdaily`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchmycompletetaskmobdaily`, _data);
        }
        return this.http.post(`/tasks/fetchmycompletetaskmobdaily`, _data);
    }
    /**
     * FetchMyCompleteTaskMobMonthly
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchMyCompleteTaskMobMonthly(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchmycompletetaskmobmonthly`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchmycompletetaskmobmonthly`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchmycompletetaskmobmonthly`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchmycompletetaskmobmonthly`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchmycompletetaskmobmonthly`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchmycompletetaskmobmonthly`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchmycompletetaskmobmonthly`, _data);
        }
        return this.http.post(`/tasks/fetchmycompletetaskmobmonthly`, _data);
    }
    /**
     * FetchMyCompleteTaskMonthlyZS
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchMyCompleteTaskMonthlyZS(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchmycompletetaskmonthlyzs`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchmycompletetaskmonthlyzs`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchmycompletetaskmonthlyzs`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchmycompletetaskmonthlyzs`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchmycompletetaskmonthlyzs`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchmycompletetaskmonthlyzs`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchmycompletetaskmonthlyzs`, _data);
        }
        return this.http.post(`/tasks/fetchmycompletetaskmonthlyzs`, _data);
    }
    /**
     * FetchMyCompleteTaskZS
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchMyCompleteTaskZS(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchmycompletetaskzs`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchmycompletetaskzs`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchmycompletetaskzs`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchmycompletetaskzs`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchmycompletetaskzs`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchmycompletetaskzs`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchmycompletetaskzs`, _data);
        }
        return this.http.post(`/tasks/fetchmycompletetaskzs`, _data);
    }
    /**
     * FetchMyFavorites
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchMyFavorites(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchmyfavorites`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchmyfavorites`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchmyfavorites`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchmyfavorites`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchmyfavorites`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchmyfavorites`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchmyfavorites`, _data);
        }
        return this.http.post(`/tasks/fetchmyfavorites`, _data);
    }
    /**
     * FetchMyPlansTaskMobMonthly
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchMyPlansTaskMobMonthly(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchmyplanstaskmobmonthly`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchmyplanstaskmobmonthly`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchmyplanstaskmobmonthly`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchmyplanstaskmobmonthly`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchmyplanstaskmobmonthly`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchmyplanstaskmobmonthly`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchmyplanstaskmobmonthly`, _data);
        }
        return this.http.post(`/tasks/fetchmyplanstaskmobmonthly`, _data);
    }
    /**
     * FetchMyTomorrowPlanTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchMyTomorrowPlanTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchmytomorrowplantask`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchmytomorrowplantask`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchmytomorrowplantask`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchmytomorrowplantask`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchmytomorrowplantask`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchmytomorrowplantask`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchmytomorrowplantask`, _data);
        }
        return this.http.post(`/tasks/fetchmytomorrowplantask`, _data);
    }
    /**
     * FetchMyTomorrowPlanTaskMobDaily
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchMyTomorrowPlanTaskMobDaily(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchmytomorrowplantaskmobdaily`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchmytomorrowplantaskmobdaily`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchmytomorrowplantaskmobdaily`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchmytomorrowplantaskmobdaily`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchmytomorrowplantaskmobdaily`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchmytomorrowplantaskmobdaily`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchmytomorrowplantaskmobdaily`, _data);
        }
        return this.http.post(`/tasks/fetchmytomorrowplantaskmobdaily`, _data);
    }
    /**
     * FetchNextWeekCompleteTaskMobZS
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchNextWeekCompleteTaskMobZS(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchnextweekcompletetaskmobzs`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchnextweekcompletetaskmobzs`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchnextweekcompletetaskmobzs`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchnextweekcompletetaskmobzs`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchnextweekcompletetaskmobzs`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchnextweekcompletetaskmobzs`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchnextweekcompletetaskmobzs`, _data);
        }
        return this.http.post(`/tasks/fetchnextweekcompletetaskmobzs`, _data);
    }
    /**
     * FetchNextWeekCompleteTaskZS
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchNextWeekCompleteTaskZS(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchnextweekcompletetaskzs`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchnextweekcompletetaskzs`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchnextweekcompletetaskzs`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchnextweekcompletetaskzs`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchnextweekcompletetaskzs`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchnextweekcompletetaskzs`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchnextweekcompletetaskzs`, _data);
        }
        return this.http.post(`/tasks/fetchnextweekcompletetaskzs`, _data);
    }
    /**
     * FetchNextWeekPlanCompleteTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchNextWeekPlanCompleteTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchnextweekplancompletetask`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchnextweekplancompletetask`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchnextweekplancompletetask`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchnextweekplancompletetask`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchnextweekplancompletetask`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchnextweekplancompletetask`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchnextweekplancompletetask`, _data);
        }
        return this.http.post(`/tasks/fetchnextweekplancompletetask`, _data);
    }
    /**
     * FetchPlanTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchPlanTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchplantask`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchplantask`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchplantask`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchplantask`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchplantask`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchplantask`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchplantask`, _data);
        }
        return this.http.post(`/tasks/fetchplantask`, _data);
    }
    /**
     * FetchProjectAppTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchProjectAppTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchprojectapptask`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchprojectapptask`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchprojectapptask`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchprojectapptask`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchprojectapptask`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchprojectapptask`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchprojectapptask`, _data);
        }
        return this.http.post(`/tasks/fetchprojectapptask`, _data);
    }
    /**
     * FetchProjectTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchProjectTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchprojecttask`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchprojecttask`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchprojecttask`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchprojecttask`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchprojecttask`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchprojecttask`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchprojecttask`, _data);
        }
        return this.http.post(`/tasks/fetchprojecttask`, _data);
    }
    /**
     * FetchRootTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchRootTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchroottask`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchroottask`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchroottask`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchroottask`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchroottask`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchroottask`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchroottask`, _data);
        }
        return this.http.post(`/tasks/fetchroottask`, _data);
    }
    /**
     * FetchTaskLinkPlan
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchTaskLinkPlan(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchtasklinkplan`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchtasklinkplan`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchtasklinkplan`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchtasklinkplan`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchtasklinkplan`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchtasklinkplan`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchtasklinkplan`, _data);
        }
        return this.http.post(`/tasks/fetchtasklinkplan`, _data);
    }
    /**
     * FetchThisMonthCompleteTaskChoice
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchThisMonthCompleteTaskChoice(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchthismonthcompletetaskchoice`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchthismonthcompletetaskchoice`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchthismonthcompletetaskchoice`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchthismonthcompletetaskchoice`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchthismonthcompletetaskchoice`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchthismonthcompletetaskchoice`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchthismonthcompletetaskchoice`, _data);
        }
        return this.http.post(`/tasks/fetchthismonthcompletetaskchoice`, _data);
    }
    /**
     * FetchThisWeekCompleteTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchThisWeekCompleteTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchthisweekcompletetask`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchthisweekcompletetask`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchthisweekcompletetask`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchthisweekcompletetask`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchthisweekcompletetask`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchthisweekcompletetask`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchthisweekcompletetask`, _data);
        }
        return this.http.post(`/tasks/fetchthisweekcompletetask`, _data);
    }
    /**
     * FetchThisWeekCompleteTaskChoice
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchThisWeekCompleteTaskChoice(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchthisweekcompletetaskchoice`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchthisweekcompletetaskchoice`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchthisweekcompletetaskchoice`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchthisweekcompletetaskchoice`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchthisweekcompletetaskchoice`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchthisweekcompletetaskchoice`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchthisweekcompletetaskchoice`, _data);
        }
        return this.http.post(`/tasks/fetchthisweekcompletetaskchoice`, _data);
    }
    /**
     * FetchThisWeekCompleteTaskMobZS
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchThisWeekCompleteTaskMobZS(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchthisweekcompletetaskmobzs`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchthisweekcompletetaskmobzs`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchthisweekcompletetaskmobzs`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchthisweekcompletetaskmobzs`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchthisweekcompletetaskmobzs`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchthisweekcompletetaskmobzs`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchthisweekcompletetaskmobzs`, _data);
        }
        return this.http.post(`/tasks/fetchthisweekcompletetaskmobzs`, _data);
    }
    /**
     * FetchThisWeekCompleteTaskZS
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchThisWeekCompleteTaskZS(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchthisweekcompletetaskzs`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchthisweekcompletetaskzs`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchthisweekcompletetaskzs`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchthisweekcompletetaskzs`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchthisweekcompletetaskzs`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchthisweekcompletetaskzs`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchthisweekcompletetaskzs`, _data);
        }
        return this.http.post(`/tasks/fetchthisweekcompletetaskzs`, _data);
    }
    /**
     * FetchTodoListTask
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchTodoListTask(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchtodolisttask`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchtodolisttask`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchtodolisttask`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchtodolisttask`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchtodolisttask`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchtodolisttask`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchtodolisttask`, _data);
        }
        return this.http.post(`/tasks/fetchtodolisttask`, _data);
    }
    /**
     * FetchTypeGroup
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchTypeGroup(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchtypegroup`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchtypegroup`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchtypegroup`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchtypegroup`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchtypegroup`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchtypegroup`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchtypegroup`, _data);
        }
        return this.http.post(`/tasks/fetchtypegroup`, _data);
    }
    /**
     * FetchTypeGroupPlan
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskService
     */
    async FetchTypeGroupPlan(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/fetchtypegroupplan`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/fetchtypegroupplan`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/fetchtypegroupplan`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/tasks/fetchtypegroupplan`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/tasks/fetchtypegroupplan`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/fetchtypegroupplan`, _data);
        }
        if (_context.projectmodule && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/fetchtypegroupplan`, _data);
        }
        return this.http.post(`/tasks/fetchtypegroupplan`, _data);
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

    /**
     * ActivateBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async ActivateBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/activatebatch`,tempData,isloading);
        }
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/activatebatch`,tempData,isloading);
        }
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/activatebatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/activatebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/activatebatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/activatebatch`,tempData,isloading);
        }
        if(context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/activatebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/tasks/activatebatch`,tempData,isloading);
    }

    /**
     * AssignToBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async AssignToBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/assigntobatch`,tempData,isloading);
        }
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/assigntobatch`,tempData,isloading);
        }
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/assigntobatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/assigntobatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/assigntobatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/assigntobatch`,tempData,isloading);
        }
        if(context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/assigntobatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/tasks/assigntobatch`,tempData,isloading);
    }

    /**
     * CancelBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async CancelBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/cancelbatch`,tempData,isloading);
        }
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/cancelbatch`,tempData,isloading);
        }
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/cancelbatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/cancelbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/cancelbatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/cancelbatch`,tempData,isloading);
        }
        if(context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/cancelbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/tasks/cancelbatch`,tempData,isloading);
    }

    /**
     * CloseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async CloseBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/closebatch`,tempData,isloading);
        }
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/closebatch`,tempData,isloading);
        }
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/closebatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/closebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/closebatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/closebatch`,tempData,isloading);
        }
        if(context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/closebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/tasks/closebatch`,tempData,isloading);
    }

    /**
     * ComputeBeginAndEndBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async ComputeBeginAndEndBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/computebeginandendbatch`,tempData,isloading);
        }
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/computebeginandendbatch`,tempData,isloading);
        }
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/computebeginandendbatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/computebeginandendbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/computebeginandendbatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/computebeginandendbatch`,tempData,isloading);
        }
        if(context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/computebeginandendbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/tasks/computebeginandendbatch`,tempData,isloading);
    }

    /**
     * ComputeWorkingHoursBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async ComputeWorkingHoursBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/computeworkinghoursbatch`,tempData,isloading);
        }
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/computeworkinghoursbatch`,tempData,isloading);
        }
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/computeworkinghoursbatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/computeworkinghoursbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/computeworkinghoursbatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/computeworkinghoursbatch`,tempData,isloading);
        }
        if(context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/computeworkinghoursbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/tasks/computeworkinghoursbatch`,tempData,isloading);
    }

    /**
     * ConfirmStoryChangeBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async ConfirmStoryChangeBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/confirmstorychangebatch`,tempData,isloading);
        }
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/confirmstorychangebatch`,tempData,isloading);
        }
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/confirmstorychangebatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/confirmstorychangebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/confirmstorychangebatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/confirmstorychangebatch`,tempData,isloading);
        }
        if(context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/confirmstorychangebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/tasks/confirmstorychangebatch`,tempData,isloading);
    }

    /**
     * CreateByCycleBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async CreateByCycleBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/createbycyclebatch`,tempData,isloading);
        }
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/createbycyclebatch`,tempData,isloading);
        }
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/createbycyclebatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/createbycyclebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/createbycyclebatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/createbycyclebatch`,tempData,isloading);
        }
        if(context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/createbycyclebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/tasks/createbycyclebatch`,tempData,isloading);
    }

    /**
     * CreateCycleTasksBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async CreateCycleTasksBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/createcycletasksbatch`,tempData,isloading);
        }
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/createcycletasksbatch`,tempData,isloading);
        }
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/createcycletasksbatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/createcycletasksbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/createcycletasksbatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/createcycletasksbatch`,tempData,isloading);
        }
        if(context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/createcycletasksbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/tasks/createcycletasksbatch`,tempData,isloading);
    }

    /**
     * DeleteBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async DeleteBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/deletebatch`,tempData,isloading);
        }
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/deletebatch`,tempData,isloading);
        }
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/deletebatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/deletebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/deletebatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/deletebatch`,tempData,isloading);
        }
        if(context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/deletebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/tasks/deletebatch`,tempData,isloading);
    }

    /**
     * DeleteEstimateBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async DeleteEstimateBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/deleteestimatebatch`,tempData,isloading);
        }
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/deleteestimatebatch`,tempData,isloading);
        }
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/deleteestimatebatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/deleteestimatebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/deleteestimatebatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/deleteestimatebatch`,tempData,isloading);
        }
        if(context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/deleteestimatebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/tasks/deleteestimatebatch`,tempData,isloading);
    }

    /**
     * EditEstimateBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async EditEstimateBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/editestimatebatch`,tempData,isloading);
        }
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/editestimatebatch`,tempData,isloading);
        }
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/editestimatebatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/editestimatebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/editestimatebatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/editestimatebatch`,tempData,isloading);
        }
        if(context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/editestimatebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/tasks/editestimatebatch`,tempData,isloading);
    }

    /**
     * FinishBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async FinishBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/finishbatch`,tempData,isloading);
        }
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/finishbatch`,tempData,isloading);
        }
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/finishbatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/finishbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/finishbatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/finishbatch`,tempData,isloading);
        }
        if(context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/finishbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/tasks/finishbatch`,tempData,isloading);
    }

    /**
     * GetNextTeamUserBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async GetNextTeamUserBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/getnextteamuserbatch`,tempData,isloading);
        }
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/getnextteamuserbatch`,tempData,isloading);
        }
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/getnextteamuserbatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/getnextteamuserbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/getnextteamuserbatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/getnextteamuserbatch`,tempData,isloading);
        }
        if(context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/getnextteamuserbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/tasks/getnextteamuserbatch`,tempData,isloading);
    }

    /**
     * GetTeamUserLeftActivityBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async GetTeamUserLeftActivityBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/getteamuserleftactivitybatch`,tempData,isloading);
        }
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/getteamuserleftactivitybatch`,tempData,isloading);
        }
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/getteamuserleftactivitybatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/getteamuserleftactivitybatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/getteamuserleftactivitybatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/getteamuserleftactivitybatch`,tempData,isloading);
        }
        if(context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/getteamuserleftactivitybatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/tasks/getteamuserleftactivitybatch`,tempData,isloading);
    }

    /**
     * GetTeamUserLeftStartBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async GetTeamUserLeftStartBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/getteamuserleftstartbatch`,tempData,isloading);
        }
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/getteamuserleftstartbatch`,tempData,isloading);
        }
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/getteamuserleftstartbatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/getteamuserleftstartbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/getteamuserleftstartbatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/getteamuserleftstartbatch`,tempData,isloading);
        }
        if(context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/getteamuserleftstartbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/tasks/getteamuserleftstartbatch`,tempData,isloading);
    }

    /**
     * LinkPlanBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async LinkPlanBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/linkplanbatch`,tempData,isloading);
        }
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/linkplanbatch`,tempData,isloading);
        }
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/linkplanbatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/linkplanbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/linkplanbatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/linkplanbatch`,tempData,isloading);
        }
        if(context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/linkplanbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/tasks/linkplanbatch`,tempData,isloading);
    }

    /**
     * OtherUpdateBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async OtherUpdateBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/otherupdatebatch`,tempData,isloading);
        }
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/otherupdatebatch`,tempData,isloading);
        }
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/otherupdatebatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/otherupdatebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/otherupdatebatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/otherupdatebatch`,tempData,isloading);
        }
        if(context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/otherupdatebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/tasks/otherupdatebatch`,tempData,isloading);
    }

    /**
     * PauseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async PauseBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/pausebatch`,tempData,isloading);
        }
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/pausebatch`,tempData,isloading);
        }
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/pausebatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/pausebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/pausebatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/pausebatch`,tempData,isloading);
        }
        if(context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/pausebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/tasks/pausebatch`,tempData,isloading);
    }

    /**
     * RecordEstimateBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async RecordEstimateBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/recordestimatebatch`,tempData,isloading);
        }
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/recordestimatebatch`,tempData,isloading);
        }
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/recordestimatebatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/recordestimatebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/recordestimatebatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/recordestimatebatch`,tempData,isloading);
        }
        if(context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/recordestimatebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/tasks/recordestimatebatch`,tempData,isloading);
    }

    /**
     * RecordTimZeroLeftAfterContinueBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async RecordTimZeroLeftAfterContinueBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/recordtimzeroleftaftercontinuebatch`,tempData,isloading);
        }
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/recordtimzeroleftaftercontinuebatch`,tempData,isloading);
        }
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/recordtimzeroleftaftercontinuebatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/recordtimzeroleftaftercontinuebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/recordtimzeroleftaftercontinuebatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/recordtimzeroleftaftercontinuebatch`,tempData,isloading);
        }
        if(context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/recordtimzeroleftaftercontinuebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/tasks/recordtimzeroleftaftercontinuebatch`,tempData,isloading);
    }

    /**
     * RecordTimateZeroLeftBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async RecordTimateZeroLeftBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/recordtimatezeroleftbatch`,tempData,isloading);
        }
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/recordtimatezeroleftbatch`,tempData,isloading);
        }
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/recordtimatezeroleftbatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/recordtimatezeroleftbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/recordtimatezeroleftbatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/recordtimatezeroleftbatch`,tempData,isloading);
        }
        if(context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/recordtimatezeroleftbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/tasks/recordtimatezeroleftbatch`,tempData,isloading);
    }

    /**
     * RecordTimateZeroLeftAfterStartBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async RecordTimateZeroLeftAfterStartBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/recordtimatezeroleftafterstartbatch`,tempData,isloading);
        }
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/recordtimatezeroleftafterstartbatch`,tempData,isloading);
        }
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/recordtimatezeroleftafterstartbatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/recordtimatezeroleftafterstartbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/recordtimatezeroleftafterstartbatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/recordtimatezeroleftafterstartbatch`,tempData,isloading);
        }
        if(context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/recordtimatezeroleftafterstartbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/tasks/recordtimatezeroleftafterstartbatch`,tempData,isloading);
    }

    /**
     * RestartBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async RestartBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/restartbatch`,tempData,isloading);
        }
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/restartbatch`,tempData,isloading);
        }
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/restartbatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/restartbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/restartbatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/restartbatch`,tempData,isloading);
        }
        if(context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/restartbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/tasks/restartbatch`,tempData,isloading);
    }

    /**
     * SendMessageBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async SendMessageBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/sendmessagebatch`,tempData,isloading);
        }
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/sendmessagebatch`,tempData,isloading);
        }
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/sendmessagebatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/sendmessagebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/sendmessagebatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/sendmessagebatch`,tempData,isloading);
        }
        if(context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/sendmessagebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/tasks/sendmessagebatch`,tempData,isloading);
    }

    /**
     * SendMsgPreProcessBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async SendMsgPreProcessBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/sendmsgpreprocessbatch`,tempData,isloading);
        }
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/sendmsgpreprocessbatch`,tempData,isloading);
        }
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/sendmsgpreprocessbatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/sendmsgpreprocessbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/sendmsgpreprocessbatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/sendmsgpreprocessbatch`,tempData,isloading);
        }
        if(context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/sendmsgpreprocessbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/tasks/sendmsgpreprocessbatch`,tempData,isloading);
    }

    /**
     * StartBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async StartBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/startbatch`,tempData,isloading);
        }
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/startbatch`,tempData,isloading);
        }
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/startbatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/startbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/startbatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/startbatch`,tempData,isloading);
        }
        if(context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/startbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/tasks/startbatch`,tempData,isloading);
    }

    /**
     * TaskForwardBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async TaskForwardBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/taskforwardbatch`,tempData,isloading);
        }
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/taskforwardbatch`,tempData,isloading);
        }
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/taskforwardbatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/taskforwardbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/taskforwardbatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/taskforwardbatch`,tempData,isloading);
        }
        if(context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/taskforwardbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/tasks/taskforwardbatch`,tempData,isloading);
    }

    /**
     * UpdateRelatedPlanStatusBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TaskServiceBase
     */
    public async UpdateRelatedPlanStatusBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.project && context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/projectmodules/${context.projectmodule}/tasks/updaterelatedplanstatusbatch`,tempData,isloading);
        }
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/tasks/updaterelatedplanstatusbatch`,tempData,isloading);
        }
        if(context.product && context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/productplans/${context.productplan}/tasks/updaterelatedplanstatusbatch`,tempData,isloading);
        }
        if(context.project && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projects/${context.project}/tasks/updaterelatedplanstatusbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/tasks/updaterelatedplanstatusbatch`,tempData,isloading);
        }
        if(context.productplan && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/productplans/${context.productplan}/tasks/updaterelatedplanstatusbatch`,tempData,isloading);
        }
        if(context.projectmodule && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/projectmodules/${context.projectmodule}/tasks/updaterelatedplanstatusbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/tasks/updaterelatedplanstatusbatch`,tempData,isloading);
    }
}
