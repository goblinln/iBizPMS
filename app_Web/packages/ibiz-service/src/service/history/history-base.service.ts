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
        if (_context.project && _context.doclib && _context.doc && _context.action && true) {
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.product && _context.doclib && _context.doc && _context.action && true) {
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.project && _context.build && _context.action && true) {
            return this.http.post(`/projects/${_context.project}/builds/${_context.build}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.project && _context.testtask && _context.action && true) {
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.project && _context.testreport && _context.action && true) {
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.project && _context.task && _context.action && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.project && _context.doclib && _context.action && true) {
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.doclib && _context.doc && _context.action && true) {
            return this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.product && _context.story && _context.action && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.product && _context.productplan && _context.action && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.product && _context.case && _context.action && true) {
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.product && _context.build && _context.action && true) {
            return this.http.post(`/products/${_context.product}/builds/${_context.build}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.product && _context.testreport && _context.action && true) {
            return this.http.post(`/products/${_context.product}/testreports/${_context.testreport}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.product && _context.testtask && _context.action && true) {
            return this.http.post(`/products/${_context.product}/testtasks/${_context.testtask}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.product && _context.testsuite && _context.action && true) {
            return this.http.post(`/products/${_context.product}/testsuites/${_context.testsuite}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.product && _context.release && _context.action && true) {
            return this.http.post(`/products/${_context.product}/releases/${_context.release}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.product && _context.bug && _context.action && true) {
            return this.http.post(`/products/${_context.product}/bugs/${_context.bug}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.product && _context.doclib && _context.action && true) {
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.project && _context.action && true) {
            return this.http.post(`/projects/${_context.project}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.story && _context.action && true) {
            return this.http.post(`/stories/${_context.story}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.ibzlib && _context.action && true) {
            return this.http.post(`/ibzlibs/${_context.ibzlib}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.build && _context.action && true) {
            return this.http.post(`/builds/${_context.build}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.case && _context.action && true) {
            return this.http.post(`/cases/${_context.case}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.testsuite && _context.action && true) {
            return this.http.post(`/testsuites/${_context.testsuite}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.testtask && _context.action && true) {
            return this.http.post(`/testtasks/${_context.testtask}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.ibzreportly && _context.action && true) {
            return this.http.post(`/ibzreportlies/${_context.ibzreportly}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.ibzmonthly && _context.action && true) {
            return this.http.post(`/ibzmonthlies/${_context.ibzmonthly}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.ibzdaily && _context.action && true) {
            return this.http.post(`/ibzdailies/${_context.ibzdaily}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.doc && _context.action && true) {
            return this.http.post(`/docs/${_context.doc}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.doclib && _context.action && true) {
            return this.http.post(`/doclibs/${_context.doclib}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.testreport && _context.action && true) {
            return this.http.post(`/testreports/${_context.testreport}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.ibzweekly && _context.action && true) {
            return this.http.post(`/ibzweeklies/${_context.ibzweekly}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.release && _context.action && true) {
            return this.http.post(`/releases/${_context.release}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.task && _context.action && true) {
            return this.http.post(`/tasks/${_context.task}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.todo && _context.action && true) {
            return this.http.post(`/todos/${_context.todo}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.productplan && _context.action && true) {
            return this.http.post(`/productplans/${_context.productplan}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.product && _context.action && true) {
            return this.http.post(`/products/${_context.product}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.bug && _context.action && true) {
            return this.http.post(`/bugs/${_context.bug}/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        if (_context.action && true) {
            return this.http.post(`/actions/${_context.action}/histories/fetchdefault`, _data);
        }
        return this.http.post(`/histories/fetchdefault`, _data);
    }
}
