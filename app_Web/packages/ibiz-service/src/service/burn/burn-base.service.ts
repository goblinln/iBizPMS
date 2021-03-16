import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IBurn, Burn } from '../../entities';
import keys from '../../entities/burn/burn-keys';

/**
 * burn服务对象基类
 *
 * @export
 * @class BurnBaseService
 * @extends {EntityBaseService}
 */
export class BurnBaseService extends EntityBaseService<IBurn> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'Burn';
    protected APPDENAMEPLURAL = 'Burns';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'date';
    protected quickSearchFields = ['date',];
    protected selectContextParam = {
        project: 'project',
    };

    async addLocal(context: IContext, entity: IBurn): Promise<IBurn | null> {
        return this.cache.add(context, new Burn(entity) as any);
    }

    async createLocal(context: IContext, entity: IBurn): Promise<IBurn | null> {
        return super.createLocal(context, new Burn(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IBurn> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.project && entity.project !== '') {
            const s = await ___ibz___.gs.getProjectService();
            const data = await s.getLocal2(context, entity.project);
            if (data) {
                entity.project = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IBurn): Promise<IBurn> {
        return super.updateLocal(context, new Burn(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IBurn = {}): Promise<IBurn> {
        if (_context.project && _context.project !== '') {
            const s = await ___ibz___.gs.getProjectService();
            const data = await s.getLocal2(_context, _context.project);
            if (data) {
                entity.project = data.id;
            }
        }
        return new Burn(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BurnService
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
     * @memberof BurnService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.burn) {
            return this.http.get(`/projects/${_context.project}/burns/${_context.burn}/select`);
        }
        return this.http.get(`/burns/${_context.burn}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BurnService
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
            return this.http.post(`/projects/${_context.project}/burns`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/burns`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BurnService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.burn) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/burns/${_context.burn}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/burns/${_context.burn}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BurnService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.burn) {
            return this.http.delete(`/projects/${_context.project}/burns/${_context.burn}`);
        }
        return this.http.delete(`/burns/${_context.burn}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BurnService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.burn) {
            const res = await this.http.get(`/projects/${_context.project}/burns/${_context.burn}`);
            return res;
        }
        const res = await this.http.get(`/burns/${_context.burn}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BurnService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/burns/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/burns/getdraft`, _data);
        return res;
    }
    /**
     * ComputeBurn
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BurnService
     */
    async ComputeBurn(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.burn) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/burns/${_context.burn}/computeburn`, _data);
        }
        return this.http.post(`/burns/${_context.burn}/computeburn`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BurnService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            return this.http.get(`/projects/${_context.project}/burns/fetchdefault`, _data);
        }
        return this.http.get(`/burns/fetchdefault`, _data);
    }
    /**
     * FetchESTIMATEANDLEFT
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BurnService
     */
    async FetchESTIMATEANDLEFT(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            return this.http.get(`/projects/${_context.project}/burns/fetchestimateandleft`, _data);
        }
        return this.http.get(`/burns/fetchestimateandleft`, _data);
    }
}
