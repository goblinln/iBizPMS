import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IIbzLibCasesteps, IbzLibCasesteps } from '../../entities';
import keys from '../../entities/ibz-lib-casesteps/ibz-lib-casesteps-keys';

/**
 * 用例库用例步骤服务对象基类
 *
 * @export
 * @class IbzLibCasestepsBaseService
 * @extends {EntityBaseService}
 */
export class IbzLibCasestepsBaseService extends EntityBaseService<IIbzLibCasesteps> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'IbzLibCasesteps';
    protected APPDENAMEPLURAL = 'IbzLibCasesteps';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'expect';
    protected quickSearchFields = ['expect',];
    protected selectContextParam = {
        ibzcase: 'ibizcase',
    };

    newEntity(data: IIbzLibCasesteps): IbzLibCasesteps {
        return new IbzLibCasesteps(data);
    }

    async addLocal(context: IContext, entity: IIbzLibCasesteps): Promise<IIbzLibCasesteps | null> {
        return this.cache.add(context, new IbzLibCasesteps(entity) as any);
    }

    async createLocal(context: IContext, entity: IIbzLibCasesteps): Promise<IIbzLibCasesteps | null> {
        return super.createLocal(context, new IbzLibCasesteps(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IIbzLibCasesteps> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.ibizcase && entity.ibizcase !== '') {
            const s = await ___ibz___.gs.getIbzCaseService();
            const data = await s.getLocal2(context, entity.ibizcase);
            if (data) {
                entity.ibizcase = data.id;
                entity.ibzcase = data;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IIbzLibCasesteps): Promise<IIbzLibCasesteps> {
        return super.updateLocal(context, new IbzLibCasesteps(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IIbzLibCasesteps = {}): Promise<IIbzLibCasesteps> {
        if (_context.ibzcase && _context.ibzcase !== '') {
            const s = await ___ibz___.gs.getIbzCaseService();
            const data = await s.getLocal2(_context, _context.ibzcase);
            if (data) {
                entity.ibizcase = data.id;
                entity.ibzcase = data;
            }
        }
        return new IbzLibCasesteps(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzLibCasestepsService
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
     * @memberof IbzLibCasestepsService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzlib && _context.ibzcase && _context.ibzlibcasesteps) {
            return this.http.get(`/ibzlibs/${_context.ibzlib}/ibzcases/${_context.ibzcase}/ibzlibcasesteps/${_context.ibzlibcasesteps}/select`);
        }
        if (_context.ibzcase && _context.ibzlibcasesteps) {
            return this.http.get(`/ibzcases/${_context.ibzcase}/ibzlibcasesteps/${_context.ibzlibcasesteps}/select`);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzLibCasestepsService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzlib && _context.ibzcase && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/ibzlibs/${_context.ibzlib}/ibzcases/${_context.ibzcase}/ibzlibcasesteps`, _data);
        }
        if (_context.ibzcase && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/ibzcases/${_context.ibzcase}/ibzlibcasesteps`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzLibCasestepsService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzlib && _context.ibzcase && _context.ibzlibcasesteps) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/ibzlibs/${_context.ibzlib}/ibzcases/${_context.ibzcase}/ibzlibcasesteps/${_context.ibzlibcasesteps}`, _data);
        }
        if (_context.ibzcase && _context.ibzlibcasesteps) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/ibzcases/${_context.ibzcase}/ibzlibcasesteps/${_context.ibzlibcasesteps}`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzLibCasestepsService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzlib && _context.ibzcase && _context.ibzlibcasesteps) {
            return this.http.delete(`/ibzlibs/${_context.ibzlib}/ibzcases/${_context.ibzcase}/ibzlibcasesteps/${_context.ibzlibcasesteps}`);
        }
        if (_context.ibzcase && _context.ibzlibcasesteps) {
            return this.http.delete(`/ibzcases/${_context.ibzcase}/ibzlibcasesteps/${_context.ibzlibcasesteps}`);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof IbzLibCasestepsService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzlib && _context.ibzcase && _context.ibzlibcasesteps) {
            const res = await this.http.get(`/ibzlibs/${_context.ibzlib}/ibzcases/${_context.ibzcase}/ibzlibcasesteps/${_context.ibzlibcasesteps}`);
            return res;
        }
        if (_context.ibzcase && _context.ibzlibcasesteps) {
            const res = await this.http.get(`/ibzcases/${_context.ibzcase}/ibzlibcasesteps/${_context.ibzlibcasesteps}`);
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
     * @memberof IbzLibCasestepsService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzlib && _context.ibzcase && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/ibzlibs/${_context.ibzlib}/ibzcases/${_context.ibzcase}/ibzlibcasesteps/getdraft`, _data);
            return res;
        }
        if (_context.ibzcase && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/ibzcases/${_context.ibzcase}/ibzlibcasesteps/getdraft`, _data);
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
     * @memberof IbzLibCasestepsService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.ibzlib && _context.ibzcase && true) {
            return this.http.get(`/ibzlibs/${_context.ibzlib}/ibzcases/${_context.ibzcase}/ibzlibcasesteps/fetchdefault`, _data);
        }
        if (_context.ibzcase && true) {
            return this.http.get(`/ibzcases/${_context.ibzcase}/ibzlibcasesteps/fetchdefault`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
}
