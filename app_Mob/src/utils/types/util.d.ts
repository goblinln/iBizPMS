import { Route } from 'vue-router';
import Vue, { VNode, CreateElement } from "vue";

/**
 * 工具类
 *
 * @export
 * @interface Util
 */
export declare interface Util {
    /**
     * 创建 UUID
     *
     * @returns {string}
     * @memberof Util
     */
    createUUID(): string;
    /**
     * 创建序列号
     *
     * @returns {number}
     * @memberof Util
     */
    createSerialNumber(): number
    /**
     * 判断是否为一个函数
     *
     * @param {*} func
     * @returns {boolean}
     * @memberof Util
     */
    isFunction(func: any): boolean;
    /**
     * 处理请求结果
     *
     * @param {*} o
     * @memberof Util
     */
    processResult(o: any): void;
    /**
     * 下载文件
     *
     * @param {string} url
     * @memberof Util
     */
    download(url: string): void;
    /**
     * 
     *
     * @param {string} url
     * @param {*} params
     * @returns {string}
     * @memberof Util
     */
    parseURL2(url: string, params: any): string;
    /**
     * 是否是数字
     *
     * @param {*} num
     * @returns {boolean}
     * @memberof Util
     */
    isNumberNaN(num: any): boolean;
    /**
     * 是否未定义
     *
     * @param {*} value
     * @returns {boolean}
     * @memberof Util
     */
    isUndefined(value: any): boolean;
    /**
     * 是否为空
     *
     * @param {*} value
     * @returns {boolean}
     * @memberof Util
     */
    isEmpty(value: any): boolean;
    /**
     * 转换为矩阵参数
     *
     * @param {*} obj
     * @returns {*}
     * @memberof Util
     */
    formatMatrixStringify(obj: any): any;
    /**
     * 准备路由参数
     *
     * @param {*} { route: route, sourceNode: sourceNode, targetNode: targetNode, data: data }
     * @returns {*}
     * @memberof Util
     */
    prepareRouteParmas({ route: route, sourceNode: sourceNode, targetNode: targetNode, data: data }: any): any;
    /**
     * 获取当前值类型
     *
     * @param {*} obj
     * @returns {string}
     * @memberof Util
     */
    typeOf(obj: any):string;
    /**
     * 深拷贝(deepCopy)
     *
     * @param {*} data
     * @returns {*}
     * @memberof Util
     */
    deepCopy(data: any): any;
    /**
     * 名称格式化
     *
     * @param {string} name
     * @returns {string}
     * @memberof Util
     */
    srfFilePath2(name: string): string;
    
   /**
     * 深度合并对象
     * 
     * @param FirstOBJ 目标对象
     * @param SecondOBJ 原对象
     * @returns {Object}
     * @memberof Util
     */
    deepObjectMerge(FirstOBJ:any, SecondOBJ:any) :any;

    /**
     * 日期格式化
     *
     * @static
     * @param {string} fmt 格式化字符串
     * @param {any} date 日期对象
     * @returns {string}
     * @memberof Util
     */
    dateFormat(date: any,fmt?: string):string ;
}