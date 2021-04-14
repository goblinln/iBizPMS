import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIbzCase, IbzCase } from '../../entities';
import keys from '../../entities/ibz-case/ibz-case-keys';

/**
 * 测试用例服务对象基类
 *
 * @export
 * @class IbzCaseBaseService
 * @extends {EntityBaseService}
 */
export class IbzCaseBaseService extends EntityBaseService<IIbzCase> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IbzCase';
    protected APPDENAMEPLURAL = 'IbzCases';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'title';
    protected quickSearchFields = ['title',];
    protected selectContextParam = {
        ibzlib: 'lib',
    };

    newEntity(data: IIbzCase): IbzCase {
        return new IbzCase(data);
    }

    async addLocal(context: IContext, entity: IIbzCase): Promise<IIbzCase | null> {
        return this.cache.add(context, new IbzCase(entity) as any);
    }

    async createLocal(context: IContext, entity: IIbzCase): Promise<IIbzCase | null> {
        return super.createLocal(context, new IbzCase(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIbzCase> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.lib && entity.lib !== '') {
            const s = await ___ibz___.gs.getIbzLibService();
            const data = await s.getLocal2(context, entity.lib);
            if (data) {
                entity.libname = data.name;
                entity.lib = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIbzCase): Promise<IIbzCase> {
        return super.updateLocal(context, new IbzCase(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIbzCase = {}): Promise<IIbzCase> {
        if (_context.ibzlib && _context.ibzlib !== '') {
            const s = await ___ibz___.gs.getIbzLibService();
            const data = await s.getLocal2(_context, _context.ibzlib);
            if (data) {
                entity.libname = data.name;
                entity.lib = data.id;
            }
        }
        return new IbzCase(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzCaseService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }
    /**
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzCaseService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzlib && _context.ibzcase) {
            return this.http.get(`/ibzlibs/${_context.ibzlib}/ibzcases/${_context.ibzcase}/select`);
        }
        return this.http.get(`/ibzcases/${_context.ibzcase}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzCaseService
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
            return this.http.post(`/ibzlibs/${_context.ibzlib}/ibzcases`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/ibzcases`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzCaseService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzlib && _context.ibzcase) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/ibzlibs/${_context.ibzlib}/ibzcases/${_context.ibzcase}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/ibzcases/${_context.ibzcase}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzCaseService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzlib && _context.ibzcase) {
            return this.http.delete(`/ibzlibs/${_context.ibzlib}/ibzcases/${_context.ibzcase}`);
        }
        return this.http.delete(`/ibzcases/${_context.ibzcase}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzCaseService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzlib && _context.ibzcase) {
            const res = await this.http.get(`/ibzlibs/${_context.ibzlib}/ibzcases/${_context.ibzcase}`);
        if (res.ok && res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
            return res;
        }
        const res = await this.http.get(`/ibzcases/${_context.ibzcase}`);
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
     * @memberof IbzCaseService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzlib && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/ibzlibs/${_context.ibzlib}/ibzcases/getdraft`, _data);
        if (res.ok && res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/ibzcases/getdraft`, _data);
        if (res.ok && res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
        return res;
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzCaseService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzlib && true) {
            return this.http.get(`/ibzlibs/${_context.ibzlib}/ibzcases/fetchdefault`, _data);
        }
        return this.http.get(`/ibzcases/fetchdefault`, _data);
    }
}