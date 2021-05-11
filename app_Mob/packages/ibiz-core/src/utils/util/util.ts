import Schema from 'async-validator';
import { clone } from "ramda";
import { AppCapacitorService, ThirdPartyService } from '../../service';

/**
 * 平台工具类
 *
 * @export
 * @class Util
 */
export class Util {
    /**
     * 创建 UUID
     *
     * @static
     * @returns {string}
     * @memberof Util
     */
    public static createUUID(): string {
        function s4() {
            return Math.floor((1 + Math.random()) * 0x10000)
                .toString(16)
                .substring(1);
        }
        return s4() + s4() + '-' + s4() + '-' + s4() + '-' + s4() + '-' + s4() + s4() + s4();
    }

    /**
     * 判断对象是否为空，避免发生数值0误判
     *
     * @param obj
     */
    public static isExist(obj: any): boolean {
        return obj !== undefined && obj !== null;
    }

    /**
     * 字符串不为空并且对象存在
     *
     * @param str
     */
    public static isExistAndNotEmpty(str: string | undefined | null): boolean {
        return this.isExist(str) && !this.isEmpty(str);
    }

    /**
     * 计算单词复数
     *
     * @static
     * @returns {string}
     * @memberof Util
     */
    public static srfpluralize(word: string) {
        // if(/(s|x|sh|ch|o)$/.test(word)) return word+"es";
        // if(/[^aeiou]y$/.test(word)) return word.replace(/y$/,"ies");
        // if(/(fe?)$/.test(word)) return word.replace(/(fe?)$/,"ves");
        // if(/(an)$/.test(word)) return word.replace(/(an)$/,"en");
        // if(/oo/.test(word)) return word.replace(/oo/,"ee");
        if (Object.is(word.substring(word.length - 2), 'es')) {
            return word;
        }
        if (Object.is(word.substring(word.length - 2), 'CS')) {
            return word;
        }
        if (Object.is(word.substring(word.length - 2), 'EN')) {
            return word;
        }
        if (Object.is(word[word.length - 1], 'Y')) {
            return word.replace(/Y$/, "ies");
        }
        if (Object.is(word[word.length - 1], 'y')) {
            return word.replace(/y$/, "ies");
        }
        if (Object.is(word[word.length - 1], 's')) {
            return word + "es";
        }
        return word + "s";
    }

    /**
     * 创建序列号
     *
     * @static
     * @returns {number}
     * @memberof Util
     */
    public static createSerialNumber(): number {
        function s4() {
            return Math.floor((1 + Math.random()) * 0x10000);
        }
        return s4();
    }

    /**
     * 判断是否为一个函数
     *
     * @static
     * @param {*} func
     * @returns {boolean}
     * @memberof Util
     */
    public static isFunction(func: any): boolean {
        return typeof func === 'function';
    }

    /**
     *
     *
     * @static
     * @param {*} [o={}]
     * @memberof Util
     */
    public static processResult(o: any = {}): void {
        if (o.url != null && o.url !== '') {
            window.location.href = o.url;
        }
        if (o.code != null && o.code !== '') {
            // tslint:disable-next-line:no-eval
            eval(o.code);
        }

        if (o.downloadurl != null && o.downloadurl !== '') {
            const downloadurl = this.parseURL2(o.downloadurl, '');
            this.download(downloadurl);
        }
    }

    /**
     * 下载文件
     *
     * @static
     * @param {string} url
     * @memberof Util
     */
    public static download(url: string): void {
        window.open(url, '_blank');
    }

    /**
     *
     *
     * @static
     * @param {any} url
     * @param {any} params
     * @returns {string}
     * @memberof Util
     */
    public static parseURL2(url: string, params: any): string {
        let tmpURL;
        if (url.indexOf('../../') !== -1) {
            tmpURL = url.substring(url.indexOf('../../') + 6, url.length);
        } else if (url.indexOf('/') === 0) {
            tmpURL = url.substring(url.indexOf('/') + 1, url.length);
        } else {
            tmpURL = url;
        }

        if (params) {
            return tmpURL + (url.indexOf('?') === -1 ? '?' : '&');
        } else {
            return tmpURL;
        }
    }

