import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IFile, File } from '../../entities';
import keys from '../../entities/file/file-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 附件服务对象基类
 *
 * @export
 * @class FileBaseService
 * @extends {EntityBaseService}
 */
export class FileBaseService extends EntityBaseService<IFile> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'File';
    protected APPDENAMEPLURAL = 'Files';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/File.json';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'title';
    protected quickSearchFields = ['title',];
    protected selectContextParam = {
    };

    constructor(opts?: any) {
        super(opts, 'File');
    }

    newEntity(data: IFile): File {
        return new File(data);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IFile> {
        const entity = await super.getLocal(context, srfKey);
        return entity!;
    }

    async updateLocal(context: IContext, entity: IFile): Promise<IFile> {
        return super.updateLocal(context, new File(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IFile = {}): Promise<IFile> {
        return new File(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof FileService
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
        return this.condCache.get('default');
    }

    protected getDocLibFileCond() {
        return this.condCache.get('docLibFile');
    }

    protected getMySubmitFileCond() {
        if (!this.condCache.has('mySubmitFile')) {
            const strCond: any[] = ['AND', ['EQ', 'ADDEDBY',{ type: 'SESSIONCONTEXT', value: 'srfloginname'}]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('mySubmitFile', cond);
            }
        }
        return this.condCache.get('mySubmitFile');
    }

    protected getProductDocLibFileCond() {
        return this.condCache.get('productDocLibFile');
    }

    protected getTypeCond() {
        if (!this.condCache.has('type')) {
            const strCond: any[] = ['AND', ['EQ', 'OBJECTID',{ type: 'DATACONTEXT', value: 'srfparentkey'}], ['EQ', 'OBJECTTYPE',{ type: 'DATACONTEXT', value: 'objecttype'}], ['NOTEQ', 'EXTRA','editor']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('type', cond);
            }
        }
        return this.condCache.get('type');
    }

    protected getTypeNotBySrfparentkeyCond() {
        return this.condCache.get('typeNotBySrfparentkey');
    }

    protected getViewCond() {
        return this.condCache.get('view');
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof FileService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.doclib && _context.doc && _context.file) {
            const res = await this.http.delete(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.test && _context.testcase && _context.testreult && _context.file) {
            const res = await this.http.delete(`/tests/${encodeURIComponent(_context.test)}/testcases/${encodeURIComponent(_context.testcase)}/testreults/${encodeURIComponent(_context.testreult)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.test && _context.testcase && _context.testcasestep && _context.file) {
            const res = await this.http.delete(`/tests/${encodeURIComponent(_context.test)}/testcases/${encodeURIComponent(_context.testcase)}/testcasesteps/${encodeURIComponent(_context.testcasestep)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.project && _context.doclib && _context.doc && _context.file) {
            const res = await this.http.delete(`/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.product && _context.project && _context.testtask && _context.file) {
            const res = await this.http.delete(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testtasks/${encodeURIComponent(_context.testtask)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.product && _context.project && _context.testreport && _context.file) {
            const res = await this.http.delete(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.product && _context.project && _context.story && _context.file) {
            const res = await this.http.delete(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/stories/${encodeURIComponent(_context.story)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.product && _context.project && _context.task && _context.file) {
            const res = await this.http.delete(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/tasks/${encodeURIComponent(_context.task)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.product && _context.project && _context.build && _context.file) {
            const res = await this.http.delete(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.product && _context.project && _context.bug && _context.file) {
            const res = await this.http.delete(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/bugs/${encodeURIComponent(_context.bug)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.product && _context.project && _context.productplan && _context.file) {
            const res = await this.http.delete(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/productplans/${encodeURIComponent(_context.productplan)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.product && _context.project && _context.doclib && _context.file) {
            const res = await this.http.delete(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.product && _context.doclib && _context.doc && _context.file) {
            const res = await this.http.delete(`/products/${encodeURIComponent(_context.product)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.test && _context.testreport && _context.file) {
            const res = await this.http.delete(`/tests/${encodeURIComponent(_context.test)}/testreports/${encodeURIComponent(_context.testreport)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.test && _context.testcase && _context.file) {
            const res = await this.http.delete(`/tests/${encodeURIComponent(_context.test)}/testcases/${encodeURIComponent(_context.testcase)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.test && _context.bug && _context.file) {
            const res = await this.http.delete(`/tests/${encodeURIComponent(_context.test)}/bugs/${encodeURIComponent(_context.bug)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.project && _context.testtask && _context.file) {
            const res = await this.http.delete(`/projects/${encodeURIComponent(_context.project)}/testtasks/${encodeURIComponent(_context.testtask)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.project && _context.testreport && _context.file) {
            const res = await this.http.delete(`/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.project && _context.story && _context.file) {
            const res = await this.http.delete(`/projects/${encodeURIComponent(_context.project)}/stories/${encodeURIComponent(_context.story)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.project && _context.task && _context.file) {
            const res = await this.http.delete(`/projects/${encodeURIComponent(_context.project)}/tasks/${encodeURIComponent(_context.task)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.project && _context.build && _context.file) {
            const res = await this.http.delete(`/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.project && _context.bug && _context.file) {
            const res = await this.http.delete(`/projects/${encodeURIComponent(_context.project)}/bugs/${encodeURIComponent(_context.bug)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.project && _context.productplan && _context.file) {
            const res = await this.http.delete(`/projects/${encodeURIComponent(_context.project)}/productplans/${encodeURIComponent(_context.productplan)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.project && _context.doclib && _context.file) {
            const res = await this.http.delete(`/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.product && _context.project && _context.file) {
            const res = await this.http.delete(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.product && _context.story && _context.file) {
            const res = await this.http.delete(`/products/${encodeURIComponent(_context.product)}/stories/${encodeURIComponent(_context.story)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.product && _context.productrelease && _context.file) {
            const res = await this.http.delete(`/products/${encodeURIComponent(_context.product)}/productreleases/${encodeURIComponent(_context.productrelease)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.product && _context.productplan && _context.file) {
            const res = await this.http.delete(`/products/${encodeURIComponent(_context.product)}/productplans/${encodeURIComponent(_context.productplan)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.product && _context.build && _context.file) {
            const res = await this.http.delete(`/products/${encodeURIComponent(_context.product)}/builds/${encodeURIComponent(_context.build)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.product && _context.doclib && _context.file) {
            const res = await this.http.delete(`/products/${encodeURIComponent(_context.product)}/doclibs/${encodeURIComponent(_context.doclib)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.test && _context.testtask && _context.file) {
            const res = await this.http.delete(`/tests/${encodeURIComponent(_context.test)}/testtasks/${encodeURIComponent(_context.testtask)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.doclib && _context.doc && _context.file) {
            const res = await this.http.delete(`/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.sysaccount && _context.todo && _context.file) {
            const res = await this.http.delete(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/todos/${encodeURIComponent(_context.todo)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.sysaccount && _context.doc && _context.file) {
            const res = await this.http.delete(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/docs/${encodeURIComponent(_context.doc)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.weekly && _context.file) {
            const res = await this.http.delete(`/weeklies/${encodeURIComponent(_context.weekly)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.todo && _context.file) {
            const res = await this.http.delete(`/todos/${encodeURIComponent(_context.todo)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.testsuite && _context.file) {
            const res = await this.http.delete(`/testsuites/${encodeURIComponent(_context.testsuite)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.testcaselib && _context.file) {
            const res = await this.http.delete(`/testcaselibs/${encodeURIComponent(_context.testcaselib)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.reportly && _context.file) {
            const res = await this.http.delete(`/reportlies/${encodeURIComponent(_context.reportly)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.project && _context.file) {
            const res = await this.http.delete(`/projects/${encodeURIComponent(_context.project)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.product && _context.file) {
            const res = await this.http.delete(`/products/${encodeURIComponent(_context.product)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.monthly && _context.file) {
            const res = await this.http.delete(`/monthlies/${encodeURIComponent(_context.monthly)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.doc && _context.file) {
            const res = await this.http.delete(`/docs/${encodeURIComponent(_context.doc)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.doclib && _context.file) {
            const res = await this.http.delete(`/doclibs/${encodeURIComponent(_context.doclib)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        if (_context.daily && _context.file) {
            const res = await this.http.delete(`/dailies/${encodeURIComponent(_context.daily)}/files/${encodeURIComponent(_context.file)}`);
            return res;
        }
        const res = await this.http.delete(`/files/${encodeURIComponent(_context.file)}`);
        return res;
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
     * @memberof FileService
     */
    async FetchProduct(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.doclib && _context.doc && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.test && _context.testcase && _context.testreult && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testcases/${encodeURIComponent(_context.testcase)}/testreults/${encodeURIComponent(_context.testreult)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.test && _context.testcase && _context.testcasestep && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testcases/${encodeURIComponent(_context.testcase)}/testcasesteps/${encodeURIComponent(_context.testcasestep)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.project && _context.doclib && _context.doc && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.product && _context.project && _context.testtask && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testtasks/${encodeURIComponent(_context.testtask)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.product && _context.project && _context.testreport && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.product && _context.project && _context.story && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/stories/${encodeURIComponent(_context.story)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.product && _context.project && _context.task && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/tasks/${encodeURIComponent(_context.task)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.product && _context.project && _context.build && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.product && _context.project && _context.bug && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/bugs/${encodeURIComponent(_context.bug)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.product && _context.project && _context.productplan && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/productplans/${encodeURIComponent(_context.productplan)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.product && _context.project && _context.doclib && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.product && _context.doclib && _context.doc && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.test && _context.testreport && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testreports/${encodeURIComponent(_context.testreport)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.test && _context.testcase && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testcases/${encodeURIComponent(_context.testcase)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.test && _context.bug && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/bugs/${encodeURIComponent(_context.bug)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.project && _context.testtask && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/testtasks/${encodeURIComponent(_context.testtask)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.project && _context.testreport && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.project && _context.story && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/stories/${encodeURIComponent(_context.story)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.project && _context.task && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/tasks/${encodeURIComponent(_context.task)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.project && _context.build && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.project && _context.bug && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/bugs/${encodeURIComponent(_context.bug)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.project && _context.productplan && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/productplans/${encodeURIComponent(_context.productplan)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.project && _context.doclib && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.product && _context.project && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.product && _context.story && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/stories/${encodeURIComponent(_context.story)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.product && _context.productrelease && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/productreleases/${encodeURIComponent(_context.productrelease)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.product && _context.productplan && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/productplans/${encodeURIComponent(_context.productplan)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.product && _context.build && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/builds/${encodeURIComponent(_context.build)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.product && _context.doclib && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/doclibs/${encodeURIComponent(_context.doclib)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.test && _context.testtask && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testtasks/${encodeURIComponent(_context.testtask)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.doclib && _context.doc && true) {
            const res = await this.http.post(`/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.sysaccount && _context.todo && true) {
            const res = await this.http.post(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/todos/${encodeURIComponent(_context.todo)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.sysaccount && _context.doc && true) {
            const res = await this.http.post(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/docs/${encodeURIComponent(_context.doc)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.weekly && true) {
            const res = await this.http.post(`/weeklies/${encodeURIComponent(_context.weekly)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.todo && true) {
            const res = await this.http.post(`/todos/${encodeURIComponent(_context.todo)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.testsuite && true) {
            const res = await this.http.post(`/testsuites/${encodeURIComponent(_context.testsuite)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.testcaselib && true) {
            const res = await this.http.post(`/testcaselibs/${encodeURIComponent(_context.testcaselib)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.reportly && true) {
            const res = await this.http.post(`/reportlies/${encodeURIComponent(_context.reportly)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.project && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.product && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.monthly && true) {
            const res = await this.http.post(`/monthlies/${encodeURIComponent(_context.monthly)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.doc && true) {
            const res = await this.http.post(`/docs/${encodeURIComponent(_context.doc)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.doclib && true) {
            const res = await this.http.post(`/doclibs/${encodeURIComponent(_context.doclib)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        if (_context.daily && true) {
            const res = await this.http.post(`/dailies/${encodeURIComponent(_context.daily)}/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
            return res;
        }
        const res = await this.http.post(`/files/fetchproduct`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProduct');
        return res;
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
     * @memberof FileService
     */
    async FetchProject(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.doclib && _context.doc && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.test && _context.testcase && _context.testreult && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testcases/${encodeURIComponent(_context.testcase)}/testreults/${encodeURIComponent(_context.testreult)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.test && _context.testcase && _context.testcasestep && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testcases/${encodeURIComponent(_context.testcase)}/testcasesteps/${encodeURIComponent(_context.testcasestep)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.project && _context.doclib && _context.doc && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.product && _context.project && _context.testtask && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testtasks/${encodeURIComponent(_context.testtask)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.product && _context.project && _context.testreport && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.product && _context.project && _context.story && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/stories/${encodeURIComponent(_context.story)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.product && _context.project && _context.task && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/tasks/${encodeURIComponent(_context.task)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.product && _context.project && _context.build && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.product && _context.project && _context.bug && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/bugs/${encodeURIComponent(_context.bug)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.product && _context.project && _context.productplan && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/productplans/${encodeURIComponent(_context.productplan)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.product && _context.project && _context.doclib && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.product && _context.doclib && _context.doc && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.test && _context.testreport && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testreports/${encodeURIComponent(_context.testreport)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.test && _context.testcase && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testcases/${encodeURIComponent(_context.testcase)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.test && _context.bug && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/bugs/${encodeURIComponent(_context.bug)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.project && _context.testtask && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/testtasks/${encodeURIComponent(_context.testtask)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.project && _context.testreport && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.project && _context.story && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/stories/${encodeURIComponent(_context.story)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.project && _context.task && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/tasks/${encodeURIComponent(_context.task)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.project && _context.build && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.project && _context.bug && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/bugs/${encodeURIComponent(_context.bug)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.project && _context.productplan && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/productplans/${encodeURIComponent(_context.productplan)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.project && _context.doclib && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.product && _context.project && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.product && _context.story && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/stories/${encodeURIComponent(_context.story)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.product && _context.productrelease && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/productreleases/${encodeURIComponent(_context.productrelease)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.product && _context.productplan && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/productplans/${encodeURIComponent(_context.productplan)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.product && _context.build && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/builds/${encodeURIComponent(_context.build)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.product && _context.doclib && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/doclibs/${encodeURIComponent(_context.doclib)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.test && _context.testtask && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testtasks/${encodeURIComponent(_context.testtask)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.doclib && _context.doc && true) {
            const res = await this.http.post(`/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.sysaccount && _context.todo && true) {
            const res = await this.http.post(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/todos/${encodeURIComponent(_context.todo)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.sysaccount && _context.doc && true) {
            const res = await this.http.post(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/docs/${encodeURIComponent(_context.doc)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.weekly && true) {
            const res = await this.http.post(`/weeklies/${encodeURIComponent(_context.weekly)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.todo && true) {
            const res = await this.http.post(`/todos/${encodeURIComponent(_context.todo)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.testsuite && true) {
            const res = await this.http.post(`/testsuites/${encodeURIComponent(_context.testsuite)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.testcaselib && true) {
            const res = await this.http.post(`/testcaselibs/${encodeURIComponent(_context.testcaselib)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.reportly && true) {
            const res = await this.http.post(`/reportlies/${encodeURIComponent(_context.reportly)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.project && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.product && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.monthly && true) {
            const res = await this.http.post(`/monthlies/${encodeURIComponent(_context.monthly)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.doc && true) {
            const res = await this.http.post(`/docs/${encodeURIComponent(_context.doc)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.doclib && true) {
            const res = await this.http.post(`/doclibs/${encodeURIComponent(_context.doclib)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        if (_context.daily && true) {
            const res = await this.http.post(`/dailies/${encodeURIComponent(_context.daily)}/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
            return res;
        }
        const res = await this.http.post(`/files/fetchproject`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProject');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchType
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof FileService
     */
    async FetchType(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.doclib && _context.doc && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.test && _context.testcase && _context.testreult && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testcases/${encodeURIComponent(_context.testcase)}/testreults/${encodeURIComponent(_context.testreult)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.test && _context.testcase && _context.testcasestep && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testcases/${encodeURIComponent(_context.testcase)}/testcasesteps/${encodeURIComponent(_context.testcasestep)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.project && _context.doclib && _context.doc && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.product && _context.project && _context.testtask && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testtasks/${encodeURIComponent(_context.testtask)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.product && _context.project && _context.testreport && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.product && _context.project && _context.story && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/stories/${encodeURIComponent(_context.story)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.product && _context.project && _context.task && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/tasks/${encodeURIComponent(_context.task)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.product && _context.project && _context.build && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.product && _context.project && _context.bug && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/bugs/${encodeURIComponent(_context.bug)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.product && _context.project && _context.productplan && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/productplans/${encodeURIComponent(_context.productplan)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.product && _context.project && _context.doclib && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.product && _context.doclib && _context.doc && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.test && _context.testreport && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testreports/${encodeURIComponent(_context.testreport)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.test && _context.testcase && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testcases/${encodeURIComponent(_context.testcase)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.test && _context.bug && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/bugs/${encodeURIComponent(_context.bug)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.project && _context.testtask && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/testtasks/${encodeURIComponent(_context.testtask)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.project && _context.testreport && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/testreports/${encodeURIComponent(_context.testreport)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.project && _context.story && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/stories/${encodeURIComponent(_context.story)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.project && _context.task && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/tasks/${encodeURIComponent(_context.task)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.project && _context.build && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.project && _context.bug && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/bugs/${encodeURIComponent(_context.bug)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.project && _context.productplan && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/productplans/${encodeURIComponent(_context.productplan)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.project && _context.doclib && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/doclibs/${encodeURIComponent(_context.doclib)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.product && _context.project && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.product && _context.story && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/stories/${encodeURIComponent(_context.story)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.product && _context.productrelease && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/productreleases/${encodeURIComponent(_context.productrelease)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.product && _context.productplan && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/productplans/${encodeURIComponent(_context.productplan)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.product && _context.build && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/builds/${encodeURIComponent(_context.build)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.product && _context.doclib && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/doclibs/${encodeURIComponent(_context.doclib)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.test && _context.testtask && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testtasks/${encodeURIComponent(_context.testtask)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.doclib && _context.doc && true) {
            const res = await this.http.post(`/doclibs/${encodeURIComponent(_context.doclib)}/docs/${encodeURIComponent(_context.doc)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.sysaccount && _context.todo && true) {
            const res = await this.http.post(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/todos/${encodeURIComponent(_context.todo)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.sysaccount && _context.doc && true) {
            const res = await this.http.post(`/sysaccounts/${encodeURIComponent(_context.sysaccount)}/docs/${encodeURIComponent(_context.doc)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.weekly && true) {
            const res = await this.http.post(`/weeklies/${encodeURIComponent(_context.weekly)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.todo && true) {
            const res = await this.http.post(`/todos/${encodeURIComponent(_context.todo)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.testsuite && true) {
            const res = await this.http.post(`/testsuites/${encodeURIComponent(_context.testsuite)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.testcaselib && true) {
            const res = await this.http.post(`/testcaselibs/${encodeURIComponent(_context.testcaselib)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.reportly && true) {
            const res = await this.http.post(`/reportlies/${encodeURIComponent(_context.reportly)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.project && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.product && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.monthly && true) {
            const res = await this.http.post(`/monthlies/${encodeURIComponent(_context.monthly)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.doc && true) {
            const res = await this.http.post(`/docs/${encodeURIComponent(_context.doc)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.doclib && true) {
            const res = await this.http.post(`/doclibs/${encodeURIComponent(_context.doclib)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        if (_context.daily && true) {
            const res = await this.http.post(`/dailies/${encodeURIComponent(_context.daily)}/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
            return res;
        }
        const res = await this.http.post(`/files/fetchtype`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchType');
        return res;
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
}
