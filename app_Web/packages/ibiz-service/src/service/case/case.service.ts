import { Http, HttpResponse, IContext, IHttpResponse } from 'ibiz-core';
import { GlobalService } from '../global.service';
import { CaseBaseService } from './case-base.service';

/**
 * 实体服务对象基类
 *
 * @export
 * @class CaseService
 * @extends {CaseBaseService}
 */
export class CaseService extends CaseBaseService {
    /**
     * Creates an instance of CaseService.
     * @memberof CaseService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('CaseService')) {
            return ___ibz___.sc.get('CaseService');
        }
        ___ibz___.sc.set('CaseService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {CaseService}
     * @memberof CaseService
     */
    static getInstance(): CaseService {
        if (!___ibz___.sc.has('CaseService')) {
            new CaseService();
        }
        return ___ibz___.sc.get('CaseService');
    }

    /**
     * TestsuitelinkCase
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<IHttpResponse>}
     * @memberof CaseService
     */
    async TestsuitelinkCase(_context: any = {}, _data: any = {}): Promise<IHttpResponse> {
        if (_context.product && _context.story && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/testsuitelinkcase`, _data);
        }
        if (_context.story && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            return this.http.post(`/stories/${_context.story}/cases/${_context.case}/testsuitelinkcase`, _data);
        }
        if (_context.product && _context.case) {
        _data = await this.obtainMinor(_context, _data);
            _context.case = 0;
            _data.id = 0;
            return this.http.post(`/products/${_context.product}/cases/${_context.case}/testsuitelinkcase`, _data);
        }
        return this.http.post(`/cases/${_context.case}/testsuitelinkcase`, _data);
    }

    /**
     * Get接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    async Get(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case) {
            const res = await this.http.get(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}`);
        if (res.ok && res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
            return res;
        }
        if (_context.story && _context.case) {
            const res = await this.http.get(`/stories/${_context.story}/cases/${_context.case}`);
        if (res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
            return res;
        }
        if (_context.product && _context.case) {
            const res = await this.http.get(`/products/${_context.product}/cases/${_context.case}`);
        if (res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
            return res;
        }
        const res = await this.http.get(`/cases/${_context.case}`);
        if (res.status === 200) {
            await this.fillMinor(_context, res.data);
        }
        return res;
    }

    /**
     * GetDraft
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async GetDraft(context: any = {}, data: any = {}, isloading?: boolean): Promise<IHttpResponse> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.case) delete tempData.case;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/cases/getdraft`,tempData,isloading);
            res.data.case = data.case;
            if (res.data && res.data.casesteps && res.data.casesteps.length > 0) {
                for (const item of res.data.casesteps) {
                    await this.addLocal(context, item);
                }
            }
            if (res.data && res.data.ibzcasesteps && res.data.ibzcasesteps.length > 0) {
                for (const item of res.data.ibzcasesteps) {
                    await this.addLocal(context, item);
                }
            }
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.case) delete tempData.case;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/stories/${context.story}/cases/getdraft`,tempData,isloading);
            res.data.case = data.case;
            if (res.data && res.data.casesteps && res.data.casesteps.length > 0) {
                for (const item of res.data.casesteps) {
                    await this.addLocal(context, item);
                }
            }
            if (res.data && res.data.ibzcasesteps && res.data.ibzcasesteps.length > 0) {
                for (const item of res.data.ibzcasesteps) {
                    await this.addLocal(context, item);
                }
            }
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.case) delete tempData.case;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/products/${context.product}/cases/getdraft`,tempData,isloading);
            res.data.case = data.case;
            if (res.data && res.data.casesteps && res.data.casesteps.length > 0) {
                for (const item of res.data.casesteps) {
                    await this.addLocal(context, item);
                }
            }
            if (res.data && res.data.ibzcasesteps && res.data.ibzcasesteps.length > 0) {
                for (const item of res.data.ibzcasesteps) {
                    await this.addLocal(context, item);
                }
            }
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        if(tempData.case) delete tempData.case;
        if(tempData.id) delete tempData.id;
        let res:any = await  Http.getInstance().get(`/cases/getdraft`,tempData,isloading);
        res.data.case = data.case;
        if (res.data && res.data.casesteps && res.data.casesteps.length > 0) {
            for (const item of res.data.casesteps) {
                await this.addLocal(context, item);
            }
        }
        if (res.data && res.data.ibzcasesteps && res.data.ibzcasesteps.length > 0) {
            for (const item of res.data.ibzcasesteps) {
                await this.addLocal(context, item);
            }
        }
        return res;
    }

    /**
     * Create
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async Create(_context: any = {}, _data: any = {}): Promise<any> {
        if (_context.product && _context.story && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            //删除从实体【ibzcasestep】数据主键
            let ibzcasestepsDatas: any[] = _data.ibzcasesteps;
            if (ibzcasestepsDatas && ibzcasestepsDatas.length > 0) {
                ibzcasestepsDatas.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
                _data.ibzcasesteps = ibzcasestepsDatas;
            }
            //删除从实体【casestep】数据主键
            let casestepsDatas: any[] = _data.casesteps;
            if (casestepsDatas && casestepsDatas.length > 0) {
                casestepsDatas.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
                _data.casesteps = casestepsDatas;
            }
            return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases`, _data);
        }
        if (_context.story && true) {
        _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            //删除从实体【ibzcasestep】数据主键
            let ibzcasestepsDatas: any[] = _data.ibzcasesteps;
            if (ibzcasestepsDatas && ibzcasestepsDatas.length > 0) {
                ibzcasestepsDatas.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
                _data.ibzcasesteps = ibzcasestepsDatas;
            }
            //删除从实体【casestep】数据主键
            let casestepsDatas: any[] = _data.casesteps;
            if (casestepsDatas && casestepsDatas.length > 0) {
                casestepsDatas.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
                _data.casesteps = casestepsDatas;
            }
            return this.http.post(`/stories/${_context.story}/cases`, _data);
        }
        if (_context.product && true) {
            // 从实体服务
            const minorIBZCaseStepService: any = await ___ibz___.gs[`getIBZCaseStepService`]();
            _data = await this.obtainMinor(_context, _data);
            if (!_data.srffrontuf || _data.srffrontuf != 1) {
                _data[this.APPDEKEY] = null;
            }
            if (_data.srffrontuf != null) {
                delete _data.srffrontuf;
            }
            //删除从实体【ibzcasestep】数据主键
            let ibzcasestepsDatas: any[] = _data.ibzcasesteps;
            if (ibzcasestepsDatas && ibzcasestepsDatas.length > 0) {
                for (const item of ibzcasestepsDatas) {
                    if (item.id) {
                        if (minorIBZCaseStepService) {
                            await minorIBZCaseStepService.removeLocal(_context, item.id);
                        }
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                }
                _data.ibzcasesteps = ibzcasestepsDatas;
            }
            //删除从实体【casestep】数据主键
            let casestepsDatas: any[] = _data.casesteps;
            if (casestepsDatas && casestepsDatas.length > 0) {
                casestepsDatas.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
                _data.casesteps = casestepsDatas;
            }
            return this.http.post(`/products/${_context.product}/cases`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        if (!_data.srffrontuf || _data.srffrontuf != 1) {
            _data[this.APPDEKEY] = null;
        }
        if (_data.srffrontuf != null) {
            delete _data.srffrontuf;
        }
        //删除从实体【ibzcasestep】数据主键
        let ibzcasestepsDatas: any[] = _data.ibzcasesteps;
        if (ibzcasestepsDatas && ibzcasestepsDatas.length > 0) {
            ibzcasestepsDatas.forEach((item: any) => {
                if (item.id) {
                    item.id = null;
                    if(item.hasOwnProperty('id') && item.id) delete item.id;
                }
            })
            _data.ibzcasesteps = ibzcasestepsDatas;
        }
        //删除从实体【casestep】数据主键
        let casestepsDatas: any[] = _data.casesteps;
        if (casestepsDatas && casestepsDatas.length > 0) {
            casestepsDatas.forEach((item: any) => {
                if (item.id) {
                    item.id = null;
                    if(item.hasOwnProperty('id') && item.id) delete item.id;
                }
            })
            _data.casesteps = casestepsDatas;
        }
        return this.http.post(`/cases`, _data);
    }

        /**
     * Update
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async Update(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
        if (_context.product && _context.story && _context.case) {
            _data = await this.obtainMinor(_context, _data);
            //删除从实体【ibzcasestep】数据主键
            let ibzcasestepsDatas: any[] = _data.ibzcasesteps;
            if (ibzcasestepsDatas && ibzcasestepsDatas.length > 0) {
                ibzcasestepsDatas.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
                _data.ibzcasesteps = ibzcasestepsDatas;
            }
            //删除从实体【casestep】数据主键
            let casestepsDatas: any[] = _data.casesteps;
            if (casestepsDatas && casestepsDatas.length > 0) {
                casestepsDatas.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
                _data.casesteps = casestepsDatas;
            }
            return this.http.put(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}`, _data);
        }
        if (_context.story && _context.case) {
            _data = await this.obtainMinor(_context, _data);
            //删除从实体【ibzcasestep】数据主键
            let ibzcasestepsDatas: any[] = _data.ibzcasesteps;
            if (ibzcasestepsDatas && ibzcasestepsDatas.length > 0) {
                ibzcasestepsDatas.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
                _data.ibzcasesteps = ibzcasestepsDatas;
            }
            //删除从实体【casestep】数据主键
            let casestepsDatas: any[] = _data.casesteps;
            if (casestepsDatas && casestepsDatas.length > 0) {
                casestepsDatas.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
                _data.casesteps = casestepsDatas;
            }
            return this.http.put(`/stories/${_context.story}/cases/${_context.case}`, _data);
        }
        if (_context.product && _context.case) {
            _data = await this.obtainMinor(_context, _data);
            //删除从实体【ibzcasestep】数据主键
            let ibzcasestepsDatas: any[] = _data.ibzcasesteps;
            if (ibzcasestepsDatas && ibzcasestepsDatas.length > 0) {
                ibzcasestepsDatas.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
                _data.ibzcasesteps = ibzcasestepsDatas;
            }
            //删除从实体【casestep】数据主键
            let casestepsDatas: any[] = _data.casesteps;
            if (casestepsDatas && casestepsDatas.length > 0) {
                casestepsDatas.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
                _data.casesteps = casestepsDatas;
            }
            return this.http.put(`/products/${_context.product}/cases/${_context.case}`, _data);
        }
        _data = await this.obtainMinor(_context, _data);
        //删除从实体【ibzcasestep】数据主键
        let ibzcasestepsDatas: any[] = _data.ibzcasesteps;
        if (ibzcasestepsDatas && ibzcasestepsDatas.length > 0) {
            ibzcasestepsDatas.forEach((item: any) => {
                if (item.id) {
                    item.id = null;
                    if(item.hasOwnProperty('id') && item.id) delete item.id;
                }
            })
            _data.ibzcasesteps = ibzcasestepsDatas;
        }
        //删除从实体【casestep】数据主键
        let casestepsDatas: any[] = _data.casesteps;
        if (casestepsDatas && casestepsDatas.length > 0) {
            casestepsDatas.forEach((item: any) => {
                if (item.id) {
                    item.id = null;
                    if(item.hasOwnProperty('id') && item.id) delete item.id;
                }
            })
            _data.casesteps = casestepsDatas;
        }
        return this.http.put(`/cases/${_context.case}`, _data);
    }

    /**
     * RunCase
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
    async RunCase(context: any = {}, data: any = {}, isloading?: boolean): Promise<HttpResponse> {
        if(context.product && context.story && context.case){
            //删除从实体【ibzcasestep】数据主键
            let ibzcasestepsDatas: any[] = data.ibzcasesteps;
            if (ibzcasestepsDatas && ibzcasestepsDatas.length > 0) {
                ibzcasestepsDatas.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
                data.ibzcasesteps = ibzcasestepsDatas;
            }
            //删除从实体【casestep】数据主键
            let casestepsDatas: any[] = data.casesteps;
            if (casestepsDatas && casestepsDatas.length > 0) {
                casestepsDatas.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
                data.casesteps = casestepsDatas;
            }
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/${context.case}/runcases`,data,isloading);
            this.fillMinor(context, data.ibzcasesteps);
            this.fillMinor(context, data.casesteps);
            return res;
        }
        if(context.story && context.case){
            //删除从实体【ibzcasestep】数据主键
            let ibzcasestepsDatas: any[] = data.ibzcasesteps;
            if (ibzcasestepsDatas && ibzcasestepsDatas.length > 0) {
                ibzcasestepsDatas.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
                data.ibzcasesteps = ibzcasestepsDatas;
            }
            //删除从实体【casestep】数据主键
            let casestepsDatas: any[] = data.casesteps;
            if (casestepsDatas && casestepsDatas.length > 0) {
                casestepsDatas.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
                data.casesteps = casestepsDatas;
            }
            let res:any = await Http.getInstance().post(`/stories/${context.story}/cases/${context.case}/runcases`,data,isloading);
            this.fillMinor(context, data.ibzcasesteps);
            this.fillMinor(context, data.casesteps);
            return res;
        }
        if(context.product && context.case){
            //删除从实体【ibzcasestep】数据主键
            let ibzcasestepsDatas: any[] = data.ibzcasesteps;
            if (ibzcasestepsDatas && ibzcasestepsDatas.length > 0) {
                ibzcasestepsDatas.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
                data.ibzcasesteps = ibzcasestepsDatas;
            }
            //删除从实体【casestep】数据主键
            let casestepsDatas: any[] = data.casesteps;
            if (casestepsDatas && casestepsDatas.length > 0) {
                casestepsDatas.forEach((item: any) => {
                    if (item.id) {
                        item.id = null;
                        if(item.hasOwnProperty('id') && item.id) delete item.id;
                    }
                })
                data.casesteps = casestepsDatas;
            }
            let res:any = await Http.getInstance().post(`/products/${context.product}/cases/${context.case}/runcases`,data,isloading);
            this.fillMinor(context, data.ibzcasesteps);
            this.fillMinor(context, data.casesteps);
            return res;
        }
        let res:any = await Http.getInstance().post(`/cases/${context.case}/runcases`,data,isloading);
        return res;
    }

    /**
     * LinkCase
     *
     * @param {*} [_context={}]
     * @param {*} [_data = {}]
     * @returns {Promise<HttpResponse>}
     * @memberof CaseService
     */
     async LinkCase(_context: any = {}, _data: any = {}): Promise<HttpResponse> {
      if (_context.product && _context.story && _context.case) {
          _data = await this.obtainMinor(_context, _data);
          _context.case = 0;
          _data.id = 0;
          return this.http.post(`/products/${_context.product}/stories/${_context.story}/cases/${_context.case}/linkcase`, _data);
      }
      if (_context.story && _context.case) {
          _data = await this.obtainMinor(_context, _data);
          _context.case = 0;
          _data.id = 0;
          return this.http.post(`/stories/${_context.story}/cases/${_context.case}/linkcase`, _data);
      }
      if (_context.product && _context.case) {
          _data = await this.obtainMinor(_context, _data);
          _context.case = 0;
          _data.id = 0;
          return this.http.post(`/products/${_context.product}/cases/${_context.case}/linkcase`, _data);
      }
      _context.case = 0;
      _data.id = 0;
      return this.http.post(`/cases/${_context.case}/linkcase`, _data);
  }
}
export default CaseService;
