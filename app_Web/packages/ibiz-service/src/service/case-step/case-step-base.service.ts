import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ICaseStep, CaseStep } from '../../entities';
import keys from '../../entities/case-step/case-step-keys';

/**
 * 用例步骤服务对象基类
 *
 * @export
 * @class CaseStepBaseService
 * @extends {EntityBaseService}
 */
export class CaseStepBaseService extends EntityBaseService<ICaseStep> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'CaseStep';
    protected APPDENAMEPLURAL = 'CaseSteps';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'expect';
    protected quickSearchFields = ['expect',];
    protected selectContextParam = {
        case: 'ibizcase',
    };

    newEntity(data: ICaseStep): CaseStep {
        return new CaseStep(data);
    }

    async addLocal(context: IContext, entity: ICaseStep): Promise<ICaseStep | null> {
        return this.cache.add(context, new CaseStep(entity) as any);
    }

    async createLocal(context: IContext, entity: ICaseStep): Promise<ICaseStep | null> {
        return super.createLocal(context, new CaseStep(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ICaseStep> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.ibizcase && entity.ibizcase !== '') {
            const s = await ___ibz___.gs.getCaseService();
            const data = await s.getLocal2(context, entity.ibizcase);
            if (data) {
                entity.ibizcase = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: ICaseStep): Promise<ICaseStep> {
        return super.updateLocal(context, new CaseStep(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ICaseStep = {}): Promise<ICaseStep> {
        if (_context.case && _context.case !== '') {
            const s = await ___ibz___.gs.getCaseService();
            const data = await s.getLocal2(_context, _context.case);
            if (data) {
                entity.ibizcase = data.id;
            }
        }
        return new CaseStep(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseStepService
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
     * @memberof CaseStepService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case && _context.casestep) {
            return this.http.get(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/casesteps/${_context.casestep}/select`);
        }
        if (_context.story && _context.case && _context.casestep) {
            return this.http.get(`/stories/${_context.story}/cases/${_context.case}/casesteps/${_context.casestep}/select`);
        }
        if (_context.product && _context.case && _context.casestep) {
            return this.http.get(`/products/${_context.product}/cases/${_context.case}/casesteps/${_context.casestep}/select`);
        }
        if (_context.case && _context.casestep) {
            return this.http.get(`/cases/${_context.case}/casesteps/${_context.casestep}/select`);
        }
        return this.http.get(`/casesteps/${_context.casestep}/select`);
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseStepService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/casesteps`, _data);
        }
        if (_context.story && _context.case && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/casesteps`, _data);
        }
        if (_context.product && _context.case && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/casesteps`, _data);
        }
        if (_context.case && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/cases/${_context.case}/casesteps`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/casesteps`, _data);
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseStepService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case && _context.casestep) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/casesteps/${_context.casestep}`, _data);
        }
        if (_context.story && _context.case && _context.casestep) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/stories/${_context.story}/cases/${_context.case}/casesteps/${_context.casestep}`, _data);
        }
        if (_context.product && _context.case && _context.casestep) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/cases/${_context.case}/casesteps/${_context.casestep}`, _data);
        }
        if (_context.case && _context.casestep) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/cases/${_context.case}/casesteps/${_context.casestep}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/casesteps/${_context.casestep}`, _data);
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseStepService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case && _context.casestep) {
            return this.http.delete(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/casesteps/${_context.casestep}`);
        }
        if (_context.story && _context.case && _context.casestep) {
            return this.http.delete(`/stories/${_context.story}/cases/${_context.case}/casesteps/${_context.casestep}`);
        }
        if (_context.product && _context.case && _context.casestep) {
            return this.http.delete(`/products/${_context.product}/cases/${_context.case}/casesteps/${_context.casestep}`);
        }
        if (_context.case && _context.casestep) {
            return this.http.delete(`/cases/${_context.case}/casesteps/${_context.casestep}`);
        }
        return this.http.delete(`/casesteps/${_context.casestep}`);
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseStepService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case && _context.casestep) {
            const res = await this.http.get(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/casesteps/${_context.casestep}`);
            return res;
        }
        if (_context.story && _context.case && _context.casestep) {
            const res = await this.http.get(`/stories/${_context.story}/cases/${_context.case}/casesteps/${_context.casestep}`);
            return res;
        }
        if (_context.product && _context.case && _context.casestep) {
            const res = await this.http.get(`/products/${_context.product}/cases/${_context.case}/casesteps/${_context.casestep}`);
            return res;
        }
        if (_context.case && _context.casestep) {
            const res = await this.http.get(`/cases/${_context.case}/casesteps/${_context.casestep}`);
            return res;
        }
        const res = await this.http.get(`/casesteps/${_context.casestep}`);
        return res;
    }
    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseStepService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/casesteps/getdraft`, _data);
            return res;
        }
        if (_context.story && _context.case && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/stories/${_context.story}/cases/${_context.case}/casesteps/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.case && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/cases/${_context.case}/casesteps/getdraft`, _data);
            return res;
        }
        if (_context.case && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/cases/${_context.case}/casesteps/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/casesteps/getdraft`, _data);
        return res;
    }
    /**
     * FetchCurTest
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseStepService
     */
    async FetchCurTest(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/casesteps/fetchcurtest`, _data);
        }
        if (_context.story && _context.case && true) {
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/casesteps/fetchcurtest`, _data);
        }
        if (_context.product && _context.case && true) {
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/casesteps/fetchcurtest`, _data);
        }
        if (_context.case && true) {
            return this.http.post(`/cases/${_context.case}/casesteps/fetchcurtest`, _data);
        }
        return this.http.post(`/casesteps/fetchcurtest`, _data);
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseStepService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/casesteps/fetchdefault`, _data);
        }
        if (_context.story && _context.case && true) {
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/casesteps/fetchdefault`, _data);
        }
        if (_context.product && _context.case && true) {
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/casesteps/fetchdefault`, _data);
        }
        if (_context.case && true) {
            return this.http.post(`/cases/${_context.case}/casesteps/fetchdefault`, _data);
        }
        return this.http.post(`/casesteps/fetchdefault`, _data);
    }
    /**
     * FetchDefault1
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseStepService
     */
    async FetchDefault1(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/casesteps/fetchdefault1`, _data);
        }
        if (_context.story && _context.case && true) {
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/casesteps/fetchdefault1`, _data);
        }
        if (_context.product && _context.case && true) {
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/casesteps/fetchdefault1`, _data);
        }
        if (_context.case && true) {
            return this.http.post(`/cases/${_context.case}/casesteps/fetchdefault1`, _data);
        }
        return this.http.post(`/casesteps/fetchdefault1`, _data);
    }
    /**
     * FetchMob
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseStepService
     */
    async FetchMob(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/casesteps/fetchmob`, _data);
        }
        if (_context.story && _context.case && true) {
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/casesteps/fetchmob`, _data);
        }
        if (_context.product && _context.case && true) {
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/casesteps/fetchmob`, _data);
        }
        if (_context.case && true) {
            return this.http.post(`/cases/${_context.case}/casesteps/fetchmob`, _data);
        }
        return this.http.post(`/casesteps/fetchmob`, _data);
    }
    /**
     * FetchVersion
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseStepService
     */
    async FetchVersion(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/casesteps/fetchversion`, _data);
        }
        if (_context.story && _context.case && true) {
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/casesteps/fetchversion`, _data);
        }
        if (_context.product && _context.case && true) {
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/casesteps/fetchversion`, _data);
        }
        if (_context.case && true) {
            return this.http.post(`/cases/${_context.case}/casesteps/fetchversion`, _data);
        }
        return this.http.post(`/casesteps/fetchversion`, _data);
    }
    /**
     * FetchVersions
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseStepService
     */
    async FetchVersions(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/casesteps/fetchversions`, _data);
        }
        if (_context.story && _context.case && true) {
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/casesteps/fetchversions`, _data);
        }
        if (_context.product && _context.case && true) {
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/casesteps/fetchversions`, _data);
        }
        if (_context.case && true) {
            return this.http.post(`/cases/${_context.case}/casesteps/fetchversions`, _data);
        }
        return this.http.post(`/casesteps/fetchversions`, _data);
    }
}