    /**
     * 是否是数字值(包括可转换的,已排除了空值)
     *
     * @param {*} num
     * @returns {boolean}
     * @memberof Util
     */
    public static isNumber(num: any): boolean {
        return !this.isEmpty(num) && !isNaN(num);
    }

    /**
     * 是否未定义
     *
     * @static
     * @param {*} value
     * @returns {boolean}
     * @memberof Util
     */
    public static isUndefined(value: any): boolean {
        return typeof value === 'undefined';
    }

    /**
     * 是否为空
     *
     * @static
     * @param {*} value
     * @returns {boolean}
     * @memberof Util
     */
    public static isEmpty(value: any): boolean {
        return this.isUndefined(value) || Object.is(value, '') || value === null || value !== value;
    }

    /**
     * 转换为矩阵参数
     *
     * @static
     * @param {*} obj
     * @returns {*}
     * @memberof Util
     */
    public static formatMatrixStringify(obj: any): any {
        let str: string = '';
        if (obj && !(obj instanceof Array) && obj instanceof Object) {
            const keys: string[] = Object.keys(obj);
            keys.forEach((key: string) => {
                if (!obj[key]) {
                    return;
                }
                if (!Object.is(str, '')) {
                    str += ';';
                }
                str += `${key}=${obj[key]}`;
            });
        }
        return Object.is(str, '') ? undefined : str;
    }

    /**
     * 准备路由参数
     *
     * @static
     * @param {*} { route: route, sourceNode: sourceNode, targetNode: targetNode, data: data }
     * @returns {*}
     * @memberof Util
     */
    public static prepareRouteParmas({
        route: route,
        sourceNode: sourceNode,
        targetNode: targetNode,
        data: data,
    }: any): any {
        const params: any = {};
        if (!sourceNode || (sourceNode && Object.is(sourceNode, ''))) {
            return params;
        }
        if (!targetNode || (targetNode && Object.is(targetNode, ''))) {
            return params;
        }
        const indexName = route.matched[0].name;
        Object.assign(params, { [indexName]: route.params[indexName] });
        Object.assign(params, { [targetNode]: this.formatMatrixStringify(data) });
        return params;
    }

    /**
     * 获取当前值类型
     *
     * @static
     * @param {*} obj
     * @returns
     * @memberof Util
     */
    public static typeOf(obj: any): string {
        const toString = Object.prototype.toString;
        const map: any = {
            '[object Boolean]': 'boolean',
            '[object Number]': 'number',
            '[object String]': 'string',
            '[object Function]': 'function',
            '[object Array]': 'array',
            '[object Date]': 'date',
            '[object RegExp]': 'regExp',
            '[object Undefined]': 'undefined',
            '[object Null]': 'null',
            '[object Object]': 'object',
        };
        return map[toString.call(obj)];
    }

    /**
     * 深拷贝(deepCopy)
     *
     * @static
     * @param {*} data
     * @returns {*}
     * @memberof Util
     */
    public static deepCopy(data: any): any {
        return clone(data);
    }

    /**
     * 名称格式化
     *
     * @static
     * @param {string} name
     * @returns {string}
     * @memberof Util
     */
    public static srfFilePath2(name: string): string {
        if (!name || (name && Object.is(name, ''))) {
            console.error('名称异常');
            return '';
        }
        name = name.replace(/[_]/g, '-');
        let state: number = 0;
        let _str = '';
        const uPattern = /^[A-Z]{1}$/;

        const str1 = name.substring(0, 1);
        const str2 = name.substring(1);
        state = uPattern.test(str1) ? 1 : 0;
        _str = `${_str}${str1.toLowerCase()}`;

        for (let chr of str2) {
            if (uPattern.test(chr)) {
                if (state === 1) {
                    _str = `${_str}${chr.toLowerCase()}`;
                } else {
                    _str = `${_str}-${chr.toLowerCase()}`;
                }
                state = 1;
            } else {
                _str = `${_str}${chr.toLowerCase()}`;
                state = 0;
            }
        }
        _str = _str.replace(/---/g, '-').replace(/--/g, '-');

        return _str;
    }

