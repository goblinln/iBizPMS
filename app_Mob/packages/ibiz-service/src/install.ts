import { IBzDynamicConfig } from './types';
import { GlobalService } from './service/global.service';

/**
 * 安装插件
 *
 * @param config 配置
 */
export const install = function (config: IBzDynamicConfig): void {
    // 避免出现重复实例化
    if (window.___ibz___api_init === true) {
        return;
    }
    window.___ibz___api_init = true;
    window.___ibz___ = {
        // 全局实体服务
        gs: new GlobalService(),
        sc: new Map(),
    };
    window.IBzDynamicConfig = {};
    if (!config) {
        return;
    }
    window.___ibz___GlobalHeaders = new Headers();
    const cfg = window.IBzDynamicConfig;
    if (config.baseUrl) {
        cfg.baseUrl = config.baseUrl;
    }
};

/**
 * 全局新增请求Header
 *
 * @param key
 * @param val
 */
export const addGlobalHeader = function (key: string, val: string): void {
    window.___ibz___GlobalHeaders.set(key, val);
};

/**
 * 删除全局Header
 *
 * @param key
 * @param val
 */
export const removeGlobalHeader = function (key: string): void {
    window.___ibz___GlobalHeaders.delete(key);
};
