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
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'title';
    protected quickSearchFields = ['title',];
    protected selectContextParam = {
    };

    newEntity(data: IFile): File {
        return new File(data);
    }

    async addLocal(context: IContext, entity: IFile): Promise<IFile | null> {
        return this.cache.add(context, new File(entity) as any);
    }

    async createLocal(context: IContext, entity: IFile): Promise<IFile | null> {
        return super.createLocal(context, new File(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IFile> {
        const entity = this.cache.get(context, srfKey);
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
     * FetchType
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof FileService
     */
    async FetchType(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.test && _context.testcase && _context.testreult && true) {
            return this.http.post(`/tests/${_context.test}/testcases/${_context.testcase}/testreults/${_context.testreult}/files/fetchtype`, _data);
        }
        if (_context.test && _context.testcase && _context.testcasestep && true) {
            return this.http.post(`/tests/${_context.test}/testcases/${_context.testcase}/testcasesteps/${_context.testcasestep}/files/fetchtype`, _data);
        }
        if (_context.project && _context.doclib && _context.doc && true) {
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/files/fetchtype`, _data);
        }
        if (_context.product && _context.doclib && _context.doc && true) {
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/files/fetchtype`, _data);
        }
        if (_context.test && _context.testreport && true) {
            return this.http.post(`/tests/${_context.test}/testreports/${_context.testreport}/files/fetchtype`, _data);
        }
        if (_context.test && _context.testcase && true) {
            return this.http.post(`/tests/${_context.test}/testcases/${_context.testcase}/files/fetchtype`, _data);
        }
        if (_context.test && _context.bug && true) {
            return this.http.post(`/tests/${_context.test}/bugs/${_context.bug}/files/fetchtype`, _data);
        }
        if (_context.project && _context.testtask && true) {
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/files/fetchtype`, _data);
        }
        if (_context.project && _context.testreport && true) {
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/files/fetchtype`, _data);
        }
        if (_context.project && _context.story && true) {
            return this.http.post(`/projects/${_context.project}/stories/${_context.story}/files/fetchtype`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/files/fetchtype`, _data);
        }
        if (_context.project && _context.build && true) {
            return this.http.post(`/projects/${_context.project}/builds/${_context.build}/files/fetchtype`, _data);
        }
        if (_context.project && _context.bug && true) {
            return this.http.post(`/projects/${_context.project}/bugs/${_context.bug}/files/fetchtype`, _data);
        }
        if (_context.project && _context.productplan && true) {
            return this.http.post(`/projects/${_context.project}/productplans/${_context.productplan}/files/fetchtype`, _data);
        }
        if (_context.project && _context.doclib && true) {
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/files/fetchtype`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/files/fetchtype`, _data);
        }
        if (_context.product && _context.productrelease && true) {
            return this.http.post(`/products/${_context.product}/productreleases/${_context.productrelease}/files/fetchtype`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/files/fetchtype`, _data);
        }
        if (_context.product && _context.build && true) {
            return this.http.post(`/products/${_context.product}/builds/${_context.build}/files/fetchtype`, _data);
        }
        if (_context.product && _context.doclib && true) {
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/files/fetchtype`, _data);
        }
        if (_context.test && _context.testtask && true) {
            return this.http.post(`/tests/${_context.test}/testtasks/${_context.testtask}/files/fetchtype`, _data);
        }
        if (_context.doclib && _context.doc && true) {
            return this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/files/fetchtype`, _data);
        }
        if (_context.sysaccount && _context.todo && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/todos/${_context.todo}/files/fetchtype`, _data);
        }
        if (_context.sysaccount && _context.doc && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/docs/${_context.doc}/files/fetchtype`, _data);
        }
        if (_context.ibzweekly && true) {
            return this.http.post(`/ibzweeklies/${_context.ibzweekly}/files/fetchtype`, _data);
        }
        if (_context.todo && true) {
            return this.http.post(`/todos/${_context.todo}/files/fetchtype`, _data);
        }
        if (_context.testsuite && true) {
            return this.http.post(`/testsuites/${_context.testsuite}/files/fetchtype`, _data);
        }
        if (_context.testcaselib && true) {
            return this.http.post(`/testcaselibs/${_context.testcaselib}/files/fetchtype`, _data);
        }
        if (_context.reportly && true) {
            return this.http.post(`/reportlies/${_context.reportly}/files/fetchtype`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/files/fetchtype`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/files/fetchtype`, _data);
        }
        if (_context.monthly && true) {
            return this.http.post(`/monthlies/${_context.monthly}/files/fetchtype`, _data);
        }
        if (_context.doc && true) {
            return this.http.post(`/docs/${_context.doc}/files/fetchtype`, _data);
        }
        if (_context.doclib && true) {
            return this.http.post(`/doclibs/${_context.doclib}/files/fetchtype`, _data);
        }
        if (_context.daily && true) {
            return this.http.post(`/dailies/${_context.daily}/files/fetchtype`, _data);
        }
        return this.http.post(`/files/fetchtype`, _data);
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
        if (_context.test && _context.testcase && _context.testreult && true) {
            return this.http.post(`/tests/${_context.test}/testcases/${_context.testcase}/testreults/${_context.testreult}/files/fetchproject`, _data);
        }
        if (_context.test && _context.testcase && _context.testcasestep && true) {
            return this.http.post(`/tests/${_context.test}/testcases/${_context.testcase}/testcasesteps/${_context.testcasestep}/files/fetchproject`, _data);
        }
        if (_context.project && _context.doclib && _context.doc && true) {
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/files/fetchproject`, _data);
        }
        if (_context.product && _context.doclib && _context.doc && true) {
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/files/fetchproject`, _data);
        }
        if (_context.test && _context.testreport && true) {
            return this.http.post(`/tests/${_context.test}/testreports/${_context.testreport}/files/fetchproject`, _data);
        }
        if (_context.test && _context.testcase && true) {
            return this.http.post(`/tests/${_context.test}/testcases/${_context.testcase}/files/fetchproject`, _data);
        }
        if (_context.test && _context.bug && true) {
            return this.http.post(`/tests/${_context.test}/bugs/${_context.bug}/files/fetchproject`, _data);
        }
        if (_context.project && _context.testtask && true) {
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/files/fetchproject`, _data);
        }
        if (_context.project && _context.testreport && true) {
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/files/fetchproject`, _data);
        }
        if (_context.project && _context.story && true) {
            return this.http.post(`/projects/${_context.project}/stories/${_context.story}/files/fetchproject`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/files/fetchproject`, _data);
        }
        if (_context.project && _context.build && true) {
            return this.http.post(`/projects/${_context.project}/builds/${_context.build}/files/fetchproject`, _data);
        }
        if (_context.project && _context.bug && true) {
            return this.http.post(`/projects/${_context.project}/bugs/${_context.bug}/files/fetchproject`, _data);
        }
        if (_context.project && _context.productplan && true) {
            return this.http.post(`/projects/${_context.project}/productplans/${_context.productplan}/files/fetchproject`, _data);
        }
        if (_context.project && _context.doclib && true) {
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/files/fetchproject`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/files/fetchproject`, _data);
        }
        if (_context.product && _context.productrelease && true) {
            return this.http.post(`/products/${_context.product}/productreleases/${_context.productrelease}/files/fetchproject`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/files/fetchproject`, _data);
        }
        if (_context.product && _context.build && true) {
            return this.http.post(`/products/${_context.product}/builds/${_context.build}/files/fetchproject`, _data);
        }
        if (_context.product && _context.doclib && true) {
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/files/fetchproject`, _data);
        }
        if (_context.test && _context.testtask && true) {
            return this.http.post(`/tests/${_context.test}/testtasks/${_context.testtask}/files/fetchproject`, _data);
        }
        if (_context.doclib && _context.doc && true) {
            return this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/files/fetchproject`, _data);
        }
        if (_context.sysaccount && _context.todo && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/todos/${_context.todo}/files/fetchproject`, _data);
        }
        if (_context.sysaccount && _context.doc && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/docs/${_context.doc}/files/fetchproject`, _data);
        }
        if (_context.ibzweekly && true) {
            return this.http.post(`/ibzweeklies/${_context.ibzweekly}/files/fetchproject`, _data);
        }
        if (_context.todo && true) {
            return this.http.post(`/todos/${_context.todo}/files/fetchproject`, _data);
        }
        if (_context.testsuite && true) {
            return this.http.post(`/testsuites/${_context.testsuite}/files/fetchproject`, _data);
        }
        if (_context.testcaselib && true) {
            return this.http.post(`/testcaselibs/${_context.testcaselib}/files/fetchproject`, _data);
        }
        if (_context.reportly && true) {
            return this.http.post(`/reportlies/${_context.reportly}/files/fetchproject`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/files/fetchproject`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/files/fetchproject`, _data);
        }
        if (_context.monthly && true) {
            return this.http.post(`/monthlies/${_context.monthly}/files/fetchproject`, _data);
        }
        if (_context.doc && true) {
            return this.http.post(`/docs/${_context.doc}/files/fetchproject`, _data);
        }
        if (_context.doclib && true) {
            return this.http.post(`/doclibs/${_context.doclib}/files/fetchproject`, _data);
        }
        if (_context.daily && true) {
            return this.http.post(`/dailies/${_context.daily}/files/fetchproject`, _data);
        }
        return this.http.post(`/files/fetchproject`, _data);
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
        if (_context.test && _context.testcase && _context.testreult && true) {
            return this.http.post(`/tests/${_context.test}/testcases/${_context.testcase}/testreults/${_context.testreult}/files/fetchproduct`, _data);
        }
        if (_context.test && _context.testcase && _context.testcasestep && true) {
            return this.http.post(`/tests/${_context.test}/testcases/${_context.testcase}/testcasesteps/${_context.testcasestep}/files/fetchproduct`, _data);
        }
        if (_context.project && _context.doclib && _context.doc && true) {
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/files/fetchproduct`, _data);
        }
        if (_context.product && _context.doclib && _context.doc && true) {
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/files/fetchproduct`, _data);
        }
        if (_context.test && _context.testreport && true) {
            return this.http.post(`/tests/${_context.test}/testreports/${_context.testreport}/files/fetchproduct`, _data);
        }
        if (_context.test && _context.testcase && true) {
            return this.http.post(`/tests/${_context.test}/testcases/${_context.testcase}/files/fetchproduct`, _data);
        }
        if (_context.test && _context.bug && true) {
            return this.http.post(`/tests/${_context.test}/bugs/${_context.bug}/files/fetchproduct`, _data);
        }
        if (_context.project && _context.testtask && true) {
            return this.http.post(`/projects/${_context.project}/testtasks/${_context.testtask}/files/fetchproduct`, _data);
        }
        if (_context.project && _context.testreport && true) {
            return this.http.post(`/projects/${_context.project}/testreports/${_context.testreport}/files/fetchproduct`, _data);
        }
        if (_context.project && _context.story && true) {
            return this.http.post(`/projects/${_context.project}/stories/${_context.story}/files/fetchproduct`, _data);
        }
        if (_context.project && _context.task && true) {
            return this.http.post(`/projects/${_context.project}/tasks/${_context.task}/files/fetchproduct`, _data);
        }
        if (_context.project && _context.build && true) {
            return this.http.post(`/projects/${_context.project}/builds/${_context.build}/files/fetchproduct`, _data);
        }
        if (_context.project && _context.bug && true) {
            return this.http.post(`/projects/${_context.project}/bugs/${_context.bug}/files/fetchproduct`, _data);
        }
        if (_context.project && _context.productplan && true) {
            return this.http.post(`/projects/${_context.project}/productplans/${_context.productplan}/files/fetchproduct`, _data);
        }
        if (_context.project && _context.doclib && true) {
            return this.http.post(`/projects/${_context.project}/doclibs/${_context.doclib}/files/fetchproduct`, _data);
        }
        if (_context.product && _context.story && true) {
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/files/fetchproduct`, _data);
        }
        if (_context.product && _context.productrelease && true) {
            return this.http.post(`/products/${_context.product}/productreleases/${_context.productrelease}/files/fetchproduct`, _data);
        }
        if (_context.product && _context.productplan && true) {
            return this.http.post(`/products/${_context.product}/productplans/${_context.productplan}/files/fetchproduct`, _data);
        }
        if (_context.product && _context.build && true) {
            return this.http.post(`/products/${_context.product}/builds/${_context.build}/files/fetchproduct`, _data);
        }
        if (_context.product && _context.doclib && true) {
            return this.http.post(`/products/${_context.product}/doclibs/${_context.doclib}/files/fetchproduct`, _data);
        }
        if (_context.test && _context.testtask && true) {
            return this.http.post(`/tests/${_context.test}/testtasks/${_context.testtask}/files/fetchproduct`, _data);
        }
        if (_context.doclib && _context.doc && true) {
            return this.http.post(`/doclibs/${_context.doclib}/docs/${_context.doc}/files/fetchproduct`, _data);
        }
        if (_context.sysaccount && _context.todo && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/todos/${_context.todo}/files/fetchproduct`, _data);
        }
        if (_context.sysaccount && _context.doc && true) {
            return this.http.post(`/sysaccounts/${_context.sysaccount}/docs/${_context.doc}/files/fetchproduct`, _data);
        }
        if (_context.ibzweekly && true) {
            return this.http.post(`/ibzweeklies/${_context.ibzweekly}/files/fetchproduct`, _data);
        }
        if (_context.todo && true) {
            return this.http.post(`/todos/${_context.todo}/files/fetchproduct`, _data);
        }
        if (_context.testsuite && true) {
            return this.http.post(`/testsuites/${_context.testsuite}/files/fetchproduct`, _data);
        }
        if (_context.testcaselib && true) {
            return this.http.post(`/testcaselibs/${_context.testcaselib}/files/fetchproduct`, _data);
        }
        if (_context.reportly && true) {
            return this.http.post(`/reportlies/${_context.reportly}/files/fetchproduct`, _data);
        }
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/files/fetchproduct`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/files/fetchproduct`, _data);
        }
        if (_context.monthly && true) {
            return this.http.post(`/monthlies/${_context.monthly}/files/fetchproduct`, _data);
        }
        if (_context.doc && true) {
            return this.http.post(`/docs/${_context.doc}/files/fetchproduct`, _data);
        }
        if (_context.doclib && true) {
            return this.http.post(`/doclibs/${_context.doclib}/files/fetchproduct`, _data);
        }
        if (_context.daily && true) {
            return this.http.post(`/dailies/${_context.daily}/files/fetchproduct`, _data);
        }
        return this.http.post(`/files/fetchproduct`, _data);
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
        if (_context.test && _context.testcase && _context.testreult && _context.file) {
            return this.http.delete(`/tests/${_context.test}/testcases/${_context.testcase}/testreults/${_context.testreult}/files/${_context.file}`);
        }
        if (_context.test && _context.testcase && _context.testcasestep && _context.file) {
            return this.http.delete(`/tests/${_context.test}/testcases/${_context.testcase}/testcasesteps/${_context.testcasestep}/files/${_context.file}`);
        }
        if (_context.project && _context.doclib && _context.doc && _context.file) {
            return this.http.delete(`/projects/${_context.project}/doclibs/${_context.doclib}/docs/${_context.doc}/files/${_context.file}`);
        }
        if (_context.product && _context.doclib && _context.doc && _context.file) {
            return this.http.delete(`/products/${_context.product}/doclibs/${_context.doclib}/docs/${_context.doc}/files/${_context.file}`);
        }
        if (_context.test && _context.testreport && _context.file) {
            return this.http.delete(`/tests/${_context.test}/testreports/${_context.testreport}/files/${_context.file}`);
        }
        if (_context.test && _context.testcase && _context.file) {
            return this.http.delete(`/tests/${_context.test}/testcases/${_context.testcase}/files/${_context.file}`);
        }
        if (_context.test && _context.bug && _context.file) {
            return this.http.delete(`/tests/${_context.test}/bugs/${_context.bug}/files/${_context.file}`);
        }
        if (_context.project && _context.testtask && _context.file) {
            return this.http.delete(`/projects/${_context.project}/testtasks/${_context.testtask}/files/${_context.file}`);
        }
        if (_context.project && _context.testreport && _context.file) {
            return this.http.delete(`/projects/${_context.project}/testreports/${_context.testreport}/files/${_context.file}`);
        }
        if (_context.project && _context.story && _context.file) {
            return this.http.delete(`/projects/${_context.project}/stories/${_context.story}/files/${_context.file}`);
        }
        if (_context.project && _context.task && _context.file) {
            return this.http.delete(`/projects/${_context.project}/tasks/${_context.task}/files/${_context.file}`);
        }
        if (_context.project && _context.build && _context.file) {
            return this.http.delete(`/projects/${_context.project}/builds/${_context.build}/files/${_context.file}`);
        }
        if (_context.project && _context.bug && _context.file) {
            return this.http.delete(`/projects/${_context.project}/bugs/${_context.bug}/files/${_context.file}`);
        }
        if (_context.project && _context.productplan && _context.file) {
            return this.http.delete(`/projects/${_context.project}/productplans/${_context.productplan}/files/${_context.file}`);
        }
        if (_context.project && _context.doclib && _context.file) {
            return this.http.delete(`/projects/${_context.project}/doclibs/${_context.doclib}/files/${_context.file}`);
        }
        if (_context.product && _context.story && _context.file) {
            return this.http.delete(`/products/${_context.product}/stories/${_context.story}/files/${_context.file}`);
        }
        if (_context.product && _context.productrelease && _context.file) {
            return this.http.delete(`/products/${_context.product}/productreleases/${_context.productrelease}/files/${_context.file}`);
        }
        if (_context.product && _context.productplan && _context.file) {
            return this.http.delete(`/products/${_context.product}/productplans/${_context.productplan}/files/${_context.file}`);
        }
        if (_context.product && _context.build && _context.file) {
            return this.http.delete(`/products/${_context.product}/builds/${_context.build}/files/${_context.file}`);
        }
        if (_context.product && _context.doclib && _context.file) {
            return this.http.delete(`/products/${_context.product}/doclibs/${_context.doclib}/files/${_context.file}`);
        }
        if (_context.test && _context.testtask && _context.file) {
            return this.http.delete(`/tests/${_context.test}/testtasks/${_context.testtask}/files/${_context.file}`);
        }
        if (_context.doclib && _context.doc && _context.file) {
            return this.http.delete(`/doclibs/${_context.doclib}/docs/${_context.doc}/files/${_context.file}`);
        }
        if (_context.sysaccount && _context.todo && _context.file) {
            return this.http.delete(`/sysaccounts/${_context.sysaccount}/todos/${_context.todo}/files/${_context.file}`);
        }
        if (_context.sysaccount && _context.doc && _context.file) {
            return this.http.delete(`/sysaccounts/${_context.sysaccount}/docs/${_context.doc}/files/${_context.file}`);
        }
        if (_context.ibzweekly && _context.file) {
            return this.http.delete(`/ibzweeklies/${_context.ibzweekly}/files/${_context.file}`);
        }
        if (_context.todo && _context.file) {
            return this.http.delete(`/todos/${_context.todo}/files/${_context.file}`);
        }
        if (_context.testsuite && _context.file) {
            return this.http.delete(`/testsuites/${_context.testsuite}/files/${_context.file}`);
        }
        if (_context.testcaselib && _context.file) {
            return this.http.delete(`/testcaselibs/${_context.testcaselib}/files/${_context.file}`);
        }
        if (_context.reportly && _context.file) {
            return this.http.delete(`/reportlies/${_context.reportly}/files/${_context.file}`);
        }
        if (_context.project && _context.file) {
            return this.http.delete(`/projects/${_context.project}/files/${_context.file}`);
        }
        if (_context.product && _context.file) {
            return this.http.delete(`/products/${_context.product}/files/${_context.file}`);
        }
        if (_context.monthly && _context.file) {
            return this.http.delete(`/monthlies/${_context.monthly}/files/${_context.file}`);
        }
        if (_context.doc && _context.file) {
            return this.http.delete(`/docs/${_context.doc}/files/${_context.file}`);
        }
        if (_context.doclib && _context.file) {
            return this.http.delete(`/doclibs/${_context.doclib}/files/${_context.file}`);
        }
        if (_context.daily && _context.file) {
            return this.http.delete(`/dailies/${_context.daily}/files/${_context.file}`);
        }
        return this.http.delete(`/files/${_context.file}`);
    }
}