    /**
     * 附加参数格式化
     *
     * @static
     * @param {any} arg 表单数据
     * @param {any} parent 外层context或viewparams
     * @param {any} params 附加参数
     * @returns {any}
     * @memberof Util
     */
    public static formatData(arg: any, parent: any, params: any): any {
        let _data: any = {};
        Object.keys(params).forEach((name: string) => {
            if (!name) {
                return;
            }
            let value: string | null = params[name];
            if (value && value.startsWith('%') && value.endsWith('%')) {
                const key = value.substring(1, value.length - 1);
                if (arg && arg.hasOwnProperty(key)) {
                    if (arg[key] !== null && arg[key] !== undefined) {
                        value = arg[key];
                    } else if (parent[key] !== null && parent[key] !== undefined) {
                        value = parent[key];
                    } else {
                        value = null;
                    }
                } else {
                    value = null;
                }
            }
            Object.assign(_data, { [name]: value });
        });
        return _data;
    }


    /**
     * 格式化导航数据
     *
     * @static
     * @param {any} data 导航模型原生数据
     * @memberof Util
     */
    public static formatNavParam(navParam: any, isConvertToLower: boolean = false) {
        if (!navParam) {
            return;
        }
        let localNavparam: any = {};
        if (navParam && navParam.length > 0) {
            navParam.forEach((element: any) => {
                localNavparam[element.key.toLowerCase()] = element.rawValue ? element.value : isConvertToLower ? `%${element.value.toLowerCase()}%` : `%${element.value}%`;
            });
        }
        return localNavparam;
    }

    /**
     * 格式化视图关系参数
     *
     * @static
     * @param {any} context 应用上下文数据
     * @param {any} appDERSPaths 关系路径数据
     * @memberof Util
     */
    public static formatAppDERSPath(context: any, appDERSPaths: any) {
        if (!appDERSPaths || appDERSPaths.length === 0) {
            return [];
        }
        let counter: number = 0;
        for (const appDERSPath of appDERSPaths) {
            let tempData: any = { isInclude: true, data: [] };
            for (const singleAppDERSPath of appDERSPath) {
                const majorPSAppDataEntity = singleAppDERSPath.getMajorPSAppDataEntity?.();
                if (majorPSAppDataEntity && majorPSAppDataEntity.codeName) {
                    tempData.isInclude = context[majorPSAppDataEntity.codeName.toLowerCase()] && tempData.isInclude;
                    tempData.data.push({ pathName: this.srfpluralize(majorPSAppDataEntity.codeName).toLowerCase(), parameterName: majorPSAppDataEntity.codeName.toLowerCase() });
                }
            }
            counter++;
            if (tempData.isInclude) {
                return tempData.data;
            } else {
                if (counter === appDERSPaths.length) {
                    return [];
                }
            }
        }
    }

    /**
     * 计算导航数据
     * 先从当前数据目标计算，然后再从当前上下文计算，最后从当前视图参数计算，没有则为null
     *
     * @static
     * @param {any} data 表单数据
     * @param {any} parentContext 外层context
     * @param {any} parentParam 外层param
     * @param {any} params 附加参数
     * @returns {any}
     * @memberof Util
     */
    public static computedNavData(data: any, parentContext: any, parentParam: any, params: any): any {
        let _data: any = {};
        if (params && Object.keys(params).length > 0) {
            Object.keys(params).forEach((name: string) => {
                if (!name) {
                    return;
                }
                let value: string | null = params[name];
                if (value && value.toString().startsWith('%') && value.toString().endsWith('%')) {
                    const key = value.substring(1, value.length - 1).toLowerCase();
                    if (data && data.hasOwnProperty(key)) {
                        value = data[key];
                    } else if (parentContext && parentContext[key]) {
                        value = parentContext[key];
                    } else if (parentParam && parentParam[key]) {
                        value = parentParam[key];
                    } else {
                        value = null;
                    }
                }
                Object.assign(_data, { [name.toLowerCase()]: value });
            });
        }
        return _data;
    }

