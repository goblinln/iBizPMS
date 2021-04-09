import PSSystemDBCfg_zh_CN_Base from './pssystem-dbcfg_zh_CN_base';

function getLocaleResource(){
    const PSSystemDBCfg_zh_CN_OwnData = {};
    const targetData = Object.assign(PSSystemDBCfg_zh_CN_Base(), PSSystemDBCfg_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;