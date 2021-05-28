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
     * FetchMobType
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ActionService
     */
    async FetchMobType(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.doclib && _context.doc && true) {
            return this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/actions/fetchmobtype`, _data);
        }
        if (_context.ibzlib && true) {
            return this.http.post(`/ibzlibs/${_context.ibzlib}/actions/fetchmobtype`, _data);
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
        if (_context.ibzreportly && true) {
            return this.http.post(`/ibzreportlies/${_context.ibzreportly}/actions/fetchmobtype`, _data);
        }
        if (_context.ibzmonthly && true) {
            return this.http.post(`/ibzmonthlies/${_context.ibzmonthly}/actions/fetchmobtype`, _data);
        }
        if (_context.ibzdaily && true) {
            return this.http.post(`/ibzdailies/${_context.ibzdaily}/actions/fetchmobtype`, _data);
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
        if (_context.ibzweekly && true) {
            return this.http.post(`/ibzweeklies/${_context.ibzweekly}/actions/fetchmobtype`, _data);
        }
        if (_context.todo && true) {
            return this.http.post(`/todos/${_context.todo}/actions/fetchmobtype`, _data);
        }
        return this.http.post(`/actions/fetchmobtype`, _data);
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
        if (_context.doclib && _context.doc && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/doclibs/${_context.doclib}/docs/${_context.doc}/actions/getdraft`, _data);
            return res;
        }
        if (_context.ibzlib && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/ibzlibs/${_context.ibzlib}/actions/getdraft`, _data);
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
        if (_context.ibzreportly && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/ibzreportlies/${_context.ibzreportly}/actions/getdraft`, _data);
            return res;
        }
        if (_context.ibzmonthly && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/ibzmonthlies/${_context.ibzmonthly}/actions/getdraft`, _data);
            return res;
        }
        if (_context.ibzdaily && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/ibzdailies/${_context.ibzdaily}/actions/getdraft`, _data);
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
        if (_context.ibzweekly && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/ibzweeklies/${_context.ibzweekly}/actions/getdraft`, _data);
            return res;
        }
        if (_context.todo && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/todos/${_context.todo}/actions/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/actions/getdraft`, _data);
        return res;
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
        if (_context.doclib && _context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}`, _data);
        }
        if (_context.ibzlib && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/ibzlibs/${_context.ibzlib}/actions/${_context.action}`, _data);
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
        if (_context.ibzreportly && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/ibzreportlies/${_context.ibzreportly}/actions/${_context.action}`, _data);
        }
        if (_context.ibzmonthly && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/ibzmonthlies/${_context.ibzmonthly}/actions/${_context.action}`, _data);
        }
        if (_context.ibzdaily && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/ibzdailies/${_context.ibzdaily}/actions/${_context.action}`, _data);
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
        if (_context.ibzweekly && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/ibzweeklies/${_context.ibzweekly}/actions/${_context.action}`, _data);
        }
        if (_context.todo && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/todos/${_context.todo}/actions/${_context.action}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/actions/${_context.action}`, _data);
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
        if (_context.doclib && _context.doc && _context.action) {
            const res = await this.http.get(`/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}`);
            return res;
        }
        if (_context.ibzlib && _context.action) {
            const res = await this.http.get(`/ibzlibs/${_context.ibzlib}/actions/${_context.action}`);
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
        if (_context.ibzreportly && _context.action) {
            const res = await this.http.get(`/ibzreportlies/${_context.ibzreportly}/actions/${_context.action}`);
            return res;
        }
        if (_context.ibzmonthly && _context.action) {
            const res = await this.http.get(`/ibzmonthlies/${_context.ibzmonthly}/actions/${_context.action}`);
            return res;
        }
        if (_context.ibzdaily && _context.action) {
            const res = await this.http.get(`/ibzdailies/${_context.ibzdaily}/actions/${_context.action}`);
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
        if (_context.ibzweekly && _context.action) {
            const res = await this.http.get(`/ibzweeklies/${_context.ibzweekly}/actions/${_context.action}`);
            return res;
        }
        if (_context.todo && _context.action) {
            const res = await this.http.get(`/todos/${_context.todo}/actions/${_context.action}`);
            return res;
        }
        const res = await this.http.get(`/actions/${_context.action}`);
        return res;
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
        if (_context.ibzlib && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/ibzlibs/${_context.ibzlib}/actions`, _data);
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
        if (_context.ibzreportly && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/ibzreportlies/${_context.ibzreportly}/actions`, _data);
        }
        if (_context.ibzmonthly && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/ibzmonthlies/${_context.ibzmonthly}/actions`, _data);
        }
        if (_context.ibzdaily && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/ibzdailies/${_context.ibzdaily}/actions`, _data);
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
        if (_context.ibzweekly && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/ibzweeklies/${_context.ibzweekly}/actions`, _data);
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
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/actions`, _data);
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
        if (_context.doclib && _context.doc && true) {
            return this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/actions/fetchproducttrends`, _data);
        }
        if (_context.ibzlib && true) {
            return this.http.post(`/ibzlibs/${_context.ibzlib}/actions/fetchproducttrends`, _data);
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
        if (_context.ibzreportly && true) {
            return this.http.post(`/ibzreportlies/${_context.ibzreportly}/actions/fetchproducttrends`, _data);
        }
        if (_context.ibzmonthly && true) {
            return this.http.post(`/ibzmonthlies/${_context.ibzmonthly}/actions/fetchproducttrends`, _data);
        }
        if (_context.ibzdaily && true) {
            return this.http.post(`/ibzdailies/${_context.ibzdaily}/actions/fetchproducttrends`, _data);
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
        if (_context.ibzweekly && true) {
            return this.http.post(`/ibzweeklies/${_context.ibzweekly}/actions/fetchproducttrends`, _data);
        }
        if (_context.todo && true) {
            return this.http.post(`/todos/${_context.todo}/actions/fetchproducttrends`, _data);
        }
        return this.http.post(`/actions/fetchproducttrends`, _data);
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
        if (_context.doclib && _context.doc && true) {
            return this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/actions/fetchdefault`, _data);
        }
        if (_context.ibzlib && true) {
            return this.http.post(`/ibzlibs/${_context.ibzlib}/actions/fetchdefault`, _data);
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
        if (_context.ibzreportly && true) {
            return this.http.post(`/ibzreportlies/${_context.ibzreportly}/actions/fetchdefault`, _data);
        }
        if (_context.ibzmonthly && true) {
            return this.http.post(`/ibzmonthlies/${_context.ibzmonthly}/actions/fetchdefault`, _data);
        }
        if (_context.ibzdaily && true) {
            return this.http.post(`/ibzdailies/${_context.ibzdaily}/actions/fetchdefault`, _data);
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
        if (_context.ibzweekly && true) {
            return this.http.post(`/ibzweeklies/${_context.ibzweekly}/actions/fetchdefault`, _data);
        }
        if (_context.todo && true) {
            return this.http.post(`/todos/${_context.todo}/actions/fetchdefault`, _data);
        }
        return this.http.post(`/actions/fetchdefault`, _data);
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
        if (_context.doclib && _context.doc && true) {
            return this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/actions/fetchtype`, _data);
        }
        if (_context.ibzlib && true) {
            return this.http.post(`/ibzlibs/${_context.ibzlib}/actions/fetchtype`, _data);
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
        if (_context.ibzreportly && true) {
            return this.http.post(`/ibzreportlies/${_context.ibzreportly}/actions/fetchtype`, _data);
        }
        if (_context.ibzmonthly && true) {
            return this.http.post(`/ibzmonthlies/${_context.ibzmonthly}/actions/fetchtype`, _data);
        }
        if (_context.ibzdaily && true) {
            return this.http.post(`/ibzdailies/${_context.ibzdaily}/actions/fetchtype`, _data);
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
        if (_context.ibzweekly && true) {
            return this.http.post(`/ibzweeklies/${_context.ibzweekly}/actions/fetchtype`, _data);
        }
        if (_context.todo && true) {
            return this.http.post(`/todos/${_context.todo}/actions/fetchtype`, _data);
        }
        return this.http.post(`/actions/fetchtype`, _data);
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
        if (_context.doclib && _context.doc && true) {
            return this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/actions/fetchprojecttrends`, _data);
        }
        if (_context.ibzlib && true) {
            return this.http.post(`/ibzlibs/${_context.ibzlib}/actions/fetchprojecttrends`, _data);
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
        if (_context.ibzreportly && true) {
            return this.http.post(`/ibzreportlies/${_context.ibzreportly}/actions/fetchprojecttrends`, _data);
        }
        if (_context.ibzmonthly && true) {
            return this.http.post(`/ibzmonthlies/${_context.ibzmonthly}/actions/fetchprojecttrends`, _data);
        }
        if (_context.ibzdaily && true) {
            return this.http.post(`/ibzdailies/${_context.ibzdaily}/actions/fetchprojecttrends`, _data);
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
        if (_context.ibzweekly && true) {
            return this.http.post(`/ibzweeklies/${_context.ibzweekly}/actions/fetchprojecttrends`, _data);
        }
        if (_context.todo && true) {
            return this.http.post(`/todos/${_context.todo}/actions/fetchprojecttrends`, _data);
        }
        return this.http.post(`/actions/fetchprojecttrends`, _data);
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
        if (_context.doclib && _context.doc && true) {
            return this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/actions/fetchqueryuseryear`, _data);
        }
        if (_context.ibzlib && true) {
            return this.http.post(`/ibzlibs/${_context.ibzlib}/actions/fetchqueryuseryear`, _data);
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
        if (_context.ibzreportly && true) {
            return this.http.post(`/ibzreportlies/${_context.ibzreportly}/actions/fetchqueryuseryear`, _data);
        }
        if (_context.ibzmonthly && true) {
            return this.http.post(`/ibzmonthlies/${_context.ibzmonthly}/actions/fetchqueryuseryear`, _data);
        }
        if (_context.ibzdaily && true) {
            return this.http.post(`/ibzdailies/${_context.ibzdaily}/actions/fetchqueryuseryear`, _data);
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
        if (_context.ibzweekly && true) {
            return this.http.post(`/ibzweeklies/${_context.ibzweekly}/actions/fetchqueryuseryear`, _data);
        }
        if (_context.todo && true) {
            return this.http.post(`/todos/${_context.todo}/actions/fetchqueryuseryear`, _data);
        }
        return this.http.post(`/actions/fetchqueryuseryear`, _data);
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
        if (_context.doclib && _context.doc && true) {
            return this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/actions/fetchmytrends`, _data);
        }
        if (_context.ibzlib && true) {
            return this.http.post(`/ibzlibs/${_context.ibzlib}/actions/fetchmytrends`, _data);
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
        if (_context.ibzreportly && true) {
            return this.http.post(`/ibzreportlies/${_context.ibzreportly}/actions/fetchmytrends`, _data);
        }
        if (_context.ibzmonthly && true) {
            return this.http.post(`/ibzmonthlies/${_context.ibzmonthly}/actions/fetchmytrends`, _data);
        }
        if (_context.ibzdaily && true) {
            return this.http.post(`/ibzdailies/${_context.ibzdaily}/actions/fetchmytrends`, _data);
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
        if (_context.ibzweekly && true) {
            return this.http.post(`/ibzweeklies/${_context.ibzweekly}/actions/fetchmytrends`, _data);
        }
        if (_context.todo && true) {
            return this.http.post(`/todos/${_context.todo}/actions/fetchmytrends`, _data);
        }
        return this.http.post(`/actions/fetchmytrends`, _data);
    }
}
