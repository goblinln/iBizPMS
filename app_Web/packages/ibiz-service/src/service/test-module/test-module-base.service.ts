import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { ITestModule, TestModule } from '../../entities';
import keys from '../../entities/test-module/test-module-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 测试模块服务对象基类
 *
 * @export
 * @class TestModuleBaseService
 * @extends {EntityBaseService}
 */
export class TestModuleBaseService extends EntityBaseService<ITestModule> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'TestModule';
    protected APPDENAMEPLURAL = 'TestModules';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/TestModule.json';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name',];
    protected selectContextParam = {
        test: 'root',
    };

    constructor(opts?: any) {
        super(opts, 'TestModule');
    }

    newEntity(data: ITestModule): TestModule {
        return new TestModule(data);
    }

    async getLocal(context: IContext, srfKey: string): Promise<ITestModule> {
        const entity = await super.getLocal(context, srfKey);
        if (entity && entity.root && entity.root !== '') {
            const s = await ___ibz___.gs.getTestService();
            const data = await s.getLocal2(context, entity.root);
            if (data) {
                entity.rootname = data.name;
                entity.root = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: ITestModule): Promise<ITestModule> {
        return super.updateLocal(context, new TestModule(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: ITestModule = {}): Promise<ITestModule> {
        if (_context.test && _context.test !== '') {
            const s = await ___ibz___.gs.getTestService();
            const data = await s.getLocal2(_context, _context.test);
            if (data) {
                entity.rootname = data.name;
                entity.root = data.id;
            }
        }
        return new TestModule(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestModuleService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }

    protected getByPathCond() {
        if (!this.condCache.has('byPath')) {
            const strCond: any[] = ['AND', ['EQ', 'TYPE','bug']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('byPath', cond);
            }
        }
        return this.condCache.get('byPath');
    }

    protected getDefaultCond() {
        if (!this.condCache.has('default')) {
            const strCond: any[] = ['AND', ['EQ', 'TYPE','story'], ['EQ', 'DELETED','0']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('default', cond);
            }
        }
        return this.condCache.get('default');
    }

    protected getParentModuleCond() {
        return this.condCache.get('parentModule');
    }

    protected getRootCond() {
        if (!this.condCache.has('root')) {
            const strCond: any[] = ['AND', ['EQ', 'TYPE','story'], ['OR', ['ISNULL', 'PARENT',''], ['EQ', 'PARENT','0']]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('root', cond);
            }
        }
        return this.condCache.get('root');
    }

    protected getRoot_NoBranchCond() {
        if (!this.condCache.has('root_NoBranch')) {
            const strCond: any[] = ['AND', ['EQ', 'TYPE','story'], ['EQ', 'BRANCH','0'], ['OR', ['ISNULL', 'PARENT',''], ['EQ', 'PARENT','0']]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('root_NoBranch', cond);
            }
        }
        return this.condCache.get('root_NoBranch');
    }

    protected getTestModuleCond() {
        if (!this.condCache.has('testModule')) {
            const strCond: any[] = ['AND', ['EQ', 'TYPE','story'], ['EQ', 'DELETED','0']];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('testModule', cond);
            }
        }
        return this.condCache.get('testModule');
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
     * @memberof TestModuleService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.test && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testmodules`, _data);
            return res;
        }
    this.log.warn([`[TestModule]>>>[Create函数]异常`]);
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
     * @memberof TestModuleService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.test && _context.testmodule) {
            const res = await this.http.get(`/tests/${encodeURIComponent(_context.test)}/testmodules/${encodeURIComponent(_context.testmodule)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
    this.log.warn([`[TestModule]>>>[Get函数]异常`]);
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
     * @memberof TestModuleService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.test && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/tests/${encodeURIComponent(_context.test)}/testmodules/getdraft`, _data);
            return res;
        }
    this.log.warn([`[TestModule]>>>[GetDraft函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * Remove
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestModuleService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.test && _context.testmodule) {
            const res = await this.http.delete(`/tests/${encodeURIComponent(_context.test)}/testmodules/${encodeURIComponent(_context.testmodule)}`);
            return res;
        }
    this.log.warn([`[TestModule]>>>[Remove函数]异常`]);
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
     * @memberof TestModuleService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.test && _context.testmodule) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/tests/${encodeURIComponent(_context.test)}/testmodules/${encodeURIComponent(_context.testmodule)}`, _data);
            return res;
        }
    this.log.warn([`[TestModule]>>>[Update函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchDefault
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof TestModuleService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.test && true) {
            const res = await this.http.post(`/tests/${encodeURIComponent(_context.test)}/testmodules/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
    this.log.warn([`[TestModule]>>>[FetchDefault函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
    }

    /**
     * SaveBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof TestModuleServiceBase
     */
    public async SaveBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.test && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/tests/${_context.test}/testmodules/savebatch`,_data);
            return res;
        }
        this.log.warn([`[TestModule]>>>[SaveBatch函数]异常`]);
        return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
    }
}
