import { CodeListService } from '../app/codelist-service';
import { EntityBaseService, IContext, HttpResponse } from 'ibiz-core';
import { IBuild, Build } from '../../entities';
import keys from '../../entities/build/build-keys';
import { isNil, isEmpty } from 'ramda';
import { PSDEDQCondEngine } from 'ibiz-core';

/**
 * 版本服务对象基类
 *
 * @export
 * @class BuildBaseService
 * @extends {EntityBaseService}
 */
export class BuildBaseService extends EntityBaseService<IBuild> {
    protected get keys(): string[] {
        return keys;
    }
    protected SYSTEMNAME = 'iBizPMS';
    protected APPNAME = 'Web';
    protected APPDENAME = 'Build';
    protected APPDENAMEPLURAL = 'Builds';
    protected dynaModelFilePath:string = 'PSSYSAPPS/Web/PSAPPDATAENTITIES/Build.json';
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name',];
    protected selectContextParam = {
        product: 'product',
        project: 'project',
    };

    constructor(opts?: any) {
        super(opts, 'Build');
    }

    newEntity(data: IBuild): Build {
        return new Build(data);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IBuild> {
        const entity = await super.getLocal(context, srfKey);
        if (entity && entity.product && entity.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(context, entity.product);
            if (data) {
                entity.productname = data.name;
                entity.product = data.id;
            }
        }
        if (entity && entity.project && entity.project !== '') {
            const s = await ___ibz___.gs.getProjectService();
            const data = await s.getLocal2(context, entity.project);
            if (data) {
                entity.project = data.id;
            }
        }
        return entity!;
    }

    async updateLocal(context: IContext, entity: IBuild): Promise<IBuild> {
        return super.updateLocal(context, new Build(entity) as any);
    }

    async getDraftLocal(_context: IContext, entity: IBuild = {}): Promise<IBuild> {
        if (_context.product && _context.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(_context, _context.product);
            if (data) {
                entity.productname = data.name;
                entity.product = data.id;
            }
        }
        if (_context.project && _context.project !== '') {
            const s = await ___ibz___.gs.getProjectService();
            const data = await s.getLocal2(_context, _context.project);
            if (data) {
                entity.project = data.id;
            }
        }
        return new Build(entity);
    }

    /**
     * 深度拷贝「默认支持」
     *
     * @param {*} [context={}]
     * @param {*} [data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BuildService
     */
    async DeepCopyTemp(context: any = {}, data: any = {}): Promise<HttpResponse> {
        let entity: any;
        const result = await this.CopyTemp(context, data);
        if (result.ok) {
            entity = result.data;
        }
        return new HttpResponse(entity);
    }

    protected getBugProductBuildCond() {
        return this.condCache.get('bugProductBuild');
    }

    protected getBugProductOrProjectBuildCond() {
        return this.condCache.get('bugProductOrProjectBuild');
    }

    protected getCurProductCond() {
        if (!this.condCache.has('curProduct')) {
            const strCond: any[] = ['AND', ['OR', ['EQ', 'PRODUCT',{ type: 'DATACONTEXT', value: 'product'}], ['EQ', 'PRODUCT',{ type: 'WEBCONTEXT', value: 'product'}]]];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('curProduct', cond);
            }
        }
        return this.condCache.get('curProduct');
    }

    protected getDefaultCond() {
        return this.condCache.get('default');
    }

    protected getSimpleCond() {
        return this.condCache.get('simple');
    }

    protected getTestBuildCond() {
        return this.condCache.get('testBuild');
    }

    protected getTestRoundsCond() {
        if (!this.condCache.has('testRounds')) {
            const strCond: any[] = ['AND'];
            if (!isNil(strCond) && !isEmpty(strCond)) {
                const cond = new PSDEDQCondEngine();
                cond.parse(strCond);
                this.condCache.set('testRounds', cond);
            }
        }
        return this.condCache.get('testRounds');
    }

