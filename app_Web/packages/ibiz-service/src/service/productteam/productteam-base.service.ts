import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IPRODUCTTEAM, PRODUCTTEAM } from '../../entities';
import keys from '../../entities/productteam/productteam-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 产品团队服务对象基类
 *
 * @export
 * @class PRODUCTTEAMBaseService
 * @extends {EntityBaseService}
 */
export class PRODUCTTEAMBaseService extends EntityBaseService<IPRODUCTTEAM> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'PRODUCTTEAM';
    protected APPDENAMEPLURAL = 'PRODUCTTEAMs';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'account';
    protected quickSearchFields = ['account',];
    protected selectContextParam = {
        product: 'root',
    };

    newEntity(data: IPRODUCTTEAM): PRODUCTTEAM {
        return new PRODUCTTEAM(data);
    }

    async addLocal(context: IContext, entity: IPRODUCTTEAM): Promise<IPRODUCTTEAM | null> {
        return this.cache.add(context, new PRODUCTTEAM(entity) as any);
    }

    async createLocal(context: IContext, entity: IPRODUCTTEAM): Promise<IPRODUCTTEAM | null> {
        return super.createLocal(context, new PRODUCTTEAM(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IPRODUCTTEAM> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.root && entity.root !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(context, entity.root);
            if (data) {
                entity.root = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IPRODUCTTEAM): Promise<IPRODUCTTEAM> {
        return super.updateLocal(context, new PRODUCTTEAM(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IPRODUCTTEAM = {}): Promise<IPRODUCTTEAM> {
        if (_context.product && _context.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(_context, _context.product);
            if (data) {
                entity.root = data.id;
            }
        }
        return new PRODUCTTEAM(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof PRODUCTTEAMService
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
            const strCond: any[] = ['AND', ['EQ', 'TYPE','product']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('default', cond);
            }
        }
        return this.condCache.get('default');
    }

    protected getProductTeamInfoCond() {
        return this.condCache.get('productTeamInfo');
    }

    protected getProjectAppCond() {
        if (!this.condCache.has('projectApp')) {
            const strCond: any[] = ['AND', ['EQ', 'ROOT',{ type: 'WEBCONTEXT', value: 'product'}], ['EQ', 'TYPE','product']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('projectApp', cond);
            }
        }
        return this.condCache.get('projectApp');
    }

    protected getRowEditDefaultProductTeamCond() {
        return this.condCache.get('rowEditDefaultProductTeam');
    }

    protected getSpecifyTeamCond() {
        return this.condCache.get('specifyTeam');
    }

    protected getViewCond() {
        return this.condCache.get('view');
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof PRODUCTTEAMService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/productteams/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/productteams/getdraft`, _data);
        return res;
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof PRODUCTTEAMService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productteam) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/productteams/${_context.productteam}/update`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/productteams/${_context.productteam}/update`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof PRODUCTTEAMService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productteam) {
            return this.http.delete(`/products/${_context.product}/productteams/${_context.productteam}/remove`);
        }
        return this.http.delete(`/productteams/${_context.productteam}/remove`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof PRODUCTTEAMService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.productteam) {
            const res = await this.http.get(`/products/${_context.product}/productteams/${_context.productteam}/get`);
            return res;
        }
        const res = await this.http.get(`/productteams/${_context.productteam}/get`);
        return res;
    }
    /**
     * FetchProductTeamInfo
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof PRODUCTTEAMService
     */
    async FetchProductTeamInfo(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/productteams/fetchproductteaminfo`, _data);
        }
        return this.http.post(`/productteams/fetchproductteaminfo`, _data);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof PRODUCTTEAMService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/productteams`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/productteams`, _data);
    }
    /**
     * FetchSpecifyTeam
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof PRODUCTTEAMService
     */
    async FetchSpecifyTeam(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/productteams/fetchspecifyteam`, _data);
        }
        return this.http.post(`/productteams/fetchspecifyteam`, _data);
    }
    /**
     * FetchRowEditDefaultProductTeam
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof PRODUCTTEAMService
     */
    async FetchRowEditDefaultProductTeam(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/productteams/fetchroweditdefaultproductteam`, _data);
        }
        return this.http.post(`/productteams/fetchroweditdefaultproductteam`, _data);
    }
}
