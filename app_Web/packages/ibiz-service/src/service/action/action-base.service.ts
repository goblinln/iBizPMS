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
    protected APPNAME = 'Web';
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
        if (_context.project && _context.doclib && _context.doc && _context.action) {
            return this.http.get(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}/select`);
        }
        if (_context.product && _context.doclib && _context.doc && _context.action) {
            return this.http.get(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}/select`);
        }
        if (_context.project && _context.testtask && _context.action) {
            return this.http.get(`/projects/${_context.project}/testtasks/${_context.testtask}/actions/${_context.action}/select`);
        }
        if (_context.project && _context.testreport && _context.action) {
            return this.http.get(`/projects/${_context.project}/testreports/${_context.testreport}/actions/${_context.action}/select`);
        }
        if (_context.project && _context.task && _context.action) {
            return this.http.get(`/projects/${_context.project}/tasks/${_context.task}/actions/${_context.action}/select`);
        }
        if (_context.project && _context.doclib && _context.action) {
            return this.http.get(`/projects/${_context.project}/doclibs/${_context.doclib}/actions/${_context.action}/select`);
        }
        if (_context.doclib && _context.doc && _context.action) {
            return this.http.get(`/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}/select`);
        }
        if (_context.product && _context.story && _context.action) {
            return this.http.get(`/products/${_context.product}/stories/${_context.story}/actions/${_context.action}/select`);
        }
        if (_context.product && _context.productplan && _context.action) {
            return this.http.get(`/products/${_context.product}/productplans/${_context.productplan}/actions/${_context.action}/select`);
        }
        if (_context.product && _context.case && _context.action) {
            return this.http.get(`/products/${_context.product}/cases/${_context.case}/actions/${_context.action}/select`);
        }
        if (_context.product && _context.testreport && _context.action) {
            return this.http.get(`/products/${_context.product}/testreports/${_context.testreport}/actions/${_context.action}/select`);
        }
        if (_context.product && _context.testtask && _context.action) {
            return this.http.get(`/products/${_context.product}/testtasks/${_context.testtask}/actions/${_context.action}/select`);
        }
        if (_context.product && _context.testsuite && _context.action) {
            return this.http.get(`/products/${_context.product}/testsuites/${_context.testsuite}/actions/${_context.action}/select`);
        }
        if (_context.product && _context.release && _context.action) {
            return this.http.get(`/products/${_context.product}/releases/${_context.release}/actions/${_context.action}/select`);
        }
        if (_context.product && _context.doclib && _context.action) {
            return this.http.get(`/products/${_context.product}/doclibs/${_context.doclib}/actions/${_context.action}/select`);
        }
        if (_context.project && _context.action) {
            return this.http.get(`/projects/${_context.project}/actions/${_context.action}/select`);
        }
        if (_context.story && _context.action) {
            return this.http.get(`/stories/${_context.story}/actions/${_context.action}/select`);
        }
        if (_context.case && _context.action) {
            return this.http.get(`/cases/${_context.case}/actions/${_context.action}/select`);
        }
        if (_context.testsuite && _context.action) {
            return this.http.get(`/testsuites/${_context.testsuite}/actions/${_context.action}/select`);
        }
        if (_context.testtask && _context.action) {
            return this.http.get(`/testtasks/${_context.testtask}/actions/${_context.action}/select`);
        }
        if (_context.doc && _context.action) {
            return this.http.get(`/docs/${_context.doc}/actions/${_context.action}/select`);
        }
        if (_context.doclib && _context.action) {
            return this.http.get(`/doclibs/${_context.doclib}/actions/${_context.action}/select`);
        }
        if (_context.testreport && _context.action) {
            return this.http.get(`/testreports/${_context.testreport}/actions/${_context.action}/select`);
        }
        if (_context.release && _context.action) {
            return this.http.get(`/releases/${_context.release}/actions/${_context.action}/select`);
        }
        if (_context.task && _context.action) {
            return this.http.get(`/tasks/${_context.task}/actions/${_context.action}/select`);
        }
        if (_context.todo && _context.action) {
            return this.http.get(`/todos/${_context.todo}/actions/${_context.action}/select`);
        }
        if (_context.productplan && _context.action) {
            return this.http.get(`/productplans/${_context.productplan}/actions/${_context.action}/select`);
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
        if (_context.project && _context.doclib && _context.doc && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/actions`, _data);
        }
        if (_context.product && _context.doclib && _context.doc && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/actions`, _data);
        }
        if (_context.project && _context.testtask && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/actions`, _data);
        }
        if (_context.project && _context.testreport && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/actions`, _data);
        }
        if (_context.project && _context.task && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/actions`, _data);
        }
        if (_context.project && _context.doclib && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/actions`, _data);
        }
        if (_context.doclib && _context.doc && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/actions`, _data);
        }
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
        if (_context.product && _context.productplan && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/actions`, _data);
        }
        if (_context.product && _context.case && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/actions`, _data);
        }
        if (_context.product && _context.testreport && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/testreports/${_context.testreport}/actions`, _data);
        }
        if (_context.product && _context.testtask && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/actions`, _data);
        }
        if (_context.product && _context.testsuite && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/actions`, _data);
        }
        if (_context.product && _context.release && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/actions`, _data);
        }
        if (_context.product && _context.doclib && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/actions`, _data);
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
        if (_context.case && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/cases/${_context.case}/actions`, _data);
        }
        if (_context.testsuite && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/testsuites/${_context.testsuite}/actions`, _data);
        }
        if (_context.testtask && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/testtasks/${_context.testtask}/actions`, _data);
        }
        if (_context.doc && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/docs/${_context.doc}/actions`, _data);
        }
        if (_context.doclib && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/doclibs/${_context.doclib}/actions`, _data);
        }
        if (_context.testreport && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/testreports/${_context.testreport}/actions`, _data);
        }
        if (_context.release && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/releases/${_context.release}/actions`, _data);
        }
        if (_context.task && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/tasks/${_context.task}/actions`, _data);
        }
        if (_context.todo && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/todos/${_context.todo}/actions`, _data);
        }
        if (_context.productplan && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/productplans/${_context.productplan}/actions`, _data);
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
        if (_context.project && _context.doclib && _context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}`, _data);
        }
        if (_context.product && _context.doclib && _context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}`, _data);
        }
        if (_context.project && _context.testtask && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/testtasks/${_context.testtask}/actions/${_context.action}`, _data);
        }
        if (_context.project && _context.testreport && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/testreports/${_context.testreport}/actions/${_context.action}`, _data);
        }
        if (_context.project && _context.task && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/tasks/${_context.task}/actions/${_context.action}`, _data);
        }
        if (_context.project && _context.doclib && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/doclibs/${_context.doclib}/actions/${_context.action}`, _data);
        }
        if (_context.doclib && _context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}`, _data);
        }
        if (_context.product && _context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/stories/${_context.story}/actions/${_context.action}`, _data);
        }
        if (_context.product && _context.productplan && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/productplans/${_context.productplan}/actions/${_context.action}`, _data);
        }
        if (_context.product && _context.case && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/cases/${_context.case}/actions/${_context.action}`, _data);
        }
        if (_context.product && _context.testreport && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/testreports/${_context.testreport}/actions/${_context.action}`, _data);
        }
        if (_context.product && _context.testtask && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/testtasks/${_context.testtask}/actions/${_context.action}`, _data);
        }
        if (_context.product && _context.testsuite && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/testsuites/${_context.testsuite}/actions/${_context.action}`, _data);
        }
        if (_context.product && _context.release && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/releases/${_context.release}/actions/${_context.action}`, _data);
        }
        if (_context.product && _context.doclib && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/doclibs/${_context.doclib}/actions/${_context.action}`, _data);
        }
        if (_context.project && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/actions/${_context.action}`, _data);
        }
        if (_context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/stories/${_context.story}/actions/${_context.action}`, _data);
        }
        if (_context.case && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/cases/${_context.case}/actions/${_context.action}`, _data);
        }
        if (_context.testsuite && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/testsuites/${_context.testsuite}/actions/${_context.action}`, _data);
        }
        if (_context.testtask && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/testtasks/${_context.testtask}/actions/${_context.action}`, _data);
        }
        if (_context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/docs/${_context.doc}/actions/${_context.action}`, _data);
        }
        if (_context.doclib && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/doclibs/${_context.doclib}/actions/${_context.action}`, _data);
        }
        if (_context.testreport && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/testreports/${_context.testreport}/actions/${_context.action}`, _data);
        }
        if (_context.release && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/releases/${_context.release}/actions/${_context.action}`, _data);
        }
        if (_context.task && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/tasks/${_context.task}/actions/${_context.action}`, _data);
        }
        if (_context.todo && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/todos/${_context.todo}/actions/${_context.action}`, _data);
        }
        if (_context.productplan && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/productplans/${_context.productplan}/actions/${_context.action}`, _data);
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
        if (_context.project && _context.doclib && _context.doc && _context.action) {
            return this.http.delete(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}`);
        }
        if (_context.product && _context.doclib && _context.doc && _context.action) {
            return this.http.delete(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}`);
        }
        if (_context.project && _context.testtask && _context.action) {
            return this.http.delete(`/projects/${_context.project}/testtasks/${_context.testtask}/actions/${_context.action}`);
        }
        if (_context.project && _context.testreport && _context.action) {
            return this.http.delete(`/projects/${_context.project}/testreports/${_context.testreport}/actions/${_context.action}`);
        }
        if (_context.project && _context.task && _context.action) {
            return this.http.delete(`/projects/${_context.project}/tasks/${_context.task}/actions/${_context.action}`);
        }
        if (_context.project && _context.doclib && _context.action) {
            return this.http.delete(`/projects/${_context.project}/doclibs/${_context.doclib}/actions/${_context.action}`);
        }
        if (_context.doclib && _context.doc && _context.action) {
            return this.http.delete(`/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}`);
        }
        if (_context.product && _context.story && _context.action) {
            return this.http.delete(`/products/${_context.product}/stories/${_context.story}/actions/${_context.action}`);
        }
        if (_context.product && _context.productplan && _context.action) {
            return this.http.delete(`/products/${_context.product}/productplans/${_context.productplan}/actions/${_context.action}`);
        }
        if (_context.product && _context.case && _context.action) {
            return this.http.delete(`/products/${_context.product}/cases/${_context.case}/actions/${_context.action}`);
        }
        if (_context.product && _context.testreport && _context.action) {
            return this.http.delete(`/products/${_context.product}/testreports/${_context.testreport}/actions/${_context.action}`);
        }
        if (_context.product && _context.testtask && _context.action) {
            return this.http.delete(`/products/${_context.product}/testtasks/${_context.testtask}/actions/${_context.action}`);
        }
        if (_context.product && _context.testsuite && _context.action) {
            return this.http.delete(`/products/${_context.product}/testsuites/${_context.testsuite}/actions/${_context.action}`);
        }
        if (_context.product && _context.release && _context.action) {
            return this.http.delete(`/products/${_context.product}/releases/${_context.release}/actions/${_context.action}`);
        }
        if (_context.product && _context.doclib && _context.action) {
            return this.http.delete(`/products/${_context.product}/doclibs/${_context.doclib}/actions/${_context.action}`);
        }
        if (_context.project && _context.action) {
            return this.http.delete(`/projects/${_context.project}/actions/${_context.action}`);
        }
        if (_context.story && _context.action) {
            return this.http.delete(`/stories/${_context.story}/actions/${_context.action}`);
        }
        if (_context.case && _context.action) {
            return this.http.delete(`/cases/${_context.case}/actions/${_context.action}`);
        }
        if (_context.testsuite && _context.action) {
            return this.http.delete(`/testsuites/${_context.testsuite}/actions/${_context.action}`);
        }
        if (_context.testtask && _context.action) {
            return this.http.delete(`/testtasks/${_context.testtask}/actions/${_context.action}`);
        }
        if (_context.doc && _context.action) {
            return this.http.delete(`/docs/${_context.doc}/actions/${_context.action}`);
        }
        if (_context.doclib && _context.action) {
            return this.http.delete(`/doclibs/${_context.doclib}/actions/${_context.action}`);
        }
        if (_context.testreport && _context.action) {
            return this.http.delete(`/testreports/${_context.testreport}/actions/${_context.action}`);
        }
        if (_context.release && _context.action) {
            return this.http.delete(`/releases/${_context.release}/actions/${_context.action}`);
        }
        if (_context.task && _context.action) {
            return this.http.delete(`/tasks/${_context.task}/actions/${_context.action}`);
        }
        if (_context.todo && _context.action) {
            return this.http.delete(`/todos/${_context.todo}/actions/${_context.action}`);
        }
        if (_context.productplan && _context.action) {
            return this.http.delete(`/productplans/${_context.productplan}/actions/${_context.action}`);
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
        if (_context.project && _context.doclib && _context.doc && _context.action) {
            const res = await this.http.get(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}`);
            return res;
        }
        if (_context.product && _context.doclib && _context.doc && _context.action) {
            const res = await this.http.get(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}`);
            return res;
        }
        if (_context.project && _context.testtask && _context.action) {
            const res = await this.http.get(`/projects/${_context.project}/testtasks/${_context.testtask}/actions/${_context.action}`);
            return res;
        }
        if (_context.project && _context.testreport && _context.action) {
            const res = await this.http.get(`/projects/${_context.project}/testreports/${_context.testreport}/actions/${_context.action}`);
            return res;
        }
        if (_context.project && _context.task && _context.action) {
            const res = await this.http.get(`/projects/${_context.project}/tasks/${_context.task}/actions/${_context.action}`);
            return res;
        }
        if (_context.project && _context.doclib && _context.action) {
            const res = await this.http.get(`/projects/${_context.project}/doclibs/${_context.doclib}/actions/${_context.action}`);
            return res;
        }
        if (_context.doclib && _context.doc && _context.action) {
            const res = await this.http.get(`/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}`);
            return res;
        }
        if (_context.product && _context.story && _context.action) {
            const res = await this.http.get(`/products/${_context.product}/stories/${_context.story}/actions/${_context.action}`);
            return res;
        }
        if (_context.product && _context.productplan && _context.action) {
            const res = await this.http.get(`/products/${_context.product}/productplans/${_context.productplan}/actions/${_context.action}`);
            return res;
        }
        if (_context.product && _context.case && _context.action) {
            const res = await this.http.get(`/products/${_context.product}/cases/${_context.case}/actions/${_context.action}`);
            return res;
        }
        if (_context.product && _context.testreport && _context.action) {
            const res = await this.http.get(`/products/${_context.product}/testreports/${_context.testreport}/actions/${_context.action}`);
            return res;
        }
        if (_context.product && _context.testtask && _context.action) {
            const res = await this.http.get(`/products/${_context.product}/testtasks/${_context.testtask}/actions/${_context.action}`);
            return res;
        }
        if (_context.product && _context.testsuite && _context.action) {
            const res = await this.http.get(`/products/${_context.product}/testsuites/${_context.testsuite}/actions/${_context.action}`);
            return res;
        }
        if (_context.product && _context.release && _context.action) {
            const res = await this.http.get(`/products/${_context.product}/releases/${_context.release}/actions/${_context.action}`);
            return res;
        }
        if (_context.product && _context.doclib && _context.action) {
            const res = await this.http.get(`/products/${_context.product}/doclibs/${_context.doclib}/actions/${_context.action}`);
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
        if (_context.case && _context.action) {
            const res = await this.http.get(`/cases/${_context.case}/actions/${_context.action}`);
            return res;
        }
        if (_context.testsuite && _context.action) {
            const res = await this.http.get(`/testsuites/${_context.testsuite}/actions/${_context.action}`);
            return res;
        }
        if (_context.testtask && _context.action) {
            const res = await this.http.get(`/testtasks/${_context.testtask}/actions/${_context.action}`);
            return res;
        }
        if (_context.doc && _context.action) {
            const res = await this.http.get(`/docs/${_context.doc}/actions/${_context.action}`);
            return res;
        }
        if (_context.doclib && _context.action) {
            const res = await this.http.get(`/doclibs/${_context.doclib}/actions/${_context.action}`);
            return res;
        }
        if (_context.testreport && _context.action) {
            const res = await this.http.get(`/testreports/${_context.testreport}/actions/${_context.action}`);
            return res;
        }
        if (_context.release && _context.action) {
            const res = await this.http.get(`/releases/${_context.release}/actions/${_context.action}`);
            return res;
        }
        if (_context.task && _context.action) {
            const res = await this.http.get(`/tasks/${_context.task}/actions/${_context.action}`);
            return res;
        }
        if (_context.todo && _context.action) {
            const res = await this.http.get(`/todos/${_context.todo}/actions/${_context.action}`);
            return res;
        }
        if (_context.productplan && _context.action) {
            const res = await this.http.get(`/productplans/${_context.productplan}/actions/${_context.action}`);
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
        if (_context.project && _context.doclib && _context.doc && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.doclib && _context.doc && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/getdraft`, _data);
            return res;
        }
        if (_context.project && _context.testtask && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/testtasks/${_context.testtask}/actions/getdraft`, _data);
            return res;
        }
        if (_context.project && _context.testreport && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/testreports/${_context.testreport}/actions/getdraft`, _data);
            return res;
        }
        if (_context.project && _context.task && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/tasks/${_context.task}/actions/getdraft`, _data);
            return res;
        }
        if (_context.project && _context.doclib && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/doclibs/${_context.doclib}/actions/getdraft`, _data);
            return res;
        }
        if (_context.doclib && _context.doc && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/doclibs/${_context.doclib}/docs/${_context.doc}/actions/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.story && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/stories/${_context.story}/actions/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.productplan && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/productplans/${_context.productplan}/actions/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.case && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/cases/${_context.case}/actions/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.testreport && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/testreports/${_context.testreport}/actions/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.testtask && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/testtasks/${_context.testtask}/actions/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.testsuite && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/testsuites/${_context.testsuite}/actions/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.release && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/releases/${_context.release}/actions/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.doclib && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/doclibs/${_context.doclib}/actions/getdraft`, _data);
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
        if (_context.case && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/cases/${_context.case}/actions/getdraft`, _data);
            return res;
        }
        if (_context.testsuite && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/testsuites/${_context.testsuite}/actions/getdraft`, _data);
            return res;
        }
        if (_context.testtask && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/testtasks/${_context.testtask}/actions/getdraft`, _data);
            return res;
        }
        if (_context.doc && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/docs/${_context.doc}/actions/getdraft`, _data);
            return res;
        }
        if (_context.doclib && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/doclibs/${_context.doclib}/actions/getdraft`, _data);
            return res;
        }
        if (_context.testreport && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/testreports/${_context.testreport}/actions/getdraft`, _data);
            return res;
        }
        if (_context.release && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/releases/${_context.release}/actions/getdraft`, _data);
            return res;
        }
        if (_context.task && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/tasks/${_context.task}/actions/getdraft`, _data);
            return res;
        }
        if (_context.todo && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/todos/${_context.todo}/actions/getdraft`, _data);
            return res;
        }
        if (_context.productplan && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/productplans/${_context.productplan}/actions/getdraft`, _data);
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
        if (_context.project && _context.doclib && _context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}/comment`, _data);
        }
        if (_context.product && _context.doclib && _context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}/comment`, _data);
        }
        if (_context.project && _context.testtask && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/actions/${_context.action}/comment`, _data);
        }
        if (_context.project && _context.testreport && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/actions/${_context.action}/comment`, _data);
        }
        if (_context.project && _context.task && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/actions/${_context.action}/comment`, _data);
        }
        if (_context.project && _context.doclib && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/actions/${_context.action}/comment`, _data);
        }
        if (_context.doclib && _context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}/comment`, _data);
        }
        if (_context.product && _context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/${_context.action}/comment`, _data);
        }
        if (_context.product && _context.productplan && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/actions/${_context.action}/comment`, _data);
        }
        if (_context.product && _context.case && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/actions/${_context.action}/comment`, _data);
        }
        if (_context.product && _context.testreport && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testreports/${_context.testreport}/actions/${_context.action}/comment`, _data);
        }
        if (_context.product && _context.testtask && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/actions/${_context.action}/comment`, _data);
        }
        if (_context.product && _context.testsuite && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/actions/${_context.action}/comment`, _data);
        }
        if (_context.product && _context.release && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/actions/${_context.action}/comment`, _data);
        }
        if (_context.product && _context.doclib && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/actions/${_context.action}/comment`, _data);
        }
        if (_context.project && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/actions/${_context.action}/comment`, _data);
        }
        if (_context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/actions/${_context.action}/comment`, _data);
        }
        if (_context.case && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/cases/${_context.case}/actions/${_context.action}/comment`, _data);
        }
        if (_context.testsuite && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testsuites/${_context.testsuite}/actions/${_context.action}/comment`, _data);
        }
        if (_context.testtask && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testtasks/${_context.testtask}/actions/${_context.action}/comment`, _data);
        }
        if (_context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/docs/${_context.doc}/actions/${_context.action}/comment`, _data);
        }
        if (_context.doclib && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/actions/${_context.action}/comment`, _data);
        }
        if (_context.testreport && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testreports/${_context.testreport}/actions/${_context.action}/comment`, _data);
        }
        if (_context.release && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/releases/${_context.release}/actions/${_context.action}/comment`, _data);
        }
        if (_context.task && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/actions/${_context.action}/comment`, _data);
        }
        if (_context.todo && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/todos/${_context.todo}/actions/${_context.action}/comment`, _data);
        }
        if (_context.productplan && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/actions/${_context.action}/comment`, _data);
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
        if (_context.project && _context.doclib && _context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}/createhis`, _data);
        }
        if (_context.product && _context.doclib && _context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}/createhis`, _data);
        }
        if (_context.project && _context.testtask && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/actions/${_context.action}/createhis`, _data);
        }
        if (_context.project && _context.testreport && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/actions/${_context.action}/createhis`, _data);
        }
        if (_context.project && _context.task && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/actions/${_context.action}/createhis`, _data);
        }
        if (_context.project && _context.doclib && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/actions/${_context.action}/createhis`, _data);
        }
        if (_context.doclib && _context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}/createhis`, _data);
        }
        if (_context.product && _context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/${_context.action}/createhis`, _data);
        }
        if (_context.product && _context.productplan && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/actions/${_context.action}/createhis`, _data);
        }
        if (_context.product && _context.case && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/actions/${_context.action}/createhis`, _data);
        }
        if (_context.product && _context.testreport && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testreports/${_context.testreport}/actions/${_context.action}/createhis`, _data);
        }
        if (_context.product && _context.testtask && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/actions/${_context.action}/createhis`, _data);
        }
        if (_context.product && _context.testsuite && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/actions/${_context.action}/createhis`, _data);
        }
        if (_context.product && _context.release && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/actions/${_context.action}/createhis`, _data);
        }
        if (_context.product && _context.doclib && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/actions/${_context.action}/createhis`, _data);
        }
        if (_context.project && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/actions/${_context.action}/createhis`, _data);
        }
        if (_context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/actions/${_context.action}/createhis`, _data);
        }
        if (_context.case && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/cases/${_context.case}/actions/${_context.action}/createhis`, _data);
        }
        if (_context.testsuite && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testsuites/${_context.testsuite}/actions/${_context.action}/createhis`, _data);
        }
        if (_context.testtask && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testtasks/${_context.testtask}/actions/${_context.action}/createhis`, _data);
        }
        if (_context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/docs/${_context.doc}/actions/${_context.action}/createhis`, _data);
        }
        if (_context.doclib && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/actions/${_context.action}/createhis`, _data);
        }
        if (_context.testreport && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testreports/${_context.testreport}/actions/${_context.action}/createhis`, _data);
        }
        if (_context.release && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/releases/${_context.release}/actions/${_context.action}/createhis`, _data);
        }
        if (_context.task && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/actions/${_context.action}/createhis`, _data);
        }
        if (_context.todo && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/todos/${_context.todo}/actions/${_context.action}/createhis`, _data);
        }
        if (_context.productplan && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/actions/${_context.action}/createhis`, _data);
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
        if (_context.project && _context.doclib && _context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}/editcomment`, _data);
        }
        if (_context.product && _context.doclib && _context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}/editcomment`, _data);
        }
        if (_context.project && _context.testtask && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/actions/${_context.action}/editcomment`, _data);
        }
        if (_context.project && _context.testreport && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/actions/${_context.action}/editcomment`, _data);
        }
        if (_context.project && _context.task && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/actions/${_context.action}/editcomment`, _data);
        }
        if (_context.project && _context.doclib && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/actions/${_context.action}/editcomment`, _data);
        }
        if (_context.doclib && _context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}/editcomment`, _data);
        }
        if (_context.product && _context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/${_context.action}/editcomment`, _data);
        }
        if (_context.product && _context.productplan && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/actions/${_context.action}/editcomment`, _data);
        }
        if (_context.product && _context.case && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/actions/${_context.action}/editcomment`, _data);
        }
        if (_context.product && _context.testreport && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testreports/${_context.testreport}/actions/${_context.action}/editcomment`, _data);
        }
        if (_context.product && _context.testtask && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/actions/${_context.action}/editcomment`, _data);
        }
        if (_context.product && _context.testsuite && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/actions/${_context.action}/editcomment`, _data);
        }
        if (_context.product && _context.release && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/actions/${_context.action}/editcomment`, _data);
        }
        if (_context.product && _context.doclib && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/actions/${_context.action}/editcomment`, _data);
        }
        if (_context.project && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/actions/${_context.action}/editcomment`, _data);
        }
        if (_context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/actions/${_context.action}/editcomment`, _data);
        }
        if (_context.case && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/cases/${_context.case}/actions/${_context.action}/editcomment`, _data);
        }
        if (_context.testsuite && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testsuites/${_context.testsuite}/actions/${_context.action}/editcomment`, _data);
        }
        if (_context.testtask && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testtasks/${_context.testtask}/actions/${_context.action}/editcomment`, _data);
        }
        if (_context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/docs/${_context.doc}/actions/${_context.action}/editcomment`, _data);
        }
        if (_context.doclib && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/actions/${_context.action}/editcomment`, _data);
        }
        if (_context.testreport && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testreports/${_context.testreport}/actions/${_context.action}/editcomment`, _data);
        }
        if (_context.release && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/releases/${_context.release}/actions/${_context.action}/editcomment`, _data);
        }
        if (_context.task && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/actions/${_context.action}/editcomment`, _data);
        }
        if (_context.todo && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/todos/${_context.todo}/actions/${_context.action}/editcomment`, _data);
        }
        if (_context.productplan && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/actions/${_context.action}/editcomment`, _data);
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
        if (_context.project && _context.doclib && _context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}/managepmsee`, _data);
        }
        if (_context.product && _context.doclib && _context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}/managepmsee`, _data);
        }
        if (_context.project && _context.testtask && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/actions/${_context.action}/managepmsee`, _data);
        }
        if (_context.project && _context.testreport && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/actions/${_context.action}/managepmsee`, _data);
        }
        if (_context.project && _context.task && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/actions/${_context.action}/managepmsee`, _data);
        }
        if (_context.project && _context.doclib && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/actions/${_context.action}/managepmsee`, _data);
        }
        if (_context.doclib && _context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}/managepmsee`, _data);
        }
        if (_context.product && _context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/${_context.action}/managepmsee`, _data);
        }
        if (_context.product && _context.productplan && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/actions/${_context.action}/managepmsee`, _data);
        }
        if (_context.product && _context.case && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/actions/${_context.action}/managepmsee`, _data);
        }
        if (_context.product && _context.testreport && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testreports/${_context.testreport}/actions/${_context.action}/managepmsee`, _data);
        }
        if (_context.product && _context.testtask && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/actions/${_context.action}/managepmsee`, _data);
        }
        if (_context.product && _context.testsuite && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/actions/${_context.action}/managepmsee`, _data);
        }
        if (_context.product && _context.release && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/actions/${_context.action}/managepmsee`, _data);
        }
        if (_context.product && _context.doclib && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/actions/${_context.action}/managepmsee`, _data);
        }
        if (_context.project && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/actions/${_context.action}/managepmsee`, _data);
        }
        if (_context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/actions/${_context.action}/managepmsee`, _data);
        }
        if (_context.case && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/cases/${_context.case}/actions/${_context.action}/managepmsee`, _data);
        }
        if (_context.testsuite && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testsuites/${_context.testsuite}/actions/${_context.action}/managepmsee`, _data);
        }
        if (_context.testtask && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testtasks/${_context.testtask}/actions/${_context.action}/managepmsee`, _data);
        }
        if (_context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/docs/${_context.doc}/actions/${_context.action}/managepmsee`, _data);
        }
        if (_context.doclib && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/actions/${_context.action}/managepmsee`, _data);
        }
        if (_context.testreport && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testreports/${_context.testreport}/actions/${_context.action}/managepmsee`, _data);
        }
        if (_context.release && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/releases/${_context.release}/actions/${_context.action}/managepmsee`, _data);
        }
        if (_context.task && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/actions/${_context.action}/managepmsee`, _data);
        }
        if (_context.todo && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/todos/${_context.todo}/actions/${_context.action}/managepmsee`, _data);
        }
        if (_context.productplan && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/actions/${_context.action}/managepmsee`, _data);
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
        if (_context.project && _context.doclib && _context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}/sendmarkdone`, _data);
        }
        if (_context.product && _context.doclib && _context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}/sendmarkdone`, _data);
        }
        if (_context.project && _context.testtask && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/actions/${_context.action}/sendmarkdone`, _data);
        }
        if (_context.project && _context.testreport && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/actions/${_context.action}/sendmarkdone`, _data);
        }
        if (_context.project && _context.task && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/actions/${_context.action}/sendmarkdone`, _data);
        }
        if (_context.project && _context.doclib && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/actions/${_context.action}/sendmarkdone`, _data);
        }
        if (_context.doclib && _context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}/sendmarkdone`, _data);
        }
        if (_context.product && _context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/${_context.action}/sendmarkdone`, _data);
        }
        if (_context.product && _context.productplan && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/actions/${_context.action}/sendmarkdone`, _data);
        }
        if (_context.product && _context.case && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/actions/${_context.action}/sendmarkdone`, _data);
        }
        if (_context.product && _context.testreport && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testreports/${_context.testreport}/actions/${_context.action}/sendmarkdone`, _data);
        }
        if (_context.product && _context.testtask && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/actions/${_context.action}/sendmarkdone`, _data);
        }
        if (_context.product && _context.testsuite && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/actions/${_context.action}/sendmarkdone`, _data);
        }
        if (_context.product && _context.release && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/actions/${_context.action}/sendmarkdone`, _data);
        }
        if (_context.product && _context.doclib && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/actions/${_context.action}/sendmarkdone`, _data);
        }
        if (_context.project && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/actions/${_context.action}/sendmarkdone`, _data);
        }
        if (_context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/actions/${_context.action}/sendmarkdone`, _data);
        }
        if (_context.case && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/cases/${_context.case}/actions/${_context.action}/sendmarkdone`, _data);
        }
        if (_context.testsuite && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testsuites/${_context.testsuite}/actions/${_context.action}/sendmarkdone`, _data);
        }
        if (_context.testtask && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testtasks/${_context.testtask}/actions/${_context.action}/sendmarkdone`, _data);
        }
        if (_context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/docs/${_context.doc}/actions/${_context.action}/sendmarkdone`, _data);
        }
        if (_context.doclib && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/actions/${_context.action}/sendmarkdone`, _data);
        }
        if (_context.testreport && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testreports/${_context.testreport}/actions/${_context.action}/sendmarkdone`, _data);
        }
        if (_context.release && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/releases/${_context.release}/actions/${_context.action}/sendmarkdone`, _data);
        }
        if (_context.task && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/actions/${_context.action}/sendmarkdone`, _data);
        }
        if (_context.todo && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/todos/${_context.todo}/actions/${_context.action}/sendmarkdone`, _data);
        }
        if (_context.productplan && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/actions/${_context.action}/sendmarkdone`, _data);
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
        if (_context.project && _context.doclib && _context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}/sendtodo`, _data);
        }
        if (_context.product && _context.doclib && _context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}/sendtodo`, _data);
        }
        if (_context.project && _context.testtask && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/actions/${_context.action}/sendtodo`, _data);
        }
        if (_context.project && _context.testreport && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/actions/${_context.action}/sendtodo`, _data);
        }
        if (_context.project && _context.task && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/actions/${_context.action}/sendtodo`, _data);
        }
        if (_context.project && _context.doclib && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/actions/${_context.action}/sendtodo`, _data);
        }
        if (_context.doclib && _context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}/sendtodo`, _data);
        }
        if (_context.product && _context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/${_context.action}/sendtodo`, _data);
        }
        if (_context.product && _context.productplan && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/actions/${_context.action}/sendtodo`, _data);
        }
        if (_context.product && _context.case && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/actions/${_context.action}/sendtodo`, _data);
        }
        if (_context.product && _context.testreport && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testreports/${_context.testreport}/actions/${_context.action}/sendtodo`, _data);
        }
        if (_context.product && _context.testtask && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/actions/${_context.action}/sendtodo`, _data);
        }
        if (_context.product && _context.testsuite && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/actions/${_context.action}/sendtodo`, _data);
        }
        if (_context.product && _context.release && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/actions/${_context.action}/sendtodo`, _data);
        }
        if (_context.product && _context.doclib && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/actions/${_context.action}/sendtodo`, _data);
        }
        if (_context.project && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/actions/${_context.action}/sendtodo`, _data);
        }
        if (_context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/actions/${_context.action}/sendtodo`, _data);
        }
        if (_context.case && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/cases/${_context.case}/actions/${_context.action}/sendtodo`, _data);
        }
        if (_context.testsuite && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testsuites/${_context.testsuite}/actions/${_context.action}/sendtodo`, _data);
        }
        if (_context.testtask && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testtasks/${_context.testtask}/actions/${_context.action}/sendtodo`, _data);
        }
        if (_context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/docs/${_context.doc}/actions/${_context.action}/sendtodo`, _data);
        }
        if (_context.doclib && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/actions/${_context.action}/sendtodo`, _data);
        }
        if (_context.testreport && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testreports/${_context.testreport}/actions/${_context.action}/sendtodo`, _data);
        }
        if (_context.release && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/releases/${_context.release}/actions/${_context.action}/sendtodo`, _data);
        }
        if (_context.task && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/actions/${_context.action}/sendtodo`, _data);
        }
        if (_context.todo && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/todos/${_context.todo}/actions/${_context.action}/sendtodo`, _data);
        }
        if (_context.productplan && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/actions/${_context.action}/sendtodo`, _data);
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
        if (_context.project && _context.doclib && _context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}/sendtoread`, _data);
        }
        if (_context.product && _context.doclib && _context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}/sendtoread`, _data);
        }
        if (_context.project && _context.testtask && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/actions/${_context.action}/sendtoread`, _data);
        }
        if (_context.project && _context.testreport && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/actions/${_context.action}/sendtoread`, _data);
        }
        if (_context.project && _context.task && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/actions/${_context.action}/sendtoread`, _data);
        }
        if (_context.project && _context.doclib && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/actions/${_context.action}/sendtoread`, _data);
        }
        if (_context.doclib && _context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}/sendtoread`, _data);
        }
        if (_context.product && _context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/${_context.action}/sendtoread`, _data);
        }
        if (_context.product && _context.productplan && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/actions/${_context.action}/sendtoread`, _data);
        }
        if (_context.product && _context.case && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/actions/${_context.action}/sendtoread`, _data);
        }
        if (_context.product && _context.testreport && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testreports/${_context.testreport}/actions/${_context.action}/sendtoread`, _data);
        }
        if (_context.product && _context.testtask && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/actions/${_context.action}/sendtoread`, _data);
        }
        if (_context.product && _context.testsuite && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/actions/${_context.action}/sendtoread`, _data);
        }
        if (_context.product && _context.release && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/actions/${_context.action}/sendtoread`, _data);
        }
        if (_context.product && _context.doclib && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/actions/${_context.action}/sendtoread`, _data);
        }
        if (_context.project && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/actions/${_context.action}/sendtoread`, _data);
        }
        if (_context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/actions/${_context.action}/sendtoread`, _data);
        }
        if (_context.case && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/cases/${_context.case}/actions/${_context.action}/sendtoread`, _data);
        }
        if (_context.testsuite && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testsuites/${_context.testsuite}/actions/${_context.action}/sendtoread`, _data);
        }
        if (_context.testtask && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testtasks/${_context.testtask}/actions/${_context.action}/sendtoread`, _data);
        }
        if (_context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/docs/${_context.doc}/actions/${_context.action}/sendtoread`, _data);
        }
        if (_context.doclib && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/actions/${_context.action}/sendtoread`, _data);
        }
        if (_context.testreport && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testreports/${_context.testreport}/actions/${_context.action}/sendtoread`, _data);
        }
        if (_context.release && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/releases/${_context.release}/actions/${_context.action}/sendtoread`, _data);
        }
        if (_context.task && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/actions/${_context.action}/sendtoread`, _data);
        }
        if (_context.todo && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/todos/${_context.todo}/actions/${_context.action}/sendtoread`, _data);
        }
        if (_context.productplan && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/actions/${_context.action}/sendtoread`, _data);
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
        if (_context.project && _context.doclib && _context.doc && true) {
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/fetchdefault`, _data);
        }
        if (_context.product && _context.doclib && _context.doc && true) {
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/fetchdefault`, _data);
        }
        if (_context.project && _context.testtask && true) {
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/actions/fetchdefault`, _data);
        }
        if (_context.project && _context.testreport && true) {
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/actions/fetchdefault`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/actions/fetchdefault`, _data);
        }
        if (_context.project && _context.doclib && true) {
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/actions/fetchdefault`, _data);
        }
        if (_context.doclib && _context.doc && true) {
            return this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/actions/fetchdefault`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/fetchdefault`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/actions/fetchdefault`, _data);
        }
        if (_context.product && _context.case && true) {
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/actions/fetchdefault`, _data);
        }
        if (_context.product && _context.testreport && true) {
            return this.http.post(`/products/${_context.product}/testreports/${_context.testreport}/actions/fetchdefault`, _data);
        }
        if (_context.product && _context.testtask && true) {
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/actions/fetchdefault`, _data);
        }
        if (_context.product && _context.testsuite && true) {
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/actions/fetchdefault`, _data);
        }
        if (_context.product && _context.release && true) {
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/actions/fetchdefault`, _data);
        }
        if (_context.product && _context.doclib && true) {
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/actions/fetchdefault`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/actions/fetchdefault`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/actions/fetchdefault`, _data);
        }
        if (_context.case && true) {
            return this.http.post(`/cases/${_context.case}/actions/fetchdefault`, _data);
        }
        if (_context.testsuite && true) {
            return this.http.post(`/testsuites/${_context.testsuite}/actions/fetchdefault`, _data);
        }
        if (_context.testtask && true) {
            return this.http.post(`/testtasks/${_context.testtask}/actions/fetchdefault`, _data);
        }
        if (_context.doc && true) {
            return this.http.post(`/docs/${_context.doc}/actions/fetchdefault`, _data);
        }
        if (_context.doclib && true) {
            return this.http.post(`/doclibs/${_context.doclib}/actions/fetchdefault`, _data);
        }
        if (_context.testreport && true) {
            return this.http.post(`/testreports/${_context.testreport}/actions/fetchdefault`, _data);
        }
        if (_context.release && true) {
            return this.http.post(`/releases/${_context.release}/actions/fetchdefault`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/actions/fetchdefault`, _data);
        }
        if (_context.todo && true) {
            return this.http.post(`/todos/${_context.todo}/actions/fetchdefault`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/actions/fetchdefault`, _data);
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
        if (_context.project && _context.doclib && _context.doc && true) {
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/fetchmobtype`, _data);
        }
        if (_context.product && _context.doclib && _context.doc && true) {
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/fetchmobtype`, _data);
        }
        if (_context.project && _context.testtask && true) {
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/actions/fetchmobtype`, _data);
        }
        if (_context.project && _context.testreport && true) {
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/actions/fetchmobtype`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/actions/fetchmobtype`, _data);
        }
        if (_context.project && _context.doclib && true) {
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/actions/fetchmobtype`, _data);
        }
        if (_context.doclib && _context.doc && true) {
            return this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/actions/fetchmobtype`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/fetchmobtype`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/actions/fetchmobtype`, _data);
        }
        if (_context.product && _context.case && true) {
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/actions/fetchmobtype`, _data);
        }
        if (_context.product && _context.testreport && true) {
            return this.http.post(`/products/${_context.product}/testreports/${_context.testreport}/actions/fetchmobtype`, _data);
        }
        if (_context.product && _context.testtask && true) {
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/actions/fetchmobtype`, _data);
        }
        if (_context.product && _context.testsuite && true) {
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/actions/fetchmobtype`, _data);
        }
        if (_context.product && _context.release && true) {
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/actions/fetchmobtype`, _data);
        }
        if (_context.product && _context.doclib && true) {
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/actions/fetchmobtype`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/actions/fetchmobtype`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/actions/fetchmobtype`, _data);
        }
        if (_context.case && true) {
            return this.http.post(`/cases/${_context.case}/actions/fetchmobtype`, _data);
        }
        if (_context.testsuite && true) {
            return this.http.post(`/testsuites/${_context.testsuite}/actions/fetchmobtype`, _data);
        }
        if (_context.testtask && true) {
            return this.http.post(`/testtasks/${_context.testtask}/actions/fetchmobtype`, _data);
        }
        if (_context.doc && true) {
            return this.http.post(`/docs/${_context.doc}/actions/fetchmobtype`, _data);
        }
        if (_context.doclib && true) {
            return this.http.post(`/doclibs/${_context.doclib}/actions/fetchmobtype`, _data);
        }
        if (_context.testreport && true) {
            return this.http.post(`/testreports/${_context.testreport}/actions/fetchmobtype`, _data);
        }
        if (_context.release && true) {
            return this.http.post(`/releases/${_context.release}/actions/fetchmobtype`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/actions/fetchmobtype`, _data);
        }
        if (_context.todo && true) {
            return this.http.post(`/todos/${_context.todo}/actions/fetchmobtype`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/actions/fetchmobtype`, _data);
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
        if (_context.project && _context.doclib && _context.doc && true) {
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/fetchmytrends`, _data);
        }
        if (_context.product && _context.doclib && _context.doc && true) {
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/fetchmytrends`, _data);
        }
        if (_context.project && _context.testtask && true) {
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/actions/fetchmytrends`, _data);
        }
        if (_context.project && _context.testreport && true) {
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/actions/fetchmytrends`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/actions/fetchmytrends`, _data);
        }
        if (_context.project && _context.doclib && true) {
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/actions/fetchmytrends`, _data);
        }
        if (_context.doclib && _context.doc && true) {
            return this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/actions/fetchmytrends`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/fetchmytrends`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/actions/fetchmytrends`, _data);
        }
        if (_context.product && _context.case && true) {
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/actions/fetchmytrends`, _data);
        }
        if (_context.product && _context.testreport && true) {
            return this.http.post(`/products/${_context.product}/testreports/${_context.testreport}/actions/fetchmytrends`, _data);
        }
        if (_context.product && _context.testtask && true) {
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/actions/fetchmytrends`, _data);
        }
        if (_context.product && _context.testsuite && true) {
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/actions/fetchmytrends`, _data);
        }
        if (_context.product && _context.release && true) {
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/actions/fetchmytrends`, _data);
        }
        if (_context.product && _context.doclib && true) {
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/actions/fetchmytrends`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/actions/fetchmytrends`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/actions/fetchmytrends`, _data);
        }
        if (_context.case && true) {
            return this.http.post(`/cases/${_context.case}/actions/fetchmytrends`, _data);
        }
        if (_context.testsuite && true) {
            return this.http.post(`/testsuites/${_context.testsuite}/actions/fetchmytrends`, _data);
        }
        if (_context.testtask && true) {
            return this.http.post(`/testtasks/${_context.testtask}/actions/fetchmytrends`, _data);
        }
        if (_context.doc && true) {
            return this.http.post(`/docs/${_context.doc}/actions/fetchmytrends`, _data);
        }
        if (_context.doclib && true) {
            return this.http.post(`/doclibs/${_context.doclib}/actions/fetchmytrends`, _data);
        }
        if (_context.testreport && true) {
            return this.http.post(`/testreports/${_context.testreport}/actions/fetchmytrends`, _data);
        }
        if (_context.release && true) {
            return this.http.post(`/releases/${_context.release}/actions/fetchmytrends`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/actions/fetchmytrends`, _data);
        }
        if (_context.todo && true) {
            return this.http.post(`/todos/${_context.todo}/actions/fetchmytrends`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/actions/fetchmytrends`, _data);
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
        if (_context.project && _context.doclib && _context.doc && true) {
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/fetchproducttrends`, _data);
        }
        if (_context.product && _context.doclib && _context.doc && true) {
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/fetchproducttrends`, _data);
        }
        if (_context.project && _context.testtask && true) {
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/actions/fetchproducttrends`, _data);
        }
        if (_context.project && _context.testreport && true) {
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/actions/fetchproducttrends`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/actions/fetchproducttrends`, _data);
        }
        if (_context.project && _context.doclib && true) {
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/actions/fetchproducttrends`, _data);
        }
        if (_context.doclib && _context.doc && true) {
            return this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/actions/fetchproducttrends`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/fetchproducttrends`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/actions/fetchproducttrends`, _data);
        }
        if (_context.product && _context.case && true) {
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/actions/fetchproducttrends`, _data);
        }
        if (_context.product && _context.testreport && true) {
            return this.http.post(`/products/${_context.product}/testreports/${_context.testreport}/actions/fetchproducttrends`, _data);
        }
        if (_context.product && _context.testtask && true) {
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/actions/fetchproducttrends`, _data);
        }
        if (_context.product && _context.testsuite && true) {
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/actions/fetchproducttrends`, _data);
        }
        if (_context.product && _context.release && true) {
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/actions/fetchproducttrends`, _data);
        }
        if (_context.product && _context.doclib && true) {
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/actions/fetchproducttrends`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/actions/fetchproducttrends`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/actions/fetchproducttrends`, _data);
        }
        if (_context.case && true) {
            return this.http.post(`/cases/${_context.case}/actions/fetchproducttrends`, _data);
        }
        if (_context.testsuite && true) {
            return this.http.post(`/testsuites/${_context.testsuite}/actions/fetchproducttrends`, _data);
        }
        if (_context.testtask && true) {
            return this.http.post(`/testtasks/${_context.testtask}/actions/fetchproducttrends`, _data);
        }
        if (_context.doc && true) {
            return this.http.post(`/docs/${_context.doc}/actions/fetchproducttrends`, _data);
        }
        if (_context.doclib && true) {
            return this.http.post(`/doclibs/${_context.doclib}/actions/fetchproducttrends`, _data);
        }
        if (_context.testreport && true) {
            return this.http.post(`/testreports/${_context.testreport}/actions/fetchproducttrends`, _data);
        }
        if (_context.release && true) {
            return this.http.post(`/releases/${_context.release}/actions/fetchproducttrends`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/actions/fetchproducttrends`, _data);
        }
        if (_context.todo && true) {
            return this.http.post(`/todos/${_context.todo}/actions/fetchproducttrends`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/actions/fetchproducttrends`, _data);
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
        if (_context.project && _context.doclib && _context.doc && true) {
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/fetchprojecttrends`, _data);
        }
        if (_context.product && _context.doclib && _context.doc && true) {
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/fetchprojecttrends`, _data);
        }
        if (_context.project && _context.testtask && true) {
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/actions/fetchprojecttrends`, _data);
        }
        if (_context.project && _context.testreport && true) {
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/actions/fetchprojecttrends`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/actions/fetchprojecttrends`, _data);
        }
        if (_context.project && _context.doclib && true) {
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/actions/fetchprojecttrends`, _data);
        }
        if (_context.doclib && _context.doc && true) {
            return this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/actions/fetchprojecttrends`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/fetchprojecttrends`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/actions/fetchprojecttrends`, _data);
        }
        if (_context.product && _context.case && true) {
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/actions/fetchprojecttrends`, _data);
        }
        if (_context.product && _context.testreport && true) {
            return this.http.post(`/products/${_context.product}/testreports/${_context.testreport}/actions/fetchprojecttrends`, _data);
        }
        if (_context.product && _context.testtask && true) {
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/actions/fetchprojecttrends`, _data);
        }
        if (_context.product && _context.testsuite && true) {
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/actions/fetchprojecttrends`, _data);
        }
        if (_context.product && _context.release && true) {
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/actions/fetchprojecttrends`, _data);
        }
        if (_context.product && _context.doclib && true) {
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/actions/fetchprojecttrends`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/actions/fetchprojecttrends`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/actions/fetchprojecttrends`, _data);
        }
        if (_context.case && true) {
            return this.http.post(`/cases/${_context.case}/actions/fetchprojecttrends`, _data);
        }
        if (_context.testsuite && true) {
            return this.http.post(`/testsuites/${_context.testsuite}/actions/fetchprojecttrends`, _data);
        }
        if (_context.testtask && true) {
            return this.http.post(`/testtasks/${_context.testtask}/actions/fetchprojecttrends`, _data);
        }
        if (_context.doc && true) {
            return this.http.post(`/docs/${_context.doc}/actions/fetchprojecttrends`, _data);
        }
        if (_context.doclib && true) {
            return this.http.post(`/doclibs/${_context.doclib}/actions/fetchprojecttrends`, _data);
        }
        if (_context.testreport && true) {
            return this.http.post(`/testreports/${_context.testreport}/actions/fetchprojecttrends`, _data);
        }
        if (_context.release && true) {
            return this.http.post(`/releases/${_context.release}/actions/fetchprojecttrends`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/actions/fetchprojecttrends`, _data);
        }
        if (_context.todo && true) {
            return this.http.post(`/todos/${_context.todo}/actions/fetchprojecttrends`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/actions/fetchprojecttrends`, _data);
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
        if (_context.project && _context.doclib && _context.doc && true) {
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/fetchqueryuseryear`, _data);
        }
        if (_context.product && _context.doclib && _context.doc && true) {
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/fetchqueryuseryear`, _data);
        }
        if (_context.project && _context.testtask && true) {
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/actions/fetchqueryuseryear`, _data);
        }
        if (_context.project && _context.testreport && true) {
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/actions/fetchqueryuseryear`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/actions/fetchqueryuseryear`, _data);
        }
        if (_context.project && _context.doclib && true) {
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/actions/fetchqueryuseryear`, _data);
        }
        if (_context.doclib && _context.doc && true) {
            return this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/actions/fetchqueryuseryear`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/fetchqueryuseryear`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/actions/fetchqueryuseryear`, _data);
        }
        if (_context.product && _context.case && true) {
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/actions/fetchqueryuseryear`, _data);
        }
        if (_context.product && _context.testreport && true) {
            return this.http.post(`/products/${_context.product}/testreports/${_context.testreport}/actions/fetchqueryuseryear`, _data);
        }
        if (_context.product && _context.testtask && true) {
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/actions/fetchqueryuseryear`, _data);
        }
        if (_context.product && _context.testsuite && true) {
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/actions/fetchqueryuseryear`, _data);
        }
        if (_context.product && _context.release && true) {
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/actions/fetchqueryuseryear`, _data);
        }
        if (_context.product && _context.doclib && true) {
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/actions/fetchqueryuseryear`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/actions/fetchqueryuseryear`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/actions/fetchqueryuseryear`, _data);
        }
        if (_context.case && true) {
            return this.http.post(`/cases/${_context.case}/actions/fetchqueryuseryear`, _data);
        }
        if (_context.testsuite && true) {
            return this.http.post(`/testsuites/${_context.testsuite}/actions/fetchqueryuseryear`, _data);
        }
        if (_context.testtask && true) {
            return this.http.post(`/testtasks/${_context.testtask}/actions/fetchqueryuseryear`, _data);
        }
        if (_context.doc && true) {
            return this.http.post(`/docs/${_context.doc}/actions/fetchqueryuseryear`, _data);
        }
        if (_context.doclib && true) {
            return this.http.post(`/doclibs/${_context.doclib}/actions/fetchqueryuseryear`, _data);
        }
        if (_context.testreport && true) {
            return this.http.post(`/testreports/${_context.testreport}/actions/fetchqueryuseryear`, _data);
        }
        if (_context.release && true) {
            return this.http.post(`/releases/${_context.release}/actions/fetchqueryuseryear`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/actions/fetchqueryuseryear`, _data);
        }
        if (_context.todo && true) {
            return this.http.post(`/todos/${_context.todo}/actions/fetchqueryuseryear`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/actions/fetchqueryuseryear`, _data);
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
        if (_context.project && _context.doclib && _context.doc && true) {
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/fetchtype`, _data);
        }
        if (_context.product && _context.doclib && _context.doc && true) {
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/fetchtype`, _data);
        }
        if (_context.project && _context.testtask && true) {
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/actions/fetchtype`, _data);
        }
        if (_context.project && _context.testreport && true) {
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/actions/fetchtype`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/actions/fetchtype`, _data);
        }
        if (_context.project && _context.doclib && true) {
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/actions/fetchtype`, _data);
        }
        if (_context.doclib && _context.doc && true) {
            return this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/actions/fetchtype`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/fetchtype`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/actions/fetchtype`, _data);
        }
        if (_context.product && _context.case && true) {
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/actions/fetchtype`, _data);
        }
        if (_context.product && _context.testreport && true) {
            return this.http.post(`/products/${_context.product}/testreports/${_context.testreport}/actions/fetchtype`, _data);
        }
        if (_context.product && _context.testtask && true) {
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/actions/fetchtype`, _data);
        }
        if (_context.product && _context.testsuite && true) {
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/actions/fetchtype`, _data);
        }
        if (_context.product && _context.release && true) {
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/actions/fetchtype`, _data);
        }
        if (_context.product && _context.doclib && true) {
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/actions/fetchtype`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/actions/fetchtype`, _data);
        }
        if (_context.story && true) {
            return this.http.post(`/stories/${_context.story}/actions/fetchtype`, _data);
        }
        if (_context.case && true) {
            return this.http.post(`/cases/${_context.case}/actions/fetchtype`, _data);
        }
        if (_context.testsuite && true) {
            return this.http.post(`/testsuites/${_context.testsuite}/actions/fetchtype`, _data);
        }
        if (_context.testtask && true) {
            return this.http.post(`/testtasks/${_context.testtask}/actions/fetchtype`, _data);
        }
        if (_context.doc && true) {
            return this.http.post(`/docs/${_context.doc}/actions/fetchtype`, _data);
        }
        if (_context.doclib && true) {
            return this.http.post(`/doclibs/${_context.doclib}/actions/fetchtype`, _data);
        }
        if (_context.testreport && true) {
            return this.http.post(`/testreports/${_context.testreport}/actions/fetchtype`, _data);
        }
        if (_context.release && true) {
            return this.http.post(`/releases/${_context.release}/actions/fetchtype`, _data);
        }
        if (_context.task && true) {
            return this.http.post(`/tasks/${_context.task}/actions/fetchtype`, _data);
        }
        if (_context.todo && true) {
            return this.http.post(`/todos/${_context.todo}/actions/fetchtype`, _data);
        }
        if (_context.productplan && true) {
            return this.http.post(`/productplans/${_context.productplan}/actions/fetchtype`, _data);
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
        if(_context.project && _context.doclib && _context.doc && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/createhisbatch`,_data);
        }
        if(_context.product && _context.doclib && _context.doc && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/createhisbatch`,_data);
        }
        if(_context.project && _context.testtask && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/actions/createhisbatch`,_data);
        }
        if(_context.project && _context.testreport && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/actions/createhisbatch`,_data);
        }
        if(_context.project && _context.task && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/actions/createhisbatch`,_data);
        }
        if(_context.project && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/actions/createhisbatch`,_data);
        }
        if(_context.doclib && _context.doc && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/actions/createhisbatch`,_data);
        }
        if(_context.product && _context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/createhisbatch`,_data);
        }
        if(_context.product && _context.productplan && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/actions/createhisbatch`,_data);
        }
        if(_context.product && _context.case && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/actions/createhisbatch`,_data);
        }
        if(_context.product && _context.testreport && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testreports/${_context.testreport}/actions/createhisbatch`,_data);
        }
        if(_context.product && _context.testtask && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/actions/createhisbatch`,_data);
        }
        if(_context.product && _context.testsuite && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/actions/createhisbatch`,_data);
        }
        if(_context.product && _context.release && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/actions/createhisbatch`,_data);
        }
        if(_context.product && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/actions/createhisbatch`,_data);
        }
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/actions/createhisbatch`,_data);
        }
        if(_context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/actions/createhisbatch`,_data);
        }
        if(_context.case && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/cases/${_context.case}/actions/createhisbatch`,_data);
        }
        if(_context.testsuite && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testsuites/${_context.testsuite}/actions/createhisbatch`,_data);
        }
        if(_context.testtask && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testtasks/${_context.testtask}/actions/createhisbatch`,_data);
        }
        if(_context.doc && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/docs/${_context.doc}/actions/createhisbatch`,_data);
        }
        if(_context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/actions/createhisbatch`,_data);
        }
        if(_context.testreport && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testreports/${_context.testreport}/actions/createhisbatch`,_data);
        }
        if(_context.release && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/releases/${_context.release}/actions/createhisbatch`,_data);
        }
        if(_context.task && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/actions/createhisbatch`,_data);
        }
        if(_context.todo && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/todos/${_context.todo}/actions/createhisbatch`,_data);
        }
        if(_context.productplan && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/actions/createhisbatch`,_data);
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
        if(_context.project && _context.doclib && _context.doc && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/editcommentbatch`,_data);
        }
        if(_context.product && _context.doclib && _context.doc && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/editcommentbatch`,_data);
        }
        if(_context.project && _context.testtask && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/actions/editcommentbatch`,_data);
        }
        if(_context.project && _context.testreport && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/actions/editcommentbatch`,_data);
        }
        if(_context.project && _context.task && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/actions/editcommentbatch`,_data);
        }
        if(_context.project && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/actions/editcommentbatch`,_data);
        }
        if(_context.doclib && _context.doc && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/actions/editcommentbatch`,_data);
        }
        if(_context.product && _context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/editcommentbatch`,_data);
        }
        if(_context.product && _context.productplan && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/actions/editcommentbatch`,_data);
        }
        if(_context.product && _context.case && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/actions/editcommentbatch`,_data);
        }
        if(_context.product && _context.testreport && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testreports/${_context.testreport}/actions/editcommentbatch`,_data);
        }
        if(_context.product && _context.testtask && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/actions/editcommentbatch`,_data);
        }
        if(_context.product && _context.testsuite && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/actions/editcommentbatch`,_data);
        }
        if(_context.product && _context.release && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/actions/editcommentbatch`,_data);
        }
        if(_context.product && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/actions/editcommentbatch`,_data);
        }
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/actions/editcommentbatch`,_data);
        }
        if(_context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/actions/editcommentbatch`,_data);
        }
        if(_context.case && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/cases/${_context.case}/actions/editcommentbatch`,_data);
        }
        if(_context.testsuite && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testsuites/${_context.testsuite}/actions/editcommentbatch`,_data);
        }
        if(_context.testtask && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testtasks/${_context.testtask}/actions/editcommentbatch`,_data);
        }
        if(_context.doc && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/docs/${_context.doc}/actions/editcommentbatch`,_data);
        }
        if(_context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/actions/editcommentbatch`,_data);
        }
        if(_context.testreport && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testreports/${_context.testreport}/actions/editcommentbatch`,_data);
        }
        if(_context.release && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/releases/${_context.release}/actions/editcommentbatch`,_data);
        }
        if(_context.task && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/actions/editcommentbatch`,_data);
        }
        if(_context.todo && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/todos/${_context.todo}/actions/editcommentbatch`,_data);
        }
        if(_context.productplan && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/actions/editcommentbatch`,_data);
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
        if(_context.project && _context.doclib && _context.doc && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/managepmseebatch`,_data);
        }
        if(_context.product && _context.doclib && _context.doc && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/managepmseebatch`,_data);
        }
        if(_context.project && _context.testtask && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/actions/managepmseebatch`,_data);
        }
        if(_context.project && _context.testreport && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/actions/managepmseebatch`,_data);
        }
        if(_context.project && _context.task && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/actions/managepmseebatch`,_data);
        }
        if(_context.project && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/actions/managepmseebatch`,_data);
        }
        if(_context.doclib && _context.doc && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/actions/managepmseebatch`,_data);
        }
        if(_context.product && _context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/managepmseebatch`,_data);
        }
        if(_context.product && _context.productplan && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/actions/managepmseebatch`,_data);
        }
        if(_context.product && _context.case && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/actions/managepmseebatch`,_data);
        }
        if(_context.product && _context.testreport && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testreports/${_context.testreport}/actions/managepmseebatch`,_data);
        }
        if(_context.product && _context.testtask && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/actions/managepmseebatch`,_data);
        }
        if(_context.product && _context.testsuite && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/actions/managepmseebatch`,_data);
        }
        if(_context.product && _context.release && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/actions/managepmseebatch`,_data);
        }
        if(_context.product && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/actions/managepmseebatch`,_data);
        }
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/actions/managepmseebatch`,_data);
        }
        if(_context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/actions/managepmseebatch`,_data);
        }
        if(_context.case && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/cases/${_context.case}/actions/managepmseebatch`,_data);
        }
        if(_context.testsuite && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testsuites/${_context.testsuite}/actions/managepmseebatch`,_data);
        }
        if(_context.testtask && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testtasks/${_context.testtask}/actions/managepmseebatch`,_data);
        }
        if(_context.doc && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/docs/${_context.doc}/actions/managepmseebatch`,_data);
        }
        if(_context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/actions/managepmseebatch`,_data);
        }
        if(_context.testreport && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testreports/${_context.testreport}/actions/managepmseebatch`,_data);
        }
        if(_context.release && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/releases/${_context.release}/actions/managepmseebatch`,_data);
        }
        if(_context.task && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/actions/managepmseebatch`,_data);
        }
        if(_context.todo && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/todos/${_context.todo}/actions/managepmseebatch`,_data);
        }
        if(_context.productplan && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/actions/managepmseebatch`,_data);
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
        if(_context.project && _context.doclib && _context.doc && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/sendmarkdonebatch`,_data);
        }
        if(_context.product && _context.doclib && _context.doc && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/sendmarkdonebatch`,_data);
        }
        if(_context.project && _context.testtask && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/actions/sendmarkdonebatch`,_data);
        }
        if(_context.project && _context.testreport && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/actions/sendmarkdonebatch`,_data);
        }
        if(_context.project && _context.task && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/actions/sendmarkdonebatch`,_data);
        }
        if(_context.project && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/actions/sendmarkdonebatch`,_data);
        }
        if(_context.doclib && _context.doc && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/actions/sendmarkdonebatch`,_data);
        }
        if(_context.product && _context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/sendmarkdonebatch`,_data);
        }
        if(_context.product && _context.productplan && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/actions/sendmarkdonebatch`,_data);
        }
        if(_context.product && _context.case && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/actions/sendmarkdonebatch`,_data);
        }
        if(_context.product && _context.testreport && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testreports/${_context.testreport}/actions/sendmarkdonebatch`,_data);
        }
        if(_context.product && _context.testtask && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/actions/sendmarkdonebatch`,_data);
        }
        if(_context.product && _context.testsuite && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/actions/sendmarkdonebatch`,_data);
        }
        if(_context.product && _context.release && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/actions/sendmarkdonebatch`,_data);
        }
        if(_context.product && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/actions/sendmarkdonebatch`,_data);
        }
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/actions/sendmarkdonebatch`,_data);
        }
        if(_context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/actions/sendmarkdonebatch`,_data);
        }
        if(_context.case && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/cases/${_context.case}/actions/sendmarkdonebatch`,_data);
        }
        if(_context.testsuite && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testsuites/${_context.testsuite}/actions/sendmarkdonebatch`,_data);
        }
        if(_context.testtask && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testtasks/${_context.testtask}/actions/sendmarkdonebatch`,_data);
        }
        if(_context.doc && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/docs/${_context.doc}/actions/sendmarkdonebatch`,_data);
        }
        if(_context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/actions/sendmarkdonebatch`,_data);
        }
        if(_context.testreport && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testreports/${_context.testreport}/actions/sendmarkdonebatch`,_data);
        }
        if(_context.release && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/releases/${_context.release}/actions/sendmarkdonebatch`,_data);
        }
        if(_context.task && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/actions/sendmarkdonebatch`,_data);
        }
        if(_context.todo && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/todos/${_context.todo}/actions/sendmarkdonebatch`,_data);
        }
        if(_context.productplan && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/actions/sendmarkdonebatch`,_data);
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
        if(_context.project && _context.doclib && _context.doc && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/sendtodobatch`,_data);
        }
        if(_context.product && _context.doclib && _context.doc && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/sendtodobatch`,_data);
        }
        if(_context.project && _context.testtask && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/actions/sendtodobatch`,_data);
        }
        if(_context.project && _context.testreport && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/actions/sendtodobatch`,_data);
        }
        if(_context.project && _context.task && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/actions/sendtodobatch`,_data);
        }
        if(_context.project && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/actions/sendtodobatch`,_data);
        }
        if(_context.doclib && _context.doc && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/actions/sendtodobatch`,_data);
        }
        if(_context.product && _context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/sendtodobatch`,_data);
        }
        if(_context.product && _context.productplan && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/actions/sendtodobatch`,_data);
        }
        if(_context.product && _context.case && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/actions/sendtodobatch`,_data);
        }
        if(_context.product && _context.testreport && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testreports/${_context.testreport}/actions/sendtodobatch`,_data);
        }
        if(_context.product && _context.testtask && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/actions/sendtodobatch`,_data);
        }
        if(_context.product && _context.testsuite && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/actions/sendtodobatch`,_data);
        }
        if(_context.product && _context.release && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/actions/sendtodobatch`,_data);
        }
        if(_context.product && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/actions/sendtodobatch`,_data);
        }
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/actions/sendtodobatch`,_data);
        }
        if(_context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/actions/sendtodobatch`,_data);
        }
        if(_context.case && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/cases/${_context.case}/actions/sendtodobatch`,_data);
        }
        if(_context.testsuite && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testsuites/${_context.testsuite}/actions/sendtodobatch`,_data);
        }
        if(_context.testtask && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testtasks/${_context.testtask}/actions/sendtodobatch`,_data);
        }
        if(_context.doc && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/docs/${_context.doc}/actions/sendtodobatch`,_data);
        }
        if(_context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/actions/sendtodobatch`,_data);
        }
        if(_context.testreport && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testreports/${_context.testreport}/actions/sendtodobatch`,_data);
        }
        if(_context.release && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/releases/${_context.release}/actions/sendtodobatch`,_data);
        }
        if(_context.task && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/actions/sendtodobatch`,_data);
        }
        if(_context.todo && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/todos/${_context.todo}/actions/sendtodobatch`,_data);
        }
        if(_context.productplan && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/actions/sendtodobatch`,_data);
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
        if(_context.project && _context.doclib && _context.doc && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/sendtoreadbatch`,_data);
        }
        if(_context.product && _context.doclib && _context.doc && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/sendtoreadbatch`,_data);
        }
        if(_context.project && _context.testtask && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/actions/sendtoreadbatch`,_data);
        }
        if(_context.project && _context.testreport && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/actions/sendtoreadbatch`,_data);
        }
        if(_context.project && _context.task && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/actions/sendtoreadbatch`,_data);
        }
        if(_context.project && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/actions/sendtoreadbatch`,_data);
        }
        if(_context.doclib && _context.doc && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/actions/sendtoreadbatch`,_data);
        }
        if(_context.product && _context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/sendtoreadbatch`,_data);
        }
        if(_context.product && _context.productplan && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/actions/sendtoreadbatch`,_data);
        }
        if(_context.product && _context.case && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/actions/sendtoreadbatch`,_data);
        }
        if(_context.product && _context.testreport && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testreports/${_context.testreport}/actions/sendtoreadbatch`,_data);
        }
        if(_context.product && _context.testtask && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/actions/sendtoreadbatch`,_data);
        }
        if(_context.product && _context.testsuite && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/actions/sendtoreadbatch`,_data);
        }
        if(_context.product && _context.release && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/actions/sendtoreadbatch`,_data);
        }
        if(_context.product && _context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/actions/sendtoreadbatch`,_data);
        }
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/actions/sendtoreadbatch`,_data);
        }
        if(_context.story && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/actions/sendtoreadbatch`,_data);
        }
        if(_context.case && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/cases/${_context.case}/actions/sendtoreadbatch`,_data);
        }
        if(_context.testsuite && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testsuites/${_context.testsuite}/actions/sendtoreadbatch`,_data);
        }
        if(_context.testtask && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testtasks/${_context.testtask}/actions/sendtoreadbatch`,_data);
        }
        if(_context.doc && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/docs/${_context.doc}/actions/sendtoreadbatch`,_data);
        }
        if(_context.doclib && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/doclibs/${_context.doclib}/actions/sendtoreadbatch`,_data);
        }
        if(_context.testreport && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/testreports/${_context.testreport}/actions/sendtoreadbatch`,_data);
        }
        if(_context.release && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/releases/${_context.release}/actions/sendtoreadbatch`,_data);
        }
        if(_context.task && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/tasks/${_context.task}/actions/sendtoreadbatch`,_data);
        }
        if(_context.todo && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/todos/${_context.todo}/actions/sendtoreadbatch`,_data);
        }
        if(_context.productplan && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/productplans/${_context.productplan}/actions/sendtoreadbatch`,_data);
        }
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/actions/sendtoreadbatch`,_data);
        }
        return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
}