    protected getUpdateLogCond() {
        return this.condCache.get('updateLog');
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
     * @memberof BuildService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && true) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Create');
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/builds`, _data);
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
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/builds`, _data);
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
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/builds`, _data);
            return res;
        }
    this.log.warn([`[Build]>>>[Create函数]异常`]);
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
     * @memberof BuildService
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.build) {
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.project && _context.build) {
            const res = await this.http.get(`/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
        if (_context.product && _context.build) {
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/builds/${encodeURIComponent(_context.build)}`);
        res.data = await this.afterExecuteAction(_context,res?.data,'Get');
            return res;
        }
    this.log.warn([`[Build]>>>[Get函数]异常`]);
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
     * @memberof BuildService
     */
    async GetDraft(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/builds/getdraft`, _data);
            return res;
        }
        if (_context.project && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${encodeURIComponent(_context.project)}/builds/getdraft`, _data);
            return res;
        }
        if (_context.product && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${encodeURIComponent(_context.product)}/builds/getdraft`, _data);
            return res;
        }
    this.log.warn([`[Build]>>>[GetDraft函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * LinkBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BuildService
     */
    async LinkBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.build) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'LinkBug');
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}/linkbug`, _data);
            return res;
        }
        if (_context.project && _context.build) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'LinkBug');
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}/linkbug`, _data);
            return res;
        }
        if (_context.product && _context.build) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'LinkBug');
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/builds/${encodeURIComponent(_context.build)}/linkbug`, _data);
            return res;
        }
    this.log.warn([`[Build]>>>[LinkBug函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * LinkStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BuildService
     */
    async LinkStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.build) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'LinkStory');
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}/linkstory`, _data);
            return res;
        }
        if (_context.project && _context.build) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'LinkStory');
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}/linkstory`, _data);
            return res;
        }
        if (_context.product && _context.build) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'LinkStory');
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/builds/${encodeURIComponent(_context.build)}/linkstory`, _data);
            return res;
        }
    this.log.warn([`[Build]>>>[LinkStory函数]异常`]);
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
     * @memberof BuildService
     */
    async Remove(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.build) {
            const res = await this.http.delete(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}`);
            return res;
        }
        if (_context.project && _context.build) {
            const res = await this.http.delete(`/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}`);
            return res;
        }
        if (_context.product && _context.build) {
            const res = await this.http.delete(`/products/${encodeURIComponent(_context.product)}/builds/${encodeURIComponent(_context.build)}`);
            return res;
        }
    this.log.warn([`[Build]>>>[Remove函数]异常`]);
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
     * @memberof BuildService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && _context.build) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}`, _data);
            return res;
        }
        if (_context.project && _context.build) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/projects/${encodeURIComponent(_context.project)}/builds/${encodeURIComponent(_context.build)}`, _data);
            return res;
        }
        if (_context.product && _context.build) {
        _data = await this.obtainMinor(_context, _data);
        _data = await this.beforeExecuteAction(_context,_data,'Update');
            const res = await this.http.put(`/products/${encodeURIComponent(_context.product)}/builds/${encodeURIComponent(_context.build)}`, _data);
            return res;
        }
    this.log.warn([`[Build]>>>[Update函数]异常`]);
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
     * @memberof BuildService
     */
    async FetchDefault(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/builds/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.project && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/builds/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
        if (_context.product && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/builds/fetchdefault`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchDefault');
            return res;
        }
    this.log.warn([`[Build]>>>[FetchDefault函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
    }
    /**
     * FetchProductBuild
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BuildService
     */
    async FetchProductBuild(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        try {
        if (_context.product && _context.project && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/projects/${encodeURIComponent(_context.project)}/builds/fetchproductbuild`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProductBuild');
            return res;
        }
        if (_context.project && true) {
            const res = await this.http.post(`/projects/${encodeURIComponent(_context.project)}/builds/fetchproductbuild`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProductBuild');
            return res;
        }
        if (_context.product && true) {
            const res = await this.http.post(`/products/${encodeURIComponent(_context.product)}/builds/fetchproductbuild`, _data);
        res.data = await this.afterExecuteActionBatch(_context,res?.data,'FetchProductBuild');
            return res;
        }
    this.log.warn([`[Build]>>>[FetchProductBuild函数]异常`]);
    return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
            } catch (error) {
                return this.handleResponseError(error);
            }
    }

    /**
     * LinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BuildServiceBase
     */
    public async LinkBugBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.project && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/builds/linkbugbatch`,_data);
            return res;
        }
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/projects/${_context.project}/builds/linkbugbatch`,_data);
            return res;
        }
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/products/${_context.product}/builds/linkbugbatch`,_data);
            return res;
        }
        this.log.warn([`[Build]>>>[LinkBugBatch函数]异常`]);
        return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
    }

    /**
     * LinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BuildServiceBase
     */
    public async LinkStoryBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.product && _context.project && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/products/${_context.product}/projects/${_context.project}/builds/linkstorybatch`,_data);
            return res;
        }
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/projects/${_context.project}/builds/linkstorybatch`,_data);
            return res;
        }
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            const res = await this.http.post(`/products/${_context.product}/builds/linkstorybatch`,_data);
            return res;
        }
        this.log.warn([`[Build]>>>[LinkStoryBatch函数]异常`]);
        return new HttpResponse({message:'无匹配请求地址'}, { status: 404, statusText: '无匹配请求地址!' });
    }
}
