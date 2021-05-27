import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IAction, Action } from '../../entities';
import keys from '../../entities/action/action-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 系统日志服务对象基类
 *
 * @export
 * @class ActionBaseService
 * @extends {EntityBaseService}
 */
export class ActionBaseService extends EntityBaseService<IAction> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Mob';
    protected APPDENAME = 'Action';
    protected APPDENAMEPLURAL = 'Actions';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'comment';
    protected quickSearchFields = ['actor',];
    protected selectContextParam = {
    };

    newEntity(data: IAction): Action {
        return new Action(data);
    }

    async addLocal(context: IContext, entity: IAction): Promise<IAction | null> {
        return this.cache.add(context, new Action(entity) as any);
    }

    async createLocal(context: IContext, entity: IAction): Promise<IAction | null> {
        return super.createLocal(context, new Action(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IAction> {
        const entity = this.cache.get(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IAction): Promise<IAction> {
        return super.updateLocal(context, new Action(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IAction = {}): Promise<IAction> {
        return new Action(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ActionService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }

    protected getBianGengLineHistoryCond() {
        return this.condCache.get('bianGengLineHistory');
    }

    protected getDefaultCond() {
        return this.condCache.get('default');
    }

    protected getMobTypeCond() {
        if (!this.condCache.has('mobType')) {
            const strCond: any[] = ['AND', ['EQ', 'OBJECTID',{ type: 'DATACONTEXT', value: 'srfparentkey'}], ['EQ', 'OBJECTTYPE',{ type: 'DATACONTEXT', value: 'objecttype'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('mobType', cond);
            }
        }
        return this.condCache.get('mobType');
    }

    protected getMyActionCond() {
        if (!this.condCache.has('myAction')) {
            const strCond: any[] = ['AND', ['EQ', 'ACTOR',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('myAction', cond);
            }
        }
        return this.condCache.get('myAction');
    }

    protected getMyTrendsCond() {
        return this.condCache.get('myTrends');
    }

    protected getProductTrendsCond() {
        return this.condCache.get('productTrends');
    }

    protected getProjectTrendsCond() {
        return this.condCache.get('projectTrends');
    }

    protected getQueryUserYEARCond() {
        return this.condCache.get('queryUserYEAR');
    }

    protected getTypeCond() {
        if (!this.condCache.has('type')) {
            const strCond: any[] = ['AND', ['EQ', 'OBJECTID',{ type: 'DATACONTEXT', value: 'srfparentkey'}], ['EQ', 'OBJECTTYPE',{ type: 'DATACONTEXT', value: 'objecttype'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('type', cond);
            }
        }
        return this.condCache.get('type');
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
     * @memberof ActionService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.action) {
            return this.http.get(`/products/${_context.product}/stories/${_context.story}/actions/${_context.action}/select`);
        }
        if (_context.project && _context.action) {
            return this.http.get(`/projects/${_context.project}/actions/${_context.action}/select`);
        }
        if (_context.story && _context.action) {
            return this.http.get(`/stories/${_context.story}/actions/${_context.action}/select`);
        }
        if (_context.product && _context.action) {
            return this.http.get(`/products/${_context.product}/actions/${_context.action}/select`);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ActionService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions`, _data);
        }
        if (_context.project && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projects/${_context.project}/actions`, _data);
        }
        if (_context.story && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/stories/${_context.story}/actions`, _data);
        }
        if (_context.product && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/actions`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ActionService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/stories/${_context.story}/actions/${_context.action}`, _data);
        }
        if (_context.project && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/actions/${_context.action}`, _data);
        }
        if (_context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/stories/${_context.story}/actions/${_context.action}`, _data);
        }
        if (_context.product && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/actions/${_context.action}`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ActionService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.action) {
            return this.http.delete(`/products/${_context.product}/stories/${_context.story}/actions/${_context.action}`);
        }
        if (_context.project && _context.action) {
            return this.http.delete(`/projects/${_context.project}/actions/${_context.action}`);
        }
        if (_context.story && _context.action) {
            return this.http.delete(`/stories/${_context.story}/actions/${_context.action}`);
        }
        if (_context.product && _context.action) {
            return this.http.delete(`/products/${_context.product}/actions/${_context.action}`);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Get
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ActionService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.action) {
            const res = await this.http.get(`/products/${_context.product}/stories/${_context.story}/actions/${_context.action}`);
            return res;
        }
        if (_context.project && _context.action) {
            const res = await this.http.get(`/projects/${_context.project}/actions/${_context.action}`);
            return res;
        }
        if (_context.story && _context.action) {
            const res = await this.http.get(`/stories/${_context.story}/actions/${_context.action}`);
            return res;
        }
        if (_context.product && _context.action) {
            const res = await this.http.get(`/products/${_context.product}/actions/${_context.action}`);
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
     * @memberof ActionService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/stories/${_context.story}/actions/getdraft`, _data);
            return res;
        }
        if (_context.project && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/actions/getdraft`, _data);
            return res;
        }
        if (_context.story && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/stories/${_context.story}/actions/getdraft`, _data);
            return res;
        }
        if (_context.product && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/actions/getdraft`, _data);
            return res;
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * Comment
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ActionService
     */
    async Comment(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/${_context.action}/comment`, _data);
        }
        if (_context.project && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/actions/${_context.action}/comment`, _data);
        }
        if (_context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/actions/${_context.action}/comment`, _data);
        }
        if (_context.product && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/actions/${_context.action}/comment`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * CreateHis
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ActionService
     */
    async CreateHis(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/${_context.action}/createhis`, _data);
        }
        if (_context.project && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/actions/${_context.action}/createhis`, _data);
        }
        if (_context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/actions/${_context.action}/createhis`, _data);
        }
        if (_context.product && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/actions/${_context.action}/createhis`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * EditComment
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ActionService
     */
    async EditComment(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/${_context.action}/editcomment`, _data);
        }
        if (_context.project && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/actions/${_context.action}/editcomment`, _data);
        }
        if (_context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/actions/${_context.action}/editcomment`, _data);
        }
        if (_context.product && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/actions/${_context.action}/editcomment`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * ManagePmsEe
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ActionService
     */
    async ManagePmsEe(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/${_context.action}/managepmsee`, _data);
        }
        if (_context.project && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/actions/${_context.action}/managepmsee`, _data);
        }
        if (_context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/actions/${_context.action}/managepmsee`, _data);
        }
        if (_context.product && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/actions/${_context.action}/managepmsee`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * SendMarkDone
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ActionService
     */
    async SendMarkDone(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/${_context.action}/sendmarkdone`, _data);
        }
        if (_context.project && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/actions/${_context.action}/sendmarkdone`, _data);
        }
        if (_context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/actions/${_context.action}/sendmarkdone`, _data);
        }
        if (_context.product && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/actions/${_context.action}/sendmarkdone`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * SendTodo
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ActionService
     */
    async SendTodo(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/${_context.action}/sendtodo`, _data);
        }
        if (_context.project && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/actions/${_context.action}/sendtodo`, _data);
        }
        if (_context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/actions/${_context.action}/sendtodo`, _data);
        }
        if (_context.product && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/actions/${_context.action}/sendtodo`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * SendToread
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ActionService
     */
    async SendToread(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/${_context.action}/sendtoread`, _data);
        }
        if (_context.project && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/actions/${_context.action}/sendtoread`, _data);
        }
        if (_context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/actions/${_context.action}/sendtoread`, _data);
        }
        if (_context.product && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/actions/${_context.action}/sendtoread`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ActionService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/fetchdefault`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/actions/fetchdefault`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/actions/fetchdefault`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/actions/fetchdefault`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * FetchMobType
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ActionService
     */
    async FetchMobType(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/fetchmobtype`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/actions/fetchmobtype`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/actions/fetchmobtype`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/actions/fetchmobtype`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * FetchMyTrends
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ActionService
     */
    async FetchMyTrends(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/fetchmytrends`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/actions/fetchmytrends`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/actions/fetchmytrends`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/actions/fetchmytrends`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * FetchProductTrends
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ActionService
     */
    async FetchProductTrends(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/fetchproducttrends`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/actions/fetchproducttrends`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/actions/fetchproducttrends`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/actions/fetchproducttrends`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * FetchProjectTrends
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ActionService
     */
    async FetchProjectTrends(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/fetchprojecttrends`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/actions/fetchprojecttrends`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/actions/fetchprojecttrends`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/actions/fetchprojecttrends`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * FetchQueryUserYEAR
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ActionService
     */
    async FetchQueryUserYEAR(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/fetchqueryuseryear`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/actions/fetchqueryuseryear`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/actions/fetchqueryuseryear`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/actions/fetchqueryuseryear`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * FetchType
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ActionService
     */
    async FetchType(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/fetchtype`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/actions/fetchtype`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/actions/fetchtype`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/actions/fetchtype`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * CreateHisBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ActionServiceBase
     */
    public async CreateHisBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/createhisbatch`,_data);
        }
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/actions/createhisbatch`,_data);
        }
        if(_context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/actions/createhisbatch`,_data);
        }
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/actions/createhisbatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * EditCommentBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ActionServiceBase
     */
    public async EditCommentBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/editcommentbatch`,_data);
        }
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/actions/editcommentbatch`,_data);
        }
        if(_context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/actions/editcommentbatch`,_data);
        }
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/actions/editcommentbatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * ManagePmsEeBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ActionServiceBase
     */
    public async ManagePmsEeBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/managepmseebatch`,_data);
        }
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/actions/managepmseebatch`,_data);
        }
        if(_context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/actions/managepmseebatch`,_data);
        }
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/actions/managepmseebatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * SendMarkDoneBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ActionServiceBase
     */
    public async SendMarkDoneBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/sendmarkdonebatch`,_data);
        }
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/actions/sendmarkdonebatch`,_data);
        }
        if(_context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/actions/sendmarkdonebatch`,_data);
        }
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/actions/sendmarkdonebatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * SendTodoBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ActionServiceBase
     */
    public async SendTodoBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/sendtodobatch`,_data);
        }
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/actions/sendtodobatch`,_data);
        }
        if(_context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/actions/sendtodobatch`,_data);
        }
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/actions/sendtodobatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * SendToreadBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof ActionServiceBase
     */
    public async SendToreadBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/sendtoreadbatch`,_data);
        }
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/actions/sendtoreadbatch`,_data);
        }
        if(_context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/actions/sendtoreadbatch`,_data);
        }
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/actions/sendtoreadbatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
}
