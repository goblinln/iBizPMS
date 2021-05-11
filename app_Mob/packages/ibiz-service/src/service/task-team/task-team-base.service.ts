import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ITaskTeam, TaskTeam } from '../../entities';
import keys from '../../entities/task-team/task-team-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 任务团队服务对象基类
 *
 * @export
 * @class TaskTeamBaseService
 * @extends {EntityBaseService}
 */
export class TaskTeamBaseService extends EntityBaseService<ITaskTeam> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Mob';
    protected APPDENAME = 'TaskTeam';
    protected APPDENAMEPLURAL = 'TaskTeams';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'account';
    protected quickSearchFields = ['account',];
    protected selectContextParam = {
        task: 'root',
    };

    newEntity(data: ITaskTeam): TaskTeam {
        return new TaskTeam(data);
    }

    async addLocal(context: IContext, entity: ITaskTeam): Promise<ITaskTeam | null> {
        return this.cache.add(context, new TaskTeam(entity) as any);
    }

    async createLocal(context: IContext, entity: ITaskTeam): Promise<ITaskTeam | null> {
        return super.createLocal(context, new TaskTeam(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ITaskTeam> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.root && entity.root !== '') {
            const s = await ___ibz___.gs.getTaskService();
            const data = await s.getLocal2(context, entity.root);
            if (data) {
                entity.root = data.tasksn;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: ITaskTeam): Promise<ITaskTeam> {
        return super.updateLocal(context, new TaskTeam(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ITaskTeam = {}): Promise<ITaskTeam> {
        if (_context.task && _context.task !== '') {
            const s = await ___ibz___.gs.getTaskService();
            const data = await s.getLocal2(_context, _context.task);
            if (data) {
                entity.root = data.tasksn;
            }
        }
        return new TaskTeam(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskTeamService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }

    protected getDefaultCond() {
        if (!this.condCache.has('default')) {
            const strCond: any[] = ['AND', ['EQ', 'TYPE','task']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('default', cond);
            }
        }
        return this.condCache.get('default');
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
     * @memberof TaskTeamService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && _context.taskteam) {
            return this.http.get(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/taskteams/${_context.taskteam}/select`);
        }
        if (_context.product && _context.story && _context.task && _context.taskteam) {
            return this.http.get(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/taskteams/${_context.taskteam}/select`);
        }
        if (_context.product && _context.productplan && _context.task && _context.taskteam) {
            return this.http.get(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/taskteams/${_context.taskteam}/select`);
        }
        if (_context.project && _context.task && _context.taskteam) {
            return this.http.get(`/projects/${_context.project}/tasks/${_context.task}/taskteams/${_context.taskteam}/select`);
        }
        if (_context.story && _context.task && _context.taskteam) {
            return this.http.get(`/stories/${_context.story}/tasks/${_context.task}/taskteams/${_context.taskteam}/select`);
        }
        if (_context.productplan && _context.task && _context.taskteam) {
            return this.http.get(`/productplans/${_context.productplan}/tasks/${_context.task}/taskteams/${_context.taskteam}/select`);
        }
        if (_context.projectmodule && _context.task && _context.taskteam) {
            return this.http.get(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/taskteams/${_context.taskteam}/select`);
        }
        if (_context.task && _context.taskteam) {
            return this.http.get(`/tasks/${_context.task}/taskteams/${_context.taskteam}/select`);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskTeamService
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
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/taskteams`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/taskteams`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/taskteams`, _data);
        }
        if (_context.project && _context.task && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/taskteams`, _data);
        }
        if (_context.story && _context.task && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/taskteams`, _data);
        }
        if (_context.productplan && _context.task && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/taskteams`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/taskteams`, _data);
        }
        if (_context.task && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/tasks/${_context.task}/taskteams`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskTeamService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && _context.taskteam) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/taskteams/${_context.taskteam}`, _data);
        }
        if (_context.product && _context.story && _context.task && _context.taskteam) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/taskteams/${_context.taskteam}`, _data);
        }
        if (_context.product && _context.productplan && _context.task && _context.taskteam) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/taskteams/${_context.taskteam}`, _data);
        }
        if (_context.project && _context.task && _context.taskteam) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/tasks/${_context.task}/taskteams/${_context.taskteam}`, _data);
        }
        if (_context.story && _context.task && _context.taskteam) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/stories/${_context.story}/tasks/${_context.task}/taskteams/${_context.taskteam}`, _data);
        }
        if (_context.productplan && _context.task && _context.taskteam) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/productplans/${_context.productplan}/tasks/${_context.task}/taskteams/${_context.taskteam}`, _data);
        }
        if (_context.projectmodule && _context.task && _context.taskteam) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/taskteams/${_context.taskteam}`, _data);
        }
        if (_context.task && _context.taskteam) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/tasks/${_context.task}/taskteams/${_context.taskteam}`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskTeamService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && _context.taskteam) {
            return this.http.delete(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/taskteams/${_context.taskteam}`);
        }
        if (_context.product && _context.story && _context.task && _context.taskteam) {
            return this.http.delete(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/taskteams/${_context.taskteam}`);
        }
        if (_context.product && _context.productplan && _context.task && _context.taskteam) {
            return this.http.delete(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/taskteams/${_context.taskteam}`);
        }
        if (_context.project && _context.task && _context.taskteam) {
            return this.http.delete(`/projects/${_context.project}/tasks/${_context.task}/taskteams/${_context.taskteam}`);
        }
        if (_context.story && _context.task && _context.taskteam) {
            return this.http.delete(`/stories/${_context.story}/tasks/${_context.task}/taskteams/${_context.taskteam}`);
        }
        if (_context.productplan && _context.task && _context.taskteam) {
            return this.http.delete(`/productplans/${_context.productplan}/tasks/${_context.task}/taskteams/${_context.taskteam}`);
        }
        if (_context.projectmodule && _context.task && _context.taskteam) {
            return this.http.delete(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/taskteams/${_context.taskteam}`);
        }
        if (_context.task && _context.taskteam) {
            return this.http.delete(`/tasks/${_context.task}/taskteams/${_context.taskteam}`);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskTeamService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && _context.taskteam) {
            const res = await this.http.get(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/taskteams/${_context.taskteam}`);
            return res;
        }
        if (_context.product && _context.story && _context.task && _context.taskteam) {
            const res = await this.http.get(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/taskteams/${_context.taskteam}`);
            return res;
        }
        if (_context.product && _context.productplan && _context.task && _context.taskteam) {
            const res = await this.http.get(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/taskteams/${_context.taskteam}`);
            return res;
        }
        if (_context.project && _context.task && _context.taskteam) {
            const res = await this.http.get(`/projects/${_context.project}/tasks/${_context.task}/taskteams/${_context.taskteam}`);
            return res;
        }
        if (_context.story && _context.task && _context.taskteam) {
            const res = await this.http.get(`/stories/${_context.story}/tasks/${_context.task}/taskteams/${_context.taskteam}`);
            return res;
        }
        if (_context.productplan && _context.task && _context.taskteam) {
            const res = await this.http.get(`/productplans/${_context.productplan}/tasks/${_context.task}/taskteams/${_context.taskteam}`);
            return res;
        }
        if (_context.projectmodule && _context.task && _context.taskteam) {
            const res = await this.http.get(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/taskteams/${_context.taskteam}`);
            return res;
        }
        if (_context.task && _context.taskteam) {
            const res = await this.http.get(`/tasks/${_context.task}/taskteams/${_context.taskteam}`);
            return res;
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskTeamService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/taskteams/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.story && _context.task && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/taskteams/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.productplan && _context.task && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/taskteams/getdraft`, _data);
            return res;
        }
        if (_context.project && _context.task && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/tasks/${_context.task}/taskteams/getdraft`, _data);
            return res;
        }
        if (_context.story && _context.task && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/stories/${_context.story}/tasks/${_context.task}/taskteams/getdraft`, _data);
            return res;
        }
        if (_context.productplan && _context.task && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/productplans/${_context.productplan}/tasks/${_context.task}/taskteams/getdraft`, _data);
            return res;
        }
        if (_context.projectmodule && _context.task && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/taskteams/getdraft`, _data);
            return res;
        }
        if (_context.task && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/tasks/${_context.task}/taskteams/getdraft`, _data);
            return res;
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TaskTeamService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/tasks/${_context.task}/taskteams/fetchdefault`, _data);
        }
        if (_context.product && _context.story && _context.task && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/tasks/${_context.task}/taskteams/fetchdefault`, _data);
        }
        if (_context.product && _context.productplan && _context.task && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/tasks/${_context.task}/taskteams/fetchdefault`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/taskteams/fetchdefault`, _data);
        }
        if (_context.story && _context.task && true) {
            return this.http.post(`/stories/${_context.story}/tasks/${_context.task}/taskteams/fetchdefault`, _data);
        }
        if (_context.productplan && _context.task && true) {
            return this.http.post(`/productplans/${_context.productplan}/tasks/${_context.task}/taskteams/fetchdefault`, _data);
        }
        if (_context.projectmodule && _context.task && true) {
            return this.http.post(`/projectmodules/${_context.projectmodule}/tasks/${_context.task}/taskteams/fetchdefault`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/taskteams/fetchdefault`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
}
