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

    newEntity(data: IHistory): History {
        return new History(data);
    }

    async addLocal(context: IContext, entity: IHistory): Promise<IHistory | null> {
        return this.cache.add(context, new History(entity) as any);
    }

    async createLocal(context: IContext, entity: IHistory): Promise<IHistory | null> {
        return super.createLocal(context, new History(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IHistory> {
        const entity = this.cache.get(context, srfKey);
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
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.project && _context.doclib && _context.doc && _context.action && true) {
            const res = await this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.product && _context.project && _context.testtask && _context.action && true) {
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/testtasks/${_context.testtask}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.product && _context.project && _context.testreport && _context.action && true) {
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/testreports/${_context.testreport}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.product && _context.project && _context.story && _context.action && true) {
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/stories/${_context.story}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.product && _context.project && _context.task && _context.action && true) {
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/tasks/${_context.task}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.product && _context.project && _context.build && _context.action && true) {
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/builds/${_context.build}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.product && _context.project && _context.bug && _context.action && true) {
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/bugs/${_context.bug}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.product && _context.project && _context.productplan && _context.action && true) {
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/productplans/${_context.productplan}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.product && _context.project && _context.doclib && _context.action && true) {
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/doclibs/${_context.doclib}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.product && _context.doclib && _context.doc && _context.action && true) {
            const res = await this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.test && _context.testreport && _context.action && true) {
            const res = await this.http.post(`/tests/${_context.test}/testreports/${_context.testreport}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.test && _context.testcase && _context.action && true) {
            const res = await this.http.post(`/tests/${_context.test}/testcases/${_context.testcase}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.test && _context.bug && _context.action && true) {
            const res = await this.http.post(`/tests/${_context.test}/bugs/${_context.bug}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.project && _context.testtask && _context.action && true) {
            const res = await this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.project && _context.testreport && _context.action && true) {
            const res = await this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.project && _context.story && _context.action && true) {
            const res = await this.http.post(`/projects/${_context.project}/stories/${_context.story}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.project && _context.task && _context.action && true) {
            const res = await this.http.post(`/projects/${_context.project}/tasks/${_context.task}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.project && _context.build && _context.action && true) {
            const res = await this.http.post(`/projects/${_context.project}/builds/${_context.build}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.project && _context.bug && _context.action && true) {
            const res = await this.http.post(`/projects/${_context.project}/bugs/${_context.bug}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.project && _context.productplan && _context.action && true) {
            const res = await this.http.post(`/projects/${_context.project}/productplans/${_context.productplan}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.project && _context.doclib && _context.action && true) {
            const res = await this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.product && _context.project && _context.action && true) {
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.product && _context.story && _context.action && true) {
            const res = await this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.product && _context.productrelease && _context.action && true) {
            const res = await this.http.post(`/products/${_context.product}/productreleases/${_context.productrelease}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.product && _context.productplan && _context.action && true) {
            const res = await this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.product && _context.build && _context.action && true) {
            const res = await this.http.post(`/products/${_context.product}/builds/${_context.build}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.product && _context.doclib && _context.action && true) {
            const res = await this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.test && _context.testtask && _context.action && true) {
            const res = await this.http.post(`/tests/${_context.test}/testtasks/${_context.testtask}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.doclib && _context.doc && _context.action && true) {
            const res = await this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.sysaccount && _context.todo && _context.action && true) {
            const res = await this.http.post(`/sysaccounts/${_context.sysaccount}/todos/${_context.todo}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.sysaccount && _context.doc && _context.action && true) {
            const res = await this.http.post(`/sysaccounts/${_context.sysaccount}/docs/${_context.doc}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.weekly && _context.action && true) {
            const res = await this.http.post(`/weeklies/${_context.weekly}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.todo && _context.action && true) {
            const res = await this.http.post(`/todos/${_context.todo}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.testsuite && _context.action && true) {
            const res = await this.http.post(`/testsuites/${_context.testsuite}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.reportly && _context.action && true) {
            const res = await this.http.post(`/reportlies/${_context.reportly}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.project && _context.action && true) {
            const res = await this.http.post(`/projects/${_context.project}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.product && _context.action && true) {
            const res = await this.http.post(`/products/${_context.product}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.monthly && _context.action && true) {
            const res = await this.http.post(`/monthlies/${_context.monthly}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.doc && _context.action && true) {
            const res = await this.http.post(`/docs/${_context.doc}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.doclib && _context.action && true) {
            const res = await this.http.post(`/doclibs/${_context.doclib}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.daily && _context.action && true) {
            const res = await this.http.post(`/dailies/${_context.daily}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
        if (_context.sysaccount && _context.action && true) {
            const res = await this.http.post(`/sysaccounts/${_context.sysaccount}/actions/${_context.action}/histories/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data);
            return res;
        }
    this.log.warn([`[History]>>>[FetchDefault函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                console.warn(error);
                return new HttpResponse({message:error.message}, {
                    ok: false,
                    status: 500,
                });
            }
    }
}
