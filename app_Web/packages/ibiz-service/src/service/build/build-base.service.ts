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
    protected APPDEKEY = 'id';
    protected APPDETEXT = 'name';
    protected quickSearchFields = ['name',];
    protected selectContextParam = {
        product: 'product',
        project: 'project',
    };

    newEntity(data: IBuild): Build {
        return new Build(data);
    }

    async addLocal(context: IContext, entity: IBuild): Promise<IBuild | null> {
        return this.cache.add(context, new Build(entity) as any);
    }

    async createLocal(context: IContext, entity: IBuild): Promise<IBuild | null> {
        return super.createLocal(context, new Build(entity) as any);
    }

    async getLocal(context: IContext, srfKey: string): Promise<IBuild> {
        const entity = this.cache.get(context, srfKey);
        if (entity && entity.product && entity.product !== '') {
            const s = await ___ibz___.gs.getProductService();
            const data = await s.getLocal2(context, entity.product);
            if (data) {
                entity.productname = data.name;
                entity.product = data.productsn;
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
                entity.product = data.productsn;
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
     * Select
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BuildService
     */
    async Select(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.build) {
            return this.http.get(`/projects/${_context.project}/builds/${_context.build}/select`);
        }
        if (_context.product && _context.build) {
            return this.http.get(`/products/${_context.product}/builds/${_context.build}/select`);
        }
        return this.http.get(`/builds/${_context.build}/select`);
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
        if (_context.project && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/projects/${_context.project}/builds`, _data);
        }
        if (_context.product && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            return this.http.post(`/products/${_context.product}/builds`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        return this.http.post(`/builds`, _data);
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
        if (_context.project && _context.build) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/builds/${_context.build}`, _data);
        }
        if (_context.product && _context.build) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/builds/${_context.build}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.put(`/builds/${_context.build}`, _data);
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
        if (_context.project && _context.build) {
            return this.http.delete(`/projects/${_context.project}/builds/${_context.build}`);
        }
        if (_context.product && _context.build) {
            return this.http.delete(`/products/${_context.product}/builds/${_context.build}`);
        }
        return this.http.delete(`/builds/${_context.build}`);
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
        if (_context.project && _context.build) {
            const res = await this.http.get(`/projects/${_context.project}/builds/${_context.build}`);
            return res;
        }
        if (_context.product && _context.build) {
            const res = await this.http.get(`/products/${_context.product}/builds/${_context.build}`);
            return res;
        }
        const res = await this.http.get(`/builds/${_context.build}`);
        return res;
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
        if (_context.project && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/projects/${_context.project}/builds/getdraft`, _data);
            return res;
        }
        if (_context.product && true) {
            _data[this.APPDENAME?.toLowerCase()] = undefined;
            _data[this.APPDEKEY] = undefined;
            const res = await this.http.get(`/products/${_context.product}/builds/getdraft`, _data);
            return res;
        }
        _data[this.APPDENAME?.toLowerCase()] = undefined;
        _data[this.APPDEKEY] = undefined;
        const res = await this.http.get(`/builds/getdraft`, _data);
        return res;
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
        if (_context.project && _context.build) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/builds/${_context.build}/linkbug`, _data);
        }
        if (_context.product && _context.build) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/builds/${_context.build}/linkbug`, _data);
        }
        return this.http.post(`/builds/${_context.build}/linkbug`, _data);
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
        if (_context.project && _context.build) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/builds/${_context.build}/linkstory`, _data);
        }
        if (_context.product && _context.build) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/builds/${_context.build}/linkstory`, _data);
        }
        return this.http.post(`/builds/${_context.build}/linkstory`, _data);
    }
    /**
     * MobProjectBuildCounter
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BuildService
     */
    async MobProjectBuildCounter(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.build) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/projects/${_context.project}/builds/${_context.build}/mobprojectbuildcounter`, _data);
        }
        if (_context.product && _context.build) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.put(`/products/${_context.product}/builds/${_context.build}/mobprojectbuildcounter`, _data);
        }
        return this.http.put(`/builds/${_context.build}/mobprojectbuildcounter`, _data);
    }
    /**
     * OneClickRelease
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BuildService
     */
    async OneClickRelease(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.build) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/builds/${_context.build}/oneclickrelease`, _data);
        }
        if (_context.product && _context.build) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/builds/${_context.build}/oneclickrelease`, _data);
        }
        return this.http.post(`/builds/${_context.build}/oneclickrelease`, _data);
    }
    /**
     * UnlinkBug
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BuildService
     */
    async UnlinkBug(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.build) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/builds/${_context.build}/unlinkbug`, _data);
        }
        if (_context.product && _context.build) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/builds/${_context.build}/unlinkbug`, _data);
        }
        return this.http.post(`/builds/${_context.build}/unlinkbug`, _data);
    }
    /**
     * UnlinkStory
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BuildService
     */
    async UnlinkStory(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && _context.build) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/builds/${_context.build}/unlinkstory`, _data);
        }
        if (_context.product && _context.build) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/builds/${_context.build}/unlinkstory`, _data);
        }
        return this.http.post(`/builds/${_context.build}/unlinkstory`, _data);
    }
    /**
     * FetchBugProductBuild
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BuildService
     */
    async FetchBugProductBuild(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/builds/fetchbugproductbuild`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/builds/fetchbugproductbuild`, _data);
        }
        return this.http.post(`/builds/fetchbugproductbuild`, _data);
    }
    /**
     * FetchBugProductOrProjectBuild
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BuildService
     */
    async FetchBugProductOrProjectBuild(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/builds/fetchbugproductorprojectbuild`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/builds/fetchbugproductorprojectbuild`, _data);
        }
        return this.http.post(`/builds/fetchbugproductorprojectbuild`, _data);
    }
    /**
     * FetchCurProduct
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BuildService
     */
    async FetchCurProduct(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/builds/fetchcurproduct`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/builds/fetchcurproduct`, _data);
        }
        return this.http.post(`/builds/fetchcurproduct`, _data);
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
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/builds/fetchdefault`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/builds/fetchdefault`, _data);
        }
        return this.http.post(`/builds/fetchdefault`, _data);
    }
    /**
     * FetchTestBuild
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BuildService
     */
    async FetchTestBuild(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/builds/fetchtestbuild`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/builds/fetchtestbuild`, _data);
        }
        return this.http.post(`/builds/fetchtestbuild`, _data);
    }
    /**
     * FetchTestRounds
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BuildService
     */
    async FetchTestRounds(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/builds/fetchtestrounds`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/builds/fetchtestrounds`, _data);
        }
        return this.http.post(`/builds/fetchtestrounds`, _data);
    }
    /**
     * FetchUpdateLog
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof BuildService
     */
    async FetchUpdateLog(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.project && true) {
            return this.http.post(`/projects/${_context.project}/builds/fetchupdatelog`, _data);
        }
        if (_context.product && true) {
            return this.http.post(`/products/${_context.product}/builds/fetchupdatelog`, _data);
        }
        return this.http.post(`/builds/fetchupdatelog`, _data);
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
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/builds/linkbugbatch`,_data);
        }
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/builds/linkbugbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/builds/linkbugbatch`,_data);
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
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/builds/linkstorybatch`,_data);
        }
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/builds/linkstorybatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/builds/linkstorybatch`,_data);
    }

    /**
     * OneClickReleaseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BuildServiceBase
     */
    public async OneClickReleaseBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/builds/oneclickreleasebatch`,_data);
        }
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/builds/oneclickreleasebatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/builds/oneclickreleasebatch`,_data);
    }

    /**
     * UnlinkBugBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BuildServiceBase
     */
    public async UnlinkBugBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/builds/unlinkbugbatch`,_data);
        }
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/builds/unlinkbugbatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/builds/unlinkbugbatch`,_data);
    }

    /**
     * UnlinkStoryBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof BuildServiceBase
     */
    public async UnlinkStoryBatch(_context: any = {},_data: any = {}): Promise<HttpResponse> {
        if(_context.project && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/projects/${_context.project}/builds/unlinkstorybatch`,_data);
        }
        if(_context.product && true){
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/builds/unlinkstorybatch`,_data);
        }
        _data = await this.obtainMinor(_context, _data);
        return this.http.post(`/builds/unlinkstorybatch`,_data);
    }
}
