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
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ProjectModuleService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/projectmodules/fetchdefault`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
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
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
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
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
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
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
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
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
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
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
}