    /**
     * 日期格式化
     *
     * @static
     * @param {string} fmt 格式化字符串
     * @param {any} date 日期对象
     * @returns {string}
     * @memberof Util
     */
    public static dateFormat(date: any, fmt: string = 'YYYY-mm-dd HH:MM:SS'): string {
        let ret;
        const opt: any = {
            'Y+': date.getFullYear().toString(), // 年
            'm+': (date.getMonth() + 1).toString(), // 月
            'd+': date.getDate().toString(), // 日
            'H+': date.getHours().toString(), // 时
            'M+': date.getMinutes().toString(), // 分
            'S+': date.getSeconds().toString(), // 秒
            // 有其他格式化字符需求可以继续添加，必须转化成字符串
        };
        for (let k in opt) {
            ret = new RegExp('(' + k + ')').exec(fmt);
            if (ret) {
                fmt = fmt.replace(ret[1], ret[1].length == 1 ? opt[k] : opt[k].padStart(ret[1].length, '0'));
            }
        }
        return fmt;
    }

    /**
     * 深度合并对象
     *
     * @param FirstOBJ 目标对象
     * @param SecondOBJ 原对象
     * @returns {Object}
     * @memberof Util
     */
    public static deepObjectMerge(FirstOBJ: any, SecondOBJ: any) {
        for (var key in SecondOBJ) {
            FirstOBJ[key] =
                FirstOBJ[key] && FirstOBJ[key].toString() === '[object Object]'
                    ? this.deepObjectMerge(FirstOBJ[key], SecondOBJ[key])
                    : (FirstOBJ[key] = SecondOBJ[key]);
        }
        return FirstOBJ;
    }

    /**
     * 表单项校验
     *
     * @param property 表单项属性名
     * @param data 表单数据
     * @param rules 表单值规则
     * @returns {Promise}
     * @memberof Util
     */
    public static validateItem(property: string, data: any, rules: any) {
        // 1.获取数值和规则
        const rule = rules[property];
        // 2.创建校验规则
        const schema = new Schema({ [property]: rule });
        // 校验返回Promise
        return schema.validate(data);
    }

    /**
     * 设置cookie
     *
     * @memberof Util
     */
    public static setCookie(name: any, value: any, day: any) {
        if (day !== 0) {
            //当设置的时间等于0时，不设置expires属性，cookie在浏览器关闭后删除
            let curDate = new Date();
            let curTamp = curDate.getTime();
            let curWeeHours = new Date(curDate.toLocaleDateString()).getTime() - 1;
            let passedTamp = curTamp - curWeeHours;
            let leftTamp = 24 * 60 * 60 * 1000 - passedTamp;
            let leftTime = new Date();
            leftTime.setTime(leftTamp + curTamp);
            document.cookie = name + '=' + escape(value) + ';expires=' + leftTime.toUTCString();
        } else {
            document.cookie = name + '=' + escape(value);
        }
    }

    /**
     * 获取cookie
     *
     * @memberof Util
     */
    public static getCookie(name: any): any {
        let arr;
        let reg = new RegExp('(^| )' + name + '=([^;]*)(;|$)');
        if ((arr = document.cookie.match(reg))) return unescape(arr[2]);
        else return null;
    }

    /**
     * 比较两个对象的属性是否都相等
     *
     * @static
     * @param {*} newVal
     * @param {*} oldVal
     * @returns
     * @memberof Util
     */
    public static isFieldsSame(newVal: any, oldVal: any) {
        try {
            // 两者不同看属性是否相同，先判断个数，后判断值
            if (Object.keys(newVal).length !== Object.keys(oldVal).length) {
                return false;
            }
            for (const field of Object.keys(newVal)) {
                if (newVal[field] !== oldVal[field]) {
                    return false;
                }
            }
            return true;
        } catch (error) {
            // 报错说明不是对象，直接判断是否相等
            return newVal === oldVal;
        }
    }

    /**
     * 把context和viewparams转换成视图用的viewdata和viewparam参数
     *
     * @static
     * @param {*} context
     * @param {*} viewparams
     * @returns
     * @memberof Util
     */
    public static getViewProps(context: any, viewparams: any) {
        return { viewdata: JSON.stringify(context), viewparam: JSON.stringify(viewparams) };
    }

    /**
     * 给定值在数组查找目标元素
     *
     * @static
     * @param {Array<any>} array 目标数组
     * @param {string} field 属性
     * @param {*} value 值
     * @returns
     * @memberof Util
     */
    public static findElementByField(array: Array<any>, field: string, value: any) {
        if (!array || !field || !value) {
            return null;
        }
        return array.find((element: any) => {
            return element[field] == value;
        })
    }

