import PSSystemDBCfg_en_US_Base from './pssystem-dbcfg_en_US_base';

function getLocaleResource(){
    const PSSystemDBCfg_en_US_OwnData = {};
    const targetData = Object.assign(PSSystemDBCfg_en_US_Base(), PSSystemDBCfg_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;