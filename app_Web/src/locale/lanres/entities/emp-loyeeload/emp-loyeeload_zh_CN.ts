import EmpLoyeeload_zh_CN_Base from './emp-loyeeload_zh_CN_base';

function getLocaleResource(){
    const EmpLoyeeload_zh_CN_OwnData = {};
    const targetData = Object.assign(EmpLoyeeload_zh_CN_Base(), EmpLoyeeload_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;