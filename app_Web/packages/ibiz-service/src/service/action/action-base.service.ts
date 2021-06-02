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

    protected getAccountCond() {
        return this.condCache.get('account');
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

    protected getMyCond() {
        return this.condCache.get('my');
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
     * FetchMyTrends
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ActionService
     */
    async FetchMyTrends(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.sysaccount && _context.project && _context.testtask && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/testtasks/${_context.testtask}/actions/fetchmytrends`, _data);
        }
        if (_context.sysaccount && _context.project && _context.testreport && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/testreports/${_context.testreport}/actions/fetchmytrends`, _data);
        }
        if (_context.sysaccount && _context.project && _context.story && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/stories/${_context.story}/actions/fetchmytrends`, _data);
        }
        if (_context.sysaccount && _context.project && _context.task && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/tasks/${_context.task}/actions/fetchmytrends`, _data);
        }
        if (_context.sysaccount && _context.project && _context.build && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/builds/${_context.build}/actions/fetchmytrends`, _data);
        }
        if (_context.sysaccount && _context.project && _context.bug && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/bugs/${_context.bug}/actions/fetchmytrends`, _data);
        }
        if (_context.sysaccount && _context.project && _context.productplan && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/productplans/${_context.productplan}/actions/fetchmytrends`, _data);
        }
        if (_context.sysaccount && _context.product && _context.story && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/stories/${_context.story}/actions/fetchmytrends`, _data);
        }
        if (_context.sysaccount && _context.product && _context.productplan && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/productplans/${_context.productplan}/actions/fetchmytrends`, _data);
        }
        if (_context.sysaccount && _context.product && _context.build && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/builds/${_context.build}/actions/fetchmytrends`, _data);
        }
        if (_context.test && _context.testreport && true) {
            return this.http.post(`/tests/${_context.test}/testreports/${_context.testreport}/actions/fetchmytrends`, _data);
        }
        if (_context.test && _context.bug && true) {
            return this.http.post(`/tests/${_context.test}/bugs/${_context.bug}/actions/fetchmytrends`, _data);
        }
        if (_context.project && _context.testtask && true) {
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/actions/fetchmytrends`, _data);
        }
        if (_context.project && _context.testreport && true) {
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/actions/fetchmytrends`, _data);
        }
        if (_context.project && _context.story && true) {
            return this.http.post(`/projects/${_context.project}/stories/${_context.story}/actions/fetchmytrends`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/actions/fetchmytrends`, _data);
        }
        if (_context.project && _context.build && true) {
            return this.http.post(`/projects/${_context.project}/builds/${_context.build}/actions/fetchmytrends`, _data);
        }
        if (_context.project && _context.bug && true) {
            return this.http.post(`/projects/${_context.project}/bugs/${_context.bug}/actions/fetchmytrends`, _data);
        }
        if (_context.project && _context.productplan && true) {
            return this.http.post(`/projects/${_context.project}/productplans/${_context.productplan}/actions/fetchmytrends`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/fetchmytrends`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/actions/fetchmytrends`, _data);
        }
        if (_context.product && _context.build && true) {
            return this.http.post(`/products/${_context.product}/builds/${_context.build}/actions/fetchmytrends`, _data);
        }
        if (_context.sysaccount && _context.todo && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/todos/${_context.todo}/actions/fetchmytrends`, _data);
        }
        if (_context.sysaccount && _context.testtask && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/testtasks/${_context.testtask}/actions/fetchmytrends`, _data);
        }
        if (_context.sysaccount && _context.task && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/tasks/${_context.task}/actions/fetchmytrends`, _data);
        }
        if (_context.sysaccount && _context.story && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/stories/${_context.story}/actions/fetchmytrends`, _data);
        }
        if (_context.sysaccount && _context.project && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/actions/fetchmytrends`, _data);
        }
        if (_context.sysaccount && _context.product && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/actions/fetchmytrends`, _data);
        }
        if (_context.sysaccount && _context.bug && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/bugs/${_context.bug}/actions/fetchmytrends`, _data);
        }
        if (_context.testsuite && true) {
            return this.http.post(`/testsuites/${_context.testsuite}/actions/fetchmytrends`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/actions/fetchmytrends`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/actions/fetchmytrends`, _data);
        }
        if (_context.sysaccount && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/actions/fetchmytrends`, _data);
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
        if (_context.sysaccount && _context.project && _context.testtask && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/testtasks/${_context.testtask}/actions/${_context.action}`, _data);
        }
        if (_context.sysaccount && _context.project && _context.testreport && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/testreports/${_context.testreport}/actions/${_context.action}`, _data);
        }
        if (_context.sysaccount && _context.project && _context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/stories/${_context.story}/actions/${_context.action}`, _data);
        }
        if (_context.sysaccount && _context.project && _context.task && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/tasks/${_context.task}/actions/${_context.action}`, _data);
        }
        if (_context.sysaccount && _context.project && _context.build && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/builds/${_context.build}/actions/${_context.action}`, _data);
        }
        if (_context.sysaccount && _context.project && _context.bug && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/bugs/${_context.bug}/actions/${_context.action}`, _data);
        }
        if (_context.sysaccount && _context.project && _context.productplan && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/productplans/${_context.productplan}/actions/${_context.action}`, _data);
        }
        if (_context.sysaccount && _context.product && _context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/stories/${_context.story}/actions/${_context.action}`, _data);
        }
        if (_context.sysaccount && _context.product && _context.productplan && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/productplans/${_context.productplan}/actions/${_context.action}`, _data);
        }
        if (_context.sysaccount && _context.product && _context.build && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/builds/${_context.build}/actions/${_context.action}`, _data);
        }
        if (_context.test && _context.testreport && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/tests/${_context.test}/testreports/${_context.testreport}/actions/${_context.action}`, _data);
        }
        if (_context.test && _context.bug && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/tests/${_context.test}/bugs/${_context.bug}/actions/${_context.action}`, _data);
        }
        if (_context.project && _context.testtask && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/testtasks/${_context.testtask}/actions/${_context.action}`, _data);
        }
        if (_context.project && _context.testreport && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/testreports/${_context.testreport}/actions/${_context.action}`, _data);
        }
        if (_context.project && _context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/stories/${_context.story}/actions/${_context.action}`, _data);
        }
        if (_context.project && _context.task && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/tasks/${_context.task}/actions/${_context.action}`, _data);
        }
        if (_context.project && _context.build && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/builds/${_context.build}/actions/${_context.action}`, _data);
        }
        if (_context.project && _context.bug && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/bugs/${_context.bug}/actions/${_context.action}`, _data);
        }
        if (_context.project && _context.productplan && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/productplans/${_context.productplan}/actions/${_context.action}`, _data);
        }
        if (_context.product && _context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/stories/${_context.story}/actions/${_context.action}`, _data);
        }
        if (_context.product && _context.productplan && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/productplans/${_context.productplan}/actions/${_context.action}`, _data);
        }
        if (_context.product && _context.build && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/builds/${_context.build}/actions/${_context.action}`, _data);
        }
        if (_context.sysaccount && _context.todo && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/sysaccounts/${_context.sysaccount}/todos/${_context.todo}/actions/${_context.action}`, _data);
        }
        if (_context.sysaccount && _context.testtask && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/sysaccounts/${_context.sysaccount}/testtasks/${_context.testtask}/actions/${_context.action}`, _data);
        }
        if (_context.sysaccount && _context.task && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/sysaccounts/${_context.sysaccount}/tasks/${_context.task}/actions/${_context.action}`, _data);
        }
        if (_context.sysaccount && _context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/sysaccounts/${_context.sysaccount}/stories/${_context.story}/actions/${_context.action}`, _data);
        }
        if (_context.sysaccount && _context.project && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/actions/${_context.action}`, _data);
        }
        if (_context.sysaccount && _context.product && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/actions/${_context.action}`, _data);
        }
        if (_context.sysaccount && _context.bug && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/sysaccounts/${_context.sysaccount}/bugs/${_context.bug}/actions/${_context.action}`, _data);
        }
        if (_context.testsuite && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/testsuites/${_context.testsuite}/actions/${_context.action}`, _data);
        }
        if (_context.project && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/actions/${_context.action}`, _data);
        }
        if (_context.product && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/actions/${_context.action}`, _data);
        }
        if (_context.sysaccount && _context.action) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/sysaccounts/${_context.sysaccount}/actions/${_context.action}`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * FetchMain
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ActionService
     */
    async FetchMain(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.sysaccount && _context.project && _context.testtask && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/testtasks/${_context.testtask}/actions/fetchmain`, _data);
        }
        if (_context.sysaccount && _context.project && _context.testreport && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/testreports/${_context.testreport}/actions/fetchmain`, _data);
        }
        if (_context.sysaccount && _context.project && _context.story && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/stories/${_context.story}/actions/fetchmain`, _data);
        }
        if (_context.sysaccount && _context.project && _context.task && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/tasks/${_context.task}/actions/fetchmain`, _data);
        }
        if (_context.sysaccount && _context.project && _context.build && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/builds/${_context.build}/actions/fetchmain`, _data);
        }
        if (_context.sysaccount && _context.project && _context.bug && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/bugs/${_context.bug}/actions/fetchmain`, _data);
        }
        if (_context.sysaccount && _context.project && _context.productplan && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/productplans/${_context.productplan}/actions/fetchmain`, _data);
        }
        if (_context.sysaccount && _context.product && _context.story && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/stories/${_context.story}/actions/fetchmain`, _data);
        }
        if (_context.sysaccount && _context.product && _context.productplan && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/productplans/${_context.productplan}/actions/fetchmain`, _data);
        }
        if (_context.sysaccount && _context.product && _context.build && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/builds/${_context.build}/actions/fetchmain`, _data);
        }
        if (_context.test && _context.testreport && true) {
            return this.http.post(`/tests/${_context.test}/testreports/${_context.testreport}/actions/fetchmain`, _data);
        }
        if (_context.test && _context.bug && true) {
            return this.http.post(`/tests/${_context.test}/bugs/${_context.bug}/actions/fetchmain`, _data);
        }
        if (_context.project && _context.testtask && true) {
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/actions/fetchmain`, _data);
        }
        if (_context.project && _context.testreport && true) {
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/actions/fetchmain`, _data);
        }
        if (_context.project && _context.story && true) {
            return this.http.post(`/projects/${_context.project}/stories/${_context.story}/actions/fetchmain`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/actions/fetchmain`, _data);
        }
        if (_context.project && _context.build && true) {
            return this.http.post(`/projects/${_context.project}/builds/${_context.build}/actions/fetchmain`, _data);
        }
        if (_context.project && _context.bug && true) {
            return this.http.post(`/projects/${_context.project}/bugs/${_context.bug}/actions/fetchmain`, _data);
        }
        if (_context.project && _context.productplan && true) {
            return this.http.post(`/projects/${_context.project}/productplans/${_context.productplan}/actions/fetchmain`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/fetchmain`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/actions/fetchmain`, _data);
        }
        if (_context.product && _context.build && true) {
            return this.http.post(`/products/${_context.product}/builds/${_context.build}/actions/fetchmain`, _data);
        }
        if (_context.sysaccount && _context.todo && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/todos/${_context.todo}/actions/fetchmain`, _data);
        }
        if (_context.sysaccount && _context.testtask && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/testtasks/${_context.testtask}/actions/fetchmain`, _data);
        }
        if (_context.sysaccount && _context.task && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/tasks/${_context.task}/actions/fetchmain`, _data);
        }
        if (_context.sysaccount && _context.story && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/stories/${_context.story}/actions/fetchmain`, _data);
        }
        if (_context.sysaccount && _context.project && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/actions/fetchmain`, _data);
        }
        if (_context.sysaccount && _context.product && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/actions/fetchmain`, _data);
        }
        if (_context.sysaccount && _context.bug && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/bugs/${_context.bug}/actions/fetchmain`, _data);
        }
        if (_context.testsuite && true) {
            return this.http.post(`/testsuites/${_context.testsuite}/actions/fetchmain`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/actions/fetchmain`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/actions/fetchmain`, _data);
        }
        if (_context.sysaccount && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/actions/fetchmain`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * FetchProduct
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ActionService
     */
    async FetchProduct(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.sysaccount && _context.project && _context.testtask && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/testtasks/${_context.testtask}/actions/fetchproduct`, _data);
        }
        if (_context.sysaccount && _context.project && _context.testreport && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/testreports/${_context.testreport}/actions/fetchproduct`, _data);
        }
        if (_context.sysaccount && _context.project && _context.story && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/stories/${_context.story}/actions/fetchproduct`, _data);
        }
        if (_context.sysaccount && _context.project && _context.task && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/tasks/${_context.task}/actions/fetchproduct`, _data);
        }
        if (_context.sysaccount && _context.project && _context.build && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/builds/${_context.build}/actions/fetchproduct`, _data);
        }
        if (_context.sysaccount && _context.project && _context.bug && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/bugs/${_context.bug}/actions/fetchproduct`, _data);
        }
        if (_context.sysaccount && _context.project && _context.productplan && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/productplans/${_context.productplan}/actions/fetchproduct`, _data);
        }
        if (_context.sysaccount && _context.product && _context.story && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/stories/${_context.story}/actions/fetchproduct`, _data);
        }
        if (_context.sysaccount && _context.product && _context.productplan && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/productplans/${_context.productplan}/actions/fetchproduct`, _data);
        }
        if (_context.sysaccount && _context.product && _context.build && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/builds/${_context.build}/actions/fetchproduct`, _data);
        }
        if (_context.test && _context.testreport && true) {
            return this.http.post(`/tests/${_context.test}/testreports/${_context.testreport}/actions/fetchproduct`, _data);
        }
        if (_context.test && _context.bug && true) {
            return this.http.post(`/tests/${_context.test}/bugs/${_context.bug}/actions/fetchproduct`, _data);
        }
        if (_context.project && _context.testtask && true) {
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/actions/fetchproduct`, _data);
        }
        if (_context.project && _context.testreport && true) {
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/actions/fetchproduct`, _data);
        }
        if (_context.project && _context.story && true) {
            return this.http.post(`/projects/${_context.project}/stories/${_context.story}/actions/fetchproduct`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/actions/fetchproduct`, _data);
        }
        if (_context.project && _context.build && true) {
            return this.http.post(`/projects/${_context.project}/builds/${_context.build}/actions/fetchproduct`, _data);
        }
        if (_context.project && _context.bug && true) {
            return this.http.post(`/projects/${_context.project}/bugs/${_context.bug}/actions/fetchproduct`, _data);
        }
        if (_context.project && _context.productplan && true) {
            return this.http.post(`/projects/${_context.project}/productplans/${_context.productplan}/actions/fetchproduct`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/fetchproduct`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/actions/fetchproduct`, _data);
        }
        if (_context.product && _context.build && true) {
            return this.http.post(`/products/${_context.product}/builds/${_context.build}/actions/fetchproduct`, _data);
        }
        if (_context.sysaccount && _context.todo && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/todos/${_context.todo}/actions/fetchproduct`, _data);
        }
        if (_context.sysaccount && _context.testtask && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/testtasks/${_context.testtask}/actions/fetchproduct`, _data);
        }
        if (_context.sysaccount && _context.task && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/tasks/${_context.task}/actions/fetchproduct`, _data);
        }
        if (_context.sysaccount && _context.story && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/stories/${_context.story}/actions/fetchproduct`, _data);
        }
        if (_context.sysaccount && _context.project && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/actions/fetchproduct`, _data);
        }
        if (_context.sysaccount && _context.product && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/actions/fetchproduct`, _data);
        }
        if (_context.sysaccount && _context.bug && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/bugs/${_context.bug}/actions/fetchproduct`, _data);
        }
        if (_context.testsuite && true) {
            return this.http.post(`/testsuites/${_context.testsuite}/actions/fetchproduct`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/actions/fetchproduct`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/actions/fetchproduct`, _data);
        }
        if (_context.sysaccount && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/actions/fetchproduct`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * FetchProject
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ActionService
     */
    async FetchProject(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.sysaccount && _context.project && _context.testtask && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/testtasks/${_context.testtask}/actions/fetchproject`, _data);
        }
        if (_context.sysaccount && _context.project && _context.testreport && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/testreports/${_context.testreport}/actions/fetchproject`, _data);
        }
        if (_context.sysaccount && _context.project && _context.story && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/stories/${_context.story}/actions/fetchproject`, _data);
        }
        if (_context.sysaccount && _context.project && _context.task && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/tasks/${_context.task}/actions/fetchproject`, _data);
        }
        if (_context.sysaccount && _context.project && _context.build && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/builds/${_context.build}/actions/fetchproject`, _data);
        }
        if (_context.sysaccount && _context.project && _context.bug && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/bugs/${_context.bug}/actions/fetchproject`, _data);
        }
        if (_context.sysaccount && _context.project && _context.productplan && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/productplans/${_context.productplan}/actions/fetchproject`, _data);
        }
        if (_context.sysaccount && _context.product && _context.story && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/stories/${_context.story}/actions/fetchproject`, _data);
        }
        if (_context.sysaccount && _context.product && _context.productplan && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/productplans/${_context.productplan}/actions/fetchproject`, _data);
        }
        if (_context.sysaccount && _context.product && _context.build && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/builds/${_context.build}/actions/fetchproject`, _data);
        }
        if (_context.test && _context.testreport && true) {
            return this.http.post(`/tests/${_context.test}/testreports/${_context.testreport}/actions/fetchproject`, _data);
        }
        if (_context.test && _context.bug && true) {
            return this.http.post(`/tests/${_context.test}/bugs/${_context.bug}/actions/fetchproject`, _data);
        }
        if (_context.project && _context.testtask && true) {
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/actions/fetchproject`, _data);
        }
        if (_context.project && _context.testreport && true) {
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/actions/fetchproject`, _data);
        }
        if (_context.project && _context.story && true) {
            return this.http.post(`/projects/${_context.project}/stories/${_context.story}/actions/fetchproject`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/actions/fetchproject`, _data);
        }
        if (_context.project && _context.build && true) {
            return this.http.post(`/projects/${_context.project}/builds/${_context.build}/actions/fetchproject`, _data);
        }
        if (_context.project && _context.bug && true) {
            return this.http.post(`/projects/${_context.project}/bugs/${_context.bug}/actions/fetchproject`, _data);
        }
        if (_context.project && _context.productplan && true) {
            return this.http.post(`/projects/${_context.project}/productplans/${_context.productplan}/actions/fetchproject`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/fetchproject`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/actions/fetchproject`, _data);
        }
        if (_context.product && _context.build && true) {
            return this.http.post(`/products/${_context.product}/builds/${_context.build}/actions/fetchproject`, _data);
        }
        if (_context.sysaccount && _context.todo && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/todos/${_context.todo}/actions/fetchproject`, _data);
        }
        if (_context.sysaccount && _context.testtask && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/testtasks/${_context.testtask}/actions/fetchproject`, _data);
        }
        if (_context.sysaccount && _context.task && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/tasks/${_context.task}/actions/fetchproject`, _data);
        }
        if (_context.sysaccount && _context.story && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/stories/${_context.story}/actions/fetchproject`, _data);
        }
        if (_context.sysaccount && _context.project && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/actions/fetchproject`, _data);
        }
        if (_context.sysaccount && _context.product && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/actions/fetchproject`, _data);
        }
        if (_context.sysaccount && _context.bug && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/bugs/${_context.bug}/actions/fetchproject`, _data);
        }
        if (_context.testsuite && true) {
            return this.http.post(`/testsuites/${_context.testsuite}/actions/fetchproject`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/actions/fetchproject`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/actions/fetchproject`, _data);
        }
        if (_context.sysaccount && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/actions/fetchproject`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * FetchMy
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ActionService
     */
    async FetchMy(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.sysaccount && _context.project && _context.testtask && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/testtasks/${_context.testtask}/actions/fetchmy`, _data);
        }
        if (_context.sysaccount && _context.project && _context.testreport && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/testreports/${_context.testreport}/actions/fetchmy`, _data);
        }
        if (_context.sysaccount && _context.project && _context.story && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/stories/${_context.story}/actions/fetchmy`, _data);
        }
        if (_context.sysaccount && _context.project && _context.task && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/tasks/${_context.task}/actions/fetchmy`, _data);
        }
        if (_context.sysaccount && _context.project && _context.build && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/builds/${_context.build}/actions/fetchmy`, _data);
        }
        if (_context.sysaccount && _context.project && _context.bug && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/bugs/${_context.bug}/actions/fetchmy`, _data);
        }
        if (_context.sysaccount && _context.project && _context.productplan && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/productplans/${_context.productplan}/actions/fetchmy`, _data);
        }
        if (_context.sysaccount && _context.product && _context.story && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/stories/${_context.story}/actions/fetchmy`, _data);
        }
        if (_context.sysaccount && _context.product && _context.productplan && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/productplans/${_context.productplan}/actions/fetchmy`, _data);
        }
        if (_context.sysaccount && _context.product && _context.build && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/builds/${_context.build}/actions/fetchmy`, _data);
        }
        if (_context.test && _context.testreport && true) {
            return this.http.post(`/tests/${_context.test}/testreports/${_context.testreport}/actions/fetchmy`, _data);
        }
        if (_context.test && _context.bug && true) {
            return this.http.post(`/tests/${_context.test}/bugs/${_context.bug}/actions/fetchmy`, _data);
        }
        if (_context.project && _context.testtask && true) {
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/actions/fetchmy`, _data);
        }
        if (_context.project && _context.testreport && true) {
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/actions/fetchmy`, _data);
        }
        if (_context.project && _context.story && true) {
            return this.http.post(`/projects/${_context.project}/stories/${_context.story}/actions/fetchmy`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/actions/fetchmy`, _data);
        }
        if (_context.project && _context.build && true) {
            return this.http.post(`/projects/${_context.project}/builds/${_context.build}/actions/fetchmy`, _data);
        }
        if (_context.project && _context.bug && true) {
            return this.http.post(`/projects/${_context.project}/bugs/${_context.bug}/actions/fetchmy`, _data);
        }
        if (_context.project && _context.productplan && true) {
            return this.http.post(`/projects/${_context.project}/productplans/${_context.productplan}/actions/fetchmy`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/fetchmy`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/actions/fetchmy`, _data);
        }
        if (_context.product && _context.build && true) {
            return this.http.post(`/products/${_context.product}/builds/${_context.build}/actions/fetchmy`, _data);
        }
        if (_context.sysaccount && _context.todo && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/todos/${_context.todo}/actions/fetchmy`, _data);
        }
        if (_context.sysaccount && _context.testtask && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/testtasks/${_context.testtask}/actions/fetchmy`, _data);
        }
        if (_context.sysaccount && _context.task && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/tasks/${_context.task}/actions/fetchmy`, _data);
        }
        if (_context.sysaccount && _context.story && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/stories/${_context.story}/actions/fetchmy`, _data);
        }
        if (_context.sysaccount && _context.project && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/actions/fetchmy`, _data);
        }
        if (_context.sysaccount && _context.product && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/actions/fetchmy`, _data);
        }
        if (_context.sysaccount && _context.bug && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/bugs/${_context.bug}/actions/fetchmy`, _data);
        }
        if (_context.testsuite && true) {
            return this.http.post(`/testsuites/${_context.testsuite}/actions/fetchmy`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/actions/fetchmy`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/actions/fetchmy`, _data);
        }
        if (_context.sysaccount && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/actions/fetchmy`, _data);
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
        if (_context.sysaccount && _context.project && _context.testtask && _context.action) {
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/testtasks/${_context.testtask}/actions/${_context.action}`);
            return res;
        }
        if (_context.sysaccount && _context.project && _context.testreport && _context.action) {
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/testreports/${_context.testreport}/actions/${_context.action}`);
            return res;
        }
        if (_context.sysaccount && _context.project && _context.story && _context.action) {
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/stories/${_context.story}/actions/${_context.action}`);
            return res;
        }
        if (_context.sysaccount && _context.project && _context.task && _context.action) {
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/tasks/${_context.task}/actions/${_context.action}`);
            return res;
        }
        if (_context.sysaccount && _context.project && _context.build && _context.action) {
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/builds/${_context.build}/actions/${_context.action}`);
            return res;
        }
        if (_context.sysaccount && _context.project && _context.bug && _context.action) {
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/bugs/${_context.bug}/actions/${_context.action}`);
            return res;
        }
        if (_context.sysaccount && _context.project && _context.productplan && _context.action) {
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/productplans/${_context.productplan}/actions/${_context.action}`);
            return res;
        }
        if (_context.sysaccount && _context.product && _context.story && _context.action) {
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/stories/${_context.story}/actions/${_context.action}`);
            return res;
        }
        if (_context.sysaccount && _context.product && _context.productplan && _context.action) {
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/productplans/${_context.productplan}/actions/${_context.action}`);
            return res;
        }
        if (_context.sysaccount && _context.product && _context.build && _context.action) {
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/builds/${_context.build}/actions/${_context.action}`);
            return res;
        }
        if (_context.test && _context.testreport && _context.action) {
            const res = await this.http.get(`/tests/${_context.test}/testreports/${_context.testreport}/actions/${_context.action}`);
            return res;
        }
        if (_context.test && _context.bug && _context.action) {
            const res = await this.http.get(`/tests/${_context.test}/bugs/${_context.bug}/actions/${_context.action}`);
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
        if (_context.project && _context.story && _context.action) {
            const res = await this.http.get(`/projects/${_context.project}/stories/${_context.story}/actions/${_context.action}`);
            return res;
        }
        if (_context.project && _context.task && _context.action) {
            const res = await this.http.get(`/projects/${_context.project}/tasks/${_context.task}/actions/${_context.action}`);
            return res;
        }
        if (_context.project && _context.build && _context.action) {
            const res = await this.http.get(`/projects/${_context.project}/builds/${_context.build}/actions/${_context.action}`);
            return res;
        }
        if (_context.project && _context.bug && _context.action) {
            const res = await this.http.get(`/projects/${_context.project}/bugs/${_context.bug}/actions/${_context.action}`);
            return res;
        }
        if (_context.project && _context.productplan && _context.action) {
            const res = await this.http.get(`/projects/${_context.project}/productplans/${_context.productplan}/actions/${_context.action}`);
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
        if (_context.product && _context.build && _context.action) {
            const res = await this.http.get(`/products/${_context.product}/builds/${_context.build}/actions/${_context.action}`);
            return res;
        }
        if (_context.sysaccount && _context.todo && _context.action) {
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/todos/${_context.todo}/actions/${_context.action}`);
            return res;
        }
        if (_context.sysaccount && _context.testtask && _context.action) {
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/testtasks/${_context.testtask}/actions/${_context.action}`);
            return res;
        }
        if (_context.sysaccount && _context.task && _context.action) {
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/tasks/${_context.task}/actions/${_context.action}`);
            return res;
        }
        if (_context.sysaccount && _context.story && _context.action) {
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/stories/${_context.story}/actions/${_context.action}`);
            return res;
        }
        if (_context.sysaccount && _context.project && _context.action) {
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/actions/${_context.action}`);
            return res;
        }
        if (_context.sysaccount && _context.product && _context.action) {
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/actions/${_context.action}`);
            return res;
        }
        if (_context.sysaccount && _context.bug && _context.action) {
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/bugs/${_context.bug}/actions/${_context.action}`);
            return res;
        }
        if (_context.testsuite && _context.action) {
            const res = await this.http.get(`/testsuites/${_context.testsuite}/actions/${_context.action}`);
            return res;
        }
        if (_context.project && _context.action) {
            const res = await this.http.get(`/projects/${_context.project}/actions/${_context.action}`);
            return res;
        }
        if (_context.product && _context.action) {
            const res = await this.http.get(`/products/${_context.product}/actions/${_context.action}`);
            return res;
        }
        if (_context.sysaccount && _context.action) {
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/actions/${_context.action}`);
            return res;
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
        if (_context.sysaccount && _context.project && _context.testtask && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/testtasks/${_context.testtask}/actions`, _data);
        }
        if (_context.sysaccount && _context.project && _context.testreport && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/testreports/${_context.testreport}/actions`, _data);
        }
        if (_context.sysaccount && _context.project && _context.story && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/stories/${_context.story}/actions`, _data);
        }
        if (_context.sysaccount && _context.project && _context.task && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/tasks/${_context.task}/actions`, _data);
        }
        if (_context.sysaccount && _context.project && _context.build && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/builds/${_context.build}/actions`, _data);
        }
        if (_context.sysaccount && _context.project && _context.bug && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/bugs/${_context.bug}/actions`, _data);
        }
        if (_context.sysaccount && _context.project && _context.productplan && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/productplans/${_context.productplan}/actions`, _data);
        }
        if (_context.sysaccount && _context.product && _context.story && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/stories/${_context.story}/actions`, _data);
        }
        if (_context.sysaccount && _context.product && _context.productplan && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/productplans/${_context.productplan}/actions`, _data);
        }
        if (_context.sysaccount && _context.product && _context.build && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/builds/${_context.build}/actions`, _data);
        }
        if (_context.test && _context.testreport && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/tests/${_context.test}/testreports/${_context.testreport}/actions`, _data);
        }
        if (_context.test && _context.bug && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/tests/${_context.test}/bugs/${_context.bug}/actions`, _data);
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
        if (_context.project && _context.story && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projects/${_context.project}/stories/${_context.story}/actions`, _data);
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
        if (_context.project && _context.build && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projects/${_context.project}/builds/${_context.build}/actions`, _data);
        }
        if (_context.project && _context.bug && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projects/${_context.project}/bugs/${_context.bug}/actions`, _data);
        }
        if (_context.project && _context.productplan && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projects/${_context.project}/productplans/${_context.productplan}/actions`, _data);
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
        if (_context.product && _context.build && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/builds/${_context.build}/actions`, _data);
        }
        if (_context.sysaccount && _context.todo && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/sysaccounts/${_context.sysaccount}/todos/${_context.todo}/actions`, _data);
        }
        if (_context.sysaccount && _context.testtask && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/sysaccounts/${_context.sysaccount}/testtasks/${_context.testtask}/actions`, _data);
        }
        if (_context.sysaccount && _context.task && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/sysaccounts/${_context.sysaccount}/tasks/${_context.task}/actions`, _data);
        }
        if (_context.sysaccount && _context.story && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/sysaccounts/${_context.sysaccount}/stories/${_context.story}/actions`, _data);
        }
        if (_context.sysaccount && _context.project && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/actions`, _data);
        }
        if (_context.sysaccount && _context.product && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/actions`, _data);
        }
        if (_context.sysaccount && _context.bug && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/sysaccounts/${_context.sysaccount}/bugs/${_context.bug}/actions`, _data);
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
        if (_context.sysaccount && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/sysaccounts/${_context.sysaccount}/actions`, _data);
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
        if (_context.sysaccount && _context.project && _context.testtask && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/testtasks/${_context.testtask}/actions/getdraft`, _data);
            return res;
        }
        if (_context.sysaccount && _context.project && _context.testreport && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/testreports/${_context.testreport}/actions/getdraft`, _data);
            return res;
        }
        if (_context.sysaccount && _context.project && _context.story && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/stories/${_context.story}/actions/getdraft`, _data);
            return res;
        }
        if (_context.sysaccount && _context.project && _context.task && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/tasks/${_context.task}/actions/getdraft`, _data);
            return res;
        }
        if (_context.sysaccount && _context.project && _context.build && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/builds/${_context.build}/actions/getdraft`, _data);
            return res;
        }
        if (_context.sysaccount && _context.project && _context.bug && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/bugs/${_context.bug}/actions/getdraft`, _data);
            return res;
        }
        if (_context.sysaccount && _context.project && _context.productplan && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/productplans/${_context.productplan}/actions/getdraft`, _data);
            return res;
        }
        if (_context.sysaccount && _context.product && _context.story && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/stories/${_context.story}/actions/getdraft`, _data);
            return res;
        }
        if (_context.sysaccount && _context.product && _context.productplan && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/productplans/${_context.productplan}/actions/getdraft`, _data);
            return res;
        }
        if (_context.sysaccount && _context.product && _context.build && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/builds/${_context.build}/actions/getdraft`, _data);
            return res;
        }
        if (_context.test && _context.testreport && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/tests/${_context.test}/testreports/${_context.testreport}/actions/getdraft`, _data);
            return res;
        }
        if (_context.test && _context.bug && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/tests/${_context.test}/bugs/${_context.bug}/actions/getdraft`, _data);
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
        if (_context.project && _context.story && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/stories/${_context.story}/actions/getdraft`, _data);
            return res;
        }
        if (_context.project && _context.task && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/tasks/${_context.task}/actions/getdraft`, _data);
            return res;
        }
        if (_context.project && _context.build && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/builds/${_context.build}/actions/getdraft`, _data);
            return res;
        }
        if (_context.project && _context.bug && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/bugs/${_context.bug}/actions/getdraft`, _data);
            return res;
        }
        if (_context.project && _context.productplan && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/productplans/${_context.productplan}/actions/getdraft`, _data);
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
        if (_context.product && _context.build && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/builds/${_context.build}/actions/getdraft`, _data);
            return res;
        }
        if (_context.sysaccount && _context.todo && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/todos/${_context.todo}/actions/getdraft`, _data);
            return res;
        }
        if (_context.sysaccount && _context.testtask && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/testtasks/${_context.testtask}/actions/getdraft`, _data);
            return res;
        }
        if (_context.sysaccount && _context.task && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/tasks/${_context.task}/actions/getdraft`, _data);
            return res;
        }
        if (_context.sysaccount && _context.story && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/stories/${_context.story}/actions/getdraft`, _data);
            return res;
        }
        if (_context.sysaccount && _context.project && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/actions/getdraft`, _data);
            return res;
        }
        if (_context.sysaccount && _context.product && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/actions/getdraft`, _data);
            return res;
        }
        if (_context.sysaccount && _context.bug && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/bugs/${_context.bug}/actions/getdraft`, _data);
            return res;
        }
        if (_context.testsuite && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/testsuites/${_context.testsuite}/actions/getdraft`, _data);
            return res;
        }
        if (_context.project && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/actions/getdraft`, _data);
            return res;
        }
        if (_context.product && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/actions/getdraft`, _data);
            return res;
        }
        if (_context.sysaccount && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/sysaccounts/${_context.sysaccount}/actions/getdraft`, _data);
            return res;
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
    /**
     * FetchAccount
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ActionService
     */
    async FetchAccount(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.sysaccount && _context.project && _context.testtask && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/testtasks/${_context.testtask}/actions/fetchaccount`, _data);
        }
        if (_context.sysaccount && _context.project && _context.testreport && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/testreports/${_context.testreport}/actions/fetchaccount`, _data);
        }
        if (_context.sysaccount && _context.project && _context.story && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/stories/${_context.story}/actions/fetchaccount`, _data);
        }
        if (_context.sysaccount && _context.project && _context.task && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/tasks/${_context.task}/actions/fetchaccount`, _data);
        }
        if (_context.sysaccount && _context.project && _context.build && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/builds/${_context.build}/actions/fetchaccount`, _data);
        }
        if (_context.sysaccount && _context.project && _context.bug && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/bugs/${_context.bug}/actions/fetchaccount`, _data);
        }
        if (_context.sysaccount && _context.project && _context.productplan && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/productplans/${_context.productplan}/actions/fetchaccount`, _data);
        }
        if (_context.sysaccount && _context.product && _context.story && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/stories/${_context.story}/actions/fetchaccount`, _data);
        }
        if (_context.sysaccount && _context.product && _context.productplan && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/productplans/${_context.productplan}/actions/fetchaccount`, _data);
        }
        if (_context.sysaccount && _context.product && _context.build && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/builds/${_context.build}/actions/fetchaccount`, _data);
        }
        if (_context.test && _context.testreport && true) {
            return this.http.post(`/tests/${_context.test}/testreports/${_context.testreport}/actions/fetchaccount`, _data);
        }
        if (_context.test && _context.bug && true) {
            return this.http.post(`/tests/${_context.test}/bugs/${_context.bug}/actions/fetchaccount`, _data);
        }
        if (_context.project && _context.testtask && true) {
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/actions/fetchaccount`, _data);
        }
        if (_context.project && _context.testreport && true) {
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/actions/fetchaccount`, _data);
        }
        if (_context.project && _context.story && true) {
            return this.http.post(`/projects/${_context.project}/stories/${_context.story}/actions/fetchaccount`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/actions/fetchaccount`, _data);
        }
        if (_context.project && _context.build && true) {
            return this.http.post(`/projects/${_context.project}/builds/${_context.build}/actions/fetchaccount`, _data);
        }
        if (_context.project && _context.bug && true) {
            return this.http.post(`/projects/${_context.project}/bugs/${_context.bug}/actions/fetchaccount`, _data);
        }
        if (_context.project && _context.productplan && true) {
            return this.http.post(`/projects/${_context.project}/productplans/${_context.productplan}/actions/fetchaccount`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/fetchaccount`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/actions/fetchaccount`, _data);
        }
        if (_context.product && _context.build && true) {
            return this.http.post(`/products/${_context.product}/builds/${_context.build}/actions/fetchaccount`, _data);
        }
        if (_context.sysaccount && _context.todo && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/todos/${_context.todo}/actions/fetchaccount`, _data);
        }
        if (_context.sysaccount && _context.testtask && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/testtasks/${_context.testtask}/actions/fetchaccount`, _data);
        }
        if (_context.sysaccount && _context.task && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/tasks/${_context.task}/actions/fetchaccount`, _data);
        }
        if (_context.sysaccount && _context.story && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/stories/${_context.story}/actions/fetchaccount`, _data);
        }
        if (_context.sysaccount && _context.project && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/projects/${_context.project}/actions/fetchaccount`, _data);
        }
        if (_context.sysaccount && _context.product && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/products/${_context.product}/actions/fetchaccount`, _data);
        }
        if (_context.sysaccount && _context.bug && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/bugs/${_context.bug}/actions/fetchaccount`, _data);
        }
        if (_context.testsuite && true) {
            return this.http.post(`/testsuites/${_context.testsuite}/actions/fetchaccount`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/actions/fetchaccount`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/actions/fetchaccount`, _data);
        }
        if (_context.sysaccount && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/actions/fetchaccount`, _data);
        }
    return new HttpResponse(null, { status: 404, statusText: '无匹配请求地址!' });
    }
}
