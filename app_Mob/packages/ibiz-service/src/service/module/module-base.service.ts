import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IModule, Module } from '../../entities';
import keys from '../../entities/module/module-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 模块服务对象基类
 *
 * @export
 * @class ModuleBaseService
 * @extends {EntityBaseService}
 */
export class ModuleBaseService extends EntityBaseService<IModule> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Mob';
    protected APPDENAME = 'Module';
    protected APPDENAMEPLURAL = 'Modules';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Mob/PSAPPDATAENTITIES/Module.json';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name',];
    protected selectContextParam = {
    };

    newEntity(data: IModule): Module {
        return new Module(data);
    }

    async addLocal(context: IContext, entity: IModule): Promise<IModule | null> {
        return this.cache.add(context, new Module(entity) as any);
    }

    async createLocal(context: IContext, entity: IModule): Promise<IModule | null> {
        return super.createLocal(context, new Module(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IModule> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IModule): Promise<IModule> {
        return super.updateLocal(context, new Module(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IModule = {}): Promise<IModule> {
        return new Module(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ModuleService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }

    protected getBugModuleCond() {
        return this.condCache.get('bugModule');
    }

    protected getBugModuleCodeListCond() {
        return this.condCache.get('bugModuleCodeList');
    }

    protected getDefaultCond() {
        return this.condCache.get('default');
    }

    protected getDocModuleCond() {
        if (!this.condCache.has('docModule')) {
            const strCond: any[] = ['AND', ['EQ', 'TYPE','doc'], ['EQ', 'ROOT',{ type: 'DATACONTEXT', value: 'product'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('docModule', cond);
            }
        }
        return this.condCache.get('docModule');
    }

    protected getLineCond() {
        if (!this.condCache.has('line')) {
            const strCond: any[] = ['AND', ['EQ', 'TYPE','line']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('line', cond);
            }
        }
        return this.condCache.get('line');
    }

    protected getStoryModuleCond() {
        if (!this.condCache.has('storyModule')) {
            const strCond: any[] = ['AND', ['EQ', 'ROOT',{ type: 'WEBCONTEXT', value: 'product'}], ['EQ', 'TYPE','story'], ['EQ', 'BRANCH',{ type: 'DATACONTEXT', value: 'branch'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('storyModule', cond);
            }
        }
        return this.condCache.get('storyModule');
    }

    protected getTaskModuleCond() {
        if (!this.condCache.has('taskModule')) {
            const strCond: any[] = ['AND', ['EQ', 'ROOT',{ type: 'DATACONTEXT', value: 'project'}], ['EQ', 'TYPE','task']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('taskModule', cond);
            }
        }
        return this.condCache.get('taskModule');
    }

    protected getViewCond() {
        return this.condCache.get('view');
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ModuleService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        const res = await this.http.post(`/modules`, _data);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * Fix
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ModuleService
     */
    async Fix(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/modules/${_context.module}/fix`, _data);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ModuleService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.get(`/modules/${_context.module}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ModuleService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/modules/getdraft`, _data);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ModuleService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.delete(`/modules/${_context.module}`);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ModuleService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
        const res = await this.http.put(`/modules/${_context.module}`, _data);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchBugModule
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ModuleService
     */
    async FetchBugModule(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/modules/fetchbugmodule`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchBugModule');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchBugModuleCodeList
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ModuleService
     */
    async FetchBugModuleCodeList(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/modules/fetchbugmodulecodelist`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchBugModuleCodeList');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ModuleService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/modules/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchDocModule
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ModuleService
     */
    async FetchDocModule(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/modules/fetchdocmodule`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDocModule');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchLine
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ModuleService
     */
    async FetchLine(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/modules/fetchline`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchLine');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchStoryModule
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ModuleService
     */
    async FetchStoryModule(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/modules/fetchstorymodule`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchStoryModule');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchTaskModule
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ModuleService
     */
    async FetchTaskModule(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.post(`/modules/fetchtaskmodule`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchTaskModule');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ModuleService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        const res = await this.http.get(`/modules/${_context.module}/select`);
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }

    /**
     * FixBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ModuleServiceBase
     */
    public async FixBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        _data = await this.obtainMinor(_context, _data);
        const res = await this.http.post(`/modules/fixbatch`,_data);
        return res;
    }
}
