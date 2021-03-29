import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIbzLibModule, IbzLibModule } from '../../entities';
import keys from '../../entities/ibz-lib-module/ibz-lib-module-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 用例库模块服务对象基类
 *
 * @export
 * @class IbzLibModuleBaseService
 * @extends {EntityBaseService}
 */
export class IbzLibModuleBaseService extends EntityBaseService<IIbzLibModule> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IbzLibModule';
    protected APPDENAMEPLURAL = 'IbzLibModules';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name',];
    protected selectContextParam = {
        ibzlib: 'root',
    };

    newEntity(data: IIbzLibModule): IbzLibModule {
        return new IbzLibModule(data);
    }

    async addLocal(context: IContext, entity: IIbzLibModule): Promise<IIbzLibModule | null> {
        return this.cache.add(context, new IbzLibModule(entity) as any);
    }

    async createLocal(context: IContext, entity: IIbzLibModule): Promise<IIbzLibModule | null> {
        return super.createLocal(context, new IbzLibModule(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIbzLibModule> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.root && entity.root !== '') {
            const s = await ___ibz___.gs.getIbzLibService();
            const data = await s.getLocal2(context, entity.root);
            if (data) {
                entity.root = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIbzLibModule): Promise<IIbzLibModule> {
        return super.updateLocal(context, new IbzLibModule(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIbzLibModule = {}): Promise<IIbzLibModule> {
        if (_context.ibzlib && _context.ibzlib !== '') {
            const s = await ___ibz___.gs.getIbzLibService();
            const data = await s.getLocal2(_context, _context.ibzlib);
            if (data) {
                entity.root = data.id;
            }
        }
        return new IbzLibModule(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzLibModuleService
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
            const strCond: any[] = ['AND', ['EQ', 'TYPE','caselib']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('default', cond);
            }
        }
        return this.condCache.get('default');
    }

    protected getRoot_NoBranchCond() {
        if (!this.condCache.has('root_NoBranch')) {
            const strCond: any[] = ['AND', ['EQ', 'TYPE','caselib'], ['OR', ['ISNULL', 'PARENT',''], ['EQ', 'PARENT','0']]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('root_NoBranch', cond);
            }
        }
        return this.condCache.get('root_NoBranch');
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
     * @memberof IbzLibModuleService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzlib && _context.ibzlibmodule) {
            return this.http.get(`/ibzlibs/${_context.ibzlib}/ibzlibmodules/${_context.ibzlibmodule}/select`);
        }
        return this.http.get(`/ibzlibmodules/${_context.ibzlibmodule}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzLibModuleService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzlib && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/ibzlibs/${_context.ibzlib}/ibzlibmodules`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibzlibmodules`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzLibModuleService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzlib && _context.ibzlibmodule) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/ibzlibs/${_context.ibzlib}/ibzlibmodules/${_context.ibzlibmodule}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibzlibmodules/${_context.ibzlibmodule}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzLibModuleService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzlib && _context.ibzlibmodule) {
            return this.http.delete(`/ibzlibs/${_context.ibzlib}/ibzlibmodules/${_context.ibzlibmodule}`);
        }
        return this.http.delete(`/ibzlibmodules/${_context.ibzlibmodule}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzLibModuleService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzlib && _context.ibzlibmodule) {
            const res = await this.http.get(`/ibzlibs/${_context.ibzlib}/ibzlibmodules/${_context.ibzlibmodule}`);
            return res;
        }
        const res = await this.http.get(`/ibzlibmodules/${_context.ibzlibmodule}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzLibModuleService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzlib && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/ibzlibs/${_context.ibzlib}/ibzlibmodules/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibzlibmodules/getdraft`, _data);
        return res;
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzLibModuleService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzlib && true) {
            return this.http.get(`/ibzlibs/${_context.ibzlib}/ibzlibmodules/fetchdefault`, _data);
        }
        return this.http.get(`/ibzlibmodules/fetchdefault`, _data);
    }
    /**
     * FetchRoot_NoBranch
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzLibModuleService
     */
    async FetchRoot_NoBranch(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzlib && true) {
            return this.http.get(`/ibzlibs/${_context.ibzlib}/ibzlibmodules/fetchroot_nobranch`, _data);
        }
        return this.http.get(`/ibzlibmodules/fetchroot_nobranch`, _data);
    }
}
