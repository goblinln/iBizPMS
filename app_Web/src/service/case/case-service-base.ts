import { Environment } from '@/environments/environment';
import { Http } from '@/utils';
import { Util } from '@/utils';
import EntityService from '../entity-service';
import GetCaseStepByIdVersionLogic from '@/service/case/get-case-step-by-id-version-logic';



/**
 * 测试用例服务对象基类
 *
 * @export
 * @class CaseServiceBase
 * @extends {EntityServie}
 */
export default class CaseServiceBase extends EntityService {

    /**
     * Creates an instance of  CaseServiceBase.
     * 
     * @param {*} [opts={}]
     * @memberof  CaseServiceBase
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 初始化基础数据
     *
     * @memberof CaseServiceBase
     */
    public initBasicData(){
        this.APPLYDEKEY ='case';
        this.APPDEKEY = 'id';
        this.APPDENAME = 'cases';
        this.APPDETEXT = 'title';
        this.APPNAME = 'web';
        this.SYSTEMNAME = 'pms';
    }

// 实体接口

    /**
     * BugCreateCase接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async BugCreateCase(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * CaseFavorite接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async CaseFavorite(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/${context.case}/casefavorite`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.story && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/cases/${context.case}/casefavorite`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.product && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/cases/${context.case}/casefavorite`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
            let res:any = await Http.getInstance().post(`/cases/${context.case}/casefavorite`,data,isloading);
            return res;
    }

    /**
     * CaseNFavorite接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async CaseNFavorite(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/${context.case}/casenfavorite`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.story && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/cases/${context.case}/casenfavorite`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.product && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/cases/${context.case}/casenfavorite`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
            let res:any = await Http.getInstance().post(`/cases/${context.case}/casenfavorite`,data,isloading);
            return res;
    }

    /**
     * CheckKey接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async CheckKey(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/${context.case}/checkkey`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.story && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/cases/${context.case}/checkkey`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.product && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/cases/${context.case}/checkkey`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
            let res:any = await Http.getInstance().post(`/cases/${context.case}/checkkey`,data,isloading);
            return res;
    }

    /**
     * ConfirmChange接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async ConfirmChange(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/stories/${context.story}/cases/${context.case}/confirmchange`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.story && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/stories/${context.story}/cases/${context.case}/confirmchange`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.product && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/cases/${context.case}/confirmchange`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
            let res:any = await Http.getInstance().put(`/cases/${context.case}/confirmchange`,data,isloading);
            return res;
    }

    /**
     * ConfirmChangeBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async ConfirmChangeBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/confirmchangebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/confirmchangebatch`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/confirmchangebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/cases/confirmchangebatch`,tempData,isloading);
    }

    /**
     * Confirmstorychange接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async Confirmstorychange(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/${context.case}/confirmstorychange`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.story && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/cases/${context.case}/confirmstorychange`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.product && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/cases/${context.case}/confirmstorychange`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
            let res:any = await Http.getInstance().post(`/cases/${context.case}/confirmstorychange`,data,isloading);
            return res;
    }

    /**
     * ConfirmstorychangeBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async ConfirmstorychangeBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/confirmstorychangebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/confirmstorychangebatch`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/confirmstorychangebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/cases/confirmstorychangebatch`,tempData,isloading);
    }

    /**
     * Create接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async Create(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let tempContext:any = JSON.parse(JSON.stringify(context));
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases`,data,isloading);
            this.tempStorage.setItem(tempContext.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(tempContext.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));
            this.tempStorage.setItem(tempContext.srfsessionkey+'_testresults',JSON.stringify(res.data.testresults?res.data.testresults:[]));
            
            return res;
        }
        if(context.story && true){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let tempContext:any = JSON.parse(JSON.stringify(context));
            let res:any = await Http.getInstance().post(`/stories/${context.story}/cases`,data,isloading);
            this.tempStorage.setItem(tempContext.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(tempContext.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));
            this.tempStorage.setItem(tempContext.srfsessionkey+'_testresults',JSON.stringify(res.data.testresults?res.data.testresults:[]));
            
            return res;
        }
        if(context.product && true){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            if(!data.srffrontuf || data.srffrontuf !== "1"){
                data[this.APPDEKEY] = null;
            }
            if(data.srffrontuf){
                delete data.srffrontuf;
            }
            let tempContext:any = JSON.parse(JSON.stringify(context));
            let res:any = await Http.getInstance().post(`/products/${context.product}/cases`,data,isloading);
            this.tempStorage.setItem(tempContext.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(tempContext.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));
            this.tempStorage.setItem(tempContext.srfsessionkey+'_testresults',JSON.stringify(res.data.testresults?res.data.testresults:[]));
            
            return res;
        }
        let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
        Object.assign(data,masterData);
        if(!data.srffrontuf || data.srffrontuf !== "1"){
            data[this.APPDEKEY] = null;
        }
        if(data.srffrontuf){
            delete data.srffrontuf;
        }
        let tempContext:any = JSON.parse(JSON.stringify(context));
        let res:any = await Http.getInstance().post(`/cases`,data,isloading);
        this.tempStorage.setItem(tempContext.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
        this.tempStorage.setItem(tempContext.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));
        this.tempStorage.setItem(tempContext.srfsessionkey+'_testresults',JSON.stringify(res.data.testresults?res.data.testresults:[]));
        
        return res;
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
    public async Get(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case){
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/cases/${context.case}`,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.story && context.case){
            let res:any = await Http.getInstance().get(`/stories/${context.story}/cases/${context.case}`,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.product && context.case){
            let res:any = await Http.getInstance().get(`/products/${context.product}/cases/${context.case}`,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
            let res:any = await Http.getInstance().get(`/cases/${context.case}`,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
    }

    /**
     * GetByTestTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async GetByTestTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case){
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/cases/${context.case}/getbytesttask`,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.story && context.case){
            let res:any = await Http.getInstance().get(`/stories/${context.story}/cases/${context.case}/getbytesttask`,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.product && context.case){
            let res:any = await Http.getInstance().get(`/products/${context.product}/cases/${context.case}/getbytesttask`,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
            let res:any = await Http.getInstance().get(`/cases/${context.case}/getbytesttask`,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
    }

    /**
     * GetByTestTaskBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async GetByTestTaskBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/getbytesttaskbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/getbytesttaskbatch`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/getbytesttaskbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/cases/getbytesttaskbatch`,tempData,isloading);
    }

    /**
     * GetDraft接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async GetDraft(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.case) delete tempData.case;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/cases/getdraft`,tempData,isloading);
            res.data.case = data.case;
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.case) delete tempData.case;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/stories/${context.story}/cases/getdraft`,tempData,isloading);
            res.data.case = data.case;
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            if(tempData.case) delete tempData.case;
            if(tempData.id) delete tempData.id;
            let res:any = await Http.getInstance().get(`/products/${context.product}/cases/getdraft`,tempData,isloading);
            res.data.case = data.case;
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        if(tempData.case) delete tempData.case;
        if(tempData.id) delete tempData.id;
        let res:any = await  Http.getInstance().get(`/cases/getdraft`,tempData,isloading);
        res.data.case = data.case;
                    this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

        return res;
    }

    /**
     * GetTestTaskCntRun接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async GetTestTaskCntRun(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/stories/${context.story}/cases/${context.case}/gettesttaskcntrun`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.story && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/stories/${context.story}/cases/${context.case}/gettesttaskcntrun`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.product && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/cases/${context.case}/gettesttaskcntrun`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
            let res:any = await Http.getInstance().put(`/cases/${context.case}/gettesttaskcntrun`,data,isloading);
            return res;
    }

    /**
     * GetTestTaskCntRunBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async GetTestTaskCntRunBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/gettesttaskcntrunbatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/gettesttaskcntrunbatch`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/gettesttaskcntrunbatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/cases/gettesttaskcntrunbatch`,tempData,isloading);
    }

    /**
     * LinkCase接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async LinkCase(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/${context.case}/linkcase`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.story && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/cases/${context.case}/linkcase`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.product && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/cases/${context.case}/linkcase`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
            let res:any = await Http.getInstance().post(`/cases/${context.case}/linkcase`,data,isloading);
            return res;
    }

    /**
     * LinkCaseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async LinkCaseBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/linkcasebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/linkcasebatch`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/linkcasebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/cases/linkcasebatch`,tempData,isloading);
    }

    /**
     * MobLinkCase接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async MobLinkCase(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/${context.case}/moblinkcase`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.story && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/cases/${context.case}/moblinkcase`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.product && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/cases/${context.case}/moblinkcase`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
            let res:any = await Http.getInstance().post(`/cases/${context.case}/moblinkcase`,data,isloading);
            return res;
    }

    /**
     * MobLinkCaseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async MobLinkCaseBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/moblinkcasebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/moblinkcasebatch`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/moblinkcasebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/cases/moblinkcasebatch`,tempData,isloading);
    }

    /**
     * Remove接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async Remove(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case){
            let res:any = await Http.getInstance().delete(`/products/${context.product}/stories/${context.story}/cases/${context.case}`,isloading);
            return res;
        }
        if(context.story && context.case){
            let res:any = await Http.getInstance().delete(`/stories/${context.story}/cases/${context.case}`,isloading);
            return res;
        }
        if(context.product && context.case){
            let res:any = await Http.getInstance().delete(`/products/${context.product}/cases/${context.case}`,isloading);
            return res;
        }
            let res:any = await Http.getInstance().delete(`/cases/${context.case}`,isloading);
            return res;
    }

    /**
     * RunCase接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async RunCase(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/${context.case}/runcase`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.story && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/cases/${context.case}/runcase`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.product && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/cases/${context.case}/runcase`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
            let res:any = await Http.getInstance().post(`/cases/${context.case}/runcase`,data,isloading);
            return res;
    }

    /**
     * RunCaseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async RunCaseBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/runcasebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/runcasebatch`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/runcasebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/cases/runcasebatch`,tempData,isloading);
    }

    /**
     * RunCases接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async RunCases(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/${context.case}/runcases`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.story && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/cases/${context.case}/runcases`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.product && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/cases/${context.case}/runcases`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
            let res:any = await Http.getInstance().post(`/cases/${context.case}/runcases`,data,isloading);
            return res;
    }

    /**
     * Save接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async Save(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/${context.case}/save`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.story && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/cases/${context.case}/save`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.product && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/cases/${context.case}/save`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().post(`/cases/${context.case}/save`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
    }

    /**
     * TestRunCase接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async TestRunCase(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/${context.case}/testruncase`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.story && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/cases/${context.case}/testruncase`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.product && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/cases/${context.case}/testruncase`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
            let res:any = await Http.getInstance().post(`/cases/${context.case}/testruncase`,data,isloading);
            return res;
    }

    /**
     * TestRunCaseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async TestRunCaseBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/testruncasebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/testruncasebatch`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/testruncasebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/cases/testruncasebatch`,tempData,isloading);
    }

    /**
     * TestRunCases接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async TestRunCases(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/${context.case}/testruncases`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.story && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/cases/${context.case}/testruncases`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.product && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/cases/${context.case}/testruncases`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
            let res:any = await Http.getInstance().post(`/cases/${context.case}/testruncases`,data,isloading);
            return res;
    }

    /**
     * TestsuitelinkCase接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async TestsuitelinkCase(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/${context.case}/testsuitelinkcase`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.story && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/cases/${context.case}/testsuitelinkcase`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.product && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/cases/${context.case}/testsuitelinkcase`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
            let res:any = await Http.getInstance().post(`/cases/${context.case}/testsuitelinkcase`,data,isloading);
            return res;
    }

    /**
     * TestsuitelinkCaseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async TestsuitelinkCaseBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/testsuitelinkcasebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/testsuitelinkcasebatch`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/testsuitelinkcasebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/cases/testsuitelinkcasebatch`,tempData,isloading);
    }

    /**
     * UnlinkCase接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async UnlinkCase(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/${context.case}/unlinkcase`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.story && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/cases/${context.case}/unlinkcase`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.product && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/cases/${context.case}/unlinkcase`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
            let res:any = await Http.getInstance().post(`/cases/${context.case}/unlinkcase`,data,isloading);
            return res;
    }

    /**
     * UnlinkCaseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async UnlinkCaseBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/unlinkcasebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/unlinkcasebatch`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/unlinkcasebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/cases/unlinkcasebatch`,tempData,isloading);
    }

    /**
     * UnlinkCases接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async UnlinkCases(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/${context.case}/unlinkcases`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.story && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/cases/${context.case}/unlinkcases`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.product && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/cases/${context.case}/unlinkcases`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
            let res:any = await Http.getInstance().post(`/cases/${context.case}/unlinkcases`,data,isloading);
            return res;
    }

    /**
     * UnlinkSuiteCase接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async UnlinkSuiteCase(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/${context.case}/unlinksuitecase`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.story && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/cases/${context.case}/unlinksuitecase`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.product && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/cases/${context.case}/unlinksuitecase`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
            let res:any = await Http.getInstance().post(`/cases/${context.case}/unlinksuitecase`,data,isloading);
            return res;
    }

    /**
     * UnlinkSuiteCaseBatch接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async UnlinkSuiteCaseBatch(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/unlinksuitecasebatch`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/unlinksuitecasebatch`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/unlinksuitecasebatch`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/cases/unlinksuitecasebatch`,tempData,isloading);
    }

    /**
     * UnlinkSuiteCases接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async UnlinkSuiteCases(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/${context.case}/unlinksuitecases`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.story && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/stories/${context.story}/cases/${context.case}/unlinksuitecases`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.product && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().post(`/products/${context.product}/cases/${context.case}/unlinksuitecases`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
            let res:any = await Http.getInstance().post(`/cases/${context.case}/unlinksuitecases`,data,isloading);
            return res;
    }

    /**
     * Update接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async Update(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/stories/${context.story}/cases/${context.case}`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.story && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/stories/${context.story}/cases/${context.case}`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        if(context.product && context.case){
            let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
            Object.assign(data,masterData);
            let res:any = await Http.getInstance().put(`/products/${context.product}/cases/${context.case}`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
        }
        let masterData:any = {};
        let casestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_casesteps'),'undefined')){
            casestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_casesteps') as any);
            if(casestepsData && casestepsData.length && casestepsData.length > 0 && Environment.isStudioSystem === false){
                casestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.casesteps = casestepsData;
        let ibzcasestepsData:any = [];
        if(!Object.is(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps'),'undefined')){
            ibzcasestepsData = JSON.parse(this.tempStorage.getItem(context.srfsessionkey+'_ibzcasesteps') as any);
            if(ibzcasestepsData && ibzcasestepsData.length && ibzcasestepsData.length > 0 && Environment.isStudioSystem === false){
                ibzcasestepsData.forEach((item:any) => {
                    if(item.srffrontuf){
                        if(Object.is(item.srffrontuf,"0")){
                            item.id = null;
                            if(item.hasOwnProperty('id') && item.id) delete item.id;
                        }
                        delete item.srffrontuf;
                    }
                });
            }
        }
        masterData.ibzcasesteps = ibzcasestepsData;
        Object.assign(data,masterData);
            let res:any = await  Http.getInstance().put(`/cases/${context.case}`,data,isloading);
                        this.tempStorage.setItem(context.srfsessionkey+'_casesteps',JSON.stringify(res.data.casesteps?res.data.casesteps:[]));
            this.tempStorage.setItem(context.srfsessionkey+'_ibzcasesteps',JSON.stringify(res.data.ibzcasesteps?res.data.ibzcasesteps:[]));

            return res;
    }

    /**
     * FetchBatchNew接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchBatchNew(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/cases/fetchbatchnew`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/cases/fetchbatchnew`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/cases/fetchbatchnew`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/cases/fetchbatchnew`,tempData,isloading);
        return res;
    }

    /**
     * searchBatchNew接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async searchBatchNew(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/searchbatchnew`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/searchbatchnew`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/searchbatchnew`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/cases/searchbatchnew`,tempData,isloading);
    }

    /**
     * FetchCurOpenedCase接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchCurOpenedCase(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/cases/fetchcuropenedcase`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/cases/fetchcuropenedcase`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/cases/fetchcuropenedcase`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/cases/fetchcuropenedcase`,tempData,isloading);
        return res;
    }

    /**
     * searchCurOpenedCase接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async searchCurOpenedCase(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/searchcuropenedcase`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/searchcuropenedcase`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/searchcuropenedcase`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/cases/searchcuropenedcase`,tempData,isloading);
    }

    /**
     * FetchCurSuite接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchCurSuite(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/fetchcursuite`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/stories/${context.story}/cases/fetchcursuite`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/cases/fetchcursuite`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/cases/fetchcursuite`,tempData,isloading);
        return res;
    }

    /**
     * searchCurSuite接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async searchCurSuite(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/searchcursuite`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/searchcursuite`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/searchcursuite`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/cases/searchcursuite`,tempData,isloading);
    }

    /**
     * FetchCurTestTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchCurTestTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/fetchcurtesttask`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/stories/${context.story}/cases/fetchcurtesttask`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/cases/fetchcurtesttask`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/cases/fetchcurtesttask`,tempData,isloading);
        return res;
    }

    /**
     * searchCurTestTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async searchCurTestTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/searchcurtesttask`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/searchcurtesttask`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/searchcurtesttask`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/cases/searchcurtesttask`,tempData,isloading);
    }

    /**
     * FetchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/stories/${context.story}/cases/fetchdefault`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/cases/fetchdefault`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/cases/fetchdefault`,tempData,isloading);
        return res;
    }

    /**
     * searchDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async searchDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/searchdefault`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/searchdefault`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/searchdefault`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/cases/searchdefault`,tempData,isloading);
    }

    /**
     * FetchESBulk接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchESBulk(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/cases/fetchesbulk`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/cases/fetchesbulk`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/cases/fetchesbulk`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/cases/fetchesbulk`,tempData,isloading);
        return res;
    }

    /**
     * searchESBulk接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async searchESBulk(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/searchesbulk`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/searchesbulk`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/searchesbulk`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/cases/searchesbulk`,tempData,isloading);
    }

    /**
     * FetchModuleRePortCase接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchModuleRePortCase(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/cases/fetchmodulereportcase`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/cases/fetchmodulereportcase`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/cases/fetchmodulereportcase`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/cases/fetchmodulereportcase`,tempData,isloading);
        return res;
    }

    /**
     * searchModuleRePortCase接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async searchModuleRePortCase(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/searchmodulereportcase`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/searchmodulereportcase`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/searchmodulereportcase`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/cases/searchmodulereportcase`,tempData,isloading);
    }

    /**
     * FetchModuleRePortCaseEntry接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchModuleRePortCaseEntry(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/cases/fetchmodulereportcaseentry`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/cases/fetchmodulereportcaseentry`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/cases/fetchmodulereportcaseentry`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/cases/fetchmodulereportcaseentry`,tempData,isloading);
        return res;
    }

    /**
     * searchModuleRePortCaseEntry接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async searchModuleRePortCaseEntry(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/searchmodulereportcaseentry`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/searchmodulereportcaseentry`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/searchmodulereportcaseentry`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/cases/searchmodulereportcaseentry`,tempData,isloading);
    }

    /**
     * FetchModuleRePortCase_Project接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchModuleRePortCase_Project(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/cases/fetchmodulereportcase_project`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/cases/fetchmodulereportcase_project`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/cases/fetchmodulereportcase_project`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/cases/fetchmodulereportcase_project`,tempData,isloading);
        return res;
    }

    /**
     * searchModuleRePortCase_Project接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async searchModuleRePortCase_Project(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/searchmodulereportcase_project`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/searchmodulereportcase_project`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/searchmodulereportcase_project`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/cases/searchmodulereportcase_project`,tempData,isloading);
    }

    /**
     * FetchMyFavorites接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchMyFavorites(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/fetchmyfavorites`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/stories/${context.story}/cases/fetchmyfavorites`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/cases/fetchmyfavorites`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/cases/fetchmyfavorites`,tempData,isloading);
        return res;
    }

    /**
     * searchMyFavorites接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async searchMyFavorites(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/searchmyfavorites`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/searchmyfavorites`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/searchmyfavorites`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/cases/searchmyfavorites`,tempData,isloading);
    }

    /**
     * FetchNotCurTestSuite接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchNotCurTestSuite(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/fetchnotcurtestsuite`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/stories/${context.story}/cases/fetchnotcurtestsuite`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/cases/fetchnotcurtestsuite`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/cases/fetchnotcurtestsuite`,tempData,isloading);
        return res;
    }

    /**
     * searchNotCurTestSuite接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async searchNotCurTestSuite(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/searchnotcurtestsuite`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/searchnotcurtestsuite`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/searchnotcurtestsuite`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/cases/searchnotcurtestsuite`,tempData,isloading);
    }

    /**
     * FetchNotCurTestTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchNotCurTestTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/fetchnotcurtesttask`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/stories/${context.story}/cases/fetchnotcurtesttask`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/cases/fetchnotcurtesttask`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/cases/fetchnotcurtesttask`,tempData,isloading);
        return res;
    }

    /**
     * searchNotCurTestTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async searchNotCurTestTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/searchnotcurtesttask`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/searchnotcurtesttask`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/searchnotcurtesttask`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/cases/searchnotcurtesttask`,tempData,isloading);
    }

    /**
     * FetchNotCurTestTaskProject接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchNotCurTestTaskProject(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/cases/fetchnotcurtesttaskproject`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/cases/fetchnotcurtesttaskproject`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/cases/fetchnotcurtesttaskproject`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/cases/fetchnotcurtesttaskproject`,tempData,isloading);
        return res;
    }

    /**
     * searchNotCurTestTaskProject接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async searchNotCurTestTaskProject(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/searchnotcurtesttaskproject`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/searchnotcurtesttaskproject`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/searchnotcurtesttaskproject`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/cases/searchnotcurtesttaskproject`,tempData,isloading);
    }

    /**
     * FetchRePortCase接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchRePortCase(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/fetchreportcase`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/stories/${context.story}/cases/fetchreportcase`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/cases/fetchreportcase`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/cases/fetchreportcase`,tempData,isloading);
        return res;
    }

    /**
     * searchRePortCase接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async searchRePortCase(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/searchreportcase`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/searchreportcase`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/searchreportcase`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/cases/searchreportcase`,tempData,isloading);
    }

    /**
     * FetchRePortCaseEntry接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchRePortCaseEntry(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/cases/fetchreportcaseentry`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/cases/fetchreportcaseentry`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/cases/fetchreportcaseentry`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/cases/fetchreportcaseentry`,tempData,isloading);
        return res;
    }

    /**
     * searchRePortCaseEntry接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async searchRePortCaseEntry(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/searchreportcaseentry`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/searchreportcaseentry`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/searchreportcaseentry`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/cases/searchreportcaseentry`,tempData,isloading);
    }

    /**
     * FetchRePortCase_Project接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchRePortCase_Project(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/fetchreportcase_project`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/stories/${context.story}/cases/fetchreportcase_project`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().post(`/products/${context.product}/cases/fetchreportcase_project`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().post(`/cases/fetchreportcase_project`,tempData,isloading);
        return res;
    }

    /**
     * searchRePortCase_Project接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async searchRePortCase_Project(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/searchreportcase_project`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/searchreportcase_project`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/searchreportcase_project`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/cases/searchreportcase_project`,tempData,isloading);
    }

    /**
     * FetchRunERRePortCase接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchRunERRePortCase(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/cases/fetchrunerreportcase`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/cases/fetchrunerreportcase`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/cases/fetchrunerreportcase`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/cases/fetchrunerreportcase`,tempData,isloading);
        return res;
    }

    /**
     * searchRunERRePortCase接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async searchRunERRePortCase(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/searchrunerreportcase`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/searchrunerreportcase`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/searchrunerreportcase`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/cases/searchrunerreportcase`,tempData,isloading);
    }

    /**
     * FetchRunERRePortCaseEntry接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchRunERRePortCaseEntry(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/cases/fetchrunerreportcaseentry`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/cases/fetchrunerreportcaseentry`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/cases/fetchrunerreportcaseentry`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/cases/fetchrunerreportcaseentry`,tempData,isloading);
        return res;
    }

    /**
     * searchRunERRePortCaseEntry接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async searchRunERRePortCaseEntry(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/searchrunerreportcaseentry`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/searchrunerreportcaseentry`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/searchrunerreportcaseentry`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/cases/searchrunerreportcaseentry`,tempData,isloading);
    }

    /**
     * FetchRunERRePortCase_Project接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchRunERRePortCase_Project(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/cases/fetchrunerreportcase_project`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/cases/fetchrunerreportcase_project`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/cases/fetchrunerreportcase_project`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/cases/fetchrunerreportcase_project`,tempData,isloading);
        return res;
    }

    /**
     * searchRunERRePortCase_Project接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async searchRunERRePortCase_Project(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/searchrunerreportcase_project`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/searchrunerreportcase_project`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/searchrunerreportcase_project`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/cases/searchrunerreportcase_project`,tempData,isloading);
    }

    /**
     * FetchRunRePortCase接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchRunRePortCase(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/cases/fetchrunreportcase`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/cases/fetchrunreportcase`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/cases/fetchrunreportcase`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/cases/fetchrunreportcase`,tempData,isloading);
        return res;
    }

    /**
     * searchRunRePortCase接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async searchRunRePortCase(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/searchrunreportcase`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/searchrunreportcase`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/searchrunreportcase`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/cases/searchrunreportcase`,tempData,isloading);
    }

    /**
     * FetchRunRePortCaseEntry接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchRunRePortCaseEntry(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/cases/fetchrunreportcaseentry`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/cases/fetchrunreportcaseentry`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/cases/fetchrunreportcaseentry`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/cases/fetchrunreportcaseentry`,tempData,isloading);
        return res;
    }

    /**
     * searchRunRePortCaseEntry接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async searchRunRePortCaseEntry(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/searchrunreportcaseentry`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/searchrunreportcaseentry`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/searchrunreportcaseentry`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/cases/searchrunreportcaseentry`,tempData,isloading);
    }

    /**
     * FetchRunRePortCase_Project接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchRunRePortCase_Project(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/cases/fetchrunreportcase_project`,tempData,isloading);
            return res;
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/stories/${context.story}/cases/fetchrunreportcase_project`,tempData,isloading);
            return res;
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            let res:any = await Http.getInstance().get(`/products/${context.product}/cases/fetchrunreportcase_project`,tempData,isloading);
            return res;
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        let res:any = await Http.getInstance().get(`/cases/fetchrunreportcase_project`,tempData,isloading);
        return res;
    }

    /**
     * searchRunRePortCase_Project接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async searchRunRePortCase_Project(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/stories/${context.story}/cases/searchrunreportcase_project`,tempData,isloading);
        }
        if(context.story && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/stories/${context.story}/cases/searchrunreportcase_project`,tempData,isloading);
        }
        if(context.product && true){
            let tempData:any = JSON.parse(JSON.stringify(data));
            return await Http.getInstance().post(`/products/${context.product}/cases/searchrunreportcase_project`,tempData,isloading);
        }
        let tempData:any = JSON.parse(JSON.stringify(data));
        return await Http.getInstance().post(`/cases/searchrunreportcase_project`,tempData,isloading);
    }

    /**
     * FetchTempBatchNew接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchTempBatchNew(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempCurOpenedCase接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchTempCurOpenedCase(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempCurSuite接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchTempCurSuite(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempCurTestTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchTempCurTestTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempDefault接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchTempDefault(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempESBulk接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchTempESBulk(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempModuleRePortCase接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchTempModuleRePortCase(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempModuleRePortCaseEntry接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchTempModuleRePortCaseEntry(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempModuleRePortCase_Project接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchTempModuleRePortCase_Project(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempMyFavorites接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchTempMyFavorites(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempNotCurTestSuite接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchTempNotCurTestSuite(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempNotCurTestTask接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchTempNotCurTestTask(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempNotCurTestTaskProject接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchTempNotCurTestTaskProject(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempRePortCase接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchTempRePortCase(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempRePortCaseEntry接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchTempRePortCaseEntry(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempRePortCase_Project接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchTempRePortCase_Project(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempRunERRePortCase接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchTempRunERRePortCase(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempRunERRePortCaseEntry接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchTempRunERRePortCaseEntry(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempRunERRePortCase_Project接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchTempRunERRePortCase_Project(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempRunRePortCase接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchTempRunRePortCase(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempRunRePortCaseEntry接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchTempRunRePortCaseEntry(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * FetchTempRunRePortCase_Project接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async FetchTempRunRePortCase_Project(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
    }

    /**
     * Select接口方法
     *
     * @param {*} [context={}]
     * @param {*} [data={}]
     * @param {boolean} [isloading]
     * @returns {Promise<any>}
     * @memberof CaseServiceBase
     */
    public async Select(context: any = {},data: any = {}, isloading?: boolean): Promise<any> {
        if(context.product && context.story && context.case){
            let res:any = await Http.getInstance().get(`/products/${context.product}/stories/${context.story}/cases/${context.case}/select`,isloading);
            
            return res;
        }
        if(context.story && context.case){
            let res:any = await Http.getInstance().get(`/stories/${context.story}/cases/${context.case}/select`,isloading);
            
            return res;
        }
        if(context.product && context.case){
            let res:any = await Http.getInstance().get(`/products/${context.product}/cases/${context.case}/select`,isloading);
            
            return res;
        }
            let res:any = await Http.getInstance().get(`/cases/${context.case}/select`,isloading);
            
            return res;
    }
}