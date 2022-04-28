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
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/Action.json';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'comment';
    protected quickSearchFields = ['actor',];
    protected selectContextParam = {
    };

    constructor(opts?: any) {
        super(opts, 'Action');
    }

    newEntity(data: IAction): Action {
        return new Action(data);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IAction> {
        const entity = await super.getLocal(context, srfKey);
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
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof ActionService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.doclib && _context.doc && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions`, _data);
            return res;
        }
        if (_context.project && _context.doclib && _context.doc && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions`, _data);
            return res;
        }
        if (_context.product && _context.project && _context.testtask && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testtasks/${encodeURIComponent(_context.testtask)}/actions`, _data);
            return res;
        }
        if (_context.product && _context.project && _context.testreport && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/actions`, _data);
            return res;
        }
        if (_context.product && _context.project && _context.story && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/stories/${encodeURIComponent(_context.story)}/actions`, _data);
            return res;
        }
        if (_context.product && _context.project && _context.task && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/tasks/${encodeURIComponent(_context.task)}/actions`, _data);
            return res;
        }
        if (_context.product && _context.project && _context.build && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}/actions`, _data);
            return res;
        }
        if (_context.product && _context.project && _context.bug && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/bugs/${encodeURIComponent(_context.bug)}/actions`, _data);
            return res;
        }
        if (_context.product && _context.project && _context.productplan && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/productplans/${encodeURIComponent(_context.productplan)}/actions`, _data);
            return res;
        }
        if (_context.product && _context.project && _context.doclib && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/actions`, _data);
            return res;
        }
        if (_context.product && _context.doclib && _context.doc && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions`, _data);
            return res;
        }
        if (_context.test && _context.testreport && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testreports/${encodeURIComponent(_context.testreport)}/actions`, _data);
            return res;
        }
        if (_context.test && _context.testcase && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testcases/${encodeURIComponent(_context.testcase)}/actions`, _data);
            return res;
        }
        if (_context.test && _context.bug && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/bugs/${encodeURIComponent(_context.bug)}/actions`, _data);
            return res;
        }
        if (_context.project && _context.testtask && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/testtasks/${encodeURIComponent(_context.testtask)}/actions`, _data);
            return res;
        }
        if (_context.project && _context.testreport && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/actions`, _data);
            return res;
        }
        if (_context.project && _context.story && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/stories/${encodeURIComponent(_context.story)}/actions`, _data);
            return res;
        }
        if (_context.project && _context.task && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/tasks/${encodeURIComponent(_context.task)}/actions`, _data);
            return res;
        }
        if (_context.project && _context.build && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}/actions`, _data);
            return res;
        }
        if (_context.project && _context.bug && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/bugs/${encodeURIComponent(_context.bug)}/actions`, _data);
            return res;
        }
        if (_context.project && _context.productplan && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/productplans/${encodeURIComponent(_context.productplan)}/actions`, _data);
            return res;
        }
        if (_context.project && _context.doclib && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/actions`, _data);
            return res;
        }
        if (_context.product && _context.project && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/actions`, _data);
            return res;
        }
        if (_context.product && _context.story && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/stories/${encodeURIComponent(_context.story)}/actions`, _data);
            return res;
        }
        if (_context.product && _context.productrelease && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/productreleases/${encodeURIComponent(_context.productrelease)}/actions`, _data);
            return res;
        }
        if (_context.product && _context.productplan && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/productplans/${encodeURIComponent(_context.productplan)}/actions`, _data);
            return res;
        }
        if (_context.product && _context.build && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/builds/${encodeURIComponent(_context.build)}/actions`, _data);
            return res;
        }
        if (_context.product && _context.doclib && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/doclibs/${encodeURIComponent(_context.doclib)}/actions`, _data);
            return res;
        }
        if (_context.test && _context.testtask && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testtasks/${encodeURIComponent(_context.testtask)}/actions`, _data);
            return res;
        }
        if (_context.doclib && _context.doc && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions`, _data);
            return res;
        }
        if (_context.sysaccount && _context.todo && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/todos/${encodeURIComponent(_context.todo)}/actions`, _data);
            return res;
        }
        if (_context.sysaccount && _context.doc && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/docs/${encodeURIComponent(_context.doc)}/actions`, _data);
            return res;
        }
        if (_context.weekly && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/weeklies/${encodeURIComponent(_context.weekly)}/actions`, _data);
            return res;
        }
        if (_context.todo && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/todos/${encodeURIComponent(_context.todo)}/actions`, _data);
            return res;
        }
        if (_context.testsuite && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/testsuites/${encodeURIComponent(_context.testsuite)}/actions`, _data);
            return res;
        }
        if (_context.reportly && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/reportlies/${encodeURIComponent(_context.reportly)}/actions`, _data);
            return res;
        }
        if (_context.project && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/actions`, _data);
            return res;
        }
        if (_context.product && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/actions`, _data);
            return res;
        }
        if (_context.monthly && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/monthlies/${encodeURIComponent(_context.monthly)}/actions`, _data);
            return res;
        }
        if (_context.doc && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/docs/${encodeURIComponent(_context.doc)}/actions`, _data);
            return res;
        }
        if (_context.doclib && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/doclibs/${encodeURIComponent(_context.doclib)}/actions`, _data);
            return res;
        }
        if (_context.daily && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/dailies/${encodeURIComponent(_context.daily)}/actions`, _data);
            return res;
        }
        if (_context.sysaccount && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/actions`, _data);
            return res;
        }
    this.log.warn([`[Action]>>>[Create函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.product && _context.project && _context.doclib && _context.doc && _context.action) {
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.project && _context.doclib && _context.doc && _context.action) {
            const res = await this.http.get(`/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.product && _context.project && _context.testtask && _context.action) {
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testtasks/${encodeURIComponent(_context.testtask)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.product && _context.project && _context.testreport && _context.action) {
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.product && _context.project && _context.story && _context.action) {
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/stories/${encodeURIComponent(_context.story)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.product && _context.project && _context.task && _context.action) {
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/tasks/${encodeURIComponent(_context.task)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.product && _context.project && _context.build && _context.action) {
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.product && _context.project && _context.bug && _context.action) {
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/bugs/${encodeURIComponent(_context.bug)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.product && _context.project && _context.productplan && _context.action) {
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/productplans/${encodeURIComponent(_context.productplan)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.product && _context.project && _context.doclib && _context.action) {
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.product && _context.doclib && _context.doc && _context.action) {
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.test && _context.testreport && _context.action) {
            const res = await this.http.get(`/tests/${encodeURIComponent(_context.test)}/testreports/${encodeURIComponent(_context.testreport)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.test && _context.testcase && _context.action) {
            const res = await this.http.get(`/tests/${encodeURIComponent(_context.test)}/testcases/${encodeURIComponent(_context.testcase)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.test && _context.bug && _context.action) {
            const res = await this.http.get(`/tests/${encodeURIComponent(_context.test)}/bugs/${encodeURIComponent(_context.bug)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.project && _context.testtask && _context.action) {
            const res = await this.http.get(`/projects/${encodeURIComponent(_context.project)}/testtasks/${encodeURIComponent(_context.testtask)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.project && _context.testreport && _context.action) {
            const res = await this.http.get(`/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.project && _context.story && _context.action) {
            const res = await this.http.get(`/projects/${encodeURIComponent(_context.project)}/stories/${encodeURIComponent(_context.story)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.project && _context.task && _context.action) {
            const res = await this.http.get(`/projects/${encodeURIComponent(_context.project)}/tasks/${encodeURIComponent(_context.task)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.project && _context.build && _context.action) {
            const res = await this.http.get(`/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.project && _context.bug && _context.action) {
            const res = await this.http.get(`/projects/${encodeURIComponent(_context.project)}/bugs/${encodeURIComponent(_context.bug)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.project && _context.productplan && _context.action) {
            const res = await this.http.get(`/projects/${encodeURIComponent(_context.project)}/productplans/${encodeURIComponent(_context.productplan)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.project && _context.doclib && _context.action) {
            const res = await this.http.get(`/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.product && _context.project && _context.action) {
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.product && _context.story && _context.action) {
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/stories/${encodeURIComponent(_context.story)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.product && _context.productrelease && _context.action) {
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/productreleases/${encodeURIComponent(_context.productrelease)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.product && _context.productplan && _context.action) {
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/productplans/${encodeURIComponent(_context.productplan)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.product && _context.build && _context.action) {
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/builds/${encodeURIComponent(_context.build)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.product && _context.doclib && _context.action) {
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/doclibs/${encodeURIComponent(_context.doclib)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.test && _context.testtask && _context.action) {
            const res = await this.http.get(`/tests/${encodeURIComponent(_context.test)}/testtasks/${encodeURIComponent(_context.testtask)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.doclib && _context.doc && _context.action) {
            const res = await this.http.get(`/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.sysaccount && _context.todo && _context.action) {
            const res = await this.http.get(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/todos/${encodeURIComponent(_context.todo)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.sysaccount && _context.doc && _context.action) {
            const res = await this.http.get(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/docs/${encodeURIComponent(_context.doc)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.weekly && _context.action) {
            const res = await this.http.get(`/weeklies/${encodeURIComponent(_context.weekly)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.todo && _context.action) {
            const res = await this.http.get(`/todos/${encodeURIComponent(_context.todo)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.testsuite && _context.action) {
            const res = await this.http.get(`/testsuites/${encodeURIComponent(_context.testsuite)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.reportly && _context.action) {
            const res = await this.http.get(`/reportlies/${encodeURIComponent(_context.reportly)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.project && _context.action) {
            const res = await this.http.get(`/projects/${encodeURIComponent(_context.project)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.product && _context.action) {
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.monthly && _context.action) {
            const res = await this.http.get(`/monthlies/${encodeURIComponent(_context.monthly)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.doc && _context.action) {
            const res = await this.http.get(`/docs/${encodeURIComponent(_context.doc)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.doclib && _context.action) {
            const res = await this.http.get(`/doclibs/${encodeURIComponent(_context.doclib)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.daily && _context.action) {
            const res = await this.http.get(`/dailies/${encodeURIComponent(_context.daily)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.sysaccount && _context.action) {
            const res = await this.http.get(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/actions/${encodeURIComponent(_context.action)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
    this.log.warn([`[Action]>>>[Get函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.product && _context.project && _context.doclib && _context.doc && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.project && _context.doclib && _context.doc && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.project && _context.testtask && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testtasks/${encodeURIComponent(_context.testtask)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.project && _context.testreport && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.project && _context.story && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/stories/${encodeURIComponent(_context.story)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.project && _context.task && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/tasks/${encodeURIComponent(_context.task)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.project && _context.build && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.project && _context.bug && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/bugs/${encodeURIComponent(_context.bug)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.project && _context.productplan && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/productplans/${encodeURIComponent(_context.productplan)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.project && _context.doclib && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.doclib && _context.doc && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.test && _context.testreport && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/tests/${encodeURIComponent(_context.test)}/testreports/${encodeURIComponent(_context.testreport)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.test && _context.testcase && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/tests/${encodeURIComponent(_context.test)}/testcases/${encodeURIComponent(_context.testcase)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.test && _context.bug && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/tests/${encodeURIComponent(_context.test)}/bugs/${encodeURIComponent(_context.bug)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.project && _context.testtask && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${encodeURIComponent(_context.project)}/testtasks/${encodeURIComponent(_context.testtask)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.project && _context.testreport && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.project && _context.story && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${encodeURIComponent(_context.project)}/stories/${encodeURIComponent(_context.story)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.project && _context.task && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${encodeURIComponent(_context.project)}/tasks/${encodeURIComponent(_context.task)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.project && _context.build && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.project && _context.bug && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${encodeURIComponent(_context.project)}/bugs/${encodeURIComponent(_context.bug)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.project && _context.productplan && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${encodeURIComponent(_context.project)}/productplans/${encodeURIComponent(_context.productplan)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.project && _context.doclib && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.project && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.story && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/stories/${encodeURIComponent(_context.story)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.productrelease && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/productreleases/${encodeURIComponent(_context.productrelease)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.productplan && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/productplans/${encodeURIComponent(_context.productplan)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.build && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/builds/${encodeURIComponent(_context.build)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.product && _context.doclib && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/doclibs/${encodeURIComponent(_context.doclib)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.test && _context.testtask && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/tests/${encodeURIComponent(_context.test)}/testtasks/${encodeURIComponent(_context.testtask)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.doclib && _context.doc && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.sysaccount && _context.todo && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/todos/${encodeURIComponent(_context.todo)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.sysaccount && _context.doc && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/docs/${encodeURIComponent(_context.doc)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.weekly && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/weeklies/${encodeURIComponent(_context.weekly)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.todo && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/todos/${encodeURIComponent(_context.todo)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.testsuite && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/testsuites/${encodeURIComponent(_context.testsuite)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.reportly && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/reportlies/${encodeURIComponent(_context.reportly)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.project && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${encodeURIComponent(_context.project)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.product && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.monthly && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/monthlies/${encodeURIComponent(_context.monthly)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.doc && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/docs/${encodeURIComponent(_context.doc)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.doclib && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/doclibs/${encodeURIComponent(_context.doclib)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.daily && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/dailies/${encodeURIComponent(_context.daily)}/actions/getdraft`, _data);
            return res;
        }
        if (_context.sysaccount && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/actions/getdraft`, _data);
            return res;
        }
    this.log.warn([`[Action]>>>[GetDraft函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.product && _context.project && _context.doclib && _context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.project && _context.doclib && _context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.product && _context.project && _context.testtask && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testtasks/${encodeURIComponent(_context.testtask)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.product && _context.project && _context.testreport && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.product && _context.project && _context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/stories/${encodeURIComponent(_context.story)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.product && _context.project && _context.task && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/tasks/${encodeURIComponent(_context.task)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.product && _context.project && _context.build && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.product && _context.project && _context.bug && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/bugs/${encodeURIComponent(_context.bug)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.product && _context.project && _context.productplan && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/productplans/${encodeURIComponent(_context.productplan)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.product && _context.project && _context.doclib && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.product && _context.doclib && _context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/products/${encodeURIComponent(_context.product)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.test && _context.testreport && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/tests/${encodeURIComponent(_context.test)}/testreports/${encodeURIComponent(_context.testreport)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.test && _context.testcase && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/tests/${encodeURIComponent(_context.test)}/testcases/${encodeURIComponent(_context.testcase)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.test && _context.bug && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/tests/${encodeURIComponent(_context.test)}/bugs/${encodeURIComponent(_context.bug)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.project && _context.testtask && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/projects/${encodeURIComponent(_context.project)}/testtasks/${encodeURIComponent(_context.testtask)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.project && _context.testreport && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.project && _context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/projects/${encodeURIComponent(_context.project)}/stories/${encodeURIComponent(_context.story)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.project && _context.task && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/projects/${encodeURIComponent(_context.project)}/tasks/${encodeURIComponent(_context.task)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.project && _context.build && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.project && _context.bug && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/projects/${encodeURIComponent(_context.project)}/bugs/${encodeURIComponent(_context.bug)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.project && _context.productplan && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/projects/${encodeURIComponent(_context.project)}/productplans/${encodeURIComponent(_context.productplan)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.project && _context.doclib && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.product && _context.project && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.product && _context.story && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/products/${encodeURIComponent(_context.product)}/stories/${encodeURIComponent(_context.story)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.product && _context.productrelease && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/products/${encodeURIComponent(_context.product)}/productreleases/${encodeURIComponent(_context.productrelease)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.product && _context.productplan && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/products/${encodeURIComponent(_context.product)}/productplans/${encodeURIComponent(_context.productplan)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.product && _context.build && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/products/${encodeURIComponent(_context.product)}/builds/${encodeURIComponent(_context.build)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.product && _context.doclib && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/products/${encodeURIComponent(_context.product)}/doclibs/${encodeURIComponent(_context.doclib)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.test && _context.testtask && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/tests/${encodeURIComponent(_context.test)}/testtasks/${encodeURIComponent(_context.testtask)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.doclib && _context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.sysaccount && _context.todo && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/todos/${encodeURIComponent(_context.todo)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.sysaccount && _context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/docs/${encodeURIComponent(_context.doc)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.weekly && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/weeklies/${encodeURIComponent(_context.weekly)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.todo && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/todos/${encodeURIComponent(_context.todo)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.testsuite && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/testsuites/${encodeURIComponent(_context.testsuite)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.reportly && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/reportlies/${encodeURIComponent(_context.reportly)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.project && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/projects/${encodeURIComponent(_context.project)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.product && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/products/${encodeURIComponent(_context.product)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.monthly && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/monthlies/${encodeURIComponent(_context.monthly)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.doc && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/docs/${encodeURIComponent(_context.doc)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.doclib && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/doclibs/${encodeURIComponent(_context.doclib)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.daily && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/dailies/${encodeURIComponent(_context.daily)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
        if (_context.sysaccount && _context.action) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/actions/${encodeURIComponent(_context.action)}`, _data);
            return res;
        }
    this.log.warn([`[Action]>>>[Update函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.product && _context.project && _context.doclib && _context.doc && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.project && _context.doclib && _context.doc && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.product && _context.project && _context.testtask && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testtasks/${encodeURIComponent(_context.testtask)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.product && _context.project && _context.testreport && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.product && _context.project && _context.story && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/stories/${encodeURIComponent(_context.story)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.product && _context.project && _context.task && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/tasks/${encodeURIComponent(_context.task)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.product && _context.project && _context.build && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.product && _context.project && _context.bug && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/bugs/${encodeURIComponent(_context.bug)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.product && _context.project && _context.productplan && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/productplans/${encodeURIComponent(_context.productplan)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.product && _context.project && _context.doclib && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.product && _context.doclib && _context.doc && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.test && _context.testreport && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testreports/${encodeURIComponent(_context.testreport)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.test && _context.testcase && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testcases/${encodeURIComponent(_context.testcase)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.test && _context.bug && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/bugs/${encodeURIComponent(_context.bug)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.project && _context.testtask && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/testtasks/${encodeURIComponent(_context.testtask)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.project && _context.testreport && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.project && _context.story && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/stories/${encodeURIComponent(_context.story)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.project && _context.task && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/tasks/${encodeURIComponent(_context.task)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.project && _context.build && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.project && _context.bug && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/bugs/${encodeURIComponent(_context.bug)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.project && _context.productplan && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/productplans/${encodeURIComponent(_context.productplan)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.project && _context.doclib && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.product && _context.project && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.product && _context.story && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/stories/${encodeURIComponent(_context.story)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.product && _context.productrelease && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/productreleases/${encodeURIComponent(_context.productrelease)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.product && _context.productplan && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/productplans/${encodeURIComponent(_context.productplan)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.product && _context.build && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/builds/${encodeURIComponent(_context.build)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.product && _context.doclib && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/doclibs/${encodeURIComponent(_context.doclib)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.test && _context.testtask && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testtasks/${encodeURIComponent(_context.testtask)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.doclib && _context.doc && true) {
            const res = await this.http.post(`/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.sysaccount && _context.todo && true) {
            const res = await this.http.post(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/todos/${encodeURIComponent(_context.todo)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.sysaccount && _context.doc && true) {
            const res = await this.http.post(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/docs/${encodeURIComponent(_context.doc)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.weekly && true) {
            const res = await this.http.post(`/weeklies/${encodeURIComponent(_context.weekly)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.todo && true) {
            const res = await this.http.post(`/todos/${encodeURIComponent(_context.todo)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.testsuite && true) {
            const res = await this.http.post(`/testsuites/${encodeURIComponent(_context.testsuite)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.reportly && true) {
            const res = await this.http.post(`/reportlies/${encodeURIComponent(_context.reportly)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.project && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.product && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.monthly && true) {
            const res = await this.http.post(`/monthlies/${encodeURIComponent(_context.monthly)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.doc && true) {
            const res = await this.http.post(`/docs/${encodeURIComponent(_context.doc)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.doclib && true) {
            const res = await this.http.post(`/doclibs/${encodeURIComponent(_context.doclib)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.daily && true) {
            const res = await this.http.post(`/dailies/${encodeURIComponent(_context.daily)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
        if (_context.sysaccount && true) {
            const res = await this.http.post(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/actions/fetchaccount`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchAccount');
            return res;
        }
    this.log.warn([`[Action]>>>[FetchAccount函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.product && _context.project && _context.doclib && _context.doc && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.project && _context.doclib && _context.doc && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.product && _context.project && _context.testtask && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testtasks/${encodeURIComponent(_context.testtask)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.product && _context.project && _context.testreport && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.product && _context.project && _context.story && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/stories/${encodeURIComponent(_context.story)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.product && _context.project && _context.task && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/tasks/${encodeURIComponent(_context.task)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.product && _context.project && _context.build && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.product && _context.project && _context.bug && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/bugs/${encodeURIComponent(_context.bug)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.product && _context.project && _context.productplan && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/productplans/${encodeURIComponent(_context.productplan)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.product && _context.project && _context.doclib && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.product && _context.doclib && _context.doc && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.test && _context.testreport && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testreports/${encodeURIComponent(_context.testreport)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.test && _context.testcase && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testcases/${encodeURIComponent(_context.testcase)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.test && _context.bug && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/bugs/${encodeURIComponent(_context.bug)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.project && _context.testtask && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/testtasks/${encodeURIComponent(_context.testtask)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.project && _context.testreport && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.project && _context.story && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/stories/${encodeURIComponent(_context.story)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.project && _context.task && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/tasks/${encodeURIComponent(_context.task)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.project && _context.build && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.project && _context.bug && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/bugs/${encodeURIComponent(_context.bug)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.project && _context.productplan && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/productplans/${encodeURIComponent(_context.productplan)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.project && _context.doclib && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.product && _context.project && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.product && _context.story && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/stories/${encodeURIComponent(_context.story)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.product && _context.productrelease && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/productreleases/${encodeURIComponent(_context.productrelease)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.product && _context.productplan && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/productplans/${encodeURIComponent(_context.productplan)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.product && _context.build && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/builds/${encodeURIComponent(_context.build)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.product && _context.doclib && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/doclibs/${encodeURIComponent(_context.doclib)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.test && _context.testtask && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testtasks/${encodeURIComponent(_context.testtask)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.doclib && _context.doc && true) {
            const res = await this.http.post(`/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.sysaccount && _context.todo && true) {
            const res = await this.http.post(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/todos/${encodeURIComponent(_context.todo)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.sysaccount && _context.doc && true) {
            const res = await this.http.post(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/docs/${encodeURIComponent(_context.doc)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.weekly && true) {
            const res = await this.http.post(`/weeklies/${encodeURIComponent(_context.weekly)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.todo && true) {
            const res = await this.http.post(`/todos/${encodeURIComponent(_context.todo)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.testsuite && true) {
            const res = await this.http.post(`/testsuites/${encodeURIComponent(_context.testsuite)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.reportly && true) {
            const res = await this.http.post(`/reportlies/${encodeURIComponent(_context.reportly)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.project && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.product && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.monthly && true) {
            const res = await this.http.post(`/monthlies/${encodeURIComponent(_context.monthly)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.doc && true) {
            const res = await this.http.post(`/docs/${encodeURIComponent(_context.doc)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.doclib && true) {
            const res = await this.http.post(`/doclibs/${encodeURIComponent(_context.doclib)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.daily && true) {
            const res = await this.http.post(`/dailies/${encodeURIComponent(_context.daily)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
        if (_context.sysaccount && true) {
            const res = await this.http.post(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/actions/fetchmain`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMain');
            return res;
        }
    this.log.warn([`[Action]>>>[FetchMain函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.product && _context.project && _context.doclib && _context.doc && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.project && _context.doclib && _context.doc && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.product && _context.project && _context.testtask && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testtasks/${encodeURIComponent(_context.testtask)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.product && _context.project && _context.testreport && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.product && _context.project && _context.story && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/stories/${encodeURIComponent(_context.story)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.product && _context.project && _context.task && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/tasks/${encodeURIComponent(_context.task)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.product && _context.project && _context.build && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.product && _context.project && _context.bug && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/bugs/${encodeURIComponent(_context.bug)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.product && _context.project && _context.productplan && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/productplans/${encodeURIComponent(_context.productplan)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.product && _context.project && _context.doclib && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.product && _context.doclib && _context.doc && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.test && _context.testreport && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testreports/${encodeURIComponent(_context.testreport)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.test && _context.testcase && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testcases/${encodeURIComponent(_context.testcase)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.test && _context.bug && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/bugs/${encodeURIComponent(_context.bug)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.project && _context.testtask && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/testtasks/${encodeURIComponent(_context.testtask)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.project && _context.testreport && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.project && _context.story && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/stories/${encodeURIComponent(_context.story)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.project && _context.task && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/tasks/${encodeURIComponent(_context.task)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.project && _context.build && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.project && _context.bug && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/bugs/${encodeURIComponent(_context.bug)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.project && _context.productplan && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/productplans/${encodeURIComponent(_context.productplan)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.project && _context.doclib && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.product && _context.project && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.product && _context.story && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/stories/${encodeURIComponent(_context.story)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.product && _context.productrelease && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/productreleases/${encodeURIComponent(_context.productrelease)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.product && _context.productplan && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/productplans/${encodeURIComponent(_context.productplan)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.product && _context.build && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/builds/${encodeURIComponent(_context.build)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.product && _context.doclib && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/doclibs/${encodeURIComponent(_context.doclib)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.test && _context.testtask && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testtasks/${encodeURIComponent(_context.testtask)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.doclib && _context.doc && true) {
            const res = await this.http.post(`/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.sysaccount && _context.todo && true) {
            const res = await this.http.post(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/todos/${encodeURIComponent(_context.todo)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.sysaccount && _context.doc && true) {
            const res = await this.http.post(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/docs/${encodeURIComponent(_context.doc)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.weekly && true) {
            const res = await this.http.post(`/weeklies/${encodeURIComponent(_context.weekly)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.todo && true) {
            const res = await this.http.post(`/todos/${encodeURIComponent(_context.todo)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.testsuite && true) {
            const res = await this.http.post(`/testsuites/${encodeURIComponent(_context.testsuite)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.reportly && true) {
            const res = await this.http.post(`/reportlies/${encodeURIComponent(_context.reportly)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.project && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.product && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.monthly && true) {
            const res = await this.http.post(`/monthlies/${encodeURIComponent(_context.monthly)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.doc && true) {
            const res = await this.http.post(`/docs/${encodeURIComponent(_context.doc)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.doclib && true) {
            const res = await this.http.post(`/doclibs/${encodeURIComponent(_context.doclib)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.daily && true) {
            const res = await this.http.post(`/dailies/${encodeURIComponent(_context.daily)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
        if (_context.sysaccount && true) {
            const res = await this.http.post(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/actions/fetchmy`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMy');
            return res;
        }
    this.log.warn([`[Action]>>>[FetchMy函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.product && _context.project && _context.doclib && _context.doc && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.project && _context.doclib && _context.doc && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.product && _context.project && _context.testtask && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testtasks/${encodeURIComponent(_context.testtask)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.product && _context.project && _context.testreport && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.product && _context.project && _context.story && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/stories/${encodeURIComponent(_context.story)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.product && _context.project && _context.task && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/tasks/${encodeURIComponent(_context.task)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.product && _context.project && _context.build && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.product && _context.project && _context.bug && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/bugs/${encodeURIComponent(_context.bug)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.product && _context.project && _context.productplan && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/productplans/${encodeURIComponent(_context.productplan)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.product && _context.project && _context.doclib && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.product && _context.doclib && _context.doc && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.test && _context.testreport && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testreports/${encodeURIComponent(_context.testreport)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.test && _context.testcase && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testcases/${encodeURIComponent(_context.testcase)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.test && _context.bug && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/bugs/${encodeURIComponent(_context.bug)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.project && _context.testtask && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/testtasks/${encodeURIComponent(_context.testtask)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.project && _context.testreport && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.project && _context.story && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/stories/${encodeURIComponent(_context.story)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.project && _context.task && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/tasks/${encodeURIComponent(_context.task)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.project && _context.build && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.project && _context.bug && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/bugs/${encodeURIComponent(_context.bug)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.project && _context.productplan && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/productplans/${encodeURIComponent(_context.productplan)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.project && _context.doclib && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.product && _context.project && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.product && _context.story && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/stories/${encodeURIComponent(_context.story)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.product && _context.productrelease && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/productreleases/${encodeURIComponent(_context.productrelease)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.product && _context.productplan && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/productplans/${encodeURIComponent(_context.productplan)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.product && _context.build && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/builds/${encodeURIComponent(_context.build)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.product && _context.doclib && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/doclibs/${encodeURIComponent(_context.doclib)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.test && _context.testtask && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testtasks/${encodeURIComponent(_context.testtask)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.doclib && _context.doc && true) {
            const res = await this.http.post(`/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.sysaccount && _context.todo && true) {
            const res = await this.http.post(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/todos/${encodeURIComponent(_context.todo)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.sysaccount && _context.doc && true) {
            const res = await this.http.post(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/docs/${encodeURIComponent(_context.doc)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.weekly && true) {
            const res = await this.http.post(`/weeklies/${encodeURIComponent(_context.weekly)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.todo && true) {
            const res = await this.http.post(`/todos/${encodeURIComponent(_context.todo)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.testsuite && true) {
            const res = await this.http.post(`/testsuites/${encodeURIComponent(_context.testsuite)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.reportly && true) {
            const res = await this.http.post(`/reportlies/${encodeURIComponent(_context.reportly)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.project && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.product && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.monthly && true) {
            const res = await this.http.post(`/monthlies/${encodeURIComponent(_context.monthly)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.doc && true) {
            const res = await this.http.post(`/docs/${encodeURIComponent(_context.doc)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.doclib && true) {
            const res = await this.http.post(`/doclibs/${encodeURIComponent(_context.doclib)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.daily && true) {
            const res = await this.http.post(`/dailies/${encodeURIComponent(_context.daily)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
        if (_context.sysaccount && true) {
            const res = await this.http.post(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/actions/fetchmytrends`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchMyTrends');
            return res;
        }
    this.log.warn([`[Action]>>>[FetchMyTrends函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.product && _context.project && _context.doclib && _context.doc && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.project && _context.doclib && _context.doc && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.product && _context.project && _context.testtask && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testtasks/${encodeURIComponent(_context.testtask)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.product && _context.project && _context.testreport && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.product && _context.project && _context.story && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/stories/${encodeURIComponent(_context.story)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.product && _context.project && _context.task && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/tasks/${encodeURIComponent(_context.task)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.product && _context.project && _context.build && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.product && _context.project && _context.bug && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/bugs/${encodeURIComponent(_context.bug)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.product && _context.project && _context.productplan && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/productplans/${encodeURIComponent(_context.productplan)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.product && _context.project && _context.doclib && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.product && _context.doclib && _context.doc && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.test && _context.testreport && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testreports/${encodeURIComponent(_context.testreport)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.test && _context.testcase && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testcases/${encodeURIComponent(_context.testcase)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.test && _context.bug && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/bugs/${encodeURIComponent(_context.bug)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.project && _context.testtask && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/testtasks/${encodeURIComponent(_context.testtask)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.project && _context.testreport && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.project && _context.story && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/stories/${encodeURIComponent(_context.story)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.project && _context.task && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/tasks/${encodeURIComponent(_context.task)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.project && _context.build && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.project && _context.bug && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/bugs/${encodeURIComponent(_context.bug)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.project && _context.productplan && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/productplans/${encodeURIComponent(_context.productplan)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.project && _context.doclib && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.product && _context.project && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.product && _context.story && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/stories/${encodeURIComponent(_context.story)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.product && _context.productrelease && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/productreleases/${encodeURIComponent(_context.productrelease)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.product && _context.productplan && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/productplans/${encodeURIComponent(_context.productplan)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.product && _context.build && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/builds/${encodeURIComponent(_context.build)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.product && _context.doclib && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/doclibs/${encodeURIComponent(_context.doclib)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.test && _context.testtask && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testtasks/${encodeURIComponent(_context.testtask)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.doclib && _context.doc && true) {
            const res = await this.http.post(`/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.sysaccount && _context.todo && true) {
            const res = await this.http.post(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/todos/${encodeURIComponent(_context.todo)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.sysaccount && _context.doc && true) {
            const res = await this.http.post(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/docs/${encodeURIComponent(_context.doc)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.weekly && true) {
            const res = await this.http.post(`/weeklies/${encodeURIComponent(_context.weekly)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.todo && true) {
            const res = await this.http.post(`/todos/${encodeURIComponent(_context.todo)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.testsuite && true) {
            const res = await this.http.post(`/testsuites/${encodeURIComponent(_context.testsuite)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.reportly && true) {
            const res = await this.http.post(`/reportlies/${encodeURIComponent(_context.reportly)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.project && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.product && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.monthly && true) {
            const res = await this.http.post(`/monthlies/${encodeURIComponent(_context.monthly)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.doc && true) {
            const res = await this.http.post(`/docs/${encodeURIComponent(_context.doc)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.doclib && true) {
            const res = await this.http.post(`/doclibs/${encodeURIComponent(_context.doclib)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.daily && true) {
            const res = await this.http.post(`/dailies/${encodeURIComponent(_context.daily)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.sysaccount && true) {
            const res = await this.http.post(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/actions/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
    this.log.warn([`[Action]>>>[FetchProduct函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
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
        try {
        if (_context.product && _context.project && _context.doclib && _context.doc && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.project && _context.doclib && _context.doc && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.product && _context.project && _context.testtask && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testtasks/${encodeURIComponent(_context.testtask)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.product && _context.project && _context.testreport && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.product && _context.project && _context.story && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/stories/${encodeURIComponent(_context.story)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.product && _context.project && _context.task && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/tasks/${encodeURIComponent(_context.task)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.product && _context.project && _context.build && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.product && _context.project && _context.bug && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/bugs/${encodeURIComponent(_context.bug)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.product && _context.project && _context.productplan && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/productplans/${encodeURIComponent(_context.productplan)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.product && _context.project && _context.doclib && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.product && _context.doclib && _context.doc && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.test && _context.testreport && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testreports/${encodeURIComponent(_context.testreport)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.test && _context.testcase && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testcases/${encodeURIComponent(_context.testcase)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.test && _context.bug && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/bugs/${encodeURIComponent(_context.bug)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.project && _context.testtask && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/testtasks/${encodeURIComponent(_context.testtask)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.project && _context.testreport && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.project && _context.story && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/stories/${encodeURIComponent(_context.story)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.project && _context.task && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/tasks/${encodeURIComponent(_context.task)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.project && _context.build && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.project && _context.bug && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/bugs/${encodeURIComponent(_context.bug)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.project && _context.productplan && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/productplans/${encodeURIComponent(_context.productplan)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.project && _context.doclib && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.product && _context.project && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.product && _context.story && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/stories/${encodeURIComponent(_context.story)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.product && _context.productrelease && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/productreleases/${encodeURIComponent(_context.productrelease)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.product && _context.productplan && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/productplans/${encodeURIComponent(_context.productplan)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.product && _context.build && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/builds/${encodeURIComponent(_context.build)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.product && _context.doclib && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/doclibs/${encodeURIComponent(_context.doclib)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.test && _context.testtask && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testtasks/${encodeURIComponent(_context.testtask)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.doclib && _context.doc && true) {
            const res = await this.http.post(`/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.sysaccount && _context.todo && true) {
            const res = await this.http.post(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/todos/${encodeURIComponent(_context.todo)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.sysaccount && _context.doc && true) {
            const res = await this.http.post(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/docs/${encodeURIComponent(_context.doc)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.weekly && true) {
            const res = await this.http.post(`/weeklies/${encodeURIComponent(_context.weekly)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.todo && true) {
            const res = await this.http.post(`/todos/${encodeURIComponent(_context.todo)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.testsuite && true) {
            const res = await this.http.post(`/testsuites/${encodeURIComponent(_context.testsuite)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.reportly && true) {
            const res = await this.http.post(`/reportlies/${encodeURIComponent(_context.reportly)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.project && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.product && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.monthly && true) {
            const res = await this.http.post(`/monthlies/${encodeURIComponent(_context.monthly)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.doc && true) {
            const res = await this.http.post(`/docs/${encodeURIComponent(_context.doc)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.doclib && true) {
            const res = await this.http.post(`/doclibs/${encodeURIComponent(_context.doclib)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.daily && true) {
            const res = await this.http.post(`/dailies/${encodeURIComponent(_context.daily)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.sysaccount && true) {
            const res = await this.http.post(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/actions/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
    this.log.warn([`[Action]>>>[FetchProject函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
}
