import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IHistory, History } from '../../entities';
import keys from '../../entities/history/history-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 操作历史服务对象基类
 *
 * @export
 * @class HistoryBaseService
 * @extends {EntityBaseService}
 */
export class HistoryBaseService extends EntityBaseService<IHistory> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'History';
    protected APPDENAMEPLURAL = 'Histories';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/History.json';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'diff';
    protected quickSearchFields = ['diff',];
    protected selectContextParam = {
        action: 'action',
    };

    constructor(opts?: any) {
        super(opts, 'History');
    }

    newEntity(data: IHistory): History {
        return new History(data);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IHistory> {
        const entity = await super.getLocal(context, srfKey);
        if (entity && entity.action && entity.action !== '') {
            const s = await ___ibz___.gs.getActionService();
            const data = await s.getLocal2(context, entity.action);
            if (data) {
                entity.action = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IHistory): Promise<IHistory> {
        return super.updateLocal(context, new History(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IHistory = {}): Promise<IHistory> {
        if (_context.action && _context.action !== '') {
            const s = await ___ibz___.gs.getActionService();
            const data = await s.getLocal2(_context, _context.action);
            if (data) {
                entity.action = data.id;
            }
        }
        return new History(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof HistoryService
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
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('default', cond);
            }
        }
        return this.condCache.get('default');
    }

    protected getViewCond() {
        return this.condCache.get('view');
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof HistoryService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.doclib && _context.doc && _context.action && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.project && _context.doclib && _context.doc && _context.action && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.product && _context.project && _context.testtask && _context.action && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testtasks/${encodeURIComponent(_context.testtask)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.product && _context.project && _context.testreport && _context.action && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.product && _context.project && _context.story && _context.action && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/stories/${encodeURIComponent(_context.story)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.product && _context.project && _context.task && _context.action && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/tasks/${encodeURIComponent(_context.task)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.product && _context.project && _context.build && _context.action && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.product && _context.project && _context.bug && _context.action && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/bugs/${encodeURIComponent(_context.bug)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.product && _context.project && _context.productplan && _context.action && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/productplans/${encodeURIComponent(_context.productplan)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.product && _context.project && _context.doclib && _context.action && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.product && _context.doclib && _context.doc && _context.action && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.test && _context.testreport && _context.action && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testreports/${encodeURIComponent(_context.testreport)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.test && _context.testcase && _context.action && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testcases/${encodeURIComponent(_context.testcase)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.test && _context.bug && _context.action && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/bugs/${encodeURIComponent(_context.bug)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.project && _context.testtask && _context.action && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/testtasks/${encodeURIComponent(_context.testtask)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.project && _context.testreport && _context.action && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.project && _context.story && _context.action && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/stories/${encodeURIComponent(_context.story)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.project && _context.task && _context.action && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/tasks/${encodeURIComponent(_context.task)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.project && _context.build && _context.action && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.project && _context.bug && _context.action && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/bugs/${encodeURIComponent(_context.bug)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.project && _context.productplan && _context.action && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/productplans/${encodeURIComponent(_context.productplan)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.project && _context.doclib && _context.action && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.product && _context.project && _context.action && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.product && _context.story && _context.action && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/stories/${encodeURIComponent(_context.story)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.product && _context.productrelease && _context.action && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/productreleases/${encodeURIComponent(_context.productrelease)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.product && _context.productplan && _context.action && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/productplans/${encodeURIComponent(_context.productplan)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.product && _context.build && _context.action && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/builds/${encodeURIComponent(_context.build)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.product && _context.doclib && _context.action && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/doclibs/${encodeURIComponent(_context.doclib)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.test && _context.testtask && _context.action && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testtasks/${encodeURIComponent(_context.testtask)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.doclib && _context.doc && _context.action && true) {
            const res = await this.http.post(`/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.sysaccount && _context.todo && _context.action && true) {
            const res = await this.http.post(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/todos/${encodeURIComponent(_context.todo)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.sysaccount && _context.doc && _context.action && true) {
            const res = await this.http.post(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/docs/${encodeURIComponent(_context.doc)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.weekly && _context.action && true) {
            const res = await this.http.post(`/weeklies/${encodeURIComponent(_context.weekly)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.todo && _context.action && true) {
            const res = await this.http.post(`/todos/${encodeURIComponent(_context.todo)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.testsuite && _context.action && true) {
            const res = await this.http.post(`/testsuites/${encodeURIComponent(_context.testsuite)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.reportly && _context.action && true) {
            const res = await this.http.post(`/reportlies/${encodeURIComponent(_context.reportly)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.project && _context.action && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.product && _context.action && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.monthly && _context.action && true) {
            const res = await this.http.post(`/monthlies/${encodeURIComponent(_context.monthly)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.doc && _context.action && true) {
            const res = await this.http.post(`/docs/${encodeURIComponent(_context.doc)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.doclib && _context.action && true) {
            const res = await this.http.post(`/doclibs/${encodeURIComponent(_context.doclib)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.daily && _context.action && true) {
            const res = await this.http.post(`/dailies/${encodeURIComponent(_context.daily)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.sysaccount && _context.action && true) {
            const res = await this.http.post(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/actions/${encodeURIComponent(_context.action)}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
    this.log.warn([`[History]>>>[FetchDefault函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
}