    /**
     * 当前用户设备是否为安卓
     * 
     * @returns {boolean}
     * @memberof Util
     */
    public static isAndroid(): boolean {
        let u: any = navigator.userAgent;
        return u.indexOf('Android') > -1 || u.indexOf('Adr') > -1;
    }

    /**
     * 获取多语言资源
     *
     * @param {*} _this 获取 多语言的this对象
     * @param {string} tag 多语言标识
     * @param {*} _defalut 无多语言的默认值
     * @return {*} 
     * @memberof Util
     */
    public static getI18n(_this: any, srfCaption: string, dataInfo: string, _defalut: any): string {
        return !(_this.$t(srfCaption) === srfCaption) ? _this.$t(srfCaption) + dataInfo : _defalut;
    }


    /**
     * 退出应用
     *
     * @static
     * @memberof Util
     */
    public static exitApp() {
        if(ThirdPartyService.getInstance().platform){
            ThirdPartyService.getInstance().thirdPartyEvent('close');
        }else{
            AppCapacitorService.getInstance().exitApp();
        }
    }
}
/**
 * 创建 UUID
 *
 * @export
 * @return {*}  {string}
 */
(window as any).___IDCount = 0;

export function getPrimaryKey(): number {
    return (window as any).___IDCount++;
}

export function createUUID(): string {
    return 'ibz-design-' + getPrimaryKey();
}

/**
 * 设置cookie
 *
 * @static
 * @param {*} name 名称
 * @param {*} value 值
 * @param {*} day 过期天数
 * @param {boolean} [isDomain=false] 是否设置在主域下
 * @param {string} [path='/'] 默认归属路径
 * @memberof Util
 */
export const setCookie = function (name: string, value: string, day = 0, isDomain = false, path = '/'): void {
    let domain = '';
    // 设置cookie到主域下
    if (isDomain) {
        // 是否为ip正则
        const regExpr = /^(25[0-5]|2[0-4]\d|[0-1]\d{2}|[1-9]?\d)\.(25[0-5]|2[0-4]\d|[0-1]\d{2}|[1-9]?\d)\.(25[0-5]|2[0-4]\d|[0-1]\d{2}|[1-9]?\d)\.(25[0-5]|2[0-4]\d|[0-1]\d{2}|[1-9]?\d)$/;
        // 为ip时忽略
        if (!regExpr.test(location.hostname)) {
            const host = location.hostname;
            if (host.indexOf('.') !== host.lastIndexOf('.')) {
                domain = ';domain=' + host.substring(host.indexOf('.'), host.length);
            }
        }
    }
    // 当设置的时间等于0时，不设置expires属性，cookie在浏览器关闭后删除
    if (day !== 0) {
        const expires = day * 24 * 60 * 60 * 1000;
        const date = new Date(new Date().getTime() + expires);
        document.cookie = `${name}=${escape(value)};path=${path};expires=${date.toUTCString()}${domain}`;
    } else {
        document.cookie = `${name}=${escape(value)};path=${path}${domain}`;
    }
};

/**
 * 清除cookie
 *
 * @static
 * @param {string} cookieName
 * @param {boolean} [isDomain] 是否在主域下
 * @memberof Util
 */
export const clearCookie = function (cookieName: string, isDomain?: boolean): void {
    setCookie(cookieName, '', -1, isDomain);
};

/**
 * 设置sessionStorage数据
 *
 */
export const setSessionStorage: Function = (key: string, value: any) => {
    if (!value) {
        return;
    }
    sessionStorage.setItem(key, JSON.stringify(value));
}

/**
 * 获取sessionStorage数据
 *
 */
export const getSessionStorage: Function = (key: string) => {
    if (!key) {
        return null;
    }
    let value = sessionStorage.getItem(key);
    if (value) {
        return JSON.parse(value);
    } else {
        return value;
    }
}

/**
 * 获取cookie
 *
 * @static
 * @param {string} name
 * @return {*}  {*}
 * @memberof Util
 */
export const getCookie = function (name: string): string | null {
    const reg = new RegExp('(^| )' + name + '=([^;]*)(;|$)');
    const arr = document.cookie.match(reg);
    if (arr && arr.length > 1) {
        return unescape(arr[2]);
    }
    return null;
};
