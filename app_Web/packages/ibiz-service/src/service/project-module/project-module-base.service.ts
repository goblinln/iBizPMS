import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IProjectModule, ProjectModule } from '../../entities';
import keys from '../../entities/project-module/project-module-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 任务模块服务对象基类
 *
 * @export
 * @class ProjectModuleBaseService
 * @extends {EntityBaseService}
 */
export class ProjectModuleBaseService extends EntityBaseService<IProjectModule> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'ProjectModule';
    protected APPDENAMEPLURAL = 'ProjectModules';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name',];
    protected selectContextParam = {
        project: 'root',
    };

    newEntity(data: IProjectModule): ProjectModule {
        return new ProjectModule(data);
    }

    async addLocal(context: IContext, entity: IProjectModule): Promise<IProjectModule | null> {
        return this.cache.add(context, new ProjectModule(entity) as any);
    }

    async createLocal(context: IContext, entity: IProjectModule): Promise<IProjectModule | null> {
        return super.createLocal(context, new ProjectModule(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IProjectModule> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.root && entity.root !== '') {
            const s = await ___ibz___.gs.getProjectService();
            const data = await s.getLocal2(context, entity.root);
            if (data) {
                entity.rootname = data.name;
                entity.root = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IProjectModule): Promise<IProjectModule> {
        return super.updateLocal(context, new ProjectModule(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IProjectModule = {}): Promise<IProjectModule> {
        if (_context.project && _context.project !== '') {
            const s = await ___ibz___.gs.getProjectService();
            const data = await s.getLocal2(_context, _context.project);
            if (data) {
                entity.rootname = data.name;
                entity.root = data.id;
            }
        }
        return new ProjectModule(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectModuleService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }

    protected getByPathCond() {
        return this.condCache.get('byPath');
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

    protected getParentModuleCond() {
        return this.condCache.get('parentModule');
    }

    protected getRootCond() {
        if (!this.condCache.has('root')) {
            const strCond: any[] = ['AND', ['EQ', 'TYPE','story'], ['OR', ['ISNULL', 'PARENT',''], ['EQ', 'PARENT','0']]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('root', cond);
            }
        }
        return this.condCache.get('root');
    }

    protected getRoot_NoBranchCond() {
        if (!this.condCache.has('root_NoBranch')) {
            const strCond: any[] = ['AND', ['EQ', 'TYPE','story'], ['EQ', 'BRANCH','0'], ['OR', ['ISNULL', 'PARENT',''], ['EQ', 'PARENT','0']]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('root_NoBranch', cond);
            }
        }
        return this.condCache.get('root_NoBranch');
    }

    protected getRoot_TaskCond() {
        if (!this.condCache.has('root_Task')) {
            const strCond: any[] = ['AND', ['EQ', 'TYPE','task'], ['OR', ['ISNULL', 'PARENT',''], ['EQ', 'PARENT','0']]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('root_Task', cond);
            }
        }
        return this.condCache.get('root_Task');
    }

    protected getTaskModulesCond() {
        if (!this.condCache.has('taskModules')) {
            const strCond: any[] = ['AND', ['OR', ['EQ', 'ROOT',{ type: 'WEBCONTEXT', value: 'project'}]]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('taskModules', cond);
            }
        }
        return this.condCache.get('taskModules');
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
     * @memberof ProjectModuleService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule) {
            return this.http.get(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/select`);
        }
        return this.http.get(`/projectmodules/${_context.projectmodule}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectModuleService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projects/${_context.project}/projectmodules`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/projectmodules`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectModuleService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/projectmodules/${_context.projectmodule}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/projectmodules/${_context.projectmodule}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectModuleService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule) {
            return this.http.delete(`/projects/${_context.project}/projectmodules/${_context.projectmodule}`);
        }
        return this.http.delete(`/projectmodules/${_context.projectmodule}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectModuleService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule) {
            const res = await this.http.get(`/projects/${_context.project}/projectmodules/${_context.projectmodule}`);
            return res;
        }
        const res = await this.http.get(`/projectmodules/${_context.projectmodule}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectModuleService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/projectmodules/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/projectmodules/getdraft`, _data);
        return res;
    }
    /**
     * Fix
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectModuleService
     */
    async Fix(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/fix`, _data);
        }
        return this.http.post(`/projectmodules/${_context.projectmodule}/fix`, _data);
    }
    /**
     * RemoveModule
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectModuleService
     */
    async RemoveModule(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.projectmodule) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/projectmodules/${_context.projectmodule}/removemodule`, _data);
        }
        return this.http.put(`/projectmodules/${_context.projectmodule}/removemodule`, _data);
    }
    /**
     * FetchByPath
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectModuleService
     */
    async FetchByPath(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            return this.http.get(`/projects/${_context.project}/projectmodules/fetchbypath`, _data);
        }
        return this.http.get(`/projectmodules/fetchbypath`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectModuleService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            return this.http.get(`/projects/${_context.project}/projectmodules/fetchdefault`, _data);
        }
        return this.http.get(`/projectmodules/fetchdefault`, _data);
    }
    /**
     * FetchParentModule
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectModuleService
     */
    async FetchParentModule(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            return this.http.get(`/projects/${_context.project}/projectmodules/fetchparentmodule`, _data);
        }
        return this.http.get(`/projectmodules/fetchparentmodule`, _data);
    }
    /**
     * FetchRoot
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectModuleService
     */
    async FetchRoot(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            return this.http.get(`/projects/${_context.project}/projectmodules/fetchroot`, _data);
        }
        return this.http.get(`/projectmodules/fetchroot`, _data);
    }
    /**
     * FetchRoot_NoBranch
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectModuleService
     */
    async FetchRoot_NoBranch(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            return this.http.get(`/projects/${_context.project}/projectmodules/fetchroot_nobranch`, _data);
        }
        return this.http.get(`/projectmodules/fetchroot_nobranch`, _data);
    }
    /**
     * FetchRoot_Task
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectModuleService
     */
    async FetchRoot_Task(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            return this.http.get(`/projects/${_context.project}/projectmodules/fetchroot_task`, _data);
        }
        return this.http.get(`/projectmodules/fetchroot_task`, _data);
    }
    /**
     * FetchTaskModules
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectModuleService
     */
    async FetchTaskModules(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            return this.http.get(`/projects/${_context.project}/projectmodules/fetchtaskmodules`, _data);
        }
        return this.http.get(`/projectmodules/fetchtaskmodules`, _data);
    }
}
